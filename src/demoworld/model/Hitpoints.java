package demoworld.model;

/**
 * {@code Hitpoints} represents the hitpoints of a {@link java.lang.Character}.
 * It extends the {@link Stat} class and provides methods to manage the character's hitpoints.
 */
public class Hitpoints extends Stat {
    /**
     * Constructs a new Hitpoints instance
     *
     * @param max     initial maximum amount for both the base Value and the modifier Value in hitpoints (the initial minimums are always zero for hitpoints)
     * @param current initial current amount for the base Value in hitpoints
     */
    public Hitpoints(int max, int current) {
        super("hitpoints", "Represents how much damage this Character can take",
                0, max, current);
    }

    /**
     * Checks if the hitpoints state would indicate the character is downed.
     *
     * @return {@code true} if the current hit points are equal to the minimum hit points,
     * {@code false} otherwise
     */
    public boolean isDead() {
        return this.getTotal() == this.getTotalMin();
    }

    /**
     * Applies damage to the character's hit points.
     * Damage is taken from temporary (modified) hitpoints first, then from base hitpoints.
     *
     * @param value the amount of damage to apply
     * @return the new total hitpoints
     */
    public int damage(int value) {
        if (this.currentModifier() >= value) {
            this.addToCurrentModifier(-value);
        } else {
            int remainingDamage = value - this.currentModifier();
            this.addToCurrentModifier(-this.currentModifier());
            this.addToCurrentBase(-remainingDamage);
        }
        return this.getTotal();
    }

    /**
     * Heals the characters current hitpoints (temporary extra hitpoints can not be healed)
     *
     * @param value the amount of healing to apply
     * @return the new total hitpoints
     */
    public int heal(int value) {
        this.addToCurrentBase(value);
        return this.getTotal();
    }

    /**
     * Sets the temporary hitpoints for the {@link java.lang.Character}.
     * This can never be set to a negative number, if its given a negative number turns it to 0.
     *
     * @param value the temporary hitpoints to set
     */
    public void setTempHp(int value) {
        if (value < 0) {
            value = 0;
        }
        this.getModifier().setCurrent(value);
    }

    /**
     * Gets the current temporary hitpoints.
     *
     * @return the current temporary hitpoints
     */
    public int getTempHp() {
        return this.getModifier().current();
    }

    /**
     * Resets the temporary hit points to zero.
     */
    public void resetTempHp() {
        this.getModifier().setCurrent(0);
    }

    /**
     * returns a String representation of the current state of this instance of Hitpoints.
     * @return String representation of the current state of this instance of Hitpoints.
     */
    @Override
    public String toString() {
        String topLine = "HITPOINTS:"
                + (this.getBase().current() + this.getTempHp())
                + "/"
                + this.getBase().max();
        String midLine = "[ ]".repeat(Math.max(0, this.getBase().max()));
        String bottomLine = "TEMP HP: " + this.getTempHp();
        return topLine + "\n" + midLine + "\n" + bottomLine;
    }
}
