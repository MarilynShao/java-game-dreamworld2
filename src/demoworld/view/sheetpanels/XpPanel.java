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
    private JProgressBar xpProgressBar; // Progress bar for XP

    /**
     * Constructs the XpPanel.
     */
    public XpPanel() {

        setLayout(new GridLayout(2, 1));

        xpLabel = new JLabel();
        xpProgressBar = new JProgressBar();
        gainXpButton = new JButton("+1 Xp");
        loseXpButton = new JButton("-1 Xp");

        xpProgressBar.setStringPainted(true);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(loseXpButton);
        buttonPanel.add(gainXpButton);

        add(xpLabel);
        add(xpProgressBar);
        add(buttonPanel);

        xpLabel.setText("XP: 0/0");
    }

    /**
     * Updates the state of the XpPanel using the given Character.
     *
     * @param character the character to refer to when updating this class' internal state.
     */
    @Override
    public void updateCharacter(Character character) {

        int currentXp = character.getExperience().current();
        int maxXp = character.getExperience().max();

        xpLabel.setText("XP: " + currentXp + "/" + maxXp);

        xpProgressBar.setMaximum(maxXp);
        xpProgressBar.setValue(currentXp);
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

