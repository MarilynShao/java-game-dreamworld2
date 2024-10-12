package demoworld.controller;

import demoworld.model.Character;
import demoworld.model.RuleBook;
import demoworld.model.Feature;
import demoworld.model.Specialty;

import demoworld.scholar.Scholar;
import demoworld.scribe.Scribe;
import demoworld.view.MenuBar;
import demoworld.view.Search;
import demoworld.view.View;

import javax.swing.*;
import java.util.List;

/**
 * Controller responsible for managing the MenuBar via the View.
 */
public class MenuBarController extends GameController {

    /**
     * MenuBar
     */
    private final MenuBar menuBar;
    /**
     * Search
     */
    private final Search searchPanel;
    /**
     * Controller
     */
    private final Controller mainController;

    /**
     * Constructor for MenuBarController.
     * @param view - the View the controller will need to manage.
     * @param rules - the RuleBook the controller will use as part of its model.
     * @param character - the Character the controller will use as part of its model.
     * @param mainController - the Controller holding the menu bar.
     */
    public MenuBarController(View view,
                             RuleBook rules,
                             Character character,
                             Controller mainController) {
        super(view, rules, character);
        this.menuBar = (MenuBar) view.getTopMenuBar();
        this.searchPanel = view.getSearch();
        this.mainController = mainController;

        menuBar.addOption("File", "Open", e -> openFile());
        menuBar.addOption("File", "Save", e -> saveFile());
        menuBar.addOption("File", "Exit", e -> exitGame());

        menuBar.addOption("Screens", "Character Sheet", e -> openCharacterSheet());
        menuBar.addOption("Screens", "Add Specialties", e -> addSpecialties());
        menuBar.addOption("Screens", "Add Features", e -> addFeatures());
        menuBar.addOption("Screens", "Remove Specialty", e -> removeSpecialty());
        menuBar.addOption("Screens", "Remove Feature", e -> removeFeature());
    }

    /**
     * Handles opening a file.
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            Character loadedCharacter = new Scholar(getRules()).build(filePath).getCharacter();

            mainController.updateCharacter(loadedCharacter);

            if (getView().getSearch().isVisible()) {
                getView().openSheet();
            }
        }
    }

    /**
     * Handles saving a file.
     */
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            String characterData = getCharacter().toString();
            String studentId = "43442148";

            Scribe.writeToFile(filePath, characterData, studentId);
        }
    }

    /**
     * Handles exiting the game.
     */
    private void exitGame() {
        System.exit(0);
    }

    /**
     * Handles opening the character sheet.
     */
    private void openCharacterSheet() {
        getView().openSheet();
    }

    /**
     * Adds specialties to the character via the search panel.
     */
    private void addSpecialties() {
        searchPanel.clearPickListeners();

        List<String> availableSpecialties = getRules()
                .getSpecialtiesFilteredByRequirements(getCharacter())
                .stream()
                .map(Specialty::getName)
                .toList();

        searchPanel.updateList(availableSpecialties);

        searchPanel.addPickListener(e -> {
            String selectedSpecialty = searchPanel.getSelectedEntry();
            if (selectedSpecialty != null) {
                getCharacter().addSpecialty(getRules().getSpecialtyByName(selectedSpecialty));
                mainController.updateCharacter(getCharacter());
                getView().openSheet();
            }
        });
        getView().openSearch();
    }

    /**
     * Adds features to the character via the search panel.
     */
    private void addFeatures() {
        searchPanel.clearPickListeners();

        List<String> availableFeatures = getRules().getFeaturesReference().stream()
                .map(Feature::getName)
                .toList();

        searchPanel.updateList(availableFeatures);

        searchPanel.addPickListener(e -> {
            String selectedFeature = searchPanel.getSelectedEntry();
            if (selectedFeature != null) {
                getCharacter().addFeature(getRules().getFeatureByName(selectedFeature));
                mainController.updateCharacter(getCharacter());
                getView().openSheet();
            }
        });
        getView().openSearch();
    }

    /**
     * Removes a specialty from the character.
     */
    private void removeSpecialty() {
        searchPanel.clearPickListeners();

        List<String> characterSpecialties = getCharacter().getSpecialty().all().stream()
                .map(Specialty::getName)
                .toList();

        searchPanel.updateList(characterSpecialties);

        searchPanel.addPickListener(e -> {
            String selectedSpecialty = searchPanel.getSelectedEntry();
            if (selectedSpecialty != null) {
                getCharacter()
                        .removeSpecialty(getCharacter().getSpecialty().byName(selectedSpecialty));
                mainController.updateCharacter(getCharacter());
                getView().openSheet();
            }
        });
        getView().openSearch();
    }

    /**
     * Removes a feature from the character.
     */
    private void removeFeature() {
        searchPanel.clearPickListeners();

        List<String> characterFeatures = getCharacter().getFeature().all().stream()
                .map(Feature::getName)
                .toList();

        searchPanel.updateList(characterFeatures);

        searchPanel.addPickListener(e -> {
            String selectedFeature = searchPanel.getSelectedEntry();
            if (selectedFeature != null) {
                getCharacter().removeFeature(getCharacter().getFeature().byName(selectedFeature));
                mainController.updateCharacter(getCharacter());
                getView().openSheet();
            }
        });
        getView().openSearch();
    }

    /**
     * Handles the search panel logic for performing ("Add Specialties", "Add Features", etc.).
     */
    private void handleSearchSelection(String action) {
        searchPanel.clearPickListeners();
        searchPanel.addPickListener(e -> {
            String selectedEntry = searchPanel.getSelectedEntry();
            if (selectedEntry != null) {
                if (action.equals("Add Specialties")) {
                    Character character = getCharacter();
                    character.addSpecialty(getRules().getSpecialtyByName(selectedEntry));
                    mainController.updateCharacter(character);
                } else if (action.equals("Add Features")) {
                    Character character = getCharacter();
                    character.addFeature(getRules().getFeatureByName(selectedEntry));
                    mainController.updateCharacter(character);
                }
            }
        });
        getView().openSearch();
    }
}




