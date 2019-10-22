/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.typeCarburantDaoBeanLocal;
import tg.artp.geParc.entities.typeCarburant;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class typeCarburantServiceBean extends BaseServiceBean<typeCarburant, String> implements typeCarburantServiceBeanLocal {
    
    @EJB
    private typeCarburantDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<typeCarburant, String> getDao(){
        return this.dao;
    }
}
