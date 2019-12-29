package dao

import model.Explorer
import java.util.*

// a simple CRUD DAO to manage explorers
interface ExplorerDao {
    // if the entity has no id it will be created
    fun createOrUpdateExplorer(explorer: Explorer): UUID

    fun getExplorer(explorerId: UUID): Explorer

    fun deleteExplorer(explorerId: UUID)

    fun listExplorers(): List<Explorer>
}