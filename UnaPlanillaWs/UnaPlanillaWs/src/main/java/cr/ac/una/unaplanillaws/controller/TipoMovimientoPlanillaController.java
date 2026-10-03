/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.model.TipoMovimientoPlanillaDto;
import cr.ac.una.unaplanillaws.service.TipoMovimientoPlanillaService;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.Respuesta;
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
@Path("TipoMovimientosPlanilla")
public class TipoMovimientoPlanillaController {

    @EJB
    TipoMovimientoPlanillaService tipoMovimientoPlanillaService;

    @GET
    @Path("/movimientoPlanilla/{id}")
    public Response getTipoMovimientoPlanilla(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = tipoMovimientoPlanillaService.getTipoMovimientoPlanilla(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TipoMovimientoPlanilla")).build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientoPlanillaController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando el movimiento de planilla.").build();
        }
    }

    @GET
    @Path("/movimientosPlanilla")
    public Response getTiposMovimientoPlanilla() {
        try {
            Respuesta respuesta = tipoMovimientoPlanillaService.getTiposMovimientoPlanilla();
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TiposMovimientoPlanilla")).build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientoPlanillaController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando los movimientos de planilla.").build();
        }
    }

    @POST
    @Path("/movimientoPlanilla")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarTipoMovimientoPlanilla(TipoMovimientoPlanillaDto tipoMovimientoPlanillaDto) {
        try {
            Respuesta respuesta = tipoMovimientoPlanillaService.guardarTipoMovimientoPlanilla(tipoMovimientoPlanillaDto);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TipoMovimientoPlanilla")).build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientoPlanillaController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el movimiento de planilla.").build();
        }
    }

    @DELETE
    @Path("/movimientoPlanilla/{id}")
    public Response eliminarTipoMovimientoPlanilla(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = tipoMovimientoPlanillaService.eliminarTipoMovimientoPlanilla(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            Logger.getLogger(TipoMovimientoPlanillaController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el movimiento de planilla.").build();
        }
    }
}
