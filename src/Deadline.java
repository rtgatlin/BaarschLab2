import java.time.LocalDateTime;

public class Deadline extends Event implements Completable{
    private boolean complete;
    public Deadline(String name, LocalDateTime time) {
        complete = false;
        this.setName(name);
        this.setDateTime(time);
    }
    public void complete(){
        complete = true;
    }

    public boolean isComplete(){
        return complete;
    }
    public String[] getDisplayStrings(){
        String[] displayStrings = new String[3];
        displayStrings[0] = "Name: " + this.getName();
        displayStrings[1] = "Date: " + this.getDateTime().toString();
        if(this.isComplete()){
            displayStrings[2] = "Completion Status:Completed";
        }
        else{
            displayStrings[2] = "Completion Status: Not Completed";
        }

        return displayStrings;
    }
}
