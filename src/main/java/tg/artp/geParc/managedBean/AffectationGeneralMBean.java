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
import javax.faces.bean.SessionScoped;
import tg.artp.geParc.entities.fiche;
import tg.artp.geParc.entities.typeFiche;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.services.ficheServiceBeanLocal;
import tg.artp.geParc.services.typeFicheServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean(name="AffectationGeneralMBean")
@SessionScoped
public class AffectationGeneralMBean implements Serializable {
    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private List<vehicules> listeVehicule,listeVehiculeTotal;
    
    @EJB
    private ficheServiceBeanLocal ficheService;
    private List<fiche> listeFiche, listeFicheFiltre, listeFicheFiltre2;
    
    @EJB
    private typeFicheServiceBeanLocal typeFicheService;
    private List<typeFiche> listeTypeFiche;
    
     public vehicules vehicule;
     public String typeFiche,titre;
     public typeFiche idFiche;
     
     private boolean desactiver=true;

    public AffectationGeneralMBean() {
    }
    
    @PostConstruct
    public void chargerElement() {
       
        this.listeVehicule = new ArrayList();
        this.listeVehiculeTotal = new ArrayList();
        this.listeVehiculeTotal = this.vehiculeService.selectionnerTout();
        this.listeFiche = new ArrayList();
        this.listeFiche = this.ficheService.selectionnerTout();
        vehicule=null;
        desactiver=true;
        int nb = listeVehiculeTotal.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeVehiculeTotal.get(i).isEtatVehicule() == true) {
                    listeVehicule.add(listeVehiculeTotal.get(i));
                } 
            }
        }
//        System.out.println("veh "+vehicule.getImmatriculation());
        this.listeTypeFiche = new ArrayList();
        this.listeTypeFiche = typeFicheService.selectionnerTout();
    }
    
    
    public void rafraichir(){
        vehicule=null;
        this.listeVehicule = new ArrayList();
        this.listeVehiculeTotal = new ArrayList();
        this.listeVehiculeTotal = this.vehiculeService.selectionnerTout();
        this.listeFiche = new ArrayList();
        this.listeFiche = this.ficheService.selectionnerTout();
        int nb = listeVehiculeTotal.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeVehiculeTotal.get(i).isEtatVehicule() == true) {
                    listeVehicule.add(listeVehiculeTotal.get(i));
                } 
            }
        }
        desactiver = true;
    }

    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public List<vehicules> getListeVehiculeTotal() {
        return listeVehiculeTotal;
    }

    public void setListeVehiculeTotal(List<vehicules> listeVehiculeTotal) {
        this.listeVehiculeTotal = listeVehiculeTotal;
    }

    public ficheServiceBeanLocal getFicheService() {
        return ficheService;
    }

    public void setFicheService(ficheServiceBeanLocal ficheService) {
        this.ficheService = ficheService;
    }

    public List<fiche> getListeFiche() {
        return listeFiche;
    }

    public void setListeFiche(List<fiche> listeFiche) {
        this.listeFiche = listeFiche;
    }

    public List<fiche> getListeFicheFiltre() {
        return listeFicheFiltre;
    }

    public void setListeFicheFiltre(List<fiche> listeFicheFiltre) {
        this.listeFicheFiltre = listeFicheFiltre;
    }

    public List<fiche> getListeFicheFiltre2() {
        return listeFicheFiltre2;
    }

    public void setListeFicheFiltre2(List<fiche> listeFicheFiltre2) {
        this.listeFicheFiltre2 = listeFicheFiltre2;
    }

    public typeFicheServiceBeanLocal getTypeFicheService() {
        return typeFicheService;
    }

    public void setTypeFicheService(typeFicheServiceBeanLocal typeFicheService) {
        this.typeFicheService = typeFicheService;
    }

    public List<typeFiche> getListeTypeFiche() {
        return listeTypeFiche;
    }

    public void setListeTypeFiche(List<typeFiche> listeTypeFiche) {
        this.listeTypeFiche = listeTypeFiche;
    }

    public vehicules getVehicule() {
        return vehicule;
    }

    public void setVehicule(vehicules vehicule) {
        this.vehicule = vehicule;
    }

    public String getTypeFiche() {
        return typeFiche;
    }

    public void setTypeFiche(String typeFiche) {
        this.typeFiche = typeFiche;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public typeFiche getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(typeFiche idFiche) {
        this.idFiche = idFiche;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }
    
    
    
}
