/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.reparationsDaoBeanLocal;
import tg.artp.geParc.entities.reparations;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class reparationsServiceBean extends BaseServiceBean<reparations, String> implements reparationsServiceBeanLocal {
    
     @EJB
    private reparationsDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<reparations, String> getDao(){
        return this.dao;
    }
}
