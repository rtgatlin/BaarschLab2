import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton;

    public EventPanel(Event event) {
        setPreferredSize(new Dimension(600, 50));
        this.event = event;
        setBackground(new Color(150, 150, 50));

        JLabel eventLabel = new JLabel("Event: " + "\t");
        eventLabel.setFont(new Font("Serif", Font.BOLD, 15));
        add(eventLabel);

        displayEvent();

        //jButton for completing things
        //its sole purpose is to set an event to complete
        if(event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.setFont(new Font("Arial", Font.BOLD, 10));
            completeButton.setPreferredSize(new Dimension(100, 20));
            completeButton.addActionListener(e->{
                ((Completable) event).complete();
                //redisplay the event, so it appears as though only one thing changed
                displayEvent();
                revalidate();
                repaint();
            });
            add(completeButton);
        }

        updateUrgency();
    }


    //displays the event
    public void displayEvent() {
        this.removeAll();
        ArrayList<String> eventID=new ArrayList<>();
        JLabel eventLabel = new JLabel();
        eventLabel.setFont(new Font("Serif", Font.BOLD, 10));

        for(String item : event.getDisplayStrings()) {
            eventID.add(item);
        }
        for(String item : eventID) {
            JLabel itemLabel = new JLabel(item);
            itemLabel.setText(item);
            add(itemLabel);
        }
        add(eventLabel);
    }

    //FULL DISCLOSURE: I coded this before Dr. Baarsch told us it was actually optional
    //I've left this in for fun
    public void updateUrgency(){
        LocalDate now = LocalDate.now();
        LocalDate eventDate = LocalDate.of(event.getDateTime().getYear(), event.getDateTime().getMonth(),
                event.getDateTime().getDayOfMonth());


        int timeSpan = Period.between(now, eventDate).getDays();
        //if the timespan is three days or less, it goes red
        if(timeSpan<=3) {
            setBackground(Color.red);
        }
        //if you've got a week left, it's yellow
        else if(timeSpan>3&& timeSpan<7){
            setBackground(Color.yellow);
        }
        //otherwise, green
        else {
            setBackground(Color.green);
        }
    }
}
