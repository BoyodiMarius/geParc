/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.missions;
import tg.artp.geParc.entities.typeMission;
import tg.artp.geParc.services.missionsServiceBeanLocal;
import tg.artp.geParc.services.typeMissionServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class MissionMBean implements Serializable {
    
    @EJB
    private missionsServiceBeanLocal missionsService;
    private missions formObject, selectObject, supression, modif;
    private List<missions> listeMissions, listeControle;
    
    @EJB
    private typeMissionServiceBeanLocal typeMissionService;
    private typeMission formTypeMission, typeMission;
    private List<typeMission> listeTypeMission;
    
    private String message,msg;
    private String id = "MIS";
    private String code="";
    int index;
    private boolean desactiver = true;
    
    private Date dateDepart, dateRetour;
    private String objetMissions;
    
    public MissionMBean(){
        formObject = new missions();
        formTypeMission = new typeMission();
    }
    
    @PostConstruct
    public void chargerElement() {
        this.listeMissions = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeMissions = this.missionsService.selectionnerTout();
        this.listeControle = this.missionsService.selectionnerTout();
        
        this.listeTypeMission = new ArrayList();
        this.listeTypeMission = this.typeMissionService.selectionnerTout();
        
//        for (int i=0; i<=listeTypeMission.size()-1;i++){
//            System.out.println("type mission : "+listeTypeMission.get(i).getLibelleTypeMission());
//        }
    }
    
    public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        dateDepart = selectObject.getDateDepart();
        dateRetour = selectObject.getDateRetour();
        objetMissions = selectObject.getObjetMission();
        typeMission = selectObject.getIdTypeMission();
        System.out.println("type "+typeMission.getLibelleTypeMission());
         this.index = this.listeMissions.indexOf(this.selectObject);
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
            missionsService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modifier() {
        try {
            selectObject.setDateDepart(dateDepart);
            selectObject.setDateRetour(dateRetour);
            selectObject.setObjetMission(objetMissions);
            selectObject.setIdTypeMission(typeMission);
            missionsService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void ajouter() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeMissions.size();
            if (i == 0){
                code = id+"01";
            } else if(i<=8){
                code = listeMissions.get(i-1).getIdMission();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0" + Integer.toString(ii);
            } else {
                code = listeMissions.get(i-1).getIdMission();
                code = code.substring(3);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            formObject.setIdMission(code);
            formObject.setDateDepart(dateDepart);
            formObject.setDateRetour(dateRetour);
            formObject.setObjetMission(objetMissions);
            formObject.setIdTypeMission(typeMission);
            this.missionsService.ajouter(formObject);
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
            Ajax.oncomplete("PF('succesOperationMission').show()");
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
            Ajax.oncomplete("PF('succesOperationMission').show()");
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
    }

    public void effacer() {
        formObject = new missions();
        objetMissions="";
        selectObject = null;
    }

    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new missions();
    }
    
    public void confirmerEnregistrement() {
        int nb=listeControle.size();
        boolean veri=false, veri2=false,veri3=false,veri4=false,veri5=false;
        
        if(typeMission == null){
            veri2=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " choisissez un type de mission "));
        }
        if(objetMissions.trim().length()==0){
             veri3=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " L'objet de la mission ne peut être vide "));
        }
        if(dateDepart==null){
            veri4=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de départ ne peut pas être vide "));
        }
        if(dateRetour==null){
            veri5=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de retour ne peut être vide "));
        }
        
        if(veri4==false && veri5==false){
            if(dateDepart.compareTo(dateRetour) == 1){
                veri=true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La date de départ ne peut pas être supérieur à la date de retour "));
            }
        }
        
     
        
        if(veri==false && veri2==false && veri3==false && veri4==false && veri5==false){
             Ajax.oncomplete("PF('confirmerEnregistrementMission').show()");
        }
       
        //rafraichir();
    }

    public void confirmerSuppression() {
        if (selectObject == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionMission').show()");
        }
    }

    public void ajouterTypeMission() {
        String comparaison="";
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeTypeMission.size();
            if (i == 0){
                code ="TY_MIS01";
            } else if(i<=8){
                code = listeTypeMission.get(i-1).getIdTypeMission();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = "TY_MIS0" + Integer.toString(ii);
            } else {
                code = listeTypeMission.get(i-1).getIdTypeMission();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = "TY_MIS" + Integer.toString(ii);
            }
            formTypeMission.setIdTypeMission(code);
            comparaison = formTypeMission.getLibelleTypeMission().trim();
            //formMarque.setIdMarque(formMarque.getLibelleMarque().trim().toUpperCase());
            //formObject.setLibelleMarque(libelleMarque.trim().toUpperCase());
            if(formTypeMission.getLibelleTypeMission().trim().length()==0){
//                FacesContext context = FacesContext.getCurrentInstance();
//                context.addMessage(null, new FacesMessage("Avertissement",  "Libelle du type de mission ne peut être vide") );
//                context.addMessage(null, new FacesMessage("Avertissement",  "Libelle du type de mission ne peut être vide") );
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Libelle du type de mission ne peut être vide"));
                 //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "libelle de la marque ne peut être vide"));
               // msg="libelle de la marque ne peut être vide";
            } else {
                boolean existance=false;
                int nb=listeTypeMission.size();
                
                if(nb !=0){
                    for (int p=0; p<=nb-1; p++){
                        if(listeTypeMission.get(p).getLibelleTypeMission().equals(comparaison.toUpperCase())){
                            existance=true;
                        }
                    }
                    if(existance==true){
//                        FacesContext context = FacesContext.getCurrentInstance();
//                        context.addMessage(null, new FacesMessage("Avertissement",  "Ce libelle de type de mission existe déjà") );
//                        context.addMessage(null, new FacesMessage("Avertissement",  "Ce libelle de type de mission existe déjà") );
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce libelle de type de mission existe déjà"));
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce libelle de marque existe déjà"));
                        //msg=" Ce libelle de marque existe déjà";
                    } else {
                        formTypeMission.setLibelleTypeMission(comparaison.toUpperCase());
                        this.typeMissionService.ajouter(formTypeMission);
                        listeTypeMission.add(formTypeMission);
//                        FacesContext context = FacesContext.getCurrentInstance();
//                        context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
//                        context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès!", "Enregistrement éffectuer avec succès"));
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Enregistrement éffectuer avaec succès"));
                        //msg="Enregistrement éffectuer avaec succès";
                    }
                } else {
                    formTypeMission.setLibelleTypeMission(comparaison.toUpperCase());
                    this.typeMissionService.ajouter(formTypeMission);
                    listeTypeMission.add(formTypeMission);
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
//                    context.addMessage(null, new FacesMessage("Succès",  "Enregistrement éffectuer avec succès") );
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès!", "Enregistrement éffectuer avec succès"));
                    //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Enregistrement éffectuer avaec succès"));
                }
               
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    
    public void enregistrerTypeMission() {
            ajouterTypeMission();
            formTypeMission = new typeMission();
            //Ajax.oncomplete("PF('marqueDlg').hide()");
    }
    
    //getters et setters

    public missionsServiceBeanLocal getMissionsService() {
        return missionsService;
    }

    public void setMissionsService(missionsServiceBeanLocal missionsService) {
        this.missionsService = missionsService;
    }

    public missions getFormObject() {
        return formObject;
    }

    public void setFormObject(missions formObject) {
        this.formObject = formObject;
    }

    public missions getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(missions selectObject) {
        this.selectObject = selectObject;
    }

    public missions getSupression() {
        return supression;
    }

    public void setSupression(missions supression) {
        this.supression = supression;
    }

    public missions getModif() {
        return modif;
    }

    public void setModif(missions modif) {
        this.modif = modif;
    }

    public List<missions> getListeMissions() {
        return listeMissions;
    }

    public void setListeMissions(List<missions> listeMissions) {
        this.listeMissions = listeMissions;
    }

    public List<missions> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<missions> listeControle) {
        this.listeControle = listeControle;
    }

    public typeMissionServiceBeanLocal getTypeMissionService() {
        return typeMissionService;
    }

    public void setTypeMissionService(typeMissionServiceBeanLocal typeMissionService) {
        this.typeMissionService = typeMissionService;
    }

    public typeMission getFormTypeMission() {
        return formTypeMission;
    }

    public void setFormTypeMission(typeMission formTypeMission) {
        this.formTypeMission = formTypeMission;
    }

    public typeMission getTypeMission() {
        return typeMission;
    }

    public void setTypeMission(typeMission typeMission) {
        this.typeMission = typeMission;
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

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getObjetMissions() {
        return objetMissions;
    }

    public void setObjetMissions(String objetMissions) {
        this.objetMissions = objetMissions;
    }
    
    
     
    
}
