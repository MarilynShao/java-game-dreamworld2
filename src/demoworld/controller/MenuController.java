package demoworld.controller;

import demoworld.model.RuleBook;
import demoworld.view.View;

/**
 * Controls the Menu Splash Screen and directs interactions from the menu.
 */
public class MenuController {

    /**
     * View
     */
    private final View view;
    /**
     * RuleBook
     */
    private final RuleBook rules;
    /**
     * Controller
     */
    private final Controller mainController;

    /**
     * Constructs the MenuController.
     *
     * @param view           The main view used by the program.
     * @param rules          The rulebook the controller will use as part of its model.
     * @param mainController The main controller holding the menu bar and managing overall behavior.
     */
    public MenuController(View view, RuleBook rules, Controller mainController) {
        this.view = view;
        this.rules = rules;
        this.mainController = mainController;
    }

    /**
     * Opens the feature search screen.
     */
    private void openFeatureSearch() {
        mainController.openRulesFeatureSearch();
    }

    /**
     * Opens the specialty search screen.
     */
    private void openSpecialtySearch() {
        mainController.openSpecialtySearch();
    }

    /**
     * Opens the character sheet view.
     */
    private void openCharacterSheet() {
        mainController.openSheet();
    }

    /**
     * Exits the program.
     */
    private void exitProgram() {
        System.exit(0);
    }
}






