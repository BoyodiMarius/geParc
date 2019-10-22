/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.List;
import javax.ejb.Stateless;
import tg.artp.geParc.entities.Menu;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.ProfilMenu;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class ProfilMenuDaoBean extends BaseDaoBean<ProfilMenu, Long> implements ProfilMenuDaoBeanLocal {

    public ProfilMenuDaoBean() {
        super(ProfilMenu.class);
    }

    @Override
    public ProfilMenu find(Menu menu, Profil profil) {
        List<ProfilMenu> found = em.createQuery("SELECT pm FROM ProfilMenu pm "
                + "WHERE pm.menu.idMenu = :menu AND pm.profil.idProfil = :profil ")
                .setParameter("profil", profil.getIdProfil())
                .setParameter("menu", menu.getIdMenu())
                .getResultList();

        return found.isEmpty() ? null : found.get(0);
    }
}
