/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "ModuleMenu",
        uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idMenu", "idModule"})
})
public class ModuleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModuleMenu;
    @JoinColumn(name = "idMenu")
    @ManyToOne(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private Menu menu;
    @JoinColumn(name = "idModule")
    @ManyToOne(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private Modules module;

    public ModuleMenu() {
    }

    public ModuleMenu(Menu menu, Modules module) {
        this.menu = menu;
        this.module = module;
    }

    public Long getIdModuleMenu() {
        return idModuleMenu;
    }

    public void setIdModuleMenu(Long idModuleMenu) {
        this.idModuleMenu = idModuleMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.idModuleMenu != null ? this.idModuleMenu.hashCode() : 0);
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
        final ModuleMenu other = (ModuleMenu) obj;
        if (this.idModuleMenu != other.idModuleMenu && (this.idModuleMenu == null || !this.idModuleMenu.equals(other.idModuleMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String getId() {
        return "" + this.idModuleMenu;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
