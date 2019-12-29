package rest

import model.Expedition
import model.enums.ExpeditionStatus
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

// a REST service to manage expeditions. It allows CRUD operations but also async ones
interface ExpeditionService {
    // if the entity has no id it will be created
    @POST
    @Path("/expedition/createOrUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createOrUpdateExpedition(expedition: Expedition) : UUID

    @GET
    @Path("/expedition/{expeditionId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getExpedition(@PathParam("expeditionId") expeditionId: UUID) : Expedition

    @GET
    @Path("/expedition/{expeditionId}/delete")
    fun deleteExpedition(@PathParam("expeditionId") expeditionId: UUID)

    @GET
    @Path("/expedition/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun listExpeditions() : List<Expedition>

    @GET
    @Path("/expedition/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun listExpeditions(@QueryParam("filter") filter: ExpeditionStatus) : List<Expedition>

    @GET
    @Path("/expedition/{expeditionId}/assign/explorer/{explorerId}")
    fun assignExplorer(@PathParam("expeditionId") expeditionId: UUID, @PathParam("explorerId") explorerId: UUID)

    @GET
    @Path("/expedition/{expeditionId}/assign/ship/{shipId}")
    fun assignShip(@PathParam("expeditionId") expeditionId: UUID, @PathParam("shipId") shipId: UUID)

    // Asynchronously start an expedition
    @GET
    @Path("/expedition/{expeditionId}/start")
    fun startExpedition(@PathParam("expeditionId") expeditionId: UUID)

    // try to abort an expedition
    @GET
    @Path("/expedition/{expeditionId}/abort")
    fun abortExpedition(@PathParam("expeditionId") expeditionId: UUID, timeoutInSeconds: Int)
}