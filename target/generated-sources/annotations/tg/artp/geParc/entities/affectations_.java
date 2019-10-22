package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.affectationPK;
import tg.artp.geParc.entities.chauffeurs;
import tg.artp.geParc.entities.missions;
import tg.artp.geParc.entities.vehicules;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(affectations.class)
public class affectations_ { 

    public static volatile SingularAttribute<affectations, BigDecimal> MontantPercu;
    public static volatile SingularAttribute<affectations, BigDecimal> kilometrageDepart;
    public static volatile SingularAttribute<affectations, vehicules> idVehicule;
    public static volatile SingularAttribute<affectations, BigDecimal> kilometrageArrive;
    public static volatile SingularAttribute<affectations, BigDecimal> carburantConsomme;
    public static volatile SingularAttribute<affectations, affectationPK> idAffectation;
    public static volatile SingularAttribute<affectations, missions> idMission;
    public static volatile SingularAttribute<affectations, chauffeurs> idChauffeur;

}