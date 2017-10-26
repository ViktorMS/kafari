package is.hi.kafari.model;

import javax.validation.constraints.*;


/**
 *
 * @author Einar
 * 
 * Klasi sem geymir parametra úr dive formi
 * til að hægt sé að validata þá
 */
public class DiveForm {
 
    @NotNull(message = "Please enter dive location")
    @Size(min=1, max=100, message = "Please enter dive location")
    private String divingLocation;
    
    @NotNull(message = "Please enter total time of dive")
    @Min(value=0, message = "Did you travel back in time during the dive? Please enter a positive value.")
    @Max(value=1440, message = "Dangerously long dive! Please shorten your dive.")
    private int totalTime;
    
    @NotNull(message = "Please enter maximum depth during dive")
    @Min(value=0, message = "How did you take a dive above water? Please enter a positive value.")
    @Max(value=60, message = "Dangerously deep dive! Dives under 50 meters are not allowed. Dives under 60 meters are outside the dive tables.")
    private int maxDepth;

    // smiður fyrir tóman hlut
    public DiveForm() {
    }
  
    // smiður
    public DiveForm(String divingLocation, int totalTime, int maxDepth) {
        this.divingLocation = divingLocation;
        this.totalTime = totalTime;
        this.maxDepth = maxDepth;
    }
    
    @Override
    public String toString() {
        return divingLocation + totalTime + maxDepth;
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
  
    
}
