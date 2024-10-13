package demoworld.view;

import demoworld.model.Character;
import demoworld.view.sheetpanels.DicePanel;
import javax.swing.*;

/**
 * Interface representing the main view of the application.
 * Provides methods for updating and accessing different panels in the view.
 */
public interface MainView {

    /**
     * Updates the view based on the provided character data.
     *
     * @param character The character whose data will be used to update the view.
     */
    void updateCharacter(Character character);

    /**
     * Retrieves the dice panel for the view.
     *
     * @return The {@code DicePanel} used for dice rolling operations.
     */
    DicePanel getDicePanel();

    /**
     * Retrieves the menu panel for the view.
     *
     * @return The {@code Menu} panel for the application.
     */
    Menu getMenu();

    /**
     * Retrieves the search panel for the view.
     *
     * @return The {@code Search} panel for searching within the application.
     */
    Search getSearch();

    /**
     * Retrieves the currently selected search entry.
     *
     * @return A {@code String} representing the selected search pick.
     */
    String getSearchPick();

    /**
     * Retrieves the sheet panel for the view.
     *
     * @return The {@code Sheet} panel representing the character sheet.
     */
    Sheet getSheet();

    /**
     * Retrieves the top menu bar for the view.
     *
     * @return The {@code JMenuBar} used as the top menu bar in the view.
     */
    MenuBar getTopMenuBar();

    /**
     * Makes the search panel visible.
     */
    void openSearch();

    /**
     * Makes the sheet panel visible.
     */
    void openSheet();
}

