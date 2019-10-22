/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tg.artp.geParc.entities.vehicules;
import static tg.artp.geParc.managedBean.LoginMBean.heureToString;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
public class ExecutionAutomatique extends TimerTask {
    public boolean execution=false;
    @EJB
    public vehiculesServiceBeanLocal vehiculeService;
    public List<vehicules> listeVehicule;
    
    @ManagedProperty(value = "#{Declencheur}")
    private Declencheur declencheur;
    
    
    @PostConstruct
    public void chargerElement() {
        execution=false;
        System.out.println("ExecutionAutomatique chargerelement");
        this.listeVehicule = new ArrayList();
        this.listeVehicule = this.vehiculeService.selectionnerTout();
        test2();
        System.out.println("size "+listeVehicule.size());
    }
    
    
    @Override
    public void run() {
        System.out.println(new Date() + " Execution de ma tache");
        Valeur();
        execution = true;
         System.out.println("execution == "+execution);
        System.out.println("val "+Valeur());
        System.out.println("*************");
        chargerElement();
        System.out.println("*************");
    }

    
       public void test2(){
         try
        {
            boolean finished = true;
            while (! finished)
            {
                System.out.println("debut");
                // Exécution de la tâche
                String param = "20:10";
                Date today = new Date();
                String heure= heureToString(today);
                //System.out.println("heure "+heure);
                if(heure.equals(param)){
                    System.out.println("heure de l'execution");
                    declencheur.declencheur();
                }
//                System.out.println(new Date() + " Execution de ma tache");
//                System.out.println("size "+listeVehicule.size());
                Thread.sleep (2000); // En pause pour deux secondes
            }
        }
        catch (InterruptedException exception){}
    
    }

    public Declencheur getDeclencheur() {
        return declencheur;
    }

    public void setDeclencheur(Declencheur declencheur) {
        this.declencheur = declencheur;
    }
    
    
    
    public boolean Valeur(){
        boolean val=true;
        return val;
    }
    
    public boolean isExecution() {
        return execution;
    }

    public void setExecution(boolean execution) {
        this.execution = execution;
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
    
}
