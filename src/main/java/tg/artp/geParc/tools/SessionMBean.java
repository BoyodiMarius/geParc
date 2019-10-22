/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.tools;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.shiro.SecurityUtils;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.services.UtilisateurServiceBeanLocal;

/**
 *
 * @author Elvis
 */
@ManagedBean
@SessionScoped
public class SessionMBean implements Serializable {

    private Utilisateur utilisateur;
    @EJB
    private UtilisateurServiceBeanLocal service;

    public SessionMBean() {
    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("security==" + SecurityUtils.getSubject().getPrincipal());
            NumberFormat nf = NumberFormat.getInstance();
            Number myNumber = nf.parse(SecurityUtils.getSubject().getPrincipal().toString());
            utilisateur = service.selectionner(myNumber.longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
