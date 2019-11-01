package repositories

import models.LEETX_URL
import models.LeetX
import models.Torrent
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.SocketTimeoutException

object LeetXRepository : BaseRepository() {

    override val name: String = "1337x"

    override suspend fun search(request: String): List<Torrent> {

        val leetXs = mutableListOf<LeetX>()
        val url = "https://1337x.to/search/${request.replace(" ", "+")}/1/"
        var response: Response? = null
        try {
            response = makeRequest(url, "")
        } catch (e: SocketTimeoutException) {

        } finally {

            if (response?.code == 200) {
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
        }

        return leetXs
    }

    override suspend fun checkServerStatus(): Boolean {
        lateinit var response: Response
        try {
            response = makeRequest(LEETX_URL, "")
        } catch (e: Exception) {
            response.close()
        }
        return response.code == 200
    }
}
