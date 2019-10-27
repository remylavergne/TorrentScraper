package views.servers

import javafx.scene.Parent
import javafx.scene.image.Image
import javafx.scene.text.FontWeight
import models.ERROR_IMAGE_URL
import models.SUCCESS_IMAGE_URL
import tornadofx.*

class ServersStatusView : Fragment("Servers status") {

    private val controller: ServerStatusController by inject()

    private val successImage = Image(SUCCESS_IMAGE_URL)
    private val errorImage = Image(ERROR_IMAGE_URL)

    override val root: Parent = vbox {

        form {
            fieldset {
                label("Check servers status") {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 16.px
                    }
                    spacing = 10.0
                }

                controller.yggStatus = field("YggTorrent") {
                    progressbar()
                    imageview {
                        fitHeight = 15.0
                        fitWidth = 15.0
                    }
                }
                controller.leetXStatus = field("1337x") {
                    progressbar()
                    imageview {
                        fitHeight = 15.0
                        fitWidth = 15.0
                    }
                }
                controller.thePirateBayStatus = field("ThePirateBay") {
                    progressbar()
                    imageview {
                        fitHeight = 15.0
                        fitWidth = 15.0
                    }
                }
            }
        }
    }
}