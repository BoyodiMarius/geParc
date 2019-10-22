/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "modele")
public class modele extends BaseEntity {

    @Id
    @Column(name="ID_MODELE")
    private String idModele;
    @Column(name="DESIGNATION")
    private String designation;
    @Column(name="PARAMETRE_VIDANGE")
    private BigDecimal parametreVidange;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_TYPE_VEHICULE", name="ID_TYPE_VEHICULE")
    private typeVehicules idTypeVehicule;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_MARQUE", name="ID_MARQUE")
    private marques idMarque;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_TYPECARBURANT", name="ID_TYPECARBURANT")
    private typeCarburant idTypeCarburant;
    
    
    public modele(){
        
    }

    public modele(String idModele, String designation, String typecarburant, BigDecimal parametreVidange) {
        this.idModele = idModele;
        this.designation = designation;
        //this.typecarburant = typecarburant;
        this.parametreVidange = parametreVidange;
    }

    public modele(String idModele, String designation, BigDecimal parametreVidange, typeVehicules idTypeVehicule, marques idMarque, typeCarburant idTypeCarburant) {
        this.idModele = idModele;
        this.designation = designation;
        this.parametreVidange = parametreVidange;
        this.idTypeVehicule = idTypeVehicule;
        this.idMarque = idMarque;
        this.idTypeCarburant = idTypeCarburant;
    }

    
    

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getParametreVidange() {
        return parametreVidange;
    }

    public void setParametreVidange(BigDecimal parametreVidange) {
        this.parametreVidange = parametreVidange;
    }

    public typeVehicules getIdTypeVehicule() {
        return idTypeVehicule;
    }

    public void setIdTypeVehicule(typeVehicules idTypeVehicule) {
        this.idTypeVehicule = idTypeVehicule;
    }

    public marques getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(marques idMarque) {
        this.idMarque = idMarque;
    }

    public typeCarburant getIdTypeCarburant() {
        return idTypeCarburant;
    }

    public void setIdTypeCarburant(typeCarburant idTypeCarburant) {
        this.idTypeCarburant = idTypeCarburant;
    }
    
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
