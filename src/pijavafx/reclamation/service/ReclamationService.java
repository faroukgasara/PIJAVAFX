/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.event.ActionEvent;
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
public class ReclamationService {
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
               String date = rs.getString("created_at");
                String status2 = rs.getString("status2");
               // System.out.println(status);
                String companyName =rs.getString("companyName");
               boolean status =rs.getBoolean("status");
                rec.add(new Reclamation(id, message, motif, gsm,date,companyName,status2));

            }
            System.out.println(rec.toString());
            return rec;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return null;
        }
    }

    public static boolean deleteReclamation(Reclamation r) {
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
            stmt.execute("DELETE FROM reclamation WHERE id ='" + r.getId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }

    }
    public void printPdf(Reclamation r){
        try {
            String fileName ="C:\\Users\\leowa\\OneDrive\\Documents\\javaPdf\\generer.pdf";
            Document document =new Document();
            PdfWriter.getInstance(document,new FileOutputStream(fileName) );
            document.open();
            PreparedStatement pst;
             ResultSet rs;
        try {
             
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
           String sql="select *  WHERE id ='" + r.getId() + "'";
          
          rs = stmt.executeQuery("select *   from reclamation WHERE id ='" + r.getId() + "'");
             PdfPTable table= new PdfPTable(7);
              PdfPCell c1= new PdfPCell(new Phrase("id"));
              table.addCell(c1);
                c1= new PdfPCell(new Phrase("motif"));
               table.addCell(c1);
                 c1= new PdfPCell(new Phrase("message"));
               table.addCell(c1);
                c1= new PdfPCell(new Phrase("gsm"));
               table.addCell(c1);
                c1= new PdfPCell(new Phrase("created at"));
               table.addCell(c1);
                c1= new PdfPCell(new Phrase("company name"));
               table.addCell(c1);
                c1= new PdfPCell(new Phrase("status"));
               table.addCell(c1);
               
             
               
          while (rs.next()){
              Paragraph para =new Paragraph(rs.getString("id")+rs.getString("motif")+rs.getString("message")+rs.getString("gsm")+rs.getString("companyName")+rs.getString("status2")+rs.getString("created_at"));
           table.setHeaderRows(1);
               table.addCell(new Phrase(rs.getString("id")));
               table.addCell(new Phrase(rs.getString("motif")));
               table.addCell(new Phrase(rs.getString("message")));
               table.addCell(new Phrase(rs.getString("gsm")));
               table.addCell(new Phrase(rs.getString("created_at")));
               table.addCell(new Phrase(rs.getString("companyName")));
               table.addCell(new Phrase(rs.getString("status2")));
              document.add(table);
              
          }
            document.close();
            
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            
        }
        } catch (Exception e) {
        }
    }
    
    public static boolean studyRec(Reclamation r) {
        PreparedStatement pst;
        try {
            conx = BDConnector.driverBD();
            stmt = conx.createStatement();
           String sql="UPDATE `reclamation` SET `status2`=?  WHERE id ='" + r.getId() + "'";
             pst = conx.prepareStatement(sql);
            
            pst.setString(1, "studied");
              pst.executeUpdate();
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
            String sql = "INSERT INTO reclamation (`message`, `motif`, `gsm`,`created_at`,`companyName`,`status2`)"
                    + "VALUES(?,?,?,?,?,?) ";
            pst = conx.prepareStatement(sql);
            pst.setString(1, e.getMessage());
            pst.setString(2, e.getMotif());
            pst.setString(3, e.getGsm());
             pst.setString(4, e.getCreatedAt());
              pst.setString(5, e.getCompanyName());
              pst.setString(6, e.getStatus2());
            
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
            
            String sql = "UPDATE `reclamation` SET `message`=?,`motif`=?,`gsm`=?,`created_at`=?,`companyName`=? WHERE `id`=?";
            pst = conx.prepareStatement(sql);
            pst.setString(1, e.getMessage());
            pst.setString(2, e.getMotif());
            pst.setString(3, e.getGsm());
             pst.setString(4, e.getCreatedAt());
              pst.setString(5, e.getCompanyName());
            pst.setInt(6, e.getId());
            System.out.println(e.getId());
            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("Erreur :" + ex.getMessage());
        }

    }
           
    public static int getNbrReclamationn() {
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

    
 
}    