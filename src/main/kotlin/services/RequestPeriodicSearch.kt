package services

import models.SavedRequest
import models.Torrent
import java.io.File


object RequestPeriodicSearch {

    /**
     * Save the current request with all links found before
     * This method override existing file with the same name
     */
    // TODO: Check if the file doesn't exist first
    fun saveARequest(request: String, results: List<Torrent>): Boolean {
        // Create Search Object
        val newRequest =
            SavedRequest(request = request, allResults = results.map { it.url })
        // Save locally
        try {
            File("requests").mkdir()
            val newFile = File("requests/" + request.replace(' ', '-') + ".json")
            newFile.writeText(newRequest.toJson())
            return true
        } catch (e: NullPointerException) {
            // TODO: Get Stacktrace ?
        }

        return false
    }

    fun deleteARequest(request: String) {

    }

}