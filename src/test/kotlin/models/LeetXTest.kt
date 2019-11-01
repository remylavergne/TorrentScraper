package models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LeetXTest {

    private val html = listOf(
        "<td class=\"coll-1 name\"><a href=\"/sub/42/0/\" class=\"icon\"><i class=\"flaticon-hd\"></i></a><a href=\"/torrent/4050035/The-Lion-King-2019-BluRay-1080p-YTS-YIFY/\">The Lion King (2019) [BluRay] [1080p] [YTS] [YIFY]</a><span class=\"comments\"><i class=\"flaticon-message\"></i>9</span></td>",
        "<td class=\"coll-2 seeds\">15105</td>",
        "<td class=\"coll-3 leeches\">7376</td>",
        "<td class=\"coll-date\">6pm Oct. 11th</td>",
        "<td class=\"coll-4 size mob-uploader\">1.9 GB<span class=\"seeds\">15105</span></td>",
        "<td class=\"coll-5 uploader\"><a href=\"/user/YTSAGx/\">YTSAGx</a></td>",
        "<a href=\"/search/lion+king/13/\">Last</a>"
    )

    private val leetX = LeetX.fromHtml(html)

    @Test
    fun `parse url`() {
        assertThat(leetX.url).isEqualTo("https://1337x.to/torrent/4050035/The-Lion-King-2019-BluRay-1080p-YTS-YIFY/")
    }

    @Test
    fun `parse filename`() {
        assertThat(leetX.filename).isEqualTo("The Lion King (2019) [BluRay] [1080p] [YTS] [YIFY]")
    }

    @Test
    fun `parse seeders`() {
        assertThat(leetX.seeders).isEqualTo("15105")
    }

    @Test
    fun `parse leechers`() {
        assertThat(leetX.leechers).isEqualTo("7376")
    }

    @Test
    fun `parse size`() {
        assertThat(leetX.size).isEqualTo("1.9 GB")
    }

    @Test
    fun `parse comments`() {
        assertThat(leetX.commentsCount).isEqualTo("9")
    }

    @Test
    fun `parse pagination`() {
        assertThat(leetX.lastPage).isEqualTo("13")
    }


}