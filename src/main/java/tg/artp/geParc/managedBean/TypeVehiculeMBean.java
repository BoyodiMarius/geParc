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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.typeVehicules;
import tg.artp.geParc.services.typeVehiculeServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class TypeVehiculeMBean implements Serializable {

    @EJB
    private typeVehiculeServiceBeanLocal typeVehiculeService;
    private typeVehicules formObject, selectObject, supression, modif;
    private List<typeVehicules> listeTypeVehicule, listeControle;
    private String message;
    private String id = "TY_VEH";
    private String libelleTypeVehicule = "";
    private String code="";
    int index;
    private boolean desactiver = true;
    
    public TypeVehiculeMBean() {
        this.formObject = new typeVehicules();
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listeTypeVehicule = new ArrayList();
        this.listeTypeVehicule = this.typeVehiculeService.selectionnerTout();
        this.listeControle = new ArrayList();
        this.listeControle = this.typeVehiculeService.selectionnerTout();
        desactiver = true;
    }
    
    public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        libelleTypeVehicule = selectObject.getLibelleTypeVehicule();
        this.index = this.listeTypeVehicule.indexOf(this.selectObject);
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
            selectObject.setLibelleTypeVehicule(libelleTypeVehicule);
            typeVehiculeService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void modifier() {
        try {
            selectObject.setLibelleTypeVehicule(libelleTypeVehicule.trim().toUpperCase());
            typeVehiculeService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeTypeVehicule.size();
            if (i == 0){
                code = id+"01";
            } else if(i<=8){
                code = listeTypeVehicule.get(i-1).getIdTypeVehicule();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0" + Integer.toString(ii);
            } else {
                code = listeTypeVehicule.get(i-1).getIdTypeVehicule();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            formObject.setIdTypeVehicule(code);
            formObject.setLibelleTypeVehicule(libelleTypeVehicule.trim().toUpperCase());
            this.typeVehiculeService.ajouter(formObject);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void enregistrer() {
        int ct =0;
        ct = listeControle.size();
        if(ct!=0){
           this.listeControle.remove(index); 
        }
                if (selectObject != null) {
                    int nb1=0;
                    nb1=listeControle.size();
                    boolean exist1=false;
                    if(nb1 != 0){
                        for (int i=0; i<=nb1-1; i++){
                         if(listeControle.get(i).getLibelleTypeVehicule().equals(libelleTypeVehicule.trim().toUpperCase())){
                             exist1 = true;
                            }
                        }
                        if (exist1 == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce type de véhicule existe déja.";
                            Ajax.oncomplete("PF('errorOperationTypeVehicule').show()");
                            this.rafraichir();
                        } else  { 
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeVehicule').show()");
                        } 
                    } else {
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeVehicule').show()");
                    }
                } else {
                    int nb=0;
                    nb=listeTypeVehicule.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listeTypeVehicule.get(i).getLibelleTypeVehicule().equals(libelleTypeVehicule.trim().toUpperCase())){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce type de véhicule existe déja.";
                            Ajax.oncomplete("PF('errorOperationTypeVehicule').show()");
                            this.rafraichir();
                        } else  { 
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeVehicule').show()");
                        } 
                    } else {
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeVehicule').show()");
                    }
                }        
        this.rafraichir();
    }
        
    public void supprimer() {
        if (selectObject == null) {
            return;
        }
        try {
            suppression();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationTypeVehicule').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index=0;
    }
    
    public void rafraichir() {
        this.chargerElement();
        this.effacer();
        desactiver = true;
    }
    
    public void effacer() {
        formObject = new typeVehicules();
        libelleTypeVehicule = "";
        selectObject = null;
    }
     
    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new typeVehicules();
    }

    public typeVehiculeServiceBeanLocal getTypeVehiculeService() {
        return typeVehiculeService;
    }

    public void setTypeVehiculeService(typeVehiculeServiceBeanLocal typeVehiculeService) {
        this.typeVehiculeService = typeVehiculeService;
    }

    public typeVehicules getFormObject() {
        return formObject;
    }

    public void setFormObject(typeVehicules formObject) {
        this.formObject = formObject;
    }

    public typeVehicules getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(typeVehicules selectObject) {
        this.selectObject = selectObject;
    }

    public List<typeVehicules> getListeTypeVehicule() {
        return listeTypeVehicule;
    }

    public void setListeTypeVehicule(List<typeVehicules> listeTypeVehicule) {
        this.listeTypeVehicule = listeTypeVehicule;
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

    public String getLibelleTypeVehicule() {
        return libelleTypeVehicule;
    }

    public void setLibelleTypeVehicule(String libelleTypeVehicule) {
        this.libelleTypeVehicule = libelleTypeVehicule;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public typeVehicules getSupression() {
        return supression;
    }

    public void setSupression(typeVehicules supression) {
        this.supression = supression;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    public List<typeVehicules> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<typeVehicules> listeControle) {
        this.listeControle = listeControle;
    }

    public typeVehicules getModif() {
        return modif;
    }

    public void setModif(typeVehicules modif) {
        this.modif = modif;
    }
    
    //boite de dialog
    
    public void confirmerEnregistrement() {
        
                if(modif == null){
            if(libelleTypeVehicule.trim().length() == 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
            } else {
                Ajax.oncomplete("PF('confirmerEnregistrementTypeVehicule').show()");
                rafraichir();
            }  
        } else {
            if(modif.getLibelleTypeVehicule().equals(libelleTypeVehicule)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  ")); 
            } else {
                if(libelleTypeVehicule.trim().length() == 0){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
                } else {
                    Ajax.oncomplete("PF('confirmerEnregistrementTypeVehicule').show()");
                    rafraichir();
                }  
            } 
        } 
    }

    public void confirmerSuppression() {
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionTypeVehicule').show()");
        }  
    }
    
}
