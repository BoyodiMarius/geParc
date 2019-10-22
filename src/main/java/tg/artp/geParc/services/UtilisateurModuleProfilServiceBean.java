/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.UtilisateurModuleProfilDaoBeanLocal;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurModuleProfil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class UtilisateurModuleProfilServiceBean extends BaseServiceBean<UtilisateurModuleProfil, Long> implements UtilisateurModuleProfilServiceBeanLocal {

    @EJB
    private UtilisateurModuleProfilDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<UtilisateurModuleProfil, Long> getDao() {
        return this.dao;
    }

    @Override
    public UtilisateurModuleProfil find(Long idModule, String idUser) {
        return dao.find(idModule, idUser);
    }

    @Override
    public List<Utilisateur> findUserByProfil(Profil p) {
        return dao.findUserByProfil(p);
    }

    @Override
    public List<Modules> listeModuleByUserProfil(Profil profil, Utilisateur user) {
        return this.dao.listeModuleByUserProfil(profil, user);
    }
}
