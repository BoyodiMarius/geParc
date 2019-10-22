/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "affectations")
public class affectations extends BaseEntity {

    @EmbeddedId
    private affectationPK idAffectation;
    @Column(name = "KILOMETRAGE_DEPART")
    private BigDecimal kilometrageDepart;
    @Column(name = "KILOMETRAGE_ARRIVEE")
    private BigDecimal kilometrageArrive;
    @Column(name = "CARBURANT_CONSOMME")
    private BigDecimal carburantConsomme;
    @Column(name = "MONTANT_PERCU")
    private BigDecimal MontantPercu;
    @ManyToOne
     @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE", updatable = false, insertable = false)
    private vehicules idVehicule;
    @ManyToOne
     @JoinColumn(referencedColumnName ="ID_MISSION", name="ID_MISSION", updatable = false, insertable = false)
    private missions idMission;
    @ManyToOne
     @JoinColumn(referencedColumnName ="ID_CHAUFFEUR", name="ID_CHAUFFEUR", updatable = false, insertable = false)
    private chauffeurs idChauffeur;
    
    public affectations(){
        
    }


//    public affectations(affectationPK idAffectation, BigDecimal kilometrageDepart, BigDecimal kilometrageArrive) {
//        this.idAffectation = idAffectation;
//        this.kilometrageDepart = kilometrageDepart;
//        this.kilometrageArrive = kilometrageArrive;
//    }

    public affectations(affectationPK idAffectation, BigDecimal kilometrageDepart, BigDecimal kilometrageArrive, BigDecimal carburantConsomme, BigDecimal MontantPercu, vehicules idVehicule, missions idMission, chauffeurs idChauffeur) {
        this.idAffectation = idAffectation;
        this.kilometrageDepart = kilometrageDepart;
        this.kilometrageArrive = kilometrageArrive;
        this.carburantConsomme = carburantConsomme;
        this.MontantPercu = MontantPercu;
        this.idVehicule = idVehicule;
        this.idMission = idMission;
        this.idChauffeur = idChauffeur;
    }

    public BigDecimal getMontantPercu() {
        return MontantPercu;
    }

    public void setMontantPercu(BigDecimal MontantPercu) {
        this.MontantPercu = MontantPercu;
    }

    

    
    public affectationPK getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(affectationPK idAffectation) {
        this.idAffectation = idAffectation;
    }

    public BigDecimal getKilometrageDepart() {
        return kilometrageDepart;
    }

    public void setKilometrageDepart(BigDecimal kilometrageDepart) {
        this.kilometrageDepart = kilometrageDepart;
    }

    public BigDecimal getKilometrageArrive() {
        return kilometrageArrive;
    }

    public void setKilometrageArrive(BigDecimal kilometrageArrive) {
        this.kilometrageArrive = kilometrageArrive;
    }

    public BigDecimal getCarburantConsomme() {
        return carburantConsomme;
    }

    public void setCarburantConsomme(BigDecimal carburantConsomme) {
        this.carburantConsomme = carburantConsomme;
    }

    public vehicules getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(vehicules idVehicule) {
        this.idVehicule = idVehicule;
    }

    public missions getIdMission() {
        return idMission;
    }

    public void setIdMission(missions idMission) {
        this.idMission = idMission;
    }

    public chauffeurs getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(chauffeurs idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
