package dao

import dao.impl.MapExplorerDao
import model.Explorer
import model.enums.Country
import model.enums.Gender
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import java.time.LocalDate
import java.util.UUID

class MapExplorerDaoTest {
    @Test
    fun testCreateOrUpdate() {
        val dao = MapExplorerDao()

        //create
        val explorer = Explorer(name = "Christopher Columbus", country = Country.ITALIA,
                birthDate = LocalDate.of(1451, 10, 31), gender = Gender.MALE)
        val id = dao.createOrUpdateExplorer(explorer)

        id shouldNotEqual null
        dao.explorers[id]!!.apply {
            this.id shouldEqual id
            name shouldEqual "Christopher Columbus"
        }

        // update
        val explorerChanged = explorer.copy(id = id, name = "Jacques Cartier", country = Country.FRANCE,
                birthDate = LocalDate.of(1491, 12, 31))
        val id2 = dao.createOrUpdateExplorer(explorerChanged)

        id shouldEqual id2
        dao.explorers.size shouldEqual 1
        dao.explorers[id2]!!.apply {
            this.id shouldEqual id2
            name shouldEqual "Jacques Cartier"
            country shouldEqual Country.FRANCE
        }
    }

    @Test
    fun testGetExplorer() {
        val dao = MapExplorerDao()

        //create
        val explorer = Explorer(name = "Christopher Columbus", country = Country.ITALIA,
                birthDate = LocalDate.of(1451, 10, 31), gender = Gender.MALE)
        val id = dao.createOrUpdateExplorer(explorer)

        id shouldNotEqual null

        //get
        val retrievedExplorer = dao.getExplorer(id)

        retrievedExplorer!!.apply {
            this.id shouldEqual id
            name shouldEqual "Christopher Columbus"
        }

        // test get no entry
        val noExplorer = dao.getExplorer(UUID.randomUUID())
        noExplorer shouldEqual null
    }

    @Test
    fun testDelete() {
        val dao = MapExplorerDao()

        //create
        val explorer = Explorer(name = "Christopher Columbus", country = Country.ITALIA,
                birthDate = LocalDate.of(1451, 10, 31), gender = Gender.MALE)
        val id = dao.createOrUpdateExplorer(explorer)

        id shouldNotEqual null

        dao.explorers[id]!!.apply {
            this.id shouldEqual id
            name shouldEqual "Christopher Columbus"
        }

        //delete
        val result = dao.deleteExplorer(id)

        result shouldEqual true

        dao.explorers[id] shouldEqual null
    }

    @Test
    fun tesListExplorers() {
        val dao = MapExplorerDao()

        //create
        val explorer1 = Explorer(name = "Christopher Columbus", country = Country.ITALIA,
                birthDate = LocalDate.of(1451, 10, 31), gender = Gender.MALE)
        val id1 = dao.createOrUpdateExplorer(explorer1)
        val explorer2 = Explorer(name = "Jacques Cartier", country = Country.FRANCE,
                birthDate = LocalDate.of(1491, 12, 31), gender = Gender.MALE)
        val id2 = dao.createOrUpdateExplorer(explorer2)

        id1 shouldNotEqual null
        id2 shouldNotEqual null

        //list
        val explorers = dao.listExplorers()

        explorers.size shouldEqual 2
        // the original objects don't have the ids so we put them in
        explorers.shouldContain(explorer1.copy(id = id1))
        explorers.shouldContain(explorer2.copy(id = id2))
    }
}