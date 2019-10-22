/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.reparationPanneDaoBeanLocal;
import tg.artp.geParc.entities.reparationPanne;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class reparationPanneServiceBean extends BaseServiceBean<reparationPanne, String> implements reparationPanneServiceBeanLocal {
    
    @EJB
    private reparationPanneDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<reparationPanne, String> getDao(){
        return this.dao;
    }
    
}
