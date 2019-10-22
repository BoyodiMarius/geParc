/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import tg.artp.geParc.entities.services;
import tg.artp.geParc.services.servicesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class serviceMBean implements Serializable {
    
    @EJB
    private servicesServiceBeanLocal service;
    private List<services> listeService;
    
    public serviceMBean(){
        
    }
    
    @PostConstruct
    public void chargerElement(){
        this.listeService = new ArrayList();
        this.listeService = this.service.selectionnerTout();
    }

    public servicesServiceBeanLocal getService() {
        return service;
    }

    public void setService(servicesServiceBeanLocal service) {
        this.service = service;
    }

    public List<services> getListeService() {
        return listeService;
    }

    public void setListeService(List<services> listeService) {
        this.listeService = listeService;
    }
    
    
    
}
