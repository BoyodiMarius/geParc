/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.modeleDaoBeanLocal;
import tg.artp.geParc.entities.modele;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class modeleServiceBean extends BaseServiceBean<modele, String> implements modeleServiceBeanLocal {
    
    @EJB
    private modeleDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<modele, String> getDao(){
        return this.dao;
    }
     
}
