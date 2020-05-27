package dao.impl

import dao.ShipDao
import model.Ship
import java.util.*

class MapShipDao : ShipDao {
    internal val ships = mutableMapOf<UUID, Ship>()

    override fun createOrUpdateShip(ship: Ship): UUID {
        val toStore = ship.run {
            if (id == null) {
                copy(id = UUID.randomUUID())
            } else {
                copy()
            }
        }
        toStore.apply {
            ships[id!!] = this
            return id!!
        }
    }

    override fun getShip(shipId: UUID): Ship? = ships[shipId]?.copy()

    override fun deleteShip(shipId: UUID): Boolean = (ships.remove(shipId) != null)

    override fun listShips(): List<Ship> = ships.values.map { it.copy() }
}