/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class AdminEventsController implements Initializable {

    @FXML
    private Button btnload;
    @FXML
    private TableView<EventEntity> tableEvents;
    @FXML
    private TableColumn<EventEntity,String> columnId;
    @FXML
    private TableColumn<EventEntity,String> columnDate;
    @FXML
    private TableColumn<EventEntity,String> columnTitle;
    @FXML
    private TableColumn<EventEntity,String> columnType;
    @FXML
    private TableColumn<EventEntity,String> columnViewed;    
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public ArrayList<EventEntity> AfficheToutEvent() {
        ArrayList<EventEntity> event = GestionEvent.AfficheToutEvent();
        System.out.println(event.toString());
        return event;
    }
    
    
    

    
}
