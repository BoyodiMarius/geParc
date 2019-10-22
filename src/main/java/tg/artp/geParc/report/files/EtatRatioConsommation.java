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
public class EtatRatioConsommation {

    private String vehicule, kilometrageActuel, consommationTotale, ratioConsommation;

    public EtatRatioConsommation() {
    }

    public EtatRatioConsommation(Object[] obj) {
        this.vehicule = obj[0] == null ? "" : obj[0].toString();
//        this.vehicule = obj[0] == null && obj[1] == null && obj[2] == null ? "" : obj[0].toString() + " - " + obj[1].toString() + " - " + obj[2].toString();
        this.kilometrageActuel = obj[1] == null ? "0" : obj[1].toString();
//        this.kilometrageActuel = obj[3] == null ? "" : obj[3].toString();
        this.consommationTotale = obj[4] == null ? "0" : obj[4].toString();
//        this.consommationTotale = obj[6] == null ? "" : obj[6].toString();
        this.ratioConsommation = obj[5] == null ? "0" : obj[5].toString();
//        this.ratioConsommation = obj[7] == null ? "" : obj[7].toString();
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getKilometrageActuel() {
        return kilometrageActuel;
    }

    public void setKilometrageActuel(String kilometrageActuel) {
        this.kilometrageActuel = kilometrageActuel;
    }

    public String getConsommationTotale() {
        return consommationTotale;
    }

    public void setConsommationTotale(String consommationTotale) {
        this.consommationTotale = consommationTotale;
    }

    public String getRatioConsommation() {
        return ratioConsommation;
    }

    public void setRatioConsommation(String ratioConsommation) {
        this.ratioConsommation = ratioConsommation;
    }

}
