import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Meeting extends Event implements Completable{
    private boolean complete;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;
    Duration duration;
    public Meeting(String name, LocalDateTime time, LocalDateTime end, String Location){
        complete = false;
        this.setName(name);
        this.setDateTime(time);
        this.setEndDateTime(end);
        this.setLocation(Location);
        this.setDuration(time, end);
    }

    public void complete(){
        complete = true;
    }
    public boolean isComplete(){
        return complete;
    }
    public String getName(){
        return super.getName();
    }
    public LocalDateTime getDateTime(){
        return startDateTime;
    }
    public LocalDateTime getEndDateTime(){
        return endDateTime;
    }
    public Duration getDuration(){
        return duration;
    }
    public String getLocation(){
        return location;
    }
    public void setDateTime(LocalDateTime startDateTime){
        this.startDateTime=startDateTime;
    }
    public void setEndDateTime(LocalDateTime endDateTime){
        this.endDateTime=endDateTime;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public void setDuration(LocalDateTime time, LocalDateTime end){
       // int duration=0;
        Duration duration = Duration.between(time, end);
        this.duration=duration;
    }
    public String[] getDisplayStrings(){
        String[] displayStrings;
        displayStrings=new String[6];
        displayStrings[0]="Name: " + name;
        displayStrings[1]="Start: " + startDateTime.toString();
        displayStrings[2]="End : " + endDateTime.toString();
        displayStrings[3]="Location: " + location;
        displayStrings[4]="Duration: " + duration.toString();
        if(this.isComplete()){
            displayStrings[5] = "Completion Status: Completed";
        }
        else{
            displayStrings[5] = "Completion Status: Not Completed";
        }

        return displayStrings;
    }
}
