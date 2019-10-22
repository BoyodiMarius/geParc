/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.Local;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurModuleProfil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Local
public interface UtilisateurModuleProfilServiceBeanLocal extends BaseServiceBeanLocal<UtilisateurModuleProfil, Long> {

    public UtilisateurModuleProfil find(Long idModule, String idUser);

    public List<Utilisateur> findUserByProfil(Profil p);
    
    public List<Modules> listeModuleByUserProfil(Profil profil, Utilisateur user) ;

}
