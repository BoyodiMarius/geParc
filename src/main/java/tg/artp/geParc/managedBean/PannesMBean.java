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
import tg.artp.geParc.entities.pannes;
import tg.artp.geParc.services.pannesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class PannesMBean implements Serializable {
    
    @EJB
    private pannesServiceBeanLocal pannesService;
    private pannes formObject, selectObject, supression, modif;
    private List<pannes> listePannes, listeControle;
    private String message;
    private String id = "PAN";
    private String libellePanne = "";
    private String code="";
    int index;
    private boolean desactiver = true;
    
    public PannesMBean(){
        this.formObject = new pannes();
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listePannes = new ArrayList();
        this.listePannes = this.pannesService.selectionnerTout();
        this.listeControle = new ArrayList();
        this.listeControle = this.pannesService.selectionnerTout();
        desactiver = true;
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
    
    public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        libellePanne = selectObject.getLibellePanne();
        this.index = this.listePannes.indexOf(this.selectObject);
        modif = selectObject;
         desactiver = false;
    }
    
    public void suppression() {
        try {
            selectObject.setLibellePanne(libellePanne);
            pannesService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
 
    public void modifier() {
        try {
            selectObject.setLibellePanne(libellePanne.trim().toUpperCase());
            pannesService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listePannes.size();
            if (i == 0){
                code = id+"01";
            } else if(i<=8){
                code = listePannes.get(i-1).getIdPanne();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0" + Integer.toString(ii);
            } else {
                code = listePannes.get(i-1).getIdPanne();
                code = code.substring(3);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            formObject.setIdPanne(code);
            formObject.setLibellePanne(libellePanne.trim().toUpperCase());
            this.pannesService.ajouter(formObject);
           
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
                         if(listeControle.get(i).getLibellePanne().equals(libellePanne.trim().toUpperCase())){
                             exist1 = true;
                            }
                        }
                        if (exist1 == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Cette panne existe déja.";
                            Ajax.oncomplete("PF('errorOperationPanne').show()");
                            this.rafraichir();
                        } else  { 
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationPanne').show()");
                        } 
                    } else {
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationPanne').show()");
                    }
                } else {
                    int nb=0;
                    nb=listePannes.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listePannes.get(i).getLibellePanne().equals(libellePanne.trim().toUpperCase())){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Cette panne existe déja.";
                            Ajax.oncomplete("PF('errorOperationPanne').show()");
                            this.rafraichir();
                        } else  { 
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationPanne').show()");
                        } 
                    } else {
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationPanne').show()");
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
            Ajax.oncomplete("PF('succesOperationPanne').show()");
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
        formObject = new pannes();
        libellePanne = "";
        selectObject = null;
    }
     
    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new pannes();
    }
    
    //boite de dialog
    
    public void confirmerEnregistrement() {
        if(modif == null){
            if(libellePanne.trim().length() == 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
            } else {
                Ajax.oncomplete("PF('confirmerEnregistrementPanne').show()"); 
            }  
        } else {
            if(modif.getLibellePanne().equals(libellePanne)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  ")); 
            } else {
                if(libellePanne.trim().length() == 0){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
                } else {
                    Ajax.oncomplete("PF('confirmerEnregistrementPanne').show()"); 
                }  
            } 
        } 
    }

    public void confirmerSuppression() {
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionPanne').show()");
        }  
    }    
    
    //getters and setters

    public pannesServiceBeanLocal getPannesService() {
        return pannesService;
    }

    public void setPannesService(pannesServiceBeanLocal pannesService) {
        this.pannesService = pannesService;
    }

    public pannes getFormObject() {
        return formObject;
    }

    public void setFormObject(pannes formObject) {
        this.formObject = formObject;
    }

    public pannes getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(pannes selectObject) {
        this.selectObject = selectObject;
    }

    public pannes getSupression() {
        return supression;
    }

    public void setSupression(pannes supression) {
        this.supression = supression;
    }

    public List<pannes> getListePannes() {
        return listePannes;
    }

    public void setListePannes(List<pannes> listePannes) {
        this.listePannes = listePannes;
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

    public String getLibellePanne() {
        return libellePanne;
    }

    public void setLibellePanne(String libellePanne) {
        this.libellePanne = libellePanne;
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

    public List<pannes> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<pannes> listeControle) {
        this.listeControle = listeControle;
    }

    public pannes getModif() {
        return modif;
    }

    public void setModif(pannes modif) {
        this.modif = modif;
    }
   
}
