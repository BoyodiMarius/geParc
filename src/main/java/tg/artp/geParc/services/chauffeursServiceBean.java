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
import tg.artp.geParc.dao.chauffeursDaoBeanLocal;
import tg.artp.geParc.entities.chauffeurs;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class chauffeursServiceBean extends BaseServiceBean<chauffeurs, String> implements chauffeursServiceBeanLocal {

    @EJB
    private chauffeursDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<chauffeurs, String> getDao() {
        return this.dao;
    }

    @Override
    public List<chauffeurs> recupererListePriveDe(chauffeurs c) {
        return this.dao.recupererListePriveDe(c);
    }

    @Override
    public List<chauffeurs> recupererListeOrdonne(String statut) {
        return this.dao.recupererListeOrdonne(statut);
    }
}
