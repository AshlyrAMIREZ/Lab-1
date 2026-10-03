package cr.ac.una.unaplanilla.service;

import cr.ac.una.unaplanilla.model.TipoMovimientoPlanillaDto;
import cr.ac.una.unaplanilla.util.Request;
import cr.ac.una.unaplanilla.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class TipoMovimientoPlanillaService {

    public Respuesta getTipoMovimientoPlanilla(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("TipoMovimientosPlanilla/movimientoPlanilla", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            TipoMovimientoPlanillaDto movimientoPlanillaDto = (TipoMovimientoPlanillaDto) request.readEntity(TipoMovimientoPlanillaDto.class);
            return new Respuesta(true, "", "", "TipoMovimientoPlanilla", movimientoPlanillaDto);
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al consultar el movimiento de planilla.", ex);
            return new Respuesta(false, "Ocurrió un error al consultar el movimiento de planilla.", "getTipoMovimientoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta getTiposMovimientoPlanilla() {
        try {
            Request request = new Request("TipoMovimientosPlanilla/movimientosPlanilla");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<TipoMovimientoPlanillaDto> lista = (List<TipoMovimientoPlanillaDto>) request.readEntity(new GenericType<List<TipoMovimientoPlanillaDto>>() {});
            return new Respuesta(true, "", "", "TiposMovimientoPlanilla", lista);
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al consultar los movimientos de planilla.", ex);
            return new Respuesta(false, "Ocurrió un error al consultar los movimientos de planilla.", "getTiposMovimientoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta guardarTipoMovimientoPlanilla(TipoMovimientoPlanillaDto tipoMovimientoPlanilla) {
        try {
            Request request = new Request("TipoMovimientosPlanilla/movimientoPlanilla");
            request.post(tipoMovimientoPlanilla);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            TipoMovimientoPlanillaDto movimientoPlanilla = (TipoMovimientoPlanillaDto) request.readEntity(TipoMovimientoPlanillaDto.class);
            return new Respuesta(true, "", "", "TipoMovimientoPlanilla", movimientoPlanilla);
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el movimiento de planilla.", ex);
            return new Respuesta(false, "Ocurrió un error al guardar el movimiento de planilla.", "guardarTipoMovimientoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoMovimientoPlanilla(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("TipoMovimientosPlanilla/movimientoPlanilla", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el movimiento de planilla.", ex);
            return new Respuesta(false, "Ocurrió un error al eliminar el movimiento de planilla.", "eliminarTipoMovimientoPlanilla " + ex.getMessage());
        }
    }
}
