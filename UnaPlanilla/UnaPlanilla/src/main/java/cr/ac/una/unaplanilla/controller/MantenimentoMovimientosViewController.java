/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
//------------------------------------------------------------------------------
//             CONTROLADOR CORRESPONDIENTE AL LABORATORIO #1
//------------------------------------------------------------------------------
package cr.ac.una.unaplanilla.controller;

import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.model.TipoMovimientosDto;
import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.service.TipoMovimientosService;
import cr.ac.una.unaplanilla.util.BindingUtils;
import cr.ac.una.unaplanilla.util.Formato;
import cr.ac.una.unaplanilla.util.Mensaje;
import cr.ac.una.unaplanilla.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ashel_Rn
 */
public class MantenimentoMovimientosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MFXTextField txtId;
    @FXML
    private MFXCheckbox chkActivo;
    @FXML
    private MFXRadioButton rdbSiGravable;
    @FXML
    private ToggleGroup tggGravable;
    @FXML
    private MFXRadioButton rdbNoGravable;
    @FXML
    private MFXRadioButton rdbAbsoluto;
    @FXML
    private ToggleGroup tggTipoMonto;
    @FXML
    private MFXRadioButton rdbPorcentual;
    @FXML
    private MFXRadioButton rdbSiImprenta;
    @FXML
    private ToggleGroup tggImprenta;
    @FXML
    private MFXRadioButton rdbNoImprenta;
    @FXML
    private MFXTextField txtDescripcion;
    @FXML
    private MFXTextField txtMonto;
    @FXML
    private MFXRadioButton rdbTodos;
    @FXML
    private ToggleGroup tggDeducciones;
    @FXML
    private MFXRadioButton rdbGravables;
    @FXML
    private MFXRadioButton rdbImpRenta;
    @FXML
    private MFXButton btnNuevo;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnGuardar;
    private TipoMovimientosDto tipoMovimientosDto;
    private ObjectProperty<TipoMovimientosDto> tipoMovimientosProperty = new SimpleObjectProperty<>();
    private List<Node> requeridos = new ArrayList();

    @FXML
    private void onKeyPressTxtId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtId.getText().isBlank()) {
            cargarMovimientoPlanilla(Long.valueOf(txtId.getText()));
        }
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Movimientos", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            cargarValoresDefecto();
        }
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (this.tipoMovimientosDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tipo Movimiento", getStage(), "Favor consultar el tipo de movimiento a eliminar.");
            } else {
                TipoMovimientosService tipoMovimientosService = new TipoMovimientosService();
                Respuesta respuesta = tipoMovimientosService.eliminarTipoMovimiento(this.tipoMovimientosDto.getId());
                if (respuesta.getEstado()) {
                    cargarValoresDefecto();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Tipo Movimiento", getStage(), "El tipo de movimiento se eliminó correctamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tipo Movimiento", getStage(), respuesta.getMensaje());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error eliminando el tipo de movimiento.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Tipo Movimiento", getStage(), "Ocurrió un error eliminando el tipo de movimiento.");
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Movimiento", getStage(), invalidos);
            } else {
                TipoMovimientosService tipoMovimientosService = new TipoMovimientosService();
                Respuesta respuesta = tipoMovimientosService.guardarTipoMovimiento(tipoMovimientosDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Movimiento", getStage(), respuesta.getMensaje());
                } else {
                    this.tipoMovimientosDto = (TipoMovimientosDto) respuesta.getResultado("TipoMovimiento");
                    this.tipoMovimientosProperty.set(this.tipoMovimientosDto);
                    validarRequeridos();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tipo Movimiento", getStage(), "El tipo de movimiento se guardó correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error guardando el tipo de movimiento.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Movimiento", getStage(), "Ocurrio un error guardando el tipo de movimiento.");
        }
    }

    private void cargarMovimientoPlanilla(Long id) {
        try {
            TipoMovimientosService tipoMovimientosService = new TipoMovimientosService();
            Respuesta respuesta = tipoMovimientosService.getTipoMovimiento(id);
            if (respuesta.getEstado()) {
                this.tipoMovimientosDto = (TipoMovimientosDto) respuesta.getResultado("TipoMovimiento");
                this.tipoMovimientosProperty.setValue(this.tipoMovimientosDto);
                validarRequeridos();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Tipo Movimiento", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error buscando el tipo de movimiento.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Tipo Movimiento", getStage(), "Ocurrió un error buscando el tipo de movimiento.");
        }
    }

    private void bindMovimientos() {
        try {
            tipoMovimientosProperty.addListener((obs, oldVal, newVal) -> {
                if (oldVal != null) {
                    txtId.textProperty().unbind();
                    txtMonto.textProperty().unbindBidirectional(oldVal.getMontoProperty());
                    txtDescripcion.textProperty().unbindBidirectional(oldVal.getDescripcionProperty());
                    chkActivo.selectedProperty().unbindBidirectional(oldVal.getEstadoProperty());
                    BindingUtils.unbindToggleGroupToProperty(tggGravable, oldVal.getGravableProperty());
                    BindingUtils.unbindToggleGroupToProperty(tggTipoMonto, oldVal.getTipoMontoProperty());
                    BindingUtils.unbindToggleGroupToProperty(tggImprenta, oldVal.getImpRentaProperty());
                    BindingUtils.unbindToggleGroupToProperty(tggDeducciones, oldVal.getTipoAplDedPorcProperty());
                }
                if (newVal != null) {
                    if (newVal.getIdProperty().get() != null
                            && !newVal.getIdProperty().get().isBlank()) {
                        txtId.textProperty().bind(newVal.getIdProperty());
                    }
                    txtMonto.textProperty().bindBidirectional(newVal.getMontoProperty());
                    txtDescripcion.textProperty().bindBidirectional(newVal.getDescripcionProperty());
                    chkActivo.selectedProperty().bindBidirectional(newVal.getEstadoProperty());
                    BindingUtils.bindToggleGroupToProperty(tggGravable, newVal.getGravableProperty());
                    BindingUtils.bindToggleGroupToProperty(tggTipoMonto, newVal.getTipoMontoProperty());
                    BindingUtils.bindToggleGroupToProperty(tggImprenta, newVal.getImpRentaProperty());
                    BindingUtils.bindToggleGroupToProperty(tggDeducciones, newVal.getTipoAplDedPorcProperty());
                }
            });

        } catch (Exception ex) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al realizar el bindeo", getStage(),
                    "Ocurrió un error al realizar el bindeo.");
        }
    }

    private void cargarValoresDefecto() {
        tipoMovimientosDto = new TipoMovimientosDto();
        tipoMovimientosDto.setEstado(Boolean.TRUE);
        tipoMovimientosDto.setGravable("S");
        tipoMovimientosDto.setImpRenta("S");
        tipoMovimientosDto.setTipoMonto("A");
        tipoMovimientosDto.setTipoAplDedPorc("T");
        tipoMovimientosProperty.setValue(tipoMovimientosDto);
        txtId.clear();
        txtId.requestFocus();

    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtDescripcion, txtMonto));
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof MFXTextField && (((MFXTextField) node).getText() == null || ((MFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXTextField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXTextField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXPasswordField && (((MFXPasswordField) node).getText() == null || ((MFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXPasswordField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXPasswordField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXDatePicker && ((MFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((MFXDatePicker) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXDatePicker) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXComboBox && ((MFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((MFXComboBox) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXComboBox) node).getFloatingText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbSiGravable.setUserData("S");
        rdbNoGravable.setUserData("N");
        rdbAbsoluto.setUserData("A");
        rdbPorcentual.setUserData("P");
        rdbSiImprenta.setUserData("S");
        rdbNoImprenta.setUserData("N");
        rdbTodos.setUserData("T");
        rdbGravables.setUserData("G");
        rdbImpRenta.setUserData("R");
        txtId.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtMonto.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtDescripcion.delegateSetTextFormatter(Formato.getInstance().letrasFormat(40));
        tipoMovimientosDto = new TipoMovimientosDto();
        bindMovimientos();
        cargarValoresDefecto();
        indicarRequeridos();
    }

    @Override
    public void initialize() {
    }

}
