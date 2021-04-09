/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.utils.BDConnector;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author farou
 */
public class GestionEvent {
    	static Connection conx = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	static PreparedStatement req = null;
    
    	public static ArrayList<EventEntity> AfficheToutEvent() {
		try {
			conx = BDConnector.driverBD();
			stmt = conx.createStatement();
			rs = stmt.executeQuery("SELECT * FROM evenement");

                        ArrayList<EventEntity> event= new ArrayList<>();
		
                        while(rs.next()){
                            int id = rs.getInt("id");
                            String title = rs.getString("title");
                            String type = rs.getString("type");
                            String description = rs.getString("description");
                            String localitation = rs.getString("localitation");
                            int id_societe = rs.getInt("id_societe");
                            String picture = rs.getString("picture");
                            int viewed = rs.getInt("viewed");
                            event.add(new EventEntity(id,"sdfs",title,type,description,localitation,id_societe,picture,viewed));
                            
                        }
                        
			return event;
		} catch (SQLException ex) {
			System.out.println("Erreur :" + ex.getMessage());
			return null;
		}
	}
        
        public void addEvent(EventEntity e ){
            conx = BDConnector.driverBD();
                    PreparedStatement pst;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
                try {
                    System.out.println(e.toString());
                    String sql = "INSERT INTO evenement (`date_at`, `title`, `type`, `description`, `localitation`, `id_societe`, `picture`, `viewed`)"
                            + "VALUES(?,?,?,?,?,?,?,?) ";
                    pst = conx.prepareStatement(sql);
                    pst.setString(1,date );
                    pst.setString(2,e.getTitle() );
                    pst.setString(3,e.getType());
                    pst.setString(4,e.getDescription());
                    pst.setString(5,e.getLocalitation());
                    pst.setInt(6,e.getId_societe());
                    pst.setString(7,e.getPicture());
                    pst.setInt(8,e.getViewed());
                    pst.executeUpdate();
                    
                } catch (SQLException ex) {
                    System.out.println("Erreur :" + ex.getMessage());
                }
            
        }
    
}
