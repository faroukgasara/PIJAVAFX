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
    private int id ;
    private String date_at;
    private String title ; 
    private String type;
    private String description;
    private String localitation;
    private int id_societe;
    private String picture;
    private int viewed;

    public EventEntity() {
    }
    

    public EventEntity(int id, String date_at, String title, String type, String description, String localitation, int id_societe, String picture, int viewed) {
        this.id = id;
        this.date_at = date_at;
        this.title = title;
        this.type = type;
        this.description = description;
        this.localitation = localitation;
        this.id_societe = id_societe;
        this.picture = picture;
        this.viewed = viewed;
    }

    public int getId() {
        return id;
    }

    public String getDate_at() {
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

    public String getPicture() {
        return picture;
    }

    public int getViewed() {
        return viewed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_at(String date_at) {
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

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return "EventEntity{" + "id=" + id + ", date_at=" + date_at + ", title=" + title + ", type=" + type + ", description=" + description + ", localitation=" + localitation + ", id_societe=" + id_societe + ", picture=" + picture + ", viewed=" + viewed + '}';
    }
    
    
    
    
    
    
    
}
