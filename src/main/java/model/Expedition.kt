package model

import model.enums.Country
import model.enums.ExpeditionStatus
import java.time.LocalDate
import java.util.*

// an expedition : object which encompass other sub object
data class Expedition(
        val id: UUID,
        val armada: List<Ship> = mutableListOf(),
        val explorers: List<Explorer> = mutableListOf(),
        val country: Country,
        val budget: Long,
        val startDate: LocalDate
) {
    val status: ExpeditionStatus = ExpeditionStatus.CREATED
}