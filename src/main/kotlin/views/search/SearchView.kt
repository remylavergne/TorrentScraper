package views.search

import tornadofx.*

class SearchView : View() {

    private val controller: SearchController by inject()

    override val root = hbox {
        textfield("Value")
        button("Search")
        button("Today") {
            action {
                controller.dailyFiles()
            }
        }

    }

}