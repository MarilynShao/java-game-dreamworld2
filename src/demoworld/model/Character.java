package demoworld.model;

/**
 * A representation of a characters state in a rpg game
 */
public class Character {
    /**
     * The name of the character.
     */
    private String name;

    /**
     * A reference to the rulebook that defines the rules.
     */
    private final RuleBook rulebook;

    /**
     * An entry manager that holds all the stats related to the character.
     */
    private final EntryManager<Stat> stats;

    /**
     * An entry manager that holds all the features that have been added to the character.
     */
    private final EntryManager<Feature> features;

    /**
     * An entry manager that holds all the specialties for the character.
     */
    private final EntryManager<Specialty> specialties;

    /**
     * Represents the hitpoints (HP) of the character.
     */
    private final Hitpoints hitpoints;

    /**
     * Represents the experience points (XP) of the character.
     */
    private final Experience experience;


    /**
     * Constructs a new Character with the given name and rulebook.
     *
     * @param name      the initial name for the character, which should be a String
     * @param rulebook  the DemoWorld rulebook the character should refer to.
     */
    public Character(String name, RuleBook rulebook) {
        this.name = name;
        this.rulebook = rulebook;
        this.stats = rulebook.getStat();
        this.features = new EntryManager<>();
        this.specialties = new EntryManager<>();
        this.hitpoints = rulebook.setCharacterStartingHitpoints();
        this.experience = rulebook.setCharacterStartingExperience();
    }

    /**
     * Gets the Hitpoints Stat from the Character.
     *
     * @return reference to the Hitpoints Stat from the Character
     */
    public Hitpoints getHitpoints() {
        return this.hitpoints;
    }

    /**
     * Sets the name of the Character.
     *
     * @param name the name to assign to this Character.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the Character.
     *
     * @return name of the Character.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the EntryManager for the character
     *
     * @return reference to the characters EntryManager
     */
    public EntryManager<Stat> getStat() {
        return this.stats;
    }

    /**
     * Gets the Stat with the given name from the character.
     *
     * @param name name of stat looking for
     * @return the Stat with the given name from the character.
     */
    public Stat getStatByName(String name) {
        return this.stats.byName(name);
    }

    /**
     * Gets the EntryManager for the character
     *
     * @return reference to the characters EntryManager
     */
    public EntryManager<Feature> getFeature() {
        return this.features;
    }

    /**
     * Gets the EntryManager for the character
     *
     * @return reference to the characters EntryManager
     */
    public EntryManager<Specialty> getSpecialty() {
        return this.specialties;
    }

    /**
     * Gets the characters Experience object
     *
     * @return reference to the Experience for the character
     */
    public Experience getExperience() {
        return this.experience;
    }

    /**
     * adds a specialty to the character and adjusts the character based on that Specialty
     *
     * @param specialty the Specialty being added to the character
     */
    public void addSpecialty(Specialty specialty) {
        if (!this.specialties.all().contains(specialty)) {
            this.specialties.add(specialty);
            specialty.adjust(this);
        }
    }

    /**
     * removes a specialty and all related features from the character and unadjusts the character.
     *
     * @param specialty the Specialty being removed from the character
     */
    public void removeSpecialty(Specialty specialty) {
        if (this.specialties.all().contains(specialty)) {
            specialty.unadjust(this);
            this.specialties.remove(specialty);
        }
    }

    /**
     * adds a feature to the character if they do not already have it and adjusts
     * the character based on that feature
     * @param feature the Feature to add.
     */
    public void addFeature(Feature feature) {
        if (!this.features.all().contains(feature)) {
            this.features.add(feature);
            feature.adjust(this);
        }
    }

    /**
     * removes a feature to the character and unadjusts the character based on that feature
     *
     * @param feature the Feature being removed from the character
     */
    public void removeFeature(Feature feature) {
        if (this.features.all().contains(feature)) {
            feature.unadjust(this);
            this.features.remove(feature);
        }
    }

    /**
     * damages the character and then checks for death.
     * any amount of damage may be supplied, but negative damage is not possible.
     * @param value the amount of damage to be inflicted on the character
     */
    public void damage(int value) {
        this.hitpoints.damage(value);
        checkForDeath();
    }

    /**
     * Checks if the Character is dead, if so, adds the "downed" feature.
     */
    private void checkForDeath() {
        if (this.hitpoints.isDead()) {
            Feature downed = this.rulebook.getFeatureByName("downed");
            this.addFeature(downed);
        }
    }

    /**
     * heals the character and then checks for death.
     * any amount of heal may be supplied, but negative heal is not possible.
     * @param value the amount of hitpoints to be healed
     */
    public void heal(int value) {
        this.hitpoints.heal(value);
        if (!this.hitpoints.isDead()) {
            Feature downed = this.rulebook.getFeatureByName("downed");
            this.removeFeature(downed);
        }
    }

    /**
     * adjusts the characters Experience then check if the character
     * should level up and reset the xp back to the minimum
     * @param value the value to add to the Character's current experience.
     */
    public void adjustXp(int value) {
        this.experience.addToCurrent(value);
        if (this.experience.canLevelUp()) {
            this.experience.resetToMin();
            this.hitpoints.addToMaxBase(1);
        }
    }

    /**
     * creates and returns a string representation of the characters current state
     *
     * @return a String representation of the Character's current state.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("   _______________________________").append("\n")
                .append(" / \\                              |").append("\n")
                .append(" \\__|                             |").append("\n")
                .append("    |          * ").append(this.name).append(" *          |").append("\n")
                .append("    |                             |").append("\n");

        int maxStatLength = getMaxStatLength();

        for (Stat stat : this.stats.all()) {
            String statString = stat.toString();
            sb.append("    |  ").append(padRight(statString, maxStatLength)).append("     |\n");
        }

        sb.append("    |                             |\n")
                .append("    |  ___________________________|__\n")
                .append("    \\_/_____________________________/\n");

        sb.append(this.hitpoints.toString()).append("\n");
        sb.append(this.experience.toString()).append("\n");

        sb.append("        |================================\\\n")
                .append("[0]#####>--------  SPECIALTIES  ---------->\n")
                .append("        |================================/\n\n");

        int specialtyIndex = 1;
        for (Specialty specialty : this.specialties.all()) {
            sb.append(specialtyIndex).append(". ").append(specialty.toString()).append("\n\n");
            specialtyIndex++;
        }

        sb.append("        |================================\\\n")
                .append("[0]#####>----------  FEATURES  ----------->\n")
                .append("        |================================/\n\n");

        int featureIndex = 1;
        for (Feature feature : this.features.all()) {
            sb.append(featureIndex).append(". ").append(feature.toString()).append("\n\n");
            featureIndex++;
        }

        return sb.toString();
    }

    /**
     * Calculates the maximum length of the stat string for proper alignment.
     *
     * @return the maximum length of any stat's string representation
     */
    private int getMaxStatLength() {
        int maxLength = 0;
        for (Stat stat : this.stats.all()) {
            maxLength = Math.max(maxLength, stat.toString().length());
        }
        return maxLength;
    }

    /**
     * Pads a string with spaces to ensure consistent width for alignment.
     *
     * @param str    the string to pad
     * @param length the length to pad the string to
     * @return the padded string
     */
    private String padRight(String str, int length) {
        return String.format("%-" + length + "s", str);
    }


}
