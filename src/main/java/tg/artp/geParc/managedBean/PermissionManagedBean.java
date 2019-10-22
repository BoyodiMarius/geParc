/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;

/**
 *
 * @author SENA
 *
 *
 */
@ManagedBean(name = "permissionManagedBean")
@SessionScoped
public class PermissionManagedBean implements Serializable {

    @EJB
    private UtilisateurServiceBeanLocal userService;

    /*LES GRANDS MENUS DE LA  GRH*/
    private static final String acceuil = "GEPARC";
    private static final String parc = "GEPARC_PARC";
    private static final String alerte = "GEPARC_ALERTE";
    private static final String statistiques = "GEPARC_STAT";
    private static final String administration = "GEPARC_ADMIN";

    /*LES SOUS MENUS DU MENU  ACCUEIL*/
    private static final String motDePasse = "MOTDEPASSE";

    /*LES SOUS MENUS DU MENU  PARC*/
    private static final String vehicule = "VEHICULE";
    private static final String affectation = "AFFECTATION";
    private static final String operations = "OPERATIONS";

    /*LES SOUS MENUS DU MENU  ALERTE*/
    private static final String parametres = "PARAMETRES";
    private static final String alertes = "ALERTES";

    /*LES SOUS MENUS DU MENU  STATISTIQUES*/
    /*LES SOUS MENUS DU MENU  ADMINISTRATION*/
    private static final String utilisateurs = "USER";
    private static final String droit = "DROIT";
    private static final String profil = "PROFIL";
    private static final String attributionProfil = "ATTRIBPROFIL";

    /*LES GRANDS MENUS DE GEPARC*/
    Boolean isGeparcAccueil;
    Boolean isGeparcParc;
    Boolean isGeparcAlertes;
    Boolean isGeparcStatistiques;
    Boolean isGeparcAdministration;

    /*ACTIVATION DE GRANDS MENUS DE GEPARC*/
    Boolean isActiveGeparcAccueil;
    Boolean isActiveGeparcParc;
    Boolean isActiveGeparcAlertes;
    Boolean isActiveGeparcStatistiques;
    Boolean isActiveGeparcAdministration;


    /*LES SOUS MENUS DU MENU  ACCUEIL*/
    Boolean isMotDePasse;
    /*LES SOUS MENUS DU MENU  PARC*/
    Boolean isVehicule;
    Boolean isAffectation;
    Boolean isOperations;
    /*LES SOUS MENUS DU MENU  ALERTE*/
    Boolean isParametres;
    Boolean isAlertes;
    /*LES SOUS MENUS DU MENU  STATISTIQUES*/
    /*LES SOUS MENUS DU MENU  ADMINISTRATION*/
    Boolean isUtilisateurs;
    Boolean isDroit;
    Boolean isProfil;
    Boolean isAttributionProfil;

    /**
     * *************************************************
     */
    @PostConstruct
    public void init() {
        /*LES GRANDS MENUS DE GEPARC*/
        System.out.println("debut");
        isActiveGeparcAccueil = userService.userIsPermitted(acceuil);
        isActiveGeparcParc = userService.userIsPermitted(parc);
        isActiveGeparcAlertes = userService.userIsPermitted(alerte);
        isActiveGeparcStatistiques = userService.userIsPermitted(statistiques);
        isActiveGeparcAdministration = userService.userIsPermitted(administration);

        /*LES SOUS MENUS DU MENU  ACCUEIL*/
        isMotDePasse = userService.userIsPermitted(acceuil + ":" + motDePasse);

        /*LES SOUS MENUS DU MENU  PARC*/
        isVehicule = userService.userIsPermitted(parc + ":" + vehicule);
        isAffectation = userService.userIsPermitted(parc + ":" + affectation);
        isOperations = userService.userIsPermitted(parc + ":" + operations);

        /*LES SOUS MENUS DU MENU  ALERTE*/
        isParametres = userService.userIsPermitted(alerte + ":" + parametres);
        isAlertes = userService.userIsPermitted(alerte + ":" + alertes);

        /*LES SOUS MENUS DU MENU  ADMINISTRATION*/
        isUtilisateurs = userService.userIsPermitted(administration + ":" + utilisateurs);
        isDroit = userService.userIsPermitted(administration + ":" + droit);
        isProfil = userService.userIsPermitted(administration + ":" + profil);
        isAttributionProfil = userService.userIsPermitted(administration + ":" + attributionProfil);

    }

    /**
     * ***************************************************
     */
    public PermissionManagedBean() {
    }

