package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-19T23:18:06")
@StaticMetamodel(ProfilUser.class)
public class ProfilUser_ { 

    public static volatile SingularAttribute<ProfilUser, Utilisateur> utilisateur;
    public static volatile SingularAttribute<ProfilUser, Profil> profil;
    public static volatile SingularAttribute<ProfilUser, Long> idUserProfil;

}