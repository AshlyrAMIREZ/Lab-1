package cr.ac.una.unaplanilla.controller;

import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.model.TipoMovimientoPlanillaDto;
import cr.ac.una.unaplanilla.model.TipoMovimientosDto;
import cr.ac.una.unaplanilla.model.TipoPlanillaDto;
import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.service.TipoMovimientosService;
import cr.ac.una.unaplanilla.service.TipoPlanillaService;
import cr.ac.una.unaplanilla.util.BindingUtils;
import cr.ac.una.unaplanilla.util.FlowController;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author cbcar
 */
public class TiposPlanillaController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TabPane tbpTipoPlanilla;
    @FXML
    private Tab tbpTipoPlanillas;
    @FXML
    private MFXTextField txtId;
    @FXML
    private MFXCheckbox chkActivo;
    @FXML
    private MFXTextField txtCodigo;
    @FXML
    private MFXTextField txtDescripcion;
    @FXML
    private MFXTextField txtPlanillasMes;
    @FXML
    private Tab tbpInclusionEmpleados;
    @FXML
    private MFXTextField txtIdEmpleado;
    @FXML
    private MFXTextField txtNombreEmpleado;
    @FXML
    private MFXButton btnAgregarEmpleado;
    @FXML
    private TableView<EmpleadoDto> tbvEmpleados;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcCodigo;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcNombre;
    @FXML
    private TableColumn<EmpleadoDto, Boolean> tbcEliminar;
    @FXML
    private MFXButton btnNuevo;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private Tab tbpMovimientoPlanillas;
    @FXML
    private MFXTextField txfIdPlanilla;
    @FXML
    private MFXRadioButton rdbAbsoluto;
    @FXML
    private ToggleGroup tggTipoMonto;
    @FXML
    private MFXRadioButton rdbPorcentual;
    @FXML
    private MFXTextField txfMonto;
    @FXML
    private MFXComboBox<String> cbxDeducciones;
    @FXML
    private MFXButton btnAgregarMovimiento;
    @FXML
    private TableView<TipoMovimientoPlanillaDto> tbvPlanillas;
    @FXML
    private TableColumn<TipoMovimientoPlanillaDto, String> tbcIdPlanilla;
    @FXML
    private TableColumn<TipoMovimientoPlanillaDto, String> tbcTipoMovimiento;
    @FXML
    private TableColumn<TipoMovimientoPlanillaDto, String> tbcMonto;
    @FXML
    private TableColumn<TipoMovimientoPlanillaDto, String> tbcDeduccion;
    private TipoPlanillaDto tipoPlanillaDto;
    private ObjectProperty<TipoPlanillaDto> tipoPlanillaProperty = new SimpleObjectProperty<>();
    private EmpleadoDto empleadoDto;
    private ObjectProperty<EmpleadoDto> empleadoProperty = new SimpleObjectProperty<>();
    private TipoMovimientoPlanillaDto tipoMovimientoPlanillaDto;
    private ObjectProperty<TipoMovimientoPlanillaDto> movimientoProperty = new SimpleObjectProperty<>();
    private boolean sincronizandoMovimiento;
    private List<Node> requeridos = new ArrayList<>();
    @FXML
    private TableColumn<TipoMovimientoPlanillaDto, Boolean> tbcEliminarMovimientos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdbAbsoluto.setUserData("A");
        rdbPorcentual.setUserData("P");
        txtId.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtCodigo.delegateSetTextFormatter(Formato.getInstance().maxLengthFormat(4));
        txtDescripcion.delegateSetTextFormatter(Formato.getInstance().letrasFormat(40));
        txtPlanillasMes.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txfIdPlanilla.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txfMonto.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtIdEmpleado.delegateSetTextFormatter(Formato.getInstance().integerFormat());

        this.tipoPlanillaDto = new TipoPlanillaDto();
        bindTipoPlanilla();
        this.empleadoDto = new EmpleadoDto();
        bindEmpleado();
        this.tipoMovimientoPlanillaDto = new TipoMovimientoPlanillaDto();
        bindMovimiento();

        tbcCodigo.setCellValueFactory((cd) -> cd.getValue().getIdProperty());
        tbcNombre.setCellValueFactory((cd) -> cd.getValue().getNombreProperty());
        tbcEliminar.setCellValueFactory((cd) -> new SimpleBooleanProperty(cd.getValue() != null));
        tbcEliminar.setCellFactory((cd) -> new ButtonCell());

        tbvEmpleados.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue != null) {
                this.empleadoDto = newValue;
                this.empleadoProperty.setValue(this.empleadoDto);
            }
        });

        // Configurar columnas de Movimientos Planilla
        tbcIdPlanilla.setCellValueFactory(cd -> cd.getValue().getTipoMovimientos().getIdProperty());
        tbcTipoMovimiento.setCellValueFactory(cd -> {
            String tipo = cd.getValue().getTipoMonto();
            if ("A".equals(tipo)) {
                return new javafx.beans.property.SimpleStringProperty("Absoluto");
            }
            if ("P".equals(tipo)) {
                return new javafx.beans.property.SimpleStringProperty("Porcentual");
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });
        tbcMonto.setCellValueFactory(cd -> cd.getValue().getMontoProperty());
        tbcDeduccion.setCellValueFactory(cd -> {
            String letra = cd.getValue().getTipoAplDedPorc();
            if ("T".equals(letra)) {
                return new javafx.beans.property.SimpleStringProperty("Todos");
            }
            if ("G".equals(letra)) {
                return new javafx.beans.property.SimpleStringProperty("Gravable");
            }
            if ("R".equals(letra)) {
                return new javafx.beans.property.SimpleStringProperty("Renta");
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });
        // Configuramos que la celda no esté vacía y le ponemos nuestra nueva clase de botón
        tbcEliminarMovimientos.setCellValueFactory((cd) -> new javafx.beans.property.SimpleBooleanProperty(cd.getValue() != null));
        tbcEliminarMovimientos.setCellFactory((cd) -> new ButtonCellMovimiento());

        cargarValoresDefecto();
        indicarRequeridos();
        cbxDeducciones.getItems().clear();
        cbxDeducciones.getItems().addAll("Todos", "Gravable", "Renta");
    }

    @Override
    public void initialize() {

    }

    private void bindTipoPlanilla() {
        try {
            tipoPlanillaProperty.addListener((obs, oldVal, newVal) -> {
                if (oldVal != null) {
                    txtId.textProperty().unbind();
                    txtCodigo.textProperty().unbindBidirectional(oldVal.getCodigoProperty());
                    txtDescripcion.textProperty().unbindBidirectional(oldVal.getDescripcionProperty());
                    txtPlanillasMes.textProperty().unbindBidirectional(oldVal.getPlanillaPorMesProperty());
                    chkActivo.selectedProperty().unbindBidirectional(oldVal.getActivoProperty());
                }
                if (newVal != null) {
                    if (newVal.getIdProperty().get() != null
                            && !newVal.getIdProperty().get().isBlank()) {
                        txtId.textProperty().bind(newVal.getIdProperty());
                    }
                    txtCodigo.textProperty().bindBidirectional(newVal.getCodigoProperty());
                    txtDescripcion.textProperty().bindBidirectional(newVal.getDescripcionProperty());
                    txtPlanillasMes.textProperty().bindBidirectional(newVal.getPlanillaPorMesProperty());
                    chkActivo.selectedProperty().bindBidirectional(newVal.getActivoProperty());
                }
            });

        } catch (Exception ex) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al realizar el bindeo", getStage(),
                    "Ocurrió un error al realizar el bindeo.");
        }
    }

    private void bindEmpleado() {
        try {
            empleadoProperty.addListener((obs, oldVal, newVal) -> {
                if (oldVal != null) {
                    txtIdEmpleado.textProperty().unbind();
                    txtNombreEmpleado.textProperty().unbindBidirectional(oldVal.getNombreProperty());
                }
                if (newVal != null) {
                    if (newVal.getIdProperty().get() != null
                            && !newVal.getIdProperty().get().isBlank()) {
                        txtIdEmpleado.textProperty().bind(newVal.getIdProperty());
                    }
                    txtNombreEmpleado.textProperty().bindBidirectional(newVal.getNombreProperty());
                }
            });

        } catch (Exception ex) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al realizar el bindeo", getStage(),
                    "Ocurrió un error al realizar el bindeo.");
        }
    }

    private void cargarValoresDefecto() {
        this.tipoPlanillaDto = new TipoPlanillaDto();
        this.tipoPlanillaDto.setActivo(Boolean.TRUE);
        this.tipoPlanillaProperty.setValue(this.tipoPlanillaDto);
        txtId.clear();
        txtId.requestFocus();
        limpiarEmpleado();
        cargarEmpleados();
        limpiarMovimiento();
        cargarPlanilla();
    }

    private void limpiarEmpleado() {
        tbvEmpleados.getSelectionModel().select(null);
        this.empleadoDto = new EmpleadoDto();
        this.empleadoProperty.setValue(this.empleadoDto);
        txtIdEmpleado.clear();
        txtIdEmpleado.requestFocus();
    }

    private void cargarEmpleados() {
        tbvEmpleados.getItems().clear();
        tbvEmpleados.setItems(this.tipoPlanillaDto.getEmpleados());
        tbvEmpleados.refresh();
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtDescripcion, txtCodigo, txtPlanillasMes));
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

    @FXML
    private void onKeyPressTxtId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtId.getText().isBlank()) {
            cargarTipoPlanilla(Long.valueOf(txtId.getText()));
        }
    }

    private void cargarTipoPlanilla(Long id) {
        try {
            TipoPlanillaService service = new TipoPlanillaService();
            Respuesta respuesta = service.getTipoPlanilla(id);
            if (respuesta.getEstado()) {
                this.tipoPlanillaDto = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
                this.tipoPlanillaProperty.setValue(this.tipoPlanillaDto);
                validarRequeridos();
                cargarEmpleados();
                cargarPlanilla();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tipo Planilla", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(TiposPlanillaController.class.getName()).log(Level.SEVERE, "Error consultando el tipo de planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tipo Planilla", getStage(), "Ocurrio un error consultando el tipo de planilla.");
        }
    }

    private void cargarEmpleado(Long id) {
        try {
            EmpleadoService empleadoService = new EmpleadoService();
            Respuesta respuesta = empleadoService.getEmpleado(id);
            if (respuesta.getEstado()) {
                this.empleadoDto = (EmpleadoDto) respuesta.getResultado("Empleado");
                this.empleadoProperty.setValue(this.empleadoDto);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Empleado", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error buscando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Empleado", getStage(), "Ocurrió un error buscando el empleado.");
        }
    }

    @FXML
    private void onKeyPressedTxtCodigo(KeyEvent event) {
    }

    @FXML
    private void onKeyPressedTxtIdEmpleado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdEmpleado.getText().isBlank()) {
            cargarEmpleado(Long.valueOf(txtIdEmpleado.getText()));
        }
    }

    @FXML
    private void onActionBtnAgregarEmpleado(ActionEvent event) {
        if (this.empleadoDto.getId() == null || this.empleadoDto.getNombre().isBlank()) {
            new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Empleado", getStage(), "Es necesario cargar un empleado para agregarlo a lista.");
        } else if (tbvEmpleados.getItems().stream().noneMatch(e -> e.equals(this.empleadoDto))) {
            this.empleadoDto.setModificado(true);
            tbvEmpleados.getItems().add(this.empleadoDto);
            tbvEmpleados.refresh();
        }
    }

    @FXML
    private void selectionChangeTabEmp(Event event) {
        if (tbpInclusionEmpleados.isSelected()) {
            if (this.tipoPlanillaDto == null || this.tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Empleado", getStage(),
                        "Debe cargar el tipo de planilla al que desea agregar empleados.");
                tbpTipoPlanilla.getSelectionModel().select(tbpTipoPlanillas);
            }
        }
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (tbpInclusionEmpleados.isSelected()) {
            limpiarEmpleado();
        } else if (tbpMovimientoPlanillas.isSelected()) {
            limpiarMovimiento();
        } else if (new Mensaje().showConfirmation("Limpiar tipo planilla", getStage(), "¿Está seguro que desea limpiar el registro?")) {
            cargarValoresDefecto();
        }
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        BusquedaController busquedaController = (BusquedaController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaTiposPlanilla();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
        TipoPlanillaDto tPla = (TipoPlanillaDto) busquedaController.getResultado();
        if (tPla != null) {
            cargarTipoPlanilla(tPla.getId());
        }
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (this.tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Debe cargar el tipo de planilla que desea eliminar.");
            } else {
                TipoPlanillaService service = new TipoPlanillaService();
                Respuesta respuesta = service.eliminarTipoPlanilla(this.tipoPlanillaDto.getId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tipo planilla", getStage(), "Tipo planilla eliminado correctamente.");
                    cargarValoresDefecto();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TiposPlanillaController.class.getName()).log(Level.SEVERE, "Error eliminando el tipo planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Ocurrio un error eliminando el tipo planilla.");
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Planilla", getStage(), invalidos);
            } else {
                TipoPlanillaService tipoPlanillaService = new TipoPlanillaService();
                Respuesta respuesta = tipoPlanillaService.guardarTipoPlanilla(this.tipoPlanillaDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Planilla", getStage(), respuesta.getMensaje());
                } else {
                    this.tipoPlanillaDto = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
                    this.tipoPlanillaProperty.setValue(this.tipoPlanillaDto);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tipo Planilla", getStage(), "Tipo Planilla actualizada correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error guardando el tipo de planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Planilla", getStage(), "Ocurrio un error guardando el tipo de planilla.");
        }
    }

    private class ButtonCell extends TableCell<EmpleadoDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                EmpleadoDto emp = (EmpleadoDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                tipoPlanillaDto.getEmpleadosEliminados().add(emp);
                tbvEmpleados.getItems().remove(emp);
                tbvEmpleados.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    private class ButtonCellMovimiento extends TableCell<TipoMovimientoPlanillaDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellMovimiento() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                TipoMovimientoPlanillaDto mov = (TipoMovimientoPlanillaDto) ButtonCellMovimiento.this.getTableView().getItems().get(ButtonCellMovimiento.this.getIndex());
                tipoPlanillaDto.getTipoMovimientosPlanillaEliminados().add(mov);
                tbvPlanillas.getItems().remove(mov);
                tbvPlanillas.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    //--------------------------------------------------------------------------
    //             SECCIÓN CORRESPONDIENTE AL LABORATORIO #1
    //--------------------------------------------------------------------------
    @FXML
    private void selectionChangeTabMov(Event event) {
        if (tbpMovimientoPlanillas.isSelected()) {
            if (this.tipoPlanillaDto == null || this.tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Movimiento", getStage(),
                        "Debe cargar el tipo de planilla al que desea agregar movimientos.");
                tbpTipoPlanilla.getSelectionModel().select(tbpTipoPlanillas);
            }
        }
    }

    @FXML
    private void onActionBtnAgregarMovimiento(ActionEvent event) {
        if (this.tipoPlanillaDto.getId() == null) {
            new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Tipo de Movimiento", getStage(),
                    "Es necesario cargar una planilla para agregarle movimientos.");
            return;
        }
        // Verificar que sí se haya cargado un movimiento al presionar Enter en el ID
        if (this.tipoMovimientoPlanillaDto.getTipoMovimientos() == null
                || this.tipoMovimientoPlanillaDto.getTipoMovimientos().getId() == null) {
            new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Tipo de Movimiento", getStage(),
                    "Debe cargar el tipo de movimiento primero (presione Enter en el ID del movimiento).");
            return;
        }
        // Sacar el texto del ComboBox y guardarlo como letra
        String deduccionSeleccionada = cbxDeducciones.getSelectionModel().getSelectedItem();
        if (deduccionSeleccionada != null) {
            if (deduccionSeleccionada.equals("Todos")) {
                this.tipoMovimientoPlanillaDto.setTipoAplDedPorc("T");
            } else if (deduccionSeleccionada.equals("Gravable")) {
                this.tipoMovimientoPlanillaDto.setTipoAplDedPorc("G");
            } else if (deduccionSeleccionada.equals("Renta")) {
                this.tipoMovimientoPlanillaDto.setTipoAplDedPorc("R");
            }
        }

        if (txfMonto.getText() == null || txfMonto.getText().isBlank()) {
            new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Tipo de Movimiento", getStage(),
                    "Debe indicar el monto del movimiento.");
            return;
        }
        if (this.tipoMovimientoPlanillaDto.getTipoAplDedPorc() == null
                || this.tipoMovimientoPlanillaDto.getTipoAplDedPorc().isBlank()) {
            this.tipoMovimientoPlanillaDto.setTipoAplDedPorc("T");
        }
        if (tbvPlanillas.getItems().stream().noneMatch(e -> e.equals(this.tipoMovimientoPlanillaDto))) {
            this.tipoMovimientoPlanillaDto.setModificado(true);
            tbvPlanillas.getItems().add(this.tipoMovimientoPlanillaDto);
            tbvPlanillas.refresh();
            limpiarMovimiento();
        } else {
            this.tipoMovimientoPlanillaDto.setModificado(true);
            tbvPlanillas.refresh();
            new Mensaje().showModal(Alert.AlertType.WARNING, "Agregar Tipo de Movimiento", getStage(),
                    "El movimiento ya está en la lista, se actualizará al guardar.");
        }
    }

    @FXML
    private void onKeyPressTxtIdPlanilla(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txfIdPlanilla.getText().isBlank()) {
            cargarTipoMovimiento(Long.valueOf(txfIdPlanilla.getText()));
        }
    }

    private void bindMovimiento() {
        try {
            movimientoProperty.addListener((obs, oldVal, newVal) -> {
                if (oldVal != null) {
                    txfMonto.textProperty().unbindBidirectional(oldVal.getMontoProperty());
                    BindingUtils.unbindToggleGroupToProperty(tggTipoMonto, oldVal.getTipoMontoProperty());
                }
                if (newVal != null) {
                    txfMonto.textProperty().bindBidirectional(newVal.getMontoProperty());
                    BindingUtils.bindToggleGroupToProperty(tggTipoMonto, newVal.getTipoMontoProperty());
                }
            });
        } catch (Exception ex) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al realizar el bindeo", getStage(),
                    "Ocurrió un error al realizar el bindeo.");
        }
    }

    private void limpiarMovimiento() {
        tbvPlanillas.getSelectionModel().select(null);
        this.tipoMovimientoPlanillaDto = new TipoMovimientoPlanillaDto();
        this.tipoMovimientoPlanillaDto.setTipoMonto("A");
        this.tipoMovimientoPlanillaDto.setTipoAplDedPorc("T");
        this.movimientoProperty.setValue(this.tipoMovimientoPlanillaDto);
        txfIdPlanilla.clear();
        cbxDeducciones.getSelectionModel().clearSelection();
    }

    private void cargarPlanilla() {
        tbvPlanillas.getItems().clear();
        tbvPlanillas.setItems(this.tipoPlanillaDto.getTipoMovimientosPlanilla());
        tbvPlanillas.refresh();
    }

    private void cargarTipoMovimiento(Long id) {
        try {
            TipoMovimientosService tipoMovimientosService = new TipoMovimientosService();
            Respuesta respuesta = tipoMovimientosService.getTipoMovimiento(id);
            if (respuesta.getEstado()) {
                TipoMovimientosDto tipoMovimientosDto = (TipoMovimientosDto) respuesta.getResultado("TipoMovimiento");
                this.tipoMovimientoPlanillaDto.setTipoMovimientos(tipoMovimientosDto);
                txfIdPlanilla.setText(String.valueOf(tipoMovimientosDto.getId()));
                // Copiar los valores por defecto del movimiento a la pantalla
                if (tipoMovimientosDto.getMonto() != null) {
                    this.tipoMovimientoPlanillaDto.setMonto(tipoMovimientosDto.getMonto());
                }
                /*
                if (tipoMovimientosDto.getTipoMonto() != null) {
                    this.tipoMovimientoPlanillaDto.setTipoMonto(tipoMovimientosDto.getTipoMonto());
                }
                 */
                if (tipoMovimientosDto.getTipoMonto() != null) {
                    if (tipoMovimientosDto.getTipoMonto().equals("A")) {
                        rdbAbsoluto.setSelected(true);
                    } else if (tipoMovimientosDto.getTipoMonto().equals("P")) {
                        rdbPorcentual.setSelected(true);
                    }
                }
                if (tipoMovimientosDto.getTipoAplDedPorc() != null) {
                    String letra = tipoMovimientosDto.getTipoAplDedPorc();
                    if (letra.equals("T")) {
                        cbxDeducciones.getSelectionModel().selectItem("Todos");
                    } else if (letra.equals("G")) {
                        cbxDeducciones.getSelectionModel().selectItem("Gravable");
                    } else if (letra.equals("R")) {
                        cbxDeducciones.getSelectionModel().selectItem("Renta");
                    }
                }
                if (tipoMovimientosDto.getTipoAplDedPorc() != null && !tipoMovimientosDto.getTipoAplDedPorc().isBlank()) {
                    this.tipoMovimientoPlanillaDto.setTipoAplDedPorc(tipoMovimientosDto.getTipoAplDedPorc());
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Movimiento", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(TiposPlanillaController.class.getName()).log(Level.SEVERE, "Error buscando el tipo de movimiento.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Movimiento", getStage(), "Ocurrió un error buscando el tipo de movimiento.");
        }
    }

}
