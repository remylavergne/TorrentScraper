package services

import enums.AllRepositories
import kotlinx.coroutines.*
import models.SavedRequest
import models.Torrent
import java.io.File


object RequestPeriodicSearch {

    private const val REQUEST_DIRECTORY = "requests"

    private val temporaryData = mutableMapOf<SavedRequest, List<Torrent>>()
    private val currentRequestsSaved = mutableListOf<File>()
    private val savedRequests = mutableListOf<SavedRequest>()
    private val requestsWithTorrents = mutableListOf<RequestWithTorrents>()

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

    suspend fun checkUpdatesForSavedRequests() {
        getSaveRequestsFile()
        val jobs = makeRequestForEachFile()
        jobs.forEach { it.join() }
        compareIfSomethingIsNew()
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

    private suspend fun makeRequestForEachFile(): List<Job> {

        this.temporaryData.clear()
        val jobs = mutableListOf<Job>()

        this.savedRequests.forEach { savedRequest ->
            val requestWithTorrents = RequestWithTorrents(savedRequest)
            val currentJob = CoroutineScope(Dispatchers.IO).launch {
                AllRepositories.values().forEach { repository ->
                    async {
                        val response = repository.server.search(savedRequest.request)
                        requestWithTorrents.torrents.addAll(response)
                    }
                }
            }
            jobs.add(currentJob)
            requestsWithTorrents.add(requestWithTorrents)
        }
        return jobs
    }

    /**
     * Comparisons between links found on the previous request and the new one
     */
    private fun compareIfSomethingIsNew() {
    
    }

    fun deleteARequest(request: String) {

    }


}

/**
 * Store specific File with all its torrents
 */
data class RequestWithTorrents(
    val savedRequest: SavedRequest,
    val torrents: MutableList<Torrent> = mutableListOf()
)