/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;
import pijavafx.events.utils.BDConnector;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class AddeventController implements Initializable {

    @FXML
    private Button btnvalider;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tflocalitation;
    @FXML
    private TextField tfid_societe;
    private TextField tfpicture;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private DatePicker date_at;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void addEvent(ActionEvent event) {

        String title = tftitle.getText();
        LocalDate locald = date_at.getValue();
        Date date = Date.valueOf(locald);
        String type = tftype.getText();
        String description = tfdescription.getText();
        String localitation = tflocalitation.getText();
        int id_societe = Integer.parseInt(tfid_societe.getText());
        EventEntity e = new EventEntity(0, date, title, type, description, localitation, id_societe, 0);

        GestionEvent ge = new GestionEvent();
        ge.addEvent(e);
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/events/gui/AdminEvents.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void back(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/events/gui/AdminEvents.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
