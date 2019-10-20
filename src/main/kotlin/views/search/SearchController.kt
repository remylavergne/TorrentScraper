package views.search

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import models.YggTorrent
import repositories.YggRepository
import tornadofx.Controller
import tornadofx.observable

class SearchController : Controller() {

    var userInput: SimpleStringProperty = SimpleStringProperty()

    // Mock
    var results = mutableListOf(
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46"),
        YggTorrent(filename = "Le Seigneur des Anneaux", commentsCount = "12", seeders = "34", leechers = "46")
    ).observable()
        private set

    fun search() {
        this.results.setAll(YggRepository.search(this.userInput?.value.replace(" ", "+")))
    }


}