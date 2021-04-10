/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.formation;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import pijavafx.events.utils.BDConnector;

/**
 * FXML Controller class
 *
 * @author fedi
 */
public class FormationFXMLController implements Initializable {

    @FXML
    private Button btn_addFormation;

  
    @FXML
    private TextArea t_title;
    @FXML
    private TextArea t_desc;
    @FXML
    private TextArea t_date;
    @FXML
    private TextArea t_localisation;
    @FXML
    private TextArea t_image;
    @FXML
    private TextArea t_idsoc;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void hundleaddFormation(ActionEvent event) throws SQLException {
        String title = t_title.getText();
        String description = t_desc.getText();
        String date_at = t_date.getText();
        String localisation = t_localisation.getText();
        int id_soc = Integer.parseInt(t_idsoc.getText());
        String imagef = t_image.getText();
          Formation f = new Formation(0,  description , date_at ,title , localisation , id_soc , imagef);      
        formationCrud fc = new formationCrud();
        fc.addformation(f);
               
        
       
            
        
    }
   
    
}
