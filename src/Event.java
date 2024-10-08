import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public abstract class Event implements Comparable<Event> {
    String name;
    LocalDateTime dateTime;

    public String getName(){
        return name;
    }
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public void setName(String name){
        this.name = name;
    }
    public int compareTo(Event other){
        return this.dateTime.compareTo(other.dateTime);
    }
    public String[] getDisplayStrings(){
        String[] displayStrings = new String[0];
        return displayStrings;
    }
}
