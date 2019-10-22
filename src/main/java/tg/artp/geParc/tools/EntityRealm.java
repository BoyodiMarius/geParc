/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import tg.artp.geParc.dao.UtilisateurDaoBeanLocal;
import tg.artp.geParc.entities.Utilisateur;

public class EntityRealm extends AuthorizingRealm {
    //@EJB

    protected UtilisateurDaoBeanLocal userDAO;
    protected static Utilisateur user;
    //@EJB
    //  protected TraceServiceBeanLocal traceService;

    public EntityRealm() throws NamingException {
        setName("entityRealm");
        CredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("SHA-256");
        this.setCredentialsMatcher(credentialsMatcher);

        InitialContext context = new InitialContext();
        this.userDAO = (UtilisateurDaoBeanLocal) context.lookup("java:global/geParc1.0/UtilisateurDaoBean");
        System.out.println("realm; userDAO: " + this.userDAO);
        System.out.println("charger");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("Appel: doGetAuthenticationInfo()");
        final UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        user = userDAO.loadUserByLogin(token.getUsername());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        try {
            if (user != null) {
                if (user.getActivation() == true) {
                    simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getIdUser(), user.getPassword(), getName());
                } 
            }
        } catch (Exception e) {
            if (user != null) {
                if (user.getActivation() == false) {
                    simpleAuthenticationInfo = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Compte désactivé"));
                } else {
                    simpleAuthenticationInfo = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Nom d'utilisateur ou mot de passe incorrect"));
                }
            } 
            else {
                simpleAuthenticationInfo = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur!", "Utilisateur Inconnu"));
            }
        }
//        SimpleAuthorizationInfo info = doGetAuthorizationInfo(null);
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Appel: doGetAuthorizationInfo()");

        Long userId = (Long) principals.fromRealm(this.getName()).iterator().next();
        user = userDAO.selectionner(userId);
        System.out.println("user "+user.getLogin());
        if (user != null) {
            final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            /*
             * On recupère les profils de l'utilisateur
             * (role == profil ) : Dans notre application un profil correspond à un role dans shiro 
             */
            final List<String> roles = userDAO.listeRoleShiro(user);
            info.addRoles(roles);
            for (String role : roles) {
                System.out.println(role);
            }
            /*
             * On recupère la liste des menus auquel à droit l'utilisateur
             * (role == profil ) : Dans notre application un profil correspond à un role dans shiro 
             */
            if (info.getRoles().size() > 0) {
                final List<String> perm = userDAO.listePermissionParProfil(user);
                info.addStringPermissions(perm);
                for (String perm1 : perm) {
                    System.out.println(perm1);
                }
            }
            return info;
        } else {
            return null;
        }
    }

    public static Utilisateur getUser() {
        System.out.println("realm; getUser: ");
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return user;
        }
        return null;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("realm; clearCachedAuthorizationInfo: ");
        super.clearCachedAuthorizationInfo(principals);
    }

}
