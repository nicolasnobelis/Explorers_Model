package dao

import model.Ship
import java.util.*

// a simple CRUD DAO to manage ships
interface ShipDao {
    // if the entity has no id it will be created
    fun createOrUpdateShip(ship: Ship) : UUID

    fun getShip(shipId: UUID)

    fun deleteShip(shipId: UUID)

    fun listShips(): List<Ship>
}