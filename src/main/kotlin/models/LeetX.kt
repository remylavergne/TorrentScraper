package models

import enums.Category
import enums.SubCategory
import java.util.*

data class LeetX(
    override val id: String = UUID.randomUUID().toString(),
    override val category: Category? = null,
    override val subCategory: SubCategory? = null,
    override val url: String = "",
    override val filename: String = "",
    override val commentsCount: String = "",
    override val elapsedTimestamp: Long = 0,
    override val size: String = "",
    override val completions: String = "0",
    override val seeders: String = "",
    override val leechers: String = "",
    override val domain: String = "1337x.to"
) : Torrent() {

    companion object {

        private const val LEETX_DOMAIN = "https://1337x.to"

        fun fromHtml(html: List<String>): LeetX {

            if (html.count() != 6) {
                throw Exception("Wrong list size!")
            }

            return LeetX(
                url = LEETX_DOMAIN + LeetXRegex.FILE_DETAILS_URL.regex.find(html[0])?.groupValues?.get(1),
                filename = LeetXRegex.FILE_DETAILS_URL.regex.find(html[0])?.groupValues?.get(2) ?: "No name",
                seeders = LeetXRegex.SEEDERS_LEECHERS.regex.find(html[1])?.groupValues?.last() ?: "0",
                leechers = LeetXRegex.SEEDERS_LEECHERS.regex.find(html[2])?.groupValues?.last() ?: "0",
                size = LeetXRegex.SIZE.regex.find(html[4])?.groupValues?.last() ?: "0",
                commentsCount = LeetXRegex.COMMENTS.regex.find(html[0])?.groupValues?.last() ?: "0"
            )
        }
    }
}

enum class LeetXRegex(val regex: Regex) {
    FILE_DETAILS_URL(Regex("<\\/a><a href=\"(.+)\">(.+)<\\/a>")),
    SEEDERS_LEECHERS(Regex(">(\\d+)")),
    SIZE(Regex("\">(.+)<span")),
    COMMENTS(Regex("<\\/i>(\\d+)<\\/span>"))
}