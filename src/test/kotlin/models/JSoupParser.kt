package models

import models.mock.MockHtmlResponse
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test
import repositories.YggRepository

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
    fun leetX() {

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

    @Test
    fun `parse the pirate bay`() {

    }

    @Test
    fun `parse rarbg`() {
        val url = "https://eztv.io/search/how-to-get-away"


        val makeRequest = YggRepository.makeRequest(url, "")

        makeRequest.body?.string()?.let { body ->

            Jsoup.parse(body).run {

                val elementsByClass = this.getElementsByClass("forum_header_border")

                if (elementsByClass.isNotEmpty()) {
                    val filter = elementsByClass.filter { it.tagName() == "tr" }

                    filter.forEach { element ->
                        var temporaryHtml = ""
                        element.childNodes().filterIsInstance<Element>().forEach { informations ->
                            temporaryHtml += informations.toString()
                        }
                        // All informations necessary to build an object
                        println()
                    }

                }



                println()


            }

        }





    }
}