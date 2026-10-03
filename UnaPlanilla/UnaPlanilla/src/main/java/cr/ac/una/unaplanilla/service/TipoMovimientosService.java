package cr.ac.una.unaplanilla.service;

import cr.ac.una.unaplanilla.model.TipoMovimientosDto;
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
public class TipoMovimientosService {

    public Respuesta getTipoMovimiento(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("TipoMovimientos/movimiento", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            TipoMovimientosDto movimientoDto = (TipoMovimientosDto) request.readEntity(TipoMovimientosDto.class);
            return new Respuesta(true, "", "", "TipoMovimiento", movimientoDto);
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientosService.class.getName()).log(Level.SEVERE, "Ocurrió un error al consultar el tipo de movimiento.", ex);
            return new Respuesta(false, "Ocurrió un error al consultar el tipo de movimiento.", "getTipoMovimiento " + ex.getMessage());
        }
    }

    public Respuesta getTiposMovimientos() {
        try {
            Request request = new Request("TipoMovimientos/movimientos");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<TipoMovimientosDto> movimientos = (List<TipoMovimientosDto>) request.readEntity(new GenericType<List<TipoMovimientosDto>>() {});
            return new Respuesta(true, "", "", "TiposMovimientos", movimientos);
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientosService.class.getName()).log(Level.SEVERE, "Ocurrió un error al consultar los tipos de movimientos.", ex);
            return new Respuesta(false, "Ocurrió un error al consultar los tipos de movimientos.", "getTiposMovimientos " + ex.getMessage());
        }
    }

    public Respuesta guardarTipoMovimiento(TipoMovimientosDto tipoMovimiento) {
        try {
            Request request = new Request("TipoMovimientos/movimiento");
            request.post(tipoMovimiento);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            TipoMovimientosDto movimiento = (TipoMovimientosDto) request.readEntity(TipoMovimientosDto.class);
            return new Respuesta(true, "", "", "TipoMovimiento", movimiento);
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientosService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el tipo de movimiento.", ex);
            return new Respuesta(false, "Ocurrió un error al guardar el tipo de movimiento.", "guardarTipoMovimiento " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoMovimiento(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("TipoMovimientos/movimiento", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(TipoMovimientosService.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el tipo de movimiento.", ex);
            return new Respuesta(false, "Ocurrió un error al eliminar el tipo de movimiento.", "eliminarTipoMovimiento " + ex.getMessage());
        }
    }
}
