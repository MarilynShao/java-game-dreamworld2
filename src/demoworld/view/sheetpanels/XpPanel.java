package demoworld.view.sheetpanels;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * XpPanel extends JPanel and implements ReliesOnCharacterData.
 * Responsible for displaying the Character's current XP score and managing
 * buttons that alter the character's Experience state.
 */
public class XpPanel extends JPanel implements ReliesOnCharacterData {

    private JLabel xpLabel;
    private JButton gainXpButton;
    private JButton loseXpButton;

    /**
     * Constructs the XpPanel.
     */
    public XpPanel() {
        // Set the layout for the panel
        setLayout(new GridLayout(1, 3));  // Layout to display buttons and label in one row

        // Initialize label and buttons
        xpLabel = new JLabel();
        gainXpButton = new JButton("+1 Xp");
        loseXpButton = new JButton("-1 Xp");

        // Add buttons and label to the panel
        add(loseXpButton);
        add(gainXpButton);
        add(xpLabel);

        // Default label for XP (initial values)
        xpLabel.setText("XP: 0/0");
    }

    /**
     * Updates the state of the XpPanel using the given Character.
     *
     * @param character the character to refer to when updating this class' internal state.
     */
    public void updateCharacter(Character character) {
        // Update the XP label with the character's XP
        int currentXp = character.getExperience().current();
        int maxXp = character.getExperience().max();

        // Set the XP label text
        xpLabel.setText("XP: " + currentXp + "/" + maxXp);
    }

    /**
     * Adds a listener for gaining XP.
     *
     * @param listener the ActionListener that will trigger when the gain XP button is pressed.
     */
    public void addGainXpListener(ActionListener listener) {
        gainXpButton.addActionListener(listener);
    }

    /**
     * Adds a listener for losing XP.
     *
     * @param listener the ActionListener that will trigger when the lose XP button is pressed.
     */
    public void addLoseXpListener(ActionListener listener) {
        loseXpButton.addActionListener(listener);
    }
}

