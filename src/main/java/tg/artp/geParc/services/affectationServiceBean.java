/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.affectationDaoBeanLocal;
import tg.artp.geParc.entities.affectations;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class affectationServiceBean extends BaseServiceBean<affectations, String> implements affectationServiceBeanLocal {
    
    @EJB
    private affectationDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<affectations, String> getDao(){
        return this.dao;
    }
}
