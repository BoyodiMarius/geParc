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
public class affectationPK implements Serializable {
    
    @Column(name = "ID_MISSION")
    private String idMission;
    @Column(name = "ID_VEHICULE")
    private String idVehicule;
    @Column(name = "ID_CHAUFFEUR")
    private String idChauffeur;
    
    public affectationPK(){
        
    }

    public affectationPK(String idMission, String idVehicule, String idChauffeur) {
        this.idMission = idMission;
        this.idVehicule = idVehicule;
        this.idChauffeur = idChauffeur;
    }

    public String getIdMission() {
        return idMission;
    }

    public void setIdMission(String idMission) {
        this.idMission = idMission;
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
