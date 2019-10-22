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

/**
 *
 * @author media
 */
@Entity
@Table(name = "UtilisateurModuleProfil")
public class UtilisateurModuleProfil extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateurModuleProfil;
    @JoinColumn(name = "idUtilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
    @JoinColumn(name = "idProfil")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profil profil;
    @JoinColumn(name = "idModule")
    @ManyToOne(fetch = FetchType.LAZY)
    private Modules module;

    public UtilisateurModuleProfil() {
    }

    public UtilisateurModuleProfil(Utilisateur utilisateur, Profil profil, Modules module) {
        this.utilisateur = utilisateur;
        this.profil = profil;
        this.module = module;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Long getIdUtilisateurModuleProfil() {
        return idUtilisateurModuleProfil;
    }

    public void setIdUtilisateurModuleProfil(Long idUtilisateurModuleProfil) {
        this.idUtilisateurModuleProfil = idUtilisateurModuleProfil;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }

    @Override
    public String getId() {
        return "" + this.idUtilisateurModuleProfil;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.idUtilisateurModuleProfil != null ? this.idUtilisateurModuleProfil.hashCode() : 0);
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
        final UtilisateurModuleProfil other = (UtilisateurModuleProfil) obj;
        if (this.idUtilisateurModuleProfil != other.idUtilisateurModuleProfil && (this.idUtilisateurModuleProfil == null || !this.idUtilisateurModuleProfil.equals(other.idUtilisateurModuleProfil))) {
            return false;
        }
        return true;
    }
}
