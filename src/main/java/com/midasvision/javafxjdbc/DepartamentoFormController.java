package com.midasvision.javafxjdbc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartamentoFormController implements Initializable {

    private Departamento departamento;

    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelar;
    @FXML
    private Label errorMsg;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;

    public void setDepartamento(Departamento dep) {
        this.departamento = dep;
    }

    @FXML
    public void onBtSalvar() {
        System.out.println("Botão salvar foi clicado");
    }

    @FXML
    public void onBtCancelar() {
        System.out.println("Botão cancelar foi clicado");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarNodes();
    }

    private void inicializarNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void attDadosFormulario() {
        if(departamento == null) throw new IllegalStateException();

        txtId.setText(String.valueOf(departamento.getId()));
        txtNome.setText(departamento.getNome());
    }
}
