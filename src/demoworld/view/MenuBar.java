package demoworld.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * MenuBar that handles file options and screens.
 * Implements a JMenuBar with options for file and screen-related actions.
 */
public class MenuBar extends JMenuBar {

    private final HashMap<String, JMenu> menus;

    /**
     * Constructor for MenuBar.
     * Initializes the menus and their options.
     */
    public MenuBar() {
        menus = new HashMap<>();

        // Create the "File" menu and add options
        addParentMenu("File");
        addOption("File", "Open", e -> System.out.println("Open clicked"));
        addOption("File", "Save", e -> System.out.println("Save clicked"));
        addOption("File", "Exit", e -> System.exit(0));

        // Create the "Screens" menu and add options
        addParentMenu("Screens");
        addOption("Screens", "Character Sheet", e -> System.out.println("Character Sheet clicked"));
        addOption("Screens", "Add Specialties", e -> System.out.println("Add Specialties clicked"));
        addOption("Screens", "Add Features", e -> System.out.println("Add Features clicked"));
        addOption("Screens", "Remove Specialty", e -> System.out.println("Remove Specialty clicked"));
        addOption("Screens", "Remove Feature", e -> System.out.println("Remove Feature clicked"));
    }


    /**
     * Adds an option to a parent menu.
     *
     * @param menu The parent menu name.
     * @param label The label of the menu option.
     * @param listener The action listener for the option.
     */
    public void addOption(String menu, String label, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(listener);
        menus.get(menu).add(menuItem);
    }

    /**
     * Adds a new parent menu to the menu bar.
     *
     * @param name The name of the parent menu.
     */
    public void addParentMenu(String name) {
        JMenu menu = new JMenu(name);
        menus.put(name, menu);
        add(menu);
    }
}

