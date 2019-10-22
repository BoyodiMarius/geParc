/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.artp.geParc.managedBean;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author BOYODI Wiyow Marius
 */
public class test extends TimerTask{

    private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;
    
    @ManagedProperty(value = "#{Declencheur}")
    private Declencheur execution;
    
    
    @Override
    public void run() {
        System.out.println("06/10/2017");
        Date today = new Date();
        System.out.println(" timer **********"+today+"****");
        execution.declencheur();
//        long currentTime = System.currentTimeMillis();
//        long stopTime = currentTime +1000;
//        while(stopTime != System.currentTimeMillis()){
//            System.out.println("***************************");
//            Date today = new Date();
//            System.out.println(" timer **********"+today+"****");
//            execution.declencheur();
//        }
    }
    
    public static void startTask(){
        test task = new test();
        Timer timer = new Timer();
        timer.schedule(task, new Date(), ONCE_PER_DAY);
    }

//    public static void main(String args[]){
//        startTask();
//    }

    public Declencheur getExecution() {
        return execution;
    }

    public void setExecution(Declencheur execution) {
        this.execution = execution;
    }
    
    
    
    
    
    
}
