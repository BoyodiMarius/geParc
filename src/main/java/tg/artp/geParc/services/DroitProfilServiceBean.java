/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.DroitProfilDaoBeanLocal;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.DroitProfil;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author SENA
 */
@Stateless
public class DroitProfilServiceBean extends BaseServiceBean<DroitProfil, Long> implements DroitProfilServiceBeanLocal {

    @EJB
    protected DroitProfilDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<DroitProfil, Long> getDao() {
        return this.dao;
    }

    @Override
    public List<Droit> listDroits(Modules module, Profil profil) {
        return dao.listDroits(module, profil);

    }

    @Override
    public List<Droit> listDroits(Profil profil) {

        return dao.listDroits(profil);
    }

    @Override
    public DroitProfil find(Profil profil, Droit droit) {

        return dao.find(profil, droit);

    }

    @Override
    public void deleteDroitProfil(Droit d, Profil p) {
        dao.deleteDroitProfil(d, p);
    }

    @Override
    public List<Profil> listProfilToUse() {
        return this.dao.listProfilToUse();
    }

}
