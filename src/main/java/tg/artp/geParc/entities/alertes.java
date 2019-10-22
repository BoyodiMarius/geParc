/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "alertes")
public class alertes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALERTE")
    private Long idAlerte;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "DATE_SIGNALISATION")
    @Temporal(TemporalType.DATE)
    private Date dateSignalisation;
    @Column(name= "HEURE_SIGNALISATION")
    private String heureSignalisation;
    @Column(name= "CONSULTER")
    private boolean consulter;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE")
    private vehicules idVehicule;
    
    public alertes(){
        
    }

    public alertes(Long idAlerte, String message, Date dateSignalisation, String heureSignalisation, boolean consulter, vehicules idVehicule) {
        this.idAlerte = idAlerte;
        this.message = message;
        this.dateSignalisation = dateSignalisation;
        this.heureSignalisation = heureSignalisation;
        this.consulter = consulter;
        this.idVehicule = idVehicule;
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateSignalisation() {
        return dateSignalisation;
    }

    public void setDateSignalisation(Date dateSignalisation) {
        this.dateSignalisation = dateSignalisation;
    }

    public String getHeureSignalisation() {
        return heureSignalisation;
    }

    public void setHeureSignalisation(String heureSignalisation) {
        this.heureSignalisation = heureSignalisation;
    }

    public boolean isConsulter() {
        return consulter;
    }

    public void setConsulter(boolean consulter) {
        this.consulter = consulter;
    }

    public vehicules getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(vehicules idVehicule) {
        this.idVehicule = idVehicule;
    }
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
