package views.search

import enums.AllRepositories
import javafx.beans.property.SimpleStringProperty
import kotlinx.coroutines.*
import models.Torrent
import services.RequestPeriodicSearch
import tornadofx.Controller
import tornadofx.observable

class SearchController : Controller() {

    private var previousRequest: String = ""
    private lateinit var lastItemChoosed: Torrent
    private val temporaryData = mutableListOf<Torrent>()

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

            this.temporaryData.clear()
            makeAllRequests().join()

            results.addAll(temporaryData.sortedByDescending { it.seeders })
            resultsCount.set(results.count().toString())
        }
    }

    private fun makeAllRequests(): Job {

        return GlobalScope.launch {
            AllRepositories.values().forEach { repository ->
                async {
                    val response = repository.server.search(userInput.value.trim())
                    temporaryData.addAll(response)
                }
            }
        }
    }

    fun itemDoubleClicked(selectedItem: Torrent?) {
        selectedItem?.let {
            this.lastItemChoosed = selectedItem
        }
    }

    fun saveRequest() {
        RequestPeriodicSearch.saveARequest(this.userInput.value.trim(), results.toList())
    }

    fun checkSavedRequests() {
        CoroutineScope(Dispatchers.IO).launch {
            RequestPeriodicSearch.checkSavedRequests()
        }
    }
}