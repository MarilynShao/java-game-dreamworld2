package demoworld.controller.sheetcontrollers;

import demoworld.ReliesOnCharacterData;
import demoworld.controller.GameController;
import demoworld.model.Character;
import demoworld.model.Feature;
import demoworld.model.RuleBook;
import demoworld.model.Specialty;
import demoworld.view.Notification;
import demoworld.view.View;

import javax.swing.*;

/**
 * Controller for the Sheet via the View using the RuleBook and Character as our model.
 */
public class SheetController extends GameController implements ReliesOnCharacterData {

    /**
     * Constructs a new SheetController.
     *
     * @param view The main view responsible for the program.
     * @param rules Rules used by the sheet.
     * @param character Character used by the sheet.
     */
    public SheetController(View view, RuleBook rules, Character character) {
        super(view, rules, character);

        view.getSheet().addDamageListener(e -> damage());
        view.getSheet().addHealListener(e -> heal());
        view.getSheet().addTempHpListener(e -> gainTempHp());

        view.getSheet().addGainXpListener(e -> gainXp());
        view.getSheet().addLoseXpListener(e -> loseXp());

        view.getSheet().addRemoveFeatureListener(e -> removeFeature());
        view.getSheet().addRemoveSpecialtyListener(e -> removeSpecialty());

        view.getSheet().addDisplayFeatureInfoListener(e -> displayFeatureInfo());
        view.getSheet().addDisplaySpecialtyInfoListener(e -> displaySpecialtyInfo());
    }

    /**
     * Handles removing a Feature from the Character triggered by the Sheet
     * and then updates character state for View.
     */
    public void removeFeature() {
        String selectedFeature = getView().getSheet().getFeaturePick();

        if (selectedFeature != null) {
            Feature featureToRemove = getCharacter().getFeature().byName(selectedFeature);
            if (featureToRemove != null) {
                getCharacter().removeFeature(featureToRemove);
                getView().updateCharacter(getCharacter());
            }
        }
    }

    /**
     * Removes the currently selected specialty from the character and updates the character view.
     * If no specialty is selected, or the selected specialty does not exist, the action is ignored.
     */
    private void removeSpecialty() {
        String selectedSpecialty = getView().getSheet().getSpecialtyPick();
        if (selectedSpecialty != null) {
            Specialty specialtyToRemove = getCharacter().getSpecialty().byName(selectedSpecialty);
            if (specialtyToRemove != null) {
                getCharacter().removeSpecialty(specialtyToRemove);
                getView().updateCharacter(getCharacter());
            }
        }
    }

    /**
     * Displays information about the currently selected feature.
     * If no feature is selected, or the selected feature does not exist, no action is taken.
     * Information is shown in a dialog box.
     */
    private void displayFeatureInfo() {
        String selectedFeature = getView().getSheet().getFeaturePick();
        if (selectedFeature != null) {
            Feature feature = getCharacter().getFeature().byName(selectedFeature);
            if (feature != null) {
                JOptionPane.showMessageDialog(null,
                        feature.toString(), "Feature Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Displays information about the currently selected specialty.
     * If no specialty is selected, or the selected specialty does not exist, no action is taken.
     * Information is shown in a dialog box.
     */
    private void displaySpecialtyInfo() {
        String selectedSpecialty = getView().getSheet().getSpecialtyPick();
        if (selectedSpecialty != null) {
            Specialty specialty = getCharacter().getSpecialty().byName(selectedSpecialty);
            if (specialty != null) {
                JOptionPane.showMessageDialog(null,
                        specialty.toString(),
                        "Specialty Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Handles healing the Character by 1 and updating the View.
     */
    public void heal() {
        getCharacter().heal(1);
        getView().updateCharacter(getCharacter());

        new Notification("Heal!", "HP: (" + getCharacter().getHitpoints().getTempHp() + ") "
                + getCharacter().getHitpoints().getBase().current()
                + "/" + getCharacter().getHitpoints().getBase().max());
    }

    /**
     * Handles increasing the Character's temphp by 1 and updating the View.
     */
    public void gainTempHp() {
        getCharacter().getHitpoints().setTempHp(getCharacter().getHitpoints().getTempHp() + 1);
        getView().updateCharacter(getCharacter());

        new Notification("Gain Temp HP!",
                "HP: (" + getCharacter().getHitpoints().getTempHp() + ") "
                + getCharacter().getHitpoints().getBase().current()
                + "/" + getCharacter().getHitpoints().getBase().max());
    }

    /**
     * Handles damaging the Character by 1 and updating the View.
     */
    public void damage() {
        getCharacter().damage(1);
        getView().updateCharacter(getCharacter());

        new Notification("Damage!", "HP: (" + getCharacter().getHitpoints().getTempHp() + ") "
                + getCharacter().getHitpoints().getBase().current() + "/"
                + getCharacter().getHitpoints().getBase().max());
    }


    /**
     * Handles increasing xp for Character by 1 and updating the View.
     */
    public void gainXp() {
        int oldXp = getCharacter().getExperience().current();

        getCharacter().adjustXp(1);
        getView().updateCharacter(getCharacter());

        int newXp = getCharacter().getExperience().current();
        int maxXp = getCharacter().getExperience().max();

        // Check if level-up occurred
        if (oldXp < maxXp && newXp == 0) {
            new Notification("Level Up!", "+1 Max Hp!");
        } else {
            new Notification("XP!", newXp + "/" + maxXp);
        }
    }

    /**
     * Handles decreasing xp for Character by 1 and updating the View.
     */
    public void loseXp() {
        getCharacter().adjustXp(-1);
        getView().updateCharacter(getCharacter());

        int currentXp = getCharacter().getExperience().current();
        int maxXp = getCharacter().getExperience().max();
        new Notification("XP!", currentXp + "/" + maxXp);
    }

    /**
     * Sets View to display the Sheet.
     */
    public void open() {
        getView().openSheet();
    }

    /**
     * Updates View and relevant children with a new Character state.
     *
     * @param character The updated Character state.
     */
    @Override
    public void updateCharacter(Character character) {
        setCharacter(character);
        getView().updateCharacter(character);
    }
}
