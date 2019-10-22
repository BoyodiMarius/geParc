/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.Date;
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
@Table(name = "affectationChauffeurVehicule")
public class affectationChauffeurVehicule extends BaseEntity {
    
    @Id
    private String idAffectationChauffeurVehicule;
    @Temporal(TemporalType.DATE)
    private Date dateDebutAffectation;
    @Temporal(TemporalType.DATE)
    private Date dateFinAffectation;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE")
    private vehicules idVehicule;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_CHAUFFEUR", name="ID_CHAUFFEUR")
    private chauffeurs idChauffeur;
    
    
     
    public affectationChauffeurVehicule(){
         
    }

    public affectationChauffeurVehicule(String idAffectationChauffeurVehicule, Date dateDebutAffectation, Date dateFinAffectation, vehicules idVehicule, chauffeurs idChauffeur) {
        this.idAffectationChauffeurVehicule = idAffectationChauffeurVehicule;
        this.dateDebutAffectation = dateDebutAffectation;
        this.dateFinAffectation = dateFinAffectation;
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
    }

    
    
    public String getIdAffectationChauffeurVehicule() {
        return idAffectationChauffeurVehicule;
    }

    public void setIdAffectationChauffeurVehicule(String idAffectationChauffeurVehicule) {
        this.idAffectationChauffeurVehicule = idAffectationChauffeurVehicule;
    }

    public vehicules getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(vehicules idVehicule) {
        this.idVehicule = idVehicule;
    }

    public chauffeurs getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(chauffeurs idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public Date getDateDebutAffectation() {
        return dateDebutAffectation;
    }

    public void setDateDebutAffectation(Date dateDebutAffectation) {
        this.dateDebutAffectation = dateDebutAffectation;
    }

    public Date getDateFinAffectation() {
        return dateFinAffectation;
    }

    public void setDateFinAffectation(Date dateFinAffectation) {
        this.dateFinAffectation = dateFinAffectation;
    }
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}