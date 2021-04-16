/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.offre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pijavafx.events.utils.BDConnector;

/**
 *
 * @author Lenovo
 */
class CategorieOffreCrud {
        
     static Connection conx = null;
    static PreparedStatement pst = null;

      public void addoffre (Offre  f){
           conx = BDConnector.driverBD();
        String SQL = "Insert into offre (id , type ) values (?,?,)";
   
        try {
            pst = conx.prepareStatement(SQL);
         //   pst.setInt(1,f.getid());
         //   pst.setString(2,f.getspecialite());
          //  pst.setString(3,f.getLocalisation());
          //  pst.setInt(4,f.getnb_dem());
          //  pst.setString(5,f.getdescription ());
           //  pst.setString(6,f.getimagesoffre ());
            
            System.out.println("f.getid()"+f.getId());
            
              
                
                                  
                         pst.setInt(1,f.getId());
          pst.setString(2,f.getType());
         
           
            pst.executeUpdate();
           
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
          
          
      }

    void addcategorieoffre(CategorieOffre f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
