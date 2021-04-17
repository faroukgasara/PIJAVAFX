/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class EventinfoController implements Initializable {

    @FXML
    private Label labeltitle;
    @FXML
    private Label labeltype;
    @FXML
    private Label labeldesc;
    @FXML
    private Label labelloc;
    @FXML
    private Label labelviewed;
    @FXML
    private Label labeldate;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button print;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void Eventinfo(int id){
        GestionEvent.UpdateViewedEvent(id);
        EventEntity event = GestionEvent.AfficheEventInfo(id);
        labeltitle.setText(event.getTitle());
        labeltype.setText(event.getType());
        labeldesc.setText(event.getDescription());
        labelloc.setText(event.getLocalitation());
        labelviewed.setText(Integer.toString(event.getViewed()));
        labeldate.setText((event.getDate_at()).toString());
        
        
    }



    @FXML
    private void print(ActionEvent event) {
    }
}
