package views.search

import models.YggTorrent
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
                readonlyColumn("ID", YggTorrent::id)
                readonlyColumn("Name", YggTorrent::filename)
                readonlyColumn("Added", YggTorrent::elapsedTimestamp)
                readonlyColumn("Comments", YggTorrent::commentsCount)
            }

        }

}