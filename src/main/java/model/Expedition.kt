package model

import model.enums.Country
import model.enums.ExpeditionStatus
import java.time.LocalDate
import java.util.*

// an expedition : object which encompass other sub objects
data class Expedition(
        var id: UUID? = null,
        var name: String? = null,
        var country: Country? = null,
        var startDate: LocalDate? = null,
        var status: ExpeditionStatus = ExpeditionStatus.CREATED,
        var budget: Long = 0
) {
    var ships = mutableListOf<Ship>()
    var explorers = mutableListOf<Explorer>()
}