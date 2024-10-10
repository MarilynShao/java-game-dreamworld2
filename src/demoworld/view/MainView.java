package demoworld.view;

import demoworld.model.Character;
import demoworld.view.sheetpanels.DicePanel;
import demoworld.view.Sheet;
import javax.swing.*;

public interface MainView {

    void updateCharacter(Character character);

    // Method to get the DicePanel
    DicePanel getDicePanel();

    // Method to get the Menu panel
    Menu getMenu();

    // Method to get the Search panel
    Search getSearch();

    // Method to return the current selected search pick
    String getSearchPick();

    // Method to return the sheet panel
    Sheet getSheet();

    // Method to return the top menu bar
    JMenuBar getTopMenuBar();

    // Method to set the search panel as visible
    void openSearch();

    // Method to set the sheet panel as visible
    void openSheet();
}

