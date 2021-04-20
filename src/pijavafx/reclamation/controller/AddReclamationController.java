package pijavafx.reclamation.controller;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pijavafx.events.controller.AdminEventsController;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;
import pijavafx.reclamation.entity.Reclamation;
import pijavafx.reclamation.entity.Company;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import pijavafx.events.controller.UpdateeventController;
import pijavafx.reclamation.service.ReclamationService;
import pijavafx.reclamation.service.ReclamationService;
import pijavafx.events.utils.BDConnector;
import pijavafx.reclamation.service.Mail;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class AddReclamationController implements Initializable {
  
       static Connection conx = null;
    static ResultSet rs = null;
    static ResultSet rs1 = null;
    static Statement stmt = null;
    static PreparedStatement req = null;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField gsm;
    @FXML
    private TextField message;
    @FXML
    private Button btnvalider;
    @FXML
    private Button btn;
    @FXML
    private ComboBox<String> motif;
    @FXML
    private ComboBox<String> company;
    final ObservableList options =FXCollections.observableArrayList(
            "problem of connexion","being late","being rude");
    final ObservableList optionsC =FXCollections.observableArrayList();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        motif.setItems(options);
        fillComboBox();
        company.setItems(optionsC);
      
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
    @FXML
    private void ajouterReclamation(ActionEvent rec) throws Exception {

        String rgsm = gsm.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now();  
        String rdate=dtf.format(now);
         String rmessage = message.getText();
         String rmotif=motif.getValue();
         System.out.println(rmotif);
           String  rcompany=company.getValue();
          String status2="in progress"; 
           
         
        System.out.println(rdate);
      
    
         
       //  Company c=new Company(0, rcompany);
        Reclamation e = new Reclamation(0, rmessage, rmotif, rgsm,rdate,rcompany, status2);
        

        ReclamationService ge = new ReclamationService();
        ge.ajouterReclamation(e);
         Mail.sendMail("wael.bannani@esprit.tn");
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/reclamation/views/reclamationList.fxml"));
       
            
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
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
