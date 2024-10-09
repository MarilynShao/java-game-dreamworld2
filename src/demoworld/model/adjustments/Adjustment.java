package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Character;

/**
 * {@code Adjustment} provides a base implementation for adjusting
 * and unadjusting a character's state.
 * Implements the {@link Adjuster} interface.
 */
public abstract class Adjustment implements Adjuster {

    /**
     * Adjusts the given character's state.
     * Implementations will define how the character is adjusted.
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted {@link Character}
     */
    @Override
    public abstract Character adjust(Character character);

    /**
     * Undoes the adjustments made to the given character's state
     * (i.e. if 3 was added to something before, 3 would be subtracted now).
     * Implementations will define how the character is unadjusted.
     *
     * @param character the {@link Character} to unadjust
     * @return the unadjusted {@link Character}
     */
    @Override
    public abstract Character unadjust(Character character);
}