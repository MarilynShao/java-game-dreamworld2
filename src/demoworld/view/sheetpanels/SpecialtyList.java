package demoworld.view.sheetpanels;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;

/**
 * SpecialtyList extends EntryList and implements ReliesOnCharacterData.
 * Responsible for supporting a representation of a Character's Specialties.
 */
public class SpecialtyList extends EntryList implements ReliesOnCharacterData {

    /**
     * Constructs a new SpecialtyList instance with the label "Specialties".
     */
    public SpecialtyList() {
        super("Specialties");
    }

    /**
     * Updates itself based on the new character data and then passes it on to its relevant children.
     *
     * @param character The character to refer to when updating this class' internal state.
     */
    public void updateCharacter(Character character) {
        // Clear the current model
        getListModel().clear();

        // Add all specialties from the character to the list model
        character.getSpecialty().all().forEach(specialty -> getListModel().addElement(specialty.getName()));
    }
}


