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
@Table(name = "marques")
public class marques extends BaseEntity {
    
    @Id
    @Column(name = "ID_MARQUE")
    private String idMarque;
    @Column(name = "LIBELLE_MARQUE")
    private String libelleMarque;
    
    public marques(){
        
    }

    public marques(String idMarque, String libelleMarque) {
        this.idMarque = idMarque;
        this.libelleMarque = libelleMarque;
    }

    public String getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getLibelleMarque() {
        return libelleMarque;
    }

    public void setLibelleMarque(String libelleMarque) {
        this.libelleMarque = libelleMarque;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
