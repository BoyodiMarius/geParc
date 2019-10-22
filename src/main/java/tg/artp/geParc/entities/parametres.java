/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "parametres")
public class parametres extends BaseEntity {
    @Id
    @Column(name = "ID_PARAMETRE")
    private String idParametre;
    @Column(name = "LIBELLE_PARAMETRE")
    private String libelleParametre;
    @Column(name = "VALEUR")
    private BigDecimal valeur;
    
    public parametres(){
        
    }

    public parametres(String idParametre, String libelleParametre, BigDecimal valeur) {
        this.idParametre = idParametre;
        this.libelleParametre = libelleParametre;
        this.valeur = valeur;
    }

    public String getIdParametre() {
        return idParametre;
    }

    public void setIdParametre(String idParametre) {
        this.idParametre = idParametre;
    }

    public String getLibelleParametre() {
        return libelleParametre;
    }

    public void setLibelleParametre(String libelleParametre) {
        this.libelleParametre = libelleParametre;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
