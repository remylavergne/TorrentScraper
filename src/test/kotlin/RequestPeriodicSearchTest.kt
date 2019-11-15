import org.junit.jupiter.api.Test
import java.io.File

class RequestPeriodicSearchTest {

    private val requestDir = "requests/"

    @Test
    fun `get all files in save request dir`() {
        File(this.requestDir).walk().forEach { path ->
            println(path.name)
        }
    }
}