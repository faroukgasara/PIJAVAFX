/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.entites.ParticipantE;
import pijavafx.events.service.GestionEvent;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class ClientController implements Initializable {

    @FXML
    private TableView<EventEntity> tableEvents;
    @FXML
    private TableColumn<EventEntity, String> columnID;
    @FXML
    private TableColumn<EventEntity, String> columnDATE;
    @FXML
    private TableColumn<EventEntity, String> columnTYPE;
    @FXML
    private TableColumn<EventEntity, String> columnTITLE;
    @FXML
    private TextField eventid;
    @FXML
    private TextField mail;
    @FXML
    private TextField nom;
    @FXML
    private TextField age;
    @FXML
    private Button btnadd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnID.setEditable(false);
        ObservableList<EventEntity> events = GestionEvent.AfficheToutEvent();
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDATE.setCellValueFactory(new PropertyValueFactory<>("date_at"));
        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnTITLE.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableEvents.setItems(events);

    }

    @FXML
    private void addparticipatian(ActionEvent event) {
        eventid.setText(Integer.toString(tableEvents.getSelectionModel().getSelectedItem().getId()));
        String email = mail.getText();
        String nnom = nom.getText();
        int aage = Integer.parseInt(age.getText());
        ParticipantE p = new ParticipantE(0, email, nnom, aage);
        System.out.println(p.toString());

        GestionEvent ge = new GestionEvent();
        ge.addParticipant(p,tableEvents.getSelectionModel().getSelectedItem().getId());
    }

}
