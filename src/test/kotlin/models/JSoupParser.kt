package models

import models.mock.MockHtmlResponse
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test

class JSoupParser {

    @Test
    fun shouldParseYGGHTML() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://www2.yggtorrent.pe/engine/search?name=how+to+get+away&do=search").get().run {

            val elements = this.getElementsByClass("table-responsive results")

            // Get Node who hold every results
            val childNodes = elements.first().childNodes().filterIsInstance<Element>()
                .first().childNodes().filterIsInstance<Element>().first { it.tagName() == "tbody" }
                .childNodes().filterIsInstance<Element>()

            // List to hold all Objects
            val items = mutableListOf<YggTorrent>()

            // Extract all objects found
            childNodes.forEach { element ->
                val tempElement = element.childNodes().filterIsInstance<Element>()

                val informations = mutableListOf<String>()

                tempElement.forEach { subElement ->
                    informations.add(subElement.toString().replace("\n", ""))
                }

                // Create object & Save it
                val fromListHtml = YggTorrent.fromListHtml(informations)
                items.add(fromListHtml)
            }
        }
    }

    @Test
    fun parseYtlHtml() {

        var items = mutableListOf<YTL>()

        Jsoup.connect("https://yts.lt/browse-movies/lion%20king/").get().run {

            val elements = this.getElementsByClass("browse-movie-wrap")

            elements.forEach { element ->


                println()
            }

            println()
        }
    }

    @Test
    fun leetX() {

        /*val url = "https://1337x.to/search/lion+king/1/"

        val request = LeetXRepository.makeRequest(url, "")*/

        //  if (request.code == 200) {

        // Mock


        // val body = request.body?.string()

        Jsoup.parse(MockHtmlResponse.leetX()).run {

            val elementsByClass = this.getElementsByClass("table-list")
            val elements = elementsByClass.first().childNodes().filterIsInstance<Element>()
                .filter { it.tag().normalName() == "tbody" }
                .first()
                .childNodes().filterIsInstance<Element>()

            val leetXs = mutableListOf<LeetX>()

            elements.forEach { element ->
                val tempList = mutableListOf<String>()
                // Elements information extraction here
                element.childNodes().filterIsInstance<Element>().forEach {
                    // Store in a listOf<String>()
                    tempList.add(it.toString().replace("\n", ""))
                }
                // Create a true LeetX object and store it
                //leetXs.add(LeetX.fromHtml())
                println()
            }

            println()

        }
        //  }

        println()

    }
}