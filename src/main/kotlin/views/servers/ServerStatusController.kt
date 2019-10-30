package views.servers

import enums.AllRepositories
import javafx.scene.control.ProgressBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import models.ERROR_IMAGE_URL
import models.SUCCESS_IMAGE_URL
import repositories.BaseRepository
import tornadofx.Controller
import tornadofx.Field
import tornadofx.Fieldset
import tornadofx.hide

class ServerStatusController : Controller() {

    lateinit var fieldset: Fieldset

    private var repoViews: MutableMap<Field, BaseRepository> = mutableMapOf()

    fun generateServersViews() {
        AllRepositories.values().forEach {
            val field = Field(it.server.name)
            field.inputs.add(ProgressBar())
            val imageview = ImageView()
            imageview.fitHeight = 15.0
            imageview.fitWidth = 15.0
            field.inputs.add(imageview)
            this.fieldset.children.add(field)
            // Save
            repoViews[field] = it.server
        }
    }

    suspend fun checkServersStatus() {
        withContext(Dispatchers.IO) {
            repoViews.forEach { (field, repository) ->
                async(Dispatchers.IO) {
                    // Get current field by its id
                    val available = repository.checkServerStatus()
                    field.inputs[0].hide()
                    (field.inputs[1] as ImageView).image = if (available) {
                        Image(SUCCESS_IMAGE_URL)
                    } else {
                        Image(ERROR_IMAGE_URL)
                    }
                }
            }
        }
    }
}