/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurModulePoids;
import tg.artp.geParc.entities.UtilisateurModuleProfil;
import tg.artp.geParc.services.DroitProfilServiceBeanLocal;
import tg.artp.geParc.services.ModuleServiceBeanLocal;
import tg.artp.geParc.services.UtilisateurModuleProfilServiceBeanLocal;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;

/**
 *
 * @author LENOVO
 */
@ManagedBean(name = "utilisateurProfilManagedBean")
@ViewScoped
public class UtilisateurProfilManagedBean implements Serializable {

    @EJB
    private UtilisateurServiceBeanLocal utilisateurService;
    @EJB
    private ModuleServiceBeanLocal moduleService;
//    @EJB
//    private AgenceServiceBeanLocal agenceService;
//    @EJB
//    private UtilisateurModulePoidsServiceBeanLocal userModulePoidsService;
    @EJB
    private DroitProfilServiceBeanLocal droitProfilService;
    @EJB
    private UtilisateurModuleProfilServiceBeanLocal userModuleProfilService;

    private Utilisateur user;
    private Profil profilSelected;
    private UtilisateurModuleProfil userModuleProfil;
//    private List<Agence> listAgence;
    private List<Profil> listeProfil;
    private List<Modules> listeModulesByProfil;
    private List<Utilisateur> listeUtilisateur;
    private List<UtilisateurModuleProfil> listeModuleProfil;
    private String msg;

    /**
     * Creates a new instance of UtilisateurProfilManagedBean
     */
    public UtilisateurProfilManagedBean() {

        user = new Utilisateur();
        profilSelected = null;
//        listAgence = new ArrayList<>();
        listeProfil = new ArrayList<>();
        listeUtilisateur = new ArrayList<>();
        listeModuleProfil = new ArrayList<>();
        listeModulesByProfil = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
//            listAgence = agenceService.selectionnerTout();
            listeProfil = droitProfilService.listProfilToUse();
            listeUtilisateur = utilisateurService.selectionnerTout();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void effacer() {
        user = new Utilisateur();
        listeModuleProfil = new ArrayList<>();
    }

    public List<UtilisateurModuleProfil> createListModuleProfil() {
        //Recuperation de la liste des modules lié au profil
        if (user != null) {
            listeModuleProfil = null; //AMEN
//            listeModuleProfil = user.getListUtilisateurModuleProfil();
            if (listeModuleProfil.isEmpty()) {
                List<Modules> listeModule = moduleService.selectionnerTout();
                for (Modules m : listeModule) {
                    UtilisateurModuleProfil ump = new UtilisateurModuleProfil();
                    ump.setModule(m);
                    listeModuleProfil.add(ump);
                }
            }
        }

        return listeModuleProfil;
    }

    public List<UtilisateurModuleProfil> listeMooduleProfilByUser() {
        //Recuperation de la liste des modules lié au profil
        if (user != null) { //AMEN
            if (!user.getListUtilisateurModuleProfil().isEmpty()) {
                listeModuleProfil = user.getListUtilisateurModuleProfil();
                try {
                    profilSelected = listeModuleProfil.get(0).getProfil() == null ? null : listeModuleProfil.get(0).getProfil();
                    if (profilSelected != null) {
                        selectionModuleByProfil(profilSelected);
                    }
                } catch (Exception e) {
                }
            }
        }

        return listeModuleProfil;
    }

    public void handleUserSelect() {
        listeMooduleProfilByUser();
        //createListModuleProfil();
    }

    public void handleUserSelectProfil() {
        selectionModuleByProfil(profilSelected);
        if (user != null) {
            listeModuleProfil = new ArrayList<>();
            for (Modules md : listeModulesByProfil) {
                UtilisateurModuleProfil ump = new UtilisateurModuleProfil();
                ump.setModule(md);
                ump.setProfil(profilSelected);
                ump.setUtilisateur(user);
                listeModuleProfil.add(ump);
            }
        }
    }

    public void selectionModuleByProfil(Profil prof) {
        if (prof != null) {
            listeModulesByProfil = moduleService.listeModuleByProfil(prof);
            System.err.println("listeModulesByProfil " + listeModulesByProfil.size());
        }
    }

    public void confirmation() {
        try {
            if (user.getLogin() != null) {
                Ajax.oncomplete("PF('confirmUser').show()");
            } else {
                msg = "Veuillez sélèctionner un utilisateur";
                Ajax.oncomplete("PF('echecUser').show()");
            }
        } catch (Exception e) {

            msg = "Veuillez sélèctionner un utilisateur";
            Ajax.oncomplete("PF('echecUser').show()");
        }

    }

    public void save() {
        try {
//            user.setActive(true);
            utilisateurService.modifier(user);
            for (UtilisateurModuleProfil ump : listeModuleProfil) {
                ump.setUtilisateur(user);
                if (ump.getIdUtilisateurModuleProfil() != null) {
                    userModuleProfilService.modifier(ump);
                } else {
                    userModuleProfilService.ajouter(ump);
                    saveUMP(ump);
                }
            }
            Ajax.oncomplete("PF('succesUser').show()");
        } catch (Exception e) {

            msg = "Une erreur à été rencontré pendant l'enregistrement des données."
                    + " vérifier si l'utilisateur n'est pas connecté sur un poste.";
            Ajax.oncomplete("PF('echecUser').show()");
        }

    }

    //Enregistrer dans la table associée utilisateur module profile et poids
    public void saveUMP(UtilisateurModuleProfil saveUMP) {
        UtilisateurModulePoids ump = new UtilisateurModulePoids();
        ump.setModule(saveUMP.getModule());
        ump.setUtilisateur(saveUMP.getUtilisateur());
        ump.setPoids(null);
//        List<UtilisateurModulePoids> tmp = this.userModulePoidsService.
//                selectionnerPoidsParUserModule(saveUMP.getModule(), saveUMP.getUtilisateur());
//        boolean save = true;
//        if(tmp != null && !tmp.isEmpty()) {
//            for(UtilisateurModulePoids t : tmp) {
//                if(t.getPoids() == null) {
//                    save = false;
//                }
//            }
//        }
//        if(save) {
//            userModulePoidsService.ajouter(ump);
//        }
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

//    public List<Agence> getListAgence() {
//        return listAgence;
//    }
//
//    public void setListAgence(List<Agence> listAgence) {
//        this.listAgence = listAgence;
//    }
    public List<Profil> getListeProfil() {
        return listeProfil;
    }

    public void setListeProfil(List<Profil> listProfil) {
        this.listeProfil = listProfil;
    }

    public List<Utilisateur> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
        this.listeUtilisateur = listeUtilisateur;
    }

    public List<UtilisateurModuleProfil> getListeModuleProfil() {
        return listeModuleProfil;
    }

    public void setListeModuleProfil(List<UtilisateurModuleProfil> listeModuleProfil) {
        this.listeModuleProfil = listeModuleProfil;
    }

    public List<Modules> getListeModulesByProfil() {
        return listeModulesByProfil;
    }

    public void setListeModulesByProfil(List<Modules> listeModulesByProfil) {
        this.listeModulesByProfil = listeModulesByProfil;
    }

    public Profil getProfilSelected() {
        return profilSelected;
    }

    public void setProfilSelected(Profil profilSelected) {
        this.profilSelected = profilSelected;
    }

    public String getMsg() {
        return msg;
    }

}
