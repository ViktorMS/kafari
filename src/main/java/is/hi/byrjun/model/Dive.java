package is.hi.byrjun.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Einar
 * 
 * Inniheldur upplýsingar um dýfu
 * 
 */
@Entity
@Table (name="dive")
public class Dive {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="diverId")
    private Diver diver;
    
    //private Long diverId;
    private Timestamp diveDate; //  new java.sql.Timestamp( utilDate.getTime() )
    private String divingLocation;
    private int totalTime;
    private int maxDepth;
    private String decompression;
    private String letter;

    // smiður fyrir tóman hlut
    public Dive() {
    }
  
    // smiður
    public Dive(Diver diver, Timestamp diveDate, String divingLocation, int totalTime, int maxDepth, String decompression, String letter) {
        this.diver = diver;
        this.diveDate = diveDate;
        this.divingLocation = divingLocation;
        this.totalTime = totalTime;
        this.maxDepth = maxDepth;
        this.decompression = decompression;
        this.letter = letter;
    }
    
    @Override
    public String toString() {
        return diveDate + divingLocation + totalTime + maxDepth + decompression + letter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diver getDiver() {
        return diver;
    }

    public void setDiver(Diver diver) {
        this.diver = diver;
    }

    public Timestamp getDiveDate() {
        return diveDate;
    }

    public void setDiveDate(Timestamp diveDate) {
        this.diveDate = diveDate;
    }

    public String getDivingLocation() {
        return divingLocation;
    }

    public void setDivingLocation(String divingLocation) {
        this.divingLocation = divingLocation;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getDecompression() {
        return decompression;
    }

    public void setDecompression(String decompression) {
        this.decompression = decompression;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    
    
}
