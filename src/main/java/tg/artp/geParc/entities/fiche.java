/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

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
@Table(name = "fiche")
public class fiche extends BaseEntity {
    
    @Id
    @NotNull
    @Column(name = "ID_FICHE")
    private String idFiche;
    @NotNull
    @Column(name = "DATE_DELIVRER")
    @Temporal(TemporalType.DATE)
    private Date dateDelivrer;
    @NotNull
    @Column(name = "DATE_EXPIRATION")
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    @NotNull
    @Column(name = "ETAT_FICHE")
    private Boolean etatFiche;
    @ManyToOne
     @JoinColumn(referencedColumnName ="ID_TYPE_FICHE", name="ID_TYPE_FICHE")
    private typeFiche idTypeFiche;
    @ManyToOne
     @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE")
    private vehicules idVehicule;
    
    public fiche (){
        
    }

    public fiche(String idFiche, Date dateDelivrer, Date dateExpiration, Boolean etatFiche, typeFiche idTypeFiche, vehicules idVehicule) {
        this.idFiche = idFiche;
        this.dateDelivrer = dateDelivrer;
        this.dateExpiration = dateExpiration;
        this.etatFiche = etatFiche;
        this.idTypeFiche = idTypeFiche;
        this.idVehicule = idVehicule;
    }

    public String getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(String idFiche) {
        this.idFiche = idFiche;
    }

    public Date getDateDelivrer() {
        return dateDelivrer;
    }

    public void setDateDelivrer(Date dateDelivrer) {
        this.dateDelivrer = dateDelivrer;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Boolean getEtatFiche() {
        return etatFiche;
    }

    public void setEtatFiche(Boolean etatFiche) {
        this.etatFiche = etatFiche;
    }

    public typeFiche getIdTypeFiche() {
        return idTypeFiche;
    }

    public void setIdTypeFiche(typeFiche idTypeFiche) {
        this.idTypeFiche = idTypeFiche;
    }

    public vehicules getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(vehicules idVehicule) {
        this.idVehicule = idVehicule;
    }
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
