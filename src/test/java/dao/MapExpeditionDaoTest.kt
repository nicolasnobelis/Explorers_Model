package dao

import dao.impl.MapExpeditionDao
import model.Expedition
import model.enums.Country
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import java.time.LocalDate

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
            name shouldEqual  "The great expedition"
        }

        // update
        val expeditionChanged = expedition.copy(id = id, name = "the small expedition")
        val id2 = dao.createOrUpdateExpedition(expeditionChanged)

        id shouldEqual id2
        dao.expeditions.size shouldEqual 1
        dao.expeditions[id2]!!.apply {
            this.id shouldEqual id2
            name shouldEqual  "the small expedition"
        }
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
            name shouldEqual  "The great expedition"
        }

        //delete
        val result = dao.deleteExpedition(id)

        result shouldEqual true

        dao.expeditions[id] shouldEqual null
    }
}