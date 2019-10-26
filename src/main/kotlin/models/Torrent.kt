package models

import enums.Category
import enums.SubCategory

abstract class Torrent {
    abstract val id: String
    abstract val category: Category?
    abstract val subCategory: SubCategory?
    abstract val url: String
    abstract val filename: String
    abstract val commentsCount: String
    abstract val elapsedTimestamp: Long
    abstract val size: String
    abstract val completions: String
    abstract val seeders: String
    abstract val leechers: String
    abstract val domain: String
}
