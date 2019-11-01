package views.servers

import javafx.event.EventDispatchChain
import javafx.scene.Parent
import javafx.scene.text.FontWeight
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*

class ServersStatusView(var listener: Verification) : View("Servers status") {

    private val controller: ServerStatusController by inject()

    override fun onBeforeShow() {
        controller.generateServersViews()
        GlobalScope.launch {
            controller.checkServersStatus()
            listener.checksDone()
        }
        super.onBeforeShow()
    }

    override val root: Parent = vbox {

        form {
            controller.fieldset = fieldset {
                label("Check servers status") {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 16.px
                    }
                    spacing = 10.0
                }
            }
        }
    }
}


interface Verification {
    fun checksDone()
}