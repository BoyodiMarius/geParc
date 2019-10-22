/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "chargerCarburant")
public class chargerCarburant extends BaseEntity {

    @Id
    @Column(name = "ID_CHARGER_CARBURANT")
    private String idChargerCarburant;
    @Column(name = "DATE_CHARGER_CARBURANT")
    @Temporal(TemporalType.DATE)
    private Date dateChargerCarburant;
    @Column(name="QUANTITE_CHARGER")
    private BigDecimal quantiteCharger;
    @Column(name="KILOMETRAGE_AVANT_CHARGEMENT")
    private BigDecimal kilometrageAvantChargement;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VEHICULE", name="ID_VEHICULE")
    private vehicules idVehicule;
    
    public chargerCarburant(){
        
    }

    public chargerCarburant(String idChargerCarburant, Date dateChargerCarburant, BigDecimal quantiteCharger, BigDecimal kilometrageAvantChargement, vehicules idVehicule) {
        this.idChargerCarburant = idChargerCarburant;
        this.dateChargerCarburant = dateChargerCarburant;
        this.quantiteCharger = quantiteCharger;
        this.kilometrageAvantChargement = kilometrageAvantChargement;
        this.idVehicule = idVehicule;
    }

    

    public String getIdChargerCarburant() {
        return idChargerCarburant;
    }

    public void setIdChargerCarburant(String idChargerCarburant) {
        this.idChargerCarburant = idChargerCarburant;
    }

    public Date getDateChargerCarburant() {
        return dateChargerCarburant;
    }

    public void setDateChargerCarburant(Date dateChargerCarburant) {
        this.dateChargerCarburant = dateChargerCarburant;
    }

    public BigDecimal getQuantiteCharger() {
        return quantiteCharger;
    }

    public void setQuantiteCharger(BigDecimal quantiteCharger) {
        this.quantiteCharger = quantiteCharger;
    }

    public BigDecimal getKilometrageAvantChargement() {
        return kilometrageAvantChargement;
    }

    public void setKilometrageAvantChargement(BigDecimal kilometrageAvantChargement) {
        this.kilometrageAvantChargement = kilometrageAvantChargement;
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
