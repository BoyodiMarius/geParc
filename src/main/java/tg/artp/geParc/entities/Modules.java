/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author media
 */
@Entity
public class Modules extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModule;
    private String libelleModule;
    @OneToMany(mappedBy = "module")
    private List<UtilisateurModuleProfil> ListUserModuleProfil = new ArrayList<UtilisateurModuleProfil>();
    @OneToMany(mappedBy = "module")
    private List<UtilisateurModulePoids> ListUtilisateurModulePoids = new ArrayList<UtilisateurModulePoids>();
    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private List<ModuleMenu> ListModuleMenu = new ArrayList<ModuleMenu>();
    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private List<Droit> ListDroit = new ArrayList<Droit>();

    public Modules() {
    }

    public Modules(String libelleModule) {
        this.libelleModule = libelleModule;
    }

    public Long getIdModule() {
        return idModule;
    }

    public void setIdModule(Long idModule) {
        this.idModule = idModule;
    }

    public String getLibelleModule() {
        return libelleModule;
    }

    public void setLibelleModule(String libelleModule) {
        this.libelleModule = libelleModule;
    }

    public List<UtilisateurModuleProfil> getListUserModuleProfil() {
        return ListUserModuleProfil;
    }

    public void setListUserModuleProfil(List<UtilisateurModuleProfil> ListUserModuleProfil) {
        this.ListUserModuleProfil = ListUserModuleProfil;
    }

    public List<UtilisateurModulePoids> getListUtilisateurModulePoids() {
        return ListUtilisateurModulePoids;
    }

    public void setListUtilisateurModulePoids(List<UtilisateurModulePoids> ListUtilisateurModulePoids) {
        this.ListUtilisateurModulePoids = ListUtilisateurModulePoids;
    }

    public List<ModuleMenu> getListModuleMenu() {
        return ListModuleMenu;
    }

    public void setListModuleMenu(List<ModuleMenu> ListModuleMenu) {
        this.ListModuleMenu = ListModuleMenu;
    }

    public List<Droit> getListDroit() {
        return ListDroit;
    }

    public void setListDroit(List<Droit> ListDroit) {
        this.ListDroit = ListDroit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.idModule != null ? this.idModule.hashCode() : 0);
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
        final Modules other = (Modules) obj;
        if (this.idModule != other.idModule && (this.idModule == null || !this.idModule.equals(other.idModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String getId() {
        return "" + this.idModule;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return libelleModule;
    }
}
