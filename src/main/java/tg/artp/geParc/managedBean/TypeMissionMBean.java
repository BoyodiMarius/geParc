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
import tg.artp.geParc.entities.typeMission;
import tg.artp.geParc.services.typeMissionServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class TypeMissionMBean implements Serializable {
    
    @EJB
    private typeMissionServiceBeanLocal typeMissionService;
    private typeMission formObject, selectObject, supression, modif;
    private List<typeMission> listeTypeMission, listeControle;
    private String message;
    private String id = "TY_MIS";
    private String libelleTypeMission = "";
    private String code="";
    int index;
    private boolean desactiver = true;
    
    public TypeMissionMBean(){
        this.formObject = new typeMission();
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listeTypeMission = new ArrayList();
        this.listeTypeMission = this.typeMissionService.selectionnerTout();
        this.listeControle = new ArrayList();
        this.listeControle = this.typeMissionService.selectionnerTout();
        desactiver = true;
    }
    
     public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        libelleTypeMission = selectObject.getLibelleTypeMission();
        this.index = this.listeTypeMission.indexOf(this.selectObject);
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
            selectObject.setLibelleTypeMission(libelleTypeMission);
            typeMissionService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void modifier() {
        try {
            selectObject.setLibelleTypeMission(libelleTypeMission.trim().toUpperCase());
            typeMissionService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeTypeMission.size();
            if (i == 0){
                code = id+"01";
            } else  if (i <= 8) {
                code = listeTypeMission.get(i-1).getIdTypeMission();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = id +"0"+ Integer.toString(ii);
            } else {
                code = listeTypeMission.get(i-1).getIdTypeMission();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            formObject.setIdTypeMission(code);
            formObject.setLibelleTypeMission(libelleTypeMission.trim().toUpperCase());
            this.typeMissionService.ajouter(formObject);
           
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
                         if(listeControle.get(i).getLibelleTypeMission().equals(libelleTypeMission.trim().toUpperCase())){
                             exist1 = true;
                            }
                        }
                        if (exist1 == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce Type de mission existe déja.";
                            Ajax.oncomplete("PF('errorOperationTypeMission').show()");
                            this.rafraichir();
                        } else  { 
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeMission').show()");
                        } 
                    } else {
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeMission').show()");
                    }
                } else {
                    int nb=0;
                    nb=listeTypeMission.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listeTypeMission.get(i).getLibelleTypeMission().equals(libelleTypeMission.trim().toUpperCase())){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce Garage existe déja.";
                            Ajax.oncomplete("PF('errorOperationTypeMission').show()");
                            this.rafraichir();
                        } else  { 
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeMission').show()");
                        } 
                    } else {
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeMission').show()");
                    }
                }        
        this.rafraichir();
    }
     
     public void supprimer() {
        if (selectObject == null) {
            System.out.println("null");
            return;
        }
        try {
            suppression();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationTypeMission').show()");
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
        formObject = new typeMission();
        libelleTypeMission = "";
        selectObject = null;
    }
     
    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new typeMission();
    }
    
     //boite de dialog
    
    public void confirmerEnregistrement() {
        if(modif == null){
            if(libelleTypeMission.trim().length() == 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
            } else {
                Ajax.oncomplete("PF('confirmerEnregistrementTypeMission').show()");
                rafraichir();
            }  
        } else {
            if(modif.getLibelleTypeMission().equals(libelleTypeMission)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  ")); 
            } else {
                if(libelleTypeMission.trim().length() == 0){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
                } else {
                    Ajax.oncomplete("PF('confirmerEnregistrementTypeMission').show()");
                    rafraichir();
                }  
            } 
        } 
    }

    public void confirmerSuppression() {
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionTypeMission').show()");
        }  
    }
    
    //getters et setters

    public typeMissionServiceBeanLocal getTypeMissionService() {
        return typeMissionService;
    }

    public void setTypeMissionService(typeMissionServiceBeanLocal typeMissionService) {
        this.typeMissionService = typeMissionService;
    }

    public typeMission getFormObject() {
        return formObject;
    }

    public void setFormObject(typeMission formObject) {
        this.formObject = formObject;
    }

    public typeMission getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(typeMission selectObject) {
        this.selectObject = selectObject;
    }

    public typeMission getSupression() {
        return supression;
    }

    public void setSupression(typeMission supression) {
        this.supression = supression;
    }

    public List<typeMission> getListeTypeMission() {
        return listeTypeMission;
    }

    public void setListeTypeMission(List<typeMission> listeTypeMission) {
        this.listeTypeMission = listeTypeMission;
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

    public String getLibelleTypeMission() {
        return libelleTypeMission;
    }

    public void setLibelleTypeMission(String libelleTypeMission) {
        this.libelleTypeMission = libelleTypeMission;
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

    public List<typeMission> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<typeMission> listeControle) {
        this.listeControle = listeControle;
    }

    public typeMission getModif() {
        return modif;
    }

    public void setModif(typeMission modif) {
        this.modif = modif;
    }
    
}
