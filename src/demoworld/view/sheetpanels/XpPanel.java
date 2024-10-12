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

    /**
     * The button for gaining experience points.
     * A {@code JButton} that triggers an action to increase the character's XP.
     */
    private JButton gainXpButton;

    /**
     * The button for losing experience points.
     * A {@code JButton} that triggers an action to decrease the character's XP.
     */
    private JButton loseXpButton;

    /**
     * A progress bar visually displaying the character's experience points.
     * A {@code JProgressBar} used to represent the XP progress in a graphical form.
     */
    private JProgressBar xpProgressBar;

    /**
     * A reference to the character whose experience is displayed and updated.
     */
    private Character character;


    /**
     * Constructs the XpPanel.
     */
    public XpPanel() {

        setLayout(new BorderLayout());

        xpProgressBar = new JProgressBar(0, 100);
        gainXpButton = new JButton("+1 Xp");
        loseXpButton = new JButton("-1 Xp");

        xpProgressBar.setString("XP: 0/0");
        xpProgressBar.setStringPainted(true);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(loseXpButton);
        buttonPanel.add(gainXpButton);

        add(xpProgressBar, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
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

        xpProgressBar.setMaximum(maxXp);
        xpProgressBar.setValue(currentXp);
        xpProgressBar.setString("XP: " + currentXp + "/" + maxXp);

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

