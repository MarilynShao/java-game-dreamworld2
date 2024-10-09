package demoworld.model;

import demoworld.model.Character;
import demoworld.model.adjustments.Adjustment;
import java.util.ArrayList;
import java.util.List;

/**
 * A game feature in the game that can include automated adjustments and {@link Requirement} that
 * can be checked against
 * implements the {@link Adjuster} interface to adjust and unadjust a characters state.
 */
public class Feature implements Adjuster, Named, HasRequirements {

    /**
     * name
     */
    private final String name;

    /**
     * description
     */
    private final String description;

    /**
     * adjustments
     */
    private final List<Adjustment> adjustments;

    /**
     * requirements
     */
    private final EntryManager<Requirement> requirements;

    /**
     * Constructs a {@code feature} that can hold {@link Adjustment}s and {@link Requirement}
     * related to that {@code feature}.
     *
     * @param name        the name of the feature
     * @param description the description of the feature
     */
    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
        this.adjustments = new ArrayList<>();
        this.requirements = new EntryManager<>();
    }

    /**
     * Gets the name of the feature.
     *
     * @return name of the feature
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the text description of the feature.
     *
     * @return text description of the feature
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds an {@link Adjustment} to the adjustments this feature uses.
     *
     * @param adjustment - the adjustment
     */
    public void addAdjustment(Adjustment adjustment) {
        this.adjustments.add(adjustment);
    }

    /**
     * Adds an {@link Requirement} to the requirements this feature uses;
     *
     * @param requirement - the requirement to be added to the feature
     */
    @Override
    public void addRequirement(Requirement requirement) {
        this.requirements.add(requirement);
    }


    /**
     * Returns if the given {@link Character} meets the requirements for this feature.
     * @param character - {@link Character} whos state will be checked to determine if they meet the
     *                 {@link Requirement} for this feature.
     * @return True if they do, False otherwise.
     */
    @Override
    public boolean meetsRequirements(Character character) {
        boolean meetsRequirements = true;
        for (Requirement requirement : this.requirements.all()) {
            if (!requirement.check(character)) {
                meetsRequirements = false;
            }
        }
        return meetsRequirements;
    }

    /**
     * Returns a new list of references to the {@link Requirement}s stored in this feature.
     *
     * @return new List of {@link Requirement}
     */
    @Override
    public List<Requirement> getRequirements() {
        return new ArrayList<>(requirements.all());
    }

    /**
     * Applies all adjusts required by the feature
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted character
     */
    @Override
    public Character adjust(Character character) {
        for (Adjustment adjustment : adjustments) {
            adjustment.adjust(character);
        }
        return character;
    }

    /**
     * Removes all the adjustments that were applied for this feature.
     *
     * @param character the {@link Character} to unadjust
     * @return the unadjusted character
     */
    @Override
    public Character unadjust(Character character) {
        for (Adjustment adjustment : adjustments) {
            adjustment.unadjust(character);
        }
        return character;
    }

    /**
     * Returns a string representation of the Feature
     *
     * @return a string representation of the Feature
     */
    @Override
    public String toString() {
        String title = this.getName().toUpperCase();
        String adjustmentsTracker = "[" + "*".repeat(this.adjustments.size()) + "]";
        StringBuilder requirements = new StringBuilder();

        for (Requirement requirement : this.requirements.all()) {
            requirements.append("requires: ").append(
                    requirement.toString()
            ).append("\n");
        }

        return title
               + " "
               + adjustmentsTracker
               + "\n"
               + requirements
               + '"'
               + this.getDescription()
               + '"'
               + "\n";
    }
}
