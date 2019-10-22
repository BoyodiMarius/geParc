/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.affectationChauffeurVehicule;
import tg.artp.geParc.entities.affectationPK;
import tg.artp.geParc.entities.affectationVehiculeService;
import tg.artp.geParc.entities.affectations;
import tg.artp.geParc.entities.chauffeurs;
import tg.artp.geParc.entities.estimation;
import tg.artp.geParc.entities.fiche;
import tg.artp.geParc.entities.missions;
import tg.artp.geParc.entities.services;
import tg.artp.geParc.entities.typeVehicules;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.report.files.EtatRatioConsommation;
import tg.artp.geParc.services.affectationChauffeurVehiculeServiceBeanLocal;
import tg.artp.geParc.services.affectationServiceBeanLocal;
import tg.artp.geParc.services.affectationVehiculeSerServiceBeanLocal;
import tg.artp.geParc.services.chauffeursServiceBeanLocal;
import tg.artp.geParc.services.ficheServiceBeanLocal;
import tg.artp.geParc.services.missionsServiceBeanLocal;
import tg.artp.geParc.services.serviceServiceBeanLocal;
import tg.artp.geParc.services.typeVehiculeServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class AffectationMBean implements Serializable {

    @EJB
    private affectationServiceBeanLocal affectationService;
    private affectations formObjectA, selectObjectA, supressionA, modifA;
    private List<affectations> listeAffectation, listeControleA, listeAffectationFiltre;

    @EJB
    private affectationChauffeurVehiculeServiceBeanLocal affectationChauffeurVehiculeService;
    private affectationChauffeurVehicule formObjectACV, selectObjectACV, supressionACV, modifACV;
    private List<affectationChauffeurVehicule> listeAffectationACV, listeControleACV, listeAffectationAVCFiltre;

    @EJB
    private affectationVehiculeSerServiceBeanLocal affectationServiceVehiculeService;
    private affectationVehiculeService formObjectAVS, selectObjectAVS, supressionAVS, modifAVS;
    private List<affectationVehiculeService> listeAffectationAVS, listeControleAVS, listeAffectationAVSFiltre;

    @EJB
    private chauffeursServiceBeanLocal chauffeurService;
    private chauffeurs formObjectCh, selectObjectCh, supressionCh, modifCh, enlever;
    private List<chauffeurs> listeChauffeur, listeControleChauffeur, listeChauffeur2;

    @EJB
    private serviceServiceBeanLocal servicesService;
    private services formObjectSer, selectObjectSer, supressionSer, modifSer, enleverSer, service;
    private List<services> listeService, listeControleService;

    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private vehicules formVehicule;
    private List<vehicules> listeVehicule, listevehiculeTotal, listeEstimationVehicule;

    @EJB
    private missionsServiceBeanLocal missionsService;
    private List<missions> listeMission;

    @EJB
    private ficheServiceBeanLocal ficheService;
    private fiche formObject, info1, info2;
    private List<fiche> listeFiche;
    
    @EJB
    private typeVehiculeServiceBeanLocal typeVehiculeService;
    private List<typeVehicules> listeTypeVehicule;

    private String message, infoTitre;
    private String id1 = "AFF";
    private String id2 = "AF_CHA";
    private String id3 = "CHA";
    private String id4 = "AF_V_S";
    private String code = "";
    int index1, index2;
    private boolean desactiver = true, desactivation = true;
    private String nom, prenom, nationalite, contact;
    private vehicules vehicule, veh1;
    private affectationPK idAffectationMission;
    private chauffeurs chof;
    private missions mis;
    private Date dateduJour;
    private String m1, m2,m3;
    private boolean afm1 = false, afm2 = false;

    private BigDecimal montant,vidange, vidangeTotal, kiloEstimer;
    private List<estimation> ListeEstimation;
    private estimation formEstimation;
    private typeVehicules typeVehicule;
    
    public AffectationMBean() {
        this.formObjectA = new affectations();
        this.formObjectACV = new affectationChauffeurVehicule();
        this.formObjectCh = new chauffeurs();
        this.formObjectAVS = new affectationVehiculeService();
        this.formObjectSer = new services();
        this.formObject = new fiche();
        this.info1 = new fiche();
        this.info2 = new fiche();
        this.formVehicule = new vehicules();
        this.formEstimation = new estimation();
    }

    @ManagedProperty(value = "#{AffectationGeneralMBean}")
    private AffectationGeneralMBean recuparation;

    @PostConstruct
    public void chargerElement() {
        this.listeAffectation = new ArrayList();
        this.listeControleA = new ArrayList();
        this.listeAffectation = this.affectationService.selectionnerTout();
        this.listeControleA = this.affectationService.selectionnerTout();

        this.listeAffectationACV = new ArrayList();
        this.listeControleACV = new ArrayList();
        this.listeAffectationACV = this.affectationChauffeurVehiculeService.selectionnerTout();
        this.listeControleACV = this.affectationChauffeurVehiculeService.selectionnerTout();

        this.listeAffectationAVS = new ArrayList();
        this.listeControleAVS = new ArrayList();
        this.listeAffectationAVS = affectationServiceVehiculeService.selectionnerTout();
        this.listeControleAVS = affectationServiceVehiculeService.selectionnerTout();

        this.listeService = new ArrayList();
        this.listeControleService = new ArrayList();
        this.listeService = this.servicesService.selectionnerTout();
        //this.listeService = servicesService.selectionnerTout();
        //this.formObjectSer = servicesService.selectionner("SER01");
        //this.listeService.add(formObjectSer);

        this.listeChauffeur = new ArrayList();
        this.listeControleChauffeur = new ArrayList();
        this.listeChauffeur2 = new ArrayList();
        this.listeChauffeur = this.chauffeurService.selectionnerTout();
        this.listeControleChauffeur = this.chauffeurService.selectionnerTout();
//        this.listeChauffeur2 = this.chauffeurService.selectionnerTout();

        this.listeVehicule = new ArrayList();
        this.listevehiculeTotal = new ArrayList();
        this.listevehiculeTotal = this.vehiculeService.selectionnerTout();

        this.listeMission = new ArrayList();
        this.listeMission = this.missionsService.selectionnerTout();

        int nb = listevehiculeTotal.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listevehiculeTotal.get(i).isEtatVehicule() == true) {
                    listeVehicule.add(listevehiculeTotal.get(i));
                }
            }
        }

        int nb2 = listeChauffeur.size();
        String comp;
        if (nb != 0) {
//            System.out.println("avant");
//             for (int p=0;p<=nb2-1;p++){
//                 System.out.println("nom "+listeChauffeur2.get(p).getNomChauffeur());
//             }
            for (int p = 0; p <= nb2 - 1; p++) {
                comp = listeChauffeur.get(p).getStatut();
                if (comp.equals("CHAUFFEUR")) {
                    listeChauffeur2.add(listeChauffeur.get(p));
                }
            }
//            System.out.println("apres");
//             for (int i=0;i<=listeChauffeur2.size()-1;i++){
//                 System.out.println("nom "+listeChauffeur2.get(i).getNomChauffeur());
//             }
        }
        desactivation = true;
        afm1 = false;
        afm2 = false;

        this.listeFiche = new ArrayList();
        this.listeFiche = this.ficheService.selectionnerTout();

        this.dateduJour = new Date();
        int nb1 = listeFiche.size();

        if (nb1 != 0) {
            for (int i = 0; i <= nb1 - 1; i++) {
                if ((dateduJour.compareTo(listeFiche.get(i).getDateDelivrer()) == 1 || dateduJour.compareTo(listeFiche.get(i).getDateDelivrer()) == 0)
                        && (dateduJour.compareTo(listeFiche.get(i).getDateExpiration()) == -1 || dateduJour.compareTo(listeFiche.get(i).getDateExpiration()) == 0)) {
                    formObject = listeFiche.get(i);
                    formObject.setEtatFiche(true);
                    ficheService.modifier(formObject);
                } else {
                    formObject = listeFiche.get(i);
                    formObject.setEtatFiche(false);
                    ficheService.modifier(formObject);
                }
            }

        }
        vehicule = null;
        this.ListeEstimation = new ArrayList();
        this.listeEstimationVehicule = new ArrayList();
        this.listeEstimationVehicule = this.listeVehicule;
        
        this.listeTypeVehicule = new ArrayList();
        this.listeTypeVehicule = this.typeVehiculeService.selectionnerTout();
        System.out.println("charger");
    }

    public void activation() {
        if (vehicule==null){
            desactivation = true;
        } else {
            desactivation = false;
        }
        
        listeAffectationAVCF();
        listeAffectationAVSF();
        listeAffectationF();
    }

    public void rowSelectA() {
        montant = selectObjectA.getMontantPercu();
        this.listeAffectation = new ArrayList();
        this.listeControleA = new ArrayList();
        this.listeControleA = affectationService.selectionnerTout();
        int nb = listeControleA.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeControleA.get(i).getIdMission().getObjetMission().equals(selectObjectA.getIdMission().getObjetMission())
                        && listeControleA.get(i).getIdChauffeur().getNomChauffeur().equals(selectObjectA.getIdChauffeur().getNomChauffeur())
                        && listeControleA.get(i).getIdChauffeur().getPrenomChauffeur().equals(selectObjectA.getIdChauffeur().getPrenomChauffeur())
                        && listeControleA.get(i).getIdMission().getDateDepart().compareTo(selectObjectA.getIdMission().getDateDepart()) == 0
                        && listeControleA.get(i).getIdMission().getDateRetour().compareTo(selectObjectA.getIdMission().getDateRetour()) == 0
                        && listeControleA.get(i).getIdVehicule().getImmatriculation().equals(selectObjectA.getIdVehicule().getImmatriculation())
                        && listeControleA.get(i).getKilometrageDepart().compareTo(selectObjectA.getKilometrageDepart()) == 0) {
                    System.out.println("retirer");
                } else {
                    listeAffectation.add(listeControleA.get(i));
                }
            }
        }

        formObjectA = selectObjectA;
        supressionA = selectObjectA;
        this.index1 = this.listeAffectation.indexOf(this.selectObjectA);
        modifA = selectObjectA;
        vehicule = vehiculeService.selectionner(selectObjectA.getIdAffectation().getIdVehicule());
        veh1 = vehiculeService.selectionner(selectObjectA.getIdAffectation().getIdVehicule());
        chof = chauffeurService.selectionner(selectObjectA.getIdAffectation().getIdChauffeur());
        //mis = missionsService.selectionner(selectObjectA.getIdAffectation().getIdMission());

        mis = selectObjectA.getIdMission();

