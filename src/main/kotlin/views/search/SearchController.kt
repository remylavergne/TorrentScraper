package views.search

import models.YggTorrent
import tornadofx.Controller
import tornadofx.observable

class SearchController : Controller() {

    // Mock
    var results = listOf(
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46")
    ).observable()
        private set

    fun dailyFiles() {

    }


}