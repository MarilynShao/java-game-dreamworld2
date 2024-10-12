package demoworld.view;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.view.sheetpanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Sheet class represents the UI panel for displaying and interacting with a character's data.
 * It includes panels for hit points (HP), experience points (XP), stats,
 * features, specialties, and dice rolling.
 */
public class Sheet extends JPanel implements ReliesOnCharacterData {

    /**
     * Label for displaying the character's name.
     */
    private JLabel characterNameLabel;
    /**
     * Panel for displaying and updating the character's HP (Hit Points).
     */
    private HpPanel hpPanel;
    /**
     * Panel for displaying and updating the character's XP (Experience Points).
     */
    private XpPanel xpPanel;
    /**
     * Panel for displaying and managing the character's stats.
     */
    private StatsPanel statsPanel;
    /**
     * Panel for managing dice rolling interactions.
     */
    private DicePanel dicePanel;
    /**
     * List component for displaying and managing the character's features.
     */
    private FeatureList featureList;
    /**
     * List component for displaying and managing the character's specialties.
     */
    private SpecialtyList specialtyList;

    /**
     * Constructs a new Sheet panel and arranges its components using a {@link GridBagLayout}.
     * This includes panels for the character's name, HP, XP, stats,
     * dice panel, features, and specialties.
     */
    public Sheet() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        characterNameLabel = new JLabel("Character Name", SwingConstants.LEFT);
        characterNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(characterNameLabel, gbc);

        hpPanel = new HpPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        add(hpPanel, gbc);

        xpPanel = new XpPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(xpPanel, gbc);

        statsPanel = new StatsPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(statsPanel, gbc);

        dicePanel = new DicePanel();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(dicePanel, gbc);

        featureList = new FeatureList();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 0.3;
        gbc.gridwidth = 1;
        add(featureList, gbc);

        specialtyList = new SpecialtyList();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 0.3;
        gbc.gridwidth = 1;
        add(specialtyList, gbc);
    }

    /**
     * Updates the components of the sheet with the character's data.
     *
     * @param character The character whose data will be displayed and updated in the sheet.
     */
    @Override
    public void updateCharacter(Character character) {
        characterNameLabel.setText(character.getName());
        hpPanel.updateCharacter(character);
        xpPanel.updateCharacter(character);
        statsPanel.updateCharacter(character);
        featureList.updateCharacter(character);
        specialtyList.updateCharacter(character);
    }

    /**
     * Returns the dice panel for dice-related interactions.
     *
     * @return the dice panel used in the sheet.
     */
    public DicePanel getDicePanel() {
        return dicePanel;
    }

    /**
     * Adds an ActionListener for gaining XP.
     *
     * @param listener The listener triggered when the gain XP button is pressed.
     */
    public void addGainXpListener(ActionListener listener) {
        xpPanel.addGainXpListener(listener);
    }

    /**
     * Adds an ActionListener for losing XP.
     *
     * @param listener The listener triggered when the lose XP button is pressed.
     */
    public void addLoseXpListener(ActionListener listener) {
        xpPanel.addLoseXpListener(listener);
    }

    /**
     * Adds an ActionListener for healing the character.
     *
     * @param listener The listener triggered when the heal button is pressed.
     */
    public void addHealListener(ActionListener listener) {
        hpPanel.addHealListener(listener);
    }

    /**
     * Adds an ActionListener for gaining temporary HP.
     *
     * @param listener The listener triggered when the gain temporary HP button is pressed.
     */
    public void addTempHpListener(ActionListener listener) {
        hpPanel.addTempHpListener(listener);
    }

    /**
     * Adds an ActionListener for damaging the character.
     *
     * @param listener The listener triggered when the damage button is pressed.
     */
    public void addDamageListener(ActionListener listener) {
        hpPanel.addDamageListener(listener);
    }

    /**
     * Adds an ActionListener for removing a feature from the character.
     *
     * @param listener The listener triggered when the remove feature button is pressed.
     */
    public void addRemoveFeatureListener(ActionListener listener) {
        featureList.addRemoveListener(listener);
    }

    /**
     * Adds an ActionListener for removing a specialty from the character.
     *
     * @param listener The listener triggered when the remove specialty button is pressed.
     */
    public void addRemoveSpecialtyListener(ActionListener listener) {
        specialtyList.addRemoveListener(listener);
    }

    /**
     * Returns the currently selected specialty.
     *
     * @return the name of the selected specialty.
     */
    public String getSpecialtyPick() {
        return specialtyList.getSelectedEntry();
    }

    /**
     * Returns the currently selected feature.
     *
     * @return the name of the selected feature.
     */
    public String getFeaturePick() {
        return featureList.getSelectedEntry();
    }

    /**
     * Adds an ActionListener for displaying information about the selected specialty.
     *
     * @param listener The listener triggered when a specialty is selected.
     */
    public void addDisplaySpecialtyInfoListener(ActionListener listener) {
        specialtyList.addSelectionListener(listener);
    }

    /**
     * Adds an ActionListener for displaying information about the selected feature.
     *
     * @param listener The listener triggered when a feature is selected.
     */
    public void addDisplayFeatureInfoListener(ActionListener listener) {
        featureList.addSelectionListener(listener);
    }
}


