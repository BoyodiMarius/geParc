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
@Table(name = "UtilisateurMenu",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"idMenu", "idUtilisateur"})
        })
public class UtilisateurMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateurMenu;
    @JoinColumn(name = "idMenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @JoinColumn(name = "idUtilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public UtilisateurMenu() {
    }

    public UtilisateurMenu(Menu menu, Utilisateur utilisateur) {
        this.menu = menu;
        this.utilisateur = utilisateur;
    }

    public Long getIdUtilisateurMenu() {
        return idUtilisateurMenu;
    }

    public void setIdUtilisateurMenu(Long idUtilisateurMenu) {
        this.idUtilisateurMenu = idUtilisateurMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String getId() {
        return "" + this.idUtilisateurMenu;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.menu != null ? this.menu.hashCode() : 0);
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
        final UtilisateurMenu other = (UtilisateurMenu) obj;
        if (this.menu != other.menu && (this.menu == null || !this.menu.equals(other.menu))) {
            return false;
        }
        if (this.utilisateur != other.utilisateur && (this.utilisateur == null || !this.utilisateur.equals(other.utilisateur))) {
            return false;
        }
        return true;
    }
}
