/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author media
 */
@Entity
@Table(name = "UtilisateurDroit",
        uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idDroit", "idUtilisateur"})
})
public class UtilisateurDroit extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateurDroit;
    @JoinColumn(name = "idDroit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Droit droit;
    @JoinColumn(name = "idUtilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public UtilisateurDroit() {
    }

    public UtilisateurDroit(Droit droit, Utilisateur utilisateur) {
        this.droit = droit;
        this.utilisateur = utilisateur;
    }

    public Long getIdUtilisateurDroit() {
        return idUtilisateurDroit;
    }

    public void setIdUtilisateurDroit(Long idUtilisateurDroit) {
        this.idUtilisateurDroit = idUtilisateurDroit;
    }

    public Droit getDroit() {
        return droit;
    }

    public void setDroit(Droit droit) {
        this.droit = droit;
    }

   
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String getId() {
        return "" + this.idUtilisateurDroit;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.droit != null ? this.droit.hashCode() : 0);
        hash = 97 * hash + (this.utilisateur != null ? this.utilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UtilisateurDroit other = (UtilisateurDroit) obj;
        if (this.droit != other.droit && (this.droit == null || !this.droit.equals(other.droit))) {
            return false;
        }
        if (this.utilisateur != other.utilisateur && (this.utilisateur == null || !this.utilisateur.equals(other.utilisateur))) {
            return false;
        }
        return true;
    }
}
