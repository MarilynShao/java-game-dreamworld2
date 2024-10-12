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

    /**
     * The table that displays the character's stats.
     * A {@code JTable} used to visually represent the character's stats,
     * such as total, base, and modifier values.
     */
    private JTable statsTable;

    /**
     * The model that holds the data for the stats table.
     * A {@code DefaultTableModel} that manages the data shown in the {@code statsTable},
     * including columns for name, total, base, and modifier values.
     */
    private DefaultTableModel tableModel;


    /**
     * Instantiates a StatsPanel.
     */
    public StatsPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Total", "Base", "Modifier"};
        tableModel = new DefaultTableModel(columnNames, 0);
        statsTable = new JTable(tableModel);

        statsTable.setFillsViewportHeight(true);

        add(statsTable.getTableHeader(), BorderLayout.NORTH);
        add(statsTable, BorderLayout.CENTER);
    }

    /**
     * Updates the state of StatsPanel using the given Character's stats.
     *
     * @param character the character to refer to when updating this class's internal state.
     */
    @Override
    public void updateCharacter(Character character) {
        tableModel.setRowCount(0);

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
