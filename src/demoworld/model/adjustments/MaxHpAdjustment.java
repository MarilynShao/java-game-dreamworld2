package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Hitpoints;
import demoworld.model.Character;


/**
 * This is an implementation for adjusting the maximum {@link Hitpoints} of a {@link Character}.
 *
 * @see Adjuster#adjust(Character)
 * @see Adjustment#adjust(Character)
 */
public class MaxHpAdjustment extends Adjustment implements Adjuster {

    /**
     * Constructs a {@code MaxHpAdjustment} with the specified value.
     *
     * @param value the value to adjust the maximum {@link Hitpoints} with
     */
    public MaxHpAdjustment(int value) {
        super();
        this.value = value;
    }

    /**
     * Adjusts the maximum {@link Hitpoints} of the given character.
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted {@link Character}
     */
    @Override
    public Character adjust(Character character) {
        character.getHitpoints().addToMaxBase(this.value);
        return character;
    }

    /**
     * Undoes the adjustment of the maximum {@link Hitpoints} for the given {@link Character}.
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted {@link Character}
     */
    @Override
    public Character unadjust(Character character) {
        character.getHitpoints().addToMaxBase(-this.value);
        return character;
    }

    /**
     * The {@code value} to adjust the maximum {@link Hitpoints} by
     */
    private final int value;
}
