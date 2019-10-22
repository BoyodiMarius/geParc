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
 * @author SENA
 */
@Entity
@Table(name = "PROFILUSER",
        uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Profil", "utilisateur"})
})
public class ProfilUser extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserProfil;
    @JoinColumn(name = "utilisateur")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
    @JoinColumn(name = "profil")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profil profil;

    public ProfilUser() {
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
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

    public Long getIdUserProfil() {
        return idUserProfil;
    }

    public void setIdUserProfil(Long idUserProfil) {
        this.idUserProfil = idUserProfil;
    }
 
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLibelleForMembre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
