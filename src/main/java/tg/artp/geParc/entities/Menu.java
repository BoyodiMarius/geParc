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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author media
 */
@Entity
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    @Column(nullable = false)
    private String libelleMenu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Menu parent;
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private List<Menu> children = new ArrayList<Menu>();
    @OneToMany(mappedBy = "menu")
    private List<ProfilMenu> ListProfilMenu = new ArrayList<ProfilMenu>();
    @OneToMany(mappedBy = "menu")
    private List<UtilisateurMenu> ListUtilisateurMenu = new ArrayList<UtilisateurMenu>();
    @OneToMany(mappedBy = "menu")
    private List<ModuleMenu> ListModuleMenu = new ArrayList<ModuleMenu>();

    public Menu() {
    }

    public Menu(String libelleMenu, Menu parent) {
        this.libelleMenu = libelleMenu;
        this.parent = parent;
        parent.addChild(this);
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getLibelleMenu() {
        return libelleMenu;
    }

    public void setLibelleMenu(String libelleMenu) {
        this.libelleMenu = libelleMenu;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
    /*
     * ajouter un sous-menu à un menu
     */

    private void addChild(Menu child) {
        if (child != null) {
            this.children.add(child);
        }
    }

    // Verifier si le menu a des sous-menus
    public boolean hasChildren() {
        if (getChildren().size() > 0) {
            return true;
        }
        return false;
    }

    // Verifier si le menu a un parent
    public boolean hasParent() {
        if (this.getParent() != null) {
            return true;
        }
        return false;
    }

    public List<ProfilMenu> getListProfilMenu() {
        return ListProfilMenu;
    }

    public void setListProfilMenu(List<ProfilMenu> ListProfilMenu) {
        this.ListProfilMenu = ListProfilMenu;
    }

    public List<UtilisateurMenu> getListUtilisateurMenu() {
        return ListUtilisateurMenu;
    }

    public void setListUtilisateurMenu(List<UtilisateurMenu> ListUtilisateurMenu) {
        this.ListUtilisateurMenu = ListUtilisateurMenu;
    }

    public List<ModuleMenu> getListModuleMenu() {
        return ListModuleMenu;
    }

    public void setListModuleMenu(List<ModuleMenu> ListModuleMenu) {
        this.ListModuleMenu = ListModuleMenu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.idMenu != null ? this.idMenu.hashCode() : 0);
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
        final Menu other = (Menu) obj;
        if (this.idMenu != other.idMenu && (this.idMenu == null || !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.libelleMenu;
    }

    @Override
    public String getId() {
        return "" + this.idMenu;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
