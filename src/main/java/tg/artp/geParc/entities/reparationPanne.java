/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "reparationPanne")
public class reparationPanne extends BaseEntity {
    
    @EmbeddedId
    private reparationsPK idReparationPanne;
    @Column(name = "DETAIL_REPARATION_PANNE")
    private String detailReparationPanne;
    
    public reparationPanne(){
        
    }

    public reparationPanne(reparationsPK idReparationPanne, String detailReparationPanne) {
        this.idReparationPanne = idReparationPanne;
        this.detailReparationPanne = detailReparationPanne;
    }

    public reparationsPK getIdReparationPanne() {
        return idReparationPanne;
    }

    public void setIdReparationPanne(reparationsPK idReparationPanne) {
        this.idReparationPanne = idReparationPanne;
    }

    public String getDetailReparationPanne() {
        return detailReparationPanne;
    }

    public void setDetailReparationPanne(String detailReparationPanne) {
        this.detailReparationPanne = detailReparationPanne;
    }
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
