package views.search

import Exts.unaccent
import javafx.application.HostServices
import javafx.event.EventHandler
import javafx.scene.control.TableView
import javafx.scene.input.KeyCode
import models.Torrent
import tornadofx.*

class SearchView : View() {

    private val controller: SearchController by inject()
    private lateinit var tablevw: TableView<Torrent>
    private val hostService: HostServices = hostServices

    override val root =

        form {
            vbox() {
                text("Search")
                hbox {
                    textfield {
                        onKeyReleased = EventHandler {
                            if (it.code == KeyCode.ENTER) {
                                controller.search()
                            }
                        }
                    }.textProperty().bindBidirectional(controller.userInput)
                    button("Search") {
                        action {
                            controller.search()
                        }
                    }
                }
            }
            hbox {
                button("Today") {
                    action {

                    }
                }
            }

            tableview(controller.results) {
                readonlyColumn("Domain", Torrent::domain).remainingWidth().maxWidth(100)
                readonlyColumn("Name", Torrent::filename).remainingWidth().minWidth(300).maxWidth(800)
                readonlyColumn("Added", Torrent::elapsedTimestamp)
                readonlyColumn("Comments", Torrent::commentsCount)
                readonlyColumn("Downloads", Torrent::completions)
                readonlyColumn("Seeders", Torrent::seeders)
                readonlyColumn("Leechers", Torrent::leechers)

                onDoubleClick {
                    openUrl(selectedItem)
                }
            }

        }

    private fun openUrl(item: Torrent?) {
        item?.let {
            hostService.showDocument(it.url.unaccent())
            controller.itemDoubleClicked(it)
        }
    }

}