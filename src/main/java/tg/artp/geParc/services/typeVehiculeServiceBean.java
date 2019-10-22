/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.typeVehiculesDaoBeanLocal;
import tg.artp.geParc.entities.typeVehicules;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class typeVehiculeServiceBean extends BaseServiceBean<typeVehicules, String> implements typeVehiculeServiceBeanLocal {
    
     @EJB
    private typeVehiculesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<typeVehicules, String> getDao(){
        return this.dao;
    }
}
