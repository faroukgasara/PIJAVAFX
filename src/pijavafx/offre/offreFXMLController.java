/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.offre;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import pijavafx.events.utils.BDConnector;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */

public class offreFXMLController implements Initializable {

        @FXML
    private TextField t_id;

    @FXML
    private TextField t_specialite;

    @FXML
    private TextField t_localisation;

    @FXML
    private TextField t_description;

    @FXML
    private TextField t_imagesoffre;
    

    @FXML
    private  TextField t_nb_dem;

 @FXML
    private TableView<Offre> table;
    @FXML
    private TableColumn<Offre, Integer> tab_id;
    @FXML
    private TableColumn<Offre, String> tab_specialite;
    @FXML
    private TableColumn<Offre, String> tab_localisation ;
    @FXML
    private TableColumn<Offre, Integer> tab_nb_dem;
    @FXML
    private TableColumn<Offre, String> tab_description;
    @FXML
    private TableColumn<Offre, String> tab_imagesoffre ;
    
    
    ObservableList<Offre> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            Connection conx = BDConnector.driverBD();
            ResultSet rs = conx.createStatement().executeQuery("select * from offre");
            
            while (rs.next()) {

                System.out.println("rs.getString(\"specialite\")" + rs.getString("specialite"));
                oblist.add(new Offre(rs.getInt("id"),rs.getString("specialite") , rs.getString("localisation"), rs.getInt("nb_dem"), rs.getString("description"),rs.getString("imagesoffre")));
                
            }
                    
                   
        } catch (SQLException ex) {
            Logger.getLogger(AfficheoffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tab_specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
                    tab_localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                    tab_nb_dem.setCellValueFactory(new PropertyValueFactory<>("nb_dem"));
                    tab_description.setCellValueFactory(new PropertyValueFactory<>("desception"));
                    tab_imagesoffre.setCellValueFactory(new PropertyValueFactory<>("imagesoffre"));
                    
                    table.setItems(oblist);
    }    
     @FXML
    private void hundleaddoffre(ActionEvent event) throws SQLException {
       
         int id = Integer.parseInt(t_id.getText());
       
        String specialite = t_specialite.getText();
        String localisation  = t_localisation.getText(); 
       int nb_dem = Integer.parseInt(t_nb_dem.getText());
        String description = t_description.getText();
        String imagesoffre = t_imagesoffre.getText();
     
        
       
          Offre f = new Offre (id, specialite , localisation  ,nb_dem , description  , imagesoffre);      
        offreCrud fc = new offreCrud();
        fc.addoffre(f);
               
        
       
            
        
    }
}
