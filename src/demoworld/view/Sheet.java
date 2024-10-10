package demoworld.view;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.view.sheetpanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Sheet
        extends JPanel
        implements ReliesOnCharacterData {

    private HpPanel hpPanel; // Top HP panel
    private XpPanel xpPanel; // XP panel
    private StatsPanel statsPanel; // Left panel for stats
    private DicePanel dicePanel; // Right panel for dice rolling
    private FeatureList featureList; // Left panel for features
    private SpecialtyList specialtyList; // Right panel for specialties

    public Sheet() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create and add the HP Panel
        hpPanel = new HpPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Spanning across two columns
        add(hpPanel, gbc);

        // Create a gap
        gbc.gridy = 1;
        add(Box.createVerticalStrut(10), gbc);

        // Create and add the XP Panel
        xpPanel = new XpPanel();
        gbc.gridy = 2;
        add(xpPanel, gbc);

        // Create another gap
        gbc.gridy = 3;
        add(Box.createVerticalStrut(10), gbc);

        // Add the stat panel on the left
        statsPanel = new StatsPanel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Resetting gridwidth to 1
        add(statsPanel, gbc);

        // Add the dice panel on the right
        dicePanel = new DicePanel();
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(dicePanel, gbc);

        // Add the feature panel on the left
        featureList = new FeatureList();
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(featureList, gbc);

        // Add the specialty panel on the right
        specialtyList = new SpecialtyList();
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(specialtyList, gbc);
    }

    @Override
    public void updateCharacter(Character character) {
        hpPanel.updateCharacter(character);
        xpPanel.updateCharacter(character);
        statsPanel.updateCharacter(character);
        featureList.updateCharacter(character);
        specialtyList.updateCharacter(character);
    }


    public DicePanel getDicePanel() {
        return dicePanel;
    }

    public void addGainXpListener(ActionListener listener) {
        xpPanel.addGainXpListener(listener);
    }

    public void addLoseXpListener(ActionListener listener) {
        xpPanel.addLoseXpListener(listener);
    }

    public void addHealListener(ActionListener listener) {
        hpPanel.addHealListener(listener);
    }

    public void addTempHpListener(ActionListener listener) {
        hpPanel.addTempHpListener(listener);
    }

    public void addDamageListener(ActionListener listener) {
        hpPanel.addDamageListener(listener);
    }

    public void addRemoveFeatureListener(ActionListener listener) {
        featureList.addRemoveListener(listener);
    }

    public void addRemoveSpecialtyListener(ActionListener listener) {
        specialtyList.addRemoveListener(listener);
    }

    public String getSpecialtyPick() {
        return specialtyList.getSelectedEntry();
    }

    public String getFeaturePick() {
        return featureList.getSelectedEntry();
    }

    public void addDisplaySpecialtyInfoListener(ActionListener listener) {
        specialtyList.addSelectionListener(listener);
    }

    public void addDisplayFeatureInfoListener(ActionListener listener) {
        featureList.addSelectionListener(listener);
    }
}


