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

    private JProgressBar hpBar;
    private JLabel hpLabel;
    private JButton damageButton;
    private JButton healButton;
    private JButton tempHpButton;

    /**
     * Constructor for HpPanel.
     * Initializes the panel, adds components (buttons and label), and sets a default layout.
     */
    public HpPanel() {
        setLayout(new BorderLayout());

        hpBar = new JProgressBar(0, 100);
        hpBar.setStringPainted(true);

        hpLabel = new JLabel("HITPOINTS (0) 0/0", SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        damageButton = new JButton("Damage");
        healButton = new JButton("Heal");
        tempHpButton = new JButton("+1 Temp Hp");

        buttonPanel.add(damageButton);
        buttonPanel.add(healButton);
        buttonPanel.add(tempHpButton);

        add(hpBar, BorderLayout.NORTH);
        add(hpLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the state of the HpPanel to display the character's current
     * hitpoints and change the background color based on health percentage.
     *
     * @param character the character whose hitpoints are displayed in the panel.
     *                  This method pulls data from the character's hitpoints and updates the panel's label.
     */
    public void updateCharacter(Character character) {

        int currentHp = character.getHitpoints().getBase().current();
        int maxHp = character.getHitpoints().getBase().max();
        int tempHp = character.getHitpoints().getTempHp();

        int percentage = (int) (((double) currentHp / maxHp) * 100);
        hpBar.setValue(percentage);

        hpLabel.setText("HITPOINTS (" + tempHp + ") " + currentHp + "/" + maxHp);

        if (percentage <= 50) {
            hpBar.setForeground(new Color(148, 24, 37));
        } else if (percentage > 50 && percentage <= 70) {
            hpBar.setForeground(new Color(210, 111, 47));
        } else if (percentage > 70 && percentage <= 80) {
            hpBar.setForeground(new Color(193, 161, 20));
        } else {
            hpBar.setForeground(new Color(40, 100, 23));
        }
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

