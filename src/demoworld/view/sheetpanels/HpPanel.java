package demoworld.view.sheetpanels;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * HpPanel extends JPanel and implements ReliesOnCharacterData.
 * Responsible for representing the Hitpoints state of a Character
 * in a visual panel with buttons for interacting
 * (e.g., damaging, healing, adding temporary hitpoints).
 */
public class HpPanel extends JPanel implements ReliesOnCharacterData {

    /**
     * The progress bar that visually represents the character's hitpoints (HP).
     * A {@code JProgressBar} used to display the current HP relative to the maximum HP.
     */
    private JProgressBar hpBar;

    /**
     * The button used to apply damage to the character.
     * A {@code JButton} that triggers the reduction of HP when pressed.
     */
    private JButton damageButton;

    /**
     * The button used to heal the character.
     * A {@code JButton} that triggers an increase in HP when pressed.
     */
    private JButton healButton;

    /**
     * The button used to add temporary hitpoints to the character.
     * A {@code JButton} that triggers the addition of temporary HP when pressed.
     */
    private JButton tempHpButton;

    /**
     * Character
     */
    private Character character;


    /**
     * Constructor for HpPanel.
     * Initializes the panel, adds components (buttons and label), and sets a default layout.
     */
    public HpPanel() {
        setLayout(new BorderLayout());

        hpBar = new JProgressBar(0, 100);
        hpBar.setString("HITPOINTS (0) 0/0");
        hpBar.setStringPainted(true);

        damageButton = new JButton("Damage");
        healButton = new JButton("Heal");
        tempHpButton = new JButton("+1 Temp Hp");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(damageButton);
        buttonPanel.add(healButton);
        buttonPanel.add(tempHpButton);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(0, 20));

        add(hpBar, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(emptyPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the state of the HpPanel to display the character's current
     * hitpoints and change the background color based on health percentage.
     *
     * @param character the character whose hitpoints are displayed in the panel.
     *                  This method pulls data from the character's hitpoints
     *                  and updates the panel's label.
     */
    public void updateCharacter(Character character) {

        this.character = character;

        int currentHp = character.getHitpoints().getBase().current();
        int maxHp = character.getHitpoints().getBase().max();
        int tempHp = character.getHitpoints().getTempHp();

        hpBar.setMaximum(maxHp);
        hpBar.setValue(currentHp);

        hpBar.setString("HITPOINTS (" + tempHp + ") " + currentHp + "/" + maxHp);

        int percentage = (int) (((double) currentHp / maxHp) * 100);

        if (percentage <= 50) {
            hpBar.setForeground(new Color(148, 24, 37));
        } else if (percentage > 50 && percentage <= 70) {
            hpBar.setForeground(new Color(210, 111, 47));
        } else if (percentage > 70 && percentage <= 80) {
            hpBar.setForeground(new Color(193, 161, 20));
        } else {
            hpBar.setForeground(new Color(40, 100, 23));
        }
        hpBar.repaint();
    }

    /**
     * Adds an ActionListener to the "Damage" button.
     *
     * @param listener the ActionListener to be attached to the damage button.
     */
    public void addDamageListener(ActionListener listener) {
        damageButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Heal" button.
     *
     * @param listener the ActionListener to be attached to the heal button.
     */
    public void addHealListener(ActionListener listener) {
        healButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Temp Hp" button.
     *
     * @param listener the ActionListener to be attached to the temp hitpoints button.
     */
    public void addTempHpListener(ActionListener listener) {
        tempHpButton.addActionListener(listener);
    }
}

