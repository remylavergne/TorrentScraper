package models

import enums.Category
import enums.SubCategory

data class ThePirateBay(
    override val id: String,
    override val category: Category?,
    override val subCategory: SubCategory?,
    override val url: String,
    override val filename: String,
    override val commentsCount: String,
    override val elapsedTimestamp: Long,
    override val size: String,
    override val completions: String,
    override val seeders: String,
    override val leechers: String,
    override val domain: String
) : Torrent() {

    companion object {

    }
}