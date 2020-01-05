package dao.impl

import dao.ExpeditionDao
import model.Expedition
import model.enums.ExpeditionStatus
import java.util.UUID

/**
 * An simple implementation of the ExpeditionDao that uses an in-memory map
 * @see ExpeditionDao
 */
class MapExpeditionDao : ExpeditionDao {
    internal val expeditions = mutableMapOf<UUID, Expedition>()

    override fun createOrUpdateExpedition(expedition: Expedition): UUID {
        val toStore = expedition.run {
            if (id == null) {
                copy(id = UUID.randomUUID())
            } else {
                copy()
            }
        }
        expeditions[toStore.id!!] = toStore
        return toStore.id
    }

    override fun getExpedition(expeditionId: UUID): Expedition? = expeditions[expeditionId]?.copy()

    override fun deleteExpedition(expeditionId: UUID): Boolean = (expeditions.remove(expeditionId) != null)

    override fun listExpeditions(): List<Expedition> = expeditions.values.map { it.copy() }

    override fun listExpeditions(filter: ExpeditionStatus): List<Expedition> =
            expeditions.values.filter { it.status == filter }.map { it.copy() }
}