import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;



public class EventListPanel extends JPanel{
    ArrayList<Event> events;
    JPanel controlPanel = new JPanel();
    JPanel displayPanel = new JPanel();
    final String[] SORT_OPTIONS = {"Name", "Date", "Reverse Name", "Reverse Date"};
    final String[] FILTERS = {"Completed", "Deadlines", "Meetings"};
    JComboBox sortDropDown = new JComboBox(SORT_OPTIONS);
    JButton addEventButton = new JButton("Add Event");
    ArrayList<JCheckBox> filters;

    public EventListPanel() {
        setPreferredSize(new Dimension(750, 1000));
        setBackground(Color.black);
        this.events = new ArrayList<>();

        //control panel
        controlPanel.setPreferredSize(new Dimension(700, 300));
        add(controlPanel);

        //Sort Drop Down Menu (JCombo Box)
        sortDropDown.setFont(new Font("Serif", Font.PLAIN, 12));
        sortDropDown.addActionListener(e -> {
            if(sortDropDown.getSelectedItem().equals(SORT_OPTIONS[0])) {
                events.sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));
            }
            if(sortDropDown.getSelectedItem().equals(SORT_OPTIONS[1])) {
                events.sort((a1, a2) -> a1.getDateTime().compareTo(a2.getDateTime()));
            }
            if(sortDropDown.getSelectedItem().equals(SORT_OPTIONS[2])) {
                events.sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()) * -1);
            }
            if(sortDropDown.getSelectedItem().equals(SORT_OPTIONS[3])) {
                events.sort((a1, a2) -> a1.getDateTime().compareTo(a2.getDateTime()) * -1);
            }
            updateDisplay();
        });
        controlPanel.add(sortDropDown);

        //JCheckBoxes
        filters = new ArrayList<>();
        for(String filter : FILTERS) {
            JCheckBox box = new JCheckBox(filter);
            box.setFont(new Font("Arial", Font.BOLD, 30));
            box.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    updateDisplay();
                }
            });
            filters.add(box);
        }
        //actually adding the boxes to the panel
        for(JCheckBox filter : filters) {
            controlPanel.add(filter);
        }

        //addEvent button
        addEventButton.setFont(new Font("Arial", Font.BOLD, 30));
        addEventButton.addActionListener(e -> {
            new AddEventModal(this);
        });
        controlPanel.add(addEventButton);


        //display Panel
        displayPanel.setPreferredSize(new Dimension(700, 650));
        add(displayPanel);
    }

    public void addEvent(Event e) {
        this.events.add(e);
        this.displayPanel.add(new EventPanel(e));
        revalidate();
        repaint();
    }

    public boolean isFiltered(Event e){
        for(JCheckBox filter : filters) {
            if(filter.isSelected()) {
                switch(filter.getText()){
                    case "Completed":
                        if(e instanceof Completable)
                        {
                            if(((Completable)e).isComplete())
                                return true;
                        }
                        break;
                    case "Meetings":
                        if(e instanceof Meeting) {
                            return true;
                        }
                        break;
                    case "Deadlines":
                        if(e instanceof Deadline) {
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    public void updateDisplay() {
        displayPanel.removeAll();
        for(Event e : events) {
            if(!isFiltered(e)) {
                displayPanel.add(new EventPanel(e));
            }
        }
        revalidate();
        repaint();
    }
}
