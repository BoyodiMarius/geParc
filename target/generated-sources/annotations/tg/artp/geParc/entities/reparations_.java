package tg.artp.geParc.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.garage;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(reparations.class)
public class reparations_ { 

    public static volatile SingularAttribute<reparations, Date> dateDebutReparation;
    public static volatile SingularAttribute<reparations, BigDecimal> prixReparation;
    public static volatile SingularAttribute<reparations, String> imageFacture;
    public static volatile SingularAttribute<reparations, vehicules> idVehicule;
    public static volatile SingularAttribute<reparations, Date> dateFinReparation;
    public static volatile SingularAttribute<reparations, String> idReparation;
    public static volatile SingularAttribute<reparations, garage> idGarage;

}