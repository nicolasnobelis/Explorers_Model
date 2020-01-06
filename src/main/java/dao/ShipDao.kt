package dao

import model.Ship
import java.util.UUID

// a simple CRUD DAO to manage ships
interface ShipDao {
    // if the entity has no id it will be created
    fun createOrUpdateShip(ship: Ship) : UUID

    fun getShip(shipId: UUID): Ship?

    fun deleteShip(shipId: UUID): Boolean

    fun listShips(): List<Ship>
}