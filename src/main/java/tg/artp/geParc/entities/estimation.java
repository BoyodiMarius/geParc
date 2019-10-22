/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.persistence.Column;

/**
 *
 * @author BOYODI Wiyow Marius
 */
public class estimation {
    private String marques;
    private String immatriculation;
    private String dateExpirationAssurance;
    private String dateExpirationVisitetechnique;
    private  String kilometrageVidange;
    private  BigDecimal esimationCarburant;

    public estimation() {
    }

    public estimation(String marques, String immatriculation, String dateExpirationAssurance, String dateExpirationVisitetechnique, String kilometrageVidange, BigDecimal esimationCarburant) {
        this.marques = marques;
        this.immatriculation = immatriculation;
        this.dateExpirationAssurance = dateExpirationAssurance;
        this.dateExpirationVisitetechnique = dateExpirationVisitetechnique;
        this.kilometrageVidange = kilometrageVidange;
        this.esimationCarburant = esimationCarburant;
    }

    public String getMarques() {
        return marques;
    }

    public void setMarques(String marques) {
        this.marques = marques;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getDateExpirationAssurance() {
        return dateExpirationAssurance;
    }

    public void setDateExpirationAssurance(String dateExpirationAssurance) {
        this.dateExpirationAssurance = dateExpirationAssurance;
    }

    public String getDateExpirationVisitetechnique() {
        return dateExpirationVisitetechnique;
    }

    public void setDateExpirationVisitetechnique(String dateExpirationVisitetechnique) {
        this.dateExpirationVisitetechnique = dateExpirationVisitetechnique;
    }

    public String getKilometrageVidange() {
        return kilometrageVidange;
    }

    public void setKilometrageVidange(String kilometrageVidange) {
        this.kilometrageVidange = kilometrageVidange;
    }

    public BigDecimal getEsimationCarburant() {
        return esimationCarburant;
    }

    public void setEsimationCarburant(BigDecimal esimationCarburant) {
        this.esimationCarburant = esimationCarburant;
    }
    
    
}
