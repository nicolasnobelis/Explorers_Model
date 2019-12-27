package services

import model.Ship
import model.enums.ShipStatus
import java.util.*

// a complex service to manage ships. It allows CRUD operations but also privileged ones
interface ShipService {
    fun getShip(shipId: UUID)

    fun createShip(ship: Ship) : UUID

    // this is a privileged operation : the owner needs to be enforced
    fun changeShipStatus(shipId: UUID, shipStatus: ShipStatus)

    fun deleteShip(shipId: UUID)

}