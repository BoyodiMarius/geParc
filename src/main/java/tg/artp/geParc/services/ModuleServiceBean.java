/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.ModuleDaoBeanLocal;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class ModuleServiceBean extends BaseServiceBean<Modules, Long> implements ModuleServiceBeanLocal {

    @EJB
    private ModuleDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<Modules, Long> getDao() {
        return this.dao;
    }

    @Override
    public List<Modules> listeModuleByProfil(Profil profil) {

        return this.dao.listeModuleByProfil(profil);
    }
}
