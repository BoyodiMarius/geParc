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
import tg.artp.geParc.entities.typeFiche;
import tg.artp.geParc.services.typeFicheServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class TypeFicheMBean implements Serializable {
    
    @EJB
    private typeFicheServiceBeanLocal typeFicheService;
    private typeFiche formObject, selectObject, supression, modif;
    private List<typeFiche> listeTypeFiche, listeControle;
    private String message;
    private String id = "TY_FIC";
    private String libelleTypeFiche = "";
    private String code="";
    int index;
    private boolean desactiver = true;
    
    public TypeFicheMBean() {
        this.formObject = new typeFiche();
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listeTypeFiche = new ArrayList();
        this.listeTypeFiche = this.typeFicheService.selectionnerTout();
        this.listeControle = new ArrayList();
        this.listeControle = this.typeFicheService.selectionnerTout();
        desactiver = true;
    }
    
     public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        libelleTypeFiche = selectObject.getLibelleTypeFiche();
        this.index = this.listeTypeFiche.indexOf(this.selectObject);
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
            selectObject.setLibelleTypeFiche(libelleTypeFiche);
            typeFicheService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void modifier() {
        try {
            selectObject.setLibelleTypeFiche(libelleTypeFiche.trim().toUpperCase());
            typeFicheService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeTypeFiche.size();
            if (i == 0){
                code = id+"01";
            } else if(i<=8){
                code = listeTypeFiche.get(i-1).getIdTypeFiche();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = id +"0"+ Integer.toString(ii);
            } else {
                code = listeTypeFiche.get(i-1).getIdTypeFiche();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            formObject.setIdTypeFiche(code);
            formObject.setLibelleTypeFiche(libelleTypeFiche.trim().toUpperCase());
            this.typeFicheService.ajouter(formObject);
           
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
                         if(listeControle.get(i).getLibelleTypeFiche().equals(libelleTypeFiche.trim().toUpperCase())){
                             exist1 = true;
                            }
                        }
                        if (exist1 == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce Type de fiche existe déja.";
                            Ajax.oncomplete("PF('errorOperationTypeFiche').show()");
                            this.rafraichir();
                        } else  { 
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeFiche').show()");
                        } 
                    } else {
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeFiche').show()");
                    }
                } else {
                    int nb=0;
                    nb=listeTypeFiche.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listeTypeFiche.get(i).getLibelleTypeFiche().equals(libelleTypeFiche.trim().toUpperCase())){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce type de fiche existe déja.";
                            Ajax.oncomplete("PF('errorOperationTypeFiche').show()");
                            this.rafraichir();
                        } else  { 
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeFiche').show()");
                        } 
                    } else {
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationTypeFiche').show()");
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
            Ajax.oncomplete("PF('succesOperationTypeFiche').show()");
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
        formObject = new typeFiche();
        libelleTypeFiche = "";
        selectObject = null;
    }
     
    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new typeFiche();
    }
    
     //boite de dialog
    
    public void confirmerEnregistrement() {
        if(modif == null){
            if(libelleTypeFiche.trim().length() == 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
            } else {
                Ajax.oncomplete("PF('confirmerEnregistrementTypeFiche').show()"); 
                rafraichir();
            }  
        } else {
            if(modif.getLibelleTypeFiche().equals(libelleTypeFiche)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  ")); 
            } else {
                if(libelleTypeFiche.trim().length() == 0){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
                } else {
                    Ajax.oncomplete("PF('confirmerEnregistrementTypeFiche').show()"); 
                    rafraichir();
                }  
            } 
        } 
    }

    public void confirmerSuppression() {
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionTypeFiche').show()");
        }  
    }
    
    //getters et setters

    public typeFicheServiceBeanLocal getTypeFicheService() {
        return typeFicheService;
    }

    public void setTypeFicheService(typeFicheServiceBeanLocal typeFicheService) {
        this.typeFicheService = typeFicheService;
    }

    public typeFiche getFormObject() {
        return formObject;
    }

    public void setFormObject(typeFiche formObject) {
        this.formObject = formObject;
    }

    public typeFiche getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(typeFiche selectObject) {
        this.selectObject = selectObject;
    }

    public typeFiche getSupression() {
        return supression;
    }

    public void setSupression(typeFiche supression) {
        this.supression = supression;
    }

    public List<typeFiche> getListeTypeFiche() {
        return listeTypeFiche;
    }

    public void setListeTypeFiche(List<typeFiche> listeTypeFiche) {
        this.listeTypeFiche = listeTypeFiche;
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

    public String getLibelleTypeFiche() {
        return libelleTypeFiche;
    }

    public void setLibelleTypeFiche(String libelleTypeFiche) {
        this.libelleTypeFiche = libelleTypeFiche;
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

    public List<typeFiche> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<typeFiche> listeControle) {
        this.listeControle = listeControle;
    }

    public typeFiche getModif() {
        return modif;
    }

    public void setModif(typeFiche modif) {
        this.modif = modif;
    }
    
    
    
}
