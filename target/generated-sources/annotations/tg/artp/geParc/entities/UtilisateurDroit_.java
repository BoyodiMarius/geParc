package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Droit;
import tg.artp.geParc.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:38")
@StaticMetamodel(UtilisateurDroit.class)
public class UtilisateurDroit_ { 

    public static volatile SingularAttribute<UtilisateurDroit, Long> idUtilisateurDroit;
    public static volatile SingularAttribute<UtilisateurDroit, Utilisateur> utilisateur;
    public static volatile SingularAttribute<UtilisateurDroit, Droit> droit;

}