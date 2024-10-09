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

    // UI components: JLabel for displaying HP and buttons for damage, healing, and temp HP
    private JLabel hpLabel;
    private JButton damageButton;
    private JButton healButton;
    private JButton tempHpButton;

    /**
     * Constructor for HpPanel.
     * Initializes the panel, adds components (buttons and label), and sets a default layout.
     */
    public HpPanel() {
        // Set layout for the panel (Grid layout to display components in a row of 4 columns)
        setLayout(new GridLayout(1, 4));

        // Initialize the components (buttons and label)
        hpLabel = new JLabel();
        damageButton = new JButton("Damage");
        healButton = new JButton("Heal");
        tempHpButton = new JButton("+1 Temp Hp");

        // Add the buttons and label to the panel
        add(damageButton);
        add(healButton);
        add(tempHpButton);
        add(hpLabel);

        // Set default text for hitpoints label (e.g., when no character data is loaded)
        hpLabel.setText("HITPOINTS (0) 0/0");
    }

    /**
     * Updates the state of the HpPanel to display the character's current hitpoints.
     *
     * @param character the character whose hitpoints are displayed in the panel.
     *                  This method pulls data from the character's hitpoints and updates the panel's label.
     */
    public void updateCharacter(Character character) {
        // Retrieve current hitpoints, max hitpoints, and temporary hitpoints from the character
        int currentHp = character.getHitpoints().getBase().current();  // Current base HP
        int maxHp = character.getHitpoints().getBase().max();          // Maximum base HP
        int tempHp = character.getHitpoints().getTempHp();             // Temporary HP

        // Update the label to show hitpoints in the format: "HITPOINTS (tempHp) currentHp/maxHp"
        hpLabel.setText("HITPOINTS (" + tempHp + ") " + currentHp + "/" + maxHp);
    }

    /**
     * Adds an ActionListener to the "Damage" button.
     * This allows external components to handle the damage action when the button is clicked.
     *
     * @param listener the ActionListener to be attached to the damage button.
     */
    public void addDamageListener(ActionListener listener) {
        damageButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Heal" button.
     * This allows external components to handle the heal action when the button is clicked.
     *
     * @param listener the ActionListener to be attached to the heal button.
     */
    public void addHealListener(ActionListener listener) {
        healButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Temp Hp" button.
     * This allows external components to handle adding temporary hitpoints when the button is clicked.
     *
     * @param listener the ActionListener to be attached to the temp hitpoints button.
     */
    public void addTempHpListener(ActionListener listener) {
        tempHpButton.addActionListener(listener);
    }
}
