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
                .first().childNodes().filterIsInstance<Element>().first { it.tagName() == "tbody" }
                .childNodes().filterIsInstance<Element>()

            // List to hold all Objects
            val objects = mutableListOf<YggTorrent>()

            // Extract all objects found
            childNodes.forEach { element ->
                val tempElement = element.childNodes().filterIsInstance<Element>()

                val informations = mutableListOf<String>()

                tempElement.forEach { subElement ->
                    informations.add(subElement.toString().replace("\n", ""))
                }

                // Create object
                val fromListJson = YggTorrent.fromListJson(informations)


                println()
            }


            println(elements)

        }
    }
}