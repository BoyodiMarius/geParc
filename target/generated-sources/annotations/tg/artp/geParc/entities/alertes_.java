package tg.artp.geParc.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(alertes.class)
public class alertes_ { 

    public static volatile SingularAttribute<alertes, String> heureSignalisation;
    public static volatile SingularAttribute<alertes, Long> idAlerte;
    public static volatile SingularAttribute<alertes, vehicules> idVehicule;
    public static volatile SingularAttribute<alertes, Date> dateSignalisation;
    public static volatile SingularAttribute<alertes, String> message;
    public static volatile SingularAttribute<alertes, Boolean> consulter;

}