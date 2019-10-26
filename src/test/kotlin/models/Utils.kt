package models

import Exts.toDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Utils {

    @Test
    fun `right timestamp to date`() {
        val toDate = 1545155622000.toDate()
        assertThat(toDate).isEqualTo("18/12/2018")
    }

    @Test
    fun `timestamp too short`() {
        val toDate = 15451556220.toDate()
        assertThat(toDate).isEqualTo("18/12/2018")
    }

}