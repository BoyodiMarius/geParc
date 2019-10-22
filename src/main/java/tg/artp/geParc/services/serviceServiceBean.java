/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.serviceDaoBeanLocal;
import tg.artp.geParc.entities.services;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class serviceServiceBean extends BaseServiceBean<services, String> implements serviceServiceBeanLocal {
    
    @EJB
    private serviceDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<services, String> getDao(){
        return this.dao;
    }
}
