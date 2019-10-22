/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.artp.geParc.dao.BaseDaoBeanLocal;
import tg.artp.geParc.dao.vehiculesDaoBeanLocal;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.report.files.EtatRatioConsommation;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class vehiculesServiceBean extends BaseServiceBean<vehicules, String> implements vehiculesServiceBeanLocal {

    @EJB
    private vehiculesDaoBeanLocal dao;

    @Override
    protected BaseDaoBeanLocal<vehicules, String> getDao() {
        return this.dao;
    }

    @Override
    public List<vehicules> listeVehicules() {
        return this.dao.listeVehicules();
    }

    @Override
    public List<Object[]> recupererRatioConsommation(vehicules ve) {
//    public List<EtatRatioConsommation> recupererRatioConsommation(vehicules ve) {
//        List<EtatRatioConsommation> liste = new ArrayList();
//        List<Object[]> listObject = this.dao.recupererRatioConsommation(ve);
//        EtatRatioConsommation etat;
//        for (Object[] obj : listObject) {
//            etat = new EtatRatioConsommation(obj);
//            liste.add(etat);
//        }
        return this.dao.recupererRatioConsommation(ve);
//        return liste;
    }
}
