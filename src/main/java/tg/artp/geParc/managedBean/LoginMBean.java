/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.fiche;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;
import tg.artp.geParc.services.ficheServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author Elvis
 */
@ManagedBean(name="loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

    @EJB
    private UtilisateurServiceBeanLocal userService;
    private Utilisateur user, modifier;
    private List<Utilisateur> liste;
    private String username, password,info, ancienMotDePasse, nouveauMotDePasse, confirmationMotDePasse, message;

    @EJB
    private ficheServiceBeanLocal ficheService;
    private fiche modif;
    private List<fiche> listeFiche;
    
    @EJB
    private vehiculesServiceBeanLocal serviceVehicule;
    private vehicules formvehicule;
    private List<vehicules> listeVehicule;
    
    private int nbconnexion=0, ve=0;
    private Boolean remember = true, desactivation=false , veri=false;
    private Date ladate= new Date();
    
    @ManagedProperty(value = "#{Declencheur}")
    private Declencheur execution, ee;
    
    private AlertesMBean alertes;
    
    public static final long VINGT_QUATRE_HEURES = 1000 * 60 * 60 * 24;
    // public static final long VINGT_QUATRE_HEURES = 120000;
    
    public LoginMBean() {
        desactivation=false;
        formvehicule = new vehicules();
//        execution = new Declencheur();
//        test task = new test();
//        System.out.println("exe "+execution);
//        task.setExecution(execution);
//        test.startTask();
        //Timer timer = new Timer();
        //timer.schedule(task, new Date(), VINGT_QUATRE_HEURES);
        
    }

    @PostConstruct
    public void chargerElement() {
        desactivation=false;
        user=null;
        nbconnexion=0;
        this.listeVehicule = new ArrayList();
       this.listeVehicule = this.serviceVehicule.selectionnerTout();
        //System.out.println("this "+this.execution);
        //test2();
//        execution.chargerElement();
//        ee=this.execution;
//        test task = new test();
//       System.out.println("exe "+execution);
//       task.setExecution(execution);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 8);
//        calendar.set(Calendar.MINUTE, 30);
//        calendar.set(Calendar.SECOND, 0);
//       Date time = calendar.getTime();
//       Timer timer;
//       timer = new Timer();
        //timer.schedule(task, time, VINGT_QUATRE_HEURES);
        System.out.println("charger");
    }
    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); 
    //scheduler.scheduleAtFixedRate(yourRunnable, 8, 8, TimeUnit.HOURS);
    
    public static String heureToString(Date date) {
         String heure = "" + date.getHours();
        String minute = ""+ date.getMinutes();
        if(heure.length() ==1){
            heure = "0"+heure;
        }
        if(minute.length() == 1){
            minute ="0"+minute;
        }
        
        
        return (heure+ ":" + minute);
    }
    
    public void test2(){
         try
        {
            boolean finished = true;
            while (! finished)
            {
                // Exécution de la tâche
                String param = "21:15";
                Date today = new Date();
                String heure= heureToString(today);
                System.out.println("heure "+heure);
                if(heure.equals(param)){
                    System.out.println("heure de l'execution");
                    execution.declencheur();
                }
//                System.out.println(new Date() + " Execution de ma tache");
//                System.out.println("size "+listeVehicule.size());
                Thread.sleep (2000); // En pause pour deux secondes
            }
        }
        catch (InterruptedException exception){}
    
    }
    
    
    @ManagedProperty(value = "#{ExecutionAutomatique}")
    private ExecutionAutomatique recuparation;
    
    public  void test(){
        //recuparation.declencheur();
        System.out.println("execution commence");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 51);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        //Timer timer = new Timer();
        System.out.println(" time **********"+time);
        Timer timer;
       timer = new Timer();
       Date today = new Date();
       System.out.println(" timer **********"+today);
       // timer.schedule(new ExecutionAutomatique(), 1000, 5000);
        
        timer.schedule(new ExecutionAutomatique(), time, VINGT_QUATRE_HEURES);
        System.out.println("execution == "+recuparation.execution);
