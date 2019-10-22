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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.affectations;
import tg.artp.geParc.entities.chargerCarburant;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.report.ConteneurRequeteManagedBean;
import tg.artp.geParc.report.EtatUtils;
import tg.artp.geParc.report.ExportManagedBean;
import tg.artp.geParc.report.files.EtatRatioConsommation;
import tg.artp.geParc.services.affectationServiceBeanLocal;
import tg.artp.geParc.services.chargerCarburantServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class ChargerCarburantMBean implements Serializable {

    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;

    @EJB
    private chargerCarburantServiceBeanLocal chargerCarburantService;
    private chargerCarburant formObject, selectObject, supression, modif;
    private List<chargerCarburant> listeChargerCarburant, listeControle;

    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private vehicules vehicule;
    private List<vehicules> listeVehicule, listeVehiculeTotal;

    @EJB
    private affectationServiceBeanLocal affectationService;
    private List<affectations> listeAffectation;

    private String message, msg;
    private String id = "CHA_CA";
    private String code = "";
    int index;
    private boolean desactiver = true;

    private Date dateDeChargement;
    private BigDecimal quantiteCharger, kilometrageAvantChargement, vidange, vidangeTotal;

    public ChargerCarburantMBean() {
        this.formObject = new chargerCarburant();
    }

    @ManagedProperty(value = "#{OperationVehicule}")
    private operationVehiculeMBean recuparation;

    @PostConstruct
    public void chargerElement() {
        this.listeChargerCarburant = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeChargerCarburant = this.chargerCarburantService.selectionnerTout();
        this.listeControle = this.chargerCarburantService.selectionnerTout();

        this.listeVehicule = new ArrayList();
        this.listeVehiculeTotal = new ArrayList();
        this.listeVehicule = this.vehiculeService.selectionnerTout();

        int nb = listeVehiculeTotal.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeVehiculeTotal.get(i).isEtatVehicule() == true) {
                    listeVehicule.add(listeVehiculeTotal.get(i));
                } else {

                }
            }
        }

        this.listeAffectation = new ArrayList();
        this.listeAffectation = this.affectationService.selectionnerTout();

    }

    public void visualiser() {
        Ajax.oncomplete("PF('visualiserListeChargementCarburantDlg').show()");
    }

    public void rowSelect() {
        formObject = selectObject;
        supression = selectObject;

        this.index = this.listeChargerCarburant.indexOf(this.selectObject);
        modif = selectObject;
        desactiver = false;
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

    public void suppression() {
        try {
            chargerCarburantService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier() {
        try {
            chargerCarburantService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouter() {
        vehicule = recuparation.getVehicule();
        System.out.println("vehicule " + vehicule.getImmatriculation());
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeChargerCarburant.size();
            if (i == 0) {
                code = id + "01";
            } else if (i <= 8) {
                code = listeChargerCarburant.get(i - 1).getIdChargerCarburant();
                code = code.substring(7);
                System.out.println("code " + code);
                ii = Integer.parseInt(code) + 1;
                code = id + "0" + Integer.toString(ii);
            } else {
                code = listeChargerCarburant.get(i - 1).getIdChargerCarburant();
                code = code.substring(6);
                ii = Integer.parseInt(code) + 1;
                code = id + Integer.toString(ii);
            }
            vidange = kilometrageAvantChargement.subtract(vehicule.getKilometrageActuel());
            vidangeTotal = vehicule.getKilometrageActuel().add(vidange);
            vehicule.setKilometrageVidange(vidangeTotal);
            //this.kilometrageAvantChargement = vehicule.getKilometrageActuel();
            formObject.setIdChargerCarburant(code);
            formObject.setDateChargerCarburant(dateDeChargement);
            //formObject.setQuantiteCharger(quantiteCharger);
            formObject.setKilometrageAvantChargement(kilometrageAvantChargement);
            formObject.setIdVehicule(vehicule);
            vehicule.setKilometrageActuel(kilometrageAvantChargement);
            this.chargerCarburantService.ajouter(formObject);
            this.vehiculeService.modifier(vehicule);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrer() {
        if (selectObject != null) {
            modifier();
            this.rafraichir();

        } else {
            ajouter();
            message = "Enregistrement effectué avec succès";
            Ajax.oncomplete("PF('succesOperationChargerCarburant').show()");
            this.rafraichir();
        }
    }

    public void supprimer() {
        if (selectObject == null) {
            return;
        }
        try {
            suppression();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationChargerCarburant').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index = 0;
    }

    public void rafraichir() {
        this.chargerElement();
        this.effacer();
        desactiver = true;
        recuparation.rafraichir();
    }

    public void effacer() {
        formObject = new chargerCarburant();
        dateDeChargement = null;
        vehicule = null;
        quantiteCharger = null;
        code = "";
        selectObject = null;
        kilometrageAvantChargement = null;
//        recuparation.chargerElement();
    }

    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new chargerCarburant();
    }

    public void confirmerEnregistrement() {
        vehicule = recuparation.getVehicule();
        int nb = listeAffectation.size();
        int comp = 0;
        BigDecimal compar = new BigDecimal(comp);
        boolean veri = false, veri2 = false, veri3 = false;
        System.out.println("chargement");
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeAffectation.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation()) && listeAffectation.get(i).getKilometrageArrive() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Impossible de faire un rechargement de carburant. Ce véhicule est en mission "));
                    veri = true;
                }
            }
        }
        if (formObject.getQuantiteCharger().compareTo(compar) == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La quantité charger ne peut être négatif"));
            veri3 = true;
        }
        if (vehicule.getKilometrageActuel().compareTo(kilometrageAvantChargement) == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Le kilometrage saisie ne peut être inférieur au dernier kilometrage renseigner"));
            veri2 = true;
        }

        if (veri == false && veri2 == false && veri3 == false) {
            Ajax.oncomplete("PF('confirmerEnregistrementChargerCarburant').show()");
        }

    }

    public void confirmerSuppression() {
        if (selectObject == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionChargerCarburant').show()");
        }
    }

    public List<EtatRatioConsommation> construireListeRatio(List<Object[]> liste) {
        List<EtatRatioConsommation> listeRatio = new ArrayList();
        EtatRatioConsommation ratio;
        System.out.println("ok");
        for (Object[] ra : liste) {
            System.out.println("vrai");
            ratio = new EtatRatioConsommation(ra);
            listeRatio.add(ratio);
        }
        return listeRatio;
    }

    public void imprimerListeRatio() {
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            System.out.println("impression");
            this.conteneur.setParametres(param);
            this.conteneur.setListeEtat(construireListeRatio(this.vehiculeService.recupererRatioConsommation(null)));
//            this.conteneur.setListeEtat((vehiculeService.recupererRatioConsommation(null)));
            this.conteneur.setCompileFileName("rationConsommationVehicule");
            EtatUtils.mettresParametresExportAValeurParDefault(conteneur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //getters et setters
    public chargerCarburantServiceBeanLocal getChargerCarburantService() {
        return chargerCarburantService;
    }

    public void setChargerCarburantService(chargerCarburantServiceBeanLocal chargerCarburantService) {
        this.chargerCarburantService = chargerCarburantService;
    }

    public chargerCarburant getFormObject() {
        return formObject;
    }

    public void setFormObject(chargerCarburant formObject) {
        this.formObject = formObject;
    }

    public chargerCarburant getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(chargerCarburant selectObject) {
        this.selectObject = selectObject;
    }

    public chargerCarburant getSupression() {
        return supression;
    }

    public void setSupression(chargerCarburant supression) {
        this.supression = supression;
    }

    public chargerCarburant getModif() {
        return modif;
    }

    public void setModif(chargerCarburant modif) {
        this.modif = modif;
    }

    public List<chargerCarburant> getListeChargerCarburant() {
        return listeChargerCarburant;
    }

    public void setListeChargerCarburant(List<chargerCarburant> listeChargerCarburant) {
        this.listeChargerCarburant = listeChargerCarburant;
    }

    public List<chargerCarburant> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<chargerCarburant> listeControle) {
        this.listeControle = listeControle;
    }

    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public vehicules getVehicule() {
        return vehicule;
    }

    public void setVehicule(vehicules vehicule) {
        this.vehicule = vehicule;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    public Date getDateDeChargement() {
        return dateDeChargement;
    }

    public void setDateDeChargement(Date dateDeChargement) {
        this.dateDeChargement = dateDeChargement;
    }

    public BigDecimal getQuantiteCharger() {
        return quantiteCharger;
    }

    public void setQuantiteCharger(BigDecimal quantiteCharger) {
        this.quantiteCharger = quantiteCharger;
    }

    public BigDecimal getKilometrageAvantChargement() {
        return kilometrageAvantChargement;
    }

    public void setKilometrageAvantChargement(BigDecimal kilometrageAvantChargement) {
        this.kilometrageAvantChargement = kilometrageAvantChargement;
    }

    public operationVehiculeMBean getRecuparation() {
        return recuparation;
    }

    public void setRecuparation(operationVehiculeMBean recuparation) {
        this.recuparation = recuparation;
    }

    public List<vehicules> getListeVehiculeTotal() {
        return listeVehiculeTotal;
    }

    public void setListeVehiculeTotal(List<vehicules> listeVehiculeTotal) {
        this.listeVehiculeTotal = listeVehiculeTotal;
    }

    public affectationServiceBeanLocal getAffectationService() {
        return affectationService;
    }

    public void setAffectationService(affectationServiceBeanLocal affectationService) {
        this.affectationService = affectationService;
    }

    public List<affectations> getListeAffectation() {
        return listeAffectation;
    }

    public void setListeAffectation(List<affectations> listeAffectation) {
        this.listeAffectation = listeAffectation;
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

    public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }

    public ExportManagedBean getExportManagedBean() {
        return exportManagedBean;
    }

    public void setExportManagedBean(ExportManagedBean exportManagedBean) {
        this.exportManagedBean = exportManagedBean;
    }

}
