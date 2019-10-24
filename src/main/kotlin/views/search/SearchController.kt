package views.search

import javafx.beans.property.SimpleStringProperty
import models.Torrent
import models.YggTorrent
import repositories.LeetXRepository
import repositories.YggRepository
import tornadofx.Controller
import tornadofx.asObservable

class SearchController : Controller() {



    private var previousRequest: String = ""
    private lateinit var lastItemChoosed: Torrent

    // Mock
    var results = mutableListOf<Torrent>().asObservable()
        private set

    // Bind values
    var resultsCount: SimpleStringProperty = SimpleStringProperty(results.count().toString())
    var userInput: SimpleStringProperty = SimpleStringProperty()

    fun search() {
        if (this.userInput.value.length >= 3 && this.userInput.value != this.previousRequest) {
            this.previousRequest = this.userInput.value
            this.results.addAll(YggRepository.search(this.userInput.value.replace(" ", "+")))
            this.results.addAll(LeetXRepository.search(this.userInput.value.replace(" ", "+")))
        }
    }

    fun itemDoubleClicked(selectedItem: Torrent?) {
        selectedItem?.let {
            this.lastItemChoosed = selectedItem
        }
    }
}