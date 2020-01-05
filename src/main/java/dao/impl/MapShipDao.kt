package dao.impl

import dao.ShipDao
import model.Ship
import java.util.UUID

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
        ships[toStore.id!!] = toStore
        return toStore.id
    }

    override fun getShip(shipId: UUID) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteShip(shipId: UUID) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listShips(): List<Ship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}