/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "garages")
public class garage extends BaseEntity {
    @Id
    @NotNull
    @Column(name = "ID_GARAGE")
    private String idGarage;
    @NotNull
    @Column(name = "LIBELLE_GARAGE")
    private String libelleGarage;
    @Size(max = 15 , min = 8)
    @Column(name = "CONTACT_GARAGE")
    private String contactGarage;
    @Column(name = "EMAIL_GARAGE")
    private String emailGarage;
    
    public garage(){
        
    }

    public garage(String idGarage, String libelleGarage, String contactGarage, String emailGarage) {
        this.idGarage = idGarage;
        this.libelleGarage = libelleGarage;
        this.contactGarage = contactGarage;
        this.emailGarage = emailGarage;
    }

    public String getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(String idGarage) {
        this.idGarage = idGarage;
    }

    public String getLibelleGarage() {
        return libelleGarage;
    }

    public void setLibelleGarage(String libelleGarage) {
        this.libelleGarage = libelleGarage;
    }

    public String getContactGarage() {
        return contactGarage;
    }

    public void setContactGarage(String contactGarage) {
        this.contactGarage = contactGarage;
    }

    public String getEmailGarage() {
        return emailGarage;
    }

    public void setEmailGarage(String emailGarage) {
        this.emailGarage = emailGarage;
    }
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
