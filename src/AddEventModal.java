import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddEventModal extends JFrame{
    private EventListPanel eventListPanel;
    AddEventModal modal;
    record Attribute(String name, JComponent value){}
    ArrayList<Attribute> attributes;
    JPanel infoCollectorPanel;
    JComboBox<String> eventTypeComboBox;

    public static final String[] eventTypes =  {"Meeting", "Deadline"};
    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;                            // Needed so that dispose can be in action Listener.
        this.eventListPanel = eventListPanel;    // Need so Modal can send new event back to eventList.

        this.setTitle("Add Event");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(addEventPanel());
        this.pack();
        this.setVisible(true);
    }

    private JPanel addEventPanel(){
        JPanel panel = new JPanel();
        attributes = new ArrayList<>();

        infoCollectorPanel = new JPanel();
        infoCollectorPanel.setPreferredSize(new Dimension(600, 400));
        infoCollectorPanel.setBackground(Color.gray);

        eventTypeComboBox = new JComboBox<String>(eventTypes);
        eventTypeComboBox.addActionListener(getEventChooser());

        panel.add(eventTypeComboBox);
        panel.add(infoCollectorPanel);

        JButton addEventButton = new JButton("Submit");
        addEventButton.addActionListener(getEventCreator());

        panel.setPreferredSize(new Dimension(500, 500));
        panel.add(addEventButton);

        return panel;
    }

    //This returns an ActionListener that adds the appropriate fields to the infoCollectorPanel depending on which
    //event is selected from the eventTypeComboBox
    private ActionListener getEventChooser(){
        return e -> {
            attributes.clear();
            infoCollectorPanel.removeAll();
            switch (eventTypeComboBox.getSelectedIndex()) {
                case 0: {
                    attributes.add(new Attribute("Name", new JTextField(20)));
                    attributes.add(new Attribute("Meeting Start DateTime", new DateTimeInput()));
                    attributes.add(new Attribute("Meeting End DateTime", new DateTimeInput()));
                    attributes.add(new Attribute("Location", new JTextField(20)));
                    break;
                }
                case 1: {
                    attributes.add(new Attribute("Name", new JTextField(20)));
                    attributes.add(new Attribute("Deadline Date", new DateTimeInput()));
                    break;
                }
            }
            attributes.forEach(attr -> {
                infoCollectorPanel.add(new JLabel(attr.name));
                infoCollectorPanel.add(attr.value);
            });
            infoCollectorPanel.revalidate();
            infoCollectorPanel.repaint();
        };
    }
    // This function returns an ActionLister that creates an event based on the fields in the InfoCollector Panel
    // It calls the appropriate constructor based on the eventTypeComboBox.
    // It then disposes of the creator modal.
    private ActionListener getEventCreator(){
        return e -> {
            String name = " ";
            LocalDateTime startTime =null;
            LocalDateTime endTime= null;
            LocalDateTime deadline = null;
            String location = "";

            //grabs all the info typed into the boxes
            for(Attribute attr : attributes){
                JComponent component = attr.value;
                switch(attr.name()){
                    case "Name":
                        name = ((JTextField)component).getText();
                        break;
                    case "Meeting Start DateTime":
                        startTime = ((DateTimeInput) component).getDateInput();
                        break;
                    case "Meeting End DateTime":
                        endTime = ((DateTimeInput) component).getDateInput();
                        break;
                    case "Deadline Date":
                        deadline = ((DateTimeInput) component).getDateInput();
                        break;
                    case "Location":
                        location = ((JTextField)component).getText();
                    break;
                }
            }

            //takes all the info that was just processed and actually turns them into events
            Event addingEvent;
            if(eventTypeComboBox.getSelectedIndex() == 0){
                addingEvent=new Meeting(name, startTime, endTime, location);
            }
            else{
                addingEvent = new Deadline(name, deadline);
            }
            eventListPanel.addEvent(addingEvent);

            modal.dispose(); //gets rid of the modal when you click submit
                             //why you don't have to add an event listener here is beyond me
        };
    }

    //this was added in order to make it possible to take each individual element of the date and time
    //and read them
    private static class DateTimeInput extends JPanel{
        private JTextField yearText;
        private JTextField monthText;
        private JTextField dayText;
        private JTextField hourText;
        private JTextField minuteText;

        public DateTimeInput() {
            yearText = new JTextField(4);
            monthText = new JTextField(2);
            dayText = new JTextField(2);
            hourText = new JTextField(2);
            minuteText = new JTextField(2);

            JLabel yearLabel = new JLabel("Year");
            add(yearLabel);
            add(yearText);
            JLabel monthLabel = new JLabel("Month");
            add(monthLabel);
            add(monthText);
            JLabel dayLabel = new JLabel("Day");
            add(dayLabel);
            add(dayText);
            JLabel hourLabel = new JLabel("Hour");
            add(hourLabel);
            add(hourText);
            JLabel minuteLabel = new JLabel("Minute");
            add(minuteLabel);
            add(minuteText);
        }
        public LocalDateTime getDateInput(){
            int year = Integer.parseInt(yearText.getText());
            int month = Integer.parseInt(monthText.getText());
            int day = Integer.parseInt(dayText.getText());
            int hour = Integer.parseInt(hourText.getText());
            int minute = Integer.parseInt(minuteText.getText());

            return LocalDateTime.of(year, month, day, hour, minute);
        }
    }

    //Helps get text values from generic components.
    private String getInput (JComponent c) {
        if (c instanceof JTextComponent) {
            return ((JTextComponent) c).getText();
        }
        return "";
    }
}
