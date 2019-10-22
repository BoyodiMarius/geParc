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
import org.omnifaces.util.Ajax;
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
@ManagedBean(name="OperationVehicule")
@SessionScoped
public class operationVehiculeMBean implements Serializable  {
    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private List<vehicules> listeVehicule,listeVehiculeTotal;
    
    @EJB
    private ficheServiceBeanLocal ficheService;
    private List<fiche> listeFiche, listeFicheFiltre, listeFicheFiltre2, maListe;
    
    @EJB
    private typeFicheServiceBeanLocal typeFicheService;
    private List<typeFiche> listeTypeFiche;
    
     public vehicules vehicule;
     public String typeFiche,titre;
     public typeFiche idFiche;
     
     private boolean desactiver=true;
    
//    @ManagedProperty(value="#{ChargerCarburantMBean}")
//    private ChargerCarburantMBean ChargerCarburantMBean;
//    @ManagedProperty(value="#{FicheMBean}")
//    private FicheMBean FicheMBean;
    
    public void operationVehiculeMBean(){
        //ChargerCarburantMBean = new ChargerCarburantMBean();
        //B = new FicheMBean();
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
        vehicule= new vehicules();
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
    
    public void selection(){
        this.rafraichir();
        try {
           this.maListe = new ArrayList();
           this.maListe = ficheService.selectionnerTout();
           this.listeFicheFiltre = new ArrayList();
           this.listeFicheFiltre2 = new ArrayList();
            int nb = maListe.size();
            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    //System.out.println("fiche"+i+" : "+listeFiche.get(i).getIdTypeFiche().getLibelleTypeFiche());
                    //System.out.println("vehicule"+i+" : "+listeFiche.get(i).getIdVehicule().getImmatriculation());
                    if (maListe.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE")) {
                        System.out.println("fiche"+i+" : "+maListe.get(i).getIdTypeFiche().getLibelleTypeFiche());
                        System.out.println("vehicule"+i+" : "+maListe.get(i).getIdVehicule().getImmatriculation());
                        listeFicheFiltre.add(maListe.get(i));
                    }  else{
                        listeFicheFiltre2.add(maListe.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
     public void visualisation(){
         this.rafraichir();
          try {
         selection();
         Ajax.oncomplete("PF('visualiserListeAssuranceVisiteDlg').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
    public void visualisation1(){
        this.rafraichir();
         try {
           selection();
          Ajax.oncomplete("PF('visualiserListeAssuranceVisiteDlg1').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void affiche() {
        int nb = listeFicheFiltre.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                System.out.println("immatriculation " + listeFicheFiltre.get(i).getIdVehicule().getImmatriculation());
                System.out.println("Fiche " + listeFicheFiltre.get(i).getIdTypeFiche().getLibelleTypeFiche());
            }
        } else {
            System.out.println("null");
        }

    }
    
    public void renitialiser() {
        vehicule = new vehicules();
    }
    
    public void activation(){
        if(vehicule==null){
            desactiver = true;
        } else {
            desactiver = false;
        }
            
    }
    

    public void assurance(){
        int nb=listeTypeFiche.size();
        if(nb!=0){
            for(int i=0;i<=nb-1;i++){
                if(listeTypeFiche.get(i).getLibelleTypeFiche().equals("ASSURANCE")){
                    typeFiche=listeTypeFiche.get(i).getIdTypeFiche();
                }
            }
            idFiche= typeFicheService.selectionner(typeFiche);
        titre="LISTE DES "+idFiche.getLibelleTypeFiche()+" DU VEHICULE "+vehicule.getImmatriculation();
        System.out.println("TITRE "+titre);
        }
        //typeFiche = "TY_FIC01";
        
        //visualisation();
    }
    
    public void visiteTechnique(){
        int nb=listeTypeFiche.size();
        if(nb!=0){
            for(int i=0;i<=nb-1;i++){
                if(listeTypeFiche.get(i).getLibelleTypeFiche().equals("VISITE TECHNIQUE")){
                    typeFiche=listeTypeFiche.get(i).getIdTypeFiche();
                }
            }
            
             idFiche= typeFicheService.selectionner(typeFiche);
        titre="LISTE DES "+idFiche.getLibelleTypeFiche()+" DU VEHICULE "+vehicule.getImmatriculation();
        System.out.println("TITRE "+titre);
        }
        
        //typeFiche = "TY_FIC02";
       
        //visualisation();
    }
    
    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public vehicules getVehicule() {
        return vehicule;
    }

    public void setVehicule(vehicules vehicule) {
        this.vehicule = vehicule;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    public String getTypeFiche() {
        return typeFiche;
    }

    public void setTypeFiche(String typeFiche) {
        this.typeFiche = typeFiche;
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

    public typeFicheServiceBeanLocal getTypeFicheService() {
        return typeFicheService;
    }

    public void setTypeFicheService(typeFicheServiceBeanLocal typeFicheService) {
        this.typeFicheService = typeFicheService;
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

    public List<vehicules> getListeVehiculeTotal() {
        return listeVehiculeTotal;
    }

    public void setListeVehiculeTotal(List<vehicules> listeVehiculeTotal) {
        this.listeVehiculeTotal = listeVehiculeTotal;
    }

    public List<fiche> getListeFicheFiltre2() {
        return listeFicheFiltre2;
    }

    public void setListeFicheFiltre2(List<fiche> listeFicheFiltre2) {
        this.listeFicheFiltre2 = listeFicheFiltre2;
    }

    public List<typeFiche> getListeTypeFiche() {
        return listeTypeFiche;
    }

    public void setListeTypeFiche(List<typeFiche> listeTypeFiche) {
        this.listeTypeFiche = listeTypeFiche;
    }

    public List<fiche> getMaListe() {
        return maListe;
    }

    public void setMaListe(List<fiche> maListe) {
        this.maListe = maListe;
    }

}
