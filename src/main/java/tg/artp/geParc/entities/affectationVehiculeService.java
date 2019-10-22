/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.Date;
import javax.persistence.Entity;
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
@Table(name = "affectationVehiculeService")
public class affectationVehiculeService extends BaseEntity {
    
    @Id
    private String idAffectationVehiculeService;
    @Temporal(TemporalType.DATE)
    private Date dateDebutAffectation;
    @Temporal(TemporalType.DATE)
    private Date dateFinAffectation;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE")
    private vehicules idVehicule;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_SERVICE", name="ID_SERVICE")
    private services idService;

    public affectationVehiculeService() {
    }
    
    public affectationVehiculeService(String idAffectationVehiculeService, Date dateDebutAffectation, Date dateFinAffectation, vehicules idVehicule, services idService) {
        this.idAffectationVehiculeService = idAffectationVehiculeService;
        this.dateDebutAffectation = dateDebutAffectation;
        this.dateFinAffectation = dateFinAffectation;
        this.idVehicule = idVehicule;
        this.idService = idService;
    }

    public String getIdAffectationVehiculeService() {
        return idAffectationVehiculeService;
    }

    public void setIdAffectationVehiculeService(String idAffectationVehiculeService) {
        this.idAffectationVehiculeService = idAffectationVehiculeService;
    }

    public Date getDateDebutAffectation() {
        return dateDebutAffectation;
    }

    public void setDateDebutAffectation(Date dateDebutAffectation) {
        this.dateDebutAffectation = dateDebutAffectation;
    }

    public Date getDateFinAffectation() {
        return dateFinAffectation;
    }

    public void setDateFinAffectation(Date dateFinAffectation) {
        this.dateFinAffectation = dateFinAffectation;
    }

    public vehicules getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(vehicules idVehicule) {
        this.idVehicule = idVehicule;
    }

    public services getIdService() {
        return idService;
    }

    public void setIdService(services idService) {
        this.idService = idService;
    }
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
