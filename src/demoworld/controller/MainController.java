package demoworld.controller;

import demoworld.model.Character;

/**
 * Interface that indicates anything that implements it supports all the
 * functions of a main controller.
 */
public interface MainController {

    /**
     * Updates the main view and all relevant children with the new Character data.
     *
     * @param character The new character state.
     */
    void updateCharacter(Character character);

    /**
     * Instructs the view to open the Character sheet page.
     */
    void openSheet();

    /**
     * Instructs the view to open the search screen
     * configured for searching the RuleBook's Features
     * that the current Character meets the requirements for.
     */
    void openRulesFeatureSearch();

    /**
     * Instructs the view to open the search screen configured for
     * searching the Character's Features and configures the pick
     * button to be set to fire a removal event.
     */
    void openCharacterFeatureRemoval();

    /**
     * Instructs the view to open the search screen configured
     * for searching the Character's Specialties and configures
     * the pick button to be set to fire a removal event.
     */
    void openCharacterSpecialtyRemoval();

    /**
     * Instructs the view to open the search screen
     * configured for searching the RuleBook's Specialties
     * that the current Character meets the requirements for.
     */
    void openSpecialtySearch();

}

