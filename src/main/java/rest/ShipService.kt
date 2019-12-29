package rest

import model.Ship
import model.enums.ShipStatus
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

// a REST service to manage ships. It allows CRUD operations but also privileged ones
interface ShipService {
    // this is a privileged operation : the owner needs to be enforced
    fun changeShipStatus(shipId: UUID, shipStatus: ShipStatus)

    // if the entity has no id it will be created
    @POST
    @Path("/ship/createOrUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createOrUpdateShip(ship: Ship) : UUID

    @GET
    @Path("/ship/{shipId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getShip(@PathParam("shipId") shipId: UUID)

    @GET
    @Path("/ship/{shipId}/delete")
    fun deleteShip(@PathParam("shipId") shipId: UUID)

    @GET
    @Path("/ship/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun listShips(): List<Ship>
}