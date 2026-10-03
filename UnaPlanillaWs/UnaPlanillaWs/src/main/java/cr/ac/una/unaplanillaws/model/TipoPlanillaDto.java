/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author cbcar
 */
public class TipoPlanillaDto {

    private Long id;
    @NotBlank(message = "El codigo de la planilla no puede estar vacio")
    private String codigo;
    @NotBlank(message = "La descripcion de la planilla no puede estar vacia")
    private String descripcion;
    @Min(value = 1, message = "La cantidad de planillas por mes debe ser minimo 1")
    private Integer planillaPorMes;
    @Size(min = 1960, max = 2100, message = "El año de la ultima planilla debe contener año valido")
    private Integer anoUltimaPlanilla;
    @Size(min = 1, max = 12, message = "El mes de la planilla debe estar en el rango de 1 a 12")
    private Integer mesUltimaPlanilla;
    private Boolean activo;
    private Long version;
    private Boolean modificado;
    private List<EmpleadoDto> empleados;
    private List<EmpleadoDto> empleadosEliminados;
    private List<TipoMovimientoPlanillaDto> tipoMovimientosPlanilla;
    private List<TipoMovimientoPlanillaDto> tipoMovimientosPlanillaEliminados;

    public TipoPlanillaDto() {
        this.modificado = false;
        this.empleados = new ArrayList<>();
        this.empleadosEliminados = new ArrayList<>();
        this.tipoMovimientosPlanilla = new ArrayList<>();
        this.tipoMovimientosPlanillaEliminados = new ArrayList<>();
    }

    public TipoPlanillaDto(TipoPlanilla tipoPlanilla) {
        this();
        this.id = tipoPlanilla.getId();
        this.codigo = tipoPlanilla.getCodigo();
        this.descripcion = tipoPlanilla.getDescripcion();
        this.planillaPorMes = tipoPlanilla.getPlanillaPorMes();
        this.anoUltimaPlanilla = tipoPlanilla.getAnoUltimaPlanilla();
        this.mesUltimaPlanilla = tipoPlanilla.getMesUltimaPlanilla();
        this.activo = tipoPlanilla.getEstado().equals("A");
        this.version = tipoPlanilla.getVersion();
        if (tipoPlanilla.getPlamTipmovplanillaList() != null) {
            for (TipomovimientoPlanilla tmp : tipoPlanilla.getPlamTipmovplanillaList()) {
                this.tipoMovimientosPlanilla.add(new TipoMovimientoPlanillaDto(tmp));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPlanillaPorMes() {
        return planillaPorMes;
    }

    public void setPlanillaPorMes(Integer planillaPorMes) {
        this.planillaPorMes = planillaPorMes;
    }

    public Integer getAnoUltimaPlanilla() {
        return anoUltimaPlanilla;
    }

    public void setAnoUltimaPlanilla(Integer anoUltimaPlanilla) {
        this.anoUltimaPlanilla = anoUltimaPlanilla;
    }

    public Integer getMesUltimaPlanilla() {
        return mesUltimaPlanilla;
    }

    public void setMesUltimaPlanilla(Integer mesUltimaPlanilla) {
        this.mesUltimaPlanilla = mesUltimaPlanilla;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public List<EmpleadoDto> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDto> empleados) {
        this.empleados = empleados;
    }

    public List<EmpleadoDto> getEmpleadosEliminados() {
        return empleadosEliminados;
    }

    public void setEmpleadosEliminados(List<EmpleadoDto> empleadosEliminados) {
        this.empleadosEliminados = empleadosEliminados;
    }

    public List<TipoMovimientoPlanillaDto> getTipoMovimientosPlanilla() {
        return tipoMovimientosPlanilla;
    }

    public void setTipoMovimientosPlanilla(List<TipoMovimientoPlanillaDto> tipoMovimientosPlanilla) {
        this.tipoMovimientosPlanilla = tipoMovimientosPlanilla;
    }

    public List<TipoMovimientoPlanillaDto> getTipoMovimientosPlanillaEliminados() {
        return tipoMovimientosPlanillaEliminados;
    }

    public void setTipoMovimientosPlanillaEliminados(List<TipoMovimientoPlanillaDto> tipoMovimientosPlanillaEliminados) {
        this.tipoMovimientosPlanillaEliminados = tipoMovimientosPlanillaEliminados;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final TipoPlanillaDto other = (TipoPlanillaDto) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "TipoPlanillaDto{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }

}
