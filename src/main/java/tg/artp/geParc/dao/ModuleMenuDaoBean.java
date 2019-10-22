/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import javax.ejb.Stateless;
import tg.artp.geParc.entities.ModuleMenu;

/**
 *
 * @author ELVO
 */
@Stateless
public class ModuleMenuDaoBean extends BaseDaoBean<ModuleMenu, Long> implements ModuleMenuDaoBeanLocal {

    public ModuleMenuDaoBean() {
        super(ModuleMenu.class);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
