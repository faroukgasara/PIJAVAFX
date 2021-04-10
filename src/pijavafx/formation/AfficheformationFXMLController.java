/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.formation;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pijavafx.events.utils.BDConnector;

/**
 * FXML Controller class
 *
 * @author fedi
 */
public class AfficheformationFXMLController implements Initializable {

    @FXML
    private TableView<Formation> table;
    @FXML
    private TableColumn<Formation, Integer> tab_id;
    @FXML
    private TableColumn<Formation, String> tab_title;
    @FXML
    private TableColumn<Formation, String> tab_desc;
    @FXML
    private TableColumn<Formation, String> tab_date;
    @FXML
    private TableColumn<Formation, String> tab_loc;
    @FXML
    private TableColumn<Formation, Integer> tab_idsoc;
    @FXML
    private TableColumn<Formation, String> tab_image;
    
    ObservableList<Formation> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection conx = BDConnector.driverBD();
            ResultSet rs = conx.createStatement().executeQuery("select * from Formation");
            
            while (rs.next()) {

                oblist.add(new Formation(rs.getInt("id"),rs.getString("Description") , rs.getString("date_at"), rs.getString("Title"), rs.getString("Localisation"),rs.getInt("id_soc") ,rs.getString("Imagef") ));
                
            }
                    
                   
        } catch (SQLException ex) {
            Logger.getLogger(AfficheformationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tab_title.setCellValueFactory(new PropertyValueFactory<>("title"));
                    tab_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
                    tab_date.setCellValueFactory(new PropertyValueFactory<>("date_at"));
                    tab_loc.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                    tab_idsoc.setCellValueFactory(new PropertyValueFactory<>("id_soc"));
                    tab_image.setCellValueFactory(new PropertyValueFactory<>("imagef"));
                    
                    table.setItems(oblist);
    }    
    
}
