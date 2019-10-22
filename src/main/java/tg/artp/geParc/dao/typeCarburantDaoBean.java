/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import javax.ejb.Stateless;
import tg.artp.geParc.entities.typeCarburant;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class typeCarburantDaoBean extends BaseDaoBean<typeCarburant, String> implements typeCarburantDaoBeanLocal {
    
    public typeCarburantDaoBean(){
        super(typeCarburant.class);
    }
    
}
