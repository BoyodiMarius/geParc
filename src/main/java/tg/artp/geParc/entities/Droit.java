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

@Entity
public class Droit extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idDroit;
    @Column
    private String codeDroit;
    @Column
    private String libelleDroit;
    @JoinColumn(name = "module")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modules module;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Droit parent;
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST,
        CascadeType.MERGE, CascadeType.REFRESH})
    private List<Droit> children = new ArrayList<Droit>();
    
    public static final String CAISSE = "CAISSE";
    public static final String COMPTABILITE = "COMPTABILITE";

    public Droit() {
    }

    public Droit(String codeDroit, String libelleDroit, Droit parent) {
        this.codeDroit = codeDroit;
        this.libelleDroit = libelleDroit;
        this.parent = parent;
        parent.addChild(this);
    }

    /*
     * ajouter un sous-menu à un menu
     */
    private void addChild(Droit child) {
        if (child != null) {
            this.children.add(child);
        }
    }

    // Verifier si le Droit a des sous-Droits
    public boolean hasChildren() {
        if (getChildren().size() > 0) {
            return true;
        }
        return false;
    }

    // Verifier si le Droit a un parent
    public boolean hasParent() {
        if (this.getParent() != null) {
            return true;
        }
        return false;
    }

    public Long getIdDroit() {
        return idDroit;
    }

    public void setIdDroit(Long idDroit) {
        this.idDroit = idDroit;
    }

    public String getLibelleDroit() {
        return libelleDroit;
    }

    public void setLibelleDroit(String libelleDroit) {
        this.libelleDroit = libelleDroit;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }

    public String getCodeDroit() {
        return codeDroit;
    }

    public void setCodeDroit(String codeDroit) {
        this.codeDroit = codeDroit;
    }

    public Droit getParent() {
        return parent;
    }

    public void setParent(Droit parent) {
        this.parent = parent;
    }

    public List<Droit> getChildren() {
        return children;
    }

    public void setChildren(List<Droit> children) {
        this.children = children;
    }

    @Override
    public String getId() {
        return "" + this.idDroit;
    }

    @Override
    public String toString() {
        return this.libelleDroit;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.idDroit != null ? this.idDroit.hashCode() : 0);
        hash = 43 * hash + (this.codeDroit != null ? this.codeDroit.hashCode() : 0);
        hash = 43 * hash + (this.libelleDroit != null ? this.libelleDroit.hashCode() : 0);
        hash = 43 * hash + (this.module != null ? this.module.hashCode() : 0);
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
        final Droit other = (Droit) obj;
        if (this.idDroit != other.idDroit && (this.idDroit == null || !this.idDroit.equals(other.idDroit))) {
            return false;
        }
        if ((this.codeDroit == null) ? (other.codeDroit != null) : !this.codeDroit.equals(other.codeDroit)) {
            return false;
        }
        if ((this.libelleDroit == null) ? (other.libelleDroit != null) : !this.libelleDroit.equals(other.libelleDroit)) {
            return false;
        }
        if (this.module != other.module && (this.module == null || !this.module.equals(other.module))) {
            return false;
        }
        return true;
    }
 
    
    
}
