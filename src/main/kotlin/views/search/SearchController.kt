package views.search

import enums.AllRepositories
import javafx.beans.property.SimpleStringProperty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import models.Torrent
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
            this.previousRequest = this.userInput.value.trim()
            results.clear()

            GlobalScope.launch {
                AllRepositories.values().forEach { repository ->
                    async {
                        val response = repository.server.search(userInput.value)
                        results.addAll(response)
                        resultsCount.set(results.count().toString())
                    }
                }
            }
        }
    }

    fun itemDoubleClicked(selectedItem: Torrent?) {
        selectedItem?.let {
            this.lastItemChoosed = selectedItem
        }
    }
}