package rest

import model.Explorer
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

// a REST service to manage explorers. It allows CRUD operations
interface ExplorerService {
    // if the entity has no id it will be created
    @POST
    @Path("/explorer/createOrUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createOrUpdateExplorer(explorer: Explorer): UUID

    @GET
    @Path("/explorer/{explorerId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getExplorer(@PathParam("explorerId") explorerId: UUID): Explorer

    @GET
    @Path("/explorer/{explorerId}/delete")
    fun deleteExplorer(@PathParam("explorerId") explorerId: UUID)

    @GET
    @Path("/explorer/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun listExplorers(): List<Explorer>
}
