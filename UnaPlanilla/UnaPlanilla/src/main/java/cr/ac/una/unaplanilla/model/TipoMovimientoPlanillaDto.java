package cr.ac.una.unaplanilla.model;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Oscar
 */
public class TipoMovimientoPlanillaDto {

    private StringProperty id;
    private ObjectProperty<String> tipoMonto;
    private StringProperty monto;
    private StringProperty tipoAplDedPorc;
    private Long version;
    private Boolean modificado;
    private TipoMovimientosDto tipoMovimientos;

    public TipoMovimientoPlanillaDto() {
        this.id = new SimpleStringProperty("");
        this.tipoMonto = new SimpleObjectProperty("A");
        this.monto = new SimpleStringProperty("");
        this.tipoAplDedPorc = new SimpleStringProperty("T");
        this.modificado = false;
        this.tipoMovimientos = new TipoMovimientosDto();
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

    public StringProperty getIdProperty() {
        return id;
    }

    public ObjectProperty getTipoMontoProperty() {
        return tipoMonto;
    }

    public StringProperty getMontoProperty() {
        return monto;
    }

    public StringProperty getTipoAplDedPorcProperty() {
        return tipoAplDedPorc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id.get());
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
        if (this.getId() != null && other.getId() != null) {
            return Objects.equals(this.getId(), other.getId());
        }
        if (this.tipoMovimientos != null && other.tipoMovimientos != null) {
            return Objects.equals(this.tipoMovimientos, other.tipoMovimientos);
        }
        return Objects.equals(this.id.get(), other.id.get());
    }

    @Override
    public String toString() {
        return "TipoMovimientoPlanillaDto{" + "id=" + id + ", tipoMonto=" + tipoMonto + ", monto=" + monto + ", tipoMovimientos=" + tipoMovimientos + '}';
    }
}
