package models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EztvTest {

    private val html =
        "<td width=\"35\" class=\"forum_thread_post\" align=\"center\"><a href=\"/shows/1112/how-to-get-away-with-murder/\" title=\"How to Get Away with Murder Torrent\"><img src=\"/images/eztv_show_info3.png\" border=\"0\" alt=\"Info\" title=\"How to Get Away with Murder Torrents\"></a></td><td class=\"forum_thread_post\"> <a href=\"/ep/1384641/how-to-get-away-with-murder-s06e06-internal-480p-x264-msd/\" title=\"How to Get Away with Murder S06E06 iNTERNAL 480p x264-mSD [eztv] (165.81 MB)\" alt=\"How to Get Away with Murder S06E06 iNTERNAL 480p x264-mSD [eztv] (165.81 MB)\" class=\"epinfo\">How to Get Away with Murder S06E06 iNTERNAL 480p x264-mSD [eztv]</a> </td><td align=\"center\" class=\"forum_thread_post\"> <a href=\"magnet:?xt=urn:btih:6395d67341c94bee68adc8b8a63d8bd6f1c99a4e&amp;dn=How.to.Get.Away.with.Murder.S06E06.iNTERNAL.480p.x264-mSD%5Beztv%5D.mkv%5Beztv%5D&amp;tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A80&amp;tr=udp%3A%2F%2Fglotorrents.pw%3A6969%2Fannounce&amp;tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337%2Fannounce&amp;tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" class=\"magnet\" title=\"How to Get Away with Murder S06E06 iNTERNAL 480p x264-mSD [eztv] (165.81 MB) Magnet Link\" rel=\"nofollow\"></a> <a href=\"https://zoink.ch/torrent/How.to.Get.Away.with.Murder.S06E06.iNTERNAL.480p.x264-mSD[eztv].mkv.torrent\" rel=\"nofollow\" class=\"download_1\" title=\"How to Get Away with Murder S06E06 iNTERNAL 480p x264-mSD Torrent: Download Mirror #1\"></a> </td><td align=\"center\" class=\"forum_thread_post\">165.81 MB</td><td align=\"center\" class=\"forum_thread_post\">18h 43m</td><td align=\"center\" class=\"forum_thread_post_end\"><font color=\"green\">163</font></td>"

    private val eztv = Eztv.fromHtml(html)

    @Test
    fun `parse url`() {
        assertThat(eztv.url).isEqualTo("https://eztv.io" + "/ep/1384641/how-to-get-away-with-murder-s06e06-internal-480p-x264-msd/")
    }

    @Test
    fun `parse filename`() {
        assertThat(eztv.filename).isEqualTo("How to Get Away with Murder S06E06 iNTERNAL 480p x264-mSD [eztv]")
    }

    @Test
    fun `parse seeders`() {
        assertThat(eztv.seeders).isEqualTo(163)
    }
}