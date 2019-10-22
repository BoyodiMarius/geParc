/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.marquesDaoBeanLocal;
import tg.artp.geParc.entities.marques;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class marquesServiceBean extends BaseServiceBean<marques, String> implements marquesServiceBeanLocal {
    
    @EJB
    private marquesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<marques, String> getDao(){
        return this.dao;
    }
}
