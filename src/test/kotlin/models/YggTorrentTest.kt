package models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class YggTorrentTest {

    private val randomFile = listOf<String>(
        "<div class=\"hidden\">2179</div><a href=\"https://www2.yggtorrent.pe/engine/url_builder?type=query&amp;source=sub_category&amp;id=2179\"><span class=\"tag_subcat_2179\"></a></span>",
        "<a href=\"https://www2.yggtorrent.pe/torrent/filmvideo/animation-serie/515360-the-boondocks-s04-vosta-dvdrip-mpeg2-ac3\">The.Boondocks.S04.VOSTA.DVDRip.MPEG2.AC3</a>",
        "<a target=\"515360\" id=\"get_nfo\"\"><img src=\"https://www2.yggtorrent.pe/static/img/nfo.gif\">",
        "1",
        "<div class=\"hidden\">1571559356</div>4 heures",
        "<div class=\"hidden\">9272148554</div>8.64Go",
        "1",
        "3",
        "5"
    )

    @Test
    fun `check url`() {
        val randomObject = YggTorrent.fromList(randomFile)
        assertThat(randomObject.url).isEqualTo("https://www2.yggtorrent.pe/torrent/filmvideo/animation-serie/515360-the-boondocks-s04-vosta-dvdrip-mpeg2-ac3")
    }

}