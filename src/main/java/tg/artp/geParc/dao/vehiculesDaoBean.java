  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tg.artp.geParc.entities.vehicules;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class vehiculesDaoBean extends BaseDaoBean<vehicules, String> implements vehiculesDaoBeanLocal {

    public vehiculesDaoBean() {
        super(vehicules.class);
    }

    @Override
    public List<vehicules> listeVehicules() {
        Query q = this.em.createQuery("SELECT v FROM vehicules v ORDER BY v.etatVehicule desc");
        return q.getResultList();
    }

    @Override
    public List<Object[]> recupererRatioConsommation(vehicules ve) {
        String requete = "SELECT DISTINCT ve.IMMATRICULATION as immat, ve.KILOMETRAGE_ACTUEL as kilActuel,\n" +
"                (case when carb.quantiteCons is null then 0 else carb.quantiteCons end) as quantiteCons,\n" +
"								(case when affect.carbCons is null then 0 else  affect.carbCons end) as carbCons,\n" +
"                ((case when carb.quantiteCons is null then 0 else carb.quantiteCons end)\n" +
"								+ (case when affect.carbCons is null then 0 else affect.carbCons end)) as consTotale,\n" +
"                (((case when carb.quantiteCons is null then 0 else carb.quantiteCons end)\n" +
"								+ (case when affect.carbCons is null then 0 else affect.carbCons end)) / ve.KILOMETRAGE_ACTUEL) as ratio\n" +
"                 FROM vehicules ve\n" +
"                 JOIN\n" +
"                (SELECT DISTINCT cc.ID_VEHICULE as idVeh, SUM(cc.QUANTITE_CHARGER) as quantiteCons\n" +
"                FROM chargercarburant cc GROUP BY cc.ID_VEHICULE) carb ON carb.idVeh = ve.ID_VEHICULE\n" +
"                LEFT JOIN\n" +
"                (SELECT DISTINCT aff.ID_VEHICULE as idVeh, SUM(aff.CARBURANT_CONSOMME) as carbCons\n" +
"                FROM affectations aff GROUP BY aff.ID_VEHICULE) affect ON affect.idVeh = ve.ID_VEHICULE";

//        String requete = "SELECT DISTINCT ve.immatriculation, "
//        String requete = "SELECT DISTINCT ve.idModele.designation, ve.idModele.idMarque.libelleMarque, ve.immatriculation, "
//                + " ve.kilometrageActuel, carb.quantiteChargee, affs.carburantConsomme,"
//                + " (carb.quantiteChargee + affs.carburantConsomme),"
//                + " ((carb.quantiteChargee + affs.carburantConsomme) / ve.kilometrageActuel)  "
//                + " FROM vehicules ve"
//                + " JOIN (SELECT DISTINCT cc.idVehicule.idVehicule as idVeh, SUM(cc.quantiteCharger) as quantiteChargee"
//                + " FROM chargerCarburant cc GROUP BY cc.idVehicule.idVehicule) carb ON carb.idVeh = ve.idVehicule "
//                + " JOIN (SELECT DISTINCT aff.idVehicule.idVehicule as idVeh, SUM(aff.carburantConsomme) as carburantConsomme) "
//                + " FROM affectations aff GROUP BY aff.idVehicule.idVehicule) affs ON affs.idVeh = ve.idVehicule"
//                + " where 1 = 1 ";
//        Map<String, Object> parametres = new HashMap();
//        if (ve != null) {
//            requete += " AND ve.idVehicule = :vehicule ";
//            parametres.put("vehicule", ve.getIdVehicule());
//        }
//        requete += " GROUP BY ve.idModele.designation, ve.idModele.idMarque.libelleMarque, "
//                + " ve.immatriculation, ve.kilometrageActuel, cc.quantiteCharger, aff.carburantConsomme";
//                + " ORDER ve.immatriculation ASC ";
        Query query = this.em.createNativeQuery(requete);
//        for (String cle : parametres.keySet()) {
//            query.setParameter(cle, parametres.get(cle));
//        }

        return query.getResultList();
    }
}
