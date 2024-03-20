package com.midasvision.javafxjdbc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainViewController {
    @FXML
    private Label lb;
    @FXML
    public void onClick() {
        lb.setText("Funcionou");
    }
}
