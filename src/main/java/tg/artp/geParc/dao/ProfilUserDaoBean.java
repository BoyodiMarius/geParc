/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import javax.ejb.Stateless;
import tg.artp.geParc.entities.ProfilUser;

/**
 *
 * @author ocdi caisse
 */
@Stateless
public class ProfilUserDaoBean extends  BaseDaoBean<ProfilUser, Long> implements ProfilUserDaoBeanLocal {
    
    public ProfilUserDaoBean()
    {
        super(ProfilUser.class);
    }

}
