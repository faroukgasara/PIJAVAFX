/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField tfid;
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
    @FXML
    private TextField tfpicture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    public ArrayList<EventEntity> AfficheToutEvent() {
        ArrayList<EventEntity> event = GestionEvent.AfficheToutEvent();
        System.out.println(event.toString());
        return event;
    }
    
    @FXML
    private void addEvent(ActionEvent event) {
        int id = Integer.parseInt(tfid.getText());
        String title = tftitle.getText();
        String type = tftype.getText();
        String description = tfdescription.getText();
        String localitation = tflocalitation.getText();
        int id_societe = Integer.parseInt(tfid_societe.getText());
        String picture = tfpicture.getText();
        int viewed = Integer.parseInt(tfid.getText());
        EventEntity e = new EventEntity(id, type, title, type, description, localitation, id_societe, picture, viewed);
        
        GestionEvent ge= new GestionEvent();
        ge.addEvent(e);
                            
                            
    }

    

    
}
