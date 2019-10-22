/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Embeddable
public class affectationChauffeurVehiculePK implements Serializable{
    
    @Column(name = "ID_VEHICULE")
    private String idVehicule;
    @Column(name = "ID_CHAUFFEUR")
    private String idChauffeur;

    public affectationChauffeurVehiculePK() {
    }

    public affectationChauffeurVehiculePK(String idVehicule, String idChauffeur) {
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(String idChauffeur) {
        this.idChauffeur = idChauffeur;
    }
    
    
}
