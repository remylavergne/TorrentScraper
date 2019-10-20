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
    val elapsedTimestamp: Long = 0,
    val size: Long = 0,
    val completions: String = "",
    val seeders: String = "",
    val leechers: String = ""
) {

    companion object {

        /**
         * Some endpoints are parse from JSON, and some tags are a little bit different
         */
        fun fromListJson(list: List<String>, category: Category? = null, subCategory: SubCategory? = null): YggTorrent {

            if (list.count() != 9) {
                throw Exception("Wrong list !")
            }

            return YggTorrent(
                url = YggJsonEnums.FILE_DETAILS_URL.regex.find(list[1])?.groupValues?.last() ?: "No url",
                filename = YggJsonEnums.FILENAME.regex.find(list[1])?.groupValues?.last() ?: "No name",
                commentsCount = list[3],
                elapsedTimestamp = YggJsonEnums.ELAPSED_TIME.regex.find(list[4])?.groupValues?.last()?.toLong() ?: 0,
                size = YggJsonEnums.FILE_SIZE.regex.find(list[5])?.groupValues?.last()?.toLong() ?: 0,
                completions = list[6],
                seeders = list[7],
                leechers = list[8]
            )
        }

        fun fromListHtml(list: List<String>, category: Category? = null, subCategory: SubCategory? = null): YggTorrent {
            if (list.count() != 9) {
                throw Exception("Wrong list !")
            }

            return YggTorrent(
                url = YggHtmlEnums.FILE_DETAILS_URL.regex.find(list[1])?.groupValues?.last() ?: "No url",
                filename = YggHtmlEnums.FILENAME.regex.find(list[1])?.groupValues?.last()?.trim() ?: "No name",
                commentsCount = YggHtmlEnums.COMMENTS.regex.find(list[3])?.groupValues?.last()?.trim() ?: "0",
                elapsedTimestamp = YggHtmlEnums.ELAPSED_TIME.regex.find(list[4])?.groupValues?.last()?.toLong() ?: 0,
                size = 0,
                completions = "",
                seeders = "",
                leechers = ""
            )
        }
    }
}

enum class YggJsonEnums(val regex: Regex) {
    FILE_DETAILS_URL(Regex("=\"(.+)\">")),
    FILENAME(Regex("\">(.+)<\\/a>")),
    ELAPSED_TIME(Regex(">(\\d+)<")),
    FILE_SIZE(Regex(">(\\d+)<"))
}

enum class YggHtmlEnums(val regex: Regex) {
    FILE_DETAILS_URL(Regex("href=\"(.+)\">")),
    FILENAME(Regex("\">.+\">(.+)<\\/a>")),
    COMMENTS(Regex("<td>(\\d+)[ ]*<span")),
    ELAPSED_TIME(Regex("class=\"hidden\">[ ]*(\\d+)[ ]*<\\/div>")),
    FILE_SIZE(Regex(">(\\d+)<"))
}

enum class YggEndpoints(val endpoint: String) {
    DAILY("https://www2.yggtorrent.pe/engine/ajax_top_query/day"),
    WEEKLY("https://www2.yggtorrent.pe/engine/ajax_top_query/week"),
    MONTH("https://www2.yggtorrent.pe/engine/ajax_top_query/week"),

}