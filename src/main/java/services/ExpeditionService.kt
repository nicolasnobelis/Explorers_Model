package services

import model.Expedition
import model.enums.ExpeditionStatus
import java.util.*

// a complex service to manage expeditions. It allows CRUD operations but also async ones
interface ExpeditionService {


    fun createExpedition(expedition: Expedition) : UUID

    fun getExpedition(expeditionId: UUID) : Expedition

    fun deleteExpedition(expeditionId: UUID)

    // Asynchronously start an expedition
    fun startExpedition(expedition: Expedition)

    // try to abort an expedition
    fun abortExpedition(expeditionId: UUID, timeoutInSeconds: Int)

    fun listExpeditions() : List<Expedition>

    fun listExpeditions(filter: ExpeditionStatus) : List<Expedition>
}