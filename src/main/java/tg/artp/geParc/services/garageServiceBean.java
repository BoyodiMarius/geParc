/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.garageDaoBeanLocal;
import tg.artp.geParc.entities.garage;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class garageServiceBean extends BaseServiceBean<garage, String> implements garageServiceBeanLocal {
    
    @EJB
    private garageDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<garage, String> getDao(){
        return this.dao;
    }
}
