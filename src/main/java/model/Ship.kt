package model

import model.enums.ShipStatus
import java.util.UUID

// a ship : object which is immutable after construction
data class Ship(
        val id: UUID? = null,
        val name: String,
        val shipStatus: ShipStatus = ShipStatus.IN_CONSTRUCTION,
        val canons: Int = 0,
        val capacityInTons: Int = 0
)