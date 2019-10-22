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
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "reparations")
public class reparations extends BaseEntity {

    @Id
    @Column(name = "ID_REPARATION")
    private String idReparation;
    @Column(name = "DATE_DEBUT_REPARATION")
    @Temporal(TemporalType.DATE)
    private Date dateDebutReparation;
    @Column(name = "DATE_FIN_REPARATION")
    @Temporal(TemporalType.DATE)
    private Date dateFinReparation;
    @Column(name = "PRIX_REPARATION")
    private BigDecimal prixReparation;
    @Column(name = "IMAGE_FACTURE")
    private String imageFacture;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_GARAGE", name="ID_GARAGE")
    private garage idGarage;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE")
    private vehicules idVehicule;
    
    public reparations(){
        
    }

    public reparations(String idReparation, Date dateDebutReparation, Date dateFinReparation, BigDecimal prixReparation, String imageFacture, garage idGarage, vehicules idVehicule) {
        this.idReparation = idReparation;
        this.dateDebutReparation = dateDebutReparation;
        this.dateFinReparation = dateFinReparation;
        this.prixReparation = prixReparation;
        this.imageFacture = imageFacture;
        this.idGarage = idGarage;
        this.idVehicule = idVehicule;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public garage getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(garage idGarage) {
        this.idGarage = idGarage;
    }

    public vehicules getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(vehicules idVehicule) {
        this.idVehicule = idVehicule;
    }

    public Date getDateDebutReparation() {
        return dateDebutReparation;
    }

    public void setDateDebutReparation(Date dateDebutReparation) {
        this.dateDebutReparation = dateDebutReparation;
    }

    public Date getDateFinReparation() {
        return dateFinReparation;
    }

    public void setDateFinReparation(Date dateFinReparation) {
        this.dateFinReparation = dateFinReparation;
    }

    public BigDecimal getPrixReparation() {
        return prixReparation;
    }

    public void setPrixReparation(BigDecimal prixReparation) {
        this.prixReparation = prixReparation;
    }

    public String getImageFacture() {
        return imageFacture;
    }

    public void setImageFacture(String imageFacture) {
        this.imageFacture = imageFacture;
    }
    
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
