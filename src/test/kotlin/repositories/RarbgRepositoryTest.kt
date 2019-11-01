package repositories

import models.Rarbg
import models.Torrent
import models.mock.MockHtmlResponse
import org.assertj.core.api.Assertions.assertThat
import org.jsoup.Jsoup
import org.junit.jupiter.api.Test

class RarbgRepositoryTest {

    @Test
    fun `search method`() {
        val url = "https://rarbgmirror.com/torrents.php?search=lion+king&order=seeders&by=DESC"
        //val makeRequest = RarbgRepository.makeRequest(url, "")

        val torrents = mutableListOf<Torrent>()

        Jsoup.parse(MockHtmlResponse.rarbg()).run {

            val elementsByClass = this.getElementsByClass("lista2")

            elementsByClass.forEach { element ->
                val html = element.toString()
                val newElement = Rarbg.fromHtml(html)
                torrents.add(newElement)
            }

        }
        assertThat(torrents.count()).isEqualTo(25)

    }



}