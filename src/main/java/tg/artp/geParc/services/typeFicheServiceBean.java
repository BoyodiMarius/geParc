/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.typeFicheDaoBeanLocal;
import tg.artp.geParc.entities.typeFiche;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class typeFicheServiceBean extends BaseServiceBean<typeFiche, String> implements typeFicheServiceBeanLocal {
    
     @EJB
    private typeFicheDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<typeFiche, String> getDao(){
        return this.dao;
    }
}
