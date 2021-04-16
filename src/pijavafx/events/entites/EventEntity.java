/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.entites;

import java.sql.Date;

/**
 *
 * @author farou
 */
public class EventEntity {
    int id ;
    Date date_at;
    String title ; 
    String type;
    String description;
    String localitation;
    int id_societe;
    int viewed;

    public EventEntity() {
    }
    

    public EventEntity(int id, Date date_at, String title, String type, String description, String localitation, int id_societe, int viewed) {
        this.id = id;
        this.date_at = date_at;
        this.title = title;
        this.type = type;
        this.description = description;
        this.localitation = localitation;
        this.id_societe = id_societe;
        this.viewed = viewed;
    }

    public int getId() {
        return id;
    }

    public Date getDate_at() {
        return date_at;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalitation() {
        return localitation;
    }

    public int getId_societe() {
        return id_societe;
    }



    public int getViewed() {
        return viewed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_at(Date date_at) {
        this.date_at = date_at;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalitation(String localitation) {
        this.localitation = localitation;
    }

    public void setId_societe(int id_societe) {
        this.id_societe = id_societe;
    }


    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return "EventEntity{" + "id=" + id + ", date_at=" + date_at + ", title=" + title + ", type=" + type + ", description=" + description + ", localitation=" + localitation + ", id_societe=" + id_societe + " viewed=" + viewed + '}';
    }
    
    
    
    
    
    
    
}
