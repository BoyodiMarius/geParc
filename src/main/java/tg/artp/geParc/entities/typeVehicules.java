/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "typeVehicules")
public class typeVehicules extends BaseEntity {

    @Id
    @NotNull
    @Column(name = "ID_TYPE_VEHICULE")
    private String idTypeVehicule;
    @NotNull
    @Column(name = "LIBELLE_TYPE_VEHICULE")
    private String libelleTypeVehicule;
    public typeVehicules() {
        
    }

    public typeVehicules(String idTypeVehicule, String libelleTypeVehicule) {
        this.idTypeVehicule = idTypeVehicule;
        this.libelleTypeVehicule = libelleTypeVehicule;
    }

    public String getIdTypeVehicule() {
        return idTypeVehicule;
    }

    public void setIdTypeVehicule(String idTypeVehicule) {
        this.idTypeVehicule = idTypeVehicule;
    }

    public String getLibelleTypeVehicule() {
        return libelleTypeVehicule;
    }

    public void setLibelleTypeVehicule(String libelleTypeVehicule) {
        this.libelleTypeVehicule = libelleTypeVehicule;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
