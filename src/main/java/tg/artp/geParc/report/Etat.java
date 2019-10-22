/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.report;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author MANU
 */
public class Etat<T> extends Thread {

    private List listeElement;
    private Class resultatClass;
    private CopyOnWriteArrayList<T> resultat;
    private List<T> listeResultat;
    private Map<String, BigDecimal> sommeDesChamps;

    public Etat(CopyOnWriteArrayList liste, Class classe, int debut, int fin, CopyOnWriteArrayList<T> result) {
        this.listeElement = liste.subList(debut, fin);
        this.resultatClass = classe;
        this.resultat = result;
    }

    public Etat(CopyOnWriteArrayList liste, Class classe, int debut, int fin, List l) {
        this.listeElement = liste.subList(debut, fin);
        this.resultatClass = classe;
        this.listeResultat = l;
    }

    public Etat(List liste, Class classe, List resultat) {
        this.listeElement = liste;
        this.resultatClass = classe;
        this.listeResultat = resultat;
    }

    public List getListeElement() {
        return listeElement;
    }

    public void setListeElement(List listeElement) {
        this.listeElement = listeElement;
    }

    public Class getResultatClass() {
        return resultatClass;
    }

    public void setResultatClass(Class resultatClass) {
        this.resultatClass = resultatClass;
    }

    public CopyOnWriteArrayList<T> getResultat() {
        return resultat;
    }

    public void setResultat(CopyOnWriteArrayList<T> resultat) {
        this.resultat = resultat;
    }

    @Override
    public void run() {
        Class[] t = new Class[1];
        t[0] = Object[].class;
        int tail = this.listeElement.size();
        try {
            Constructor construct = this.resultatClass.getConstructor(t);
            for (int i = 0; i < tail; i++) {
                this.ajouterElement((T) construct.newInstance(this.listeElement.get(i)));
//                if((i % 5) == 0){
//                    try{
//                        Thread.sleep(10);
//                    }
//                    catch(InterruptedException e){
//                        System.err.println(e.getMessage());
//                    }
//                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("Exception : " + e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException s) {
            System.err.println("Exception : " + s.getMessage());
            s.printStackTrace();
        } catch (IllegalAccessException i) {
            System.err.println("Exception : " + i.getMessage());
            i.printStackTrace();
        } catch (InvocationTargetException k) {
            System.err.println("Exception : " + k.getMessage());
            k.printStackTrace();
        } catch (IllegalArgumentException d) {
            System.err.println("Exception : " + d.getMessage());
            d.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Exception : " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    private void ajouterElement(T element) {
        if (this.resultat != null) {
            this.resultat.add(element);
        } else {
            this.listeResultat.add(element);
        }
    }
}
