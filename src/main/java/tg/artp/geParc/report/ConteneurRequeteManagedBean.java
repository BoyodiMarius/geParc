/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author MANU
 */
@ManagedBean(name = "conteneurRequeteManagedBean")
@SessionScoped
public class ConteneurRequeteManagedBean implements Serializable {

    private String requete;
    private List listeEtat;
    private List<Map<String, ?>> mapEtat;
    private String compileFileName;
    private Map<String, Object> parametres;
    private Boolean condense;
    private List listeEtatCondense;
    private String elementsDerniereRecherche;
    private byte[] ficherPdf;
    private InputStream fluxPdf;
    private Map<String, List> mapListeEtat;
    private boolean afficherOptionImpression;

    private List secondeListe;

    /**
     * Creates a new instance of ConteneurRequeteManagedBean
     */
    public ConteneurRequeteManagedBean() {
    }

    public List getSecondeListe() {
        return secondeListe;
    }

    public void setSecondeListe(List secondeListe) {
        this.secondeListe = secondeListe;
    }

    public Map<String, List> getMapListeEtat() {
        return mapListeEtat;
    }

    public void setMapListeEtat(Map<String, List> mapListeEtat) {
        this.mapListeEtat = mapListeEtat;
    }

    public byte[] getFicherPdf() {
        return ficherPdf;
    }

    public void setFicherPdf(byte[] ficherPdf) {
        this.ficherPdf = ficherPdf;
        if (ficherPdf != null) {
            ByteArrayInputStream tmp = new ByteArrayInputStream(ficherPdf);
            this.fluxPdf = tmp;
        }
    }

    public InputStream getFluxPdf() {
        return fluxPdf;
    }

    public void setFluxPdf(InputStream fluxPdf) {
        this.fluxPdf = fluxPdf;
    }

    public String getElementsDerniereRecherche() {
        return elementsDerniereRecherche;
    }

    public void setElementsDerniereRecherche(String elementsDerniereRecherche) {
        this.elementsDerniereRecherche = elementsDerniereRecherche;
    }

    public Boolean isCondense() {
        return (condense == null ? false : condense);
    }

    public void setCondense(Boolean condense) {
        this.condense = condense;
    }

    public List getListeEtatCondense() {
        return listeEtatCondense;
    }

    public void setListeEtatCondense(List listeEtatCondense) {
        this.listeEtatCondense = listeEtatCondense;
    }

    public Map<String, Object> getParametres() {
        return parametres;
    }

    public void setParametres(Map<String, Object> parametres) {
        this.parametres = parametres == null ? new HashMap() : parametres;
//        Utilisateur utilisateur = FidescoUtils.getUtilisateur();
//        utilisateur = utilisateur == null ? new Utilisateur() : utilisateur;

        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            ServletContext context = (ServletContext) externalContext.getContext();
        } catch (Exception e) {
            System.out.println("*********°°°°°******* erreur logo *********************");
        }
        this.parametres.put("APPLICATION_NAME", "DOUM");
        //this.parametres.put("EDITEUR", utilisateur.getNom() + " " + utilisateur.getPrenom());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.parametres.put("DATE_EDITION", format.format(date));

    }

    public String getRequete() {
        return requete;
    }

    public void setRequete(String requete) {
        this.requete = requete;
    }

    public List getListeEtat() {
        return listeEtat;
    }

    public void setListeEtat(List listeEtat) {
        this.listeEtat = listeEtat;
    }

    public String getCompileFileName() {
        return compileFileName;
    }

    public void setCompileFileName(String compileFileName) {
        this.compileFileName = compileFileName;
    }

    public List<Map<String, ?>> getMapEtat() {
        return mapEtat;
    }

    public void setMapEtat(List<Map<String, ?>> mapEtat) {
        this.mapEtat = mapEtat;
    }

    public boolean isAfficherOptionImpression() {
        return afficherOptionImpression;
    }

    public void setAfficherOptionImpression(boolean afficherOptionImpression) {
        this.afficherOptionImpression = afficherOptionImpression;
    }
}
