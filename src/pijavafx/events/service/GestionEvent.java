/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.service;

import java.sql.Connection;
import java.sql.Date;
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
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pijavafx.events.entites.ParticipantE;

/**
 *
 * @author farou
 */
public class GestionEvent {

    static Connection conx = null;
    static ResultSet rs = null;
    static ResultSet rs1 = null;
    static Statement stmt = null;
    static PreparedStatement req = null;

    public static ObservableList<EventEntity> AfficheToutEvent() {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM evenement");

            ObservableList<EventEntity> event = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date_at");
                String title = rs.getString("title");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String localitation = rs.getString("localitation");
                int id_societe = rs.getInt("id_societe");
                int viewed = rs.getInt("viewed");
                event.add(new EventEntity(id, date, title, type, description, localitation, id_societe, viewed));

            }

            return event;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return null;
        }
    }

    public void addEvent(EventEntity e) {
        conx = BDConnector.driverBD();
        PreparedStatement pst;

        try {
            String sql = "INSERT INTO evenement (`date_at`, `title`, `type`, `description`, `localitation`, `id_societe`, `viewed`)"
                    + "VALUES(?,?,?,?,?,?,?) ";
            pst = conx.prepareStatement(sql);
            pst.setDate(1, e.getDate_at());
            pst.setString(2, e.getTitle());
            pst.setString(3, e.getType());
            pst.setString(4, e.getDescription());
            pst.setString(5, e.getLocalitation());
            pst.setInt(6, e.getId_societe());
            pst.setInt(7, e.getViewed());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

    }

    public void updateEvent(EventEntity e) {
        try {
            System.out.println(e.toString());
            conx = BDConnector.driverBD();
            PreparedStatement pst;
            //String sql = "UPDATE evenement SET date_at="+date+",type="+e.getType()+",description="+e.getDescription()+",localitation="+e.getLocalitation()+",picture="+e.getPicture() +" WHERE id = "+e.getId()+"  ";
            String sql = "UPDATE `evenement` SET `date_at`=?,`title`=?,`type`=?,`description`=?,`localitation`=? WHERE `id`=?";
            pst = conx.prepareStatement(sql);
            pst.setDate(1, e.getDate_at());
            pst.setString(2, e.getTitle());
            pst.setString(3, e.getType());
            pst.setString(4, e.getDescription());
            pst.setString(5, e.getLocalitation());
            pst.setInt(6, e.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestionEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean deleteEvent(EventEntity e) {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            stmt.execute("DELETE FROM evenement WHERE id ='" + e.getId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }

    }

    public static ObservableList<ParticipantE> AfficheParticipant(int id) {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `participation_e` WHERE `id_evenement` = " + id + " ");
            ObservableList<ParticipantE> participant = FXCollections.observableArrayList();
            int[] tab = new int[5];
            int i = 0;
            while (rs.next()) {
                int idd = rs.getInt("id_participant");
                tab[i] = rs.getInt("id_participant");
                i = i + 1;
            }
            for (int j : tab) {
                System.out.println(j);
            }
            System.out.println(Arrays.toString(tab).replace("[", "(").replace("]", ")"));
            rs = stmt.executeQuery("SELECT * FROM `participant_e` WHERE `id` IN " + Arrays.toString(tab).replace("[", "(").replace("]", ")") + "");
            while (rs.next()) {
                int iddd = rs.getInt("id");
                int age = rs.getInt("age");
                String nom = rs.getString("nom");
                String mail = rs.getString("mail");
                participant.add(new ParticipantE(iddd, mail, nom, age));

            }

            return participant;

        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return null;
        }
    }

    public static boolean deletePar(ParticipantE e) {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            stmt.execute("DELETE FROM participant_e WHERE id ='" + e.getId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());

            return false;
        }

    }

    public void addParticipant(ParticipantE e, int id) {
        conx = BDConnector.driverBD();
        PreparedStatement pst;

        try {
            String sql = "INSERT INTO participant_e (`mail`, `nom`, `age`)"
                    + "VALUES(?,?,?) ";
            pst = conx.prepareStatement(sql);
            pst.setString(1, e.getMail());
            pst.setString(2, e.getNom());
            pst.setInt(3, e.getAge());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        try {
            rs = stmt.executeQuery("SELECT max(id) FROM `participant_e` ");
            while (rs.next()) {
                int idp = rs.getInt(1);
                System.out.println(idp);
                
                String sql = "INSERT INTO `participation_e`(`id_evenement`, `id_participant`)"
                        + "VALUES(?,?) ";
                pst = conx.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setInt(2, idp);
                pst.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