//        listeMission.get(index1);
        listeMission.add(mis);
        System.out.println("mis " + mis.getObjetMission());

        afm1 = true;
        if (selectObjectA.getKilometrageArrive() != null) {
            afm2 = true;
        }

    }

    public void rowSelectACV() {
        this.listeControleACV = new ArrayList();
        this.listeControleACV = this.affectationChauffeurVehiculeService.selectionnerTout();
        formObjectACV = selectObjectACV;
        supressionACV = selectObjectACV;
        this.index2 = this.listeAffectationACV.indexOf(this.selectObjectACV);
        modifACV = selectObjectACV;
        System.out.println("ind " + index2);
        listeControleACV.remove(index2);
        System.out.println("res " + formObjectACV.getIdChauffeur().getNomChauffeur());

        listeChauffeur.clear();
        listeChauffeur.add(formObjectACV.getIdChauffeur());
        listeChauffeur.addAll(this.chauffeurService.recupererListePriveDe(formObjectACV.getIdChauffeur()));
        //listeChauffeur.remove(index1);
    }

    public void rowSelectAVS() {
        this.listeControleAVS = new ArrayList();

        int nb = listeAffectationAVS.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeAffectationAVS.get(i).equals(selectObjectAVS)) {
                    System.out.println("retirer");
                } else {
                    listeControleAVS.add(listeAffectationAVS.get(i));
                }
            }

        }

        //this.listeControleAVS = this.affectationServiceVehiculeService.selectionnerTout();
        formObjectAVS = selectObjectAVS;
        supressionAVS = selectObjectAVS;
        veh1 = formObjectAVS.getIdVehicule();
        //vehicule = vehiculeService.selectionner(selectObjectAVS.getIdVehicule().getImmatriculation());
        vehicule = selectObjectAVS.getIdVehicule();
        System.out.println("vehicule " + vehicule.getImmatriculation());
        //this.index2 = this.listeAffectationAVS.indexOf(this.selectObjectAVS);
        //modifAVS = selectObjectAVS;
        //listeControleAVS.remove(index2);
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }

    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
    }

    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Vous n'avez rien saisie"));
    }

    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }

    public void suppressionA() {
        try {
            affectationService.supprimer(selectObjectA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierA() {
        try {
            //affectations intermediaire;
            //intermediaire = selectObjectA;
            if (selectObjectA.getKilometrageArrive() == null) {
                System.out.println("kilo null");
            } else {
                formVehicule = vehicule;
                vidange=formObjectA.getKilometrageArrive().subtract(formVehicule.getKilometrageActuel());
                vidangeTotal=formVehicule.getKilometrageVidange().add(vidange);
                formVehicule.setKilometrageVidange(vidangeTotal);
                formVehicule.setKilometrageActuel(selectObjectA.getKilometrageArrive());
                vehiculeService.modifier(formVehicule);
            }

            if (selectObjectA.getMontantPercu() == null) {
                System.out.println("montant null");
            } else {
                formObjectCh = selectObjectA.getIdChauffeur();
                BigDecimal total, inter;
                int combine = 0;
                BigDecimal combine1 = new BigDecimal(combine);
                if (formObjectCh.getMotantTotalMission().compareTo(combine1) == 0) {
                    System.out.println("ok");
                    total = formObjectCh.getMotantTotalMission().add(selectObjectA.getMontantPercu());
                } else {
                    System.out.println("ok2");
                    inter = formObjectCh.getMotantTotalMission().subtract(montant);
                    System.out.println("inter " + inter);
                    total = inter.add(selectObjectA.getMontantPercu());
                    System.out.println("total " + total);
                }
                formObjectCh.setMotantTotalMission(total);
                chauffeurService.modifier(formObjectCh);
            }

            affectationService.modifier(selectObjectA);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void chof(){
//        System.out.println("chof "+chof.getNomChauffeur());
//    }
//    
//    public void mis(){
//        System.out.println("mis  "+mis.getIdMission());
//    }
    public void ajouterA() {
        try {
            idAffectationMission = new affectationPK();
            idAffectationMission.setIdMission(mis.getIdMission());
            idAffectationMission.setIdChauffeur(chof.getIdChauffeur());
            idAffectationMission.setIdVehicule(vehicule.getIdVehicule());
            formObjectA.setIdMission(mis);
            vehicule = recuparation.getVehicule();
            String id = vehicule.getIdVehicule();
            vehicule = null;
            vehicule = vehiculeService.selectionner(id);
           
            formObjectA.setIdVehicule(vehicule);
            formObjectA.setIdChauffeur(chof);
            formObjectA.setIdAffectation(idAffectationMission);
            formObjectA.setKilometrageDepart(vehicule.getKilometrageActuel());
            this.affectationService.ajouter(formObjectA);

            if (formObjectA.getKilometrageArrive() == null) {
                System.out.println("kilo null");
            } else {
                formVehicule = vehicule;
                vidange=formObjectA.getKilometrageArrive().subtract(formVehicule.getKilometrageActuel());
                vidangeTotal=formVehicule.getKilometrageVidange().add(vidange);
                formVehicule.setKilometrageVidange(vidangeTotal);
                formVehicule.setKilometrageActuel(formObjectA.getKilometrageArrive());
                vehiculeService.modifier(formVehicule);
            }

            if (formObjectA.getMontantPercu() == null) {
                System.out.println("montant null");
            } else {
                formObjectCh = chof;
                BigDecimal total;
                total = formObjectCh.getMotantTotalMission().add(formObjectA.getMontantPercu());
                formObjectCh.setMotantTotalMission(total);
                chauffeurService.modifier(formObjectCh);
            }

            //formVehicule = new vehicules();
            //formObjectCh = new chauffeurs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerA() {
        if (selectObjectA != null) {
            modifierA();
            message = "Modification effectué avec succès";
            Ajax.oncomplete("PF('succesOperationAFFCHMV').show()");
            this.rafraichir();
            listeAffectationF();
            //recuparation.rafraichir();
        } else {
            ajouterA();
            message = "Enregistrement effectué avec succès";
            Ajax.oncomplete("PF('succesOperationAFFCHMV').show()");
            this.rafraichir();
            listeAffectationF();
        }
    }

    public void supprimerA() {
        if (selectObjectA == null) {
            return;
        }
        try {
            suppressionA();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationAFFCHMV').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index1 = 0;
    }

    //acv
    public void suppressionACV() {
        try {
            affectationChauffeurVehiculeService.supprimer(selectObjectACV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierACV() {
        try {
            selectObjectACV.setIdVehicule(vehicule);
            affectationChauffeurVehiculeService.modifier(selectObjectACV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterACV() {
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeAffectationACV.size();
            if (i == 0) {
                code = "";
                code = id2 + "01";
            } else if (i <= 8) {
                code = listeAffectationACV.get(i - 1).getIdAffectationChauffeurVehicule();
                code = code.substring(7);
                ii = Integer.parseInt(code) + 1;
                code = id2 + "0" + Integer.toString(ii);
            } else {
                code = listeAffectationACV.get(i - 1).getIdAffectationChauffeurVehicule();
                code = code.substring(6);
                ii = Integer.parseInt(code) + 1;
                code = id2 + Integer.toString(ii);
            }
            formObjectACV.setIdAffectationChauffeurVehicule(code);
            vehicule = recuparation.getVehicule();
            formObjectACV.setIdVehicule(vehicule);
            this.affectationChauffeurVehiculeService.ajouter(formObjectACV);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerACV() {
        if (selectObjectACV != null) {
            modifierACV();
            message = "Modification effectué avec succès";
            Ajax.oncomplete("PF('succesOperationAVC').show()");
            this.rafraichir();
            listeAffectationAVCF();
        } else {
            ajouterACV();
            message = "Enregistrement effectué avec succès";
            Ajax.oncomplete("PF('succesOperationAVC').show()");
            this.rafraichir();
            listeAffectationAVCF();
        }
    }

    public void supprimerACV() {
        if (selectObjectACV == null) {
            return;
        }
        try {
            suppressionACV();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationAVC').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index2 = 0;
    }

    //avs
    public void suppressionAVS() {
        try {
            affectationServiceVehiculeService.supprimer(selectObjectAVS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierAVS() {
        try {
            System.out.println("veh " + veh1.getImmatriculation());
            System.out.println("veh2 " + formObjectAVS.getIdVehicule().getImmatriculation());
            selectObjectAVS.setIdVehicule(veh1);
            formObjectAVS.setIdService(service);
            affectationServiceVehiculeService.modifier(selectObjectAVS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterAVS() {
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeAffectationAVS.size();
            if (i == 0) {
                code = "";
                code = id4 + "01";
            } else if (i <= 8) {
                code = listeAffectationAVS.get(i - 1).getIdAffectationVehiculeService();
                code = code.substring(7);
                ii = Integer.parseInt(code) + 1;
                code = id4 + "0" + Integer.toString(ii);
            } else {
                code = listeAffectationAVS.get(i - 1).getIdAffectationVehiculeService();
                code = code.substring(6);
                ii = Integer.parseInt(code) + 1;
                code = id4 + Integer.toString(ii);
            }
            formObjectAVS.setIdAffectationVehiculeService(code);
            vehicule = recuparation.getVehicule();
            formObjectAVS.setIdVehicule(vehicule);
            formObjectAVS.setIdService(service);
            this.affectationServiceVehiculeService.ajouter(formObjectAVS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerAVS() {
        if (selectObjectAVS != null) {
            modifierAVS();
            message = "Modification effectué avec succès";
            Ajax.oncomplete("PF('succesOperationAVS').show()");
            this.rafraichir();
            listeAffectationAVSF();
        } else {
            ajouterAVS();
            message = "Enregistrement effectué avec succès";
            Ajax.oncomplete("PF('succesOperationAVS').show()");
            this.rafraichir();
            listeAffectationAVSF();
        }
    }

    public void supprimerAVS() {
        if (selectObjectAVS == null) {
            return;
        }
        try {
            suppressionAVS();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationAVS').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index2 = 0;
    }

    public void rafraichir() {
        this.chargerElement();
        this.effacer();
        //recuparation.rafraichir();
        desactiver = true;
    }

    public void effacer() {
        formObjectA = new affectations();
        selectObjectA = null;
        formObjectACV = new affectationChauffeurVehicule();
        selectObjectACV = null;
        formObjectAVS = new affectationVehiculeService();
        selectObjectAVS = null;
        mis = null;
        nom = "";
        prenom = "";
        nationalite = "";
        contact = "";
        //vehicule = null;
        idAffectationMission = null;
        chof = null;
    }

    public void remplirFormulaire() {
        formObjectA = selectObjectA != null ? selectObjectA : new affectations();
        formObjectACV = selectObjectACV != null ? selectObjectACV : new affectationChauffeurVehicule();
    }

    public void confirmerEnregistrementA() {
        vehicule = recuparation.getVehicule();

        if (selectObjectA != null) {
            mis = selectObjectA.getIdMission();
            chof = selectObjectA.getIdChauffeur();
        }

        boolean veri1 = false, veri2 = false, veri3 = false, veri4 = false, veri5 = false, veri6 = false, veri7 = false, veri8 = false, veri9=false, veri10=false;
        if (vehicule == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucun véhicule n'a été sélectionner "));
            veri1 = true;
        }
        if (mis == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucune mission n'a été sélectionner "));
            veri2 = true;
        }
        if (chof == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucun chauffeur n'a été sélectionner "));
            veri3 = true;
        }

        if (veri1 == false && veri2 == false && veri3 == false) {

            int nb1 = listeAffectation.size();
            if (nb1 != 0) {
                for (int i = 0; i <= nb1 - 1; i++) {
                    if (listeAffectation.get(i).getIdMission().getObjetMission().equals(mis.getObjetMission())
                            && listeAffectation.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule a été sélectionner pour cette mission "));
                        veri4 = true;
                    }
                }

                for (int i = 0; i <= nb1 - 1; i++) {
                    if (listeAffectation.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                            && mis.getDateDepart().compareTo(listeAffectation.get(i).getIdMission().getDateDepart()) == -1
                            && mis.getDateRetour().compareTo(listeAffectation.get(i).getIdMission().getDateRetour()) == -1) {
                        System.out.println("bien joueer");
                    } else {
                        if (listeAffectation.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && (listeAffectation.get(i).getIdMission().getDateRetour().compareTo(mis.getDateDepart()) == 1
                                || (listeAffectation.get(i).getIdMission().getDateDepart().compareTo(mis.getDateDepart()) == 1 && listeAffectation.get(i).getIdMission().getDateRetour().compareTo(mis.getDateRetour()) == -1))) {

                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule est en mission du "
                                    + dateToString(listeAffectation.get(i).getIdMission().getDateDepart()) + " au " + dateToString(listeAffectation.get(i).getIdMission().getDateRetour())));
                            veri5 = true;
                        }
                    }
                }

                for (int i = 0; i <= nb1 - 1; i++) {
                    if (listeAffectation.get(i).getIdChauffeur().getIdChauffeur().equals(chof.getIdChauffeur())
                            && mis.getDateDepart().compareTo(listeAffectation.get(i).getIdMission().getDateDepart()) == -1
                            && mis.getDateRetour().compareTo(listeAffectation.get(i).getIdMission().getDateRetour()) == -1) {
                        System.out.println("bien joueer");
                    } else {
                        if (listeAffectation.get(i).getIdChauffeur().getIdChauffeur().equals(chof.getIdChauffeur())
                                && (listeAffectation.get(i).getIdMission().getDateRetour().compareTo(mis.getDateDepart()) == 1
                                || (listeAffectation.get(i).getIdMission().getDateDepart().compareTo(mis.getDateDepart()) == 1 && listeAffectation.get(i).getIdMission().getDateRetour().compareTo(mis.getDateRetour()) == -1))) {

                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce chauffeur est en mission du "
                                    + dateToString(listeAffectation.get(i).getIdMission().getDateDepart()) + " au " + dateToString(listeAffectation.get(i).getIdMission().getDateRetour()) + " avec le véhicule " + listeAffectation.get(i).getIdVehicule().getImmatriculation()));
                            veri6 = true;
                        }
                    }
                }

                for (int i = 0; i <= nb1 - 1; i++) {
                    if (listeAffectation.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation()) && listeAffectation.get(i).getKilometrageArrive() == null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le Kilométrage de retour de la mission précédente n'est pas renseigner  "));
                        veri7 = true;
                    }
                }

            }

        }
        if (veri1 == false && veri2 == false && veri3 == false && veri4 == false && veri5 == false && veri6 == false && veri7 == false) {

            if (formObjectA.getKilometrageArrive() == null) {
                System.out.println("non renseigné");
            } else {
                if (selectObjectA == null) {
                    if (vehicule.getKilometrageActuel().compareTo(formObjectA.getKilometrageArrive()) == 0 || vehicule.getKilometrageActuel().compareTo(formObjectA.getKilometrageArrive()) == 1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le Kilométrage de retour ne peut pas être inférieur au kilométrage actuel  "));
                        veri8 = true;
                    }
                }

            }
            int comp = 0;
            BigDecimal compar = new BigDecimal(comp);
            if(formObjectA.getCarburantConsomme()== null){
                System.out.println("non renseigné");
            } else {
                 if (selectObjectA == null) {
                    if (formObjectA.getCarburantConsomme().compareTo(compar) == -1 ) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le carburant consommer ne peut être négatif "));
                        veri9 = true;
                    }
                }
            }
            if(formObjectA.getMontantPercu() == null){
                System.out.println("non renseigné");
            } else {
                 if (selectObjectA == null) {
                    if (formObjectA.getMontantPercu().compareTo(compar) == -1 ) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le montant percu ne peut être négatif "));
                        veri10 = true;
                    }
                }
            }
            

            if (veri8 == false && veri9==false && veri10==false) {
                BigDecimal kiloVidange;
                kiloVidange = vehicule.getIdModele().getParametreVidange().subtract(vehicule.getKilometrageVidange());
                int comp1 = -1;
                BigDecimal compar1 = new BigDecimal(comp1);
                if(kiloVidange.compareTo(compar)==-1){
                    m3="La dernière vidange prévu pour ce véhicule n'a pa été effectuer. Elle a effectuer "+kiloVidange.multiply(compar1)+" Km de plus.";
                } else {
                    m3="Il reste "+kiloVidange+" km avant la prochaine vidange.";
                }
                m1 = "Le véhicule " + vehicule.getImmatriculation() + " ne possède aucune assurance en cour de validiter. ";
                m2 = "Ce véhicule ne possède aucune visite technique en cour de validiter. ";
                int nb = listeFiche.size();
                if (nb != 0) {
                    for (int i = 0; i <= nb - 1; i++) {
                        System.out.println("etat " + listeFiche.get(i).getEtatFiche());
                        System.out.println("Liste vehicule " + listeFiche.get(i).getIdVehicule().getImmatriculation());
                        System.out.println("Vehicule " + vehicule.getImmatriculation());
                        System.out.println("Type fiche " + listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche());
                        if (listeFiche.get(i).getEtatFiche() == true && listeFiche.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE")) {
                            info1 = listeFiche.get(i);
                            System.out.println("vrai");
                            m1 = "L'assurance du véhicule " + vehicule.getImmatriculation() + " expire le " + dateToString(info1.getDateExpiration()) + ". ";
                        }
                        if (listeFiche.get(i).getEtatFiche() == true && listeFiche.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("VISITE TECHNIQUE")) {
                            info2 = listeFiche.get(i);
                            m2 = "La visite technique de ce véhicule expire le " + dateToString(info2.getDateExpiration()) + ". ";
                        }
                    }
                }
                if(selectObjectA==null){
                     message = m1 + m2 +" "+m3+" " + " Voulez-vous utiliser le véhicule " + vehicule.getImmatriculation() + " pour cette mission du " + dateToString(mis.getDateDepart()) + " au " + dateToString(mis.getDateRetour()) + "??? ";
                } else {
                    message = "Voulez-vous effectué ses modifications";
                }
               
                Ajax.oncomplete("PF('confirmerEnregistrementAFFCHMV').show()");

            }
        }

        //rafraichir();
    }
    
    public void infoVehicule(){
        int comp = 0;
        BigDecimal compar = new BigDecimal(comp);
        BigDecimal kiloVidange;
                kiloVidange = vehicule.getIdModele().getParametreVidange().subtract(vehicule.getKilometrageVidange());
                int comp1 = -1;
                BigDecimal compar1 = new BigDecimal(comp1);
                if(kiloVidange.compareTo(compar)==-1){
                    m3="La dernière vidange prévu pour ce véhicule n'a pa été effectuer. Elle a effectuer "+kiloVidange.multiply(compar1)+" Km de plus.";
                } else {
                    m3="Il reste "+kiloVidange+" km avant la prochaine vidange.";
                }
                m1 = "Le véhicule " + vehicule.getImmatriculation() + " ne possède aucune assurance en cour de validiter. ";
                m2 = "Ce véhicule ne possède aucune visite technique en cour de validiter. ";
                int nb = listeFiche.size();
                if (nb != 0) {
                    for (int i = 0; i <= nb - 1; i++) {
                        System.out.println("etat " + listeFiche.get(i).getEtatFiche());
                        System.out.println("Liste vehicule " + listeFiche.get(i).getIdVehicule().getImmatriculation());
                        System.out.println("Vehicule " + vehicule.getImmatriculation());
                        System.out.println("Type fiche " + listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche());
                        if (listeFiche.get(i).getEtatFiche() == true && listeFiche.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE")) {
                            info1 = listeFiche.get(i);
                            System.out.println("vrai");
                            m1 = "L'assurance du véhicule " + vehicule.getImmatriculation() + " expire le " + dateToString(info1.getDateExpiration()) + ". ";
                        }
                        if (listeFiche.get(i).getEtatFiche() == true && listeFiche.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("VISITE TECHNIQUE")) {
                            info2 = listeFiche.get(i);
                            m2 = "La visite technique de ce véhicule expire le " + dateToString(info2.getDateExpiration()) + ". ";
                        }
                    }
                }
                infoTitre = "Informations du véhicule"+vehicule.getImmatriculation();
                 message = m1 + m2 +" "+m3;
                          Ajax.oncomplete("PF('InfoVehicule').show()");
    }
    
    public static String dateToString(Date date) {
        String jour = "" + date.getDate();
        String mois = "" + (date.getMonth() + 1);
        String annee = "" + (date.getYear() + 1900);
        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return (jour + "/" + mois + "/" + annee);
    }

    public void confirmerSuppressionA() {
        if (selectObjectA == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionAFFCHMV').show()");
        }
    }

    public void confirmerEnregistrementACV() {
        boolean veri = false, veri2 = false, veri3 = false, veri4 = false;
        int nb = listeControleACV.size();
        vehicule = recuparation.getVehicule();
        if (formObjectACV.getIdChauffeur() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucun responsable n'a été sélectionner "));
            veri3 = true;
        }
        if (formObjectACV.getDateDebutAffectation() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de début d'affectation n'a pas été renseigner"));
            veri4 = true;
        }

        if (veri3 == false && veri4 == false) {

            if (formObjectACV.getDateFinAffectation() == null) {
                if (nb != 0) {
                    for (int i = 0; i <= nb - 1; i++) {
                        if (listeControleACV.get(i).getIdVehicule().getIdVehicule().equals(vehicule.getIdVehicule())
                                && (listeControleACV.get(i).getDateFinAffectation() == null || listeControleACV.get(i).getDateFinAffectation().compareTo(formObjectACV.getDateDebutAffectation()) == 1)) {
                            veri = true;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!",
                                                                                                " Ce véhicule est déjà affecter à un responsable "));
                        }
                    }
                    for (int i = 0; i <= nb - 1; i++) {
                        if (listeControleACV.get(i).getIdChauffeur().getIdChauffeur().equals(formObjectACV.getIdChauffeur().getIdChauffeur())
                                && (listeControleACV.get(i).getDateFinAffectation() == null || listeControleACV.get(i).getDateFinAffectation().compareTo(formObjectACV.getDateDebutAffectation()) == 1)) {
                            veri2 = true;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce responsable est déjà affecter à un véhicule "));
                        }
                    }
                }
            } else {
                if (formObjectACV.getDateDebutAffectation().compareTo(formObjectACV.getDateFinAffectation()) == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de fin affectation ne peut pas être inférieur à la date de début affectation  "));
                    veri = true;
                } else if (formObjectACV.getDateDebutAffectation().compareTo(formObjectACV.getDateFinAffectation()) == 0) {
                    veri = true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de fin affectation ne peut pas être égale à la date de début affectation "));
                } else {
                    if (nb != 0) {
                        for (int i = 0; i <= nb - 1; i++) {
                            System.out.println("vehi " + vehicule.getIdVehicule());
                            if(listeControleACV.get(i).getIdVehicule()!=null){
                                 if (listeControleACV.get(i).getIdVehicule().getIdVehicule().equals(vehicule.getIdVehicule())
                                    && (listeControleACV.get(i).getDateFinAffectation() == null || listeControleACV.get(i).getDateFinAffectation().compareTo(formObjectACV.getDateDebutAffectation()) == 1)) {
                                    veri = true;
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule est déjà affecter à un responsable "));
                                }
                            }
                           
                        }
                        for (int i = 0; i <= nb - 1; i++) {
                            if (listeControleACV.get(i).getIdChauffeur().getIdChauffeur().equals(formObjectACV.getIdChauffeur().getIdChauffeur())
                                    && (listeControleACV.get(i).getDateFinAffectation() == null || listeControleACV.get(i).getDateFinAffectation().compareTo(formObjectACV.getDateDebutAffectation()) == 1)) {
                                veri2 = true;
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce responsable est déjà affecter à un véhicule "));
                            }
                        }
                    }

                }
            }

            if (veri == false && veri2 == false) {
                Ajax.oncomplete("PF('confirmerEnregistrementAVC').show()");
            }
        }
        //rafraichir();
    }

    public void confirmerSuppressionACV() {
        if (selectObjectACV == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionAVC').show()");
        }
    }

    public void confirmerEnregistrementAVS() {
        vehicule = recuparation.getVehicule();
        boolean veri = false, veri2 = false, veri3 = false, veri4 = false;
        int nb = listeControleAVS.size();
        System.out.println("serv ");
        if (service == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucun service n'a été sélectionner "));
            veri3 = true;
        }
        if (formObjectAVS.getDateDebutAffectation() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de début d'affectation n'a pas été renseigner"));
            veri4 = true;
        }

        if (veri3 == false && veri4 == false) {

            if (formObjectAVS.getDateFinAffectation() == null) {
                if (nb != 0) {
                    for (int i = 0; i <= nb - 1; i++) {
                        if (listeControleAVS.get(i).getIdVehicule().getIdVehicule().equals(vehicule.getIdVehicule())
                                && (listeControleAVS.get(i).getDateFinAffectation() == null || listeControleAVS.get(i).getDateFinAffectation().compareTo(formObjectAVS.getDateDebutAffectation()) == 1)) {
                            veri = true;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule est déjà affecter à un service "));
                        }
                    }
//                for (int i = 0; i <= nb - 1; i++) {
//                    if (listeControleAVS.get(i).getIdService().getIdService().equals(formObjectAVS.getIdService().getIdService())
//                            && (listeControleAVS.get(i).getDateFinAffectation() == null || listeControleAVS.get(i).getDateFinAffectation().compareTo(formObjectAVS.getDateDebutAffectation()) == 1)) {
//                        veri2 = true;
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce responsable est déjà affecter à un véhicule "));
//                    }
//                }
                }
            } else {
                if (formObjectAVS.getDateDebutAffectation().compareTo(formObjectAVS.getDateFinAffectation()) == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de fin affectation ne peut pas être inférieur à la date de début affectation  "));
                    veri = true;
                } else if (formObjectAVS.getDateDebutAffectation().compareTo(formObjectAVS.getDateFinAffectation()) == 0) {
                    veri = true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de fin affectation ne peut pas être égale à la date de début affectation "));
                } else {
                    if (nb != 0) {
                        for (int i = 0; i <= nb - 1; i++) {
                            System.out.println("vehi " + vehicule.getIdVehicule());
                            if (listeControleAVS.get(i).getIdVehicule().getIdVehicule().equals(vehicule.getIdVehicule())
                                    && (listeControleAVS.get(i).getDateFinAffectation() == null || listeControleAVS.get(i).getDateFinAffectation().compareTo(formObjectAVS.getDateDebutAffectation()) == 1)) {
                                veri = true;
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule est déjà affecter à un service "));
                            }
                        }
//                    for (int i = 0; i <= nb - 1; i++) {
//                        if (listeControleACV.get(i).getIdChauffeur().getIdChauffeur().equals(formObjectACV.getIdChauffeur().getIdChauffeur())
//                                && (listeControleACV.get(i).getDateFinAffectation() == null || listeControleACV.get(i).getDateFinAffectation().compareTo(formObjectACV.getDateDebutAffectation()) == 1)) {
//                            veri2 = true;
//                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce responsable est déjà affecter à un véhicule "));
//                        }
//                    }
                    }

                }
            }

            if (veri == false && veri2 == false) {
                Ajax.oncomplete("PF('confirmerEnregistrementAVS').show()");
            }
        }
        //rafraichir();
    }

    public void confirmerSuppressionAVS() {
        if (selectObjectAVS == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionAVS').show()");
        }
    }

    public void ajouterChauffeur() {
        String comparaison = "";
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeChauffeur.size();
            if (i == 0) {
                code = "CHA01";
            } else if (i <= 8) {
                code = listeChauffeur.get(i - 1).getIdChauffeur();
                code = code.substring(4);
                ii = Integer.parseInt(code) + 1;
                code = "CHA0" + Integer.toString(ii);
            } else {
                code = listeChauffeur.get(i - 1).getIdChauffeur();
                code = code.substring(3);
                ii = Integer.parseInt(code) + 1;
                code = "CHA" + Integer.toString(ii);
            }
            formObjectCh.setIdChauffeur(code);
            boolean veri1 = false, veri2 = false, veri3 = false, veri4 = false, veri5 = false;

            if (nom.trim().length() == 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Le nom ne peut être vide"));
            } else {
                veri1 = true;
            }

            if (prenom.trim().length() == 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Le prénom ne peut être vide"));
            } else {
                veri2 = true;
            }

            if (contact.trim().length() != 0) {
                if (contact.trim().length() == 8) {
                    try {
                        int conversion = Integer.parseInt(contact.trim());
                        veri3 = true;
                    } catch (NumberFormatException nfe) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le contact n'est pas valide "));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le contact n'est pas valide "));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le contact n'est pas renseigner "));
            }
            int nb = listeControleChauffeur.size();
            if (veri1 == true && veri2 == true && veri3 == true) {

                String pre = prenom.trim();
                int n = pre.length();
                String premiere = pre.substring(0, 1).toUpperCase();
                prenom = premiere + pre.substring(1, n);

                if (nb != 0) {
                    for (int p = 0; p <= nb - 1; p++) {
                        if (listeControleChauffeur.get(p).getNomChauffeur().equals(nom.trim().toUpperCase()) && listeControleChauffeur.get(p).getPrenomChauffeur().equals(prenom)) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le nom et le prénom saisies existe déja dans la base "));
                            veri4 = true;
                        }
                    }
                }
            } else {
                veri4 = true;
            }

            if (veri4 == false) {
                formObjectCh.setNomChauffeur(nom.trim().toUpperCase());
                formObjectCh.setPrenomChauffeur(prenom);
                formObjectCh.setContactChauffeur(contact.trim());
                this.chauffeurService.ajouter(formObjectCh);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Enrégistrement effectuée avec succès "));
                nom = "";
                prenom = "";
                contact = "";
                listeChauffeur.add(formObjectCh);
                listeControleChauffeur.add(formObjectCh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerChauffeur() {
        ajouterChauffeur();
        formObjectCh = new chauffeurs();
        rafraichir();
        //Ajax.oncomplete("PF('marqueDlg').hide()");
    }

    public void ajouterService() {
        String comparaison = "";
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeService.size();
            if (i == 0) {
                code = "SER01";
            } else if (i <= 8) {
                code = listeService.get(i - 1).getIdService();
                code = code.substring(4);
                ii = Integer.parseInt(code) + 1;
                code = "SER0" + Integer.toString(ii);
            } else {
                code = listeService.get(i - 1).getIdService();
                code = code.substring(3);
                ii = Integer.parseInt(code) + 1;
                code = "SER" + Integer.toString(ii);
            }
            formObjectSer.setIdService(code);
            boolean veri1 = false, veri4 = false;

            if (formObjectSer.getLibelleService().trim().length() == 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Le libellé ne peut être vide"));
            } else {
                veri1 = true;
            }

            int nb = listeControleService.size();
            if (veri1 == true) {
                if (nb != 0) {
                    for (int p = 0; p <= nb - 1; p++) {
                        if (listeControleService.get(p).getLibelleService().equals(formObjectSer.getLibelleService().trim().toUpperCase())) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le libellé saisies existe déja dans la base "));
                            veri4 = true;
                        }
                    }
                }
            } else {
                veri4 = true;
            }

            if (veri4 == false) {
                String insertion = formObjectSer.getLibelleService().trim().toUpperCase();
                formObjectSer.setLibelleService(insertion);
                this.servicesService.ajouter(formObjectSer);
                listeService.add(formObjectSer);
                listeControleService.add(formObjectSer);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Enrégistrement effectuée avec succès "));
                //formObjectSer = new services();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerService() {
        ajouterService();
        formObjectSer = new services();
        //Ajax.oncomplete("PF('marqueDlg').hide()");
    }

    public void listeAffectationF() {
        this.listeAffectationFiltre = new ArrayList();
        vehicule = recuparation.getVehicule();
        int nb = listeAffectation.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeAffectation.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())) {
                    listeAffectationFiltre.add(listeAffectation.get(i));
                }
            }
        }
    }

    public void listeAffectationAVCF() {
        this.listeAffectationAVCFiltre = new ArrayList();
        vehicule = recuparation.getVehicule();
        int nb = listeAffectationACV.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeAffectationACV.get(i).getIdVehicule() != null) {
                    if (listeAffectationACV.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())) {
                        listeAffectationAVCFiltre.add(listeAffectationACV.get(i));
                    }
                }

            }
        }
    }

    public void listeAffectationAVSF() {
        this.listeAffectationAVSFiltre = new ArrayList();
        vehicule = recuparation.getVehicule();
        int nb = listeAffectationAVS.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeAffectationAVS.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())) {
                    listeAffectationAVSFiltre.add(listeAffectationAVS.get(i));
                }
            }
        }
    }

    
    
    public List<EtatRatioConsommation> construireListeRatio(List<Object[]> liste) {
        List<EtatRatioConsommation> listeRatio = new ArrayList();
        EtatRatioConsommation ratio;
        for (Object[] ra : liste) {
            ratio = new EtatRatioConsommation(ra);
            listeRatio.add(ratio);
        }
        return listeRatio;
    }
    
    
    public void estimation(){
        int comparaison = 0;
        BigDecimal comparaison1 = new BigDecimal(comparaison);
        boolean veri1=false, veri2=false, veri3=false;
        if(typeVehicule == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez selectionner un type de véhicule "));
            veri1 = true;
        }
        
        if(kiloEstimer==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez renseigner le kilométrage prévu  "));
            veri2 = true;
        }
        if(veri2==false){
            if(kiloEstimer.compareTo(comparaison1)==-1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le kilométrage prévu ne peut être négatif "));
            veri3=true;
            }
        }
        
        if(veri1==false && veri3==false){
       
         List<EtatRatioConsommation> listeRatio = construireListeRatio(this.vehiculeService.recupererRatioConsommation(null));
         this.ListeEstimation = new ArrayList();
         int b=listeEstimationVehicule.size();
         if(b!=0){
             for (int i1=0;i1<=b-1;i1++){
                 if(listeEstimationVehicule.get(i1).getIdModele().getIdTypeVehicule().getLibelleTypeVehicule().equals(typeVehicule.getLibelleTypeVehicule())){
                     formEstimation = new estimation();
                     formEstimation.setImmatriculation(listeEstimationVehicule.get(i1).getImmatriculation());
                     formEstimation.setMarques(listeEstimationVehicule.get(i1).getIdModele().getIdMarque().getLibelleMarque());
                     
                     vehicule = listeEstimationVehicule.get(i1);
                     int comp = 0;
        BigDecimal compar = new BigDecimal(comp);
        BigDecimal kiloVidange;
                kiloVidange = vehicule.getIdModele().getParametreVidange().subtract(vehicule.getKilometrageVidange());
                int comp1 = -1;
                BigDecimal compar1 = new BigDecimal(comp1);
                if(kiloVidange.compareTo(compar)==-1){
                    m3="La dernière vidange prévu pour ce véhicule n'a pa été effectuer. Elle a effectuer "+kiloVidange.multiply(compar1)+" Km de plus.";
                } else {
                    m3=""+kiloVidange+"";
                }
                m1 = "Le véhicule " + vehicule.getImmatriculation() + " ne possède aucune assurance en cour de validiter. ";
                m2 = "Ce véhicule ne possède aucune visite technique en cour de validiter. ";
                int nb = listeFiche.size();
                if (nb != 0) {
                    for (int i = 0; i <= nb - 1; i++) {
                        System.out.println("etat " + listeFiche.get(i).getEtatFiche());
                        System.out.println("Liste vehicule " + listeFiche.get(i).getIdVehicule().getImmatriculation());
                        System.out.println("Vehicule " + vehicule.getImmatriculation());
                        System.out.println("Type fiche " + listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche());
                        if (listeFiche.get(i).getEtatFiche() == true && listeFiche.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE")) {
                            info1 = listeFiche.get(i);
                            System.out.println("vrai");
                            m1 = " " + dateToString(info1.getDateExpiration());
                        }
                        if (listeFiche.get(i).getEtatFiche() == true && listeFiche.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                                && listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("VISITE TECHNIQUE")) {
                            info2 = listeFiche.get(i);
                            m2 = " " + dateToString(info2.getDateExpiration()) + " ";
                        }
                    }
                }
                     
                  formEstimation.setDateExpirationAssurance(m1);
                  formEstimation.setDateExpirationVisitetechnique(m2);
                  formEstimation.setKilometrageVidange(m3);
                  boolean ok=false;
                  int t= listeRatio.size();
                  if(t!=0){
                      for (int a=0;a<=t-1;a++){
                          if(listeRatio.get(a).getVehicule().equals(vehicule.getImmatriculation())){
                              BigDecimal cal1 = new BigDecimal(listeRatio.get(a).getRatioConsommation());
                              BigDecimal cal;
                              cal= kiloEstimer.multiply(cal1);
                              formEstimation.setEsimationCarburant(cal);
                              ok=true;
                          }
                      }
                  }
                  if(ok==true){
                     ListeEstimation.add(formEstimation);
                  }
                  
                 }
             }
         }
        }
    }
    
    
    //getters et setters
    public affectationServiceBeanLocal getAffectationService() {
        return affectationService;
    }

    public void setAffectationService(affectationServiceBeanLocal affectationService) {
        this.affectationService = affectationService;
    }

    public affectations getFormObjectA() {
        return formObjectA;
    }

    public void setFormObjectA(affectations formObjectA) {
        this.formObjectA = formObjectA;
    }

    public affectations getSelectObjectA() {
        return selectObjectA;
    }

    public void setSelectObjectA(affectations selectObjectA) {
        this.selectObjectA = selectObjectA;
    }

    public affectations getSupressionA() {
        return supressionA;
    }

    public void setSupressionA(affectations supressionA) {
        this.supressionA = supressionA;
    }

    public affectations getModifA() {
        return modifA;
    }

    public void setModifA(affectations modifA) {
        this.modifA = modifA;
    }

    public List<affectations> getListeAffectation() {
        return listeAffectation;
    }

    public void setListeAffectation(List<affectations> listeAffectation) {
        this.listeAffectation = listeAffectation;
    }

    public List<affectations> getListeControleA() {
        return listeControleA;
    }

    public void setListeControleA(List<affectations> listeControleA) {
        this.listeControleA = listeControleA;
    }

    public affectationChauffeurVehiculeServiceBeanLocal getAffectationChauffeurVehiculeService() {
        return affectationChauffeurVehiculeService;
    }

    public void setAffectationChauffeurVehiculeService(affectationChauffeurVehiculeServiceBeanLocal affectationChauffeurVehiculeService) {
        this.affectationChauffeurVehiculeService = affectationChauffeurVehiculeService;
    }

    public affectationChauffeurVehicule getFormObjectACV() {
        return formObjectACV;
    }

    public void setFormObjectACV(affectationChauffeurVehicule formObjectACV) {
        this.formObjectACV = formObjectACV;
    }

    public affectationChauffeurVehicule getSelectObjectACV() {
        return selectObjectACV;
    }

    public void setSelectObjectACV(affectationChauffeurVehicule selectObjectACV) {
        this.selectObjectACV = selectObjectACV;
    }

    public affectationChauffeurVehicule getSupressionACV() {
        return supressionACV;
    }

    public void setSupressionACV(affectationChauffeurVehicule supressionACV) {
        this.supressionACV = supressionACV;
    }

    public affectationChauffeurVehicule getModifACV() {
        return modifACV;
    }

    public void setModifACV(affectationChauffeurVehicule modifACV) {
        this.modifACV = modifACV;
    }

    public List<affectationChauffeurVehicule> getListeAffectationACV() {
        return listeAffectationACV;
    }

    public void setListeAffectationACV(List<affectationChauffeurVehicule> listeAffectationACV) {
        this.listeAffectationACV = listeAffectationACV;
    }

    public List<affectationChauffeurVehicule> getListeControleACV() {
        return listeControleACV;
    }

    public void setListeControleACV(List<affectationChauffeurVehicule> listeControleACV) {
        this.listeControleACV = listeControleACV;
    }

    public chauffeursServiceBeanLocal getChauffeurService() {
        return chauffeurService;
    }

    public void setChauffeurService(chauffeursServiceBeanLocal chauffeurService) {
        this.chauffeurService = chauffeurService;
    }

    public chauffeurs getFormObjectCh() {
        return formObjectCh;
    }

    public void setFormObjectCh(chauffeurs formObjectCh) {
        this.formObjectCh = formObjectCh;
    }

    public chauffeurs getSelectObjectCh() {
        return selectObjectCh;
    }

    public void setSelectObjectCh(chauffeurs selectObjectCh) {
        this.selectObjectCh = selectObjectCh;
    }

    public chauffeurs getSupressionCh() {
        return supressionCh;
    }

    public void setSupressionCh(chauffeurs supressionCh) {
        this.supressionCh = supressionCh;
    }

    public chauffeurs getModifCh() {
        return modifCh;
    }

    public void setModifCh(chauffeurs modifCh) {
        this.modifCh = modifCh;
    }

    public List<chauffeurs> getListeChauffeur() {
        return listeChauffeur;
    }

    public void setListeChauffeur(List<chauffeurs> listeChauffeur) {
        this.listeChauffeur = listeChauffeur;
    }

    public List<chauffeurs> getListeControleChauffeur() {
        return listeControleChauffeur;
    }

    public void setListeControleChauffeur(List<chauffeurs> listeControleChauffeur) {
        this.listeControleChauffeur = listeControleChauffeur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public missionsServiceBeanLocal getMissionsService() {
        return missionsService;
    }

    public void setMissionsService(missionsServiceBeanLocal missionsService) {
        this.missionsService = missionsService;
    }

    public List<missions> getListeMission() {
        return listeMission;
    }

    public void setListeMission(List<missions> listeMission) {
        this.listeMission = listeMission;
    }

    public List<chauffeurs> getListeChauffeur2() {
        return listeChauffeur2;
    }

    public void setListeChauffeur2(List<chauffeurs> listeChauffeur2) {
        this.listeChauffeur2 = listeChauffeur2;
    }

    public chauffeurs getEnlever() {
        return enlever;
    }

    public void setEnlever(chauffeurs enlever) {
        this.enlever = enlever;
    }

    public vehicules getVehicule() {
        return vehicule;
    }

    public void setVehicule(vehicules vehicule) {
        this.vehicule = vehicule;
    }

    public affectationPK getIdAffectationMission() {
        return idAffectationMission;
    }

    public void setIdAffectationMission(affectationPK idAffectationMission) {
        this.idAffectationMission = idAffectationMission;
    }

    public chauffeurs getChof() {
        return chof;
    }

    public void setChof(chauffeurs chof) {
        this.chof = chof;
    }

    public missions getMis() {
        return mis;
    }

    public void setMis(missions mis) {
        this.mis = mis;
    }

    public List<vehicules> getListevehiculeTotal() {
        return listevehiculeTotal;
    }

    public void setListevehiculeTotal(List<vehicules> listevehiculeTotal) {
        this.listevehiculeTotal = listevehiculeTotal;
    }

    public affectationVehiculeSerServiceBeanLocal getAffectationServiceVehiculeService() {
        return affectationServiceVehiculeService;
    }

    public void setAffectationServiceVehiculeService(affectationVehiculeSerServiceBeanLocal affectationServiceVehiculeService) {
        this.affectationServiceVehiculeService = affectationServiceVehiculeService;
    }

    public affectationVehiculeService getFormObjectAVS() {
        return formObjectAVS;
    }

    public void setFormObjectAVS(affectationVehiculeService formObjectAVS) {
        this.formObjectAVS = formObjectAVS;
    }

    public affectationVehiculeService getSelectObjectAVS() {
        return selectObjectAVS;
    }

    public void setSelectObjectAVS(affectationVehiculeService selectObjectAVS) {
        this.selectObjectAVS = selectObjectAVS;
    }

    public affectationVehiculeService getSupressionAVS() {
        return supressionAVS;
    }

    public void setSupressionAVS(affectationVehiculeService supressionAVS) {
        this.supressionAVS = supressionAVS;
    }

    public affectationVehiculeService getModifAVS() {
        return modifAVS;
    }

    public void setModifAVS(affectationVehiculeService modifAVS) {
        this.modifAVS = modifAVS;
    }

    public List<affectationVehiculeService> getListeAffectationAVS() {
        return listeAffectationAVS;
    }

    public void setListeAffectationAVS(List<affectationVehiculeService> listeAffectationAVS) {
        this.listeAffectationAVS = listeAffectationAVS;
    }

    public List<affectationVehiculeService> getListeControleAVS() {
        return listeControleAVS;
    }

    public void setListeControleAVS(List<affectationVehiculeService> listeControleAVS) {
        this.listeControleAVS = listeControleAVS;
    }

    public String getId4() {
        return id4;
    }

    public void setId4(String id4) {
        this.id4 = id4;
    }

    public boolean isDesactivation() {
        return desactivation;
    }

    public void setDesactivation(boolean desactivation) {
        this.desactivation = desactivation;
    }

    public serviceServiceBeanLocal getServicesService() {
        return servicesService;
    }

    public void setServicesService(serviceServiceBeanLocal servicesService) {
        this.servicesService = servicesService;
    }

    public services getFormObjectSer() {
        return formObjectSer;
    }

    public void setFormObjectSer(services formObjectSer) {
        this.formObjectSer = formObjectSer;
    }

    public services getSelectObjectSer() {
        return selectObjectSer;
    }

    public void setSelectObjectSer(services selectObjectSer) {
        this.selectObjectSer = selectObjectSer;
    }

    public services getSupressionSer() {
        return supressionSer;
    }

    public void setSupressionSer(services supressionSer) {
        this.supressionSer = supressionSer;
    }

    public services getModifSer() {
        return modifSer;
    }

    public void setModifSer(services modifSer) {
        this.modifSer = modifSer;
    }

    public services getEnleverSer() {
        return enleverSer;
    }

    public void setEnleverSer(services enleverSer) {
        this.enleverSer = enleverSer;
    }

    public List<services> getListeService() {
        return listeService;
    }

    public void setListeService(List<services> listeService) {
        this.listeService = listeService;
    }

    public List<services> getListeControleService() {
        return listeControleService;
    }

    public void setListeControleService(List<services> listeControleService) {
        this.listeControleService = listeControleService;
    }

    public vehicules getVeh1() {
        return veh1;
    }

    public void setVeh1(vehicules veh1) {
        this.veh1 = veh1;
    }

    public ficheServiceBeanLocal getFicheService() {
        return ficheService;
    }

    public void setFicheService(ficheServiceBeanLocal ficheService) {
        this.ficheService = ficheService;
    }

    public fiche getFormObject() {
        return formObject;
    }

    public void setFormObject(fiche formObject) {
        this.formObject = formObject;
    }

    public fiche getInfo1() {
        return info1;
    }

    public void setInfo1(fiche info1) {
        this.info1 = info1;
    }

    public fiche getInfo2() {
        return info2;
    }

    public void setInfo2(fiche info2) {
        this.info2 = info2;
    }

    public List<fiche> getListeFiche() {
        return listeFiche;
    }

    public void setListeFiche(List<fiche> listeFiche) {
        this.listeFiche = listeFiche;
    }

    public Date getDateduJour() {
        return dateduJour;
    }

    public void setDateduJour(Date dateduJour) {
        this.dateduJour = dateduJour;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public services getService() {
        return service;
    }

    public void setService(services service) {
        this.service = service;
    }

//    public operationVehiculeMBean getRecuparation() {
//        return recuparation;
//    }
//
//    public void setRecuparation(operationVehiculeMBean recuparation) {
//        this.recuparation = recuparation;
//    }

    public AffectationGeneralMBean getRecuparation() {
        return recuparation;
    }

    public void setRecuparation(AffectationGeneralMBean recuparation) {
        this.recuparation = recuparation;
    }
    
    public List<affectations> getListeAffectationFiltre() {
        return listeAffectationFiltre;
    }

    public void setListeAffectationFiltre(List<affectations> listeAffectationFiltre) {
        this.listeAffectationFiltre = listeAffectationFiltre;
    }

    public List<affectationChauffeurVehicule> getListeAffectationAVCFiltre() {
        return listeAffectationAVCFiltre;
    }

    public void setListeAffectationAVCFiltre(List<affectationChauffeurVehicule> listeAffectationAVCFiltre) {
        this.listeAffectationAVCFiltre = listeAffectationAVCFiltre;
    }

    public List<affectationVehiculeService> getListeAffectationAVSFiltre() {
        return listeAffectationAVSFiltre;
    }

    public void setListeAffectationAVSFiltre(List<affectationVehiculeService> listeAffectationAVSFiltre) {
        this.listeAffectationAVSFiltre = listeAffectationAVSFiltre;
    }

    public vehicules getFormVehicule() {
        return formVehicule;
    }

    public void setFormVehicule(vehicules formVehicule) {
        this.formVehicule = formVehicule;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public boolean isAfm1() {
        return afm1;
    }

    public void setAfm1(boolean afm1) {
        this.afm1 = afm1;
    }

    public boolean isAfm2() {
        return afm2;
    }

    public void setAfm2(boolean afm2) {
        this.afm2 = afm2;
    }

    public BigDecimal getVidange() {
        return vidange;
    }

    public void setVidange(BigDecimal vidange) {
        this.vidange = vidange;
    }

    public BigDecimal getVidangeTotal() {
        return vidangeTotal;
    }

    public void setVidangeTotal(BigDecimal vidangeTotal) {
        this.vidangeTotal = vidangeTotal;
    }

    public String getM3() {
        return m3;
    }

    public void setM3(String m3) {
        this.m3 = m3;
    }

    public String getInfoTitre() {
        return infoTitre;
    }

    public void setInfoTitre(String infoTitre) {
        this.infoTitre = infoTitre;
    }

    public List<estimation> getListeEstimation() {
        return ListeEstimation;
    }

    public void setListeEstimation(List<estimation> ListeEstimation) {
        this.ListeEstimation = ListeEstimation;
    }

    public estimation getFormEstimation() {
        return formEstimation;
    }

    public void setFormEstimation(estimation formEstimation) {
        this.formEstimation = formEstimation;
    }

    public List<vehicules> getListeEstimationVehicule() {
        return listeEstimationVehicule;
    }

    public void setListeEstimationVehicule(List<vehicules> listeEstimationVehicule) {
        this.listeEstimationVehicule = listeEstimationVehicule;
    }

    public BigDecimal getKiloEstimer() {
        return kiloEstimer;
    }

    public void setKiloEstimer(BigDecimal kiloEstimer) {
        this.kiloEstimer = kiloEstimer;
    }

    public typeVehicules getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(typeVehicules typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public typeVehiculeServiceBeanLocal getTypeVehiculeService() {
        return typeVehiculeService;
    }

    public void setTypeVehiculeService(typeVehiculeServiceBeanLocal typeVehiculeService) {
        this.typeVehiculeService = typeVehiculeService;
    }

    public List<typeVehicules> getListeTypeVehicule() {
        return listeTypeVehicule;
    }

    public void setListeTypeVehicule(List<typeVehicules> listeTypeVehicule) {
        this.listeTypeVehicule = listeTypeVehicule;
    }
    
    
    
}
