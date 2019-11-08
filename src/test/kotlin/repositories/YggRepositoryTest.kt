package repositories

import models.YggTorrent
import org.assertj.core.api.Assertions.assertThat
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test

class YggRepositoryTest {

    @Test
    fun `check server status`() {
        //assertThat(YggRepository.checkServerStatus()).isTrue()
       // val makeRequest =
            YggRepository.makeRequest("https://www2.yggtorrent.pe/engine/search?name=how+to+get+away&description=&file=&uploader=&category=all&sub_category=&do=search&order=desc&sort=publish_date", "")

        val items = mutableListOf<YggTorrent>()

        Jsoup.connect("https://www2.yggtorrent.pe/engine/search?name=how+to+get+away&description=&file=&uploader=&category=all&sub_category=&do=search&order=desc&sort=publish_date").get().run {

            val elements = this.getElementsByClass("table-responsive results")

            if (elements.isNotEmpty()) {
                // Get Node who hold every results
                val childNodes = elements.first().childNodes().filterIsInstance<Element>()
                    .first().childNodes().filterIsInstance<Element>().first { it.tagName() == "tbody" }
                    .childNodes().filterIsInstance<Element>()

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

        println()
    }
}