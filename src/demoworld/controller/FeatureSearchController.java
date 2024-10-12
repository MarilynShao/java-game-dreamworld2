package demoworld.controller;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.model.Feature;
import demoworld.model.RuleBook;
import demoworld.view.View;

import java.util.List;

/**
 * Controller responsible for handling the feature search functionality.
 * It manages both adding and removing features to/from the character.
 */
public class FeatureSearchController extends GameController
        implements CanSearchCharacter, CanSearchRules, ReliesOnCharacterData {

    /**
     * Constructs a new FeatureSearchController.
     *
     * @param view      the main view of the application
     * @param rules     the game rulebook to refer to when interacting with features
     * @param character the character that is being modified in the feature search process
     */
    public FeatureSearchController(View view, RuleBook rules, Character character) {
        super(view, rules, character);
    }

    /**
     * Opens the search screen with the available features from the rulebook.
     * The user can select a feature, and the selected feature will be added to the character.
     */
    @Override
    public void openRulesSearch() {
        List<String> availableFeatures = getRules().getFeaturesReference().stream()
                .map(Feature::getName)
                .toList();

        getView().getSearch().updateList(availableFeatures);

        getView().getSearch().addPickListener(e -> {
            String selectedFeatureName = getView().getSearchPick();
            if (selectedFeatureName != null) {
                Feature selectedFeature = getRules().getFeatureByName(selectedFeatureName);
                getCharacter().addFeature(selectedFeature);
                updateCharacter(getCharacter());
                getView().openSheet();
            }
        });

        getView().openSearch();
    }

    /**
     * Opens the search screen with the features currently assigned to the character.
     * The user can select a feature to remove from the character.
     */
    @Override
    public void openCharacterSearch() {
        List<String> characterFeatures = getCharacter().getFeature().all().stream()
                .map(Feature::getName)
                .toList();

        getView().getSearch().updateList(characterFeatures);

        getView().getSearch().addPickListener(e -> {
            String selectedFeatureName = getView().getSearchPick();
            if (selectedFeatureName != null) {
                Feature selectedFeature = getCharacter().getFeature().byName(selectedFeatureName);
                getCharacter().removeFeature(selectedFeature);
                updateCharacter(getCharacter());
                getView().openSheet();
            }
        });

        getView().openSearch();
    }

    /**
     * Updates the character within the controller and view after any feature-related changes.
     *
     * @param character the updated character after adding/removing features
     */
    @Override
    public void updateCharacter(Character character) {
        setCharacter(character);
        getView().updateCharacter(character);
    }
}

