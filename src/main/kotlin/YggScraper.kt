import javafx.scene.text.FontWeight
import javafx.stage.Stage
import tornadofx.*
import views.search.SearchView

class YggScraper : App(SearchView::class, Styles::class) {
    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 800.0
            minHeight = 400.0
            super.start(this)
        }
    }
}

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 14.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}

fun main(args: Array<String>) {
    launch<YggScraper>(args)
}