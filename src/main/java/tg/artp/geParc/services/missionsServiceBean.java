/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.missionsDaoBeanLocal;
import tg.artp.geParc.entities.missions;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class missionsServiceBean extends BaseServiceBean<missions, String> implements missionsServiceBeanLocal {
    
    @EJB
    private missionsDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<missions, String> getDao(){
        return this.dao;
    }
}
