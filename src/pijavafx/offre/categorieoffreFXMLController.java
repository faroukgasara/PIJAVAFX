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

public class categorieoffreFXMLController implements Initializable {

   
 
 @FXML
  private TextField t_type;

 

 @FXML
    private TableView<CategorieOffre> table;
    @FXML
    private TableColumn<CategorieOffre, Integer> tab_id;
     @FXML
    private TableColumn<CategorieOffre, String> tab_type;
  
    
    
     ObservableList<CategorieOffre> oblist = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           System.out.println("rs.getString(\"type\")");
            Connection conx = BDConnector.driverBD();
            ResultSet rs = conx.createStatement().executeQuery("select * from categorie_offre");
            
            while (rs.next()) {
               

                System.out.println("rs.getString(\"type\")" + rs.getString("type"));
                oblist.add(new CategorieOffre(rs.getInt("id"),rs.getString("type") ));
                 tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tab_type.setCellValueFactory(new PropertyValueFactory<>("type"));
                 
                    table.setItems(oblist);
            }
                    
                   
        } catch (SQLException ex) {
            Logger.getLogger(AfficheoffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
     @FXML
    private void hundleaddCategorieOffre(ActionEvent event) throws SQLException {
       
        
       
        String type = t_type.getText();
      
     
        
       
          CategorieOffre f = new CategorieOffre ( );      
        CategorieOffreCrud fc = new CategorieOffreCrud();
        fc.addcategorieoffre(f);
               
        
       
            
        
    }
}
