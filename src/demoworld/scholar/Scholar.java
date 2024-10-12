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

    /**
     * The rulebook that the character refers to for game rules and data.
     * This is a reference to the game’s {@code RuleBook} object.
     */
    private final RuleBook rulebook;

    /**
     * The current mode of the game or application.
     * {@code Mode} indicates the current operational mode the character or game is in.
     */
    private Mode currentMode;

    /**
     * The name of the character.
     * This is a {@code String} that stores the name of the character in the game.
     */
    private String characterName;

    /**
     * The current hitpoints of the character.
     * This integer represents the base health of the character.
     */
    private int hitpoints;

    /**
     * The temporary hitpoints of the character.
     * {@code tempHp} represents the extra or temporary health
     * points that can be lost before affecting base health.
     */
    private int tempHp;

    /**
     * The current experience points of the character.
     * {@code experience} represents the progress towards leveling up.
     */
    private int experience;

    /**
     * A list of specialties the character has.
     * Each {@code String} represents a specialty by its name.
     */
    private final List<String> specialties;

    /**
     * A list of features the character has.
     * Each {@code String} represents a feature by its name.
     */
    private final List<String> features;

    /**
     * A reference to the {@code Character} object.
     * This represents the character being manipulated or
     * referenced within the current context.
     */
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
        System.out.println("Starting to build character from file: " + filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            character = new Character("Unnamed Character", rulebook);  // Default name

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
     * Processes a given line of text, determining if there is any information
     * within that should be tracked for future use when getCharacter is called.
     *
     * @param line The line that will be processed.
     */
    public void processLine(String line) {
        line = line.trim();

        if (line.matches("^[|=\\[\\]#>/-]+$")) {
            return;
        }
        System.out.println("Processing line: " + line);

        if (line.contains("*") && line.contains("|")) {
            System.out.println("Line with * detected: " + line);
            characterName = extractName(line);
            if (characterName != null && !characterName.isEmpty()) {
                System.out.println("Extracted Character Name: " + characterName);
            } else {
                System.err.println("Failed to extract name from line: " + line);
            }
        } else if (line.contains("FORCE") || line.contains("QUICKNESS")
                || line.contains("RESILIENCE")
                || line.contains("ANALYTICAL") || line.contains("EMPATHY")) {

            String[] parts = line.split("\\s+");

            if (parts.length >= 2) {
                String statName = parts[1].toLowerCase();
                int statValue = extractStat(line);

                System.out.println("Extracting stat name: " + statName);

                Stat stat = character.getStatByName(statName);
                if (stat != null) {
                    stat.setCurrentBase(statValue);
                } else {
                    System.err.println("Stat not found: " + statName);
                }
            } else {
                System.err.println("Invalid stat line: " + line);
            }
        } else if (line.startsWith("HITPOINTS")) {
            hitpoints = extractCurrentHp(line);
            System.out.println("Current HP: " + hitpoints);
        } else if (line.startsWith("TEMP HP")) {
            tempHp = extractTempHp(line);
            System.out.println("Temp HP: " + tempHp);
        } else if (line.startsWith("XP")) {
            experience = extractCurrentXp(line);
            System.out.println("Current XP: " + experience);
        } else if (line.contains("SPECIALTIES")) {
            currentMode = Mode.SPECIALTIES;
        } else if (line.contains("FEATURES")) {
            currentMode = Mode.FEATURES;
        } else if (currentMode == Mode.SPECIALTIES) {
            String specialtyName = extractNamedEntry(line);
            if (specialtyName != null) {
                System.out.println("Extracting specialty: " + specialtyName);
                specialties.add(specialtyName);
            }
        } else if (currentMode == Mode.FEATURES) {
            String featureName = extractNamedEntry(line);
            if (featureName != null) {
                System.out.println("Extracting feature: " + featureName);
                features.add(featureName);
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
        return line.substring(line.indexOf("*") + 1, line.lastIndexOf("*")).trim().toUpperCase();
    }

    /**
     * Extracts the stat value from the line.
     *
     * @param line the line of text expected to contain a stat
     * @return the stat value from the line as an int
     */
    public int extractStat(String line) {
        line = line.trim();

        if (line.startsWith("|")) {
            line = line.substring(1).trim();
        }
        if (line.endsWith("|")) {
            line = line.substring(0, line.length() - 1).trim();
        }

        String cleanedLine = line.replaceAll("[^0-9-]+", " ").trim();

        String[] parts = cleanedLine.split("\\s+");

        for (String part : parts) {
            try {
                return Integer.parseInt(part);
            } catch (NumberFormatException e) {
                System.err.println("Invalid stat value found: " + part);
            }
        }
        throw new NumberFormatException("No valid stat value found in line: " + line);
    }

    /**
     * Extracts a named entry from a line of text, used for features and specialties.
     *
     * @param line the line of text expected to have a named entry in it
     * @return the extracted named entry from the line, or null if the line is invalid
     */
    public String extractNamedEntry(String line) {
        line = line.trim();

        if (line.matches("^[|=/\\-]+$") || line.isEmpty()) {
            return null;
        }

        if (line.matches("^\\d+\\.\\s+.*")) {
            String entry = line.split("\\.\\s+", 2)[1].trim();

            entry = entry.replaceAll("\\[.*?\\]$", "").trim();

            return entry.toLowerCase();
        }
        return null;
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
