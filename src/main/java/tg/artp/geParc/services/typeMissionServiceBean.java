/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.typeMissionDaoBeanLocal;
import tg.artp.geParc.entities.typeMission;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class typeMissionServiceBean extends BaseServiceBean<typeMission, String> implements typeMissionServiceBeanLocal {
    
     @EJB
    private typeMissionDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<typeMission, String> getDao(){
        return this.dao;
    }
}
