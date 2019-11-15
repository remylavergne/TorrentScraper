package models

import com.google.gson.Gson
import java.io.File

data class SavedRequest(
    val createdAt: Long = System.currentTimeMillis(),
    var request: String,
    var allResults: List<String>
) {

    companion object {
        fun fromFile(file: File): SavedRequest {
            val json = file.readText()
            return Gson().fromJson<SavedRequest>(json, SavedRequest::class.java)
        }
    }

    fun toJson(): String = Gson().toJson(this)
}
