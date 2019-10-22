/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Elvis
 */
@Entity
public class Utilisateur extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nom;
    private String prenom;
    private String password;
    private String login;
    private String email;
    private String image;
    private String typeUtilisateur;
    private boolean activation = true;
    @OneToMany(mappedBy = "utilisateur")
    private List<UtilisateurModuleProfil> ListUtilisateurModuleProfil = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(Long idUser, String nom, String prenom, String password, String login, String email, String image, String typeUtilisateur) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.login = login;
        this.email = email;
        this.image = image;
        this.typeUtilisateur = typeUtilisateur;
    }

//    public Utilisateur(Long idUser, String nom, String prenom, String password, String login, String email, String typeUtilisateur, String image) {
//        this.idUser = idUser;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.password = password;
//        this.login = login;
//        this.email = email;
//        this.typeUtilisateur = typeUtilisateur;
//        this.image = image;
//    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id) {
        this.idUser = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    @Override
//    public String getId() {
//        return idUser+"";
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + idUser + ", designation=" + nom + ", motDePasse=" + password + ", login=" + login + '}';
    }

    @Override
    public String getId() {
        return idUser + "";
    }

    public List<UtilisateurModuleProfil> getListUtilisateurModuleProfil() {
        return ListUtilisateurModuleProfil;
    }

    public void setListUtilisateurModuleProfil(List<UtilisateurModuleProfil> ListUtilisateurModuleProfil) {
        this.ListUtilisateurModuleProfil = ListUtilisateurModuleProfil;
    }

}
