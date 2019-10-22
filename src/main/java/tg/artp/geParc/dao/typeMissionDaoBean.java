/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import javax.ejb.Stateless;
import tg.artp.geParc.entities.typeMission;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class typeMissionDaoBean extends BaseDaoBean<typeMission, String> implements typeMissionDaoBeanLocal {
 
    public typeMissionDaoBean(){
        super(typeMission.class);
    }
}
