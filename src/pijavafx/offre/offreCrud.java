/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.offre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import pijavafx.events.utils.BDConnector;


/**
 *
 * @author Lenovo
 */
class offreCrud {


    
     static Connection conx = null;
    static PreparedStatement pst = null;

      public void addoffre (Offre  f){
           conx = BDConnector.driverBD();
        String SQL = "Insert into offre (id , specialite , localisation , nb_dem ,description ,imagesoffre) values (?,?,?,?,?,?)";
   
        try {
            pst = conx.prepareStatement(SQL);
         //   pst.setInt(1,f.getid());
         //   pst.setString(2,f.getspecialite());
          //  pst.setString(3,f.getLocalisation());
          //  pst.setInt(4,f.getnb_dem());
          //  pst.setString(5,f.getdescription ());
           //  pst.setString(6,f.getimagesoffre ());
            
            System.out.println("f.getid()"+f.getId());
            
              System.out.println("f.getspecialite()"+f.getSpecialite());
                System.out.println("f.getLocalisation()"+f.getLocalisation());
                  System.out.println("f.getnb_dem()"+f.getNbDem());
                    System.out.println("f.getdescription ()"+f.getDescription ());
                      System.out.println("f.getimagesoffre ()"+f.getImagesoffre ());
                
                      
                         pst.setInt(1,f.getId());
          pst.setString(2,f.getSpecialite());
           pst.setString(3,f.getLocalisation());
            pst.setInt(4,f.getNbDem());
           pst.setString(5,f.getDescription());
            pst.setString(6,f.getImagesoffre());
           //   pst.setInt(1,666);
           // pst.setString(2,"ffff");
           // pst.setString(3,"ffff");
           // pst.setInt(4,7);
           // pst.setString(5,"ffff");
           //  pst.setString(6,"ff");
           
            pst.executeUpdate();
           
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
          
          
      }
    
    
}
