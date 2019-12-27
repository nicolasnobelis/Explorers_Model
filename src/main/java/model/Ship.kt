package model

import model.enums.ShipStatus
import java.util.*

// a ship : object which is mutable after construction
data class Ship(
        val id: UUID,
        val name: String,
        val shipStatus: ShipStatus = ShipStatus.IN_CONSTRUCTION
) {
    val canons: Int = 0
    val capacityInTons: Int = 0
}