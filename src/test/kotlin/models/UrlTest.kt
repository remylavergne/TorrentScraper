package models

import org.junit.jupiter.api.Test
import java.text.Normalizer

class UrlTest {


    @Test
    fun `remove accent from url`() {

        val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

        fun CharSequence.unaccent(): String {
            val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
            return REGEX_UNACCENT.replace(temp, "")
        }

        assert("áéíóů".unaccent() == "aeiou")

    }

}