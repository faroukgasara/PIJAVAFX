/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pijavafx.events.controller.AdminEventsController;
import pijavafx.events.controller.UpdateeventController;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;

import pijavafx.reclamation.entity.Reclamation;
import pijavafx.reclamation.service.Mail;
import pijavafx.reclamation.service.ReclamationService;

/**
 *
 * @author ala
 */
public class SocieteReclamationController implements Initializable{

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, String> columnId;
    @FXML
    private TableColumn<Reclamation, String> columnMotif;
    @FXML
    private TableColumn<Reclamation, String> columnMessage;
    @FXML
    private TableColumn<Reclamation, String> columnGsm;
    @FXML
    private TableColumn<Reclamation, String> columnStatus;
    @FXML
    private Label label;
    @FXML
    private Button btnremove;
    @FXML
    private TextField recherche;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          ObservableList<Reclamation> rec = ReclamationService.AfficheToutRec();
          columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        columnGsm.setCellValueFactory(new PropertyValueFactory<>("gsm"));
       
         columnStatus.setCellValueFactory(new PropertyValueFactory<>("status2"));
         FilteredList<Reclamation> filtredData = new FilteredList<>(rec, b -> true);
              recherche.textProperty().addListener(((observable, oldValue, newValue) -> {
            filtredData.setPredicate(recs -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (recs.getMotif().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (recs.getCompanyName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(recs.getMessage()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
            

        }));
              
          SortedList<Reclamation> sortedData = new SortedList<>(filtredData);
            sortedData.comparatorProperty().bind(tableReclamation.comparatorProperty());
            tableReclamation.setItems(sortedData);
        
         tableReclamation.setRowFactory(tv -> {
            TableRow<Reclamation> row = new TableRow<>();
            
            row.setOnMouseClicked(recs -> {
                if (recs.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        Reclamation rowData = row.getItem();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pijavafx/reclamation/views/updateReclamation.fxml"));
                        AnchorPane pane = (AnchorPane) loader.load();
                        UpdateReclamationController uc = loader.getController();
                        uc.getData(rowData);
                        rootPane.getChildren().setAll(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });    
         
          
          
     }
        public void AfficheToutRec(ActionEvent rec) {
        ObservableList<Reclamation> recs = ReclamationService.AfficheToutRec();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        columnGsm.setCellValueFactory(new PropertyValueFactory<>("gsm"));
      
        tableReclamation.setItems(recs);

    }
    @FXML
          private void UpdateSelectedRow(ActionEvent rec) throws Exception {
        ReclamationService ge = new ReclamationService();
        ge.studyRec(tableReclamation.getSelectionModel().getSelectedItem());
        AfficheToutRec(rec);
        Mail.sendMail("wael.bannani@esprit.tn");
        
    }
    
}
