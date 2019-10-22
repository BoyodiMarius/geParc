/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.UtilisateurDroitDaoBeanLocal;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;
import tg.artp.geParc.entities.UtilisateurDroit;

/**
 *
 * @author SENA
 */
@Stateless
public class UtilisateurDroitServiceBean extends BaseServiceBean<UtilisateurDroit, Long> implements UtilisateurDroitServiceBeanLocal {

    @EJB
    private UtilisateurDroitDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<UtilisateurDroit, Long> getDao() {
        return this.dao;
    }

    @Override
    public UtilisateurDroit find(Utilisateur user, Droit droit) {
        return dao.find(user, droit);
    }

    @Override
    public void deleteUserDroit(Droit d, Profil p) {
        dao.deleteUserDroit(d, p);
    }

    @Override
    public List<UtilisateurDroit> listUserDroit(Utilisateur user, Profil p) {
        return this.dao.listUserDroit(user, p);
    }
}
