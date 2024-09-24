import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        JLabel completeLabel;
        //JLabel complete label if instance of comparable
        //complete label.setText(event.isComplete)
        eventLabel.setFont(new Font("Serif", Font.BOLD, 15));
        add(eventLabel);

        displayEvent();

        //jButton for completing things
        if(event instanceof Completable) {
            completeLabel = new JLabel(" ");
            completeButton = new JButton("Complete");
            completeButton.setFont(new Font("Arial", Font.BOLD, 10));
            completeButton.setPreferredSize(new Dimension(100, 20));
            completeButton.addActionListener(e->{
                ((Completable) event).complete();
                //completeLabel.setText(String.valueOf(((Completable) event).isComplete()));
                //eventLabel.add(completeLabel);
                displayEvent();
                revalidate();
                repaint();
            });


            //USE THIS FOR BUTTON FUNCTIONALITY ((Completable) event).complete();

            add(completeButton);
        }

       // updateUrgency();
    }



    public void displayEvent() {
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

    public void updateUrgency(){
        LocalDate now = LocalDate.now();
        LocalDate eventDate = LocalDate.of(event.getDateTime().getYear(), event.getDateTime().getMonth(),
                event.getDateTime().getDayOfMonth());


        int timeSpan = Period.between(now, eventDate).getDays();
        if(timeSpan<3) {
            setBackground(Color.red);
        }
        else if(timeSpan>3&& timeSpan<7){
            setBackground(Color.yellow);
        }
        else {
            setBackground(Color.green);
        }
    }
}
