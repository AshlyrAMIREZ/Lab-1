/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    public TipoMovimientoPlanillaDto() {
    }
    
    public TipoMovimientoPlanillaDto(TipomovimientoPlanilla entity){
     this.id = entity.getId();
     this.tipoMonto = entity.getTipoMonto();
     this.monto = entity.getMonto();
     this.tipoAplDedPorc = entity.getTipoAplDedPorc();
     this.version = entity.getVersion();
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
    
    
}
