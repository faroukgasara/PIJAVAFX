/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author farou
 */
@Entity
@Table(name = "participant_e")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipantE.findAll", query = "SELECT p FROM ParticipantE p"),
    @NamedQuery(name = "ParticipantE.findById", query = "SELECT p FROM ParticipantE p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipantE.findByMail", query = "SELECT p FROM ParticipantE p WHERE p.mail = :mail"),
    @NamedQuery(name = "ParticipantE.findByNom", query = "SELECT p FROM ParticipantE p WHERE p.nom = :nom"),
    @NamedQuery(name = "ParticipantE.findByAge", query = "SELECT p FROM ParticipantE p WHERE p.age = :age")})
public class ParticipantE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;

    public ParticipantE() {
    }

    public ParticipantE(Integer id) {
        this.id = id;
    }

    public ParticipantE(Integer id, String mail, String nom, int age) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipantE)) {
            return false;
        }
        ParticipantE other = (ParticipantE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pijavafx.events.entites.ParticipantE[ id=" + id + " ]";
    }
    
}
