/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "pannes")
public class pannes extends BaseEntity {

    @Id
    @Column(name = "ID_PANNE")
    private String idPanne;
    @Column(name = "LIBELLE_PANNE")
    private String libellePanne;
    
    public pannes(){
        
    }

    public pannes(String idPanne, String libellePanne) {
        this.idPanne = idPanne;
        this.libellePanne = libellePanne;
    }
    
    public String getIdPanne() {
        return idPanne;
    }

    public void setIdPanne(String idPanne) {
        this.idPanne = idPanne;
    }

    public String getLibellePanne() {
        return libellePanne;
    }

    public void setLibellePanne(String libellePanne) {
        this.libellePanne = libellePanne;
    }
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
