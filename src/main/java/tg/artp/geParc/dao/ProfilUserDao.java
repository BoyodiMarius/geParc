/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import javax.ejb.Stateless;
import tg.artp.geParc.entities.ProfilUser;

/**
 *
 * @author SENA
 */
@Stateless
public class ProfilUserDao extends BaseDaoBean<ProfilUser, Long> implements ProfilUserDaoLocal {

    public ProfilUserDao() {
        super(ProfilUser.class);
    }
}
