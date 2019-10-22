package tg.artp.geParc.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tg.artp.geParc.entities.Modules;
import tg.artp.geParc.entities.Profil;
import tg.artp.geParc.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-22T22:15:39")
@StaticMetamodel(UtilisateurModuleProfil.class)
public class UtilisateurModuleProfil_ { 

    public static volatile SingularAttribute<UtilisateurModuleProfil, Utilisateur> utilisateur;
    public static volatile SingularAttribute<UtilisateurModuleProfil, Profil> profil;
    public static volatile SingularAttribute<UtilisateurModuleProfil, Long> idUtilisateurModuleProfil;
    public static volatile SingularAttribute<UtilisateurModuleProfil, Modules> module;

}