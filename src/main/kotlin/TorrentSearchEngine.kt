import javafx.scene.text.FontWeight
import javafx.stage.Stage
import tornadofx.App
import tornadofx.Stylesheet
import tornadofx.launch
import tornadofx.px
import views.search.SearchView


fun main(args: Array<String>) {
    launch<TorrentSearchEngine>(args)
}

class TorrentSearchEngine : App(SearchView::class, Styles::class) {
    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 1080.0
            minHeight = 400.0
            super.start(this)
        }
    }
}

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 12.px
            fontWeight = FontWeight.NORMAL
        }
        tableView {

        }
    }
}

