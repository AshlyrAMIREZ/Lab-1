/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.unaplanilla.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
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
    private MFXComboBox<?> cbxDeducciones;
    @FXML
    private MFXTextField txtMonto;
    @FXML
    private MFXTextField txtDescripcion;
    @FXML
    private MFXButton btnNuevo;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnGuardar;

    @FXML
    private void onKeyPressTxtId(KeyEvent event) {
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
