package views.search

import models.Torrent
import tornadofx.*

class SearchView : View() {

    private val controller: SearchController by inject()

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
                readonlyColumn("Domain", Torrent::domain)
                readonlyColumn("Name", Torrent::filename)
                readonlyColumn("Added", Torrent::elapsedTimestamp)
                readonlyColumn("Comments", Torrent::commentsCount)
                readonlyColumn("Downloads", Torrent::completions)
                readonlyColumn("Seeders", Torrent::seeders)
                readonlyColumn("Leechers", Torrent::leechers)
            }

        }

}