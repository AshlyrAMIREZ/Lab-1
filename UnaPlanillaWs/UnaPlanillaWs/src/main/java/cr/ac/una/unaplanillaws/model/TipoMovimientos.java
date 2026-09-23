/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "PLAM_TIPOMOVIMIENTOS")
@NamedQueries({
    @NamedQuery(name = "TipoMovimientos.findAll", query = "SELECT p FROM TipoMovimientos p"),
    @NamedQuery(name = "TipoMovimientos.findById", query = "SELECT p FROM TipoMovimientos p WHERE p.id = :id"),
})
public class TipoMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PLAM_TIPOMOVIMIENTOS_TMOV_ID_GENERATOR", sequenceName = "una.PLAM_TIPOMOVIMIENTOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_TIPOMOVIMIENTOS_TMOV_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TMOV_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TMOV_TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "TMOV_DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "TMOV_GRAVABLE")
    private String gravable;
    @Basic(optional = false)
    @Column(name = "TMOV_IMPRENTA")
    private String impRenta;
    @Basic(optional = false)
    @Column(name = "TMOV_TIPOMONTO")
    private String tipoMonto;
    @Basic(optional = false)
    @Column(name = "TMOV_MONTO")
    private Long monto;
    @Basic(optional = false)
    @Column(name = "TMOV_TIPOAPLDEDPORC")
    private String tipoAplDedPorc;
    @Basic(optional = false)
    @Column(name = "TMOV_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "TMOV_VERSION")
    private Long version;
    
    @OneToMany(mappedBy = "tipoMovimientoId", fetch = FetchType.LAZY)
    private List<TipomovimientoPlanilla> tiposMovimientoPlanilla;

    public TipoMovimientos() {
    }

    public TipoMovimientos(TipoMovimientosDto dto) {
        this.id = dto.getId();
        update(dto);
    }

    public void update(TipoMovimientosDto dto) {
        this.tipo = dto.getTipo();
        this.descripcion = dto.getDescripcion();
        this.gravable = dto.getGravable() ? "S": "N";
        this.impRenta = dto.getImpRenta()? "S": "N";
        this.tipoMonto = dto.getTipoMonto();
        this.monto = dto.getMonto();
        this.tipoAplDedPorc = dto.getTipoAplDedPorc();
        this.estado = dto.getEstado() ? "A" : "I";
        this.version = dto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGravable() {
        return gravable;
    }

    public void setGravable(String gravable) {
        this.gravable = gravable;
    }

    public String getImpRenta() {
        return impRenta;
    }

    public void setImpRenta(String impRenta) {
        this.impRenta = impRenta;
    }

    public String getTipoMonto() {
        return tipoMonto;
    }

    public void setTipoMonto(String tipoMonto) {
        this.tipoMonto = tipoMonto;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public String getTipoAplDedPorc() {
        return tipoAplDedPorc;
    }

    public void setTipoAplDedPorc(String tipoAplDedPorc) {
        this.tipoAplDedPorc = tipoAplDedPorc;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<TipomovimientoPlanilla> getTiposMovimientoPlanilla() {
        return tiposMovimientoPlanilla;
    }

    public void setTiposMovimientoPlanilla(List<TipomovimientoPlanilla> tiposMovimientoPlanilla) {
        this.tiposMovimientoPlanilla = tiposMovimientoPlanilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimientos)) {
            return false;
        }
        TipoMovimientos other = (TipoMovimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanillaws.PlamTipomovimientos[ tmovId=" + id + " ]";
    }
    
}
