/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Ajax;

/**
 *
 * @author MANU
 *
 */
@ManagedBean(name = "globalExportManagedBean")
@ViewScoped
public class ExportManagedBean implements Serializable {

    private List<String> listeDesFormat;
    private String formatDocument;
    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    private String adresseEtat;
    private String messageErreur;

    /**
     * Creates a new instance of ExportManagedBean
     */
    public ExportManagedBean() {
        this.listeDesFormat = new ArrayList();
        this.listeDesFormat.add("PDF");
        this.listeDesFormat.add("EXCEL");
        this.formatDocument = "PDF";
    }

    public String getAdresseEtat() {
        return adresseEtat;
    }

    public void setAdresseEtat(String adresseEtat) {
        this.adresseEtat = adresseEtat;
    }

    public List<String> getListeDesFormat() {
        return listeDesFormat;
    }

    public void setListeDesFormat(List<String> listeDesFormat) {
        this.listeDesFormat = listeDesFormat;
    }

    public String getFormatDocument() {
        return formatDocument;
    }

    public void setFormatDocument(String format) {
        this.formatDocument = format;
    }

    public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }

    public void exporter() {
        Boolean val = this.formatDocument.equalsIgnoreCase("EXCEL");
        List liste = this.conteneur.isCondense() ? this.conteneur.getListeEtatCondense() : this.conteneur.getListeEtat();
        liste = liste == null ? this.conteneur.getMapEtat() : liste;
        liste = liste == null ? new ArrayList() : liste;
        EtatRechercheMultiple exporteur;
        if (this.conteneur.getMapEtat() != null) {
            exporteur = new EtatRechercheMultiple(liste, true);
        } else {
            exporteur = new EtatRechercheMultiple(liste);
        }
        this.conteneur.setParametres(this.conteneur.getParametres() == null ? new HashMap() : this.conteneur.getParametres());
        this.conteneur.getParametres().put("FICHIER_EXCEL", val);
        this.conteneur.getParametres().put("IS_IGNORE_PAGINATION", val);
        exporteur.changerFormat(formatDocument);
        exporteur.setCompileFileName(this.conteneur.getCompileFileName());
        try {
            exporteur.exporter(this.conteneur.getParametres());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void demandeImpression() {
        if (this.formatDocument.equalsIgnoreCase("PDF")) {
            if (this.conteneur.isAfficherOptionImpression()) {
                System.out.println("Affichage option");
                Ajax.oncomplete("PF('dialogueOptionExport').show()");
            } else {
                this.exporter();
            }
        } else {
            this.exporter();
        }
    }

    public String getMessageErreur() {
        return messageErreur;
    }

    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

}
