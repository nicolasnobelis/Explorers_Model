package dao

import dao.impl.MapExpeditionDao
import model.Expedition
import model.enums.Country
import model.enums.ExpeditionStatus
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import java.time.LocalDate
import java.util.UUID

class MapExpeditionDaoTest {
    @Test
    fun testCreateOrUpdate() {
        val dao = MapExpeditionDao()

        //create
        val expedition = Expedition(name= "The great expedition", country = Country.FRANCE, startDate = LocalDate.now() )
        val id = dao.createOrUpdateExpedition(expedition)

        id shouldNotEqual null
        dao.expeditions[id]!!.apply {
            this.id shouldEqual id
            name shouldEqual "The great expedition"
        }

        // update
        val expeditionChanged = expedition.copy(id = id, name = "the small expedition")
        val id2 = dao.createOrUpdateExpedition(expeditionChanged)

        id shouldEqual id2
        dao.expeditions.size shouldEqual 1
        dao.expeditions[id2]!!.apply {
            this.id shouldEqual id2
            name shouldEqual "the small expedition"
        }
    }

    @Test
    fun testGetExpedition() {
        val dao = MapExpeditionDao()

        //create
        val expedition = Expedition(name= "The great expedition", country = Country.FRANCE, startDate = LocalDate.now() )
        val id = dao.createOrUpdateExpedition(expedition)

        id shouldNotEqual null

        //get
        val retrievedExpedition = dao.getExpedition(id)

        retrievedExpedition!!.apply {
            this.id shouldEqual id
            name shouldEqual "The great expedition"
            country shouldEqual Country.FRANCE
        }

        // test get no entry
        val noExpedition = dao.getExpedition(UUID.randomUUID())
        noExpedition shouldEqual null
    }

    @Test
    fun testDelete() {
        val dao = MapExpeditionDao()

        //create
        val expedition = Expedition(name= "The great expedition", country = Country.FRANCE, startDate = LocalDate.now() )
        val id = dao.createOrUpdateExpedition(expedition)

        id shouldNotEqual null

        dao.expeditions[id]!!.apply {
            this.id shouldEqual id
            name shouldEqual "The great expedition"
        }

        //delete
        val result = dao.deleteExpedition(id)

        result shouldEqual true

        dao.expeditions[id] shouldEqual null
    }

    @Test
    fun tesListExpeditions() {
        val dao = MapExpeditionDao()

        //create
        val expedition1 = Expedition(name= "The great expedition", country = Country.FRANCE, startDate = LocalDate.now() )
        val id1 = dao.createOrUpdateExpedition(expedition1)
        val expedition2 = Expedition(name= "The medium expedition", country = Country.GERMANY, startDate = LocalDate.now() )
        val id2 = dao.createOrUpdateExpedition(expedition2)

        id1 shouldNotEqual null
        id2 shouldNotEqual null

        //list
        val expeditions = dao.listExpeditions()

        expeditions.size shouldEqual 2
        // the original objects don't have the ids so we put them in
        expeditions.shouldContain(expedition1.copy(id = id1))
        expeditions.shouldContain(expedition2.copy(id = id2))
    }

    @Test
    fun tesListExpeditionsWithFilter() {
        val dao = MapExpeditionDao()

        //create
        val expedition1 = Expedition(name= "The great expedition", country = Country.FRANCE, startDate = LocalDate.now() )
        val id1 = dao.createOrUpdateExpedition(expedition1)
        val expedition2 = Expedition(name= "The medium expedition", country = Country.GERMANY,
                startDate = LocalDate.now(), status = ExpeditionStatus.PENDING )
        val id2 = dao.createOrUpdateExpedition(expedition2)

        id1 shouldNotEqual null
        id2 shouldNotEqual null

        //list
        val expeditions = dao.listExpeditions(ExpeditionStatus.PENDING)

        // the original objects don't have the ids so we put them in
        expeditions.size shouldEqual 1
        expeditions.shouldContain(expedition2.copy(id = id2))
    }
}