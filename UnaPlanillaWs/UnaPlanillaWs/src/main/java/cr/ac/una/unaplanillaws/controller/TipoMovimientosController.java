/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.model.TipoMovimientosDto;
import cr.ac.una.unaplanillaws.service.TipoMovimientosService;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.Respuesta;
import cr.ac.una.unaplanillaws.util.Secure;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
@Secure
@Path("TipoMovimientos")
@Tag(name = "TipoMovimientos", description = "Operaciones sobre tipos de movimientos")
@SecurityRequirement(name = "jwt-auth")
public class TipoMovimientosController {

    @EJB
    TipoMovimientosService tipoMovimientosService;

    @GET
    @Path("/movimiento/{id}")
    public Response getTipoMovimiento(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = tipoMovimientosService.getTipoMovimiento(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TipoMovimiento")).build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientosController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando el tipo de movimiento.").build();
        }
    }

    @GET
    @Path("/movimientos")
    public Response getTiposMovimientos() {
        try {
            Respuesta respuesta = tipoMovimientosService.getTiposMovimientos();
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TiposMovimientos")).build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientosController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando los tipos de movimientos.").build();
        }
    }

    @POST
    @Path("/movimiento")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarTipoMovimiento(TipoMovimientosDto tipoMovimientosDto) {
        try {
            Respuesta respuesta = tipoMovimientosService.guardarTipoMovimiento(tipoMovimientosDto);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TipoMovimiento")).build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientosController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el tipo de movimiento.").build();
        }
    }

    @DELETE
    @Path("/movimiento/{id}")
    public Response eliminarTipoMovimiento(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = tipoMovimientosService.eliminarTipoMovimiento(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientosController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el tipo de movimiento.").build();
        }
    }
}
