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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.marques;
import tg.artp.geParc.entities.modele;
import tg.artp.geParc.entities.typeCarburant;
import tg.artp.geParc.entities.typeVehicules;
import tg.artp.geParc.services.marquesServiceBeanLocal;
import tg.artp.geParc.services.modeleServiceBeanLocal;
import tg.artp.geParc.services.typeCarburantServiceBeanLocal;
import tg.artp.geParc.services.typeVehiculeServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class modeleMBean implements Serializable {
    
    @EJB
    private modeleServiceBeanLocal modeleService;
    private modele formObject, selectObject, supression, modif;
    private List<modele> listeModele, listeControle;
    @EJB
    private marquesServiceBeanLocal marqueService;
    private marques formMarque, selectmarque;
    private List<marques> listeMarque;
    @EJB
    private typeVehiculeServiceBeanLocal typeVehiculeService;
    private typeVehicules formTypeVehicule, selectTypeVehicule;
    private List<typeVehicules> listeTypeVehicule;
    @EJB
    private typeCarburantServiceBeanLocal typeCarburantService;
    private typeCarburant formTypeCarburant, selectTypeCarburant;
    private List<typeCarburant> listeTypeCarburant;
    
    private String message,msg;
    private String id = "MOD";
    private String code="";
    int index;
    private boolean desactiver = true;
    private String idMarque, idTypeVehicule, idTypeCarburant;
    private marques IDMarque;
    private  typeVehicules IDTypeVehicule;
    private String designation;
    private BigDecimal parametreVidange;
    private typeCarburant typecarburant;
    
    public modeleMBean(){
        this.formTypeVehicule = new typeVehicules();
        this.formMarque = new marques();
        this.formObject = new modele();
        this.formTypeCarburant = new typeCarburant();
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listeModele = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeModele = this.modeleService.selectionnerTout();
        this.listeControle = this.modeleService.selectionnerTout();
        desactiver = true;
        
        this.listeMarque = new ArrayList();
        this.listeMarque = this.marqueService.selectionnerTout();
        
        this.listeTypeVehicule = new ArrayList();
        this.listeTypeVehicule = this.typeVehiculeService.selectionnerTout();
        
        this.listeTypeCarburant = new ArrayList();
        this.listeTypeCarburant = this.typeCarburantService.selectionnerTout();
        
    }
    
    public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        idTypeVehicule = selectObject.getIdTypeVehicule().getIdTypeVehicule();
        idMarque = selectObject.getIdMarque().getIdMarque();
        idTypeCarburant = selectObject.getIdTypeCarburant().getIdTypeCarburant();
        designation = selectObject.getDesignation();
        parametreVidange = selectObject.getParametreVidange();
        this.index = this.listeModele.indexOf(this.selectObject);
        modif = selectObject;
        desactiver = false;
    }

    public void suppression() {
        try {
            modeleService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modifier() {
        try {
            IDMarque = this.marqueService.selectionner(idMarque);
            IDTypeVehicule = this.typeVehiculeService.selectionner(idTypeVehicule);
            typecarburant = this.typeCarburantService.selectionner(idTypeCarburant);
            selectObject.setIdMarque(IDMarque);
            selectObject.setIdTypeVehicule(IDTypeVehicule);
            selectObject.setDesignation(designation);
            selectObject.setParametreVidange(parametreVidange);
            selectObject.setIdTypeCarburant(typecarburant);
            modeleService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            i= listeModele.size();
//            if (i == 0){
//                code = id+"01";
//            } else if(i<=8){
//                code = listeModele.get(i-1).getIdModele();
//                code = code.substring(4);
//                ii = Integer.parseInt(code)+1 ;
//                code = id+"0" + Integer.toString(ii);
//            } else {
//                code = listeModele.get(i-1).getIdModele();
//                code = code.substring(3);
//                ii = Integer.parseInt(code)+1 ;
//                code = id + Integer.toString(ii);
//            }
            System.out.println("ce code");
            if (i == 0){
                code =id+"000001";
            } else if(i<=8){
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(8);
                ii = Integer.parseInt(code)+1 ;
                code = id+"00000" + Integer.toString(ii);
            } else if(i<=98) {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0000" + Integer.toString(ii);
            } else if(i<=998) {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = id+"000" + Integer.toString(ii);
            } else if(i<=9998)  {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(5);
                ii = Integer.parseInt(code)+1 ;
                code = id+"00" + Integer.toString(ii);
            } else if(i<=99998) {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0" + Integer.toString(ii);
            } else{
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            IDMarque = this.marqueService.selectionner(idMarque);
            IDTypeVehicule = this.typeVehiculeService.selectionner(idTypeVehicule);
            typecarburant = this.typeCarburantService.selectionner(idTypeCarburant);
            formObject.setIdMarque(IDMarque);
            formObject.setIdTypeVehicule(IDTypeVehicule);
            formObject.setIdModele(code);
            formObject.setDesignation(designation.trim().toUpperCase());
            formObject.setParametreVidange(parametreVidange);
            formObject.setIdTypeCarburant(typecarburant);
            this.modeleService.ajouter(formObject);
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
                         if(listeControle.get(i).getDesignation().equals(designation.trim().toUpperCase()) && 
                            listeControle.get(i).getIdMarque().getLibelleMarque().equals(idMarque) && 
                            listeControle.get(i).getIdTypeVehicule().getLibelleTypeVehicule().equals(idTypeVehicule)){
                             exist1 = true;
                            }
                        }
                        if (exist1 == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce modele existe déja.";
                            Ajax.oncomplete("PF('errorOperationModele').show()");
                            this.rafraichir();
                        } else  { 
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationModele').show()");
                        } 
                    } else {
                            modifier();
                            message = "Modification effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationModele').show()");
                    }
                } else {
                    int nb=0;
                    nb=listeModele.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listeModele.get(i).getDesignation().equals(designation.trim().toUpperCase())&&
                            listeModele.get(i).getIdMarque().getIdMarque().equals(idMarque) &&
                            listeModele.get(i).getIdTypeVehicule().getIdTypeVehicule().equals(idTypeVehicule)){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            message = "Impossible d'éffectuer l'enrégistrement. Ce modele existe déja.";
                            Ajax.oncomplete("PF('errorOperationModele').show()");
                            this.rafraichir();
                        } else  { 
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationModele').show()");
                        } 
                    } else {
                            ajouter();
                            message = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationModele').show()");
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
            Ajax.oncomplete("PF('succesOperationModele').show()");
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
        formObject = new modele();
        idTypeVehicule ="";
        idMarque="";
        idTypeCarburant="";
        designation="";
        parametreVidange = null;
        typecarburant = null;
        selectObject = null;
    }
    
    
      //boite de dialog
    public void confirmerEnregistrement() {
        
        boolean verif1=false, verif2=false, verif3=false, verif4=false;
        
        if(designation.trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Avertissement!", "La désignation ne peut être vide "));
        } else {
            verif1=true;
        }
        if (idMarque==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Avertissement!", "Vous devez sélectionner une marque "));
        } else {
            verif2=true;
        }
        if (idTypeVehicule==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Avertissement!", "Vous devez sélectionner un type de véhicule "));
        } else {
            verif3=true;
        }
        if (idTypeCarburant==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Avertissement!", "Vous devez sélectionner un type de carburant "));
        } else {
            verif4=true;
        }
    
        if(modif == null){
            if(verif1 == true && verif2==true && verif3 == true && verif4==true){
               enregistrer();
                rafraichir();
            }  
        } else {
            if(modif.getIdTypeVehicule().getIdTypeVehicule().equals(idTypeVehicule) && modif.getIdMarque().getIdMarque().equals(idMarque) &&
                    modif.getDesignation().equals(designation) && modif.getParametreVidange().equals(parametreVidange)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", 
                                                                                    "Aucune modification n'a été éffectuer  ")); 
            } else {
                if(designation.trim().length() == 0){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!",
                                                                                        "Le champs ne peut être vide "));
                } else {
                    enregistrer();
                    rafraichir();
                }  
            } 
        } 
    }

    public void confirmerSuppression() {
        if(selectObject == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!",
                                                    " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionModele').show()");
        }  
    }
     
    //getters et setters

    public modeleServiceBeanLocal getModeleService() {
        return modeleService;
    }

    public void setModeleService(modeleServiceBeanLocal modeleService) {
        this.modeleService = modeleService;
    }

    public modele getFormObject() {
        return formObject;
    }

    public void setFormObject(modele formObject) {
        this.formObject = formObject;
    }

    public modele getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(modele selectObject) {
        this.selectObject = selectObject;
    }

    public modele getSupression() {
        return supression;
    }

    public void setSupression(modele supression) {
        this.supression = supression;
    }

    public modele getModif() {
        return modif;
    }

    public void setModif(modele modif) {
        this.modif = modif;
    }

    public List<modele> getListeModele() {
        return listeModele;
    }

    public void setListeModele(List<modele> listeModele) {
        this.listeModele = listeModele;
    }

    public List<modele> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<modele> listeControle) {
        this.listeControle = listeControle;
    }

    public marquesServiceBeanLocal getMarqueService() {
        return marqueService;
    }

    public void setMarqueService(marquesServiceBeanLocal marqueService) {
        this.marqueService = marqueService;
    }

    public List<marques> getListeMarque() {
        return listeMarque;
    }

    public void setListeMarque(List<marques> listeMarque) {
        this.listeMarque = listeMarque;
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

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    public String getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getIdTypeVehicule() {
        return idTypeVehicule;
    }

    public void setIdTypeVehicule(String idTypeVehicule) {
        this.idTypeVehicule = idTypeVehicule;
    }

    public marques getIDMarque() {
        return IDMarque;
    }

    public void setIDMarque(marques IDMarque) {
        this.IDMarque = IDMarque;
    }

    public typeVehicules getIDTypeVehicule() {
        return IDTypeVehicule;
    }

    public void setIDTypeVehicule(typeVehicules IDTypeVehicule) {
        this.IDTypeVehicule = IDTypeVehicule;
    }

    public marques getFormMarque() {
        return formMarque;
    }

    public void setFormMarque(marques formMarque) {
        this.formMarque = formMarque;
    }

    public typeVehicules getFormTypeVehicule() {
        return formTypeVehicule;
    }

    public void setFormTypeVehicule(typeVehicules formTypeVehicule) {
        this.formTypeVehicule = formTypeVehicule;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getParametreVidange() {
        return parametreVidange;
    }

    public void setParametreVidange(BigDecimal parametreVidange) {
        this.parametreVidange = parametreVidange;
    }

    public typeCarburant getTypecarburant() {
        return typecarburant;
    }

    public void setTypecarburant(typeCarburant typecarburant) {
        this.typecarburant = typecarburant;
    }

    public typeCarburantServiceBeanLocal getTypeCarburantService() {
        return typeCarburantService;
    }

    public void setTypeCarburantService(typeCarburantServiceBeanLocal typeCarburantService) {
        this.typeCarburantService = typeCarburantService;
    }

    public typeCarburant getFormTypeCarburant() {
        return formTypeCarburant;
    }

    public void setFormTypeCarburant(typeCarburant formTypeCarburant) {
        this.formTypeCarburant = formTypeCarburant;
    }

    public List<typeCarburant> getListeTypeCarburant() {
        return listeTypeCarburant;
    }

    public void setListeTypeCarburant(List<typeCarburant> listeTypeCarburant) {
        this.listeTypeCarburant = listeTypeCarburant;
    }

    public String getIdTypeCarburant() {
        return idTypeCarburant;
    }

    public void setIdTypeCarburant(String idTypeCarburant) {
        this.idTypeCarburant = idTypeCarburant;
    }

    public marques getSelectmarque() {
        return selectmarque;
    }
    
    public void setSelectmarque(marques selectmarque) {
        this.selectmarque = selectmarque;
    }

    public typeVehicules getSelectTypeVehicule() {
        return selectTypeVehicule;
    }

    public void setSelectTypeVehicule(typeVehicules selectTypeVehicule) {
        this.selectTypeVehicule = selectTypeVehicule;
    }

    public typeCarburant getSelectTypeCarburant() {
        return selectTypeCarburant;
    }

    public void setSelectTypeCarburant(typeCarburant selectTypeCarburant) {
        this.selectTypeCarburant = selectTypeCarburant;
    }

    
    public void rowSelectMarque(){
        System.out.println("marque");
        formMarque= selectmarque;
    }
    
    public void modifierMarque() {
        try {
            //selectObject.setLibelleMarque(libelleMarque.trim().toUpperCase());
            selectmarque.setLibelleMarque(formMarque.getLibelleMarque());
            marqueService.modifier(selectmarque);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void ajouterMarque() {
            String comparaison="";
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeMarque.size();
//            if (i == 0){
//                code ="MAR01";
//            } else if(i<=8){
//                code = listeMarque.get(i-1).getIdMarque();
//                code = code.substring(4);
//                System.out.println("code "+code);
//                ii = Integer.parseInt(code)+1 ;
//                code = "MAR0" + Integer.toString(ii);
//            } else if(i<=98) {
//                code = listeMarque.get(i-1).getIdMarque();
//                code = code.substring(3);
//                System.out.println("code "+code);
//                ii = Integer.parseInt(code)+1 ;
//                code = "MAR" + Integer.toString(ii);
//            } else {
//                code = listeMarque.get(i-1).getIdMarque();
//                code = code.substring(3);
//                System.out.println("code "+code);
//                ii = Integer.parseInt(code)+1 ;
//                code = "MAR" + Integer.toString(ii);
//            }
            
            if (i == 0){
                code ="MAR000001";
            } else if(i<=8){
                code = listeMarque.get(i-1).getIdMarque();
                code = code.substring(8);
                ii = Integer.parseInt(code)+1 ;
                code = "MAR00000" + Integer.toString(ii);
            } else if(i<=98) {
                code = listeMarque.get(i-1).getIdMarque();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = "MAR0000" + Integer.toString(ii);
            } else if(i<=998) {
                code = listeMarque.get(i-1).getIdMarque();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = "MAR000" + Integer.toString(ii);
            } else if(i<=9998)  {
                code = listeMarque.get(i-1).getIdMarque();
                code = code.substring(5);
                ii = Integer.parseInt(code)+1 ;
                code = "MAR00" + Integer.toString(ii);
            } else if(i<=99998) {
                code = listeMarque.get(i-1).getIdMarque();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = "MAR0" + Integer.toString(ii);
            } else{
                code = listeMarque.get(i-1).getIdMarque();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = "MAR" + Integer.toString(ii);
            }
            
            formMarque.setIdMarque(code);
            comparaison = formMarque.getLibelleMarque().trim();
            if(formMarque.getLibelleMarque().trim().length()==0){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Avertissement",  "Libelle de la marque ne peut être vide") );
            } else {
                boolean existance=false;
                int nb=listeMarque.size();
                
                if(nb !=0){
                    for (int p=0; p<=nb-1; p++){
                        if(listeMarque.get(p).getLibelleMarque().equals(comparaison.toUpperCase())){
                            existance=true;
                        }
                    }
                    if(existance==true){
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Avertissement",  "Ce libelle de marque existe déjà") );
                    } else {
                        formMarque.setLibelleMarque(comparaison.toUpperCase());
                        this.marqueService.ajouter(formMarque);
                        listeMarque.add(formMarque);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                    }
                } else {
                    formMarque.setLibelleMarque(comparaison.toUpperCase());
                    this.marqueService.ajouter(formMarque);
                    listeMarque.add(formMarque);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                }
               
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void enregistrerMarque() {
            if(selectmarque==null){
                ajouterMarque();
                formMarque = new marques();
            } else{
                modifierMarque();
            }
           
    }
    
    public void rowSelectTypeVehicule(){
        formTypeVehicule = selectTypeVehicule;
    }
    
    public void modifierTypeVehicule(){
        try {
            selectTypeVehicule.setLibelleTypeVehicule(formTypeVehicule.getLibelleTypeVehicule());
            typeVehiculeService.modifier(selectTypeVehicule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterTypeVehicule() {
       String comparaison="";
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeTypeVehicule.size();
            if (i == 0){
                code = "TY_VEH01";
            } else if(i<=8){
                code = listeTypeVehicule.get(i-1).getIdTypeVehicule();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = "TY_VEH0" + Integer.toString(ii);
            } else {
                code = listeTypeVehicule.get(i-1).getIdTypeVehicule();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = "TY_VEH" + Integer.toString(ii);
            }
            formTypeVehicule.setIdTypeVehicule(code);
            comparaison = formTypeVehicule.getLibelleTypeVehicule().trim();
            if(formTypeVehicule.getLibelleTypeVehicule().trim().length()==0){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Avertissement",  "Libelle du type de véhicule ne peut être vide") );
            } else {
                boolean existance=false;
                int nb=listeTypeVehicule.size();
                
                if(nb !=0){
                    for (int p=0; p<=nb-1; p++){
                        if(listeTypeVehicule.get(p).getLibelleTypeVehicule().equals(comparaison.toUpperCase())){
                            existance=true;
                        }
                    }
                    if(existance==true){
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Avertissement",  "Ce libelle de type de véhicule existe déjà") );
                    } else {
                        formTypeVehicule.setLibelleTypeVehicule(comparaison.toUpperCase());
                        this.typeVehiculeService.ajouter(formTypeVehicule);
                        listeTypeVehicule.add(formTypeVehicule);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                    }
                } else {
                    formTypeVehicule.setLibelleTypeVehicule(comparaison.toUpperCase());
                    this.typeVehiculeService.ajouter(formTypeVehicule);
                    listeTypeVehicule.add(formTypeVehicule);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                }
               
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void enregistrerTypeVehicule() {
        if(selectTypeVehicule==null){
            ajouterTypeVehicule();
            formTypeVehicule = new typeVehicules();
        } else {
            modifierTypeVehicule();
        }
            
    }
    
    public void rowSelectTypeCarburant(){
        formTypeCarburant = selectTypeCarburant;
    }
 
    public void modifierTypeCarburant(){
        try {
            selectTypeCarburant.setLibelleTypeCarburant(formTypeCarburant.getLibelleTypeCarburant());
            typeCarburantService.modifier(selectTypeCarburant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ajouterTypeCarburant() {
       String comparaison="";
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeTypeCarburant.size();
            if (i == 0){
                code = "TY_CAR01";
            } else if(i<=8){
                code = listeTypeCarburant.get(i-1).getIdTypeCarburant();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = "TY_CAR0" + Integer.toString(ii);
            } else {
                code = listeTypeCarburant.get(i-1).getIdTypeCarburant();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = "TY_CAR" + Integer.toString(ii);
            }
            formTypeCarburant.setIdTypeCarburant(code);
            comparaison = formTypeCarburant.getLibelleTypeCarburant().trim();
            if(formTypeCarburant.getLibelleTypeCarburant().trim().length()==0){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Avertissement",  "Libelle du type de carburant ne peut être vide") );
            } else {
                boolean existance=false;
                int nb=listeTypeCarburant.size();
                
                if(nb !=0){
                    for (int p=0; p<=nb-1; p++){
                        if(listeTypeCarburant.get(p).getLibelleTypeCarburant().equals(comparaison.toUpperCase())){
                            existance=true;
                        }
                    }
                    if(existance==true){
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Avertissement",  "Ce libelle de type de carburant existe déjà") );
                    } else {
                        formTypeCarburant.setLibelleTypeCarburant(comparaison.toUpperCase());
                        this.typeCarburantService.ajouter(formTypeCarburant);
                        listeTypeCarburant.add(formTypeCarburant);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                    }
                } else {
                    formTypeCarburant.setLibelleTypeCarburant(comparaison.toUpperCase());
                    this.typeCarburantService.ajouter(formTypeCarburant);
                    listeTypeCarburant.add(formTypeCarburant);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                }
               
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
        public void enregistrerTypeCarburant() {
            if(selectTypeCarburant==null){
                ajouterTypeCarburant();
                formTypeCarburant = new typeCarburant();
            } else {
                modifierTypeCarburant();
            }
           
    }
    
}
