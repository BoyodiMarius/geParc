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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.parametres;
import tg.artp.geParc.services.parametresServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class ParametreAlertesMBean implements Serializable {
    
    @EJB
    private parametresServiceBeanLocal parametresService;
    private parametres formObject, selectObject, supression, modif;
    private List<parametres> listeparametres;
    
    private String message;
    private boolean desactiver = true;
    private BigDecimal valeur;
    private Date date; 
    
    public ParametreAlertesMBean(){
        this.formObject = new parametres();
    }
    
    @PostConstruct
    public void chargerElement() {
        this.listeparametres = new ArrayList();
        this.listeparametres = this.parametresService.selectionnerTout();
        desactiver = true;
        date = new Date();
        System.out.println("l'heure est:"+heureToString(date));
    }
    
     public void rowSelect() {
        
        formObject = selectObject;
        valeur= formObject.getValeur();
        if(formObject.getLibelleParametre().equals("VIDANGE")){
           Ajax.oncomplete("PF('modifDlg').show()"); 
        } else {
            Ajax.oncomplete("PF('modifDlg1').show()");
        }
        
     }
     
     
      public static String heureToString(Date date) {
//        String jour = "" + date.getDate();
//        String mois = "" + (date.getMonth() + 1);
//        String annee = "" + (date.getYear() + 1900);
//        if (jour.length() == 1) {
//            jour = "0" + jour;
//        }
//        if (mois.length() == 1) {
//            mois = "0" + mois;
//        }
         String heure = "" + date.getHours();
        String minute = ""+ date.getMinutes();
        return (heure+ ":" + minute);
    }
     
     public void activation(){
         desactiver= false;
     }
     
     public void fermer(){
         System.out.println("ok");
          Ajax.oncomplete("PF('modifDlg').hide()");
           Ajax.oncomplete("PF('modifDlg1').hide()");
            Ajax.oncomplete("PF('succesOperationParametre').hide()");
     }
     
     public void modifier() {
        try {
            formObject.setValeur(valeur);
            parametresService.modifier(formObject);
            message="Modification éffectuer avec succès";
             Ajax.oncomplete("PF('succesOperationParametre').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.rafraichir();
    }
     
   public void rafraichir(){
       chargerElement();
       formObject= new parametres();
       valeur= null;
   } 
     
     
    public void confirmerEnregistrement() {
        Ajax.oncomplete("PF('confirmerEnregistrementParametre').show()");
    }
    
    //getters and setters

    public parametresServiceBeanLocal getParametresService() {
        return parametresService;
    }

    public void setParametresService(parametresServiceBeanLocal parametresService) {
        this.parametresService = parametresService;
    }

    public parametres getFormObject() {
        return formObject;
    }

    public void setFormObject(parametres formObject) {
        this.formObject = formObject;
    }

    public parametres getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(parametres selectObject) {
        this.selectObject = selectObject;
    }

    public parametres getSupression() {
        return supression;
    }

    public void setSupression(parametres supression) {
        this.supression = supression;
    }

    public parametres getModif() {
        return modif;
    }

    public void setModif(parametres modif) {
        this.modif = modif;
    }

    public List<parametres> getListeparametres() {
        return listeparametres;
    }

    public void setListeparametres(List<parametres> listeparametres) {
        this.listeparametres = listeparametres;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
