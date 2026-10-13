package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.model.EmpleadoDto;
import cr.ac.una.unaplanillaws.service.EmpleadoService;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.JwTokenHelper;
import cr.ac.una.unaplanillaws.util.Respuesta;
import cr.ac.una.unaplanillaws.util.Secure;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
@Secure
@Path("Empleados")
@Tag(name = "Empleados", description = "Operaciones sobre empleados")
@SecurityRequirement(name = "jwt-auth")
public class EmpleadoController {
    
    @Context
    SecurityContext SecurityContext;
            
    @EJB
    EmpleadoService empleadoService;
    

    @GET
    @Path("/usuario/{usuario}/{clave}")
    public Response validarUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
            Respuesta respuesta = empleadoService.validarUsuario(usuario, clave);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            EmpleadoDto empleadoDto = (EmpleadoDto) respuesta.getResultado("Empleado");
            empleadoDto.setToken(JwTokenHelper.getInstance().generatePrivateKey(clave));
            return Response.ok(empleadoDto).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error validando el usuario.").build();
        }
    }
    @POST
    @Path("/empleado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarEmpleado(@Valid EmpleadoDto empleadoDto) {
        try {
            Respuesta respuesta = empleadoService.guardarEmpleado(empleadoDto);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("Empleado")).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el empleado.").build();
        }
    }
    
    @DELETE
    @Path("/empleado/{id}")
        public Response eliminarEmpleado(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = empleadoService.eliminarEmpleado(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el empleado.").build();
        }
    }
        
    @GET
    @Path("/empleado/obtener/{id}")
    public Response getEmpleadoId(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = empleadoService.getEmpleado(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(respuesta.getResultado("Empleado")).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando el empleado.").build();
        }
    }
    
    @GET
    @Path("/empleado/obtener/{cedula}/{nombre}/{pApellido}/{sApellido}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpleadoId(@PathParam("cedula") String cedula,@PathParam("nombre") String nombre, 
            @PathParam("pApellido") String pApellido, @PathParam("sApellido") String sApellido) {
        try {
            Respuesta respuesta = empleadoService.getEmpleados(cedula, nombre, pApellido, sApellido);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok(
                    new GenericEntity<List<EmpleadoDto>>(
                    (List<EmpleadoDto>)respuesta.getResultado("Empleados")
                    ){}
            ).build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error consultando los empleados.").build();
        }
    }
    
    @GET
    @Path("/renovar")
    public Response renovarToken() {
        try {
            String usuario = SecurityContext.getUserPrincipal().getName();
            if(usuario != null && !usuario.isBlank()){
                return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuario)).build(); 
            }
            return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("No se pudo renovar el token.").build();
        } catch (Exception e) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error renovando el token.").build();
        }
    }

}
