package views.search

import Exts.unaccent
import javafx.event.EventHandler
import javafx.scene.control.ProgressIndicator
import javafx.scene.input.KeyCode
import models.Torrent
import tornadofx.*

class SearchView : View() {

    private val controller: SearchController by inject()
    private lateinit var progressIndicator: ProgressIndicator

    override val root =

        form {
            vbox() {
                text("Search")
                hbox {
                    textfield {
                        onKeyReleased = EventHandler {
                            if (it.code == KeyCode.ENTER) {
                                doSearch()
                            }
                        }
                    }.textProperty().bindBidirectional(controller.userInput)
                    button("Search") {
                        action {
                            doSearch()
                        }
                    }
                    text("Results: ")
                    text("0").textProperty().bind(controller.resultsCount)
                }

                progressIndicator = progressindicator {
                    hide()
                }
            }
            hbox {

            }

            tableview(controller.results) {
                readonlyColumn("Domain", Torrent::domain).remainingWidth().maxWidth(100)
                readonlyColumn("Name", Torrent::filename).remainingWidth().minWidth(300).maxWidth(800)
                readonlyColumn("Added", Torrent::elapsedTimestamp)
                readonlyColumn("Comments", Torrent::commentsCount)
                readonlyColumn("Downloads", Torrent::completions)
                readonlyColumn("Seeders", Torrent::seeders)
                readonlyColumn("Leechers", Torrent::leechers)

                smartResize()

                onDoubleClick {
                    openUrl(selectedItem)
                }
            }
        }

    private fun doSearch() {
        runAsync {
            progressIndicator.show()
            controller.search()
        } success {
            progressIndicator.hide()
        }
    }

    private fun openUrl(item: Torrent?) {
        item?.let {
            hostServices.showDocument(it.url.unaccent())
            controller.itemDoubleClicked(it)
        }
    }

}