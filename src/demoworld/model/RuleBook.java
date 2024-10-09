package demoworld.model;

import demoworld.model.Character;
import java.util.List;

/**
 * Abstract class spelling out what a Rulebook will have at the minimum for methods.
 */
public abstract class RuleBook {

    /**
     * Responsible for managing rolling the dice pool (multiple and various dice) required for the game.
     *
     * @return the rulebook's initial {@code DiceRoller} reference
     */
    public abstract DiceRoller getDiceRoller();

    /**
     * Gets the name of the rulebook.
     *
     * @return name of the rulebook
     */
    public abstract String getName();

    /**
     * Gets the edition of the rulebook.
     *
     * @return edition of the rulebook
     */
    public abstract double getEdition();

    /**
     * Gets a list of every {@link Feature} in the game.
     *
     * @return a List of every feature that exists for this ruleset
     */
    public abstract List<Feature> getFeaturesReference();

    /**
     * Get the rulebook's initial {@link Experience} reference.
     *
     * @return the rulebook's initial {@link Experience} reference
     */
    public abstract Experience getExperienceReference();

    /**
     * Returns the number of feature a character can pick when being created as an {@code int}.
     * @return the number of feature a character can pick when being created as an {@code int}
     */
    public abstract int getFeaturePickCount();

    /**
     * Returns a {@code List} of the spread of {@code int} values used to allocate to a
     * {@code Character}'s initial primary stats.
     * @return a {@code List} of initial primary stats used
     */
    public abstract List<Integer> getStatSpreadReference();

    /**
     * Returns a {@code List} of {@link Specialty}s that the given {@link Character} meets the
     * requirements for.
     * @param character the character state that is being checked against for which specialties
     *                  can be picked
     * @return a {@code List} of {@link Specialty}s that the given {@link Character} meets the
     * requirements for
     */
    public abstract List<Specialty> getSpecialtiesFilteredByRequirements(Character character);

    /**
     * Returns a {@code List} of {@link Feature}s that the given {@link Character}
     * meets the requirements for.
     * @param character the character state that is being checked against for which specialties
     *                  can be picked
     * @return a {@code List} of {@link Feature}s that the given {@link Character}
     * meets the requirements for.
     */
    public abstract List<Feature> getFeaturesFilteredByRequirements(Character character);

    /**
     * creates a {@code List} of the initial {@link Stat}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Stat}s for a starting {@link Character}.
     */
    protected abstract List<Stat> createAndGetStats();

    /**
     * Creates an initial {@link Hitpoints} configured with the specific rulesets defaults.
     * @return {@link Hitpoints}
     */
    protected abstract Hitpoints setCharacterStartingHitpoints();

    /**
     * Creates an initial {@link Experience} configured with the specific rulesets defaults.
     * @return {@link Experience}
     */
    protected abstract Experience setCharacterStartingExperience();

    /**
     * creates a {@code List} of the initial {@link Feature}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Feature}s for a starting {@link Character}.
     */
    protected abstract List<Feature> createAndGetFeatures();

    /**
     * creates a {@code List} of the initial {@link Specialty}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Specialty}s for a starting {@link Character}.
     */
    protected abstract List<Specialty> createAndGetSpecialties();

    /**
     * creates a {@code List} of the initial {@link Requirement}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Requirement}s for a starting {@link Character}.
     */
    protected abstract List<Requirement> createAndGetRequirements();

    /**
     * Returns a reference to the rulebook's {@link EntryManager} for {@code Stat}s.
     * @return {@link EntryManager} for {@code Stat}s
     */
    protected abstract EntryManager<Stat> getStat();

    /**
     * Returns a reference to the rulebook's {@link EntryManager} for {@code Feature}s.
     * @return {@link EntryManager} for {@code Feature}s
     */
    protected abstract EntryManager<Feature> getFeature();

    /**
     * Returns a reference to the rulebook's {@link EntryManager} for {@code Specialty}/~ies.
     * @return {@link EntryManager} for {@code Specialty}/~ies
     */
    protected abstract EntryManager<Specialty> getSpecialty();

    /**
     * Returns a reference to the rulebook's {@link EntryManager} for {@code Requirement}s.
     * @return {@link EntryManager} for {@code Requirement}s
     */
    protected abstract EntryManager<Requirement> getRequirement();

    /**
     * Returns List of Stats
     * @return List of Stats
     */
    protected abstract List<Stat> getStatReference();

    /**
     * Returns List of {@link Specialty}'s
     * @return List of {@link Specialty}
     */
    protected abstract List<Specialty> getSpecialtiesReference();

    /**
     * Get a {@link Specialty} from rulebook specified by its name.
     * @param specialtyName name of the specialty wanting to find
     * @return the {@link Specialty} from the rulebook
     */
    public abstract Specialty getSpecialtyByName(String specialtyName);

    /**
     * Get a {@link Feature} from rulebook specified by its name.
     * [v1.2 changed the parameter in this abstract declaration to "featureName" for consistency - has no effect on implementation]
     * @param featureName name of the feature wanting to find
     * @return the {@link Feature} from the rulebook
     */
    public abstract Feature getFeatureByName(String featureName);
}