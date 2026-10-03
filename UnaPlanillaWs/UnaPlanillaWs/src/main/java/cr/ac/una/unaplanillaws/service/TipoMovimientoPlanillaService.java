/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.service;

import cr.ac.una.unaplanillaws.model.TipomovimientoPlanilla;
import cr.ac.una.unaplanillaws.model.TipoMovimientoPlanillaDto;
import cr.ac.una.unaplanillaws.model.TipoMovimientos;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.Respuesta;
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

/**
 *
 * @author Oscar
 */
@Stateless
@LocalBean
public class TipoMovimientoPlanillaService {

    private static final Logger LOG = Logger.getLogger(TipoMovimientoPlanillaService.class.getName());

    @PersistenceContext(unitName = "UnaPlanillaWsPU")
    private EntityManager em;

    public Respuesta getTipoMovimientoPlanilla(Long id) {
        try {
            Query qry = em.createNamedQuery("TipomovimientoPlanilla.findById", TipomovimientoPlanilla.class);
            qry.setParameter("id", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoMovimientoPlanilla", new TipoMovimientoPlanillaDto((TipomovimientoPlanilla) qry.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un movimiento de planilla con el ID ingresado.", "getTipoMovimientoPlanilla NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar el movimiento de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar el movimiento de planilla.", "getTipoMovimientoPlanilla NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar el movimiento de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar el movimiento de planilla.", "getTipoMovimientoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta getTiposMovimientoPlanilla() {
        try {
            Query qry = em.createNamedQuery("TipomovimientoPlanilla.findAll", TipomovimientoPlanilla.class);
            List<TipomovimientoPlanilla> lista = (List<TipomovimientoPlanilla>) qry.getResultList();
            List<TipoMovimientoPlanillaDto> listaDto = new ArrayList<>();
            for (TipomovimientoPlanilla item : lista) {
                listaDto.add(new TipoMovimientoPlanillaDto(item));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TiposMovimientoPlanilla", listaDto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar los movimientos de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar los movimientos de planilla.", "getTiposMovimientoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta guardarTipoMovimientoPlanilla(TipoMovimientoPlanillaDto dto) {
        try {
            TipomovimientoPlanilla tipoMovimientoPlanilla;
            if (dto.getId() != null && dto.getId() > 0) {
                tipoMovimientoPlanilla = em.find(TipomovimientoPlanilla.class, dto.getId());
                if (tipoMovimientoPlanilla == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el movimiento de planilla a modificar.", "guardarTipoMovimientoPlanilla NoResultException");
                }
                tipoMovimientoPlanilla.update(dto);
                if (dto.getTipoMovimientos() != null && dto.getTipoMovimientos().getId() != null) {
                    tipoMovimientoPlanilla.setTmovId(em.find(TipoMovimientos.class, dto.getTipoMovimientos().getId()));
                }
                tipoMovimientoPlanilla = em.merge(tipoMovimientoPlanilla);
            } else {
                tipoMovimientoPlanilla = new TipomovimientoPlanilla(dto);
                if (dto.getTipoMovimientos() != null && dto.getTipoMovimientos().getId() != null) {
                    tipoMovimientoPlanilla.setTmovId(em.find(TipoMovimientos.class, dto.getTipoMovimientos().getId()));
                }
                em.persist(tipoMovimientoPlanilla);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoMovimientoPlanilla", new TipoMovimientoPlanillaDto(tipoMovimientoPlanilla));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el movimiento de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el movimiento de planilla.", "guardarTipoMovimientoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoMovimientoPlanilla(Long id) {
        try {
            TipomovimientoPlanilla tipoMovimientoPlanilla;
            if (id != null && id > 0) {
                tipoMovimientoPlanilla = em.find(TipomovimientoPlanilla.class, id);
                if (tipoMovimientoPlanilla == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el movimiento de planilla a eliminar.", "eliminarTipoMovimientoPlanilla NoResultException");
                }
                em.remove(tipoMovimientoPlanilla);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el movimiento de planilla a eliminar.", "eliminarTipoMovimientoPlanilla NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el movimiento de planilla porque tiene relaciones con otros registros.", "eliminarTipoMovimientoPlanilla " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrió un error al eliminar el movimiento de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al eliminar el movimiento de planilla.", "eliminarTipoMovimientoPlanilla " + ex.getMessage());
        }
    }
}
