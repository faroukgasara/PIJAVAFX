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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.entites.ParticipantE;
import pijavafx.events.service.GestionEvent;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class ParticipanteventController implements Initializable {

    @FXML
    private TableView<ParticipantE> tablepar;
    @FXML
    private TableColumn<ParticipantE, String> columnId;
    @FXML
    private TableColumn<ParticipantE, String> columnnom;
    @FXML
    private TableColumn<ParticipantE, String> columnage;
    @FXML
    private TableColumn<ParticipantE, String> columnmail;
    @FXML
    private Button btndelete;
    @FXML
    private TextField recherche;
    @FXML
    private Button back;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void getData(EventEntity rowData) {
        System.out.println(rowData);
        ObservableList<ParticipantE> participant = GestionEvent.AfficheParticipant(rowData.getId());
        System.out.println(participant.toString());

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnage.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        //tablepar.setItems(participant);
        FilteredList<ParticipantE> filtredData = new FilteredList<>(participant, b -> true);
        recherche.textProperty().addListener(((observable, oldValue, newValue) -> {
            filtredData.setPredicate(par -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (par.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (par.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(par.getAge()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });

        }));
        SortedList<ParticipantE> sortedData = new SortedList<>(filtredData);
        sortedData.comparatorProperty().bind(tablepar.comparatorProperty());
        tablepar.setItems(sortedData);

    }

    @FXML
    private void DeleteSelectedRow(ActionEvent event) {
        GestionEvent ge = new GestionEvent();
        ge.deletePar(tablepar.getSelectionModel().getSelectedItem());
        tablepar.getItems().remove(tablepar.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void recherche(ActionEvent event) {
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
