package dao

import dao.impl.MapExplorerDao
import model.Explorer
import model.enums.Country
import model.enums.Gender
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import java.time.LocalDate

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
}