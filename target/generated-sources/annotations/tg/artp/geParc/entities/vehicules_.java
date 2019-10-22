package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.alertes;
import tg.artp.geParc.entities.chargerCarburant;
import tg.artp.geParc.entities.fiche;
import tg.artp.geParc.entities.modele;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(vehicules.class)
public class vehicules_ { 

    public static volatile SingularAttribute<vehicules, String> imageInterieurVehicule;
    public static volatile SingularAttribute<vehicules, BigDecimal> kilometrageVidange;
    public static volatile SingularAttribute<vehicules, BigDecimal> parametreAmortissement;
    public static volatile SingularAttribute<vehicules, BigDecimal> kilometrageActuel;
    public static volatile SingularAttribute<vehicules, String> couleur;
    public static volatile SingularAttribute<vehicules, Boolean> etatVehicule;
    public static volatile ListAttribute<vehicules, chargerCarburant> listeChargerCarburant;
    public static volatile SingularAttribute<vehicules, String> imageAvantVehicule;
    public static volatile SingularAttribute<vehicules, modele> idModele;
    public static volatile SingularAttribute<vehicules, String> imageArriereVehicule;
    public static volatile SingularAttribute<vehicules, String> numeroChassis;
    public static volatile SingularAttribute<vehicules, String> idVehicule;
    public static volatile SingularAttribute<vehicules, String> imageDroitVehicule;
    public static volatile ListAttribute<vehicules, alertes> listeAlertes;
    public static volatile SingularAttribute<vehicules, Boolean> etatActuel;
    public static volatile SingularAttribute<vehicules, String> immatriculation;
    public static volatile SingularAttribute<vehicules, String> imageGaucheVehicule;
    public static volatile ListAttribute<vehicules, fiche> listeFicheV;

}