package models

import enums.Category
import enums.SubCategory
import java.util.*

data class YggTorrent(
    val id: String = UUID.randomUUID().toString(),
    val category: enums.Category? = null,
    val subCategory: SubCategory? = null,
    val url: String = "",
    val filename: String = "",
    val commentsCount: String = "",
    val elapsedTimestamp: Double = 0.0,
    val size: String = "",
    val completions: String = "",
    val seeders: String = "",
    val leechers: String = ""
) {

    companion object {
        fun fromList(list: List<String>, category: Category? = null, subCategory: SubCategory? = null): YggTorrent {

            if (list.count() != 9) {
                throw Exception("Wrong list !")
            }

            return YggTorrent(url = YggEnums.FILE_DETAILS_URL.regex.find(list[1])?.groupValues?.last() ?: "")
        }
    }


}

enum class YggEnums(val regex: Regex) {
    FILE_DETAILS_URL(Regex("=\"(.+)\">")),
    FILENAME(Regex("\\\\\">(.+)<\\/a>")),
    ELAPSED_TIME(Regex(">(\\d+)<")),
    FILE_SIZE(Regex("<\\/div>(.+)"))
}