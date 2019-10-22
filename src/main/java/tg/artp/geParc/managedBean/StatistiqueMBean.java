/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import tg.artp.geParc.entities.affectations;
import tg.artp.geParc.entities.chargerCarburant;
import tg.artp.geParc.entities.vehicules;
import tg.artp.geParc.report.files.EtatRatioConsommation;
import tg.artp.geParc.services.affectationServiceBeanLocal;
import tg.artp.geParc.services.chargerCarburantServiceBeanLocal;
import tg.artp.geParc.services.vehiculesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class StatistiqueMBean implements Serializable {

    @EJB
    private chargerCarburantServiceBeanLocal chargerCarburantService;
    private List<chargerCarburant> listeChargerCarburant, listeFiltreChargementCarburant;
    @EJB
    private vehiculesServiceBeanLocal vehiculeService;
    private List<vehicules> listeVehicule;

    @EJB
    private affectationServiceBeanLocal affectationService;
    private List<affectations> listeMission;

    private LineChartModel dateModel;
    private vehicules vehicule;
    private LineChartModel lineModel1;
    private List<BigDecimal> liste;
    private List<String> listeR;
    private String annee, mois, titre;
    private List<String> listeAnnee;
    private Date dateDujour;

    private PieChartModel pieModel2;
    private EtatRatioConsommation listeRatio;
    private EntityManager em;

    public StatistiqueMBean() {

    }

    @PostConstruct
    public void chargerElement() {
        this.listeChargerCarburant = new ArrayList();
        this.listeChargerCarburant = this.chargerCarburantService.selectionnerTout();
        this.listeVehicule = new ArrayList();
        this.listeVehicule = this.vehiculeService.selectionnerTout();
        vehicule = vehiculeService.selectionner("VEH01");
        listeAnnee = new ArrayList();
        for (int i = 2010; i <= 2050; i++) {
            String insertion = String.valueOf(i);
            listeAnnee.add(insertion);
        }
        dateDujour = new Date();
        mois = dateToString(dateDujour).substring(3, 5);
        annee = dateToString(dateDujour).substring(6, 10);
        this.listeMission = new ArrayList();
        this.listeMission = this.affectationService.selectionnerTout();

        //constructionStatistique();
        createLineModels();
        createPieModel2();
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public List<EtatRatioConsommation> construireListeRatio(List<Object[]> liste) {
        List<EtatRatioConsommation> listeRatio = new ArrayList();
        EtatRatioConsommation ratio;
        for (Object[] ra : liste) {
            ratio = new EtatRatioConsommation(ra);
            listeRatio.add(ratio);
        }
        return listeRatio;
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        List<EtatRatioConsommation> listeRatio = construireListeRatio(this.vehiculeService.recupererRatioConsommation(null));
//        this.listeRatio = (EtatRatioConsommation) this.vehiculeService.recupererRatioConsommation(vehicule);
//        
//       List<Object[]> list = (List<Object[]>)em.createQuery("SELECT DISTINCT ve.IMMATRICULATION as immat, ve.KILOMETRAGE_ACTUEL as kilActuel,\n"
//                + "carb.quantiteCons as quantiteCons, affect.carbCons as carbCons,\n"
//                + "(carb.quantiteCons + affect.carbCons) as consTotale,\n"
//                + "((carb.quantiteCons + affect.carbCons) / ve.KILOMETRAGE_ACTUEL) as ratio\n"
//                + "FROM vehicules ve\n"
//                + "JOIN\n"
//                + "(SELECT DISTINCT cc.ID_VEHICULE as idVeh, SUM(cc.QUANTITE_CHARGER) as quantiteCons\n"
//                + "FROM chargercarburant cc GROUP BY cc.ID_VEHICULE) carb ON carb.idVeh = ve.ID_VEHICULE\n"
//                + "JOIN\n"
//                + "(SELECT DISTINCT aff.ID_VEHICULE as idVeh, SUM(aff.CARBURANT_CONSOMME) as carbCons\n"
//                + "FROM affectations aff GROUP BY aff.ID_VEHICULE) affect ON affect.idVeh = ve.ID_VEHICULE").getResultList();
//        
//         System.out.println("taille "+list.size());
//         int n = list.size();
//         if(n!=0){
//             for(int i=0; i<=n-1;i++){
//                 pieModel2.set(listeRatio.g, 540);
//             }
//         }
//         
        NumberFormat format = NumberFormat.getInstance();
//        Number myNumber;

        for (EtatRatioConsommation ra : listeRatio) {
            try {
                //             myNumber = null;
                pieModel2.set(ra.getVehicule(), format.parse(ra.getConsommationTotale()));
            } catch (ParseException ex) {
                Logger.getLogger(StatistiqueMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        pieModel2.set;
//        pieModel2.set("Brand 2", 325);
//        pieModel2.set("Brand 3", 702);
//        pieModel2.set("Brand 4", 421);

        pieModel2.setTitle("RATIO");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

    public static String dateToString(Date date) {
        String jour = "" + date.getDate();
        String mois = "" + (date.getMonth() + 1);
        String annee = "" + (date.getYear() + 1900);
        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return (jour + "/" + mois + "/" + annee);
    }

    public LineChartModel test() {
        int veri = 0;
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();

        if (vehicule == null) {
            series1.set(0, 0);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucun véhicule n'a été selectionner "));
        } else {

            series1.setLabel(vehicule.getImmatriculation());
            this.listeFiltreChargementCarburant = new ArrayList();
            int nb = listeChargerCarburant.size();
            if (nb != 0) {
                this.liste = new ArrayList();
                for (int p = 0; p <= nb - 1; p++) {
                    if (vehicule.getImmatriculation().equals(listeChargerCarburant.get(p).getIdVehicule().getImmatriculation())) {
                        liste.add(listeChargerCarburant.get(p).getQuantiteCharger());
                        listeFiltreChargementCarburant.add(listeChargerCarburant.get(p));
                    }
                }
                //System.out.println("nbbb "+liste.size());
                int nbb = liste.size();
                if (nbb != 0) {
                    for (int i = 0; i <= nbb - 1; i++) {
//                        System.out.println("************");
//                        System.out.println("annee choisi "+annee);
//                        System.out.println("mois choisi "+mois);
//                        System.out.println("mois "+dateToString(listeFiltreChargementCarburant.get(i).getDateChargerCarburant()).substring(3, 5));
//                        System.out.println("annee "+dateToString(listeFiltreChargementCarburant.get(i).getDateChargerCarburant()).substring(6, 10));
                        if (dateToString(listeFiltreChargementCarburant.get(i).getDateChargerCarburant()).substring(3, 5).equals(mois)
                                && dateToString(listeFiltreChargementCarburant.get(i).getDateChargerCarburant()).substring(6, 10).equals(annee)
                                && vehicule.getImmatriculation().equals(listeFiltreChargementCarburant.get(i).getIdVehicule().getImmatriculation())) {
                            //int pp=0;
                            BigDecimal y;
                            if (i == 0) {
                                // y = new BigDecimal(pp);
                                y = liste.get(i);
                            } else {
                                y = liste.get(i - 1);
                            }
                            System.out.println("oh");
                            series1.set(listeFiltreChargementCarburant.get(i).getKilometrageAvantChargement(), y);
                            veri += 1;
                        }
                    }
                }
            }
            int s = listeMission.size();
            if (s != 0) {
                for (int y = 0; y <= s - 1; y++) {
                    if (dateToString(listeMission.get(y).getIdMission().getDateRetour()).substring(3, 5).equals(mois)
                            && dateToString(listeMission.get(y).getIdMission().getDateRetour()).substring(6, 10).equals(annee)
                            && listeMission.get(y).getIdVehicule().getImmatriculation().equals(vehicule.getImmatriculation())
                            && listeMission.get(y).getKilometrageArrive() != null && listeMission.get(y).getCarburantConsomme() != null) {
                        series1.set(listeMission.get(y).getKilometrageArrive(), listeMission.get(y).getCarburantConsomme());
                        veri += 1;
                    }
                }
            }
            if (veri == 0) {
                series1.set(0, 0);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", " Aucune statistique disponnible pour les paramètres spécifier "));
            }
            titre = "Statistique du véhicule " + vehicule.getImmatriculation() + " pour " + lemois(mois) + " " + annee;

        }

        model.addSeries(series1);
        return model;
    }

    public String lemois(String parametre) {
        String moisLettre = "";
        if (parametre.equals("01")) {
            moisLettre = "Janvier";
        }
        if (parametre.equals("02")) {
            moisLettre = "Février";
        }
        if (parametre.equals("03")) {
            moisLettre = "Mars";
        }
        if (parametre.equals("04")) {
            moisLettre = "Avril";
        }
        if (parametre.equals("05")) {
            moisLettre = "Mai";
        }
        if (parametre.equals("06")) {
            moisLettre = "Juin";
        }
        if (parametre.equals("07")) {
            moisLettre = "Juillet";
        }
        if (parametre.equals("08")) {
            moisLettre = "Août";
        }
        if (parametre.equals("09")) {
            moisLettre = "Septembre";
        }
        if (parametre.equals("10")) {
            moisLettre = "Octobre";
        }
        if (parametre.equals("11")) {
            moisLettre = "Novembre";
        }
        if (parametre.equals("12")) {
            moisLettre = "Décembre";
        }

        return moisLettre;
    }

    public void createLineModels() {
//        lineModel1 = initLinearModel();
//        lineModel1.setTitle("Linear Chart");
//        lineModel1.setLegendPosition("e");
//        Axis yAxis = lineModel1.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10);

        lineModel1 = test();
        lineModel1.setTitle("Carburant consommée par rapport au kilométrage éffectué");
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Kilometrage"));

        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Consommation (litre)");
        yAxis.setMin(0);
        yAxis.setMax(1000);
    }

    public void constructionStatistique() {
        dateModel = new LineChartModel();
        //String nom= "";

        int nb1 = listeVehicule.size();
        if (nb1 != 0) {
            LineChartSeries[] serie = new LineChartSeries[nb1];
            //String[] nom = new String[nb1];
            for (int i = 0; i <= nb1 - 1; i++) {
                //nom[i] = listeVehicule.get(i).getImmatriculation();

                int nb = listeChargerCarburant.size();
                if (nb != 0) {
                    for (int a = 0; a <= nb - 1; a++) {
                        System.out.println("kilo " + listeChargerCarburant.get(a).getKilometrageAvantChargement());
                        System.out.println("quan " + listeChargerCarburant.get(a).getQuantiteCharger());
                        if (listeVehicule.get(i).getImmatriculation().equals(listeChargerCarburant.get(a).getIdVehicule().getImmatriculation())) {
                            serie[i].set(listeChargerCarburant.get(a).getKilometrageAvantChargement(), listeChargerCarburant.get(a).getQuantiteCharger());
                        }
                    }
                }
            }
            for (int i = 0; i <= nb1 - 1; i++) {
                dateModel.addSeries(serie[i]);
            }

        }

        dateModel.setTitle("Carburant consommée par rapport au kilométrage éffectué");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Consommation (litre)");
        DateAxis axis = new DateAxis("Kilométrage");
        //axis.setTickAngle(-50);
        // axis.setMax("2014-02-01");
        // axis.setTickFormat("%b %#d, %y");

        dateModel.getAxes().put(AxisType.X, axis);

    }

    public chargerCarburantServiceBeanLocal getChargerCarburantService() {
        return chargerCarburantService;
    }

    public void setChargerCarburantService(chargerCarburantServiceBeanLocal chargerCarburantService) {
        this.chargerCarburantService = chargerCarburantService;
    }

    public List<chargerCarburant> getListeChargerCarburant() {
        return listeChargerCarburant;
    }

    public void setListeChargerCarburant(List<chargerCarburant> listeChargerCarburant) {
        this.listeChargerCarburant = listeChargerCarburant;
    }

    public vehiculesServiceBeanLocal getVehiculeService() {
        return vehiculeService;
    }

    public void setVehiculeService(vehiculesServiceBeanLocal vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    public List<vehicules> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<vehicules> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }

    public vehicules getVehicule() {
        return vehicule;
    }

    public void setVehicule(vehicules vehicule) {
        this.vehicule = vehicule;
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public List<chargerCarburant> getListeFiltreChargementCarburant() {
        return listeFiltreChargementCarburant;
    }

    public void setListeFiltreChargementCarburant(List<chargerCarburant> listeFiltreChargementCarburant) {
        this.listeFiltreChargementCarburant = listeFiltreChargementCarburant;
    }

    public List<BigDecimal> getListe() {
        return liste;
    }

    public void setListe(List<BigDecimal> liste) {
        this.liste = liste;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public List<String> getListeAnnee() {
        return listeAnnee;
    }

    public void setListeAnnee(List<String> listeAnnee) {
        this.listeAnnee = listeAnnee;
    }

    public affectationServiceBeanLocal getAffectationService() {
        return affectationService;
    }

    public void setAffectationService(affectationServiceBeanLocal affectationService) {
        this.affectationService = affectationService;
    }

    public List<affectations> getListeMission() {
        return listeMission;
    }

    public void setListeMission(List<affectations> listeMission) {
        this.listeMission = listeMission;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDujour() {
        return dateDujour;
    }

    public void setDateDujour(Date dateDujour) {
        this.dateDujour = dateDujour;
    }

    public EtatRatioConsommation getListeRatio() {
        return listeRatio;
    }

    public void setListeRatio(EtatRatioConsommation listeRatio) {
        this.listeRatio = listeRatio;
    }

}
