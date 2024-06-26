package com.midasvision.javafxjdbc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {
    @FXML
    private MenuItem menuItemVendedor;
    @FXML
    private MenuItem menuItemDepartamento;
    @FXML
    private MenuItem menuItemSobre;

    @FXML
    public void onMenuItemVendedor() {
        System.out.println("Menu Vendedor foi clicado");
    }

    @FXML
    public void onMenuItemDepartamento() {
        loadView("departamento-view.fxml", (DepartamentoViewController controller) -> {
            controller.setDepartamentoService(new DepartamentoService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemSobre() {
        loadView("sobre-view.fxml", x -> {});
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private synchronized <T> void loadView(String path, Consumer<T> initAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            VBox newVbox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();

            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVbox.getChildren());

            T controller = loader.getController();
            initAction.accept(controller);

        } catch(IOException e) {
            Alerts.showAlert("Erro", null, "Erro ao carregar a tela", Alert.AlertType.ERROR);
        }

    }
}
