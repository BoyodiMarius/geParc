/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.affectationVehiculeServiceDaoBeanLocal;
import tg.artp.geParc.entities.affectationVehiculeService;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class affectationVehiculeSerServiceBean extends BaseServiceBean<affectationVehiculeService, String> implements affectationVehiculeSerServiceBeanLocal {
    @EJB
    private affectationVehiculeServiceDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<affectationVehiculeService, String> getDao(){
        return this.dao;
    }
}
