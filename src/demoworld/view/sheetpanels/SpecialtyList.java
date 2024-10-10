package demoworld.view.sheetpanels;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.model.Specialty;

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
        getListModel().clear();

        for (Specialty specialty : character.getSpecialty().all()) {
            getListModel().addElement(specialty.getName());
        }
    }
}


