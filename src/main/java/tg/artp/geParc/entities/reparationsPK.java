/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Embeddable
public class reparationsPK implements Serializable {
    
   
    @Column(name = "ID_PANNE")
    private String idPanne;
   @Column(name = "ID_REPARATION")
    private String idReparation;
    
    public reparationsPK(){
        
    }

    public reparationsPK(String idPanne, String idReparation) {
        this.idPanne = idPanne;
        this.idReparation = idReparation;
    }

    public String getIdPanne() {
        return idPanne;
    }

    public void setIdPanne(String idPanne) {
        this.idPanne = idPanne;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    
    
    
}
