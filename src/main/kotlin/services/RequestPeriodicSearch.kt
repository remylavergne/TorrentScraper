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

    private const val REQUEST_DIRECTORY = "requests"

    private val temporaryData = mutableMapOf<SavedRequest, List<Torrent>>()
    private val currentRequestsSaved = mutableListOf<File>()
    private val savedRequests = mutableListOf<SavedRequest>()

    /**
     * Save the current request with all links found before
     * This method override existing file with the same name
     */
    fun saveARequest(request: String, results: List<Torrent>): Boolean {

        val newRequest =
            SavedRequest(request = request, allResults = results.map { it.url })

        try {
            File("requests").mkdir()
            val newFile = File(REQUEST_DIRECTORY + "/" + request.replace(' ', '-') + ".json")
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

            val currentJob = CoroutineScope(Dispatchers.IO).launch {
                AllRepositories.values().forEach { repository ->
                    async {
                        val response = repository.server.search(savedRequest.request)
                        temporaryData[savedRequest] = response // TODO : /!\ La clef est écrasée car non unique !!!
                    }
                }
            }
            currentJob.join()
        }

        getUpdateForEachFile()
    }

    private fun getSaveRequestsFile() {

        // TODO: Vérifier que le dossier requests existe !

        this.currentRequestsSaved.clear()

        File(REQUEST_DIRECTORY).walkTopDown().forEach { file ->
            if (file.isFile) {
                convertFileToObject(file)
            }
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