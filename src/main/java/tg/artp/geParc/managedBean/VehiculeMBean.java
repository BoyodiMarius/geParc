/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.omnifaces.util.Ajax;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import tg.artp.geParc.entities.marques;
import tg.artp.geParc.entities.modele;
import tg.artp.geParc.entities.typeCarburant;
import tg.artp.geParc.entities.typeVehicules;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.report.ConteneurRequeteManagedBean;
import tg.artp.geParc.report.EtatUtils;
import tg.artp.geParc.report.ExportManagedBean;
import tg.artp.geParc.report.files.EtatVehicule;
import tg.artp.geParc.services.marquesServiceBeanLocal;
import tg.artp.geParc.services.modeleServiceBeanLocal;
import tg.artp.geParc.services.typeCarburantServiceBeanLocal;
import tg.artp.geParc.services.typeVehiculeServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class VehiculeMBean implements Serializable {

    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;
    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private vehicules formObject, selectObject, supression, modif;
    private List<vehicules> listeVehicule, listeControle, listeControle2, listeFiltreVehicule,listeVehiculeCode;

    @EJB
    private modeleServiceBeanLocal modeleService;
    private modele formObjectModele, selectObjectModele, modifModele;
    private List<modele> listeModele, listeModeleFiltre, listeControleModele;

    @EJB
    private marquesServiceBeanLocal marqueService;
    private List<marques> listeMarque, listeMarqueFiltre;
    private marques marques;

    @EJB
    private typeVehiculeServiceBeanLocal typeVehicule;
    private List<typeVehicules> listeTypevehicule, listeTypevehiculeFiltre;
    private typeVehicules typeVehicules;

    @EJB
    private typeCarburantServiceBeanLocal typeCarburantService;
    private typeCarburant formTypeCarburant;
    private List<typeCarburant> listeTypeCarburant;
    
    
    private String message, message1;
    private String id = "VEH", id2 = "MOD";
    private String code = "";
    int index, index2 = 0;
    private boolean desactiver = true;
    private boolean desactivation = true;
    private boolean  griserChamp = false;
    //private Part image1, image2, image3, image4, image5;
    private UploadedFile resume, image1 = null, image2 = null, image3 = null, image4 = null, image5 = null;

    String numeroChassis = "", immatriculation = "", couleur = "", img1, img2, img3, img4, img5, modele, liens;
    private BigDecimal paramAmortissement = null, kilometrageActuel = null;
    private modele idModele = null;
    private boolean newModele = false;
    
    private List<String> imagesVisualiser;
    ServletContext servletContext;
    
    private String idMarque, idTypeVehicule, idTypeCarburant;
    
    private marques IDMarque;
    private  typeVehicules IDTypeVehicule;
    private String designation;
    private BigDecimal parametreVidange;
    private typeCarburant typecarburant;
    
    

    public VehiculeMBean() {
        this.formObject = new vehicules();
    }

    @PostConstruct
    public void chargerElement() {
        this.listeVehicule = new ArrayList();
        this.listeControle = new ArrayList();
        this.listeControle2 = new ArrayList();
        this.listeFiltreVehicule = new ArrayList();
        this.listeVehiculeCode  = new ArrayList();
//        this.listeVehicule = this.vehiculeService.selectionnerTout();
//        this.listeControle = this.vehiculeService.selectionnerTout();
//        this.listeControle2 = this.vehiculeService.selectionnerTout();
//        this.listeFiltreVehicule = this.vehiculeService.selectionnerTout();
        this.listeVehiculeCode = this.vehiculeService.selectionnerTout();

        int nb = listeVehiculeCode.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                if (listeVehiculeCode.get(i).isEtatVehicule() == true) {
                    listeVehicule.add(listeVehiculeCode.get(i));
                     listeControle.add(listeVehiculeCode.get(i));
                    listeControle2.add(listeVehiculeCode.get(i));
                    listeFiltreVehicule.add(listeVehiculeCode.get(i));
                } 
                
            }
        }

        this.listeMarque = new ArrayList();
        this.listeMarqueFiltre = new ArrayList();
        this.listeMarque = this.marqueService.selectionnerTout();
        this.listeMarqueFiltre = this.marqueService.selectionnerTout();

        this.listeTypevehicule = new ArrayList();
        this.listeTypevehiculeFiltre = new ArrayList();
        this.listeTypevehicule = this.typeVehicule.selectionnerTout();
        this.listeTypevehiculeFiltre = this.typeVehicule.selectionnerTout();

        this.listeModele = new ArrayList();
        this.listeModele = this.modeleService.selectionnerTout();
        this.listeModeleFiltre = new ArrayList();
        this.listeControleModele = new ArrayList();
        this.listeModeleFiltre = this.modeleService.selectionnerTout();
        this.listeControleModele = this.modeleService.selectionnerTout();
        desactivation = true;
       
        
