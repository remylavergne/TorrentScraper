package models

import com.google.gson.Gson

data class SavedRequest(
    val createdAt: Long = System.currentTimeMillis(),
    var request: String,
    var allResults: List<String>
) {
    fun toJson(): String = Gson().toJson(this)
}
