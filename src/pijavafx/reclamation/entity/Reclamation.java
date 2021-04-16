/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ala
 */
@Entity
@Table(name = "reclamation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamation.findAll", query = "SELECT r FROM Reclamation r"),
    @NamedQuery(name = "Reclamation.findById", query = "SELECT r FROM Reclamation r WHERE r.id = :id"),
    @NamedQuery(name = "Reclamation.findByMessage", query = "SELECT r FROM Reclamation r WHERE r.message = :message"),
    @NamedQuery(name = "Reclamation.findByMotif", query = "SELECT r FROM Reclamation r WHERE r.motif = :motif"),
    @NamedQuery(name = "Reclamation.findByGsm", query = "SELECT r FROM Reclamation r WHERE r.gsm = :gsm"),
    @NamedQuery(name = "Reclamation.findByCreatedAt", query = "SELECT r FROM Reclamation r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Reclamation.findByStatus", query = "SELECT r FROM Reclamation r WHERE r.status = :status")})
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @Column(name = "motif")
    private String motif;
    @Basic(optional = false)
    @Column(name = "gsm")
    private String gsm;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private Company companyId;

    public Reclamation() {
    }

    public Reclamation(Integer id) {
        this.id = id;
    }

    public Reclamation(Integer id, String message, String motif, String gsm) {
        this.id = id;
        this.message = message;
        this.motif = motif;
        this.gsm = gsm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
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
        if (!(object instanceof Reclamation)) {
            return false;
        }
        Reclamation other = (Reclamation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pijavafx.reclamation.entity.Reclamation[ id=" + id + " ]";
    }
    
}
