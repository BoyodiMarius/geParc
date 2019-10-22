/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.shiro.SecurityUtils;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.UtilisateurDaoBeanLocal;
import tg.artp.geParc.entities.Utilisateur;

/**
 *
 * @author SENA
 */
@Stateless
public class UtilisateurServiceBean extends BaseServiceBean<Utilisateur, Long> implements UtilisateurServiceBeanLocal {

    @EJB
    protected UtilisateurDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<Utilisateur, Long> getDao() {

        return this.dao;
    }

    @Override
    public Utilisateur loadUserByLogin(String login) {
        return this.dao.loadUserByLogin(login);
    }

    @Override
    public Utilisateur getCurrentUser() {
        Utilisateur user = (Utilisateur) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    @Override
    public synchronized boolean userIsPermitted(String permissionId) {
        return SecurityUtils.getSubject().isPermitted(permissionId);
    }
}
