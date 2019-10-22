/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.chargerCarburantDaoBeanLocal;
import tg.artp.geParc.entities.chargerCarburant;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class chargerCarburantServiceBean extends BaseServiceBean<chargerCarburant, String> implements chargerCarburantServiceBeanLocal {
 
    @EJB
    private chargerCarburantDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<chargerCarburant, String> getDao(){
        return this.dao;
    }
}
