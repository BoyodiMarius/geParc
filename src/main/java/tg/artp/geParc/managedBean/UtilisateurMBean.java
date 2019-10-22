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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class UtilisateurMBean implements Serializable {
 
    @EJB
    private UtilisateurServiceBeanLocal UtilisateurService;
    private Utilisateur formObject, selectObject, supression, modif;
    private List<Utilisateur> ListUtilisateur, listeControle;
    
    
    private Boolean desactiver = true;

    private String message, confirmationPassword;
    private Integer index;

    public UtilisateurMBean() {
        this.formObject = new Utilisateur();
    }
    
    @PostConstruct
    public void chargerElement() {
        this.listeControle = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeControle = this.UtilisateurService.selectionnerTout();
        this.listeControle = this.UtilisateurService.selectionnerTout();
        
    }
    
    public void rowSelect() {
        formObject = selectObject;
        supression =  selectObject;
        this.index = this.listeControle.indexOf(this.selectObject);
        modif = selectObject;
        desactiver = false;
    }
    
    
    
    

}
