package demoworld.view.sheetpanels;

import demoworld.ReliesOnCharacterData;
import demoworld.model.Character;
import demoworld.model.Stat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * StatsPanel is responsible for displaying the Character current PrimaryStat state.
 */
public class StatsPanel extends JPanel implements ReliesOnCharacterData {

    private JTable statsTable;
    private DefaultTableModel tableModel;

    /**
     * Instantiates a StatsPanel.
     */
    public StatsPanel() {
        setLayout(new BorderLayout());

        // Table columns: Name | Total | Base | Modifier
        String[] columnNames = {"Name", "Total", "Base", "Modifier"};
        tableModel = new DefaultTableModel(columnNames, 0);
        statsTable = new JTable(tableModel);

        // Add the table to the panel
        add(new JScrollPane(statsTable), BorderLayout.CENTER);
    }

    /**
     * Updates the state of StatsPanel using the given Character's stats.
     *
     * @param character the character to refer to when updating this class's internal state.
     */
    @Override
    public void updateCharacter(Character character) {
        // Clear existing rows in the table.
        tableModel.setRowCount(0);

        // Populate the table with the character's primary stats.
        List<Stat> stats = character.getStat().all();
        for (Stat stat : stats) {
            Object[] rowData = {
                    stat.getName(),
                    stat.getTotal(),
                    stat.getBase().current(),
                    stat.getModifier().current()
            };
            tableModel.addRow(rowData);
        }
    }
}
