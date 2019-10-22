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
@Table(name = "typeCarburant")
public class typeCarburant extends BaseEntity {
   
    @Id
    @Column(name = "ID_TYPECARBURANT")
    private String idTypeCarburant;
    @Column(name = "LIBELLE_TYPE_CARBURANT")
    private String libelleTypeCarburant;
    
    public typeCarburant(){
        
    }

    public typeCarburant(String idTypeCarburant, String libelleTypeCarburant) {
        this.idTypeCarburant = idTypeCarburant;
        this.libelleTypeCarburant = libelleTypeCarburant;
    }

    public String getIdTypeCarburant() {
        return idTypeCarburant;
    }

    public void setIdTypeCarburant(String idTypeCarburant) {
        this.idTypeCarburant = idTypeCarburant;
    }

    public String getLibelleTypeCarburant() {
        return libelleTypeCarburant;
    }

    public void setLibelleTypeCarburant(String libelleTypeCarburant) {
        this.libelleTypeCarburant = libelleTypeCarburant;
    }
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
