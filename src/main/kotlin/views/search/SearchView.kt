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
                    textfield()
                    button("Search")
                }
            }
            hbox {
                button("Today") {
                    action {
                        controller.dailyFiles()
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