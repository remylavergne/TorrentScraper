package models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class YggTorrentTest {

    private val randomFileFromJson = listOf(
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

    private val randomFileFromHtml = listOf<String>(
        "<td> <div class=\"hidden\">  2184 </div><a href=\"https://www2.yggtorrent.pe/engine/url_builder?type=query&amp;source=sub_category&amp;id=2184\"><span class=\"tag_subcat_2184\"></span></a></td>",
        "<td style=\"text-align: left;\"><a id=\"torrent_name\" href=\"https://www2.yggtorrent.pe/torrent/filmvidéo/série-tv/100541-how+to+get+away+with+murder+s03+vostfr+720p+hdtv+x264-scte\">How.to.Get.Away.with.Murder.S03.VOSTFR.720p.HDTV.X264-SCTE </a></td>",
        "<td><a target=\"100541\" id=\"get_nfo\"><img src=\"https://www2.yggtorrent.pe/static/img/nfo.gif\"></a></td>",
        "<td>6 <span class=\"ico_comment\"></span></td>",
        "<td> <div class=\"hidden\">  1507485883 </div><span class=\"ico_clock-o\"></span> 2 ans </td>",
        "<td>15.38Go</td>",
        "<td>107</td>",
        "<td>18</td>",
        "<td>0</td>"
    )

    private val wrongFile = listOf<String>("Not", "Enough", "Informations")
    private val randomObject = YggTorrent.fromListJson(randomFileFromJson)
    private val randomObjectFromHtml = YggTorrent.fromListHtml(randomFileFromHtml)


    /**
     * Test all data parsed from JSON
     */
    @Test
    fun `json wrong list`() {
        assertThrows<Exception>("Wrong list !") {
            YggTorrent.fromListJson(wrongFile)
        }
    }

    @Test
    fun `json extract url`() {
        assertThat(randomObject.url).isEqualTo("https://www2.yggtorrent.pe/torrent/filmvideo/animation-serie/515360-the-boondocks-s04-vosta-dvdrip-mpeg2-ac3")
    }

    @Test
    fun `json extract name`() {
        assertThat(randomObject.filename).isEqualTo("The.Boondocks.S04.VOSTA.DVDRip.MPEG2.AC3")
    }

    @Test
    fun `json extract comments count`() {
        assertThat(randomObject.commentsCount).isEqualTo("1")
    }

    @Test
    fun `json extract timestamp`() {
        assertThat(randomObject.elapsedTimestamp).isEqualTo(1571559356)
    }

    @Test
    fun `json extract size`() {
        assertThat(randomObject.size).isEqualTo(9272148554)
    }

    @Test
    fun `json extract completions`() {
        assertThat(randomObject.completions).isEqualTo("1")
    }

    @Test
    fun `json extract seeders`() {
        assertThat(randomObject.seeders).isEqualTo("3")
    }

    @Test
    fun `json extract leechers`() {
        assertThat(randomObject.leechers).isEqualTo("5")
    }

    /**
     * Test all data parsed fron HTML
     */
    @Test
    fun `html wrong list`() {
        assertThrows<Exception>("Wrong list !") {
            YggTorrent.fromListHtml(wrongFile)
        }
    }

    @Test
    fun `html extract url`() {
        assertThat(randomObjectFromHtml.url).isEqualTo("https://www2.yggtorrent.pe/torrent/filmvidéo/série-tv/100541-how+to+get+away+with+murder+s03+vostfr+720p+hdtv+x264-scte")
    }

    @Test
    fun `html extract name`() {
        assertThat(randomObjectFromHtml.filename).isEqualTo("How.to.Get.Away.with.Murder.S03.VOSTFR.720p.HDTV.X264-SCTE")
    }

    @Test
    fun `html extract comments count`() {
        assertThat(randomObjectFromHtml.commentsCount).isEqualTo("6")
    }

    @Test
    fun `html extract timestamp`() {
        assertThat(randomObjectFromHtml.elapsedTimestamp).isEqualTo(1507485883)
    }

    @Test
    fun `html extract size`() {
        assertThat(randomObjectFromHtml.size).isEqualTo(9272148554)
    }

    @Test
    fun `html extract completions`() {
        assertThat(randomObjectFromHtml.completions).isEqualTo("1")
    }

    @Test
    fun `html extract seeders`() {
        assertThat(randomObjectFromHtml.seeders).isEqualTo("3")
    }

    @Test
    fun `html extract leechers`() {
        assertThat(randomObjectFromHtml.leechers).isEqualTo("5")
    }


}