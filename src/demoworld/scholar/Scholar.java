package demoworld.scholar;

import demoworld.model.Character;
import demoworld.model.RuleBook;
import demoworld.model.Stat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for loading in a given character sheet file and processing it.
 */
public class Scholar {
    /**
     * Enum to track the current processing mode for the Scholar class.
     * The Scholar class processes a character sheet, and this enum helps
     * track which section of the sheet is currently being processed.
     */
    public enum Mode {
        /** No mode is currently active. This is the default state before processing starts. */
        NONE,

        /**
         * The mode indicating that the Scholar is currently processing the Specialties section
         * of the character sheet.
         */
        SPECIALTIES,

        /**
         * The mode indicating that the Scholar is currently processing the Features section
         * of the character sheet.
         */
        FEATURES;
    }

    private final RuleBook rulebook;
    private Mode currentMode;
    private String characterName;
    private int hitpoints;
    private int tempHp;
    private int experience;
    private final List<String> specialties;
    private final List<String> features;
    private Character character;

    /**
     * Instantiates an instance of Scholar using the given Rulebook.
     *
     * @param rulebook the DemoWorld rulebook
     */
    public Scholar(RuleBook rulebook) {
        this.rulebook = rulebook;
        this.currentMode = Mode.NONE;
        this.specialties = new ArrayList<>();
        this.features = new ArrayList<>();
    }

    /**
     * Takes the file at the given filepath, reads the text line by line, and processes it.
     *
     * @param filePath the filepath to be read and processed
     * @return a reference to the Scholar instance, allowing method chaining
     */
    public Scholar build(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the character sheet: " + e.getMessage());
        }
        return this;
    }

    /**
     * Returns the current Scholar.Mode scholar is configured to.
     *
     * @return the current Scholar.Mode scholar is configured to
     */
    public Mode getCurrentMode() {
        return this.currentMode;
    }

    /**
     * Processes a given line of text determining if there is any information
     * within that should be tracked for future use if/when getCharacter is called.
     *
     * @param line The line that will be processed.
     */
    public void processLine(String line) {
        // Check for mode change
        if (line.contains("SPECIALTIES")) {
            currentMode = Mode.SPECIALTIES;
        } else if (line.contains("FEATURES")) {
            currentMode = Mode.FEATURES;
        } else if (currentMode == Mode.SPECIALTIES) {
            specialties.add(extractNamedEntry(line));
        } else if (currentMode == Mode.FEATURES) {
            features.add(extractNamedEntry(line));
        } else {
            // If no mode is active, check for other information
            if (line.startsWith("HITPOINTS")) {
                hitpoints = extractCurrentHp(line);
            } else if (line.startsWith("TEMP HP")) {
                tempHp = extractTempHp(line);
            } else if (line.startsWith("XP")) {
                experience = extractCurrentXp(line);
            } else if (line.contains("FORCE") || line.contains("QUICKNESS") ||
                    line.contains("RESILIENCE") || line.contains("ANALYTICAL") || line.contains("EMPATHY")) {
                // Extract stat values based on the line content
                int statValue = extractStat(line);
                String statName = line.split(" ")[0].toLowerCase();

                Stat stat = character.getStatByName(statName);
                if (stat != null) {
                    stat.setCurrentBase(statValue);
                }
            } else if (line.trim().startsWith("*")) {
                characterName = extractName(line);
            }
        }
    }



    /**
     * Generates a new Character using the information stored in scholar
     * from it reading the given file and consulting the RuleBook where relevant.
     *
     * @return The new Character
     */
    public Character getCharacter() {
        Character character = new Character(characterName, rulebook);
        character.getHitpoints().setCurrentBase(hitpoints);
        character.getHitpoints().setTempHp(tempHp);
        character.getExperience().setCurrent(experience);
        for (String specialty : specialties) {
            character.addSpecialty(rulebook.getSpecialtyByName(specialty));
        }
        for (String feature : features) {
            character.addFeature(rulebook.getFeatureByName(feature));
        }
        return character;
    }

    /**
     * Extracts the current HP (hit points) from the given line of text.
     *
     * @param line the line of text containing the HP information
     * @return the extracted current HP as an int
     */
    public int extractCurrentHp(String line) {
        String[] parts = line.split(":");
        return Integer.parseInt(parts[1].split("/")[0].trim());
    }

    /**
     * Extracts the current experience from the given line of text.
     *
     * @param line the line of text containing the experience information
     * @return the extracted current experience as an int
     */
    public int extractCurrentXp(String line) {
        String[] parts = line.split(":");
        return Integer.parseInt(parts[1].split("/")[0].trim());
    }

    /**
     * Extracts the current temporary hp from the character sheet.
     *
     * @param line the line of text expected to contain the temp hp value
     * @return the extracted current temporary hp as a int
     */
    public int extractTempHp(String line) {
        String[] parts = line.split(":");
        return Integer.parseInt(parts[1].trim());
    }

    /**
     * Extracts the current name from the character sheet.
     *
     * @param line the line of text expected to contain the characters name
     * @return the extracted name as a string
     */
    public String extractName(String line) {
        return line.substring(line.indexOf("*") + 1, line.lastIndexOf("*")).trim();
    }

    /**
     * Extracts the stat value from the line.
     *
     * @param line the line of text expected to contain a stat
     * @return the stat value from the line as an int
     */
    public int extractStat(String line) {
        return Integer.parseInt(line.replaceAll("[^0-9-]", ""));
    }

    /**
     * Extracts a named entry from a line of text, used for features and specialties.
     *
     * @param line the line of text expected to have a named entry in it
     * @return the extracted named entry from the line
     */
    public String extractNamedEntry(String line) {
        return line.trim().split("\\.")[1].trim();
    }

    /**
     * Returns a string representing the internal state of Scholar.
     *
     * @return a string representing the internal state of Scholar
     */
    @Override
    public String toString() {
        return "Scholar processing mode: " + currentMode + ", characterName: " + characterName;
    }
}
