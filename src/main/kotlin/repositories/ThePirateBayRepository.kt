package repositories

import models.THEPIRATEBAY_URL
import models.Torrent

object ThePirateBayRepository : BaseRepository() {

    override suspend fun search(request: String): List<Torrent> {
        return emptyList()
    }

    override suspend fun checkServerStatus(): Boolean {
        return makeRequest(THEPIRATEBAY_URL, "").code == 200
    }
}