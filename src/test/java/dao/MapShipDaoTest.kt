package dao

import dao.impl.MapShipDao
import model.Ship
import model.enums.ShipStatus
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import java.util.UUID

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

    @Test
    fun testGetShip() {
        val dao = MapShipDao()

        //create
        val ship = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val id = dao.createOrUpdateShip(ship)

        id shouldNotEqual null

        //get
        val retrievedShip = dao.getShip(id)

        retrievedShip!!.apply {
            this.id shouldEqual id
            name shouldEqual "Santa María"
        }

        // test get no entry
        val noShip = dao.getShip(UUID.randomUUID())
        noShip shouldEqual null
    }

    @Test
    fun testDelete() {
        val dao = MapShipDao()

        //create
        val ship = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val id = dao.createOrUpdateShip(ship)

        id shouldNotEqual null

        dao.ships[id]!!.apply {
            this.id shouldEqual id
            name shouldEqual "Santa María"
        }

        //delete
        val result = dao.deleteShip(id)

        result shouldEqual true

        dao.ships[id] shouldEqual null
    }

    @Test
    fun tesListShips() {
        val dao = MapShipDao()

        //create
        val ship1 = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val id1 = dao.createOrUpdateShip(ship1)
        val ship2 = Ship(name = "HMS Victory", shipStatus = ShipStatus.RETIRED, capacityInTons = 2142, canons = 104)
        val id2 = dao.createOrUpdateShip(ship2)

        id1 shouldNotEqual null
        id2 shouldNotEqual null

        //list
        val ships = dao.listShips()

        ships.size shouldEqual 2
        // the original objects don't have the ids so we put them in
        ships.shouldContain(ship1.copy(id = id1))
        ships.shouldContain(ship2.copy(id = id2))
    }
}