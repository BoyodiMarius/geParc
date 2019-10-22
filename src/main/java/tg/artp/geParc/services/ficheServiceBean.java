/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.ficheDaoBeanLocal;
import tg.artp.geParc.entities.fiche;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class ficheServiceBean extends BaseServiceBean<fiche, String> implements ficheServiceBeanLocal {
 
    @EJB
    private ficheDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<fiche, String> getDao(){
        return this.dao;
    }
}
