/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 *
 * @author Oscar
 */
public class TipoMovimientosDto {

    private Long id;
    @NotBlank(message = "El tipo de movimiento no puede estar vacio")
    private String tipo;
    @NotBlank(message = "La descripcion del movimiento no puede estar vacia")
    @Size(min = 1, max = 40, message = "La descripcion tiene que tener minimo un valor y maximo 40")
    private String descripcion;
    @NotBlank(message = "el gravable no puede ser nulo")
    private String gravable;
    @NotBlank(message = "el impuesto de renta no puede ser nulo")
    private String impRenta;
    @NotBlank(message = "El tipo de monto no puede estar blanco")
    @Size(min = 1, max = 1, message = "El tipo de monto solo puede ser de un valor")
    private String tipoMonto;
    @NotNull(message = "El monto del movimiento no puede estar vacio")
    private Long monto;
    @NotBlank(message = "El tipo de aplicacion porcentual no puede estar blanco")
    @Size(min = 1, max = 1, message = "El tipo de aplicacion porcentual solo puede ser de un valor")
    private String tipoAplDedPorc;
    
    private Boolean estado;
    private Long version;
    private Boolean modificado;

    public TipoMovimientosDto() {
        this.modificado = false;
    }

    public TipoMovimientosDto(Long id) {
        this();
        this.id = id;
    }

    public TipoMovimientosDto(TipoMovimientos tiposMovimientos) {
        this();
        this.id = tiposMovimientos.getId();
        this.tipo = tiposMovimientos.getTipo();
        this.descripcion = tiposMovimientos.getDescripcion();
        this.gravable = tiposMovimientos.getGravable();
        this.impRenta = tiposMovimientos.getImpRenta();
        this.tipoMonto = tiposMovimientos.getTipoMonto();
        this.monto = tiposMovimientos.getMonto();
        this.tipoAplDedPorc = tiposMovimientos.getTipoAplDedPorc();
        this.estado = tiposMovimientos.getEstado() != null && tiposMovimientos.getEstado().equals("A");
        this.version = tiposMovimientos.getVersion();
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final TipoMovimientosDto other = (TipoMovimientosDto) obj;
        return Objects.equals(this.id, other.id);
    }

}
