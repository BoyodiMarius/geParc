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
import javax.validation.constraints.NotNull;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "chauffeurs")
public class chauffeurs extends BaseEntity {

    @Id
    @Column(name = "ID_CHAUFFEUR")
    private String idChauffeur;
    @Column(name = "NOM_CHAUFFEUR")
    private String nomChauffeur;
    @Column(name = "PRENOM_CHAUFFEUR")
    private String prenomChauffeur;
    @Column(name = "SEXE_CHAUFFEUR")
    private char SexeCHAUFFEUR;
    @Column(name = "NATIONALITE")
    private String nationaliteChauffeur;
    @Column(name = "CONTACT_CHAUFFEUR")
    private String contactChauffeur;
    @Column(name = "STATUT")
    private String statut;
    @Column(name = "MONTANT_TOTAL_MISSION")
    private BigDecimal motantTotalMission;
    
    public chauffeurs(){
        
    }

    public chauffeurs(String idChauffeur, String nomChauffeur, String prenomChauffeur, char SexeCHAUFFEUR, String nationaliteChauffeur, String contactChauffeur, String statut, BigDecimal motantTotalMission) {
        this.idChauffeur = idChauffeur;
        this.nomChauffeur = nomChauffeur;
        this.prenomChauffeur = prenomChauffeur;
        this.SexeCHAUFFEUR = SexeCHAUFFEUR;
        this.nationaliteChauffeur = nationaliteChauffeur;
        this.contactChauffeur = contactChauffeur;
        this.statut = statut;
        this.motantTotalMission = motantTotalMission;
    }

    public String getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(String idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }

    public String getPrenomChauffeur() {
        return prenomChauffeur;
    }

    public void setPrenomChauffeur(String prenomChauffeur) {
        this.prenomChauffeur = prenomChauffeur;
    }

    public char getSexeCHAUFFEUR() {
        return SexeCHAUFFEUR;
    }

    public void setSexeCHAUFFEUR(char SexeCHAUFFEUR) {
        this.SexeCHAUFFEUR = SexeCHAUFFEUR;
    }

    public String getNationaliteChauffeur() {
        return nationaliteChauffeur;
    }

    public void setNationaliteChauffeur(String nationaliteChauffeur) {
        this.nationaliteChauffeur = nationaliteChauffeur;
    }

    public String getContactChauffeur() {
        return contactChauffeur;
    }

    public void setContactChauffeur(String contactChauffeur) {
        this.contactChauffeur = contactChauffeur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public BigDecimal getMotantTotalMission() {
        return motantTotalMission;
    }

    public void setMotantTotalMission(BigDecimal motantTotalMission) {
        this.motantTotalMission = motantTotalMission;
    }
    
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
