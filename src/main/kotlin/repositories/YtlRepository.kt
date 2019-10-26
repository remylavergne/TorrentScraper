package repositories

import models.Torrent

object YtlRepository : BaseRepository() {

    override fun search(request: String): List<Torrent> {


        return emptyList()
    }
}