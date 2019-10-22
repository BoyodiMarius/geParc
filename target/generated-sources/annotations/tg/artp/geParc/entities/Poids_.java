package tg.artp.geParc.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.UtilisateurModulePoids;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(Poids.class)
public class Poids_ { 

    public static volatile SingularAttribute<Poids, Long> idPoids;
    public static volatile ListAttribute<Poids, UtilisateurModulePoids> ListUtilisateurModulePoids;
    public static volatile SingularAttribute<Poids, Integer> valeurPoids;
    public static volatile SingularAttribute<Poids, BigDecimal> valeurLimite;

}