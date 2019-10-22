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
 * @author ocdi caisse
 */
@Entity
@Table(name = "DroitProfil",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"droit", "profil"})
        })
public class DroitProfil extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDroitProfil;
    @JoinColumn(name = "droit")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Droit droit;
    @JoinColumn(name = "profil")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profil profil;

    public DroitProfil() {
    }

    public DroitProfil(Droit droit, Profil profil) {
        this.droit = droit;
        this.profil = profil;
    }

    public Droit getDroit() {
        return droit;
    }

    public void setDroit(Droit droit) {
        this.droit = droit;
    }

    public Long getIdDroitProfil() {
        return idDroitProfil;
    }

    public void setIdDroitProfil(Long idDroitProfil) {
        this.idDroitProfil = idDroitProfil;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Override
    public String getId() {

        return "" + idDroitProfil;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.idDroitProfil != null ? this.idDroitProfil.hashCode() : 0);
        hash = 37 * hash + (this.droit != null ? this.droit.hashCode() : 0);
        hash = 37 * hash + (this.profil != null ? this.profil.hashCode() : 0);
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
        final DroitProfil other = (DroitProfil) obj;
        if (this.idDroitProfil != other.idDroitProfil && (this.idDroitProfil == null || !this.idDroitProfil.equals(other.idDroitProfil))) {
            return false;
        }
        if (this.droit != other.droit && (this.droit == null || !this.droit.equals(other.droit))) {
            return false;
        }
        if (this.profil != other.profil && (this.profil == null || !this.profil.equals(other.profil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DroitRole{" + "idDroitProfil=" + idDroitProfil + ", droit=" + droit + ", profil=" + profil + '}';
    }

}
