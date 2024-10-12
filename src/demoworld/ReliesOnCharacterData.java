package demoworld;

import demoworld.model.Character;

/**
 * Something implementing the {@code ReliesOnCharacterData} interface indicates
 * it will update itself based on the new character data and pass it on to
 * its relevant children, so they can do the same.
 */
public interface ReliesOnCharacterData {
    /**
     * Updates itself based on the new character data and then passes it on to
     * its relevant children, so they can do the same.
     *
     * @param character {@link Character}
     */
    void updateCharacter(Character character);
}
