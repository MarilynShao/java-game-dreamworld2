package demoworld.model;

/**
 * Represents a game {@code Value} that has a mininum amount, a maximum amount, and a current amount.
 * If the current amount would ever go past the maximum, it should be set back to the maximum value.
 * If the current amount would ever go below the mininum, it should be set back to the mininum value.
 */
public class Value {
    /**
     *  min
     */
    private int min;
    /**
     *  max
     */
    private int max;
    /**
     *  current
     */
    private int current;

    /**
     * Constructs a new {@code Value} with values given for the initial boundaries and
     * its initial current state.
     * Boundaries are enforced every time and amount is set or changed.
     * If the maximum is below the minimum, it should be set to match minimum.
     * If the current is below the minimum, it should be set to match minimum.
     * If the current is above new maximum, it should be set to match maximum.
     *
     * @param min     the initial minimum amount
     * @param max     the initial maximum amount
     * @param current the initial current amount
     */
    public Value(int min, int max, int current) {
        this.min = min;
        this.max = max;
        this.current = current;
        this.enforceBounds();
    }

    /**
     * Gets the {@code Value}'s minimum amount.
     *
     * @return the minimum amount for this {@code Value}
     */
    public int min() {
        return min;
    }

    /**
     * Gets the {@code Value}'s maximum amount.
     *
     * @return the maximum amount for this {@code Value}
     */
    public int max() {
        return max;
    }

    /**
     * Gets the {@code Value}'s current amount.
     *
     * @return the current amount for this {@code Value}
     */
    public int current() {
        return current;
    }

    /**
     * Set the {@code Value}'s minimum amount to a new minimum.
     * Then enforces the boundaries on any amounts held by the value.
     * If the maximum is below the new minimum, it should be set to match that minimum.
     * If the current is below the new minimum, it should be set to match that minimum.
     *
     * @param min the value the minimum amount is being set to
     */
    public void setMin(int min) {
        this.min = min;
        this.enforceBounds();
    }

    /**
     * Set the {@code Value}'s maximum amount to a new maximum.
     * Then enforces the boundaries on any amounts held by the value.
     * If the new maximum is below the minimum, it should be set to match that minimum.
     * If the current is above the new maximum, it should be set to match that maximum.
     *
     * @param max the value the maximum amount is being set to
     */
    public void setMax(int max) {
        this.max = max;
        this.enforceBounds();
    }

    /**
     * Set the {@code Value}'s current amount to a new current.
     * Then ensures that the new current will fit into the boundaries given.
     * If the new current is below the minimum, it should be set to match that minimum.
     * If the new current is above the maximum, it should be set to match that maximum.
     *
     * @param value the value the current amount is being set to
     */
    public void setCurrent(int value) {
        this.current = value;
        this.enforceBounds();
    }

    /**
     * Add an amount to the {@code Value}'s current amount.
     * Then ensures that the new current will fit into the boundaries given.
     * If the new current is below the minimum, it should be set to match that minimum.
     * If the new current is above the maximum, it should be set to match that maximum.
     *
     * @param value the value being added to the current amount
     */
    public void addToCurrent(int value) {
        this.current += value;
        this.enforceBounds();
    }

    /**
     * Add an amount to the {@code Value}'s maximum amount.
     * Then ensures that the new max will fit into the boundaries given.
     * If the new maximum is below the minimum, it should be set to match that minimum.
     * If the current is above the new maximum, it should be set to match that maximum.
     *
     * @param value amount being added to the values max amount
     */
    public void addToMax(int value) {
        this.max += value;
        this.enforceBounds();
    }

    private void enforceBounds() {
        if (this.max < this.min) {
            this.max = this.min;
        }
        if (this.current > this.max) {
            this.current = this.max;
        }
        if (this.current < this.min) {
            this.current = this.min;
        }
    }
}
