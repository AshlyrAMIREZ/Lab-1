/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.model.TipoPlanillaDto;
import cr.ac.una.unaplanillaws.service.TipoPlanillaService;
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
@Path("Planillas")
public class TipoPlanillaController {

    @EJB
    TipoPlanillaService service;

    @GET
    @Path("/planilla/{id}")
    public Response getPlanilla(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = service.getTipoPlanilla(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TipoPlanilla")).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando la planilla.").build();
        }
    }

    @GET
    @Path("/planillas/{codigo}/{descripcion}/{plaxmes}/{idemp}/{cedula}")
    public Response getPlanillas(@PathParam("codigo") String codigo, @PathParam("descripcion") String descripcion, @PathParam("plaxmes") String plaxmes, @PathParam("idemp") String idemp, @PathParam("cedula") String cedula) {
        try {
            Respuesta respuesta = service.getTiposPlanilla(codigo, descripcion, plaxmes, idemp, cedula);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TiposPlanilla")).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando la planilla.").build();
        }
    }

    @POST
    @Path("/guardar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarTipoPlanilla(@PathParam("tipoPlanilla") TipoPlanillaDto planilla) {
        try {
            Respuesta respuesta = service.guardarTipoPlanilla(planilla);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("TipoPlanilla")).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la planilla.").build();
        }
    }
    @DELETE
    @Path("/planilla/{id}")
        public Response eliminarEmpleado(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = service.eliminarTipoPlanilla(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la planilla.").build();
        }
    }

}
