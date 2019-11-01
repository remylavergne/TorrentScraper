package models

import Exts.rarbgTimestamp
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

    @Test
    fun `Rarbg string date to Timestamp`() {
        val rarbgTimestamp = "2019-10-11 10:17:55".rarbgTimestamp()
        assertThat(rarbgTimestamp).isEqualTo(1570781875000)
    }

}