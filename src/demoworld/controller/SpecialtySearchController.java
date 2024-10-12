package demoworld.controller;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.model.RuleBook;
import demoworld.model.Specialty;
import demoworld.view.Search;
import demoworld.view.View;

import java.util.List;

/**
 * Responsible for managing the Specialty screen and its interactions with the character data model.
 */
public class SpecialtySearchController
        extends GameController
        implements CanSearchCharacter, CanSearchRules, ReliesOnCharacterData {

    /**
     * Search
     */
    private final Search searchPanel;

    /**
     * Constructor for SpecialtySearchController.
     * Instantiates a new SpecialtySearchController.
     *
     * @param view      The View the specialtySearchController is responsible for.
     * @param rules     The rulebook the game is running on.
     * @param character The character state the program is currently manipulating.
     */
    public SpecialtySearchController(View view, RuleBook rules, Character character) {
        super(view, rules, character);
        this.searchPanel = view.getSearch();
    }

    /**
     * Opens the search screen with the current Specialties the Character has.
     */
    @Override
    public void openCharacterSearch() {
        searchPanel.clearPickListeners();
        searchPanel.addPickListener(e -> {
            String selectedSpecialty = searchPanel.getSelectedEntry();
            if (selectedSpecialty != null) {
                Character character = getCharacter();
                Specialty specialty = character.getSpecialty().byName(selectedSpecialty);
                if (specialty != null) {
                    character.removeSpecialty(specialty);
                    updateCharacter(character);
                    System.out.println("Removed specialty: " + selectedSpecialty);
                }
            }
        });

        List<String> characterSpecialties = getCharacter().getSpecialty().all().stream()
                .map(Specialty::getName)
                .toList();
        searchPanel.updateList(characterSpecialties);
        getView().openSearch();
    }

    /**
     * Opens the search screen with the relevant Specialties listed from the RuleBook
     * based on the Character's current state.
     */
    @Override
    public void openRulesSearch() {
        searchPanel.clearPickListeners();
        searchPanel.addPickListener(e -> {
            String selectedSpecialty = searchPanel.getSelectedEntry();
            if (selectedSpecialty != null) {
                Character character = getCharacter();
                Specialty specialty = getRules().getSpecialtyByName(selectedSpecialty);
                if (specialty != null) {
                    character.addSpecialty(specialty);
                    updateCharacter(character);
                    System.out.println("Added specialty: " + selectedSpecialty);
                }
            }
        });
        
        List<String> availableSpecialties = getRules()
                .getSpecialtiesFilteredByRequirements(getCharacter()).stream()
                .map(Specialty::getName)
                .toList();
        searchPanel.updateList(availableSpecialties);
        getView().openSearch();
    }

    /**
     * Updates the instance of Character held by this controller
     * and passes on the updated character to the view,
     * so it can update itself if necessary.
     *
     * @param character the updated Character
     */
    @Override
    public void updateCharacter(Character character) {
        setCharacter(character);
        getView().updateCharacter(character);
    }
}


