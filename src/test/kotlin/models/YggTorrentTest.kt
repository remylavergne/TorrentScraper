package models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class YggTorrentTest {

    private val randomFile = listOf(
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

    private val wrongFile = listOf<String>("Not", "Enough", "Informations")
    private val randomObject = YggTorrent.fromList(randomFile)


    @Test
    fun `wrong list`() {
        assertThrows<Exception>("Wrong list !") {
            val randomObject = YggTorrent.fromList(wrongFile)
        }
    }

    @Test
    fun `extract url`() {
        val randomObject = YggTorrent.fromList(randomFile)
        assertThat(randomObject.url).isEqualTo("https://www2.yggtorrent.pe/torrent/filmvideo/animation-serie/515360-the-boondocks-s04-vosta-dvdrip-mpeg2-ac3")
    }

    @Test
    fun `extract name`() {
        assertThat(randomObject.filename).isEqualTo("The.Boondocks.S04.VOSTA.DVDRip.MPEG2.AC3")
    }

    @Test
    fun `extract comments count`() {
        assertThat(randomObject.commentsCount).isEqualTo("1")
    }

    @Test
    fun `extract timestamp`() {
        assertThat(randomObject.elapsedTimestamp).isEqualTo(1571559356)
    }

    @Test
    fun `extract size`() {
        assertThat(randomObject.size).isEqualTo(9272148554)
    }

    @Test
    fun `extract completions`() {
        assertThat(randomObject.completions).isEqualTo("1")
    }

    @Test
    fun `extract seeders`() {
        assertThat(randomObject.seeders).isEqualTo("3")
    }

    @Test
    fun `extract leechers`() {
        assertThat(randomObject.leechers).isEqualTo("5")
    }

}