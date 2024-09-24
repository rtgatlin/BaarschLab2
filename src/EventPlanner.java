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
        String lastDeadlineName = "Last Deadline";
        String lastDeadlineNameAlt = "Final Deadline";
        Deadline lastDeadline = new Deadline(lastDeadlineName, deadline );
        Deadline firstDeadline = new Deadline("Lab 2", deadline);
        Deadline midDeadline = new Deadline("First Deadline", deadline.plusDays(10) );

        LocalDateTime start = LocalDateTime.of(2024, 10, 7, 15, 0);
        LocalDateTime end = LocalDateTime.of(2024, 10, 7, 16, 0);
        String location = "MCS 321";
        String locationAlt =  "MCS 339";

        Meeting firstMeeting = new Meeting("Cult Ritual", start, end, location);
        Meeting lastMeeting = new Meeting("Brazilian Waxing", start.minusDays(8), end.minusDays(8), location);
        Meeting midMeeting = new Meeting("Middle Meeting", start.plusDays(2), end.plusDays(2), location);




        // addEvent(midDeadline);
        events.addEvent(firstDeadline);
        events.addEvent(lastDeadline);
        events.addEvent(firstMeeting);
        events.addEvent(lastMeeting);
        events.addEvent(midMeeting);
    }
}
