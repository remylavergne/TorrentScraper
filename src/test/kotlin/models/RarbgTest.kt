package models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RarbgTest {

    private val html = "<tr class=\"lista2\">\n" +
            " <td align=\"left\" class=\"lista\" width=\"48\" style=\"width:48px;\"><a href=\"/torrents.php?category=44\"><img src=\"https://dyncdn.me/static/20/images/categories/cat_new44.gif\" border=\"0\" alt=\"\"></a></td>\n" +
            " <td align=\"left\" class=\"lista\"><a onmouseover=\"return overlib('<img src=\\'https://dyncdn.me/mimages/333867/over_opt.jpg\\' border=0>')\" onmouseout=\"return nd();\" href=\"/torrent/cgzlp46\" title=\"The.Lion.King.2019.RERiP.1080p.BluRay.x264-SPARKS\">The.Lion.King.2019.RERiP.1080p.BluRay.x264-SPARKS</a> <a href=\"/torrents.php?imdb=tt6105098\"><img src=\"https://dyncdn.me/static/20/images/imdb_thumb.gif\" border=\"0\" alt=\"\"></a> <br><span style=\"color:DarkSlateGray\">Animation, Adventure, Drama, Family, Musical IMDB: 7.0/10</span> </td>\n" +
            " <td align=\"center\" width=\"150px\" class=\"lista\">2019-10-11 10:17:55</td>\n" +
            " <td align=\"center\" width=\"100px\" class=\"lista\">8.76 GB</td>\n" +
            " <td align=\"center\" width=\"50px\" class=\"lista\"><font color=\"#008000\">1525</font></td>\n" +
            " <td align=\"center\" width=\"50px\" class=\"lista\">687</td>\n" +
            " <td align=\"center\" width=\"50px\" class=\"lista\"><a href=\"/torrent/cgzlp46#comments\">55</a></td>\n" +
            " <td align=\"center\" class=\"lista\">p33Rn3t</td>\n" +
            "</tr>"

    private val rarbg = Rarbg.fromHtml(html)


    @Test
    fun `parse url`() {
        assertThat(rarbg.url).isEqualTo(rarbg.domain + "/torrent/cgzlp46")
    }

    @Test
    fun `parse filename`() {
        assertThat(rarbg.filename).isEqualTo("The.Lion.King.2019.RERiP.1080p.BluRay.x264-SPARKS")
    }

    @Test
    fun `parse comments`() {
        assertThat(rarbg.commentsCount).isEqualTo(55)
    }

    @Test
    fun `parse and convert elapsed time`() {
        assertThat(rarbg.elapsedTimestamp).isEqualTo(1570781875000)
    }

    @Test
    fun `parse seeders`() {
        assertThat(rarbg.seeders).isEqualTo(1525)
    }

    @Test
    fun `parse leechers`() {
        assertThat(rarbg.leechers).isEqualTo(687)
    }
}