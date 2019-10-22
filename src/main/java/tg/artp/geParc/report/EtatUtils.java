/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report;

import java.util.List;

/**
 *
 * @author USER
 */
public class EtatUtils {

    public static void mettresParametresExportAValeurParDefault(ConteneurRequeteManagedBean conteneur) {
        conteneur.setCondense(false);
        conteneur.setListeEtatCondense(null);
        conteneur.setMapListeEtat(null);
        conteneur.setMapEtat(null);
    }

    public static void mettresParametresExportPourEtatCondense(ConteneurRequeteManagedBean conteneur, List listeEtat) {
        EtatUtils.mettresParametresExportAValeurParDefault(conteneur);
        conteneur.setCondense(true);
        conteneur.setListeEtatCondense(listeEtat);
    }
}
