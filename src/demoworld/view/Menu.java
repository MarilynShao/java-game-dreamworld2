package demoworld.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Menu class representing a custom panel that acts as the main menu.
 */
public class Menu extends JPanel {

    /**
     * Constructor for the Menu class.
     * Initializes a basic layout with buttons for options.
     */
    public Menu() {
        setLayout(new GridLayout(2, 1));

        addOption("Start", e -> System.out.println("Start clicked"));
        addOption("Exit", e -> System.exit(0));
    }

    /**
     * Adds a new option button to the menu screen.
     *
     * @param label The label of the button.
     * @param listener The action listener for the button.
     */
    public void addOption(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }
}

