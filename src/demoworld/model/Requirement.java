package demoworld.model;

/**
 * Requirements check if a {@link Character} has a given {@link Stat} that matches the
 * {@code statKey} held by this and if that {@link Character} {@link Stat} is equal
 * to or above the threshold requirement is enforcing.
 */
public class Requirement implements Named {
    /**
     * name
     */
    private final String name;
    /**
     * statKey
     */
    private final String statKey;
    /**
     * threshold
     */
    private final int threshold;

    /**
     * Construct a new requirement with a given name, statKey and threshold.
     *
     * @param name      the name of the requirement
     * @param statKey   the name of the {@link Stat} you want the requirement to check against
     * @param threshold how much the {@link Stat} {@link Value} current total
     *                  amount needs to be equal to or above
     */
    public Requirement(String name, String statKey, int threshold) {
        this.name = name;
        this.statKey = statKey;
        this.threshold = threshold;
    }

    /**
     * Gets the name of the Requirement.
     *
     * @return name of the Requirement
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns if the {@link Character} current state fulfills the criteria
     * of this specific Requirement
     *
     * @param character the character state that will be checked
     * @return true if the characters {@link Stat} that matches the {@code statKey}
     */
    public boolean check(Character character) {
        return character.getStatByName(this.statKey).getTotal() >= this.threshold;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.statKey);
        result.append(" ");
        result.append(">=");
        result.append(" ");
        result.append(this.threshold);
        return result.toString();
    }
}