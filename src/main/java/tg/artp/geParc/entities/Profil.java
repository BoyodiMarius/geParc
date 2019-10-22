/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author media
 */
@Entity
public class Profil extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idProfil;
    @NotNull
    @Column(name = "Intitule", unique = true)
    private String libelleProfil;
    private String description;
    @OneToMany(mappedBy = "profil", cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private List<ProfilMenu> ListProfilMenu = new ArrayList<ProfilMenu>();
    @OneToMany(mappedBy = "profil")
    private List<UtilisateurModuleProfil> ListUserModuleProfil = new ArrayList<UtilisateurModuleProfil>();
    @OneToMany(mappedBy = "profil")
    private List<DroitProfil> ListDroitProfil = new ArrayList<DroitProfil>();

    public Profil() {
    }

    public Profil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public Long getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Long idProfil) {
        this.idProfil = idProfil;
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProfilMenu> getListProfilMenu() {
        return ListProfilMenu;
    }

    public void setListProfilMenu(List<ProfilMenu> ListProfilMenu) {
        this.ListProfilMenu = ListProfilMenu;
    }

    public List<UtilisateurModuleProfil> getListUserModuleProfil() {
        return ListUserModuleProfil;
    }

    public void setListUserModuleProfil(List<UtilisateurModuleProfil> ListUserModuleProfil) {
        this.ListUserModuleProfil = ListUserModuleProfil;
    }

    public List<DroitProfil> getListDroitProfil() {
        return ListDroitProfil;
    }

    public void setListDroitProfil(List<DroitProfil> ListDroitProfil) {
        this.ListDroitProfil = ListDroitProfil;
    }

    @Override
    public String getId() {
        return "" + this.idProfil;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idProfil != null ? this.idProfil.hashCode() : 0);
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
        final Profil other = (Profil) obj;
        if (this.idProfil != other.idProfil && (this.idProfil == null || !this.idProfil.equals(other.idProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return libelleProfil;
    }
}
