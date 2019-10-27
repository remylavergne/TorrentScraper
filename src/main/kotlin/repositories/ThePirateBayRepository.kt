package repositories

import models.Torrent

object ThePirateBayRepository : BaseRepository() {

    override suspend fun search(request: String): List<Torrent> {
        return emptyList()
    }

    override suspend fun checkServerStatus(): Boolean {
        return false
    }
}