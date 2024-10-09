package demoworld.model;

import demoworld.model.Character;
import java.util.List;

/**
 * Something implementing the {@code HasRequirements} interface indicates it can
 * have a list of requirements that may have to be met.
 */
public interface HasRequirements {

    /**
     * Check if the requirements have been met.
     * @param character - The character to be checked
     * @return true or false
     */
    boolean meetsRequirements(Character character);

    /**
     * Add a requirement.
     * @param requirement - the requirement to be added.
     */
    void addRequirement(Requirement requirement);

    /**
     * Get the list of requirements.
     * @return the list of requirements
     */
    List<Requirement> getRequirements();
}