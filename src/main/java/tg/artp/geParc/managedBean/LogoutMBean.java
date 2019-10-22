/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Faces;
import tg.artp.geParc.tools.SessionMBean;

/**
 *
 * @author Elvis
 */
@ManagedBean
@RequestScoped
public class LogoutMBean implements Serializable {

    @ManagedProperty(value = "#{sessionMBean}")
    private SessionMBean session;

    public void deconnexion() throws IOException {
        SecurityUtils.getSubject().logout();
        session = null;
        Faces.invalidateSession();
        Faces.redirect("login.xhtml");
    }

    public SessionMBean getSession() {
        return session;
    }

    public void setSession(SessionMBean session) {
        this.session = session;
    }

}
