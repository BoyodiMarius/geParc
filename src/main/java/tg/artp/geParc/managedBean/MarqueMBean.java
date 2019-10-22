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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import tg.artp.geParc.entities.marques;
import tg.artp.geParc.services.marquesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class MarqueMBean implements Serializable {

    @EJB
    private marquesServiceBeanLocal marqueService;
    private marques formObject, selectObject, supression, modif;
    private List<marques> listeMarques, listeControle;
    private String message;
    private String id = "MAR";
    private String libelleMarque = "";
    private String code = "";
    int index;
    private boolean desactiver = true;
    private boolean desactiver1 = false;

    public MarqueMBean() {
        this.formObject = new marques();
    }

    @PostConstruct
    public void chargerElement() {
        this.listeMarques = new ArrayList();
        this.listeMarques = this.marqueService.selectionnerTout();
        this.listeControle = new ArrayList();
        this.listeControle = this.marqueService.selectionnerTout();
        desactiver = true;
        desactiver1 = false;
    }

    public void rowSelect() {

        formObject = selectObject;
        supression = selectObject;
        libelleMarque = selectObject.getLibelleMarque();
        this.index = this.listeMarques.indexOf(this.selectObject);
        modif = selectObject;
        desactiver = false;
        desactiver1 = true;
    }

    public void suppression() {
        try {
            selectObject.setLibelleMarque(libelleMarque);
            marqueService.supprimer(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier() {
        try {
            selectObject.setLibelleMarque(libelleMarque.trim().toUpperCase());
            marqueService.modifier(selectObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouter() {
        try {
            int i = 0;
            int ii = 0;
            //
            i = listeMarques.size();
            if (i == 0) {
                code = id + "01";
            } else if (i <= 8) {
                code = listeMarques.get(i - 1).getIdMarque();
                code = code.substring(4);
                ii = Integer.parseInt(code) + 1;
                code = id + "0" + Integer.toString(ii);
            } else {
                code = listeMarques.get(i - 1).getIdMarque();
                code = code.substring(3);
                ii = Integer.parseInt(code) + 1;
                code = id + Integer.toString(ii);
            }
            formObject.setIdMarque(code);
            formObject.setLibelleMarque(libelleMarque.trim().toUpperCase());
            this.marqueService.ajouter(formObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrer() {
        int ct = 0;
        ct = listeControle.size();
        if (ct != 0) {
            this.listeControle.remove(index);
        }
        if (selectObject != null) {
            int nb1 = 0;
            nb1 = listeControle.size();
            boolean exist1 = false;
            if (nb1 != 0) {
                for (int i = 0; i <= nb1 - 1; i++) {
                    if (listeControle.get(i).getLibelleMarque().equals(libelleMarque.trim().toUpperCase())) {
                        exist1 = true;
                    }
                }
                if (exist1 == true) {
                    message = "Impossible d'éffectuer l'enrégistrement. Cette marque existe déja.";
                    Ajax.oncomplete("PF('errorOperationMarque').show()");
                    this.rafraichir();
                } else {
                    modifier();
                    message = "Modification effectué avec succès";
                    Ajax.oncomplete("PF('succesOperationMarque').show()");
                }
            } else {
                modifier();
                message = "Modification effectué avec succès";
                Ajax.oncomplete("PF('succesOperationMarque').show()");
            }
        } else {
            int nb = 0;
            nb = listeMarques.size();
            boolean exist = false;
            if (nb != 0) {
                for (int i = 0; i <= nb - 1; i++) {
                    if (listeMarques.get(i).getLibelleMarque().equals(libelleMarque.trim().toUpperCase())) {
                        exist = true;
                    }
                }
                if (exist == true) {
                    message = "Impossible d'éffectuer l'enrégistrement. Cette marque existe déja.";
                    Ajax.oncomplete("PF('errorOperationMarque').show()");
                    this.rafraichir();
                } else {
                    ajouter();
                    message = "Enregistrement effectué avec succès";
                    Ajax.oncomplete("PF('succesOperationMarque').show()");
                }
            } else {
                ajouter();
                message = "Enregistrement effectué avec succès";
                Ajax.oncomplete("PF('succesOperationMarque').show()");
            }
        }
        this.rafraichir();
    }

    public void supprimer() {
        if (selectObject == null) {
            return;
        }
        try {
            suppression();
            message = "Suppression effectuée avec succès";
            Ajax.oncomplete("PF('succesOperationMarque').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        rafraichir();
        index = 0;
    }

    public void rafraichir() {
        System.out.println("VRAI");
        this.chargerElement();
        this.effacer();
        desactiver = true;
    }

    public void effacer() {
        formObject = new marques();
        libelleMarque = "";
        selectObject = null;
    }

    public void remplirFormulaire() {
        formObject = selectObject != null ? selectObject : new marques();
    }

    //boite de dialog
    public void confirmerEnregistrement() {

        if (modif == null) {
            if (libelleMarque.trim().length() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
            } else {
                Ajax.oncomplete("PF('confirmerEnregistrementMarque').show()");
            }
        } else {
            if (modif.getLibelleMarque().equals(libelleMarque)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Aucune modification n'a été éffectuer  "));
            } else {
                if (libelleMarque.trim().length() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Le champs ne peut être vide "));
                } else {
                    Ajax.oncomplete("PF('confirmerEnregistrementMarque').show()");
                }
            }
        }
    }

    public void confirmerSuppression() {
        if (selectObject == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " La suppression n'est pas possible car aucun objet n'a été sélectionner "));
        } else {
            Ajax.oncomplete("PF('confirmerSuppressionMarque').show()");
        }
    }

    //getters et setters
    public marquesServiceBeanLocal getMarqueService() {
        return marqueService;
    }

    public void setMarqueService(marquesServiceBeanLocal marqueService) {
        this.marqueService = marqueService;
    }

    public marques getFormObject() {
        return formObject;
    }

    public void setFormObject(marques formObject) {
        this.formObject = formObject;
    }

    public marques getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(marques selectObject) {
        this.selectObject = selectObject;
    }

    public marques getSupression() {
        return supression;
    }

    public void setSupression(marques supression) {
        this.supression = supression;
    }

    public List<marques> getListeMarques() {
        return listeMarques;
    }

    public void setListeMarques(List<marques> listeMarques) {
        this.listeMarques = listeMarques;
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

    public String getLibelleMarque() {
        return libelleMarque;
    }

    public void setLibelleMarque(String libelleMarque) {
        this.libelleMarque = libelleMarque;
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

    public List<marques> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<marques> listeControle) {
        this.listeControle = listeControle;
    }

    public marques getModif() {
        return modif;
    }

    public void setModif(marques modif) {
        this.modif = modif;
    }

    public boolean isDesactiver1() {
        return desactiver1;
    }

    public void setDesactiver1(boolean desactiver1) {
        this.desactiver1 = desactiver1;
    }

}
