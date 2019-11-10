package models

import com.google.gson.Gson

data class SavedRequest(
    val createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var request: String,
    var allResults: List<String>,
    var previousResultsHashcode: Int
) {

    fun toJson(): String {
        this.previousResultsHashcode = allResults.hashCode()
        return Gson().toJson(this)
    }
}