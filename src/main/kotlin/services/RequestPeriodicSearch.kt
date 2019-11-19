package services

import enums.AllRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import models.SavedRequest
import models.Torrent
import java.io.File


object RequestPeriodicSearch {

    private val temporaryData = mutableMapOf<SavedRequest, List<Torrent>>()
    private val requestDir = "requests"
    private val currentRequestsSaved = mutableListOf<File>()
    private val savedRequests = mutableListOf<SavedRequest>()

    /**
     * Save the current request with all links found before
     * This method override existing file with the same name
     */
    fun saveARequest(request: String, results: List<Torrent>): Boolean {
        // Create Search Object
        val newRequest =
            SavedRequest(request = request, allResults = results.map { it.url })
        // Save locally
        try {
            File("requests").mkdir()
            val newFile = File(requestDir + "/" + request.replace(' ', '-') + ".json")
            newFile.writeText(newRequest.toJson())
            return true
        } catch (e: NullPointerException) {
            // TODO: Get Stacktrace ?
        }

        return false
    }

    suspend fun checkSavedRequests() {

        getSaveRequestsFile()

        this.temporaryData.clear()

        this.savedRequests.forEach { savedRequest ->

            CoroutineScope(Dispatchers.IO).launch {
                AllRepositories.values().forEach { repository ->
                    async {
                        val response = repository.server.search(savedRequest.request)
                        temporaryData[savedRequest] = response
                    }
                }
            }
        }

        getUpdateForEachFile()
    }

    private fun getSaveRequestsFile() {

        // TODO: Vérifier que le dossier requests existe !

        this.currentRequestsSaved.clear()

        File(requestDir).walkTopDown().forEach { file ->
            println(file)
            // convertFileToObject(file) // TODO: It's not a single file, but a file with many path
        }
    }

    private fun convertFileToObject(file: File) {
        this.savedRequests.add(SavedRequest.fromFile(file))
    }

    private fun getUpdateForEachFile() {

    }

    fun deleteARequest(request: String) {

    }

}