package tg.artp.geParc.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.typeFiche;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(fiche.class)
public class fiche_ { 

    public static volatile SingularAttribute<fiche, Date> dateExpiration;
    public static volatile SingularAttribute<fiche, vehicules> idVehicule;
    public static volatile SingularAttribute<fiche, Date> dateDelivrer;
    public static volatile SingularAttribute<fiche, typeFiche> idTypeFiche;
    public static volatile SingularAttribute<fiche, Boolean> etatFiche;
    public static volatile SingularAttribute<fiche, String> idFiche;

}