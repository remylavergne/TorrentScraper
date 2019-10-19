package views.search

import tornadofx.View
import tornadofx.button
import tornadofx.hbox
import tornadofx.textfield

class SearchView : View() {

    private val controller: SearchController by inject()

    override val root = hbox {
        textfield("Value")
        button("Search")
    }

}