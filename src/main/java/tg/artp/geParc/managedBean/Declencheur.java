/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.alertes;
import tg.artp.geParc.entities.fiche;
import tg.artp.geParc.entities.parametres;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;
import tg.artp.geParc.services.alertesServiceBeanLocal;
import tg.artp.geParc.services.ficheServiceBeanLocal;
import tg.artp.geParc.services.parametresServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean(name="Declencheur")
@SessionScoped
public class Declencheur implements Serializable {
    @EJB
    private alertesServiceBeanLocal alertesService;
    private alertes formObject, selectObject;
    private List<alertes> listeAlertes, ListeAlerteFinal;
    
    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private vehicules formvehicule;
    private List<vehicules> listeVehicule, listeVehiculeFinal;
    
    @EJB
    private parametresServiceBeanLocal parametresService;
    private List<parametres> listeparametres;
    
    @EJB
    private ficheServiceBeanLocal ficheService;
    private List<fiche> listeFiche, listeFicheFinal;
    
    @EJB
    private UtilisateurServiceBeanLocal UtilisateurService;
    private List<Utilisateur> listeUtilisateur, listeUtilisateurFinal;
    private String username="geparcARTP@gmail.com";
    private String password="artpGeparc";
    private int nbAlertes=0, cpt=0;
    
    private BigDecimal vidange, assurance, visiteTechnique;
    private Date dateDuJour;
    private String message, code="",info="Aucune alerte", info2;
    
    @ManagedProperty(value = "#{permissionManagedBean}")
    private PermissionManagedBean permissionManagedBean;
    
    public Declencheur(){
        formObject = new alertes();
        formvehicule = new vehicules();
    }
    
    @PostConstruct
    public void chargerElement() {
        dateDuJour = new Date(); 
        this.listeAlertes = new ArrayList();
        this.ListeAlerteFinal = new ArrayList();
        this.listeFiche = new ArrayList();
        this.listeFicheFinal = new ArrayList();
        this.listeVehicule = new ArrayList();
        this.listeVehiculeFinal = new ArrayList();
        this.listeparametres = new ArrayList();
        this.listeUtilisateur = new ArrayList();
        this.listeUtilisateurFinal = new ArrayList();
        
        this.listeAlertes = this.alertesService.selectionnerTout();
        this.listeFiche = this.ficheService.selectionnerTout();
        this.listeVehicule = this.vehiculeService.selectionnerTout();
        this.listeparametres = this.parametresService.selectionnerTout();
        this.listeUtilisateur = this.UtilisateurService.selectionnerTout();
        
        int d= this.listeAlertes.size();
        if(d!=0){
            for (int s=d-1;s>=0;s--){
                ListeAlerteFinal.add(listeAlertes.get(s));
            }
        }
        
        
        int nb1= this.listeFiche.size();
        if(nb1!=0){
            for (int i=0; i<=nb1-1;i++){
                if(listeFiche.get(i).getEtatFiche()==true &&
                        (listeFiche.get(i).getDateExpiration().compareTo(dateDuJour)==1 || listeFiche.get(i).getDateExpiration().compareTo(dateDuJour)==0)){
                    listeFicheFinal.add(listeFiche.get(i));
                }
            }
        }
        int nb2= this.listeVehicule.size();
        if(nb2!=0){
            for (int i=0; i<=nb2-1;i++){
                if(listeVehicule.get(i).isEtatVehicule()==true ){
                    listeVehiculeFinal.add(listeVehicule.get(i));
                }
            }
        }
        int nb3= this.listeparametres.size();
        if(nb3!=0){
            for (int i=0; i<=nb3-1;i++){
                if(listeparametres.get(i).getLibelleParametre().equals("VIDANGE")){
                    vidange = listeparametres.get(i).getValeur();
                }
                if(listeparametres.get(i).getLibelleParametre().equals("ASSURANCE")){
                    assurance = listeparametres.get(i).getValeur();
                }
                if(listeparametres.get(i).getLibelleParametre().equals("VISITE TECHNIQUE")){
                    visiteTechnique = listeparametres.get(i).getValeur();
                }
            }
        }
        int nb4=this.listeUtilisateur.size();
        if(nb4!=0){
            for (int i=0; i<=nb4-1;i++){
                if(listeUtilisateur.get(i).getTypeUtilisateur().equals("ADMIN")){
                    listeUtilisateurFinal.add(listeUtilisateur.get(i));
                }
            }
        }
        cpt=listeUtilisateurFinal.size();
        infoAlertes();
        System.out.println("fiche "+listeFicheFinal.size());
    }
    
