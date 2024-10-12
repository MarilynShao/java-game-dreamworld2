package demoworld.controller.sheetcontrollers;

import demoworld.ReliesOnCharacterData;
import demoworld.controller.GameController;
import demoworld.model.Character;
import demoworld.model.RuleBook;
import demoworld.view.Notification;
import demoworld.view.View;
import demoworld.view.sheetpanels.DicePanel;

/**
 * DiceController is responsible for controlling the DicePanel via View and relevant
 * data for the model (RuleBook and Character). It extends the GameController class
 * and implements the ReliesOnCharacterData interface.
 */
public class DiceController extends GameController implements ReliesOnCharacterData {

    /**
     * Dice panel
     */
    private final DicePanel dicePanel;

    /**
     * Constructor for DiceController.
     *
     * @param view The View interface for interacting with the GUI elements.
     * @param rules The RuleBook that contains the game rules for dice rolls.
     * @param character The character object for interacting with character-specific data.
     * @param mainController The main controller that manages interactions between
     *                       the different panels and controllers.
     */
    public DiceController(View view,
                          RuleBook rules, Character character, SheetController mainController) {
        super(view, rules, character);
        this.dicePanel = view.getDicePanel();

        dicePanel.addRollListener(e -> roll());
    }

    /**
     * Rolls the DiceRoller from RuleBook and updates the DicePanel with the result.
     * Uses the rules and character to apply dice roll logic.
     */
    public void roll() {
        boolean advantage = dicePanel.hasAdvantage();
        boolean rerollOnes = dicePanel.hasRerollOnes();
        int result;

        if (advantage && rerollOnes) {
            result = getRules().getDiceRoller().roll(1, true); // Reroll 1's with advantage
        } else if (advantage) {
            result = getRules().getDiceRoller().roll(true); // Roll with advantage
        } else if (rerollOnes) {
            result = getRules().getDiceRoller().roll(1); // Reroll 1's without advantage
        } else {
            result = getRules().getDiceRoller().roll();
        }

        dicePanel.updateResult(result);

        new Notification("Rolled 2D6", "Got: " + result);
    }


    /**
     * Updates the dice panel with a binding to the new version of character.
     *
     * @param character The new version of the character.
     */
    @Override
    public void updateCharacter(Character character) {
        setCharacter(character);
    }
}

