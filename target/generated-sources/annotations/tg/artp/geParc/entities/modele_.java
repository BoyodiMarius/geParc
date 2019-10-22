package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.marques;
import tg.artp.geParc.entities.typeCarburant;
import tg.artp.geParc.entities.typeVehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(modele.class)
public class modele_ { 

    public static volatile SingularAttribute<modele, marques> idMarque;
    public static volatile SingularAttribute<modele, typeVehicules> idTypeVehicule;
    public static volatile SingularAttribute<modele, typeCarburant> idTypeCarburant;
    public static volatile SingularAttribute<modele, String> designation;
    public static volatile SingularAttribute<modele, BigDecimal> parametreVidange;
    public static volatile SingularAttribute<modele, String> idModele;

}