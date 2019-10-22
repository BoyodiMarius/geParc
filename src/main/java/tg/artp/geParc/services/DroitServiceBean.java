/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.DroitDaoBeanLocal;
import tg.artp.geParc.entities.Droit;

/**
 *
 * @author SENA
 */
@Stateless
public class DroitServiceBean extends BaseServiceBean<Droit, Long> implements DroitServiceBeanLocal {

    @EJB
    protected DroitDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<Droit, Long> getDao() {

        return this.dao;
    }

    @Override
    public List<Droit> listeDroitsSansParent() {
        return this.dao.listeDroitsSansParent();
    }

    @Override
    public List<Droit> listeDroitsEnfantByParent(Droit droitParent) {
        return this.dao.listeDroitsEnfantByParent(droitParent);
    }
    
   

}
