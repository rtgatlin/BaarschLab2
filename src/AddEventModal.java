import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/*
public class AddEventModal extends JFrame{
    private EventListPanel eventListPanel;
    AddEventModal modal;
    record Attribute(String name, JComponent value){}
    ArrayList<Attribute> attributes;
    JPanel infoCollectorPanel;
    JComboBox<String> eventTypeComboBox;
    LocalDateTime temporary;

    public static final String[] eventTypes =  {"Meeting", "Deadline"};
    public AddEventModal(){
        modal = this;                            // Needed so that dispose can be in action Listener.
        this.eventListPanel = eventListPanel;    // Need so Modal can send new Animal back to Animal List.

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

        JButton addAnimalButton = new JButton("Submit");
        addAnimalButton.addActionListener(getEventCreator());

        panel.setPreferredSize(new Dimension(500, 500));
        panel.add(addAnimalButton);

        return panel;
    }

    // This returns an ActionListener that adds the appropriate fields to the infoCollectorPanel depending on which
    // Animal is selected from the AnimalTypeComboBox
    private ActionListener getEventChooser(){
        return e -> {
            attributes.clear();
            infoCollectorPanel.removeAll();
            switch (eventTypeComboBox.getSelectedIndex()) {
                case 0: {
                    attributes.add(new Attribute("Name", new JTextField(20)));
                    attributes.add(new Attribute("Start Time", new JTextField(12)));
                    attributes.add(new Attribute("End Time(Enter numerals only, separate by space)", new JTextField(12)));
                    attributes.add(new Attribute("Location", new JTextField(20)));
                    break;
                }
                case 1: {
                    attributes.add(new Attribute("Name", new JTextField(20)));
                    attributes.add(new Attribute("Date (Enter numerals only, separate by space)", new JTextField(12)));
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
    // This function returns an ActionLister that creates an animal based on the fields in the InfoCollection Panel
    // It calls the appropriate constructor based on the AnimalTypeComboBox.
    // It then disposes of the creator modal.
    private ActionListener getEventCreator(){
        LocalDateTime now = LocalDateTime.now();
        return e -> {
            Event newEvent = new Deadline("Error", now);
            switch (eventTypeComboBox.getSelectedIndex()) {
                case 0: {
                    String year = Integer.valueOf;
                    temporary = LocalDateTime.of(parseInt(getInput((attributes.get(1).value))), );
                    newEvent = new Meeting(getInput(attributes.get(0).value), (getInput(attributes.get(1).value)).toString(),
                    getInput(attributes.get(2).value), getInput(attributes.get(3).value));
                }
            }
        };
    }

    //Helps get text values from generic components.
    private String getInput (JComponent c) {
        if (c instanceof JTextComponent) {
            return ((JTextComponent) c).getText();
        }
        return "";
    }
}


 */