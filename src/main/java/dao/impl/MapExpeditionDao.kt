package dao.impl

import dao.ExpeditionDao
import model.Expedition
import model.enums.ExpeditionStatus
import java.util.*

/**
 * An simple implementation of the ExpeditionDao that uses an in-memory map
 * @see ExpeditionDao
 */
class MapExpeditionDao : ExpeditionDao {
    internal val expeditions = mutableMapOf<UUID, Expedition>()

    override fun createOrUpdateExpedition(expedition: Expedition): UUID {
        val toStore = expedition.run {
            if (id == null) {
                copy(UUID.randomUUID())
            } else {
                copy(expedition.id)
            }
        }
        expeditions[toStore.id!!] = toStore
        return toStore.id
    }

    override fun getExpedition(expeditionId: UUID): Expedition {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteExpedition(expeditionId: UUID) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listExpeditions(): List<Expedition> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listExpeditions(filter: ExpeditionStatus): List<Expedition> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}