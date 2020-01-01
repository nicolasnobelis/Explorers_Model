package dao

import model.Expedition
import model.enums.ExpeditionStatus
import java.util.*

// a simple CRUD DAO to manage expeditions.
interface ExpeditionDao {
    // if the entity has no id it will be created
    fun createOrUpdateExpedition(expedition: Expedition) : UUID

    fun getExpedition(expeditionId: UUID) : Expedition

    fun deleteExpedition(expeditionId: UUID) : Boolean

    fun listExpeditions() : List<Expedition>

    fun listExpeditions(filter: ExpeditionStatus) : List<Expedition>
}