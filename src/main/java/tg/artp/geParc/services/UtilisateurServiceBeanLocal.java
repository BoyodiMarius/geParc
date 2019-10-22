/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.Local;
import tg.artp.geParc.entities.Utilisateur;

/**
 *
 * @author SENA
 */
@Local
public interface UtilisateurServiceBeanLocal extends BaseServiceBeanLocal<Utilisateur, Long> {

    public Utilisateur loadUserByLogin(String login);

    public Utilisateur getCurrentUser();

    public boolean userIsPermitted(String permissionId);

}
