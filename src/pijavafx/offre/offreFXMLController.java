/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.offre;

import java.net.URL;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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



    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
