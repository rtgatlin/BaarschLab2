import java.awt.*;
import javax.swing.JFrame;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        EventListPanel eventPanel = new EventListPanel();
        frame.add(eventPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventPanel events){

    }
}
