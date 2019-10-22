/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.tools;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@RequestScoped
public class GeParcUtils implements Serializable {

    /*retourne la couleur a affecter aux icones (désactivé ou non) */
    public String iconColor(Boolean b) {
        return b ? "#2d9ae3" : "#CCC";
    }

    /*retourne la couleur a affecter aux menus (désactivé ou non) */
    public String menuColor(Boolean b) {
        return b ? "#045491" : "#CCC";
    }

    public String disPlay(Boolean b) {
        return b ? "inline" : "none";
    }

    public void reloadPage(String page) throws IOException {
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
        Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : page);
    }
}
