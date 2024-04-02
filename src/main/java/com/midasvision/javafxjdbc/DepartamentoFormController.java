package com.midasvision.javafxjdbc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class DepartamentoFormController implements Initializable {

    private Departamento departamento;
    private DepartamentoService service;
    public List<DataChangeListener> dataChangeListeners = new ArrayList<>();

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

    public void setDepartamentoService(DepartamentoService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onBtSalvar(ActionEvent event) {
        if(departamento == null)
            throw new IllegalStateException("A entidade esta nula");
        if(service == null)
            throw new IllegalStateException("O service nulo");
        try {
            departamento = getFormData();
            service.saveOrUpdate(departamento);
            notifyListeners();
            Utils.currentStage(event).close();

        } catch(DbException e) {
            Alerts.showAlert("Erro", null, "Erro ao salvar o objeto", Alert.AlertType.ERROR);
        } catch(ValidationException e) {
            setErrorMsgs(e.getErrors());
        }
    }

    @FXML
    public void onBtCancelar(ActionEvent event) {
        Utils.currentStage(event).close();
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

    private Departamento getFormData() {
        Departamento d = new Departamento();
        ValidationException exception = new ValidationException("Validation Error");
        d.setId(Utils.tryParseToInt(txtId.getText()));

        if(txtNome.getText() == null || txtNome.getText().trim().equals("")) {
            exception.addError("Nome", "O campo não pode ser vazio");
        }
        d.setNome(txtNome.getText());

        if(exception.getErrors().size() > 0) {
            throw exception;
        }
        return d;
    }

    private void notifyListeners() {
        for(DataChangeListener d : dataChangeListeners)
            d.onDataChange();
    }

    private void setErrorMsgs(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if(fields.contains("Nome")) {
            errorMsg.setText(errors.get("Nome"));
        }
    }
}
