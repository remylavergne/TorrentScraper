package repositories

import models.LEETX_URL
import models.LeetX
import models.Torrent
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object LeetXRepository : BaseRepository() {

    override val name: String = "1337x"

    override suspend fun search(request: String): List<Torrent> {

        val leetXs = mutableListOf<LeetX>()
        val url = "https://1337x.to/search/$request/1/"
        val response = makeRequest(url, "")

        if (response.code == 200) {
            val body = response.body?.string()

            Jsoup.parse(body).run {
                /*// Pagination
                val paginationByClass = this.getElementsByClass("pagination")
                val pagination = paginationByClass.first().childNodes().filterIsInstance<Element>().first().tagName("ul")
                    .childNodes()
                    .filterIsInstance<Element>().filter { it.hasAttr("class") }.first().childNodes()
                    .filterIsInstance<Element>()
                    .toString()*/

                // Object
                val elementsByClass = this.getElementsByClass("table-list")
                val elements = elementsByClass.first().childNodes().filterIsInstance<Element>()
                    .first { it.tag().normalName() == "tbody" }
                    .childNodes().filterIsInstance<Element>()

                elements.forEach { element ->
                    val tempList = mutableListOf<String>()
                    // Elements information extraction here
                    element.childNodes().filterIsInstance<Element>().forEach {
                        // Store in a listOf<String>()
                        tempList.add(it.toString().replace("\n", ""))
                    }
                    // Create a true LeetX object and store it
                    leetXs.add(LeetX.fromHtml(tempList))
                }
            }
        }
        return leetXs
    }

    override suspend fun checkServerStatus(): Boolean {
        val response = makeRequest(LEETX_URL, "")
        return response.code == 200
    }
}
