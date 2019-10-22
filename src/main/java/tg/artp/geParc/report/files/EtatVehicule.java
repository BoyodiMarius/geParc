/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report.files;

/**
 *
 * @author BOYODI Wiyow Marius
 */
public class EtatVehicule {

    private String typeVehicule, marque, modele, numChassis, immatriculation, couleur, kilometrageActuel, kilometrageAmortissement, etatVehicule;

    public EtatVehicule(String typeVehicule, String marque, String modele, String numChassis, String immatriculation, String couleur, String kilometrageActuel, String kilometrageAmortissement, String etatVehicule) {
        this.typeVehicule = typeVehicule;
        this.marque = marque;
        this.modele = modele;
        this.numChassis = numChassis;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.kilometrageActuel = kilometrageActuel;
        this.kilometrageAmortissement = kilometrageAmortissement;
        this.etatVehicule = etatVehicule;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNumChassis() {
        return numChassis;
    }

    public void setNumChassis(String numChassis) {
        this.numChassis = numChassis;
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

    public String getKilometrageActuel() {
        return kilometrageActuel;
    }

    public void setKilometrageActuel(String kilometrageActuel) {
        this.kilometrageActuel = kilometrageActuel;
    }

    public String getKilometrageAmortissement() {
        return kilometrageAmortissement;
    }

    public void setKilometrageAmortissement(String kilometrageAmortissement) {
        this.kilometrageAmortissement = kilometrageAmortissement;
    }

    public String getEtatVehicule() {
        return etatVehicule;
    }

    public void setEtatVehicule(String etatVehicule) {
        this.etatVehicule = etatVehicule;
    }

}
