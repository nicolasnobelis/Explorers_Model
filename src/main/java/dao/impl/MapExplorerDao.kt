package dao.impl

import dao.ExplorerDao
import model.Explorer
import java.util.UUID

class MapExplorerDao : ExplorerDao {
    internal val explorers = mutableMapOf<UUID, Explorer>()

    override fun createOrUpdateExplorer(explorer: Explorer): UUID {
        val toStore = explorer.run {
            if (id == null) {
                copy(id = UUID.randomUUID())
            } else {
                copy()
            }
        }
        explorers[toStore.id!!] = toStore
        return toStore.id
    }

    override fun getExplorer(explorerId: UUID): Explorer? = explorers[explorerId]?.copy()

    override fun deleteExplorer(explorerId: UUID): Boolean = (explorers.remove(explorerId) != null)

    override fun listExplorers(): List<Explorer> = explorers.values.map { it.copy() }
}