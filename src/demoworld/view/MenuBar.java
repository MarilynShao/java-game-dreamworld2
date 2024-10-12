package demoworld.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Represents the main menu bar for the application.
 * The {@code MenuBar} class extends {@link JMenuBar}
 * and allows dynamic creation of menus and menu items
 * based on the provided structure and user interactions.
 */
public class MenuBar extends JMenuBar {

    /** A map to store menu names and their
     * corresponding {@link JMenu} objects.
     */
    private final HashMap<String, JMenu> menus;

    /**
     * Constructs a new {@code MenuBar} instance and initializes the default menus.
     * The default parent menus "File" and "Screens" are added upon creation.
     */
    public MenuBar() {
        menus = new HashMap<>();

        addParentMenu("File");
        addParentMenu("Screens");
    }

    /**
     * Adds a new parent menu to the menu bar with the given name.
     * This method creates a {@link JMenu} and associates it with the given name in the menu map.
     *
     * @param name the name of the parent menu to be added
     */
    public void addParentMenu(String name) {
        JMenu menu = new JMenu(name);
        menus.put(name, menu);
        add(menu);
    }

    /**
     * Adds a new menu item (option) under the specified parent menu.
     * The menu item will trigger the provided {@link ActionListener} when selected.
     *
     * @param menu     the name of the parent menu under which the option should be added
     * @param label    the label for the menu item (option)
     * @param listener the action listener to be triggered when the menu item is selected
     */
    public void addOption(String menu, String label, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(listener);
        menus.get(menu).add(menuItem);
    }
}

