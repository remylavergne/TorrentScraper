package services

import models.SavedRequest
import models.Torrent
import java.io.File


object RequestPeriodicSearch {

    private val requestDir = "requests/"
    private val currentRequestsSaved = mutableListOf<File>()

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
            val newFile = File(this.requestDir + request.replace(' ', '-') + ".json")
            newFile.writeText(newRequest.toJson())
            return true
        } catch (e: NullPointerException) {
            // TODO: Get Stacktrace ?
        }

        return false
    }

    fun checkSavedRequests() {
        getSaveRequestsFile()
        getUpdateForEachFile()
    }

    private fun getSaveRequestsFile() {
        File(this.requestDir).walk().forEach { file ->
            this.currentRequestsSaved.add(file)
        }
    }

    private fun getUpdateForEachFile() {

    }

    fun deleteARequest(request: String) {

    }

}