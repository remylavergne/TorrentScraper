import javafx.scene.text.FontWeight
import tornadofx.*
import views.search.SearchView

class YggScraper : App(SearchView::class, Styles::class)

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}

fun main(args: Array<String>) {
    launch<YggScraper>(args)
}