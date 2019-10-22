/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.garage;
import tg.artp.geParc.services.garageServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class GarageMBean implements Serializable {

    @EJB
    private garageServiceBeanLocal garageService;
    private garage formObject, selectObject, supression, modif;
    private List<garage> listeGarage, listeControle;
    private String message;
    private String id = "GAR";
    private String libelleGarage = "";
    private String contactGarage;
    private String emailGarage="";
    private String code="";
    int index;
    private boolean desactiver = true;
    private boolean desactiver1 = false;

    public GarageMBean (){
        this.formObject = new garage();
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listeGarage = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeGarage = this.garageService.selectionnerTout();
        this.listeControle = this.garageService.selectionnerTout();
        desactiver = true;
        desactiver1 = false;
    }
    
    public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        libelleGarage = selectObject.getLibelleGarage();
        contactGarage = selectObject.getContactGarage();
        if(selectObject.getEmailGarage().equals("Non renseigné")){
            emailGarage = "";
            selectObject.setEmailGarage("");
        } else {
            emailGarage = selectObject.getEmailGarage();
        }
        this.index = this.listeGarage.indexOf(this.selectObject);
        modif = selectObject;
        desactiver = false;
        desactiver1 = true;
    

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
            selectObject.setLibelleGarage(libelleGarage);
            selectObject.setContactGarage(contactGarage);
            selectObject.setEmailGarage(emailGarage);
            garageService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modifier() {
        try {
            selectObject.setLibelleGarage(libelleGarage.trim().toUpperCase());
            selectObject.setContactGarage(contactGarage.trim());
            if(emailGarage.trim().length() == 0){
                String lib="Non renseigné";
                selectObject.setEmailGarage(lib);
            } else {
                selectObject.setEmailGarage(emailGarage.trim());
            }
            garageService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeGarage.size();
            if (i == 0){
                code = id+"01";
            } else if(i<=8){
                code = listeGarage.get(i-1).getIdGarage();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id +"0"+ Integer.toString(ii);
            } else {
                code = listeGarage.get(i-1).getIdGarage();
                code = code.substring(3);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            formObject.setIdGarage(code);
            formObject.setLibelleGarage(libelleGarage.trim().toUpperCase());
            formObject.setContactGarage(contactGarage.trim());
            if(emailGarage.trim().length() == 0){
                String lib="Non renseigné";
                formObject.setEmailGarage(lib);
            } else {
                formObject.setEmailGarage(emailGarage.trim());
            }
            this.garageService.ajouter(formObject);
           
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
                         if(listeControle.get(i).getLibelleGarage().equals(libelleGarage.trim().toUpperCase())){
                             exist1 = true;
                            }
                        }
                        if (exist1 == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce Garage existe déja.";
                            Ajax.oncomplete("PF('errorOperationGarage').show()");
                            this.rafraichir();
                        } else  { 
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationGarage').show()");
                        } 
                    } else {
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperatioGaragen').show()");
                    }
                } else {
                    int nb=0;
                    nb=listeGarage.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listeGarage.get(i).getLibelleGarage().equals(libelleGarage.trim().toUpperCase())){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce Garage existe déja.";
                            Ajax.oncomplete("PF('errorOperationGarage').show()");
                            this.rafraichir();
                        } else  { 
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationGarage').show()");
                        } 
                    } else {
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationGarage').show()");
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
            Ajax.oncomplete("PF('succesOperationGarage').show()");
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
        formObject = new garage();
        libelleGarage = "";
        emailGarage = "";
        contactGarage = null;  
        selectObject = null;
    }
    
     public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new garage();
    }

    //boite de dialog
    
    public void confirmerEnregistrement() {
        boolean verifEmail=false;
        boolean enreg1=false;
        boolean enreg2=false;         
        if(emailGarage.trim().length() != 0) {
            Pattern rfc2822 = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
            if (rfc2822.matcher(emailGarage.trim()).matches()) {
                verifEmail=true;
            } else {
              verifEmail=false;
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "L'email n'est pas valide "));
            }
        }        
        if(contactGarage.trim().length() != 0){
           if(contactGarage.trim().length() == 8){
                try {
                    int conversion = Integer.parseInt(contactGarage.trim());
                    enreg1 = true;
                } catch(NumberFormatException nfe){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le contact n'est pas valide "));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le contact n'est pas valide "));
            } 
        }
        
        if(libelleGarage.trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le libellé du garage ne peut être vide "));
        } else {
                enreg2 = true;
        }
        
        if( modif == null){
            if( (enreg2 == true && enreg1 == true && verifEmail == true) || 
                (enreg2 == true && enreg1 == true && verifEmail == false && emailGarage.trim().length() == 0)){
                    Ajax.oncomplete("PF('confirmerEnregistrementGarage').show()");
                   // rafraichir();
            }
        } else {
                 if( (enreg2 == true && enreg1 == true && verifEmail == true) || 
            (enreg2 == true && enreg1 == true && verifEmail == false && emailGarage.trim().length() == 0)){
            if ((modif.getLibelleGarage().equals(libelleGarage) && modif.getEmailGarage().equals(emailGarage) && modif.getContactGarage().equals(contactGarage) )   ){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  "));
            } else {
               Ajax.oncomplete("PF('confirmerEnregistrementGarage').show()");
               //rafraichir();    
            }                 
        }   
        }

    }

    public void confirmerSuppression() {
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionGarage').show()");
        }  
    }
    
    //getters et setters    

    public garageServiceBeanLocal getGarageService() {
        return garageService;
    }

    public void setGarageService(garageServiceBeanLocal garageService) {
        this.garageService = garageService;
    }

    public garage getFormObject() {
        return formObject;
    }

    public void setFormObject(garage formObject) {
        this.formObject = formObject;
    }

    public garage getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(garage selectObject) {
        this.selectObject = selectObject;
    }

    public garage getSupression() {
        return supression;
    }

    public void setSupression(garage supression) {
        this.supression = supression;
    }

    public List<garage> getListeGarage() {
        return listeGarage;
    }

    public void setListeGarage(List<garage> listeGarage) {
        this.listeGarage = listeGarage;
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

    public String getLibelleGarage() {
        return libelleGarage;
    }

    public void setLibelleGarage(String libelleGarage) {
        this.libelleGarage = libelleGarage;
    }

    public String getContactGarage() {
        return contactGarage;
    }

    public void setContactGarage(String contactGarage) {
        this.contactGarage = contactGarage;
    }

    public String getEmailGarage() {
        return emailGarage;
    }

    public void setEmailGarage(String emailGarage) {
        this.emailGarage = emailGarage;
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

    public List<garage> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<garage> listeControle) {
        this.listeControle = listeControle;
    }

    public boolean isDesactiver1() {
        return desactiver1;
    }

    public void setDesactiver1(boolean desactiver1) {
        this.desactiver1 = desactiver1;
    }

    public garage getModif() {
        return modif;
    }

    public void setModif(garage modif) {
        this.modif = modif;
    }
    
}
