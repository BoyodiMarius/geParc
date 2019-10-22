/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;

/**
 *
 * @author mandataireTypeOperation-AKIM
 */
@Stateless
public class ModuleDaoBean extends BaseDaoBean<Modules, Long> implements ModuleDaoBeanLocal {

    public ModuleDaoBean() {
        super(Modules.class);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Modules> listeModuleByProfil(Profil profil) {
        List<Modules> modulesSelected;
       Query query;
        try {
            query = em.createQuery("SELECT DISTINCT d.module FROM Droit d WHERE d.idDroit IN (SELECT dp.droit.idDroit from DroitProfil dp WHERE dp.profil.idProfil =:profil)");
            query.setParameter("profil", profil.getIdProfil());
            modulesSelected = (List<Modules>) query.getResultList();
        } catch (Exception e) {
            modulesSelected = new ArrayList<>();
        }
        return modulesSelected;
    }

}
