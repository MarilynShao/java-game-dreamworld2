package demoworld.model;


import demoworld.model.Character;
import java.util.ArrayList;
import java.util.List;

/**
 * A game specialty in the game that holds feautres it can bestow on a character and requirements
 * that can be checked against
 * implements the {@link Adjuster} interface to adjust and unadjust a characters state.
 */
public class Specialty implements Adjuster, Named, HasRequirements  {
    /**
     * name
     */
    private final String name;
    /**
     * description
     */
    private final String description;
    /**
     * requirements
     */
    private final EntryManager<Requirement> requirements;
    /**
     * features
     */
    private final EntryManager<Feature> features;

    /**
     * Constructs a {@code specialty} that can hold {@link Feature}s and {@link Requirement}
     * related to that {@code specialty}.
     *
     * @param name        name for the specialty
     * @param description description for the specialty
     */
    public Specialty(String name, String description) {
        this.name = name;
        this.description = description;
        this.features = new EntryManager<>();
        this.requirements = new EntryManager<>();
    }

    /**
     * Gets the name of the specialty.
     *
     * @return name of the specialty
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the text description of the specialty.
     *
     * @return text description of the specialty
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds a {@link Feature} to the features granted by this specialty.
     *
     * @param feature the feature being added to this specialty.
     */
    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    /**
     * Adds a {@link Requirement} to the requirements this specialty uses.
     *
     * @param requirement the requirement being added to this specialty.
     */
    @Override
    public void addRequirement(Requirement requirement) {
        this.requirements.add(requirement);
    }

    /**
     * Returns a new list of references to the {@link Requirement}s stored in this specialty.
     *
     * @return new List of requirements
     */
    @Override
    public List<Requirement> getRequirements() {
        return new ArrayList<>(requirements.all());
    }

    /**
     * Returns a new list of references to the {@link Feature}s stored in this specialty.
     *
     * @return new List of features
     */
    public List<Feature> getFeatures() {
        return new ArrayList<>(features.all());
    }

    /**
     * Returns true if the given character meets the requirements for this specialty.
     * @param character the character being checked for requirements
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
     * Adds this specialty's features to the {@link Character}.
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted character
     */
    @Override
    public Character adjust(Character character) {
        for (Feature feature : this.features.all()) {
            character.addFeature(feature);
        }
        return character;
    }

    /**
     * Removes this specialty's features from the {@link Character}.
     *
     * @param character the {@link Character} to unadjust
     * @return the unadjusted character
     */
    @Override
    public Character unadjust(Character character) {
        for (Feature feature : features.all()) {
            if(character.getFeature().all().contains(feature)) {
                character.removeFeature(feature);
            }
        }
        return character;
    }

    /**
     * Returns a string representation of the specialty.
     *
     * @return a string representation of the specialty.
     */
    @Override
    public String toString() {
        return this.getName().toUpperCase() + "\n" + this.getDescription();
    }
}
