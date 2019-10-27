package views.servers

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import models.ERROR_IMAGE_URL
import models.SUCCESS_IMAGE_URL
import repositories.LeetXRepository
import repositories.ThePirateBayRepository
import repositories.YggRepository
import tornadofx.Controller
import tornadofx.Field
import tornadofx.hide

class ServerStatusController : Controller() {

    // ImageViews
    lateinit var yggStatus: Field
    lateinit var leetXStatus: Field
    lateinit var thePirateBayStatus: Field

    init {
        GlobalScope.launch {
            // YGG
            val yggStatusResponse = YggRepository.checkServerStatus()
            yggStatus.inputs[0].hide()
            (yggStatus.inputs[1] as ImageView).image = if (yggStatusResponse) {
                Image(SUCCESS_IMAGE_URL)
            } else {
                Image(ERROR_IMAGE_URL)
            }
            // 1337x
            val leetxStatusResponse = LeetXRepository.checkServerStatus()
            leetXStatus.inputs[0].hide()
            (leetXStatus.inputs[1] as ImageView).image = if (leetxStatusResponse) {
                Image(SUCCESS_IMAGE_URL)
            } else {
                Image(ERROR_IMAGE_URL)
            }
            // ThePirateBay
            val tpbStatusResponse = ThePirateBayRepository.checkServerStatus()
            thePirateBayStatus.inputs[0].hide()
            (thePirateBayStatus.inputs[1] as ImageView).image = if (tpbStatusResponse) {
                Image(SUCCESS_IMAGE_URL)
            } else {
                Image(ERROR_IMAGE_URL)
            }
        }
    }
}