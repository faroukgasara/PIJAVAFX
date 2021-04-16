/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private TableColumn<EventEntity, String> columnDate;
    @FXML
    private TableColumn<EventEntity, String> columnTitle;
    @FXML
    private TableColumn<EventEntity, String> columnType;
    @FXML
    private TableColumn<EventEntity, String> columnViewed;
    @FXML
    private Label label;
    @FXML
    private Button btnremove;
    @FXML
    private Button btnaddevent;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnparticipant;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<EventEntity> events = GestionEvent.AfficheToutEvent();
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date_at"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnViewed.setCellValueFactory(new PropertyValueFactory<>("viewed"));
        FilteredList<EventEntity> filtredData = new FilteredList<>(events, b -> true);
        recherche.textProperty().addListener(((observable, oldValue, newValue) -> {
            filtredData.setPredicate(event -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (event.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(event.getDescription()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });

        }));
        SortedList<EventEntity> sortedData = new SortedList<>(filtredData);
        sortedData.comparatorProperty().bind(tableEvents.comparatorProperty());
        tableEvents.setItems(sortedData);
        
        tableEvents.setRowFactory(tv -> {
            TableRow<EventEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        EventEntity rowData = row.getItem();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pijavafx/events/gui/updateevent.fxml"));
                        AnchorPane pane = (AnchorPane) loader.load();
                        UpdateeventController uc = loader.getController();
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

    @FXML
    public void AfficheToutEvent(ActionEvent event) {
        ObservableList<EventEntity> events = GestionEvent.AfficheToutEvent();
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date_at"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnViewed.setCellValueFactory(new PropertyValueFactory<>("viewed"));
        tableEvents.setItems(events);

    }

    @FXML
    private void DeleteSelectedRow(ActionEvent event) {
        GestionEvent ge = new GestionEvent();
        ge.deleteEvent(tableEvents.getSelectionModel().getSelectedItem());
        AfficheToutEvent(event);
        
    }

    @FXML
    private void addevent(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/events/gui/addevent.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void participant(ActionEvent event) {
        TableRow<EventEntity> row = new TableRow<>();
        try {
            EventEntity rowData = tableEvents.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pijavafx/events/gui/participantevent.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            ParticipanteventController uc = loader.getController();
            uc.getData(rowData,tableEvents.getSelectionModel().getSelectedItem().getId());
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
