package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.GameManager;
import edu.upc.eetac.dsa.GameManagerImpl;
import edu.upc.eetac.dsa.classes.PuntoInteres;
import edu.upc.eetac.dsa.classes.PuntoNotFoundException;
import edu.upc.eetac.dsa.classes.User;
import edu.upc.eetac.dsa.classes.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;

@Api(value="/EETACgame", description = "Endpoint to Text Service")
@Path("/game")
public class Service {

    private GameManager gameManager;

    public Service(){this.gameManager = GameManagerImpl.getInstance();}

    @POST
    @ApiOperation(value = "add user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        String idUser = u.getUserId();
        String name = u.getName();
        this.gameManager.addUser(idUser, name);

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add punto interes", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addPuntoInteres")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(PuntoInteres puntoInteres) {
        String idStation = puntoInteres.getPuntoId();
        String type = puntoInteres.getType();
        this.gameManager.addPuntoInteres(idStation, type);

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get users sorted by name", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "List of users sorted by name"),
    })
    @Path("/usersSortedByName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsersSortedByName() {
        LinkedList<User> usersSortedByName = (LinkedList<User>) this.gameManager.getUsersSortedByName();
        GenericEntity<LinkedList<User>> entity = new GenericEntity<LinkedList<User>>(usersSortedByName){};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "User"),
            @ApiResponse(code = 404, message = "UserNotFoundException"),
    })
    @Path("/getUser/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBike(@PathParam("idUser") String idUser) {
        try {
            User user = this.gameManager.getUserInfo(idUser);
            return Response.status(201).entity(user).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get puntos interes usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class, responseContainer = "Punto interes"),
            @ApiResponse(code = 404, message = "UserNotFoundException"),
    })
    @Path("/getPuntosUser/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPuntosInteresUser(@PathParam("idUser") String idUser) {
        try {
            User user = (User) this.gameManager.getPuntosInteresUser(idUser);
            return Response.status(201).entity(user).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get usuarios punto interes", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "Usuarios"),
            @ApiResponse(code = 404, message = "PuntoNotFoundException"),
    })
    @Path("/getUsersPutno/{idPuntoInteres}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsuariosPuntoInteres(@PathParam("idPuntoInteres") String idPunto) {
        try {
            PuntoInteres puntoInteres = (PuntoInteres) this.gameManager.getUsersPuntoInteres(idPunto);
            return Response.status(201).entity(puntoInteres).build();
        } catch (PuntoNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get usuraios ordenados por puntos pasados", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "Usuarios"),
    })
    @Path("/getUsersSortedByPuntos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsersSortedByPuntos(){
            LinkedList<User> users = (LinkedList<User>) this.gameManager.getUsersSortedByName();
            GenericEntity<LinkedList<User>> entity = new GenericEntity<LinkedList<User>>(users){};
            return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "pasar un punto de interes", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "PuntoNotFoundException"),
            @ApiResponse(code = 402, message = "UserNotFoundException"),
    })
    @Path("/pasarPunto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pasarPunto(@PathParam("idUser") String userId, @PathParam("idPuntoInteres") String puntoId) throws UserNotFoundException, PuntoNotFoundException {
        try {
            this.gameManager.pasarPunto(userId, puntoId);
            return Response.status(201).build();
        }
        catch (PuntoNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        }
        catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(402).build();
        }
    }
}
