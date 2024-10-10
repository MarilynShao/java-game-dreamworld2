package demoworld.view.sheetpanels;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * EntryList is a JPanel that contains a list of selectable entries.
 * The class allows adding listeners for when items are selected or removed.
 */
public class EntryList extends JPanel {

    private final JList<String> entryList;
    private final DefaultListModel<String> listModel;
    private final JButton removeButton;

    /**
     * Instantiates EntryList with the given label.
     *
     * @param label the label for this entry list
     */
    public EntryList(String label) {
        listModel = new DefaultListModel<>();
        entryList = new JList<>(listModel);
        removeButton = new JButton("Remove");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel(label));
        add(new JScrollPane(entryList));
        add(removeButton);
    }

    /**
     * Adds an ActionListener for when a selection in JList is clicked on.
     *
     * @param listener the ActionListener you want to fire when a selection is clicked on
     */
    public void addSelectionListener(ActionListener listener) {
        entryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listener.actionPerformed(null);
            }
        });
    }

    /**
     * Adds a given ActionListener to the remove button.
     *
     * @param listener the ActionListener to attach to the remove button
     */
    public void addRemoveListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    /**
     * Returns the DefaultListModel used for the JList.
     *
     * @return the given DefaultListModel
     */
    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    /**
     * Returns the JList used by the EntryList.
     *
     * @return the JList used by the EntryList
     */
    public JList<String> getList() {
        return entryList;
    }

    /**
     * Returns the String value of the currently selected entry.
     *
     * @return the String value of the currently selected entry
     */
    public String getSelectedEntry() {
        return entryList.getSelectedValue();
    }
}
