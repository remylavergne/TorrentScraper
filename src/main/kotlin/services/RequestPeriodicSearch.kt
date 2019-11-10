package services

import models.SavedRequest
import models.Torrent
import java.io.File


object RequestPeriodicSearch {

    // TODO: Create a file by request.
    fun saveARequest(request: String, results: List<Torrent>): Boolean {
        // Create Search Object
        val newRequest =
            SavedRequest(request = request, allResults = results.map { it.url }, previousResultsHashcode = 0)

        val newFile = File(request.replace(' ', '-') + ".json")
        newFile.writeText(newRequest.toJson())

        return true
    }

    fun deleteARequest(request: String) {

    }

}