/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.shiro.crypto.hash.Sha256Hash;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class MotDePasseOublierMBean implements Serializable {
    @EJB
    private UtilisateurServiceBeanLocal UtilisateurService;
    private List<Utilisateur> listeUtilisateur;
    private String email,motDePasse,Nom,Prenom,login,monMessage;
    private boolean enregistrer=false;
    private String username="geparcARTP@gmail.com";
    private String password="artpGeparc";
    private String newPass="";
    private Utilisateur formObject;
    
    
    public MotDePasseOublierMBean(){
        
    }
    
    @PostConstruct
    public void chargerElement() {
       this.listeUtilisateur = new ArrayList();
        this.listeUtilisateur = this.UtilisateurService.selectionnerTout(); 
    }
    
    
    public void verification(){
        int nb = listeUtilisateur.size();
        if(nb != 0){
            for(int i=0;i<=nb-1;i++){
                if(listeUtilisateur.get(i).getEmail().equals(email)){
                    Nom = listeUtilisateur.get(i).getNom();
                    Prenom = listeUtilisateur.get(i).getPrenom();
                    motDePasse = listeUtilisateur.get(i).getPassword();
                    login = listeUtilisateur.get(i).getLogin();
                    
                    
                    float n1=0, n2=0;
                    int nf1=0, nf2=0;
                    int np= Prenom.length();
                    int nn=Nom.length();
                    if(np!=0 && nn!=0){
                        n1= np/3;
                        nf1=(int)n1;
                        n2=nn/3;
                        nf2=(int)n2;
                    }
                    
                    String ta = String.valueOf(nb);
                    if(nf1!=0 && nf2!=0){
                         newPass = Prenom.substring(0, nf1)+"0"+ta+"0"+Nom.substring(0, nf2).toUpperCase()+"geparc";
                    }
                   
                    formObject = new Utilisateur();
                    formObject = listeUtilisateur.get(i);
                    formObject.setPassword(new Sha256Hash(newPass).toHex());
                    UtilisateurService.modifier(formObject);
                    
                    enregistrer = true;
                }
            }
        }
        
        boolean testConnexion = netIsAvailable();
        
        if(testConnexion==false){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Vous n'êtes pas connecter a internet"));
        } else {
            if(enregistrer == true){
                monMessage = "Monsieur/Madame "+Nom+" "+Prenom+" votre login est: "+login+" et votre mot de passe a été réinitialiser. Votre nouveau mot de passe est : "+newPass+". Veuillez changer votre mot de passe lors de votre prochaine connexion pour des raison de sécuriter. ";
                envoyer();
                effacer();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERREUR!", "Cet email n'est lié à aucun compte"));
                System.out.println("Ce mail n'est lié a aucun compte");
            }
        }
        
        
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
    
    public void effacer(){
        email = "";
        Nom = "";
        Prenom = "";
        login = "";
        motDePasse = "";
        monMessage = "";
    }
    
    public void envoyer(){
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
                    InternetAddress.parse(email));
            message.setSubject("Mot de passe Oublier");
            message.setText(monMessage);
            
            Transport.send(message);
            System.out.println("message envoyer");
            
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMonMessage() {
        return monMessage;
    }

    public void setMonMessage(String monMessage) {
        this.monMessage = monMessage;
    }

    public boolean isEnregistrer() {
        return enregistrer;
    }

    public void setEnregistrer(boolean enregistrer) {
        this.enregistrer = enregistrer;
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

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    
}
