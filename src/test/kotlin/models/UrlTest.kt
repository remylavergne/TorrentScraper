package models

import org.junit.jupiter.api.Test
import java.text.Normalizer

class UrlTest {

    val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

    @Test
    fun `remove accent from url`() {


        fun CharSequence.unaccent(): String {
            val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
            return REGEX_UNACCENT.replace(temp, "")
        }

        assert("áéíóů".unaccent() == "aeiou")

    }

}