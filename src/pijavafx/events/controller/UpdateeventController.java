/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class UpdateeventController implements Initializable {

    @FXML
    private TextField tftitle;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tflocalitation;
    @FXML
    private Button btnvalider;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfidsoc;
    @FXML
    private TextField tfviewed;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private DatePicker date_at;
    @FXML
    private Button btnback;
    @FXML
    private Label titleerreur;
    @FXML
    private Label typeerreur;
    @FXML
    private Label descriptionerreur;
    @FXML
    private Label locationerreur;
    @FXML
    private Label dateerreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfid.setEditable(false);
        tfid.setVisible(false);
        tfviewed.setEditable(false);
        tfidsoc.setEditable(false);
    }

    public void getData(EventEntity rowData) {
        System.out.println(rowData);
        tfid.setText(Integer.toString(rowData.getId()));
        tfidsoc.setText(Integer.toString(rowData.getId_societe()));
        tfviewed.setText(Integer.toString(rowData.getViewed()));

        tftitle.setText(rowData.getTitle());
        tftype.setText(rowData.getType());
        tfdescription.setText(rowData.getDescription());
        tflocalitation.setText(rowData.getLocalitation());

    }

    @FXML
    private void updateevent(ActionEvent event) {

        //title
        if (tftitle.getText().isEmpty()) {
            titleerreur.setText("Title is Empty");
            titleerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (!tftitle.getText().isEmpty()) {
            titleerreur.setText("");
        }

        //type
        if (tftype.getText().isEmpty()) {
            typeerreur.setText("Type is Empty");
            typeerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (!tftype.getText().isEmpty()) {
            typeerreur.setText("");
        }

        //desc
        if (tfdescription.getText().isEmpty()) {
            descriptionerreur.setText("Description is Empty");
            descriptionerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (!tfdescription.getText().isEmpty()) {
            descriptionerreur.setText("");
        }

        //loca
        if (tflocalitation.getText().isEmpty()) {
            locationerreur.setText("Localisation is Empty");
            locationerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (!tflocalitation.getText().isEmpty()) {
            locationerreur.setText("");
        }

        //date
        if (date_at.getValue() == null) {
            dateerreur.setText("Select Date");
            dateerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (date_at.getValue() != null) {
            dateerreur.setText("");
        }
        LocalDate locald = date_at.getValue();
        Date date = Date.valueOf(locald);
        EventEntity e = new EventEntity(Integer.parseInt(tfid.getText()), date, tftitle.getText(), tftype.getText(),
                tfdescription.getText(), tflocalitation.getText(), Integer.parseInt(tfidsoc.getText()), Integer.parseInt(tfviewed.getText()));
        GestionEvent ge = new GestionEvent();
        ge.updateEvent(e);
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
