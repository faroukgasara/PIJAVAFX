/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.service;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import pijavafx.reclamation.entity.Reclamation;
import pijavafx.reclamation.entity.Company;
/**
 *
 * @author ala
 */
public interface IReclamationService {
       public void ajouterReclamation(Reclamation p);  
    public void modifierReclamation(Reclamation p);
    public ObservableList<Reclamation> getAllReclamation();
    
      //  public ObservableList<Reclamation> getReclamationByEtatNonTraite();
        //    public ObservableList<Reclamation> getReclamationByEtatTraite();


    
            //public void  traiterReclamation(Reclamation p);
    
    public int getNbrReclamation();
    
    
}
