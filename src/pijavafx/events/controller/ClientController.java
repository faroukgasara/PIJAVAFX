/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
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
    private TableColumn<EventEntity, String> columndescription;
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
    @FXML
    private Label mailerreur;
    @FXML
    private Label nomerruer;
    @FXML
    private Label ageerreur;
    @FXML
    private TextField recherche;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventid.setEditable(false);
        eventid.setVisible(false);

        ObservableList<EventEntity> events = GestionEvent.AfficheToutEvent();
        columndescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnDATE.setCellValueFactory(new PropertyValueFactory<>("date_at"));
        columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnTITLE.setCellValueFactory(new PropertyValueFactory<>("title"));
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
    private void addparticipatian(ActionEvent event) {



        //mail
        if (mail.getText().isEmpty()) {
            mailerreur.setText("Mail is Empty");
            mailerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (!mail.getText().isEmpty()) {
            mailerreur.setText("");
        }

        //nom
        if (nom.getText().isEmpty()) {
            nomerruer.setText("Nom is Empty");
            nomerruer.setTextFill(Color.rgb(210, 117, 84));
        } else if (!nom.getText().isEmpty()) {
            nomerruer.setText("");
        }

        //age
        if (age.getText().isEmpty()) {
            ageerreur.setText("Age is Empty");
            ageerreur.setTextFill(Color.rgb(210, 117, 84));
        } else if (!age.getText().isEmpty()) {
            ageerreur.setText("");
        }
        eventid.setText(Integer.toString(tableEvents.getSelectionModel().getSelectedItem().getId()));
        String email = mail.getText();
        String nnom = nom.getText();
        int aage = Integer.parseInt(age.getText());
        ParticipantE p = new ParticipantE(0, email, nnom, aage);
        System.out.println(p.toString());

        GestionEvent ge = new GestionEvent();
        ge.addParticipant(p, tableEvents.getSelectionModel().getSelectedItem().getId());

        String to = mail.getText();
        String host = "smtp.gmail.com";
        final String mail = "faroukgasaraa@gmail.com";
        final String password = "Farouk1998Gassara";

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(mail);
            m.addRecipients(Message.RecipientType.TO, to);
            m.setSubject("Participation");
            m.setText("Participation Confirmed");
            Transport.send(m);

        } catch (MessagingException e) {
        }

    }

}
