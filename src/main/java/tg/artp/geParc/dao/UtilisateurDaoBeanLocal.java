/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Utilisateur;

/**
 *
 * @author Elvis
 */
@Local
public interface UtilisateurDaoBeanLocal extends BaseDaoBeanLocal<Utilisateur, Long> {

    public Utilisateur loadUserByLogin(String login);

    public List<String> listePermission(Utilisateur user);

    public List<String> listePermissionParProfil(Utilisateur user);

    public List<String> listeRoleShiro(Utilisateur user);

}
