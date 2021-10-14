package com.rumata;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Controller {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private Label res_d;

    @FXML
    private Label res_h;

    @FXML
    private Label res_l1;

    @FXML
    private Label res_l2;

    @FXML
    private Label l2_sheme;

    @FXML
    private Label l1_sheme;

    @FXML
    private Label d_sheme;

    @FXML
    private Label h_sheme;

    @FXML
    private TextField text_l;

    @FXML
    private TextField text_h;

    @FXML
    private ComboBox<Integer> combo_box;

    @FXML
    private Button btn_calculate;

    @FXML
    private Button btn_clear;

    @FXML
    private Label text_info;

    private int l2;
    private int l1;
    private int h;
    private int d;

    @FXML
    void initialize() {
        combo_box.getItems().addAll(20, 25, 30, 35, 40, 45, 50);
        combo_box.setValue(20);

        anchor_pane.setOnKeyPressed(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            if (event.getCode() == KeyCode.ENTER) {
                btn_calculate.arm();
                pause.setOnFinished(e -> {
                    btn_calculate.disarm();
                    btn_calculate.fire();
                });
                pause.play();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                btn_clear.arm();
                pause.setOnFinished(e -> {
                    btn_clear.disarm();
                    btn_clear.fire();
                });
                pause.play();
            }
        });

        text_l.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        text_l.setText(oldValue);
                    }
                }
        );

        text_h.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        text_h.setText(oldValue);
                    }
                }
        );

        btn_calculate.setOnAction(event -> {
            if (checkNumbers()) {
                text_info.setText("");
                h = Integer.parseInt(text_h.getText());
                l1 = Integer.parseInt(text_l.getText());
                res_h.setText(String.valueOf(h));
                h_sheme.setText(String.valueOf(h));
                res_l1.setText(String.valueOf(l1));
                l1_sheme.setText(String.valueOf(l1));
                l2 = l1 + combo_box.getValue() * 2;
                res_l2.setText(String.valueOf(l2));
                l2_sheme.setText(String.valueOf(l2));
                calculateDiagonal(l1, h);
                d_sheme.setText(String.valueOf(d));
                res_d.setText(String.valueOf(d));
            }
        });

        btn_clear.setOnAction(event -> {
            text_h.setText("");
            text_l.setText("");
            text_info.setText("");
            res_d.setText("");
            res_h.setText("");
            res_l1.setText("");
            res_l2.setText("");
            d_sheme.setText("");
            l1_sheme.setText("");
            l2_sheme.setText("");
            h_sheme.setText("");
        });
    }

    private boolean checkNumbers() {
        if (text_l.getText().equals("") || text_h.getText().equals("")) {
            text_info.setStyle("-fx-text-fill: red; -fx-font-size: 36");
            text_info.setText("Введите все данные!");

            return false;
        } else {
            text_info.setText("");
            return true;
        }
    }

    private void calculateDiagonal(int l1, int h) {
        double diag = Math.sqrt(Math.pow(l1, 2) / 2 + Math.pow(h, 2));
        d = (int) Math.round(diag);
    }
}
