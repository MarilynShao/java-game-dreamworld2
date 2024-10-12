package demoworld.view;

import demoworld.model.Character;
import demoworld.view.sheetpanels.DicePanel;
import javax.swing.*;
import java.awt.*;

/**
 * View is a JFrame that Implements MainView. It manages all the view related parts of the program.
 */
public class View extends JFrame implements MainView {

    /**
     * The main menu component of the UI.
     */
    private Menu menu;
    /**
     * The search panel component, used for selecting features, specialties, etc.
     */
    private Search search;
    /**
     * The sheet panel that displays character data such as stats, HP, XP, and other details.
     */
    private Sheet sheet;
    /**
     * The menu bar component that contains various menu options like "File" and "Screens".
     */
    private MenuBar menuBar;
    /**
     * Dice panel component for dice rolling interactions.
     */
    private DicePanel dicePanel;

    /**
     * Constructs a new View, initializing the frame and its components such as
     * menu, search, sheet, and menu bar.
     * The default view displays the character sheet.
     *
     * @param title  the title of the window
     * @param width  the width of the window
     * @param height the height of the window
     */
    public View(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new Menu();
        search = new Search();
        sheet = new Sheet();

        setLayout(new BorderLayout());

        menuBar = new MenuBar();
        setJMenuBar(menuBar);

        JPanel sheetPanelWithPadding = new JPanel(new BorderLayout());
        sheetPanelWithPadding.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));
        sheetPanelWithPadding.add(sheet, BorderLayout.CENTER);

        add(menu, BorderLayout.NORTH);
        add(sheetPanelWithPadding, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Updates the character data on the sheet.
     *
     * @param character the character whose data will be displayed and updated on the sheet
     */
    @Override
    public void updateCharacter(Character character) {
        sheet.updateCharacter(character);
    }

    /**
     * Returns the dice panel component.
     *
     * @return the dice panel component for dice rolling interactions
     */
    @Override
    public DicePanel getDicePanel() {
        return sheet.getDicePanel();
    }

    /**
     * Returns the main menu component.
     *
     * @return the menu component of the UI
     */
    @Override
    public Menu getMenu() {
        return menu;
    }

    /**
     * Returns the search panel component.
     *
     * @return the search panel component of the UI
     */
    @Override
    public Search getSearch() {
        return search;
    }

    /**
     * Returns the currently selected item from the search panel.
     *
     * @return the selected search entry
     */
    @Override
    public String getSearchPick() {
        return search.getSelectedEntry();
    }

    /**
     * Returns the sheet panel component.
     *
     * @return the sheet panel for displaying character data
     */
    @Override
    public Sheet getSheet() {
        return sheet;
    }

    /**
     * Returns the top menu bar of the view.
     *
     * @return the menu bar at the top of the window
     */
    @Override
    public JMenuBar getTopMenuBar() {
        return menuBar;
    }

    /**
     * Switches the view to display the search panel.
     */
    @Override
    public void openSearch() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(search, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Switches the view to display the character sheet.
     */
    @Override
    public void openSheet() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel sheetPanelWithPadding = new JPanel(new BorderLayout());
        sheetPanelWithPadding.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));
        sheetPanelWithPadding.add(sheet, BorderLayout.CENTER);

        add(sheetPanelWithPadding, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}

