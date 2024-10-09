package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Stat;
import demoworld.model.Character;


/**
 * This is an implementation for adjusting a target {@link Stat} of a {@link Character}.
 *
 * @see Adjuster#adjust(Character)
 * @see Adjustment#adjust(Character)
 * @see Adjuster#unadjust(Character)
 * @see Adjustment#unadjust(Character)
 */
public class StatAdjustment extends Adjustment implements Adjuster {
    /**
     * Constructs a Stat Adjustment with a string indicating the Stat it is intended to adjust
     * and a value indicating by how much.
     *
     * @param stat  - the key used to indicate which {@link Stat} it should be used on
     * @param value - the value indicating how much the targeted {@link Stat} should be adjusted by
     */
    public StatAdjustment(String stat, int value) {
        super();
        this.statKey = stat;
        this.value = value;
    }

    /**
     * Gets the statKey assigned to this StatAdjustment
     * @return  the statKey assigned to this StatAdjustment
     */
    public String getStatKey() {
        return this.statKey;
    }

    /**
     * Gets the value assigned to the statAdjustment
     * @return the value assigned to the statAdjustment
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Adjusts the target {@link Stat}'s current modifier using the given value
     *
     * @param character the {@link Character} to adjust
     * @return the adjusted {@link Character}
     */
    @Override
    public Character adjust(Character character) {
        Stat statToChange = character.getStatByName(this.statKey);
        statToChange.addToCurrentModifier(this.value);
        return character;
    }

    /**
     * Unadjusts the target {@link Stat}'s current modifier using the given value,
     * undoing the state change done by adjust.
     *
     * @param character the {@link Character} to unadjust
     * @return the unadjusted {@link Character}
     */
    @Override
    public Character unadjust(Character character) {
        Stat statToChange = character.getStatByName(this.statKey);
        statToChange.addToCurrentModifier(-this.value);
        return character;
    }

    /**
     * The {@code key} used to find the relevant Stat from the {@link demoworld.model.EntryManager}
     */
    private final String statKey;

    /**
     * The {@code value} used to adjust the specific {@link Stat}
     */
    private final int value;
}

