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
@Table(name = "services")
public class services extends BaseEntity  {
    
    @Id
    @Column(name = "ID_SERVICE")
    private String idService;
    @Column(name = "LIBELLE_SERVICE")
    private String libelleService;

    public services(){
        
    }

    public services(String idService, String libelleService) {
        this.idService = idService;
        this.libelleService = libelleService;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getLibelleService() {
        return libelleService;
    }

    public void setLibelleService(String libelleService) {
        this.libelleService = libelleService;
    }
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
