/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.oceanstudy.entity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "os_fish_status", catalog = "oceanstudy", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OsFishStatus.findAll", query = "SELECT o FROM OsFishStatus o")
    , @NamedQuery(name = "OsFishStatus.findById", query = "SELECT o FROM OsFishStatus o WHERE o.id = :id")
    , @NamedQuery(name = "OsFishStatus.findByStatus", query = "SELECT o FROM OsFishStatus o WHERE o.status = :status")
    , @NamedQuery(name = "OsFishStatus.findByNote", query = "SELECT o FROM OsFishStatus o WHERE o.note = :note")})
public class OsFishStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "note", length = 50)
    private String note;
    @OneToMany(mappedBy = "status")
    private Collection<OsFish> osFishCollection;

    public OsFishStatus() {
    }
    
    public OsFishStatus(Integer id, String status) {
        super();
        this.id = id;
        this.status = status;
    }



    public OsFishStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlTransient
    public Collection<OsFish> getOsFishCollection() {
        return osFishCollection;
    }

    public void setOsFishCollection(Collection<OsFish> osFishCollection) {
        this.osFishCollection = osFishCollection;
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
        if (!(object instanceof OsFishStatus)) {
            return false;
        }
        OsFishStatus other = (OsFishStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocean.OsFishStatus[ id=" + id + " ]";
    }
    
}
