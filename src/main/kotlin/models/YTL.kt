package models

import enums.Category
import enums.SubCategory
import java.util.*

data class YTL(
    override val id: String = UUID.randomUUID().toString(),
    override val category: Category? = null,
    override val subCategory: SubCategory? = null,
    override val url: String = "",
    override val filename: String = "",
    override val commentsCount: String = "",
    override val elapsedTimestamp: Long = 0,
    override val size: String = "",
    override val completions: String = "",
    override val seeders: String = "",
    override val leechers: String = "",
    override val domain: String = ""
) : Torrent() {

    companion object {

        fun fromHtml(html: String): YTL {
            return YTL()
        }
    }

}

enum class YtlRegexEnums(val regex: Regex) {
    URL(Regex("")),
    FILENAME(Regex("")),
    DOMAIN(Regex(""))
}