/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author Oscar
 */
public class TipoMovimientoPlanillaDto {

    private Long id;
    @NotBlank(message = "El tipo de monto no puede estar vacio")
    private String tipoMonto;
    @NotNull(message = "El monto no puede estar vacio")
    private Long monto;
    @NotBlank(message = "El tipo de aplicacion de deducibles no puede estar vacio")
    private String tipoAplDedPorc;
    private Long version;
    private Boolean modificado;
    private TipoMovimientosDto tipoMovimientos;

    public TipoMovimientoPlanillaDto() {
        this.modificado = false;
    }

    public TipoMovimientoPlanillaDto(Long id) {
        this();
        this.id = id;
    }

    public TipoMovimientoPlanillaDto(TipomovimientoPlanilla entity) {
        this();
        this.id = entity.getId();
        this.tipoMonto = entity.getTipoMonto();
        this.monto = entity.getMonto();
        this.tipoAplDedPorc = entity.getTipoAplDedPorc();
        this.version = entity.getVersion();
        if (entity.getTmovId() != null) {
            this.tipoMovimientos = new TipoMovimientosDto(entity.getTmovId());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setTipoAplDedPorc(String tipoAplDedPorc) {
        this.tipoAplDedPorc = tipoAplDedPorc;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public TipoMovimientosDto getTipoMovimientos() {
        return tipoMovimientos;
    }

    public void setTipoMovimientos(TipoMovimientosDto tipoMovimientos) {
        this.tipoMovimientos = tipoMovimientos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoMovimientoPlanillaDto other = (TipoMovimientoPlanillaDto) obj;
        if (this.id != null && other.id != null) {
            return Objects.equals(this.id, other.id);
        }
        if (this.tipoMovimientos != null && other.tipoMovimientos != null) {
            return Objects.equals(this.tipoMovimientos, other.tipoMovimientos);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TipoMovimientoPlanillaDto{" + "id=" + id + ", tipoMonto=" + tipoMonto + ", monto=" + monto + ", tipoMovimientos=" + tipoMovimientos + '}';
    }
}
