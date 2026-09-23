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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "PLAM_TIPMOVPLANILLA")
@NamedQueries({
    @NamedQuery(name = "TipomovimientoPlanilla.findAll", query = "SELECT p FROM TipomovimientoPlanilla p"),
    @NamedQuery(name = "TipomovimientoPlanilla.findById", query = "SELECT p FROM TipomovimientoPlanilla p WHERE p.id = :id")})
public class TipomovimientoPlanilla implements Serializable {

    

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_TIPOMOVIMIENTOSPLANILLA_TMOVPLA_ID_GENERATOR", sequenceName = "una.PLAM_TIPMOVPLANILLA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_TIPOMOVIMIENTOSPLANILLA_TMOVPLA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TPM_ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "TPM_TIPOMONTO")
    private String tipoMonto;
    @Basic(optional = false)
    @Column(name = "TPM_MONTO")
    private Long monto;
    @Basic(optional = false)
    @Column(name = "TPM_TIPOAPLDEDPORC")
    private String tipoAplDedPorc;
    @Basic(optional = false)
    @Column(name = "TPM_VERSION")
    private Long version;
    
    @JoinColumn(name = "TPLA_ID", referencedColumnName = "TPLA_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPlanilla TipoPlanillaId;
    
    @JoinColumn(name = "TMOV_ID", referencedColumnName = "TMOV_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoMovimientos tipoMovimientoId;

    public TipomovimientoPlanilla() {
    }

    public TipomovimientoPlanilla(BigDecimal tpmId) {
        this.id = tpmId;
    }

    public TipomovimientoPlanilla(BigDecimal tpmId, String tpmTipomonto, Long tpmMonto, String tpmTipoapldedporc, Long tpmVersion) {
        this.id = tpmId;
        this.tipoMonto = tpmTipomonto;
        this.monto = tpmMonto;
        this.tipoAplDedPorc = tpmTipoapldedporc;
        this.version = tpmVersion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public void setTipoAplDedPorc(String TipoAplDedPorc) {
        this.tipoAplDedPorc = TipoAplDedPorc;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long Version) {
        this.version = Version;
    }

    public TipoMovimientos getTmovId() {
        return tipoMovimientoId;
    }

    public void setTmovId(TipoMovimientos tmovId) {
        this.tipoMovimientoId = tmovId;
    }

    public TipoPlanilla getTipoPlanillaId() {
        return TipoPlanillaId;
    }

    public void setTipoPlanillaId(TipoPlanilla TipoPlanillaId) {
        this.TipoPlanillaId = TipoPlanillaId;
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
        if (!(object instanceof TipomovimientoPlanilla)) {
            return false;
        }
        TipomovimientoPlanilla other = (TipomovimientoPlanilla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanillaws.PlamTipmovplanilla[ tpmId=" + id + " ]";
    }

    
}
