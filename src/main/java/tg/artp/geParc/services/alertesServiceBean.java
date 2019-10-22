/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.alertesDaoBeanLocal;
import tg.artp.geParc.entities.alertes;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class alertesServiceBean extends BaseServiceBean<alertes, String> implements alertesServiceBeanLocal {

    @EJB
    private alertesDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<alertes, String> getDao() {
        return this.dao;
    }
}
