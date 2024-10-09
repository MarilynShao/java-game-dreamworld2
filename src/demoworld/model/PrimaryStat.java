package demoworld.model;

/**
 * The {@code PrimaryStat} class represents a primary stat of a character.
 * It extends the {@link Stat} class.
 */
public class PrimaryStat extends Stat {
    /**
     * Constructs a new primary stat
     *
     * @param name        - name of the PrimaryStat
     * @param description - description of the PrimaryStat
     * @param min         - initial minimum amount for the PrimaryStat
     * @param max         - initial maximum amount for the PrimaryStat
     * @param current     - initial current amount for the PrimaryStat
     */
    public PrimaryStat(String name, String description, int min, int max, int current) {
        super(name, description, min, max, current);
    }
}
