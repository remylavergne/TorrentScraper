package models

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test

class JSoupParser {

    @Test
    fun shouldParseHTML() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://www2.yggtorrent.pe/engine/search?name=how+to+get+away&do=search").get().run {

            val elements = this.getElementsByClass("table-responsive results")

            // Get Node who hold every results
            val childNodes = elements.first().childNodes().filterIsInstance<Element>()
                .first().childNodes().filterIsInstance<Element>()
                .filter { it.tagName() == "tbody" }


            println(elements)

        }
    }
}