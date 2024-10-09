package demoworld.view.sheetpanels;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.model.Feature;

/**
 * FeatureList that extends EntryList and implements ReliesOnCharacterData.
 * Meant for supporting a representation of a Character's Features.
 */
public class FeatureList extends EntryList implements ReliesOnCharacterData {

    /**
     * Instantiates a new FeatureList.
     */
    public FeatureList() {
        super("Features");
    }

    /**
     * Updates the FeatureList based on the provided Character data.
     *
     * @param character the character whose feature list should be displayed.
     */
    @Override
    public void updateCharacter(Character character) {
        // Clear the existing list
        getListModel().clear();

        // Add all features of the character to the list
        for (Feature feature : character.getFeature().all()) {
            getListModel().addElement(feature.getName());
        }
    }
}

