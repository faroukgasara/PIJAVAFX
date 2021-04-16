/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.formation;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fedi
 */
@Entity
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Description;
    private String date_at;
    private String Title;
    private String Localisation;
    private int id_soc;
    private String Imagef;

    public Formation(int id, String Description, String date_at, String Title, String Localisation, int id_soc, String Imagef) {
        this.id = id;
        this.Description = Description;
        this.date_at = date_at;
        this.Title = Title;
        this.Localisation = Localisation;
        this.id_soc = id_soc;
        this.Imagef = Imagef;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate_at() {
        return date_at;
    }

    public void setDate_at(String date_at) {
        this.date_at = date_at;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
    }

    public int getId_soc() {
        return id_soc;
    }

    public void setId_soc(int id_soc) {
        this.id_soc = id_soc;
    }

    public String getImagef() {
        return Imagef;
    }

    public void setImagef(String Imagef) {
        this.Imagef = Imagef;
    }
     

   

    
    @Override
    public String toString() {
        return "pijavafx.formation.Formation[ id=" + id + " ]";
    }
    
}
