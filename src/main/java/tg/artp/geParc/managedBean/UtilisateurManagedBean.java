package tg.artp.geParc.managedBean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;

/**
 *
 * @author Elvis
 */
@ManagedBean(name = "utilisateurManagedBean")
@ViewScoped
public class UtilisateurManagedBean implements Serializable {

    @EJB
    private UtilisateurServiceBeanLocal service;

    private List<Utilisateur> dataList = new ArrayList();
    private Utilisateur formObject, selectedObject;
    private Boolean desactiverBoutonSuppr = true;
    private List<Utilisateur> ListControle;
    private String message, confirmationPassword;
    private Integer index;

    public UtilisateurManagedBean() {
        this.formObject = new Utilisateur();
    }

    @PostConstruct
    public final void loadFromDb() {
        dataList.clear();
        dataList.addAll(service.selectionnerTout());
        this.ListControle = new ArrayList();
        ListControle.addAll(dataList);
    }

    /**
     * Enrégistre les données saisies ou modifiées dans la base de données
     *
     */
    public void enregistrer() {
        try {
            
            
            if (this.selectedObject != null) {//on est en mode mise à jour
                Utilisateur modEntity = this.service.modifier(formObject);
                dataList.set(index, modEntity);
            } else {//donc on est en mode ajout
                formObject.setPassword(new Sha256Hash(formObject.getPassword()).toHex());
                this.service.ajouter(formObject);
                this.dataList.add(0, formObject);
            }
            this.message = "Enregistrement effectué avec succès.";
            Ajax.oncomplete("PF('succesOperation').show()");
            cleanUp();

        } catch (EJBTransactionRolledbackException ex) {
            this.message = "Impossible de se connecter au serveur.!";
            Ajax.oncomplete("PF('echecOperation').show()");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            this.message = "Enregistrement échoué. Veuillez réessayer!";
            Ajax.oncomplete("PF('echecOperation').show()");
        }
    }

    /**
     * Supprime la ligne sélectionnée
     */
    public void supprimer() {
        try {
            if (this.selectedObject != null) {
                this.service.supprimer(selectedObject.getIdUser());
                this.dataList.remove(selectedObject);
                cleanUp();
                formObject = new Utilisateur();
                Ajax.oncomplete("PF('succesOperation').show()");
            } else {
                this.message = "Veuillez sélectionner la ligne à supprimer!";
                Ajax.oncomplete("PF('echecOperation').show()");
            }
        } catch (Exception ex) {
            this.message = "Suppression échouée. Veuillez vérifier si cet objet n'est pas utilisé ailleurs!";
            Ajax.oncomplete("PF('echecOperation').show()");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Exception: " + ex);
        }

    }

    /**
     * Rafraîchit la vue et charge les données depuis la base de données
     */
    public void rafraichir() {
        loadFromDb();
        cleanUp();
    }

    /**
     * Efface le formulaire
     */
    public void effacer() {
        cleanUp();
        //     RequestContext.getCurrentInstance().reset("formGeneral");
    }

    /**
     * Réinitialise la vue et les objets
     */
    private void cleanUp() {
        formObject = new Utilisateur();
        this.selectedObject = null;
        desactiverBoutonSuppr = true;
    }

    /**
     * Traitement à effectuer lors de l'événement sélection d'une ligne
     */
    public void rowSelect() {
        formObject = selectedObject;
        desactiverBoutonSuppr = false;
        this.index = this.dataList.indexOf(this.selectedObject);
        this.ListControle  = new ArrayList();
        int taille = dataList.size();
        for (int i=0; i<=taille-1;i++){
            if(dataList.get(i).getIdUser().equals(selectedObject.getIdUser())){
                System.out.println("vrai");
            } else {
                ListControle.add(dataList.get(i));
            }
        }
    }

    /**
     * Affiche une boîte de dialogue de confirmation avant l'enrégistrement
     *
     */
    public void confirmerEnregistrer() {
        try {
            System.out.println("password hash : " + new Sha256Hash(formObject.getPassword()).toHex());
//            System.out.println("password hash string : " + new Sha256Hash(formObject.getPassword()).toString());
            Utilisateur tmp = service.selectionner("login", formObject.getLogin());
            boolean verifEmail=false, veri=false;
            if(formObject.getEmail().trim().length() != 0) {
            Pattern rfc2822 = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
            if (rfc2822.matcher(formObject.getEmail().trim()).matches()) {
                int nb=ListControle.size();
                for(int i=0;i<=nb-1;i++){
                    if(ListControle.get(i).getEmail().equals(formObject.getEmail().trim())){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Cet email est déjà lié a un autre compte "));
                         veri=true;
                    } 
                }
                if(veri==false){
                        verifEmail=true;
                }
            } else {
                
              verifEmail=false;
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "L'email n'est pas valide "));
            }
        }
             
            if(verifEmail==true){
                if (selectedObject != null) {
                Ajax.oncomplete("PF('confirmEnregistre').show()");
            } else {
                if (tmp != null) {
                    this.message = "Cette utilisateur existe déjà !";
                    Ajax.oncomplete("PF('warningOperation').show()");
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Cette utilisateur existe déjà ! "));
                } else {
                    if (formObject.getPassword().equals(confirmationPassword)) {
                        Ajax.oncomplete("PF('confirmEnregistre').show()");
                    } else {
                        this.message = "Confirmation du mot de passe incorrect!!!";
//                         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!",  "Confirmation du mot de passe incorrect!!!"));
                        Ajax.oncomplete("PF('warningOperation').show()");
                    }
                }
            }
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            this.message = "Erreur ! Veuillez réessayer !";
            Ajax.oncomplete("PF('echecOperation').show()");
        }
    }

    /**
     * Affiche une boîte de dialogue de confirmation avant la suppression
     */
    public void confirmeSuppression() {
        Ajax.oncomplete("PF('confirmSuppression').show()");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Utilisateur> getDataList() {
        return dataList;
    }

    public void setDataList(List<Utilisateur> dataList) {
        this.dataList = dataList;
    }

    public Utilisateur getFormObject() {
        return formObject;
    }

    public void setFormObject(Utilisateur formObject) {
        this.formObject = formObject;
    }

    public Utilisateur getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Utilisateur selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public Boolean getDesactiverBoutonSuppr() {
        return desactiverBoutonSuppr;
    }

    public void setDesactiverBoutonSuppr(Boolean desactiverBoutonSuppr) {
        this.desactiverBoutonSuppr = desactiverBoutonSuppr;
    }

    public UtilisateurServiceBeanLocal getService() {
        return service;
    }

    public void setService(UtilisateurServiceBeanLocal service) {
        this.service = service;
    }

    public List<Utilisateur> getListControle() {
        return ListControle;
    }

    public void setListControle(List<Utilisateur> ListControle) {
        this.ListControle = ListControle;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    
    
}
