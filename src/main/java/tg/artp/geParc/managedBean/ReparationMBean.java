/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.pannes;
import tg.artp.geParc.entities.reparationPanne;
import tg.artp.geParc.entities.reparations;
import tg.artp.geParc.entities.reparationsPK;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.services.pannesServiceBeanLocal;
import tg.artp.geParc.services.reparationPanneServiceBeanLocal;
import tg.artp.geParc.services.reparationsServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class ReparationMBean implements Serializable {

    @EJB
    private reparationsServiceBeanLocal reparationService;
    private reparations formObject, selectObject, supression, modif;
    private List<reparations> listeReparation, listeControle;
    @EJB
    private reparationPanneServiceBeanLocal reparationPanneService;
    private reparationPanne formObjectReparationPanne;
    private List<reparationPanne> listeReparationPanne, listeReparationPanneControle;
    @EJB
    private pannesServiceBeanLocal pannesService;
    
    private vehicules vehicule;
    private String message;
    private String id = "REP";
    private String code = "";
    int index;
    private reparationsPK  idReparationPK;
    private pannes idPannes;

    @ManagedProperty(value = "#{OperationVehicule}")
    private operationVehiculeMBean recuparation;

    public ReparationMBean() {
        this.formObject = new reparations();
        this.modif = new reparations();
        this.formObjectReparationPanne = new reparationPanne();
        this.idReparationPK = new reparationsPK();
    }

    @PostConstruct
    public void chargerElement() {
        this.listeReparation = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeReparation = reparationService.selectionnerTout();
        this.listeControle = reparationService.selectionnerTout();
        this.listeReparationPanneControle = new ArrayList();
        this.listeReparationPanneControle = reparationPanneService.selectionnerTout();
        this.listeReparationPanne = new ArrayList();
        //this.listeReparationPanne = reparationPanneService.selectionnerTout();
    }
    
    public void affichage(){
        vehicule = recuparation.vehicule;
    }
    
    public void rowSelect() {
        formObject = selectObject;
        supression = selectObject;
        this.index = this.listeReparation.indexOf(this.selectObject);
        modif = selectObject;
    }

    public void fermer() {
        recuparation.rafraichir();
        rafraichir();
        recuparation.vehicule = null;
        recuparation.setDesactiver(true);
        Ajax.oncomplete("PF('reparationDlg').hide()");
    }

    public void suppression() {
        try {
            reparationService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier() {
        try {
            reparationService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouter() {
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeReparation.size();
            if (i == 0) {
                code = id + "01";
            } else if (i <= 8) {
                code = listeReparation.get(i - 1).getIdReparation();
                code = code.substring(4);
                ii = Integer.parseInt(code) + 1;
                code = id + "0" + Integer.toString(ii);
            } else {
                code = listeReparation.get(i - 1).getIdReparation();
                code = code.substring(3);
                ii = Integer.parseInt(code) + 1;
                code = id + Integer.toString(ii);
            }
            vehicule = recuparation.vehicule;
            formObject.setIdReparation(code);
            formObject.setIdVehicule(vehicule);
            this.reparationService.ajouter(formObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrer() {
        if (selectObject != null) {
            modifier();
            message = "Modification effectué avec succès";
            Ajax.oncomplete("PF('succesOperationReparation').show()");
            this.rafraichir();

        } else {
            ajouter();
            message = "Enregistrement effectué avec succès";
            Ajax.oncomplete("PF('succesOperationReparation').show()");
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
            Ajax.oncomplete("PF('succesOperationReparation').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index = 0;
    }

    public void ajouterDetail() {
        try {
            idReparationPK = new reparationsPK();
            idReparationPK.setIdPanne(idPannes.getIdPanne());
            idReparationPK.setIdReparation(selectObject.getIdReparation());
            formObjectReparationPanne.setIdReparationPanne(idReparationPK);
            this.reparationPanneService.ajouter(formObjectReparationPanne);
            listeReparationPanne.add(formObjectReparationPanne);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerDetail() {
            ajouterDetail();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès!", "Enregistrement effectué avec succès"));
            effacer();
            this.listeReparationPanneControle = new ArrayList();
            this.listeReparationPanneControle = reparationPanneService.selectionnerTout();
            //this.rafraichir();
            
    }

    
    
    
    public void rafraichir() {
        this.chargerElement();
        this.effacer();
//        recuparation.rafraichir();
    }

    public void effacer() {
        formObject = new reparations();
        formObjectReparationPanne = new reparationPanne();
        code = "";
        selectObject = null;
    }

    public void confirmerEnregistrement() {
        boolean veri1 = false, veri2 = false, veri3 = false, veri4 = false, veri5 = false, veri6 = false;
        int comp = 0;
        BigDecimal compar = new BigDecimal(comp);
        if (vehicule == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez sélectionner un véhicule "));
            veri1 = true;
        }
        if (formObject.getPrixReparation() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez renseigner le prix de la réparation "));
            veri2 = true;
        } else {
            if (formObject.getPrixReparation().compareTo(compar) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le prix de réparation ne peut être négatif"));
                veri2 = true;
            } else if (formObject.getPrixReparation().compareTo(compar) == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le prix de réparation ne peut être null"));
                veri2 = true;
            }
        }
        if (formObject.getDateDebutReparation() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez renseigner la date de début "));
            veri3 = true;
        }
        if (formObject.getDateFinReparation() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez renseigner la date de fin "));
            veri4 = true;
        }
        if (veri3 == false && veri4 == false) {
            if (formObject.getDateFinReparation().compareTo(formObject.getDateDebutReparation()) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "La date de fin réparation ne peut être inférieur á la date de début  "));
                veri5 = true;
            }
        }
        
        int nb=listeReparation.size();
        if(nb!=0){
            for(int i=0; i<=nb-1; i++){
                if(listeControle.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                        && listeControle.get(i).getIdGarage().getLibelleGarage().equals(formObject.getIdGarage().getLibelleGarage())
                        && listeControle.get(i).getDateDebutReparation().compareTo(formObject.getDateDebutReparation())==0
                        && listeControle.get(i).getDateFinReparation().compareTo(formObject.getDateFinReparation())== 0 ){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce véhicule est déjá en réparation"));
                    veri6 = true;
                } else if(listeControle.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                        && listeControle.get(i).getDateDebutReparation().compareTo(formObject.getDateDebutReparation())==1
                        && listeControle.get(i).getDateFinReparation().compareTo(formObject.getDateFinReparation())== -1 ){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce véhicule est déjá en réparation"));
                    veri6 = true;
                } else if(listeControle.get(i).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                        && listeControle.get(i).getDateDebutReparation().compareTo(formObject.getDateDebutReparation())==1
                        && listeControle.get(i).getDateDebutReparation().compareTo(formObject.getDateFinReparation())== 1 ){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce véhicule est déjá en réparation"));
                    veri6 = true;
                }
            }
        }
        
        if(veri1==false && veri2==false && veri5==false && veri6==false){
            Ajax.oncomplete("PF('confirmerEnregistrementReparation').show()");
        }
    }
    
    public void confirmerEnregistrementDetail() {
        boolean veri1=false, veri2=false, veri3=false, veri4=false;
        if(idPannes==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez choisir une panne "));
            veri1=true;
        }
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez choisir une réparation "));
            veri2=true;
        }
        if(formObjectReparationPanne.getDetailReparationPanne().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champ détail ne peut être vide "));
            veri3=true;
        }
        if(veri1==false && veri2==false && veri3==false){
            enregistrerDetail();
        }
    }

    public String panne(String panne){
        String selectionPanne="";
        selectionPanne = pannesService.selectionner(panne).getLibellePanne();
        return selectionPanne;
    }
    
    public void detail(){
        //vehicule = selectObject.getIdVehicule();
        modif = new reparations();
        modif= selectObject;
        this.listeReparationPanne = new ArrayList();
        int n= listeReparationPanneControle.size();
        if(n!=0){
            for(int i=0; i<=n-1; i++){
                if(listeReparationPanneControle.get(i).getIdReparationPanne().getIdReparation().equals(modif.getIdReparation())){
                    listeReparationPanne.add(listeReparationPanneControle.get(i));
                }
            }
        }
        System.out.println(listeReparationPanne.size());
        Ajax.oncomplete("PF('reparationDetailDlg').show()");
    }
    
    //getters and setters
    public reparationsServiceBeanLocal getReparationService() {
        return reparationService;
    }

    public void setReparationService(reparationsServiceBeanLocal reparationService) {
        this.reparationService = reparationService;
    }

    public reparations getFormObject() {
        return formObject;
    }

    public void setFormObject(reparations formObject) {
        this.formObject = formObject;
    }

    public reparations getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(reparations selectObject) {
        this.selectObject = selectObject;
    }

    public reparations getSupression() {
        return supression;
    }

    public void setSupression(reparations supression) {
        this.supression = supression;
    }

    public reparations getModif() {
        return modif;
    }

    public void setModif(reparations modif) {
        this.modif = modif;
    }

    public List<reparations> getListeReparation() {
        return listeReparation;
    }

    public void setListeReparation(List<reparations> listeReparation) {
        this.listeReparation = listeReparation;
    }

    public List<reparations> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<reparations> listeControle) {
        this.listeControle = listeControle;
    }

    public vehicules getVehicule() {
        return vehicule;
    }

    public void setVehicule(vehicules vehicule) {
        this.vehicule = vehicule;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public operationVehiculeMBean getRecuparation() {
        return recuparation;
    }

    public void setRecuparation(operationVehiculeMBean recuparation) {
        this.recuparation = recuparation;
    }

    public reparationPanneServiceBeanLocal getReparationPanneService() {
        return reparationPanneService;
    }

    public void setReparationPanneService(reparationPanneServiceBeanLocal reparationPanneService) {
        this.reparationPanneService = reparationPanneService;
    }

    public reparationPanne getFormObjectReparationPanne() {
        return formObjectReparationPanne;
    }

    public void setFormObjectReparationPanne(reparationPanne formObjectReparationPanne) {
        this.formObjectReparationPanne = formObjectReparationPanne;
    }

    public List<reparationPanne> getListeReparationPanne() {
        return listeReparationPanne;
    }

    public void setListeReparationPanne(List<reparationPanne> listeReparationPanne) {
        this.listeReparationPanne = listeReparationPanne;
    }

    public reparationsPK getIdReparationPK() {
        return idReparationPK;
    }

    public void setIdReparationPK(reparationsPK idReparationPK) {
        this.idReparationPK = idReparationPK;
    }

    public pannes getIdPannes() {
        return idPannes;
    }

    public void setIdPannes(pannes idPannes) {
        this.idPannes = idPannes;
    }

    public pannesServiceBeanLocal getPannesService() {
        return pannesService;
    }

    public void setPannesService(pannesServiceBeanLocal pannesService) {
        this.pannesService = pannesService;
    }

    public List<reparationPanne> getListeReparationPanneControle() {
        return listeReparationPanneControle;
    }

    public void setListeReparationPanneControle(List<reparationPanne> listeReparationPanneControle) {
        this.listeReparationPanneControle = listeReparationPanneControle;
    }

    
}
