package demoworld.model;

/**
 * The {@code Experience} class represents a character's experience and how far they are
 * from hitting a level up.
 * It extends the {@link Value} class to manage the experience value,
 * the minimum should always be 0.
 */
public class Experience extends Value {
    /**
     * Constructs a new {@code Experience} object
     *
     * @param max     - the maximum amount for the experience value
     * @param current - the initial current amount for the experience value
     */
    public Experience(int max, int current) {
        super(0, max, current);
    }

    /**
     * Checks if experience bar is full.
     *
     * @return {@code true} if the current experience is equal to the maximum experience,
     * {@code false} otherwise
     */
    public boolean canLevelUp() {
        return this.current() == this.max();
    }

    /**
     * Resets the current experience to the minimum value.
     */
    public void resetToMin() {
        this.setCurrent(this.min());
    }

    /**
     * returns a String representation of the current state of this instance of Experience.
     * @return String representation of the current state of this instance of Experience.
     */
    @Override
    public String toString() {
        String topLine = "XP:"
                + (this.current())
                + "/"
                + this.max();
        String midLine = "[ ]".repeat(Math.max(0, this.max()));
        return topLine + "\n" + midLine;
    }
}
