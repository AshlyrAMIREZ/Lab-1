package cr.ac.una.unaplanilla.model;

import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Oscar
 */
public class TipoMovimientosDto {

    private StringProperty id;
    private StringProperty tipo;
    private StringProperty descripcion;
    private BooleanProperty gravable;
    private BooleanProperty impRenta;
    private StringProperty tipoMonto;
    private StringProperty monto;
    private StringProperty tipoAplDedPorc;
    private BooleanProperty estado;
    private Long version;
    private Boolean modificado;

    public TipoMovimientosDto() {
        this.id = new SimpleStringProperty("");
        this.tipo = new SimpleStringProperty("I");
        this.descripcion = new SimpleStringProperty("");
        this.gravable = new SimpleBooleanProperty(false);
        this.impRenta = new SimpleBooleanProperty(false);
        this.tipoMonto = new SimpleStringProperty("A");
        this.monto = new SimpleStringProperty("");
        this.tipoAplDedPorc = new SimpleStringProperty("T");
        this.estado = new SimpleBooleanProperty(true);
        this.modificado = false;
    }

    public Long getId() {
        if (this.id.get() != null && !this.id.get().isBlank()) {
            return Long.valueOf(this.id.get());
        }
        return null;
    }

    public void setId(Long id) {
        this.id.set(id != null ? id.toString() : "");
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public Boolean getGravable() {
        return gravable.get();
    }

    public void setGravable(Boolean gravable) {
        this.gravable.set(gravable != null ? gravable : false);
    }

    public Boolean getImpRenta() {
        return impRenta.get();
    }

    public void setImpRenta(Boolean impRenta) {
        this.impRenta.set(impRenta != null ? impRenta : false);
    }

    public String getTipoMonto() {
        return tipoMonto.get();
    }

    public void setTipoMonto(String tipoMonto) {
        this.tipoMonto.set(tipoMonto);
    }

    public Long getMonto() {
        if (this.monto.get() != null && !this.monto.get().isBlank()) {
            return Long.valueOf(this.monto.get());
        }
        return null;
    }

    public void setMonto(Long monto) {
        this.monto.set(monto != null ? monto.toString() : "");
    }

    public String getTipoAplDedPorc() {
        return tipoAplDedPorc.get();
    }

    public void setTipoAplDedPorc(String tipoAplDedPorc) {
        this.tipoAplDedPorc.set(tipoAplDedPorc);
    }

    public Boolean getEstado() {
        return estado.get();
    }

    public void setEstado(Boolean estado) {
        this.estado.set(estado != null ? estado : true);
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

    public StringProperty getIdProperty() {
        return id;
    }

    public StringProperty getTipoProperty() {
        return tipo;
    }

    public StringProperty getDescripcionProperty() {
        return descripcion;
    }

    public BooleanProperty getGravableProperty() {
        return gravable;
    }

    public BooleanProperty getImpRentaProperty() {
        return impRenta;
    }

    public StringProperty getTipoMontoProperty() {
        return tipoMonto;
    }

    public StringProperty getMontoProperty() {
        return monto;
    }

    public StringProperty getTipoAplDedPorcProperty() {
        return tipoAplDedPorc;
    }

    public BooleanProperty getEstadoProperty() {
        return estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id.get());
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
        if (this.getId() != null && other.getId() != null) {
            return Objects.equals(this.getId(), other.getId());
        }
        return Objects.equals(this.id.get(), other.id.get());
    }

    @Override
    public String toString() {
        return descripcion.get() != null && !descripcion.get().isBlank() ? descripcion.get() : "Movimiento";
    }
}