    private static boolean netIsAvailable() {
    try {
        final URL url = new URL("http://www.google.com");
        final URLConnection conn = url.openConnection();
        conn.connect();
        return true;
        } catch (MalformedURLException e) {
        throw new RuntimeException(e);
        } catch (IOException e) {
        return false;
        }
    }
    
    public void infoAlertes(){
        int nb = listeAlertes.size();
        nbAlertes=0;
        info2 = "("+nbAlertes+")";
        if(nb!=0){
            for (int i=0;i<=nb-1;i++){
                if(listeAlertes.get(i).isConsulter()==false){
                    nbAlertes = nbAlertes+1;
                }
            }
        }
        System.out.println("nb "+nbAlertes);
        if(nbAlertes!=0){
            info = nbAlertes+" nouvelle(s) alerte(s) ";
             info2 = "("+nbAlertes+")";
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
        if(heure.length() ==1){
            heure = "0"+heure;
        }
        if(minute.length() == 1){
            minute ="0"+minute;
        }
        
        
        return (heure+ ":" + minute);
    }
    
     
     public void envoyer(String destinatireEmail, String leMessage){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator(){
                 protected PasswordAuthentication getPasswordAuthentication(){
                     return new PasswordAuthentication(username, password);
                 }   
                }
        );
        
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("geparcARTP@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatireEmail));
            message.setSubject("Alerte geParc");
            message.setText(leMessage);
            
            Transport.send(message);
            System.out.println("message envoyer");
            
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
        
    }
     
    public String etat(Boolean et){
        String letat="Non lus";
        if(et==true){
            letat="Lus";
        }
        return letat;
    }

     public static String dateToString(Date date) {
        String jour = "" + date.getDate();
        String mois = "" + (date.getMonth() + 1);
        String annee = "" + (date.getYear() + 1900);
        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return (jour + "/" + mois + "/" + annee);
    } 
     
     public void declencheurVidange(){
         System.out.println("vidange");
        //alertes pour la vidange
         boolean testConnexion = netIsAvailable();
        int nb1= this.listeVehiculeFinal.size();
        if(nb1!=0){
            
            for(int i= 0;i<=nb1-1;i++){
                message="";
                int div=100;
                BigDecimal diviseur = new BigDecimal(div);
                BigDecimal controle1, controle2;
                controle1 = listeVehiculeFinal.get(i).getIdModele().getParametreVidange().multiply(vidange);
                controle2= controle1.divide(diviseur);
                
                System.out.println("limite "+controle2);
                if(listeVehiculeFinal.get(i).getKilometrageVidange().compareTo(controle2)==1){
                    if(listeVehiculeFinal.get(i).getIdModele().getParametreVidange().compareTo(listeVehiculeFinal.get(i).getKilometrageVidange())==-1){
                         message = "Le véhicule "+listeVehiculeFinal.get(i).getImmatriculation()+" a dépasser le kilométrage prévue pour la prochaine vidange."
                                 + " Ce véhicule a éffectuer "
                            +listeVehiculeFinal.get(i).getKilometrageVidange().subtract( listeVehiculeFinal.get(i).getIdModele().getParametreVidange())+
                            " kilomètre de plus. "; 
                         
                         formvehicule = new vehicules();
                         formvehicule = listeVehiculeFinal.get(i);
                         formvehicule.setKilometrageVidange(listeVehiculeFinal.get(i).getKilometrageVidange().subtract( listeVehiculeFinal.get(i).getIdModele().getParametreVidange()));
                         vehiculeService.modifier(formvehicule);
                    } else {
                         message = "Le véhicule "+listeVehiculeFinal.get(i).getImmatriculation()+" a déjà effectuer les "+vidange
                              +"% de kilométrage prévue pour la prochaine vidange. Il reste "
                            +listeVehiculeFinal.get(i).getIdModele().getParametreVidange().subtract(listeVehiculeFinal.get(i).getKilometrageVidange())+
                            " kilomètre a éffectuer avant d'éffectuer la prochaine vidange. "; 
                    }
                }
                
                if(message.equals("")){
                    
                } else {
                    //renseignement de la base
                    this.rafraichir();
                    formObject= new alertes();
                    //code= Integer.toString(listeAlertes.size()+1);
                    dateDuJour = new Date();
                    //formObject.setIdAlerte(code);
                    formObject.setDateSignalisation(dateDuJour);
                    formObject.setHeureSignalisation(heureToString(dateDuJour));
                    formObject.setMessage(message);
                    formObject.setConsulter(false);
                    formObject.setIdVehicule(listeVehiculeFinal.get(i));
                    alertesService.ajouter(formObject);
                    //envoi des alertes
                    int cpt=listeUtilisateurFinal.size();
                    if(cpt!=0){
                        if(testConnexion==true){
                            for(int p=0;p<=cpt-1;p++){
                            envoyer(listeUtilisateurFinal.get(p).getEmail(), message);
                            }
                        }
                    }
                    
                }
                
            }
        }
        this.rafraichir();
     }
     
     public void declencheurAssVT(){
         System.out.println("assurance visite technique");
          //alertes pour les asssurances et visite techniques
         boolean testConnexion = netIsAvailable();
        int nb2= this.listeFicheFinal.size();
         System.out.println("nb Fiche "+nb2);
        if(nb2!=0){
            
            for(int i= 0;i<=nb2-1;i++){
                message="";
                long diff= listeFicheFinal.get(i).getDateExpiration().getTime() - dateDuJour.getTime();
                System.out.println("diff "+diff);
                long diff1= diff/(1000*60*60*24);
                System.out.println("diff1 "+diff1);
                BigDecimal difference  = new BigDecimal(diff1);
                if(listeFicheFinal.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("ASSURANCE")){
                    message = "";
                    if(difference.compareTo(assurance)== -1 || difference.compareTo(assurance)== 0){
                    message="L'ASSURANCE du véhicule "+listeFicheFinal.get(i).getIdVehicule().getImmatriculation()+" expire dans "+diff1+
                            " jour(s). Elle expire le "+dateToString(listeFicheFinal.get(i).getDateExpiration())+" .";
                    }
                    
                     if(message.equals("")){
                    
                    } else {
                    //renseignement de la base
                    formObject= new alertes();
                    //code= Integer.toString(listeAlertes.size()+1);
                    dateDuJour = new Date();
                    //formObject.setIdAlerte(code);
                    formObject.setDateSignalisation(dateDuJour);
                    formObject.setHeureSignalisation(heureToString(dateDuJour));
                    formObject.setMessage(message);
                    formObject.setConsulter(false);
                    formObject.setIdVehicule(listeFicheFinal.get(i).getIdVehicule());
                    alertesService.ajouter(formObject);
                    //envoi des alertes
                    //int cpt=listeUtilisateurFinal.size();
                         //System.out.println("nombre user "+cpt);
                    if(cpt!=0){
                        if(testConnexion==true){
                            for(int p=0;p<=cpt-1;p++){
                            //System.out.println("email "+listeUtilisateurFinal.get(i).getEmail());
                            envoyer(listeUtilisateurFinal.get(p).getEmail(), message);
                            }
                        }
                    }
                    
                }
                    
                    
                } else if(listeFicheFinal.get(i).getIdTypeFiche().getLibelleTypeFiche().equals("VISITE TECHNIQUE")){
                    message = "";
                    if(difference.compareTo(visiteTechnique)== -1 || difference.compareTo(visiteTechnique)== 0){
                    message="La VISITE TECHNIQUE du véhicule "+listeFicheFinal.get(i).getIdVehicule().getImmatriculation()+" expire dans "+diff1+
                            " jour(s). Elle expire le "+dateToString(listeFicheFinal.get(i).getDateExpiration())+" .";
                    }
                    
                    if(message.equals("")){
                    
                    } else {
                    //renseignement de la base
                    formObject= new alertes();
                    //code= Integer.toString(listeAlertes.size()+1);
                    dateDuJour = new Date();
                    //formObject.setIdAlerte(code);
                    formObject.setDateSignalisation(dateDuJour);
                    formObject.setHeureSignalisation(heureToString(dateDuJour));
                    formObject.setMessage(message);
                    formObject.setConsulter(false);
                    formObject.setIdVehicule(listeFicheFinal.get(i).getIdVehicule());
                    alertesService.ajouter(formObject);
                    //envoi des alertes
                    int cpt=listeUtilisateurFinal.size();
                    if(cpt!=0){
                        if(testConnexion==true){
                            for(int p=0;p<=cpt-1;p++){
                            //System.out.println("email "+listeUtilisateurFinal.get(i).getEmail());
                                envoyer(listeUtilisateurFinal.get(p).getEmail(), message);
                            }
                        }
                    }
                    
                }
                    
                }
                
            }
        }
         this.rafraichir();
     }
     
     
    public void declencheur(){
        System.out.println("demarer");
        chargerElement();
        declencheurVidange();
        declencheurAssVT();
        this.rafraichir();
    }
    
    
    public void rowSelect(){
        formObject=selectObject;
        formObject.setConsulter(true);
        alertesService.modifier(formObject);
        this.chargerElement();
    }
    
    public void rafraichir(){
        chargerElement();
        formObject=null;
        selectObject=null;
    }
    
    //getters and setters

    public alertesServiceBeanLocal getAlertesService() {
        return alertesService;
    }

    public void setAlertesService(alertesServiceBeanLocal alertesService) {
        this.alertesService = alertesService;
    }

    public alertes getFormObject() {
        return formObject;
    }

    public void setFormObject(alertes formObject) {
        this.formObject = formObject;
    }

    public alertes getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(alertes selectObject) {
        this.selectObject = selectObject;
    }

    public List<alertes> getListeAlertes() {
        return listeAlertes;
    }

    public void setListeAlertes(List<alertes> listeAlertes) {
        this.listeAlertes = listeAlertes;
    }

    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

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

    public List<vehicules> getListeVehiculeFinal() {
        return listeVehiculeFinal;
    }

    public void setListeVehiculeFinal(List<vehicules> listeVehiculeFinal) {
        this.listeVehiculeFinal = listeVehiculeFinal;
    }

    public parametresServiceBeanLocal getParametresService() {
        return parametresService;
    }

    public void setParametresService(parametresServiceBeanLocal parametresService) {
        this.parametresService = parametresService;
    }

    public List<parametres> getListeparametres() {
        return listeparametres;
    }

    public void setListeparametres(List<parametres> listeparametres) {
        this.listeparametres = listeparametres;
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

    public List<fiche> getListeFicheFinal() {
        return listeFicheFinal;
    }

    public void setListeFicheFinal(List<fiche> listeFicheFinal) {
        this.listeFicheFinal = listeFicheFinal;
    }

    public UtilisateurServiceBeanLocal getUtilisateurService() {
        return UtilisateurService;
    }

    public void setUtilisateurService(UtilisateurServiceBeanLocal UtilisateurService) {
        this.UtilisateurService = UtilisateurService;
    }

    public List<Utilisateur> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
        this.listeUtilisateur = listeUtilisateur;
    }

    public List<Utilisateur> getListeUtilisateurFinal() {
        return listeUtilisateurFinal;
    }

    public void setListeUtilisateurFinal(List<Utilisateur> listeUtilisateurFinal) {
        this.listeUtilisateurFinal = listeUtilisateurFinal;
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

    public BigDecimal getVidange() {
        return vidange;
    }

    public void setVidange(BigDecimal vidange) {
        this.vidange = vidange;
    }

    public BigDecimal getAssurance() {
        return assurance;
    }

    public void setAssurance(BigDecimal assurance) {
        this.assurance = assurance;
    }

    public BigDecimal getVisiteTechnique() {
        return visiteTechnique;
    }

    public void setVisiteTechnique(BigDecimal visiteTechnique) {
        this.visiteTechnique = visiteTechnique;
    }

    public Date getDateDuJour() {
        return dateDuJour;
    }

    public void setDateDuJour(Date dateDuJour) {
        this.dateDuJour = dateDuJour;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNbAlertes() {
        return nbAlertes;
    }

    public void setNbAlertes(int nbAlertes) {
        this.nbAlertes = nbAlertes;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public List<alertes> getListeAlerteFinal() {
        return ListeAlerteFinal;
    }

    public void setListeAlerteFinal(List<alertes> ListeAlerteFinal) {
        this.ListeAlerteFinal = ListeAlerteFinal;
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public PermissionManagedBean getPermissionManagedBean() {
        return permissionManagedBean;
    }

    public void setPermissionManagedBean(PermissionManagedBean permissionManagedBean) {
        this.permissionManagedBean = permissionManagedBean;
    }

}
