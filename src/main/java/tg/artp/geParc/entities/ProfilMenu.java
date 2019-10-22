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
@Table(name = "ProfilMenu",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"idMenu", "idProfil"})
        })
public class ProfilMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfilMenu;
    @JoinColumn(name = "idMenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @JoinColumn(name = "idProfil")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profil profil;

    public ProfilMenu() {
    }

    public ProfilMenu(Menu menu, Profil profil) {
        this.menu = menu;
        this.profil = profil;
    }

    public Long getIdProfilMenu() {
        return idProfilMenu;
    }

    public void setIdProfilMenu(Long idProfilMenu) {
        this.idProfilMenu = idProfilMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Override
    public String getId() {
        return "" + this.idProfilMenu;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idProfilMenu != null ? this.idProfilMenu.hashCode() : 0);
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
        final ProfilMenu other = (ProfilMenu) obj;
        if (this.idProfilMenu != other.idProfilMenu && (this.idProfilMenu == null || !this.idProfilMenu.equals(other.idProfilMenu))) {
            return false;
        }
        return true;
    }
}
