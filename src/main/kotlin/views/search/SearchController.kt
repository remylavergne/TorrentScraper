package views.search

import javafx.beans.property.SimpleStringProperty
import models.Torrent
import repositories.LeetXRepository
import repositories.YggRepository
import tornadofx.Controller
import tornadofx.observable

class SearchController : Controller() {

    private var previousRequest: String = ""
    private lateinit var lastItemChoosed: Torrent

    // Mock
    var results = mutableListOf<Torrent>().observable()
        private set

    // Bind values
    var resultsCount: SimpleStringProperty = SimpleStringProperty()
    var userInput: SimpleStringProperty = SimpleStringProperty()

    suspend fun search() {
        if (this.userInput.value.length >= 3 && this.userInput.value != this.previousRequest) {
            this.previousRequest = this.userInput.value

            val response = YggRepository.search(userInput.value.replace(" ", "+"))
            results.addAll(response)
            resultsCount.set(results.count().toString())

            val search = LeetXRepository.search(userInput.value.replace(" ", "+"))
            results.addAll(search)
            resultsCount.set(results.count().toString())
        }
    }

    fun itemDoubleClicked(selectedItem: Torrent?) {
        selectedItem?.let {
            this.lastItemChoosed = selectedItem
        }
    }
}