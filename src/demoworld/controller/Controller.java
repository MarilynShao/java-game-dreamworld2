package demoworld.controller;

import demoworld.ReliesOnCharacterData;
import demoworld.controller.sheetcontrollers.DiceController;
import demoworld.controller.sheetcontrollers.SheetController;
import demoworld.model.Character;
import demoworld.model.RuleBook;
import demoworld.view.View;

/**
 * The main controller, responsible for managing all
 * other controllers and intercontroller communication.
 */
public class Controller extends GameController implements MainController, ReliesOnCharacterData {

    /**
     * Manages interactions related to searching and adding/removing features.
     */
    private FeatureSearchController featureSearchController;
    /**
     * Manages interactions related to searching and adding/removing specialties.
     */
    private SpecialtySearchController specialtySearchController;
    /**
     * Manages the interactions for the character sheet,
     * including updating and displaying character data.
     */
    private SheetController sheetController;
    /**
     * Manages the interactions related to the menu bar,
     * such as handling menu actions (e.g., saving, loading).
     */
    private MenuBarController menuBarController;
    /**
     * Manages the dice-rolling functionality,
     * including controlling the dice panel and any dice-based actions.
     */
    private DiceController diceController;

    /**
     * Constructor for Controller.
     *
     * @param view      the view the controller needs to manage.
     * @param rulebook  the rulebook the controller needs to manage.
     * @param character the character the controller needs to manage.
     */
    public Controller(View view, RuleBook rulebook, Character character) {
        super(view, rulebook, character);

        featureSearchController = new FeatureSearchController(view, rulebook, character);
        specialtySearchController = new SpecialtySearchController(view, rulebook, character);
        sheetController = new SheetController(view, rulebook, character);
        menuBarController = new MenuBarController(view, rulebook, character, this);
        diceController = new DiceController(view, rulebook, character, sheetController);
        System.out.println("DiceController Initialized: " + (diceController != null));
    }

    /**
     * Updates self and all relevant controllers with a new Character, then updates the view.
     *
     * @param character the updated character
     */
    @Override
    public void updateCharacter(Character character) {
        setCharacter(character);

        featureSearchController.updateCharacter(character);
        specialtySearchController.updateCharacter(character);
        sheetController.updateCharacter(character);
        diceController.updateCharacter(character);

        getView().updateCharacter(character);
    }

    /**
     * Orders the sheetController to open the sheet screen.
     */
    @Override
    public void openSheet() {
        getView().openSheet();
    }

    /**
     * Orders the featureSearchController to open the rules feature search screen.
     */
    @Override
    public void openRulesFeatureSearch() {
        featureSearchController.openRulesSearch();
    }

    /**
     * Orders the featureSearchController to open the character feature search screen.
     */
    @Override
    public void openCharacterFeatureRemoval() {
        featureSearchController.openCharacterSearch();
    }

    /**
     * Orders the specialtySearchController to open the rules specialty search screen.
     */
    @Override
    public void openCharacterSpecialtyRemoval() {
        specialtySearchController.openRulesSearch();
    }

    /**
     * Orders the specialtySearchController to open the character specialty search screen.
     */
    @Override
    public void openSpecialtySearch() {
        specialtySearchController.openCharacterSearch();
    }
}
