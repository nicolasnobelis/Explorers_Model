package model

import model.enums.Country
import model.enums.ExpeditionStatus
import java.time.LocalDate
import java.util.UUID

// an expedition : object which encompass other sub objects
// TODO add JAXB annotations to be able to serialize it as JSON
data class Expedition(
        val id: UUID? = null,
        val name: String,
        val armada: List<Ship> = mutableListOf(),
        val explorers: List<Explorer> = mutableListOf(),
        val country: Country,
        val budget: Long = 0,
        val startDate: LocalDate,
        val status: ExpeditionStatus = ExpeditionStatus.CREATED
)