//        
//        if(time.equals(today)){
//            System.out.println("ok debut");
//            timer.schedule(new ExecutionAutomatique(), time, VINGT_QUATRE_HEURES);
//        }
        
    }
    
    public void submit() throws IOException {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            Faces.redirect(servletContext.getContextPath() + "/Menu.xhtml");
            this.liste = new ArrayList();
            liste = userService.selectionnerTout();
            int nb=liste.size();
            if(nb!=0){
                for(int i=0; i<=nb-1;i++){
                    if(liste.get(i).getLogin().equals(username) && liste.get(i).getPassword().equals(new Sha256Hash(password).toHex())){
                        user=liste.get(i);
                        info = user.getNom()+" "+user.getPrenom();
                        veri=true;
                        System.out.println("vraie");
                    }
                }
            }
            miseAJour();
        } catch (AuthenticationException e) {
//            if (user != null)
//            {
//                if (user.getActivation() == false) {
//                    //simpleAuthenticationInfo = null;
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Compte désactivé"));
//                } else {
//                    //simpleAuthenticationInfo = null;
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Nom d'utilisateur ou mot de passe incorrect"));
//                }
//            } 
            //else
            user=null;
            this.liste = new ArrayList();
            liste = userService.selectionnerTout();
            int nb=liste.size();
            if(nb!=0){
                for(int i=0; i<=nb-1;i++){
                    if(liste.get(i).getLogin().equals(username) && liste.get(i).getPassword().equals(new Sha256Hash(password).toHex())){
                        user=liste.get(i);
                        info = user.getNom()+" "+user.getPrenom();
                        veri=true;
                    }
                }
            }
            if(user!=null){
                if(user.getActivation()==false){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur!", "Compte désactivé"));
                }
                
            }
            else {
                //simpleAuthenticationInfo = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur!", "Nom d'utilisateur ou mot de passe incorrect"));
                
            }
            
            username="";
                password="";
                nbconnexion = nbconnexion+1;
                System.out.println("nb connexion "+nbconnexion);
                if (nbconnexion>=3){
                    desactivation=true;
                }
            
            //e.printStackTrace();
        }
    }
    
    public void confirmerModification(){
        boolean veri1=false, veri2=false;
        if(user.getPassword().equals(new Sha256Hash(ancienMotDePasse).toHex())){
            System.out.println("bon");
        } else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "L'ancien mot de passe ne correspond pas a cet utilisateur"));
             veri1=true;
        }
        if(nouveauMotDePasse.equals(confirmationMotDePasse)){
            System.out.println("bon");
        } else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Confirmation du mot de passe incorrect!!! "));
             veri2=true;
        }
        
        if(veri1==false && veri2==false){
             Ajax.oncomplete("PF('confirmerEnregistrementModification').show()");
        }
    }
    
    public void miseAJour(){
        this.listeFiche= new ArrayList();
        this.listeFiche = ficheService.selectionnerTout();
        modif = new fiche();
        Date today = new Date();
        int n = this.listeFiche.size();
        if(n!=0){
            for (int i=0;i<=n-1;i++){
                if(listeFiche.get(i).getDateExpiration().compareTo(today)==-1 &&listeFiche.get(i).getEtatFiche()==true ){
                    modif= listeFiche.get(i);
                    modif.setEtatFiche(false);
                    ficheService.modifier(modif);
                }
            }
        }
        
        
        int n1= this.listeVehicule.size();
        if(n1!=0){
            for (int i=0;i<=n1-1;i++){
                if(listeVehicule.get(i).getIdModele().getParametreVidange().compareTo(listeVehicule.get(i).getKilometrageVidange())==-1){
                    BigDecimal nouveau = listeVehicule.get(i).getKilometrageVidange().subtract(listeVehicule.get(i).getIdModele().getParametreVidange());
                    formvehicule = listeVehicule.get(i);
                    formvehicule.setKilometrageVidange(nouveau);
                    serviceVehicule.modifier(formvehicule);
//                    vehiculeService.modifier(formvehicule);
                }
            }
        }
//        recuparation.declencheur();
    }
    
    public void effacer(){
        ancienMotDePasse="";
        confirmationMotDePasse="";
        nouveauMotDePasse="";
    }
    
    public void modifier(){
        modifier = new Utilisateur();
        modifier = user;
        modifier.setPassword(new Sha256Hash(nouveauMotDePasse).toHex());
        userService.modifier(modifier);
        message="Modification du mot de passe effectuée avec succès";
         Ajax.oncomplete("PF('succesOperationModification').show()");
    }
    
    
    public UtilisateurServiceBeanLocal getUserService() {
        return userService;
    }

    public void setUserService(UtilisateurServiceBeanLocal userService) {
        this.userService = userService;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public List<Utilisateur> getListe() {
        return liste;
    }

    public void setListe(List<Utilisateur> liste) {
        this.liste = liste;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAncienMotDePasse() {
        return ancienMotDePasse;
    }

    public void setAncienMotDePasse(String ancienMotDePasse) {
        this.ancienMotDePasse = ancienMotDePasse;
    }

    public String getNouveauMotDePasse() {
        return nouveauMotDePasse;
    }

    public void setNouveauMotDePasse(String nouveauMotDePasse) {
        this.nouveauMotDePasse = nouveauMotDePasse;
    }

    public String getConfirmationMotDePasse() {
        return confirmationMotDePasse;
    }

    public void setConfirmationMotDePasse(String confirmationMotDePasse) {
        this.confirmationMotDePasse = confirmationMotDePasse;
    }

    public Utilisateur getModifier() {
        return modifier;
    }

    public void setModifier(Utilisateur modifier) {
        this.modifier = modifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNbconnexion() {
        return nbconnexion;
    }

    public void setNbconnexion(int nbconnexion) {
        this.nbconnexion = nbconnexion;
    }

    public Boolean getDesactivation() {
        return desactivation;
    }

    public void setDesactivation(Boolean desactivation) {
        this.desactivation = desactivation;
    }

    public Boolean getVeri() {
        return veri;
    }

    public void setVeri(Boolean veri) {
        this.veri = veri;
    }

    public ficheServiceBeanLocal getFicheService() {
        return ficheService;
    }

    public void setFicheService(ficheServiceBeanLocal ficheService) {
        this.ficheService = ficheService;
    }

    public fiche getModif() {
        return modif;
    }

    public void setModif(fiche modif) {
        this.modif = modif;
    }

    public List<fiche> getListeFiche() {
        return listeFiche;
    }

    public void setListeFiche(List<fiche> listeFiche) {
        this.listeFiche = listeFiche;
    }

    public vehiculesServiceBeanLocal getServiceVehicule() {
        return serviceVehicule;
    }

    public void setServiceVehicule(vehiculesServiceBeanLocal serviceVehicule) {
        this.serviceVehicule = serviceVehicule;
    }
    

//    public vehiculesServiceBeanLocal getVehiculeService() {
//        return vehiculeService;
//    }
//
//    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
//        this.vehiculeService = vehiculeService;
//    }

    public vehicules getFormvehicule() {
        return formvehicule;
    }

    public void setFormvehicule(vehicules formvehicule) {
        this.formvehicule = formvehicule;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public ExecutionAutomatique getRecuparation() {
        return recuparation;
    }

    public void setRecuparation(ExecutionAutomatique recuparation) {
        this.recuparation = recuparation;
    }

    public Declencheur getExecution() {
        return execution;
    }

    public void setExecution(Declencheur execution) {
        this.execution = execution;
    }

    public int getVe() {
        return ve;
    }

    public void setVe(int ve) {
        this.ve = ve;
    }

    public Date getLadate() {
        return ladate;
    }

    public void setLadate(Date ladate) {
        this.ladate = ladate;
    }

    public AlertesMBean getAlertes() {
        return alertes;
    }

    public void setAlertes(AlertesMBean alertes) {
        this.alertes = alertes;
    }

    public Declencheur getEe() {
        return ee;
    }

    public void setEe(Declencheur ee) {
        this.ee = ee;
    }

    
    
}
