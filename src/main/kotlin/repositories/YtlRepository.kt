package repositories

import models.Torrent

object YtlRepository : BaseRepository() {

    override suspend fun search(request: String): List<Torrent> {


        return emptyList()
    }
}