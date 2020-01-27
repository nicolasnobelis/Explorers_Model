package utils

import dao.ExpeditionDao
import dao.ExplorerDao
import dao.ShipDao
import model.Expedition
import model.Explorer
import model.Ship
import model.enums.Country
import model.enums.Gender
import model.enums.ShipStatus
import java.time.LocalDate

object SampleDataGenerator {
    fun generateData(shipDao: ShipDao, explorerDao: ExplorerDao, expeditionDao: ExpeditionDao) {
        val singleShip = Ship(name = "HMS Victory", shipStatus = ShipStatus.RETIRED, capacityInTons = 2142, canons = 104)
        shipDao.createOrUpdateShip(singleShip)
        val singleExplorer = Explorer(name = "Jacques Cartier", country = Country.FRANCE,
                birthDate = LocalDate.of(1491, 12, 31), gender = Gender.MALE)
        explorerDao.createOrUpdateExplorer(singleExplorer)

        val explorerColomb = Explorer(name = "Christopher Columbus", country = Country.ITALIA,
                birthDate = LocalDate.of(1451, 10, 31), gender = Gender.MALE)
        val idExplorerColomb = explorerDao.createOrUpdateExplorer(explorerColomb)
        val shipColomb1 = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val idShipColomb1 = shipDao.createOrUpdateShip(shipColomb1)
        val shipColomb2 = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val idShipColomb2= shipDao.createOrUpdateShip(shipColomb2)
        val shipColomb3 = Ship(name = "Santa María", shipStatus = ShipStatus.ACTIVE, capacityInTons = 108, canons = 4)
        val idShipColomb3 = shipDao.createOrUpdateShip(shipColomb3)
        val expeditionColomb = Expedition(name ="Discovery of America", country= Country.SPAIN, startDate = LocalDate.of(1492, 8, 3))
        expeditionColomb.apply {
            explorers.add(explorerColomb.copy(idExplorerColomb))
            ships.add(shipColomb1.copy(idShipColomb1))
            ships.add(shipColomb2.copy(idShipColomb2))
            ships.add(shipColomb3.copy(idShipColomb3))
            expeditionDao.createOrUpdateExpedition(this)
        }

        val explorerGama = Explorer(name = "Vasco de Gama", country = Country.PORTUGAL,
                birthDate = LocalDate.ofYearDay(1460, 1), gender = Gender.MALE)
        val idExplorerGama = explorerDao.createOrUpdateExplorer(explorerGama)
        val shipGama1 = Ship(name = "São Gabriel", shipStatus = ShipStatus.ACTIVE)
        val idShipGama1 = shipDao.createOrUpdateShip(shipGama1)
        val shipGama2 = Ship(name = "São Rafael", shipStatus = ShipStatus.ACTIVE)
        val idShipGama2 = shipDao.createOrUpdateShip(shipGama2)
        val shipGama3 = Ship(name = "Berrio", shipStatus = ShipStatus.ACTIVE)
        val idShipGama3 = shipDao.createOrUpdateShip(shipGama3)
        val expeditionGama = Expedition(name ="Expedition to India", country= Country.PORTUGAL, startDate = LocalDate.ofYearDay(1497, 1))
        expeditionGama.apply {
            explorers.add(explorerGama.copy(idExplorerGama))
            ships.add(shipGama1.copy(idShipGama1))
            ships.add(shipGama2.copy(idShipGama2))
            ships.add(shipGama3.copy(idShipGama3))
            expeditionDao.createOrUpdateExpedition(this)
        }
    }
}