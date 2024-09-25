import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.JFrame;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        EventListPanel eventPanel = new EventListPanel();
        frame.add(eventPanel);
        addDefaultEvents(eventPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel events){
        LocalDateTime deadline = LocalDateTime.of(2024, 9, 25, 15, 0);
        Deadline labDeadline = new Deadline("Lab 2", deadline);

        LocalDateTime start = LocalDateTime.of(2024, 10, 7, 15, 0);
        LocalDateTime end = LocalDateTime.of(2024, 10, 7, 16, 0);
        String location = "THE DEN OF ALL THAT LIES BENEATH";

        Meeting cultMeeting = new Meeting("Cult Ritual", start, end, location);

        events.addEvent(labDeadline);
        events.addEvent(cultMeeting);

    }
}
