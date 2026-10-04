/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.unaplanilla.controller;

import cr.ac.una.unaplanilla.util.Formato;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private List<Node> requeridos = new ArrayList();
    private List<String> deducciones = new ArrayList();
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
    private MFXRadioButton rdbSiGravable1;
    @FXML
    private ToggleGroup tggDeducciones;
    @FXML
    private MFXRadioButton rdbAbsoluto1;
    @FXML
    private MFXRadioButton rdbSiImprenta1;
    @FXML
    private MFXButton btnNuevo;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnGuardar;

    @FXML
    private void onKeyPressTxtId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtId.getText().isBlank()) {
            cargarMovimientoPlanilla(Long.valueOf(txtId.getText()));
        }
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {        
        
    }
    
    private void limpiarMovimientos() {
    }
    
    private void cargarMovimientos() {
    }
    
    private void cargarMovimientoPlanilla(Long id) {
        
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
        txtId.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtMonto.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtDescripcion.delegateSetTextFormatter(Formato.getInstance().letrasFormat(40));
    }    

    @Override
    public void initialize() {}

    
}
