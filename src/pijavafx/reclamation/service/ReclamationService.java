/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import pijavafx.events.utils.BDConnector;
import pijavafx.reclamation.entity.Reclamation;
import pijavafx.reclamation.entity.Company;
/**
 *
 * @author ala
 */
public class ReclamationService implements IReclamationService{
        ObservableList<Reclamation>obList = FXCollections.observableArrayList();
              
 

      static Connection conx = null;
    static ResultSet rs = null;
    static ResultSet rs1 = null;
    static Statement stmt = null;
    static PreparedStatement req = null;
    
    private LineChart<String,Date>lineChart;

    public ReclamationService() {
       conx = BDConnector.driverBD();
    }
        public static ObservableList<Reclamation> AfficheToutRec() {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM reclamation");

            ObservableList<Reclamation> rec = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String message = rs.getString("message");
                String motif = rs.getString("motif");
                String gsm = rs.getString("gsm");
                java.sql.Date date = rs.getDate("created_at");
                String status = rs.getString("status");
                rec.add(new Reclamation(id, message, motif, gsm));

            }
            System.out.println(rec.toString());
            return rec;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return null;
        }
    }

    public static boolean deleteReclamation(int r) {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            stmt.execute("DELETE FROM reclamation WHERE id ='" + r + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }

    }

    public void ajouterReclamation(Reclamation e) {
        conx = BDConnector.driverBD();
        PreparedStatement pst;

        try {
            String sql = "INSERT INTO reclamation (`message`, `motif`, `gsm`)"
                    + "VALUES(?,?,?) ";
            pst = conx.prepareStatement(sql);
            pst.setString(1, e.getMessage());
            pst.setString(2, e.getMotif());
            pst.setString(3, e.getGsm());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

    }
    
        public void modifierReclamation(Reclamation e) {
        try {
            System.out.println(e.toString());
            conx = BDConnector.driverBD();
            PreparedStatement pst;
            
            String sql = "UPDATE `reclamation` SET `message`=?,`motif`=?,`gsm`=? WHERE `id`=?";
            pst = conx.prepareStatement(sql);
            pst.setString(1, e.getMessage());
            pst.setString(2, e.getMotif());
            pst.setString(3, e.getGsm());
            pst.setInt(4, e.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("Erreur :" + ex.getMessage());
        }

    }
            @Override
    public int getNbrReclamation() {
        String sql="SELECT COUNT(*) FROM reclamation";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= conx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }

    @Override
    public ObservableList<Reclamation> getAllReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}    