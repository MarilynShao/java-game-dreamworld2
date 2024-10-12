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

    /**
     * The text field where the user can input their search query.
     */
    private final JTextField searchField;
    /**
     * The list that displays the searchable entries.
     */
    private final JList<String> entryList;
    /**
     * The model that holds the data for the entry list.
     */
    private final DefaultListModel<String> listModel;
    /**
     * The button used to confirm the selection of an entry.
     */
    private final JButton pickButton;
    /**
     * A list holding the current entries available in the search panel.
     */
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

    /**
     * Abstract class that listens for changes in the text field's
     * document and triggers the {@code onUpdate} method.
     * This class is used for handling updates to the search field.
     */
    private abstract static class DocumentAdapter implements javax.swing.event.DocumentListener {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            onUpdate();
        }

        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            onUpdate();
        }

        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            onUpdate();
        }

        /**
         * Method to be implemented by the subclass to define what
         * happens when the document is updated.
         */
        public abstract void onUpdate();
    }
}

