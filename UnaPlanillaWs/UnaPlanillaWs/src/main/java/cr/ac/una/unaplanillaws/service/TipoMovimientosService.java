/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.service;

import cr.ac.una.unaplanillaws.model.TipoMovimientos;
import cr.ac.una.unaplanillaws.model.TipoMovimientosDto;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.unaplanillaws.util.Respuesta;

/**
 *
 * @author Oscar
 */
@Stateless
@LocalBean
public class TipoMovimientosService {
     private static final Logger LOG = Logger.getLogger(TipoMovimientosService.class.getName());
     
     @PersistenceContext(unitName = "UnaPlanillaWsPU")
     private EntityManager em;
     
     
     public Respuesta getTipoMovimiento(Long id){
      try {
            Query qryMovimiento = em.createNamedQuery("TipoMovimientos.findById", TipoMovimientos.class);
            qryMovimiento.setParameter("id", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoMovimiento", new TipoMovimientosDto((TipoMovimientos) qryMovimiento.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de movimiento con el código ingresado.", "getTipoMovimiento NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de movimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de movimiento.", "getTipoMovimiento NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de movimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de movimiento.", "getTipoMovimiento " + ex.getMessage());
        }
     }
     
     public Respuesta getTiposMovimientos() {
         try {
             Query qryMovimientos = em.createNamedQuery("TipoMovimientos.findAll", TipoMovimientos.class);
             List<TipoMovimientos> tiposMovimientos = (List<TipoMovimientos>) qryMovimientos.getResultList();
             List<TipoMovimientosDto> tiposMovimientosDto = new ArrayList<>();
             for (TipoMovimientos mov : tiposMovimientos) {
                 tiposMovimientosDto.add(new TipoMovimientosDto(mov));
             }
             return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TiposMovimientos", tiposMovimientosDto);
         } catch (Exception ex) {
             LOG.log(Level.SEVERE, "Ocurrio un error al consultar los tipos de movimientos.", ex);
             return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los tipos de movimientos.", "getTiposMovimientos " + ex.getMessage());
         }
     }
     
     public Respuesta guardarTipoMovimiento(TipoMovimientosDto dto){
     try {
            TipoMovimientos tipoMovimientos;
            if (dto.getId() != null && dto.getId() > 0) {
                tipoMovimientos = em.find(TipoMovimientos.class, dto.getId());
                if (tipoMovimientos == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el tipo de movimiento a modificar.", "guardarTipoMovimiento NoResultException");
                }
                tipoMovimientos.update(dto);
                tipoMovimientos = em.merge(tipoMovimientos);
            } else {
                tipoMovimientos = new TipoMovimientos(dto);
                em.persist(tipoMovimientos);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoMovimiento", new TipoMovimientosDto(tipoMovimientos));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el tipo de movimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el tipo de movimiento.", "guardarTipoMovimiento " + ex.getMessage());
        }
     }
     public Respuesta eliminarTipoMovimiento(Long id){
     try {
            TipoMovimientos tipoMovimientos;
            if (id != null && id > 0) {
                tipoMovimientos = em.find(TipoMovimientos.class, id);
                if (tipoMovimientos == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el tipo de movimiento a eliminar.", "eliminarTipoMovimiento NoResultException");
                }
                em.remove(tipoMovimientos);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el tipo de movimiento a eliminar.", "eliminarTipoMovimiento NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el tipo de movimiento porque tiene relaciones con otros registros.", "eliminarTipoMovimiento " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el tipo de movimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el tipo de movimiento.", "eliminarTipoMovimiento " + ex.getMessage());
        }
     }
}
