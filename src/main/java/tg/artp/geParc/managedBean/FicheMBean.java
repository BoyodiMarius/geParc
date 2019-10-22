/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
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
import org.omnifaces.el.functions.Dates;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.fiche;
import tg.artp.geParc.entities.typeFiche;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.report.ConteneurRequeteManagedBean;
import tg.artp.geParc.report.EtatUtils;
import tg.artp.geParc.report.ExportManagedBean;
import tg.artp.geParc.report.files.EtatAssurance;
import tg.artp.geParc.services.ficheServiceBeanLocal;
import tg.artp.geParc.services.typeFicheServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class FicheMBean implements Serializable {

    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;

    @EJB
    private ficheServiceBeanLocal ficheService;
    private fiche formObject, selectObject, supression, modif;
    private List<fiche> listeFiche, listeControle, listeFicheFiltre1, listeFicheFiltre2, listeInter1, listeInter2;

    @EJB
    private typeFicheServiceBeanLocal typeFicheService;
    private typeFiche formTypeFiche, typeFiche;
    private List<typeFiche> listeTypeFiche;

    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private vehicules vehicule;
    private List<vehicules> listeVehicule, listeVehiculeTotal;

    private String message, msg;
    private String id = "FIC";
    private String code = "";
    int index;
    private boolean desactiver = true;

    private Date dateDelivrer, dateExpiration, dateduJour;

    public FicheMBean() {
        this.formObject = new fiche();
        this.formTypeFiche = new typeFiche();
    }

    @ManagedProperty(value = "#{OperationVehicule}")
    private operationVehiculeMBean recuparation;

    @PostConstruct
    public void chargerElement() {
        this.listeFiche = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeFiche = this.ficheService.selectionnerTout();
        this.listeControle = this.ficheService.selectionnerTout();

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

        this.listeTypeFiche = new ArrayList();
        this.listeTypeFiche = this.typeFicheService.selectionnerTout();

        this.listeVehicule = new ArrayList();
        this.listeVehiculeTotal = new ArrayList();
        this.listeVehiculeTotal = this.vehiculeService.selectionnerTout();

        int nb = listeVehiculeTotal.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeVehiculeTotal.get(i).isEtatVehicule() == true) {
                    listeVehicule.add(listeVehiculeTotal.get(i));
                }
            }
        }

    }

    public void rowSelect() {
        formObject = selectObject;
        supression = selectObject;
        typeFiche = selectObject.getIdTypeFiche();
        vehicule = selectObject.getIdVehicule();
        System.out.println("fiche " + typeFiche.getLibelleTypeFiche());
        System.out.println("vehicule " + vehicule.getImmatriculation());
        dateDelivrer = selectObject.getDateDelivrer();
        dateExpiration = selectObject.getDateExpiration();
        this.index = this.listeFiche.indexOf(this.selectObject);
        typeFiche.setLibelleTypeFiche(selectObject.getIdTypeFiche().getLibelleTypeFiche());
        modif = selectObject;
        desactiver = false;
        System.out.println("avant "+listeControle.size());
        this.listeControle.remove(index);
        System.out.println("Apres "+listeControle.size());
        if(selectObject.getIdTypeFiche().getLibelleTypeFiche().equals("VISITE TECHNIQUE") && selectObject.getEtatFiche()==true){
            dateDelivrer = selectObject.getDateDelivrer();
            dateExpiration = selectObject.getDateExpiration();
            Ajax.oncomplete("PF('visiteTechniqueDlg').show()");
            System.out.println("delivre " + dateDelivrer);
            System.out.println("expire " + dateExpiration);
        } else  if(selectObject.getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE") && selectObject.getEtatFiche()==true){
            dateDelivrer = selectObject.getDateDelivrer();
            dateExpiration = selectObject.getDateExpiration(); 
            Ajax.oncomplete("PF('assuranceDlg').show()");
            System.out.println("delivre " + dateDelivrer);
            System.out.println("expire " + dateExpiration);
        }
        
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
            ficheService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier() {
        try {
            selectObject.setDateDelivrer(dateDelivrer);
            selectObject.setDateExpiration(dateExpiration);
            selectObject.setEtatFiche(true);
            selectObject.setIdTypeFiche(typeFiche);
            selectObject.setIdVehicule(vehicule);
            ficheService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void selection() {
        this.rafraichir();
        try {

            this.listeFiche = new ArrayList();
            this.listeFicheFiltre1 = new ArrayList();
            this.listeFicheFiltre2 = new ArrayList();
            this.listeFiche = this.ficheService.selectionnerTout();

            int nb = listeFiche.size();
            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    //System.out.println("fiche"+i+" : "+listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche());
                    //System.out.println("vehicule"+i+" : "+listeFiche.get(i).getIdVehicule().getImmatriculation());
                    if (listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE")) {
                        System.out.println("fiche" + i + " : " + listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche());
                        System.out.println("vehicule" + i + " : " + listeFiche.get(i).getIdVehicule().getImmatriculation());
                        listeFicheFiltre1.add(listeFiche.get(i));
                    } else {
                        listeFicheFiltre2.add(listeFiche.get(i));
                    }
                }
            }
            
            int nb2= listeFicheFiltre1.size();
        if(nb2!=0){
            listeInter1 = new ArrayList();
            listeInter2 = new ArrayList();
            for(int i=0;i<=nb2-1;i++){
               if(listeFicheFiltre1.get(i).getEtatFiche()==true){
                   listeInter1.add(listeFicheFiltre1.get(i));
               } else {
                   listeInter2.add(listeFicheFiltre1.get(i));
               }
            }
            listeFicheFiltre1.clear();
            listeFicheFiltre1.addAll(listeInter1);
            listeFicheFiltre1.addAll(listeInter2);
        }
        
            
            
            int nb3= listeFicheFiltre2.size();
        if(nb3!=0){
            listeInter1 = new ArrayList();
            listeInter2 = new ArrayList();
            for(int i=0;i<=nb3-1;i++){
               if(listeFicheFiltre2.get(i).getEtatFiche()==true){
                   listeInter1.add(listeFicheFiltre2.get(i));
               } else {
                   listeInter2.add(listeFicheFiltre2.get(i));
               }
            }
            listeFicheFiltre2.clear();
            listeFicheFiltre2.addAll(listeInter1);
            listeFicheFiltre2.addAll(listeInter2);
        }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void visualisationAssurance() {
        this.rafraichir();
        try {
            selection();
            Ajax.oncomplete("PF('visualiserListeAssuranceVisiteDlg').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visualisationVisiteTechnique() {
        this.rafraichir();
        try {
            selection();
            Ajax.oncomplete("PF('visualiserListeAssuranceVisiteDlg1').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouter() {

        try {
            int i = 0;
            int ii = 0;
            //
            i = listeFiche.size();
            if (i == 0) {
                code = id + "01";
            } else if (i <= 8) {
                code = listeFiche.get(i - 1).getIdFiche();
                code = code.substring(4);
                ii = Integer.parseInt(code) + 1;
                code = id + "0" + Integer.toString(ii);
            } else {
                code = listeFiche.get(i - 1).getIdFiche();
                code = code.substring(3);
                ii = Integer.parseInt(code) + 1;
                code = id + Integer.toString(ii);
            }
            System.out.println("typefiche 2" + typeFiche.getLibelleTypeFiche());
            formObject.setIdFiche(code);
            formObject.setDateDelivrer(dateDelivrer);
            formObject.setDateExpiration(dateExpiration);
            formObject.setEtatFiche(true);
            formObject.setIdTypeFiche(typeFiche);
            formObject.setIdVehicule(vehicule);
            this.ficheService.ajouter(formObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrer() {
        if (selectObject != null) {
            modifier();
            message = "Modification effectué avec succès";
        } else {
            ajouter();
            message = "Enregistrement effectué avec succès";
            this.rafraichir();
        }
            dateDelivrer = null;
            dateExpiration = null;
            Ajax.oncomplete("PF('assuranceDlg').hide()");
            Ajax.oncomplete("PF('visiteTechniqueDlg').hide()");
            Ajax.oncomplete("PF('succesOperationFiche').show()");
               
    }

    public void supprimer() {
        if (selectObject == null) {
            return;
        }
        try {
            suppression();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationFiche').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index = 0;
    }

    public void rafraichir() {
        System.out.println("vrai");
        this.chargerElement();
        this.effacer();
        desactiver = true;
        recuparation.rafraichir();
    }

    public void effacer() {
        System.out.println("new");
        formObject = new fiche();
        dateDelivrer = null;
        dateExpiration = null;
        selectObject = null;
    }
    
     public void effacerFormulaire() {
        System.out.println("newff");
        formObject = new fiche();
        dateDelivrer = null;
        dateExpiration = null;
        vehicule = recuparation.getVehicule();
        System.out.println("vehicule "+vehicule.getImmatriculation());
        if(selectObject!=null){
            Ajax.oncomplete("PF('visiteTechniqueDlg').hide()");
            Ajax.oncomplete("PF('assuranceDlg').hide()");
        }
        selectObject = null;
    }

    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new fiche();
    }

    public void confirmerEnregistrement() {
       if(selectObject==null){
           String idTypeFiche = recuparation.getTypeFiche();
           typeFiche = typeFicheService.selectionner(idTypeFiche);
           vehicule = recuparation.getVehicule();
       } else {
           typeFiche = selectObject.getIdTypeFiche();
           vehicule = selectObject.getIdVehicule();
       }
        System.out.println("typefiche 1 " + typeFiche.getLibelleTypeFiche());
        
        boolean verif = false;
        if(vehicule == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Veuillez  choisir un véhicule"));
            verif = true;
        }
        if(verif==false){
        if (dateDelivrer == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Veuillez  renseigner la date de délivraison "));
        } else if (dateExpiration == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Veuillez  renseigner la date d'expiration "));
        } else {

            int nb = listeControle.size();
            boolean veri = false;
            if (dateDelivrer.compareTo(dateExpiration) == 0) {
                veri = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de délivraison ne peut pas être égale à la date d'expiration"));
            } else if (dateDelivrer.compareTo(dateExpiration) == 1) {
                veri = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de délivraison ne peut pas être supérieur à la date d'expiration "));
            }

            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    System.out.println("controle fiche " + listeControle.get(i).getIdTypeFiche().getLibelleTypeFiche());
                    System.out.println("fciche " + typeFiche.getLibelleTypeFiche());
                    System.out.println("controle veh " + listeControle.get(i).getIdVehicule().getImmatriculation());
                    System.out.println("vehicule " + vehicule.getImmatriculation());
//                    if (typeFiche.getLibelleTypeFiche().equals(listeControle.get(i).getIdTypeFiche().getLibelleTypeFiche()) && vehicule.getImmatriculation().equals(listeControle.get(i).getIdVehicule().getImmatriculation()) && dateDelivrer.compareTo(listeControle.get(i).getDateExpiration()) == -1) {
//                        veri = true;
//                        System.out.println("ok");
//
//                        //System.out.println("");
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule possède une fiche en cours de validiter "));
//                    }
                    if (typeFiche.getLibelleTypeFiche().equals(listeControle.get(i).getIdTypeFiche().getLibelleTypeFiche()) 
                        && vehicule.getImmatriculation().equals(listeControle.get(i).getIdVehicule().getImmatriculation()) 
                        && listeControle.get(i).getEtatFiche()==true) {
                        veri = true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Ce véhicule possède une fiche en cours de validiter "));
                    }
                }
            }

            if (veri == false) {
                Ajax.oncomplete("PF('confirmerEnregistrementFiche').show()");
            }

        }
        }

        //rafraichir();
    }

    public void confirmerSuppression() {
        if (selectObject == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionFiche').show()");
        }
    }

    public List<EtatAssurance> construireListeFiche(List<fiche> liste) {
        List<EtatAssurance> listeEtat = new ArrayList();
        EtatAssurance etat;
        String titre="";
        for (fiche ev : liste) {
            //System.out.println("construction");
            etat = new EtatAssurance(ev.getIdVehicule().getImmatriculation(), dateToString(ev.getDateDelivrer()),
                    dateToString(ev.getDateExpiration()), "0", (ev.getEtatFiche() == true ? "ACTIF" : "EXPIRE"), titre);
            listeEtat.add(etat);
        }
        return listeEtat;
    }

    public void imprimerListeFicheAssurance() {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> mape = new HashMap();
        try {
            selection();
            System.out.println("impression");
            param.put("titre", "Liste des assurances");
            this.conteneur.setParametres(param);
            this.conteneur.setListeEtat((construireListeFiche(listeFicheFiltre1)));
            this.conteneur.setCompileFileName("listeAssurance");
            EtatUtils.mettresParametresExportAValeurParDefault(conteneur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimerListeFicheVisiteTechnique() {
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            selection();
            System.out.println("size " + listeFicheFiltre2.size());
            param.put("titre", "Liste des visites techniques");
            this.conteneur.setParametres(param);
            this.conteneur.setListeEtat((construireListeFiche(listeFicheFiltre2)));
            this.conteneur.setCompileFileName("listeAssurance");
            EtatUtils.mettresParametresExportAValeurParDefault(conteneur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     //getters et setters
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

    public fiche getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(fiche selectObject) {
        this.selectObject = selectObject;
    }

    public fiche getSupression() {
        return supression;
    }

    public void setSupression(fiche supression) {
        this.supression = supression;
    }

    public fiche getModif() {
        return modif;
    }

    public void setModif(fiche modif) {
        this.modif = modif;
    }

    public List<fiche> getListeFiche() {
        return listeFiche;
    }

    public void setListeFiche(List<fiche> listeFiche) {
        this.listeFiche = listeFiche;
    }

    public List<fiche> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<fiche> listeControle) {
        this.listeControle = listeControle;
    }

    public typeFicheServiceBeanLocal getTypeFicheService() {
        return typeFicheService;
    }

    public void setTypeFicheService(typeFicheServiceBeanLocal typeFicheService) {
        this.typeFicheService = typeFicheService;
    }

    public typeFiche getFormTypeFiche() {
        return formTypeFiche;
    }

    public void setFormTypeFiche(typeFiche formTypeFiche) {
        this.formTypeFiche = formTypeFiche;
    }

    public typeFiche getTypeFiche() {
        return typeFiche;
    }

    public void setTypeFiche(typeFiche typeFiche) {
        this.typeFiche = typeFiche;
    }

    public List<typeFiche> getListeTypeFiche() {
        return listeTypeFiche;
    }

    public void setListeTypeFiche(List<typeFiche> listeTypeFiche) {
        this.listeTypeFiche = listeTypeFiche;
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

    public Date getDateDelivrer() {
        return dateDelivrer;
    }

    public void setDateDelivrer(Date dateDelivrer) {
        this.dateDelivrer = dateDelivrer;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public operationVehiculeMBean getRecuparation() {
        return recuparation;
    }

    public void setRecuparation(operationVehiculeMBean recuparation) {
        this.recuparation = recuparation;
    }

    public List<fiche> getListeFicheFiltre1() {
        return listeFicheFiltre1;
    }

    public void setListeFicheFiltre1(List<fiche> listeFicheFiltre1) {
        this.listeFicheFiltre1 = listeFicheFiltre1;
    }

    public List<fiche> getListeFicheFiltre2() {
        return listeFicheFiltre2;
    }

    public void setListeFicheFiltre2(List<fiche> listeFicheFiltre2) {
        this.listeFicheFiltre2 = listeFicheFiltre2;
    }

    public List<vehicules> getListeVehiculeTotal() {
        return listeVehiculeTotal;
    }

    public void setListeVehiculeTotal(List<vehicules> listeVehiculeTotal) {
        this.listeVehiculeTotal = listeVehiculeTotal;
    }

    public Date getDateduJour() {
        return dateduJour;
    }

    public void setDateduJour(Date dateduJour) {
        this.dateduJour = dateduJour;
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

    public void ajouterTypeFiche() {
        String comparaison = "";
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeTypeFiche.size();
            if (i == 0) {
                code = "TY_FIC01";
            } else if (i <= 8) {
                System.out.println("i = " + i);
                code = listeTypeFiche.get(i - 1).getIdTypeFiche();
                code = code.substring(7);
                ii = Integer.parseInt(code) + 1;
                code = "TY_FIC0" + Integer.toString(ii);
            } else {
                System.out.println("i = " + i);
                code = listeTypeFiche.get(i - 1).getIdTypeFiche();
                code = code.substring(6);
                ii = Integer.parseInt(code) + 1;
                code = "TY_FIC" + Integer.toString(ii);
            }
            formTypeFiche.setIdTypeFiche(code);
            comparaison = formTypeFiche.getLibelleTypeFiche().trim();
            //formMarque.setIdMarque(formMarque.getLibelleMarque().trim().toUpperCase());
            //formObject.setLibelleMarque(libelleMarque.trim().toUpperCase());
            if (formTypeFiche.getLibelleTypeFiche().trim().length() == 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Avertissement", "Libelle du type de fiche ne peut être vide"));
                context.addMessage(null, new FacesMessage("Avertissement", "Libelle du type de fiche ne peut être vide"));

                 //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "libelle de la marque ne peut être vide"));
                // msg="libelle de la marque ne peut être vide";
            } else {
                boolean existance = false;
                int nb = listeTypeFiche.size();

                if (nb != 0) {
                    for (int p = 0; p <= nb - 1; p++) {
                        if (listeTypeFiche.get(p).getLibelleTypeFiche().equals(comparaison.toUpperCase())) {
                            existance = true;
                        }
                    }
                    if (existance == true) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Avertissement", "Ce libelle de type de fiche existe déjà"));
                        context.addMessage(null, new FacesMessage("Avertissement", "Ce libelle de type de fiche existe déjà"));
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce libelle de marque existe déjà"));
                        //msg=" Ce libelle de marque existe déjà";
                    } else {
                        formTypeFiche.setLibelleTypeFiche(comparaison.toUpperCase());
                        this.typeFicheService.ajouter(formTypeFiche);
                        listeTypeFiche.add(formTypeFiche);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Succès", "Enregistrement éffectuer avec succès"));
                        context.addMessage(null, new FacesMessage("Succès", "Enregistrement éffectuer avec succès"));
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Enregistrement éffectuer avaec succès"));
                        //msg="Enregistrement éffectuer avaec succès";
                    }
                } else {
                    formTypeFiche.setLibelleTypeFiche(comparaison.toUpperCase());
                    this.typeFicheService.ajouter(formTypeFiche);
                    listeTypeFiche.add(formTypeFiche);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Succès", "Enregistrement éffectuer avec succès"));
                    context.addMessage(null, new FacesMessage("Succès", "Enregistrement éffectuer avec succès"));

                    //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Enregistrement éffectuer avaec succès"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerTypeFiche() {
        ajouterTypeFiche();
        formTypeFiche = new typeFiche();
        //Ajax.oncomplete("PF('marqueDlg').hide()");
    }

    public List<fiche> getListeInter1() {
        return listeInter1;
    }

    public void setListeInter1(List<fiche> listeInter1) {
        this.listeInter1 = listeInter1;
    }

    public List<fiche> getListeInter2() {
        return listeInter2;
    }

    public void setListeInter2(List<fiche> listeInter2) {
        this.listeInter2 = listeInter2;
    }

    
}
