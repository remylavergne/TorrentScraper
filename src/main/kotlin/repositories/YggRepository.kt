package repositories

import models.Torrent
import models.YggTorrent
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object YggRepository : BaseRepository() {

    override fun search(request: String): List<Torrent> {

        val items = mutableListOf<YggTorrent>()

        Jsoup.connect("https://www2.yggtorrent.pe/engine/search?name=$request&do=search").get().run {

            val elements = this.getElementsByClass("table-responsive results")

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

        return items
    }


}