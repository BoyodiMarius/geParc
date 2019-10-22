/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report.files;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.el.functions.Dates;

/**
 *
 * @author BOYODI Wiyow Marius
 */
public class EtatAssurance {

    private String immatriculation, dateLivraison, dateExpiration, joursRestants, etatAssurance, titre;

    public EtatAssurance() {
    }

    public EtatAssurance(String immatriculation, String dateLivraison, String dateExpiration, String joursRestants, String etatAssurance, String titre) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.immatriculation = immatriculation;
        this.dateLivraison = dateLivraison;
        this.dateExpiration = dateExpiration;
        this.etatAssurance = etatAssurance;
        this.titre = titre;
        Date debut, fin;
        int nbrejours = 0;
        try {
//            debut = format.parse(dateLivraison);
            fin = format.parse(dateExpiration);
            nbrejours = Dates.daysBetween(new Date(), fin);
        } catch (ParseException ex) {
            Logger.getLogger(EtatAssurance.class.getName()).log(Level.SEVERE, null, ex);
        }
       int n = -1;
        if(nbrejours >= 0){
            this.joursRestants = nbrejours + "";
        } else {
            this.joursRestants = "Expiré depuis "+ nbrejours * n+" jour(s)";
        }
        
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getJoursRestants() {
        return joursRestants;
    }

    public void setJoursRestants(String joursRestants) {
        this.joursRestants = joursRestants;
    }

    public String getEtatAssurance() {
        return etatAssurance;
    }

    public void setEtatAssurance(String etatAssurance) {
        this.etatAssurance = etatAssurance;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    
}
