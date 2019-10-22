package tg.artp.geParc.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(chargerCarburant.class)
public class chargerCarburant_ { 

    public static volatile SingularAttribute<chargerCarburant, String> idChargerCarburant;
    public static volatile SingularAttribute<chargerCarburant, BigDecimal> kilometrageAvantChargement;
    public static volatile SingularAttribute<chargerCarburant, vehicules> idVehicule;
    public static volatile SingularAttribute<chargerCarburant, BigDecimal> quantiteCharger;
    public static volatile SingularAttribute<chargerCarburant, Date> dateChargerCarburant;

}