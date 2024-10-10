package demoworld.view;

import demoworld.model.Character;
import demoworld.view.sheetpanels.DicePanel;
import demoworld.view.Sheet;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements MainView {

    private Menu menu;
    private Search search;
    private Sheet sheet;
    private JMenuBar menuBar;
    private DicePanel dicePanel;

    public View(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        menu = new Menu();
        search = new Search();
        sheet = new Sheet();
        dicePanel = new DicePanel();

        // Set layout and add components
        setLayout(new BorderLayout());

        // Initialize and set the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Add components to the frame
        add(menu, BorderLayout.WEST);
        add(sheet, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void updateCharacter(Character character) {
        sheet.updateCharacter(character);
    }

    @Override
    public DicePanel getDicePanel() {
        return dicePanel;
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    @Override
    public Search getSearch() {
        return search;
    }

    @Override
    public String getSearchPick() {
        return search.getSelectedEntry();
    }

    @Override
    public Sheet getSheet() {
        return sheet;
    }

    @Override
    public JMenuBar getTopMenuBar() {
        return menuBar;
    }

    @Override
    public void openSearch() {
        // Show the search panel
        getContentPane().removeAll();
        add(search, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public void openSheet() {
        // Show the sheet panel
        getContentPane().removeAll();
        add(sheet, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}

