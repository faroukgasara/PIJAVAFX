/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.formation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pijavafx.events.utils.BDConnector;

/**
 *
 * @author fedi
 */
 

public class formationCrud {
    
     static Connection conx = null;
    static PreparedStatement pst = null;

      public void addformation(Formation f){
           conx = BDConnector.driverBD();
        String SQL = "Insert into Formation (description , date_at , title , localisation ,id_soc , imagef) values (?,?,?,?,?,?)";
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            pst = conx.prepareStatement(SQL);
            pst.setString(3,f.getTitle());
            pst.setString(1,f.getDescription());
            pst.setString(2,date);
            pst.setString(4,f.getLocalisation());
            pst.setInt(5,f.getId_soc());
            pst.setString(6,f.getImagef());
            
            pst.executeUpdate();
           
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
          
          
          
      }
    
    
    
}
