package model

import model.enums.ShipStatus
import java.util.*

// a ship : object which is immutable after construction
data class Ship(
        var id: UUID? = null,
        var name: String? = null,
        var shipStatus: ShipStatus = ShipStatus.IN_CONSTRUCTION,
        var canons: Int = 0,
        var capacityInTons: Int = 0
)