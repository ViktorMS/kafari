/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.kafari.services;

import is.hi.kafari.model.Dive;
import is.hi.kafari.model.Diver;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *  Klasinn heldur utan um reikning á OTU einingum við köfun. Hann hefur aðferðir
 * til að reikna út heildar OTU í einni köfun eða heildar OTU í köfunum í einum 
 * ArrayList. Hann geymir einnig fasta um ráðlögð viðmið og hættumörk þegar það
 * kemur að því að innbyrða súrefni. 
 * @author Símon Örn Reynisson <sor7@hi.is>
 */
public class OTUService {
    
    /*
    CONSTANTS
    */
    //OTU mörk við eðlilegan dag; OTU ætti ekki að fara yfir þetta á einum degi
    private final int dailyLimit = 300;  
    //OTU mörk sem aldrei má fara yfir á einum degi.
    private final int dailyMax = 850;
    
    //Mörk sem valda því að súrefnisupptaka minnkar um 10 % hjá 50% aðila, lungnaskaði
    private final int reducedLungCapacity = 1425;
    
    //OTU mörk við eðlilega viku, ætti ekki að fara yfir þetta í einni viku. 
    private final int weeklyLimit = 2100;
    
        //OTU mörk við eðlilega viku, ætti ekki að fara yfir þetta í einni viku. 
    private final int weeklyMax = 2660;
    
    
    
    /**
     * 
     * @param depth Dýpt köfunar
     * @param diveTime Lengd köfunar
     * @return streng sem tilgreinir OTU m.v. köfun
     */
    public String getOTU(int depth, int diveTime){
        
        double OTU  = getOTU(depth, diveTime, 0.21);
        
        return OTU + " OTU";
    }
    
    /**
     * Tekur inn lista af köfunum og reiknar út heildar OTU fyrir þær allar
     * @param dives ArrayList <Dive>
     * @return Strengur sem geymir samtals OTU
     */
    public String getOTU(ArrayList<Dive> dives){
        int sum = 0;
        for(Dive d : dives){
            sum+=getOTU(d.getMaxDepth(),d.getTotalTime(),0.21);
            
        }
        
        return Integer.toString(sum) + " OTU";
    }
    
    /**
     * Tekur inn dýpt, köfun, og lengd köfunar ásamt súrefnisprósentu í öndunarlofti,
     * og skilar magni súrefnis sem andað er í OTU mælieingunni. 
     * @param depth
     * @param diveTime
     * @param oxygenPercentage
     * @return double: Magn OTU eftir köfun. 
     */
    private double getOTU(int depth, int diveTime, double oxygenPercentage){
        
        double pO2 = (depth /10.0)+1* oxygenPercentage;
        double kp = Math.pow((pO2-0.5)/0.5,(1/1.2));
        System.out.println("pO2 = " + pO2 +  "kp ="  + kp);
        
        return diveTime*kp;
        
        
    }

    
    //Getters fyrir fasta
    public int getDailyLimit() {
        return dailyLimit;
    }

    public int getDailyMax() {
        return dailyMax;
    }

    public int getReducedLungCapacity() {
        return reducedLungCapacity;
    }

    public int getWeeklyLimit() {
        return weeklyLimit;
    }

    public int getWeeklyMax() {
        return weeklyMax;
    }
    
    
    
    
    
    //TEST FALL
    public static void main(String args[]){
        int depth = 30;
        double oxygenPercentage = 0.21;
        ArrayList<Dive> test = new ArrayList();
        Diver Joe = new Diver ( "Joe", "1234");
        Timestamp time = new Timestamp(5000);
        for(int i = 1; i < 10 ; i++) {
            //(Diver diver, Timestamp diveDate, String divingLocation, int totalTime, int maxDepth, String decompression, String letter)
            test.add(new Dive(Joe,time,"Inside",i*10,i*5,"void","A"));
        }
        double pO2 = (depth /10.0)+1 * oxygenPercentage;
        
        
        
        

        
    }
    
}
