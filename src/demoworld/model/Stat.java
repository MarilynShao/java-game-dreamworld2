package demoworld.model;

/**
 * Representation of some {@code Named} statistic ({@code Stat}) of a {@code Character},
 * an object constructed from multiple {@link Value}s with a name and a description.
 */
public class Stat implements Named {
    /**
     * name
     */
    private final String name;
    /**
     * description
     */
    private final String description;
    /**
     * base
     */
    private final Value base;
    /**
     * modifier
     */
    private final Value modifier;

    /**
     * Constructs a {@code Stat}.
     *
     * @param name        the name of the new {@code Stat}
     * @param description the description of the new {@code Stat}
     * @param min         initial minimum of both the base {@link Value} and the modifier {@link Value} in the new {@code Stat}
     * @param max         initial maximum of both the base {@link Value} and the modifier {@link Value} in the new {@code Stat}
     * @param current     initial current of the base {@link Value} in the new {@code Stat} (the current of the modifier {@link Value} is always initialised to zero, i.e. not modified)
     */
    public Stat(String name, String description, int min, int max, int current) {
        this.name = name;
        this.description = description;
        this.base = new Value(min, max, current);
        this.modifier = new Value(min, max, 0);
    }

    /**
     * Gets the name of the {@code Stat}.
     *
     * @return name the name of the stat
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the {@code Stat}.
     *
     * @return description the description of the stat
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the base {@link Value}.
     *
     * @return the base {@link Value}
     */
    public Value getBase() {
        return this.base;
    }

    /**
     * Gets the modifier {@link Value}.
     *
     * @return the modifier {@link Value}
     */
    public Value getModifier() {
        return this.modifier;
    }

    /**
     * Gets the current amount for the modifier {@link Value}.
     *
     * @return current amount for the modifier {@link Value}
     */
    public int currentModifier() {
        return this.modifier.current();
    }

    /**
     * Sets the current amount for the modifier {@link Value}.
     *
     * @param value the amount to set the current amount of modifier {@link Value} to
     */
    public void setCurrentModifier(int value) {
        this.modifier.setCurrent(value);
    }

    /**
     * Adds a value to the current amount for the modifier {@link Value}.
     *
     * @param value to the current amount for the modifier {@link Value}
     */
    public void addToCurrentModifier(int value) {
        this.modifier.addToCurrent(value);
    }

    /**
     * Sets the current amount for the base {@link Value}.
     *
     * @param value the current amount for the base {@link Value}
     */
    public void setCurrentBase(int value) {
        this.base.setCurrent(value);
    }

    /**
     * Adds a value to the current amount for the base {@link Value}.
     *
     * @param value the current amount for the base {@link Value}
     */
    public void addToCurrentBase(int value) {
        this.base.addToCurrent(value);
    }

    /**
     * Adds a value to the maximum amount for the base {@link Value}.
     *
     * @param value the maximum amount for the base {@link Value}
     */
    public void addToMaxBase(int value) {
        this.base.setMax(this.base.max() + value);
    }

    /**
     * Sets the maximum amount for the base {@link Value}.
     *
     * @param value the maximum amount for the base {@link Value}
     */
    public void setMaxBase(int value) {
        this.base.setMax(value);
    }

    /**
     * Sets the minimum amount for the base {@link Value}.
     *
     * @param value minimum amount for the base {@link Value}
     */
    public void setMinBase(int value) {
        this.base.setMin(value);
    }

    /**
     * Gets the total of the base and modifier minimum amounts {@link Value}.
     *
     * @return the total of the base and modifier minimum amounts {@link Value}
     */
    public int getTotalMin() {
        return this.base.min() + this.modifier.min();
    }

    /**
     * Gets the total of the base and modifier maximum amounts {@link Value}.
     *
     * @return the total of the base and modifier maximum amounts {@link Value}
     */
    public int getTotalMax() {
        return this.base.max() + this.modifier.max();
    }

    /**
     * Gets the total of the base and modifier current amounts {@link Value}.
     *
     * @return the total of the base and modifier current amounts {@link Value}
     */
    public int getTotal() {
        return this.base.current() + this.modifier.current();
    }

    /**
     * Returns a string representation of the {@code Stat}'s state.
     * Assumes you have 25 characters you need to fit the representation across.
     * @return a string representation of the {@code Stat}'s state
     */
    @Override
    public String toString() {
        return this.getName().toUpperCase()
                + " "
                + this.getTotal()
                + " : ("
                + this.base.current()
                + " + "
                + this.modifier.current() + ")";
    }
}