    public UtilisateurServiceBeanLocal getUserService() {
        return userService;
    }

    public void setUserService(UtilisateurServiceBeanLocal userService) {
        this.userService = userService;
    }

    public Boolean getIsGeparcAccueil() {
        return isGeparcAccueil;
    }

    public void setIsGeparcAccueil(Boolean isGeparcAccueil) {
        this.isGeparcAccueil = isGeparcAccueil;
    }

    public Boolean getIsGeparcParc() {
        return isGeparcParc;
    }

    public void setIsGeparcParc(Boolean isGeparcParc) {
        this.isGeparcParc = isGeparcParc;
    }

    public Boolean getIsGeparcAlertes() {
        return isGeparcAlertes;
    }

    public void setIsGeparcAlertes(Boolean isGeparcAlertes) {
        this.isGeparcAlertes = isGeparcAlertes;
    }

    public Boolean getIsGeparcStatistiques() {
        return isGeparcStatistiques;
    }

    public void setIsGeparcStatistiques(Boolean isGeparcStatistiques) {
        this.isGeparcStatistiques = isGeparcStatistiques;
    }

    public Boolean getIsGeparcAdministration() {
        return isGeparcAdministration;
    }

    public void setIsGeparcAdministration(Boolean isGeparcAdministration) {
        this.isGeparcAdministration = isGeparcAdministration;
    }

    public Boolean getIsActiveGeparcAccueil() {
        return isActiveGeparcAccueil;
    }

    public void setIsActiveGeparcAccueil(Boolean isActiveGeparcAccueil) {
        this.isActiveGeparcAccueil = isActiveGeparcAccueil;
    }

    public Boolean getIsActiveGeparcParc() {
        return isActiveGeparcParc;
    }

    public void setIsActiveGeparcParc(Boolean isActiveGeparcParc) {
        this.isActiveGeparcParc = isActiveGeparcParc;
    }

    public Boolean getIsActiveGeparcAlertes() {
        return isActiveGeparcAlertes;
    }

    public void setIsActiveGeparcAlertes(Boolean isActiveGeparcAlertes) {
        this.isActiveGeparcAlertes = isActiveGeparcAlertes;
    }

    public Boolean getIsActiveGeparcStatistiques() {
        return isActiveGeparcStatistiques;
    }

    public void setIsActiveGeparcStatistiques(Boolean isActiveGeparcStatistiques) {
        this.isActiveGeparcStatistiques = isActiveGeparcStatistiques;
    }

    public Boolean getIsActiveGeparcAdministration() {
        return isActiveGeparcAdministration;
    }

    public void setIsActiveGeparcAdministration(Boolean isActiveGeparcAdministration) {
        this.isActiveGeparcAdministration = isActiveGeparcAdministration;
    }

    public Boolean getIsMotDePasse() {
        return isMotDePasse;
    }

    public void setIsMotDePasse(Boolean isMotDePasse) {
        this.isMotDePasse = isMotDePasse;
    }

    public Boolean getIsVehicule() {
        return isVehicule;
    }

    public void setIsVehicule(Boolean isVehicule) {
        this.isVehicule = isVehicule;
    }

    public Boolean getIsAffectation() {
        return isAffectation;
    }

    public void setIsAffectation(Boolean isAffectation) {
        this.isAffectation = isAffectation;
    }

    public Boolean getIsOperations() {
        return isOperations;
    }

    public void setIsOperations(Boolean isOperations) {
        this.isOperations = isOperations;
    }

    public Boolean getIsParametres() {
        return isParametres;
    }

    public void setIsParametres(Boolean isParametres) {
        this.isParametres = isParametres;
    }

    public Boolean getIsAlertes() {
        return isAlertes;
    }

    public void setIsAlertes(Boolean isAlertes) {
        this.isAlertes = isAlertes;
    }

    public Boolean getIsUtilisateurs() {
        return isUtilisateurs;
    }

    public void setIsUtilisateurs(Boolean isUtilisateurs) {
        this.isUtilisateurs = isUtilisateurs;
    }

    public Boolean getIsDroit() {
        return isDroit;
    }

    public void setIsDroit(Boolean isDroit) {
        this.isDroit = isDroit;
    }

    public Boolean getIsProfil() {
        return isProfil;
    }

    public void setIsProfil(Boolean isProfil) {
        this.isProfil = isProfil;
    }

    public Boolean getIsAttributionProfil() {
        return isAttributionProfil;
    }

    public void setIsAttributionProfil(Boolean isAttributionProfil) {
        this.isAttributionProfil = isAttributionProfil;
    }

}
