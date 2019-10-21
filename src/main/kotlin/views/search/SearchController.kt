package views.search

import javafx.beans.property.SimpleStringProperty
import models.Torrent
import models.YggTorrent
import repositories.YggRepository
import tornadofx.Controller
import tornadofx.observable

class SearchController : Controller() {

    var userInput: SimpleStringProperty = SimpleStringProperty()
    private var previousRequest: String = ""

    // Mock
    var results = mutableListOf(
        YggTorrent(
            filename = "Le Seigneur des Anneaux",
            commentsCount = "12",
            seeders = "34",
            leechers = "46"
        ) as Torrent,
        YggTorrent(
            filename = "Le Seigneur des Anneaux",
            commentsCount = "12",
            seeders = "34",
            leechers = "46"
        ) as Torrent,
        YggTorrent(
            filename = "Le Seigneur des Anneaux",
            commentsCount = "12",
            seeders = "34",
            leechers = "46"
        ) as Torrent,
        YggTorrent(
            filename = "Le Seigneur des Anneaux",
            commentsCount = "12",
            seeders = "34",
            leechers = "46"
        ) as Torrent,
        YggTorrent(
            filename = "Le Seigneur des Anneaux",
            commentsCount = "12",
            seeders = "34",
            leechers = "46"
        ) as Torrent
    ).observable()
        private set

    fun search() {
        if (this.userInput.value.length >= 3 && this.userInput.value != this.previousRequest) {
            this.previousRequest = this.userInput.value
            this.results.setAll(YggRepository.search(this.userInput.value.replace(" ", "+")))
        }
    }


}