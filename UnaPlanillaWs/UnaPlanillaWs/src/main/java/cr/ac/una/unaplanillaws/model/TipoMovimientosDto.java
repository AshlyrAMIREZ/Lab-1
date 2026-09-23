/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.model;

/**
 *
 * @author Oscar
 */
class TipoMovimientosDto {
    private Long id;
    private String tipo;
    private String descripcion;
    private Boolean gravable;
    private Boolean impRenta;
    private String tipoMonto;
    private Long monto;
    private String tipoAplDedPorc;
    private Boolean estado;
    private Long version;

    public TipoMovimientosDto() {
    }

    public TipoMovimientosDto(TipoMovimientos tiposMovimientos) {
        this.id = tiposMovimientos.getId();
        this.tipo = tiposMovimientos.getTipo();
        this.descripcion = tiposMovimientos.getDescripcion();
        this.gravable = tiposMovimientos.getGravable().equals("S");
        this.impRenta = tiposMovimientos.getImpRenta().equals("S");
        this.tipoMonto = tiposMovimientos.getTipoMonto();
        this.monto = tiposMovimientos.getMonto();
        this.tipoAplDedPorc = tiposMovimientos.getTipoAplDedPorc();
        this.estado = tiposMovimientos.getEstado().equals("A");
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

    public Boolean getGravable() {
        return gravable;
    }

    public void setGravable(Boolean gravable) {
        this.gravable = gravable;
    }

    public Boolean getImpRenta() {
        return impRenta;
    }

    public void setImpRenta(Boolean impRenta) {
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
    
    
    
}
