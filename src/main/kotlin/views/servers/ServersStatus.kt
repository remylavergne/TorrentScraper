package views.servers

import javafx.scene.Parent
import tornadofx.*

class ServersStatus : Fragment("Servers status") {

    override val root: Parent = vbox {

        form {
            fieldset("Check servers status") {
                field("YggTorrent") {
                    progressbar()
                }
                field("1337x") {
                    progressbar()
                }
                field("ThePirateBay") {
                    progressbar()
                }
            }
        }
    }
}