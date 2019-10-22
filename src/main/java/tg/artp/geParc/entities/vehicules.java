/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "vehicules")
public class vehicules extends BaseEntity {
    
    @Id
    @Column(name = "ID_VEHICULE")
    private String idVehicule;
    @Column(name= "NUMERO_CHASSIS")
    private String numeroChassis;
    @Column(name="IMMATRICULATION")
    private  String immatriculation;
    @Column(name="COULEUR")
    private  String couleur;
    @Column(name="IMAGE_AVANT_VEHICULE")
    private  String imageAvantVehicule;
    @Column(name="IMAGE_ARRIERE_VEHICULE")
    private  String imageArriereVehicule;
    @Column(name="IMAGE_GAUCHE_VEHICULE")
    private  String imageGaucheVehicule;
    @Column(name="IMAGE_Droit_VEHICULE")
    private  String imageDroitVehicule;
    @Column(name="IMAGE_INTERIEUR_VEHICULE")
    private  String imageInterieurVehicule;
    @Column(name="PARAMETRE_AMORTISSEMENT")
    private  BigDecimal parametreAmortissement;
    @Column(name="KILOMETRAGE_ACTUEL")
    private  BigDecimal kilometrageActuel;
    @Column(name="KILOMETRAGE_VIDANGE")
    private  BigDecimal kilometrageVidange;
    @Column(name="ETAT_ACTUEL")
    private  boolean etatActuel;
    @Column(name="ETAT_VEHICULE")
    private  boolean etatVehicule;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_MODELE", name="ID_MODELE")
    private modele idModele;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idVehicule")
    private List<fiche> listeFicheV = new ArrayList();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idVehicule")
    private List<alertes> listeAlertes = new ArrayList();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idVehicule")
    private List<chargerCarburant> listeChargerCarburant = new ArrayList();
    
    public vehicules(){
        
    }

    public vehicules(String idVehicule, String numeroChassis, String immatriculation, String couleur, String imageAvantVehicule, String imageArriereVehicule, String imageGaucheVehicule, String imageDroitVehicule, String imageInterieurVehicule, BigDecimal parametreAmortissement, BigDecimal kilometrageActuel, BigDecimal kilometrageVidange, boolean etatActuel, boolean etatVehicule, modele idModele) {
        this.idVehicule = idVehicule;
        this.numeroChassis = numeroChassis;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.imageAvantVehicule = imageAvantVehicule;
        this.imageArriereVehicule = imageArriereVehicule;
        this.imageGaucheVehicule = imageGaucheVehicule;
        this.imageDroitVehicule = imageDroitVehicule;
        this.imageInterieurVehicule = imageInterieurVehicule;
        this.parametreAmortissement = parametreAmortissement;
        this.kilometrageActuel = kilometrageActuel;
        this.kilometrageVidange = kilometrageVidange;
        this.etatActuel = etatActuel;
        this.etatVehicule = etatVehicule;
        this.idModele = idModele;
    }

//    public vehicules(String idVehicule, String numeroChassis, String designation, String immatriculation, String couleur, String imageVehicule, BigDecimal parametreVidange, BigDecimal parametreAmortissement, BigDecimal kilometrageActuel, boolean etatActuel, boolean etatVehicule, typeVehicules idTypeVehicule, marques idMarque) {
//        this.idVehicule = idVehicule;
//        this.numeroChassis = numeroChassis;
//        this.designation = designation;
//        this.immatriculation = immatriculation;
//        this.couleur = couleur;
//        this.imageVehicule = imageVehicule;
//        this.parametreVidange = parametreVidange;
//        this.parametreAmortissement = parametreAmortissement;
//        this.kilometrageActuel = kilometrageActuel;
//        this.etatActuel = etatActuel;
//        this.etatVehicule = etatVehicule;
//        this.idTypeVehicule = idTypeVehicule;
//        this.idMarque = idMarque;
//    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getNumeroChassis() {
        return numeroChassis;
    }

    public void setNumeroChassis(String numeroChassis) {
        this.numeroChassis = numeroChassis;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public BigDecimal getParametreAmortissement() {
        return parametreAmortissement;
    }

    public void setParametreAmortissement(BigDecimal parametreAmortissement) {
        this.parametreAmortissement = parametreAmortissement;
    }

    public BigDecimal getKilometrageActuel() {
        return kilometrageActuel;
    }

    public void setKilometrageActuel(BigDecimal kilometrageActuel) {
        this.kilometrageActuel = kilometrageActuel;
    }

    public boolean isEtatActuel() {
        return etatActuel;
    }

    public void setEtatActuel(boolean etatActuel) {
        this.etatActuel = etatActuel;
    }

    public boolean isEtatVehicule() {
        return etatVehicule;
    }

    public void setEtatVehicule(boolean etatVehicule) {
        this.etatVehicule = etatVehicule;
    }


//    public marques getIdMarque() {
//        return idMarque;
//    }
//
//    public void setIdMarque(marques idMarque) {
//        this.idMarque = idMarque;
//    }

    public modele getIdModele() {
        return idModele;
    }

    public void setIdModele(modele idModele) {
        this.idModele = idModele;
    }   
    

    public List<fiche> getListeFicheV() {
        return listeFicheV;
    }

    public void setListeFicheV(List<fiche> listeFicheV) {
        this.listeFicheV = listeFicheV;
    }

    public List<alertes> getListeAlertes() {
        return listeAlertes;
    }

    public void setListeAlertes(List<alertes> listeAlertes) {
        this.listeAlertes = listeAlertes;
    }

    public List<chargerCarburant> getListeChargerCarburant() {
        return listeChargerCarburant;
    }

    public void setListeChargerCarburant(List<chargerCarburant> listeChargerCarburant) {
        this.listeChargerCarburant = listeChargerCarburant;
    }

    public String getImageAvantVehicule() {
        return imageAvantVehicule;
    }

    public void setImageAvantVehicule(String imageAvantVehicule) {
        this.imageAvantVehicule = imageAvantVehicule;
    }

    public String getImageArriereVehicule() {
        return imageArriereVehicule;
    }

    public void setImageArriereVehicule(String imageArriereVehicule) {
        this.imageArriereVehicule = imageArriereVehicule;
    }

    public String getImageGaucheVehicule() {
        return imageGaucheVehicule;
    }

    public void setImageGaucheVehicule(String imageGaucheVehicule) {
        this.imageGaucheVehicule = imageGaucheVehicule;
    }

    public String getImageDroitVehicule() {
        return imageDroitVehicule;
    }

    public void setImageDroitVehicule(String imageDroitVehicule) {
        this.imageDroitVehicule = imageDroitVehicule;
    }

    public String getImageInterieurVehicule() {
        return imageInterieurVehicule;
    }

    public void setImageInterieurVehicule(String imageInterieurVehicule) {
        this.imageInterieurVehicule = imageInterieurVehicule;
    }

    public BigDecimal getKilometrageVidange() {
        return kilometrageVidange;
    }

    public void setKilometrageVidange(BigDecimal kilometrageVidange) {
        this.kilometrageVidange = kilometrageVidange;
    }
    
    
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
