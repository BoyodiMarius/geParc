package tg.artp.geParc.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.chauffeurs;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(affectationChauffeurVehicule.class)
public class affectationChauffeurVehicule_ { 

    public static volatile SingularAttribute<affectationChauffeurVehicule, Date> dateFinAffectation;
    public static volatile SingularAttribute<affectationChauffeurVehicule, String> idAffectationChauffeurVehicule;
    public static volatile SingularAttribute<affectationChauffeurVehicule, vehicules> idVehicule;
    public static volatile SingularAttribute<affectationChauffeurVehicule, Date> dateDebutAffectation;
    public static volatile SingularAttribute<affectationChauffeurVehicule, chauffeurs> idChauffeur;

}