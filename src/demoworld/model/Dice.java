package demoworld.model;

import java.util.Random;

/**
 * Dice Class responsible for managing a single die (singular of "dice").
 * Set how many sides it has, and roll it to a get a number between 1 and
 * the total number of sides inclusive.
 */
public class Dice {

    // Number of sides on the dice
    private int sides;

    // Random generator for rolling the dice
    private Random random;

    /**
     * Instantiates a single dice.
     *
     * @param sides the integer number of sides you want the dice to have
     */
    public Dice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    /**
     * Overloaded Constructor for Dice with the given number of sides and
     * a seed to make the dice rolling predictable for testing purposes.
     * @param sides the integer number of sides
     * @param seed  the seed for random number generation.
     */
    public Dice(int sides, long seed) {
        this.sides = sides;
        this.random = new Random();
        this.random.setSeed(seed);
    }

    /**
     * Returns the number of sides assigned to this dice.
     *
     * @return the number of sides on the dice.
     */
    public int getSides() {
        return this.sides;
    }

    /**
     * sets the number of sides assigned to this dice.
     * @param sides the integer number of sides
     */
    public void setSides(int sides) {
        this.sides = sides;
    }

    /**
     * Returns a random number between 1 and the number of
     * sides on the Dice. (e.g. a 6-sided dice could only return a number between 1 and 6).
     * @return a random number between 1 and the number of sides on the Dice
     */
    public int roll() {
        // Generates a random number between 1 and sides
        return random.nextInt(this.sides) + 1;
    }
}

