package demoworld.model;

/**
 * Something implementing adjuster indicates it will adjust and unadjust a character.
 */
public interface Adjuster {
    /**
     * Adjusts the given character.
     * Implementations will define how the character is adjusted.
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted {@link Character}
     */
    Character adjust(Character character);

    /**
     * Undoes the adjustments made to the given character 
     * (e.g. if 1 was added to something before,1 would be subtracted now).
     * Implementations will define how the character is unadjusted.
     *
     * @param character the {@link Character} to unadjust
     * @return the unadjusted {@link Character}
     */
    Character unadjust(Character character);
}
