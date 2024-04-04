package com.midasvision.javafxjdbc;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartamentoViewController implements Initializable, DataChangeListener {

    @FXML
    private Button bt;
    @FXML
    private TableView<Departamento> tableViewDepartamento;
    @FXML
    private TableColumn<Departamento, Long> tableColumnId;
    @FXML
    private TableColumn<Departamento, String> tableColumnNome;
    @FXML
    private TableColumn<Departamento, Departamento> tableColumnEDIT;
    private DepartamentoService departamentoService;
    private ObservableList<Departamento> obsList;

    public void setDepartamentoService(DepartamentoService service) {
        this.departamentoService = service;
    }

    @FXML
    public void onClickButton(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Departamento obj = new Departamento();
        createDialogForm(obj,"DepartamentoForm.fxml", parentStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarNodes();
    }

    public void updateTableView() {
        if(departamentoService == null)
            throw new IllegalStateException("O service não foi inicializado");

        List<Departamento> lista = departamentoService.findAll();
        obsList = FXCollections.observableArrayList(lista);
        tableViewDepartamento.setItems(obsList);
        initEditButtons();
    }

    private void inicializarNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
    }

    private void createDialogForm(Departamento obj, String path, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();

            DepartamentoFormController controller = loader.getController();
            controller.setDepartamento(obj);
            controller.setDepartamentoService(new DepartamentoService());
            controller.subscribeDataChangeListener(this);
            controller.attDadosFormulario();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Digite o nome do departamento");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

        } catch(IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @Override
    public void onDataChange() {
        updateTableView();
    }

    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Departamento, Departamento>() {
            private final Button button = new Button("edit");
            @Override
            protected void updateItem(Departamento obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> createDialogForm(
                                obj, "DepartamentoForm.fxml",Utils.currentStage(event)));
            }
        });
    }
}
