/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.affectationChauffeurVehiculeDaoBeanLocal;
import tg.artp.geParc.entities.affectationChauffeurVehicule;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class affectationChauffeurVehiculeServiceBean extends BaseServiceBean<affectationChauffeurVehicule, String> implements affectationChauffeurVehiculeServiceBeanLocal {
    
    @EJB
    private affectationChauffeurVehiculeDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<affectationChauffeurVehicule, String> getDao(){
        return this.dao;
    }
    
}
