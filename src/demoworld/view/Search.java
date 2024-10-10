package demoworld.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Manages presenting a searchable wrapper around a JList that can be searched using a JTextField
 * and a JButton for "picking" a selected option.
 */
public class Search extends JPanel {

    private final JTextField searchField;
    private final JList<String> entryList;
    private final DefaultListModel<String> listModel;
    private final JButton pickButton;
    private List<String> entries;

    /**
     * Instantiates a new Search Panel.
     */
    public Search() {
        setLayout(new BorderLayout());

        searchField = new JTextField();
        listModel = new DefaultListModel<>();
        entryList = new JList<>(listModel);
        pickButton = new JButton("Pick");

        add(searchField, BorderLayout.NORTH);
        add(new JScrollPane(entryList), BorderLayout.CENTER);
        add(pickButton, BorderLayout.SOUTH);

        searchField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void onUpdate() {
                filterEntryList();
            }
        });
    }

    /**
     * Updates the list with new entries.
     *
     * @param entries A list of entries to populate the JList with.
     */
    public void updateList(List<String> entries) {
        this.entries = entries;
        listModel.clear();
        for (String entry : entries) {
            listModel.addElement(entry);
        }
    }

    /**
     * Returns the currently selected entry in the JList.
     *
     * @return The selected entry's string value, or null if nothing is selected.
     */
    public String getSelectedEntry() {
        return entryList.getSelectedValue();
    }

    /**
     * Adds an ActionListener to the pick button.
     *
     * @param listener The ActionListener to be attached to the pick button.
     */
    public void addPickListener(ActionListener listener) {
        pickButton.addActionListener(listener);
    }

    /**
     * Removes all listeners from the pick button.
     */
    public void clearPickListeners() {
        for (ActionListener listener : pickButton.getActionListeners()) {
            pickButton.removeActionListener(listener);
        }
    }

    /**
     * Filters the JList based on the search field's input.
     */
    public void filterEntryList() {
        String filter = searchField.getText().toLowerCase();
        listModel.clear();
        for (String entry : entries) {
            if (entry.toLowerCase().contains(filter)) {
                listModel.addElement(entry);
            }
        }
    }

    // Custom document listener for search field updates
    private abstract static class DocumentAdapter implements javax.swing.event.DocumentListener {
        public void insertUpdate(javax.swing.event.DocumentEvent e) { onUpdate(); }
        public void removeUpdate(javax.swing.event.DocumentEvent e) { onUpdate(); }
        public void changedUpdate(javax.swing.event.DocumentEvent e) { onUpdate(); }
        public abstract void onUpdate();
    }
}

