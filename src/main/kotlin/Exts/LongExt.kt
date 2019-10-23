package Exts

import java.util.*

fun Long.toDate(): String {
    return Date(this).toString()
}