//        int verif = listeModeleFiltre.size();
//        if(verif==0){
//            newModele=false;
//        } else {
//            newModele = true;
//        }
    }

    public boolean isGriserChamp() {
        return griserChamp;
    }

    public void setGriserChamp(boolean griserChamp) {
        this.griserChamp = griserChamp;
    }

    
    
    public void activation() {
        desactivation = false;
        griserChamp = true;
        System.out.println("immatri " + selectObject.getImmatriculation());
    }

    public void rowSelect() {
        this.listeControle2 = new ArrayList();
        int comp = listeVehicule.size();
        if(comp!=0){
            for(int i=0; i<=comp-1;i++){
                if(listeVehicule.get(i).getImmatriculation().equals(selectObject.getImmatriculation())){
                    System.out.println("ok");
                } else {
                    listeControle2.add(listeVehicule.get(i));
                }
            }
        }
        
        this.listeControle2 = this.vehiculeService.selectionnerTout();
        this.index2 = this.listeVehicule.indexOf(this.selectObject);
        listeControle2.remove(index2);
        //listeMarque.
        formObject = selectObject;
        supression = selectObject;
        numeroChassis = selectObject.getNumeroChassis();
        immatriculation = selectObject.getImmatriculation();
        couleur = selectObject.getCouleur();
        idModele = selectObject.getIdModele();
        typeVehicules = selectObject.getIdModele().getIdTypeVehicule();
        marques = selectObject.getIdModele().getIdMarque();
        paramAmortissement = selectObject.getParametreAmortissement();
        kilometrageActuel = selectObject.getKilometrageActuel();
        remplirListeModele();
        idModele = selectObject.getIdModele();
        typeVehicules = selectObject.getIdModele().getIdTypeVehicule();
        marques = selectObject.getIdModele().getIdMarque();
        modif = selectObject;
        test();
        desactiver = false;

    }

    public void modiSuivant() {
        System.out.println("chassis " + selectObject.getNumeroChassis());
        formObject = selectObject;
        supression = selectObject;
        numeroChassis = selectObject.getNumeroChassis();
        immatriculation = selectObject.getImmatriculation();
        couleur = selectObject.getCouleur();
        idModele = selectObject.getIdModele();
        typeVehicules = selectObject.getIdModele().getIdTypeVehicule();
        marques = selectObject.getIdModele().getIdMarque();
        paramAmortissement = selectObject.getParametreAmortissement();
        kilometrageActuel = selectObject.getKilometrageActuel();
        idModele = selectObject.getIdModele();
        typeVehicules = selectObject.getIdModele().getIdTypeVehicule();
        marques = selectObject.getIdModele().getIdMarque();
        modif = selectObject;
        //test();
        desactiver = false;
        this.listeControle2 = new ArrayList();
        
        int comp = listeVehicule.size();
        if(comp!=0){
            for(int i=0; i<=comp-1;i++){
                if(listeVehicule.get(i).getImmatriculation().equals(selectObject.getImmatriculation())){
                    System.out.println("ok");
                } else {
                    listeControle2.add(listeVehicule.get(i));
                }
            }
        }
        
        Ajax.oncomplete("PF('wiz').loadStep (PF('wiz').cfg.steps [1], true)");

    }

    
    public void nouveauModele(){
         boolean veri1=false, veri2= false;
        if(marques==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez choisir la marque. "));
            veri1=true;
        }
        if(typeVehicules==null){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Veuillez choisir le type de véhicule"));
            veri2=true;
        }
        if(veri1==false && veri2==false){
            Ajax.oncomplete("PF('ajoutModeleDlg').show()");
        }
    }
    
    public void AjouterDialog() {
       
    }
    
    public void modifImage() {

    }

    public void test() {
        this.imagesVisualiser = new ArrayList();
        liens = selectObject != null && selectObject.getImageAvantVehicule() != null ? selectObject.getImageAvantVehicule().substring(9, 28) : "";
        imagesVisualiser.add(selectObject != null && selectObject.getImageAvantVehicule() != null ? selectObject.getImageAvantVehicule().substring(43) : "");
        imagesVisualiser.add(selectObject != null && selectObject.getImageArriereVehicule() != null ? selectObject.getImageArriereVehicule().substring(43) : "");
        imagesVisualiser.add(selectObject != null && selectObject.getImageDroitVehicule() != null ? selectObject.getImageDroitVehicule().substring(43) : "");
        imagesVisualiser.add(selectObject != null && selectObject.getImageGaucheVehicule() != null ? selectObject.getImageGaucheVehicule().substring(43) : "");
        imagesVisualiser.add(selectObject != null && selectObject.getImageInterieurVehicule() != null ? selectObject.getImageInterieurVehicule().substring(43) : "");
        Ajax.oncomplete("PF('visualiserDlg').show()");
//        for (String image : imagesVisualiser) {
//            System.out.println("images " + image);
//        }
    }

    public void remplirListeVehiculeDispo() {
        try {
            this.listeFiltreVehicule = new ArrayList();
            int nb = listeVehicule.size();

            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    if (listeVehicule.get(i).getIdModele().getDesignation().equals(idModele.getDesignation())) {
                        listeFiltreVehicule.add(listeVehicule.get(i));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desactivationBouton() {

    }

    public void filtrer(){
        System.out.println("ok");
         try {
        if(marques != null && typeVehicules == null && idModele == null){
            this.listeFiltreVehicule = new ArrayList();
            //this.listeTypevehiculeFiltre = new ArrayList();
            this.listeModeleFiltre = new ArrayList();
            int n1=listeVehicule.size();
            int n2 = listeModele.size();
            if(n1!=0){
                for(int i=0; i<=n1-1;i++){
                    if(marques.getLibelleMarque().equals(listeVehicule.get(i).getIdModele().getIdMarque().getLibelleMarque())){
                        listeFiltreVehicule.add(listeVehicule.get(i));
                    }
                }
            }
            if(n2!=0){
                for(int i=0; i<=n2-1;i++){
                    if(marques.getLibelleMarque().equals(listeModele.get(i).getIdMarque().getLibelleMarque())){
                        //typeVehicules tyveh;
                        //tyveh= typeVehicule.selectionner(listeModele.get(i).getIdTypeVehicule().getIdTypeVehicule());
                        //listeTypevehiculeFiltre.add(tyveh);
                        listeModeleFiltre.add(listeModele.get(i));
                    }
                }
            }
           
        }
        
        if(marques == null && typeVehicules != null && idModele == null){
            this.listeFiltreVehicule = new ArrayList();
            this.listeModeleFiltre = new ArrayList();
            int n1=listeVehicule.size();
            int n2 = listeModele.size();
            if(n1!=0){
                for(int i=0; i<=n1-1;i++){
                   if(typeVehicules.getLibelleTypeVehicule().equals(listeVehicule.get(i).getIdModele().getIdTypeVehicule().getLibelleTypeVehicule())){
                       listeFiltreVehicule.add(listeVehicule.get(i));
                   } 
                }
            }
            if(n2!=0){
                for(int i=0; i<=n2-1;i++){
                    if(typeVehicules.getLibelleTypeVehicule().equals(listeModele.get(i).getIdTypeVehicule().getLibelleTypeVehicule())){
                        listeModeleFiltre.add(listeModele.get(i));
                    }
                }
            }
        }
        
        if(marques == null && typeVehicules == null && idModele != null){
           this.listeFiltreVehicule = new ArrayList();
            int n1=listeVehicule.size();
            if(n1!=0){
                for(int i=0; i<=n1-1;i++){
                  if(idModele.getDesignation().equals(listeVehicule.get(i).getIdModele().getDesignation())){
                      listeFiltreVehicule.add(listeVehicule.get(i));
                  }  
                }
            }
        }
        
        if(marques != null && typeVehicules != null && idModele == null){
            this.listeFiltreVehicule = new ArrayList();
            this.listeModeleFiltre = new ArrayList();
            int n1=listeVehicule.size();
            int n2 = listeModele.size();
            if(n1!=0){
                for(int i=0; i<=n1-1;i++){
                   if(marques.getLibelleMarque().equals(listeVehicule.get(i).getIdModele().getIdMarque().getLibelleMarque()) &&
                           typeVehicules.getLibelleTypeVehicule().equals(listeVehicule.get(i).getIdModele().getIdTypeVehicule().getLibelleTypeVehicule())) {
                       listeFiltreVehicule.add(listeVehicule.get(i));
                   }
                }
            }
            if(n2!=0){
                for(int i=0; i<=n2-1;i++){
                    if(marques.getLibelleMarque().equals(listeModele.get(i).getIdMarque().getLibelleMarque()) &&
                        typeVehicules.getLibelleTypeVehicule().equals(listeModele.get(i).getIdTypeVehicule().getLibelleTypeVehicule())){
                        listeModeleFiltre.add(listeModele.get(i));
                    }
                }
            }
        }
        
        if(marques == null && typeVehicules != null && idModele != null){
          this.listeFiltreVehicule = new ArrayList();
            int n1=listeVehicule.size();
            if(n1!=0){
                for(int i=0; i<=n1-1;i++){
                    if(typeVehicules.getLibelleTypeVehicule().equals(listeVehicule.get(i).getIdModele().getIdTypeVehicule().getLibelleTypeVehicule()) &&
                            idModele.getDesignation().equals(listeVehicule.get(i).getIdModele().getDesignation())){
                        listeFiltreVehicule.add(listeVehicule.get(i));
                    }
                }
            }
        }
        
        if(marques != null && typeVehicules != null && idModele != null){
           this.listeFiltreVehicule = new ArrayList();
            int n1=listeVehicule.size();
            if(n1!=0){
                for(int i=0; i<=n1-1;i++){
                     if(typeVehicules.getLibelleTypeVehicule().equals(listeVehicule.get(i).getIdModele().getIdTypeVehicule().getLibelleTypeVehicule()) &&
                            idModele.getDesignation().equals(listeVehicule.get(i).getIdModele().getDesignation()) &&
                             marques.getLibelleMarque().equals(listeVehicule.get(i).getIdModele().getIdMarque().getLibelleMarque())){
                        listeFiltreVehicule.add(listeVehicule.get(i));
                    }
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void remplirListeModele() {
//        String idTyVeh = typeVehicules.getIdTypeVehicule();
//        String idMar = marques.getIdMarque();
        
//        if(typeVehicules != null && marques != null){
//            System.out.println("vrai");
//            newModele=false;
//        } else {
//            System.out.println("ok");
//        }
//        
        try {
            this.listeModeleFiltre = new ArrayList();
            int nb = listeModele.size();

            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    if (listeModele.get(i).getIdTypeVehicule().getIdTypeVehicule().equals(typeVehicules.getIdTypeVehicule()) && listeModele.get(i).getIdMarque().getIdMarque().equals(marques.getIdMarque())) {
                        listeModeleFiltre.add(listeModele.get(i));
                    }
                }
            }
            int taille = listeModeleFiltre.size();
            System.out.println("taille "+taille);
            if (taille==0){
                System.out.println("ok");
                 newModele=false;
                 IDMarque = marques;
                 IDTypeVehicule = typeVehicules;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void affiche() {
        int nb = listeModeleFiltre.size();
        if (nb != 0) {
            for (int i = 0; i <= nb - 1; i++) {
                System.out.println("Modele " + listeModeleFiltre.get(i).getDesignation());
            }
        } else {
            System.out.println("null");
        }

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
            vehiculeService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retirer() {
        try {
            selectObject.setEtatVehicule(false);
            vehiculeService.modifier(selectObject);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Ce véhicule est retirer de votre parc"));
            Ajax.oncomplete("PF('wiz').loadStep (PF('wiz').cfg.steps [0], true)");
            this.rafraichir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier() {
        try {

            selectObject.setNumeroChassis(numeroChassis.trim());
            selectObject.setImmatriculation(immatriculation.trim().toUpperCase());
            selectObject.setCouleur(couleur.trim().toUpperCase());
            selectObject.setIdModele(idModele);
            selectObject.setKilometrageActuel(kilometrageActuel);
            selectObject.setParametreAmortissement(paramAmortissement);
            if (image1.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg1();
            }
            if (image2.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg2();
            }
            if (image3.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg3();
            }
            if (image4.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg4();
            }
            if (image5.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg5();
            }
            vehiculeService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouter() {
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeVehiculeCode.size();
//            if (i == 0) {
//                code = id + "01";
//            } else if (i <= 8) {
//                code = listeVehiculeCode.get(i - 1).getIdVehicule();
//                code = code.substring(4);
//                ii = Integer.parseInt(code) + 1;
//                code = id + "0" + Integer.toString(ii);
//            } else {
//                code = listeVehiculeCode.get(i - 1).getIdVehicule();
//                code = code.substring(3);
//                ii = Integer.parseInt(code) + 1;
//                code = id + Integer.toString(ii);
//            }
            
            if (i == 0){
                code =id+"000001";
            } else if(i<=8){
                code = listeVehiculeCode.get(i-1).getIdVehicule();
                code = code.substring(8);
                ii = Integer.parseInt(code)+1 ;
                code = id+"00000" + Integer.toString(ii);
            } else if(i<=98) {
                code = listeVehiculeCode.get(i-1).getIdVehicule();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0000" + Integer.toString(ii);
            } else if(i<=998) {
                code = listeVehiculeCode.get(i-1).getIdVehicule();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = id+"000" + Integer.toString(ii);
            } else if(i<=9998)  {
                code = listeVehiculeCode.get(i-1).getIdVehicule();
                code = code.substring(5);
                ii = Integer.parseInt(code)+1 ;
                code = id+"00" + Integer.toString(ii);
            } else if(i<=99998) {
                code = listeVehiculeCode.get(i-1).getIdVehicule();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id+"0" + Integer.toString(ii);
            } else{
                code = listeVehiculeCode.get(i-1).getIdVehicule();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id + Integer.toString(ii);
            }
            
            
            //new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\" + code.toLowerCase()).mkdir();
            formObject.setIdVehicule(code);
            formObject.setNumeroChassis(numeroChassis.trim());
            formObject.setImmatriculation(immatriculation.trim().toUpperCase());
            formObject.setCouleur(couleur.trim().toUpperCase());
            formObject.setEtatActuel(true);
            formObject.setEtatVehicule(true);
            if (image1.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg1();
            }
            if (image2.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg2();
            }
            if (image3.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg3();
            }
            if (image4.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg4();
            }
            if (image5.getFileName().equals("")) {
                System.out.println("null");
            } else {
                uploaderImg5();
            }
            formObject.setIdModele(idModele);
            formObject.setKilometrageActuel(kilometrageActuel);
            formObject.setKilometrageVidange(kilometrageActuel);
            formObject.setParametreAmortissement(paramAmortissement);
            this.vehiculeService.ajouter(formObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrer() {
        boolean veri1 = false, veri2 = false, veri3 = false, veri4 = false, veri5 = false, veri6 = false, veri7 = false;
        if (numeroChassis.equals("") || numeroChassis.trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le numero de chassis ne pas être vide"));
            veri1 = true;
        }

        if (immatriculation.equals("") || immatriculation.trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le numero d'immatriculation ne pas être vide"));
            veri2 = true;
        }

        if (couleur.equals("") || couleur.trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "La couleur ne pas être vide"));
            veri3 = true;
        }

        if (paramAmortissement == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le parametre d'amortissement ne pas être vide"));
            veri4 = true;
        }

        if (selectObject != null) {

            if (kilometrageActuel == null || kilometrageActuel.compareTo(selectObject.getKilometrageActuel()) == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le kilometrage actuel ne pas être inféririeur au kilométrage existant"));
                veri5 = true;
            }
            //listeControle2.remove();
            int nb = listeControle2.size();
            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    if (listeControle2.get(i).getNumeroChassis().equals(numeroChassis.trim())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce numero de chassis existe déjà"));
                        veri7 = true;
                    }
                    if (listeControle2.get(i).getImmatriculation().equals(immatriculation.trim().toUpperCase())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Cette immatriculation existe déjà"));
                        veri7 = true;
                    }
                }

            }

            if (veri1 == false && veri2 == false && veri3 == false && veri4 == false && veri5 == false && veri6 == false && veri7 == false) {
                modifier();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Modification effectué avec succès"));
            }
        } else {

            if (kilometrageActuel == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le kilometrage actuel ne pas être vide"));
                veri5 = true;
            }

            if (idModele == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le modèle n'est pas choisis"));
                veri6 = true;
            }

            int nb = listeControle.size();
            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    if (listeControle.get(i).getNumeroChassis().equals(numeroChassis.trim())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Ce numero de chassis existe déjà"));
                        veri7 = true;
                    }
                    if (listeControle.get(i).getImmatriculation().equals(immatriculation.trim().toUpperCase())) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Cette immatriculation existe déjà"));
                        veri7 = true;
                    }
                }

            }

            if (veri1 == false && veri2 == false && veri3 == false && veri4 == false && veri5 == false && veri6 == false && veri7 == false) {
                ajouter();
                effacer();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès!", "Enregistrement effectué avec succès"));
                message = "Enregistrement effectué avec succès";
            }

        }
//        Ajax.oncomplete("PF('wiz').loadStep (PF('wiz').cfg.steps [0], true)");
//        Ajax.oncomplete("PF('succesOperationVehicule').show()");
        this.rafraichir();
    }

    public void supprimer() {
        if (selectObject == null) {
            return;
        }
        try {
            suppression();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationVehicule').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index = 0;
    }

    public void rafraichir() {
        this.chargerElement();
        this.effacer();
        Ajax.oncomplete("PF('wiz').loadStep (PF('wiz').cfg.steps [0], true)");
        desactiver = true;
    }

    public void effacer() {
        formObject = new vehicules();
        numeroChassis = "";
        immatriculation = "";
        couleur = "";
        paramAmortissement = null;
        kilometrageActuel = null;
        selectObject = null;
        idModele = null;
        marques =null;
        typeVehicules = null;
        effacerModele();
    }

    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new vehicules();
    }

    public void confirmerEnregistrement() {
        Ajax.oncomplete("PF('confirmerEnregistrementVehicule').show()");
        //rafraichir();
    }

    public void confirmerSuppression() {
        if (selectObject == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionVehicule').show()");
        }
    }

    public void uploaderImg1() {
        try {
            InputStream in = image1.getInputstream();
            File f = new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\imagesDesVehicules\\" + image1.getFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            img1 = "resources/assets/images/imagesDesVehicules/" + image1.getFileName();
            if (selectObject != null) {
                this.selectObject.setImageAvantVehicule(img1);
            } else {
                this.formObject.setImageAvantVehicule(img1);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void uploaderImg2() {
        try {
            InputStream in = image2.getInputstream();
            //File f = new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\" + code.toLowerCase() + "\\" + image2.getFileName());
            File f = new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\imagesDesVehicules\\" + image2.getFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            img2 = "resources/assets/images/imagesDesVehicules/" + image2.getFileName();
            if (selectObject != null) {
                this.selectObject.setImageArriereVehicule(img2);
            } else {
                this.formObject.setImageArriereVehicule(img2);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void uploaderImg3() {
        try {
            InputStream in = image3.getInputstream();
            File f = new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\imagesDesVehicules\\" + image3.getFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            img3 = "resources/assets/images/imagesDesVehicules/" + image3.getFileName();
            if (selectObject != null) {
                this.selectObject.setImageDroitVehicule(img3);
            } else {
                this.formObject.setImageDroitVehicule(img3);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void uploaderImg4() {
        try {
            InputStream in = image4.getInputstream();
            File f = new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\imagesDesVehicules\\" + image4.getFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            img4 = "resources/assets/images/imagesDesVehicules/" + image4.getFileName();
            if (selectObject != null) {
                this.selectObject.setImageGaucheVehicule(img4);
            } else {
                this.formObject.setImageGaucheVehicule(img4);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void uploaderImg5() {
        try {
            InputStream in = image5.getInputstream();
            File f = new File("C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\imagesDesVehicules\\" + image5.getFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            img5 = "resources/assets/images/imagesDesVehicules/" + image5.getFileName();
            if (selectObject != null) {
                this.selectObject.setImageInterieurVehicule(img5);
            } else {
                this.formObject.setImageInterieurVehicule(img5);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public List<EtatVehicule> construireListeVehicule(List<vehicules> liste) {
        List<EtatVehicule> listeEtat = new ArrayList();
        EtatVehicule etat;
        for (vehicules ev : liste) {
            etat = new EtatVehicule(ev.getIdModele().getIdTypeVehicule().getLibelleTypeVehicule(), ev.getIdModele().getIdMarque().getLibelleMarque(),
                    ev.getIdModele().getDesignation(), ev.getNumeroChassis(), ev.getImmatriculation(), ev.getCouleur(), ev.getKilometrageActuel().toString(),
                    ev.getParametreAmortissement().toString(), (ev.isEtatVehicule() == true ? "ACTIF" : "RETIRE"));
            listeEtat.add(etat);
        }
        return listeEtat;
    }

    public void imprimerListeVehicule() {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> mape = new HashMap();
        try {
            listeVehicule = this.vehiculeService.listeVehicules();
            this.conteneur.setParametres(param);
            this.conteneur.setListeEtat((construireListeVehicule(listeVehicule)));
            this.conteneur.setCompileFileName("listeVehicule");
            EtatUtils.mettresParametresExportAValeurParDefault(conteneur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public vehicules getFormObject() {
        return formObject;
    }

    public void setFormObject(vehicules formObject) {
        this.formObject = formObject;
    }

    public vehicules getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(vehicules selectObject) {
        this.selectObject = selectObject;
    }

    public vehicules getSupression() {
        return supression;
    }

    public void setSupression(vehicules supression) {
        this.supression = supression;
    }

    public vehicules getModif() {
        return modif;
    }

    public void setModif(vehicules modif) {
        this.modif = modif;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public List<vehicules> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<vehicules> listeControle) {
        this.listeControle = listeControle;
    }

    public modeleServiceBeanLocal getModeleService() {
        return modeleService;
    }

    public void setModeleService(modeleServiceBeanLocal modeleService) {
        this.modeleService = modeleService;
    }

    public List<modele> getListeModele() {
        return listeModele;
    }

    public void setListeModele(List<modele> listeModele) {
        this.listeModele = listeModele;
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

    public typeVehiculeServiceBeanLocal getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(typeVehiculeServiceBeanLocal typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public List<typeVehicules> getListeTypevehicule() {
        return listeTypevehicule;
    }

    public void setListeTypevehicule(List<typeVehicules> listeTypevehicule) {
        this.listeTypevehicule = listeTypevehicule;
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

    public String getNumeroChassis() {
        return numeroChassis;
    }

    public void setNumeroChassis(String numeroChassis) {
        this.numeroChassis = numeroChassis;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public BigDecimal getParamAmortissement() {
        return paramAmortissement;
    }

    public void setParamAmortissement(BigDecimal paramAmortissement) {
        this.paramAmortissement = paramAmortissement;
    }

    public BigDecimal getKilometrageActuel() {
        return kilometrageActuel;
    }

    public void setKilometrageActuel(BigDecimal kilometrageActuel) {
        this.kilometrageActuel = kilometrageActuel;
    }

    public marques getMarques() {
        return marques;
    }

    public void setMarques(marques marques) {
        this.marques = marques;
    }

    public typeVehicules getTypeVehicules() {
        return typeVehicules;
    }

    public void setTypeVehicules(typeVehicules typeVehicules) {
        this.typeVehicules = typeVehicules;
    }

    public modele getIdModele() {
        return idModele;
    }

    public void setIdModele(modele idModele) {
        this.idModele = idModele;
    }

    public UploadedFile getImage1() {
        return image1;
    }

    public void setImage1(UploadedFile image1) {
        this.image1 = image1;
    }

    public UploadedFile getImage2() {
        return image2;
    }

    public void setImage2(UploadedFile image2) {
        this.image2 = image2;
    }

    public UploadedFile getImage3() {
        return image3;
    }

    public void setImage3(UploadedFile image3) {
        this.image3 = image3;
    }

    public UploadedFile getImage4() {
        return image4;
    }

    public void setImage4(UploadedFile image4) {
        this.image4 = image4;
    }

    public UploadedFile getImage5() {
        return image5;
    }

    public void setImage5(UploadedFile image5) {
        this.image5 = image5;
    }

    public List<vehicules> getListeControle2() {
        return listeControle2;
    }

    public void setListeControle2(List<vehicules> listeControle2) {
        this.listeControle2 = listeControle2;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public List<String> getImagesVisualiser() {
        return imagesVisualiser;
    }

    public void setImagesVisualiser(List<String> imagesVisualiser) {
        this.imagesVisualiser = imagesVisualiser;
    }

    public String getLiens() {
        return liens;
    }

    public void setLiens(String liens) {
        this.liens = liens;
    }

    public UploadedFile getResume() {
        return resume;
    }

    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }

    public List<vehicules> getListeFiltreVehicule() {
        return listeFiltreVehicule;
    }

    public void setListeFiltreVehicule(List<vehicules> listeFiltreVehicule) {
        this.listeFiltreVehicule = listeFiltreVehicule;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<vehicules> getListeVehiculeCode() {
        return listeVehiculeCode;
    }

    public void setListeVehiculeCode(List<vehicules> listeVehiculeCode) {
        this.listeVehiculeCode = listeVehiculeCode;
    }

    
    
    public String uploadResume() throws IOException {
        //UploadedFile uploadedPhoto = getResume();
        String filePath = "C:\\Users\\BOYODI Wiyow Marius\\Documents\\NetBeansProjects\\geParc1.0\\src\\main\\webapp\\resources\\assets\\images\\test\\";
        byte[] bytes = null;

        if (null != resume) {
            InputStream in = resume.getInputstream();
            bytes = resume.getContents();
            String filename = resume.getFileName();
            System.out.println("test " + filename);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
            int read = 0;
            while ((read = in.read(bytes)) != -1) {
                stream.write(bytes, 0, read);
            }
            stream.write(bytes);
            stream.close();
        }
        return "Succes";
    }

    String source, source1;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1;
    }

    public List<modele> getListeModeleFiltre() {
        return listeModeleFiltre;
    }

    public void setListeModeleFiltre(List<modele> listeModeleFiltre) {
        this.listeModeleFiltre = listeModeleFiltre;
    }

    public boolean isDesactivation() {
        return desactivation;
    }

    public void setDesactivation(boolean desactivation) {
        this.desactivation = desactivation;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void handleFileUpload(FileUploadEvent fileUploadEvent) throws IOException {
        String fileName = fileUploadEvent.getFile().getFileName();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        source1 = servletContext.getRealPath("");
        source = servletContext.getRealPath("") + File.separator + File.separator + fileName;
        System.out.println("source " + source);
        copyFile(fileName, fileUploadEvent.getFile().getInputstream());
        Ajax.oncomplete("PF('succesImportationOperation').show()");
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(source1 + File.separator + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("New file created!");
            source = source1 + File.separator + fileName;
            System.out.println("ma source: " + source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Ajax.oncomplete("PF('errorOperation').show()");
        }
    }

    //Wizard pour Employe
    public String handleFlow(FlowEvent event) {
        String newStep = event.getNewStep();

        if (event.getOldStep().equals("vehicule") && event.getNewStep().equals("infos")) {
            if (idModele == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le modèle n'est pas choisis"));
                newStep = event.getOldStep();
            }
        }
        return newStep;
    }

    public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }

    public ExportManagedBean getExportManagedBean() {
        return exportManagedBean;
    }

    public void setExportManagedBean(ExportManagedBean exportManagedBean) {
        this.exportManagedBean = exportManagedBean;
    }

    public boolean isNewModele() {
        return newModele;
    }

    public void setNewModele(boolean newModele) {
        this.newModele = newModele;
    }
    
    
    
    
    
    
    
    
    
    public void modifierModelele() {
        try {
            //selectObject.setDesignation(selectObject.getDesignation().trim().toUpperCase());
            //IDMarque = this.marqueService.selectionner(idMarque);
            //IDTypeVehicule = this.typeVehicule.selectionner(idTypeVehicule);
            //typecarburant = this.typeCarburantService.selectionner(idTypeCarburant);
            selectObjectModele.setIdMarque(marques);
            selectObjectModele.setIdTypeVehicule(typeVehicules);
            selectObjectModele.setDesignation(designation);
            selectObjectModele.setParametreVidange(parametreVidange);
//            selectObject.setTypecarburant(typecarburant);
            selectObjectModele.setIdTypeCarburant(typecarburant);
            modeleService.modifier(selectObjectModele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void fermer(){
        System.out.println("fermeture");
        Ajax.oncomplete("PF('ajoutModeleDlg').hide()");
         Ajax.oncomplete("PF('succesOperationAjout').hide()");
    }
    
    public void ajouterModele() {
        try {
            int i = 0;
            int ii= 0;
            //
            i= listeModele.size();
//            if (i == 0){
//                code = id2+"01";
//            } else if(i<=8){
//                code = listeModele.get(i-1).getIdModele();
//                code = code.substring(4);
//                ii = Integer.parseInt(code)+1 ;
//                code = id2+"0" + Integer.toString(ii);
//            } else {
//                code = listeModele.get(i-1).getIdModele();
//                code = code.substring(3);
//                ii = Integer.parseInt(code)+1 ;
//                code = id2 + Integer.toString(ii);
//            }
            if (i == 0){
                code =id2+"000001";
            } else if(i<=8){
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(8);
                ii = Integer.parseInt(code)+1 ;
                code = id2+"00000" + Integer.toString(ii);
            } else if(i<=98) {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(7);
                ii = Integer.parseInt(code)+1 ;
                code = id2+"0000" + Integer.toString(ii);
            } else if(i<=998) {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(6);
                ii = Integer.parseInt(code)+1 ;
                code = id2+"000" + Integer.toString(ii);
            } else if(i<=9998)  {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(5);
                ii = Integer.parseInt(code)+1 ;
                code = id2+"00" + Integer.toString(ii);
            } else if(i<=99998) {
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id2+"0" + Integer.toString(ii);
            } else{
                code = listeModele.get(i-1).getIdModele();
                code = code.substring(4);
                ii = Integer.parseInt(code)+1 ;
                code = id2 + Integer.toString(ii);
            }
            
            //IDMarque = this.marqueService.selectionner(idMarque);
            //IDTypeVehicule = this.typeVehicule.selectionner(idTypeVehicule);
            //typecarburant = this.typeCarburantService.selectionner(idTypeCarburant);
            System.out.println("marques "+ marques.getLibelleMarque());
         System.out.println("TypesVeh "+ typeVehicules.getLibelleTypeVehicule());
         formObjectModele = new modele();
            formObjectModele.setIdMarque(marques);
            formObjectModele.setIdTypeVehicule(typeVehicules);
            formObjectModele.setIdModele(code);
            formObjectModele.setDesignation(designation.trim().toUpperCase());
            formObjectModele.setParametreVidange(parametreVidange);
//            formObject.setTypecarburant(typecarburant);
            formObjectModele.setIdTypeCarburant(typecarburant);
            this.modeleService.ajouter(formObjectModele);
            message1 = "Enregistrement effectué avec succès";
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    
    public void enregistrerModele() {
        
                    int nb=0;
                    nb=listeControleModele.size();
                    boolean exist=false;
                    if(nb != 0){
                        for (int i=0; i<=nb-1; i++){
                         if(listeControleModele.get(i).getDesignation().equals(designation.trim().toUpperCase())&&
                            listeControleModele.get(i).getIdMarque().getLibelleMarque().equals(marques.getLibelleMarque()) &&
                            listeControleModele.get(i).getIdTypeVehicule().getLibelleTypeVehicule().equals(typeVehicules.getLibelleTypeVehicule())){
                             exist = true;
                            }
                        }
                        if (exist == true){
                            //message1 = "Impossible d'éffectuer l'enrégistrement. Ce modele existe déja.";
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Impossible d'éffectuer l'enrégistrement. Ce modele existe déja."));
                            //Ajax.oncomplete("PF('errorOperationModele').show()");
                            //this.rafraichir();
                        } else  { 
                            ajouterModele();
                            message1 = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationAjout').show()");
                            this.effacerModele();
                            this.rafraichir();
                        } 
                    } else {
                            ajouterModele();
                            message1 = "Enregistrement effectué avec succès";
                            Ajax.oncomplete("PF('succesOperationAjout').show()");
                            this.effacerModele();
                            this.rafraichir();
                    }
                
        
    }
  
    public void supprimerModele() {
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
    
    
//    public void rafraichirModele() {
//        System.out.println("VRAI");
//        this.chargerElement();
//        this.effacer();
//        desactiver = true;
//    }
    
    public void effacerModele() {
        formObjectModele = new modele();
        designation="";
        parametreVidange = null;
        typecarburant = null;
       
    }
    
//     public void remplirFormulaire() {
//        formObject = selectObject != null ? selectObject : new modele();
//    }
    
      //boite de dialog
    public void confirmerEnregistrementModele() {
         System.out.println("marques "+ marques.getLibelleMarque());
         System.out.println("TypesVeh "+ typeVehicules.getLibelleTypeVehicule());
        modifModele=null;
        boolean verif1=false, verif2=false, verif3=false, verif4=false;
        
        if(designation.trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "La désignation ne peut être vide "));
        } else {
            verif1=true;
        }
        if (marques==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Vous devez sélectionner une marque "));
        } else {
            verif2=true;
        }
        if (typeVehicules==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Vous devez sélectionner un type de véhicule "));
        } else {
            verif3=true;
        }
        if (typecarburant==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Vous devez sélectionner un type de carburant "));
        } else {
            verif4=true;
        }
    
        if(modifModele == null){
            if(verif1 == true && verif2==true && verif3 == true && verif4==true){
               enregistrerModele();
                //rafraichir();
            }  
        } else {
            
            if(modifModele.getIdTypeVehicule().getIdTypeVehicule().equals(typeVehicules.getIdTypeVehicule()) && modifModele.getIdMarque().getIdMarque().equals(marques.getIdMarque()) &&
                    modifModele.getDesignation().equals(designation) && modifModele.getParametreVidange().equals(parametreVidange)) {
                // && modif.getTypecarburant().equals(typecarburant)
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  ")); 
            } else {
                if(designation.trim().length() == 0){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
                } else {
                    enregistrerModele();
                    //rafraichir();
                }  
            } 
            
        } 
    }

    public modele getFormObjectModele() {
        return formObjectModele;
    }

    public void setFormObjectModele(modele formObjectModele) {
        this.formObjectModele = formObjectModele;
    }

    public modele getSelectObjectModele() {
        return selectObjectModele;
    }

    public void setSelectObjectModele(modele selectObjectModele) {
        this.selectObjectModele = selectObjectModele;
    }

    public modele getModifModele() {
        return modifModele;
    }

    public void setModifModele(modele modifModele) {
        this.modifModele = modifModele;
    }

    public List<modele> getListeControleModele() {
        return listeControleModele;
    }

    public void setListeControleModele(List<modele> listeControleModele) {
        this.listeControleModele = listeControleModele;
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

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
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

    public String getIdTypeCarburant() {
        return idTypeCarburant;
    }

    public void setIdTypeCarburant(String idTypeCarburant) {
        this.idTypeCarburant = idTypeCarburant;
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

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public List<marques> getListeMarqueFiltre() {
        return listeMarqueFiltre;
    }

    public void setListeMarqueFiltre(List<marques> listeMarqueFiltre) {
        this.listeMarqueFiltre = listeMarqueFiltre;
    }

    public List<typeVehicules> getListeTypevehiculeFiltre() {
        return listeTypevehiculeFiltre;
    }

    public void setListeTypevehiculeFiltre(List<typeVehicules> listeTypevehiculeFiltre) {
        this.listeTypevehiculeFiltre = listeTypevehiculeFiltre;
    }
    
}
