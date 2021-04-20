/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pijavafx.events.controller.AdminEventsController;
import pijavafx.events.utils.BDConnector;
import static pijavafx.reclamation.controller.AddReclamationController.conx;
import static pijavafx.reclamation.controller.AddReclamationController.rs;
import static pijavafx.reclamation.controller.AddReclamationController.stmt;
import pijavafx.reclamation.entity.Reclamation;
import pijavafx.reclamation.service.ReclamationService;

/**
 *
 * @author ala
 */
public class UpdateReclamationController implements Initializable{

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnvalider;
    @FXML
    private TextField tfid;
    @FXML
    private Button btnback;
    @FXML
    private Label id;
    @FXML
    private Label motif;
    @FXML
    private Label message;
    @FXML
    private Label gsm;
    @FXML
    private Label company;
    @FXML
    private TextField tfgsm;
    @FXML
    private ComboBox<String> tfcompany;
    @FXML
    private ComboBox<String> tfmotif;
    @FXML
    private TextField tfmessage;
     final ObservableList options =FXCollections.observableArrayList(
            "problem of connexion","being late","being rude");
    final ObservableList optionsC =FXCollections.observableArrayList();
    @FXML
    private TextField tfdate;
    @FXML
    private Label createdAt;
    String status2;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfid.setEditable(false);
        tfdate.setEditable(false);
         tfmotif.setItems(options);
        fillComboBox();
        tfcompany.setItems(optionsC);
        
    }
        public void fillComboBox() {
           try {
               conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            rs = stmt.executeQuery("SELECT name_company,id FROM Company");
               while (rs.next()){
                   optionsC.add(rs.getString("name_company"));
                   
               }
               stmt.close();
               rs.close();
           } catch (SQLException ex) {
               Logger.getLogger(AddReclamationController.class.getName()).log(Level.SEVERE, null, ex);
           }
         
    }
     public void getData(Reclamation rowData){
           tfid.setText(Integer.toString(rowData.getId()));
           tfmessage.setText(rowData.getMessage());
           tfgsm.setText(rowData.getGsm());
           tfmotif.setValue(rowData.getMotif());
           tfcompany.setValue(rowData.getCompanyName());
           tfdate.setText(rowData.getCreatedAt());
           String status2=rowData.getStatus2();
           
     }

    @FXML
    private void updatereclamation(ActionEvent event) {
       Reclamation rec =new Reclamation(Integer.parseInt(tfid.getText()), tfmessage.getText(), tfmotif.getValue(), tfgsm.getText(),tfdate.getText(),tfcompany.getValue(),status2);
            ReclamationService ge =new ReclamationService();
            ge.modifierReclamation(rec);
        try {
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/reclamation/views/reclamationList.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(UpdateReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       @FXML
    private void back(ActionEvent rec) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/reclamation/views/reclamationList.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
}
