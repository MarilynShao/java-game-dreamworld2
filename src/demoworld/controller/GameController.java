package demoworld.controller;

import demoworld.model.Character;
import demoworld.model.RuleBook;
import demoworld.view.View;

/**
 * Parent class for any controllers that require access to the full game state.
 * Holds a reference to the View, RuleBook, and Character and provides methods
 * to access and modify them.
 */
public class GameController {

    /**
     * View
     */
    private final View view;
    /**
     * RuleBook
     */
    private final RuleBook rulebook;
    /**
     * Character
     */
    private Character character;

    /**
     * Constructor for the GameController.
     * Initializes the controller with the given view, rulebook, and character.
     *
     * @param view      The view the controller needs to hold.
     * @param rulebook  The rulebook the controller needs to hold.
     * @param character The character the controller needs to hold.
     */
    public GameController(View view, RuleBook rulebook, Character character) {
        this.view = view;
        this.rulebook = rulebook;
        this.character = character;
    }

    /**
     * Returns the View object being managed by the GameController.
     *
     * @return the View object.
     */
    public View getView() {
        return view;
    }

    /**
     * Returns the RuleBook object being managed by the GameController.
     *
     * @return the RuleBook object.
     */
    public RuleBook getRules() {
        return rulebook;
    }

    /**
     * Returns the Character object being managed by the GameController.
     *
     * @return the Character object.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Updates the character state being managed by the GameController.
     * This method allows updating the character if any changes are needed.
     *
     * @param character The new character object to replace the existing one.
     */
    public void setCharacter(Character character) {
        this.character = character;
    }
}

