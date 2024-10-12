package demoworld.view.sheetpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class represents the view component for rolling dice.
 * It extends JPanel and provides functionality to roll a dice
 * with optional advantage and reroll ones.
 */
public class DicePanel extends JPanel {

    /**
     * Button to trigger rolling the dice.
     * The {@code JButton} that initiates the dice roll when clicked.
     */
    private final JButton rollButton;

    /**
     * Checkbox to indicate if the roll should have advantage.
     * When selected, the roll will follow the game's advantage rules.
     */
    private final JCheckBox advantageCheckBox;

    /**
     * Checkbox to indicate if the player wants to reroll ones.
     * When selected, dice rolls that result in a one will be rerolled.
     */
    private final JCheckBox rerollOnesCheckBox;

    /**
     * Label to display the result of the dice roll.
     * The {@code JLabel} that shows the outcome of the dice roll.
     */
    private final JLabel resultLabel;


    /**
     * Instantiates a new DicePanel.
     */
    public DicePanel() {
        this.setLayout(new GridLayout(3, 2));

        rollButton = new JButton("Roll");
        advantageCheckBox = new JCheckBox("Advantage?");
        rerollOnesCheckBox = new JCheckBox("Reroll ones?");
        resultLabel = new JLabel("Dice Panel", SwingConstants.CENTER);

        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));

        this.add(resultLabel);
        this.add(rollButton);
        this.add(advantageCheckBox);
        this.add(rerollOnesCheckBox);
    }

    /**
     * Adds a given ActionListener to the roll button.
     *
     * @param listener the ActionListener to be added to the roll button.
     */
    public void addRollListener(ActionListener listener) {
        rollButton.addActionListener(listener);
    }

    /**
     * Updates the text for the dice panel with the new result value prepended by "Result:".
     *
     * @param result the result of the dice roll.
     */
    public void updateResult(int result) {
        resultLabel.setText("Result: " + result);
    }

    /**
     * Returns if the checkbox for advantage has been checked for the dice panel.
     *
     * @return a boolean representing the checked state of the advantage checkbox.
     */
    public boolean hasAdvantage() {
        return advantageCheckBox.isSelected();
    }

    /**
     * Returns if the checkbox for reroll ones has been checked for the dice panel.
     *
     * @return a boolean representing the checked state of the reroll ones checkbox.
     */
    public boolean hasRerollOnes() {
        return rerollOnesCheckBox.isSelected();
    }
}
