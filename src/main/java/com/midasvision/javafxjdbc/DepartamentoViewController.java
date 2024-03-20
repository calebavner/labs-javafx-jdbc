package com.midasvision.javafxjdbc;

import com.midasvision.javafxjdbc.entidades.Departamento;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartamentoViewController implements Initializable {
    @FXML
    private Button bt;
    @FXML
    private TableView<Departamento> tableViewDepartamento;
    @FXML
    private TableColumn<Departamento, Long> tableColumnId;
    @FXML
    private TableColumn<Departamento, String> tableColumnNome;

    @FXML
    public void onClickButton() {
        System.out.println("O botão foi clicado");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarNodes();
    }

    private void inicializarNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
    }
}
