package services

import model.Explorer
import java.util.*

// a simple CRUD Service to manage explorers
interface ExplorerService {
    fun getExplorer(explorerId: UUID) : Explorer

    fun createExplorer(explorer: Explorer) : UUID

    fun deleteExplorer(explorerId: UUID)
}