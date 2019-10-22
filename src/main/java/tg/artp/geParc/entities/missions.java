/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "missions")
public class missions extends BaseEntity {
    
    @Id
    @Column(name = "ID_MISSION")
    private String idMission;
    @Column(name = "OBJET_MISSION")
    private String objetMission;
    @Column(name = "DATE_DEPART")
    @Temporal(TemporalType.DATE)
    private Date dateDepart;
    @Column(name = "DATE_RETOUR")
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    @ManyToOne
     @JoinColumn(referencedColumnName ="ID_TYPE_MISSION", name="ID_TYPE_MISSION")
    private typeMission idTypeMission;
    
    public missions(){
        
    }

    public missions(String idMission, String objetMission, Date dateDepart, Date dateRetour, typeMission idTypeMission) {
        this.idMission = idMission;
        this.objetMission = objetMission;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.idTypeMission = idTypeMission;
    }
    
    public String getIdMission() {
        return idMission;
    }

    public void setIdMission(String idMission) {
        this.idMission = idMission;
    }

    public String getObjetMission() {
        return objetMission;
    }

    public void setObjetMission(String objetMission) {
        this.objetMission = objetMission;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public typeMission getIdTypeMission() {
        return idTypeMission;
    }

    public void setIdTypeMission(typeMission idTypeMission) {
        this.idTypeMission = idTypeMission;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
