package views.search

import javafx.scene.control.TableView
import models.Torrent
import tornadofx.*

class SearchView : View() {

    private val controller: SearchController by inject()
    private lateinit var tablevw: TableView<Torrent>

    override val root =

        form {
            vbox() {
                text("Search")
                hbox {
                    textfield().textProperty().bindBidirectional(controller.userInput)
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
                    controller.itemDoubleClicked(selectedItem)
                }

                onRightClick {
                    // Open options
                }

            }

        }

}