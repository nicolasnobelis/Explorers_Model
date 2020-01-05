package dao

import dao.impl.MapShipDao
import model.Ship
import model.enums.ShipStatus
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test

class MapShipDaoTest {
    @Test
    fun testCreateOrUpdate() {
        val dao = MapShipDao()

        //create
        val ship = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val id = dao.createOrUpdateShip(ship)

        id shouldNotEqual null
        dao.ships[id]!!.apply {
            this.id shouldEqual id
            name shouldEqual "Santa María"
        }

        // update
        val shipChanged = ship.copy(id = id, name = "HMS Victory", shipStatus = ShipStatus.RETIRED,
                capacityInTons = 2142, canons = 104)
        val id2 = dao.createOrUpdateShip(shipChanged)

        id shouldEqual id2
        dao.ships.size shouldEqual 1
        dao.ships[id2]!!.apply {
            this.id shouldEqual id2
            name shouldEqual "HMS Victory"
            capacityInTons shouldEqual 2142
        }
    }
}