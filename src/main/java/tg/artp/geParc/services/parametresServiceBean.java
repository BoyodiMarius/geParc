/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.parametresDaoBeanLocal;
import tg.artp.geParc.entities.parametres;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class parametresServiceBean extends BaseServiceBean<parametres, String> implements parametresServiceBeanLocal {
    
     @EJB
    private parametresDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<parametres, String> getDao(){
        return this.dao;
    }
    
}
