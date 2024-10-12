package demoworld.model;


/**
 * Responsible for managing rolling the dice pool (2 six-sided dice) required for
 * this game with both, either or neither of the below sub conditions applied:
 * advantage - roll 2 pools and take the highest. re-roll number's - any dice in a
 * pool that rolls number can be re-rolled once. (i.e. if rolling 2 dice, and
 * rerolling 1's and get 1,6 the 1 could be rerolled but if 1 is rolled again it
 * will not be re-rolled as a part of that game roll action)
 */
public class DiceRoller {
    /**
     * The first dice used for rolling.
     */
    private final Dice dice1;

    /**
     * The second dice used for rolling.
     */
    private final Dice dice2;

    /**
     * Instantiate a version of the DiceRoller where the Dice are not using any provided seeds.
     */
    public DiceRoller() {
        this.dice1 = new Dice(6);
        this.dice2 = new Dice(6);
    }

    /**
     * Instantiate a version of the DiceRoller where the Dice are using seeds based
     * on a provided long value. [v1.2 update - to save always rolling "doubles"
     * (if both dice were to use the same seed), set one Dice to use the provided
     * seed and the second Dice to use seed + 1.]
     *
     * @param seed The long seed that will be used as the base to
     *             seed the Dice instantiated for this dice roller.
     */
    public DiceRoller(long seed) {
        this.dice1 = new Dice(6, seed);
        this.dice2 = new Dice(6, seed + 1);
    }

    /**
     * Attempts a game roll with the dice specified for the game , 2D6 by default
     *
     * @return an int for the dice(s) total pre any Stat based modification
     */
    public int roll() {
        return dice1.roll() + dice2.roll();
    }

    /**
     * Overloaded method that checks if the roll should be rolled "with advantage",
     * if so rolls an attempt twice and return the higher result.
     *
     * @param advantage indicates whether the dice is to be rolled more than once
     * @return an int for the dice(s) total pre any Stat based modification
     */
    public int roll(boolean advantage) {
        if (advantage) {
            int roll1 = this.roll();
            int roll2 = this.roll();
            return Math.max(roll1, roll2);
        }
        return this.roll();
    }

    /**
     * Overloaded method that will reroll each dice once if they
     * match a given number typically used for magical items or similar that let you re-roll 1's
     *
     * @param rerollTargetNumber the target number to be checked against
     * @return an int for the dice(s) total pre any Stat based modification
     */
    public int roll(int rerollTargetNumber) {
        int roll1 = dice1.roll();
        int roll2 = dice2.roll();

        if (roll1 == rerollTargetNumber) {
            roll1 = dice1.roll();
        }
        if (roll2 == rerollTargetNumber) {
            roll2 = dice2.roll();
        }
        return roll1 + roll2;
    }

    /**
     * Overloaded method that will check for the combination of advantage
     * AND a rerollable number Will do each roll with rerollTargetNumber,
     * and if advantage is flagged take the highest.
     *
     * @param rerollTargetNumber indicates what result to re-roll once on the dice
     * @param advantage          indicates whether this roll should have advantage
     * @return an int for the dice(s) total pre any Stat based modification
     */
    public int roll(int rerollTargetNumber, boolean advantage) {
        if (advantage) {
            int roll1 = this.roll(rerollTargetNumber);
            int roll2 = this.roll(rerollTargetNumber);
            return Math.max(roll1, roll2);
        }
        return this.roll(rerollTargetNumber);
    }
}

