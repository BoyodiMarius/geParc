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
@Table(name = "UtilisateurModulePoids",
        uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idUtilisateur", "idModule", "idPoids"})
})
public class UtilisateurModulePoids extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateurModulePoids;
    @JoinColumn(name = "idModule")
    @ManyToOne(fetch = FetchType.LAZY)
    private Modules module;
    @JoinColumn(name = "idPoids")
    @ManyToOne(fetch = FetchType.LAZY)
    private Poids poids;
    @JoinColumn(name = "idUtilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public UtilisateurModulePoids() {
    }

    public UtilisateurModulePoids(Modules module, Utilisateur utilisateur, Poids poids) {
        this.module = module;
        this.utilisateur = utilisateur;
        this.poids = poids;
    }

    public Long getIdUtilisateurModulePoids() {
        return idUtilisateurModulePoids;
    }

    public void setIdUtilisateurModulePoids(Long idUtilisateurModulePoids) {
        this.idUtilisateurModulePoids = idUtilisateurModulePoids;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Poids getPoids() {
        return poids;
    }

    public void setPoids(Poids poids) {
        this.poids = poids;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idUtilisateurModulePoids != null ? this.idUtilisateurModulePoids.hashCode() : 0);
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
        final UtilisateurModulePoids other = (UtilisateurModulePoids) obj;
        if (this.idUtilisateurModulePoids != other.idUtilisateurModulePoids && (this.idUtilisateurModulePoids == null || !this.idUtilisateurModulePoids.equals(other.idUtilisateurModulePoids))) {
            return false;
        }
        return true;
    }

    @Override
    public String getId() {
        return "" + this.idUtilisateurModulePoids;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
