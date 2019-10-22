/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Poids extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoids;
    private int valeurPoids;
    @Column(name = "valeurLimite")
    private BigDecimal valeurLimite;
    @OneToMany(mappedBy = "poids")
    private List<UtilisateurModulePoids> ListUtilisateurModulePoids = new ArrayList<UtilisateurModulePoids>();

    public Poids() {
    }

    public Poids(int valeurPoids) {
        this.valeurPoids = valeurPoids;
    }

    public Long getIdPoids() {
        return idPoids;
    }

    public void setIdPoids(Long idPoids) {
        this.idPoids = idPoids;
    }

    public int getValeurPoids() {
        return valeurPoids;
    }

    public void setValeurPoids(int valeurPoids) {
        this.valeurPoids = valeurPoids;
    }

    public BigDecimal getValeurLimite() {
        return valeurLimite;
    }

    public void setValeurLimite(BigDecimal valeurLimite) {
        this.valeurLimite = valeurLimite;
    }

    public List<UtilisateurModulePoids> getListUtilisateurModulePoids() {
        return ListUtilisateurModulePoids;
    }

    public void setListUtilisateurModulePoids(List<UtilisateurModulePoids> ListUtilisateurModulePoids) {
        this.ListUtilisateurModulePoids = ListUtilisateurModulePoids;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.idPoids != null ? this.idPoids.hashCode() : 0);
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
        final Poids other = (Poids) obj;
        if (this.idPoids != other.idPoids && (this.idPoids == null || !this.idPoids.equals(other.idPoids))) {
            return false;
        }
        return true;
    }

    @Override
    public String getId() {
        return "" + this.idPoids;
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "" + valeurPoids;
    }
}
