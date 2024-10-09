package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Experience;

import demoworld.model.Character;


/**
 * This is an implementation for adjusting the maximum {@link Experience} of a {@link Character}.
 *
 * @see Adjuster#adjust(Character)
 * @see Adjustment#adjust(Character)
 */
public class MaxXpAdjustment extends Adjustment implements Adjuster {

    /**
     * This is the constructor for the {@code MaxXpAdjustment} it takes a value indicating how
     * much it should alter the max of {@link Experience}
     *
     * @param value - indicates how much to alter the maximum experience required
     *              to achieve a level up
     */
    public MaxXpAdjustment(int value) {
        super();
        this.value = value;
    }

    /**
     * Adjusts the maximum {@link Experience} of the given character.
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted {@link Character}
     */
    @Override
    public Character adjust(Character character) {
        character.getExperience().addToMax(this.value);
        return character;
    }

    /**
     * Undoes the adjustment of the maximum {@link Experience} for the given character.
     *
     * @param character the {@link Character} to unadjust
     * @return the unadjusted {@link Character}
     */
    @Override
    public Character unadjust(Character character) {
        character.getExperience().addToMax(-this.value);
        return character;
    }

    /**
     * The {@code value} used for how much to adjust the maximum {@link Experience} by
     */
    private final int value;
}
