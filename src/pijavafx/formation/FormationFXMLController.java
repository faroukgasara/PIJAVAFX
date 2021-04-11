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
    
    private Connection conx = null;
    private PreparedStatement pst = null;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void hundleaddFormation(ActionEvent event) throws SQLException {
        conx = BDConnector.driverBD();
        String SQL = "Insert into Formation (description , date_at , title , localisation ,id_soc , imagef) values (?,?,?,?,?,?)";
        String title = t_title.getText();
        String description = t_desc.getText();
        String date_at = t_date.getText();
        String localisation = t_localisation.getText();
        String id_soc = t_idsoc.getText();
        String imagef = t_image.getText();
                System.out.println( title );
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            pst = conx.prepareStatement(SQL);
            pst.setString(3,title);
            pst.setString(1,description);
            pst.setString(2,date);
            pst.setString(4,localisation);
            pst.setString(5,id_soc);
            pst.setString(6,imagef);
            
            pst.executeUpdate();
           
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        
       
            
        
    }
   
    
}
