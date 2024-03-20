package com.midasvision.javafxjdbc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

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
        System.out.println("Menu Departamento foi clicado");
    }

    @FXML
    public void onMenuItemSobre() {
        System.out.println("Menu Sobre foi clicado");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
