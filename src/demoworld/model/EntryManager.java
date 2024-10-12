package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of Named entries such as Features or Specialties.
 *
 * @param <T> The Named entry type the manager is responsible for.
 */
public class EntryManager<T extends Named> {

    /**
     * A list that holds the entries managed by this EntryManager.
     * Each entry is of type {@code T}, where {@code T} represents
     * a specific type of entry (e.g., Stat, Feature, etc.).
     */
    private List<T> entries;

    /**
     * Constructs a new EntryManager instance.
     */
    public EntryManager() {
        this.entries = new ArrayList<>();
    }

    /**
     * Adds a Named entry.
     *
     * @param entry The Named entry to add.
     */
    public void add(T entry) {
        entries.add(entry);
    }

    /**
     * Searches for any entry with a matching name and returns it, otherwise throws an
     * IllegalStateException due to the program being in a IllegalState where systems
     * are requesting entries that do not exist.
     *
     * @param name The name of the entry to search for.
     * @return The Named entry with a matching name.
     * @throws IllegalStateException If no entry with the given name exists.
     */
    public T byName(String name) throws IllegalStateException {
        return entries.stream()
                .filter(entry -> entry.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(()
                        -> new IllegalStateException("Entry with name "
                        + name + " does not exist!"));
    }

    /**
     * Removes a given entry from the collection.
     *
     * @param entry The Named entry to remove.
     */
    public void remove(T entry) {
        entries.remove(entry);
    }

    /**
     * Returns a newly instantiated list holding all the entries from this manager.
     *
     * @return a newly instantiated ArrayList holding all the entries from this manager.
     */
    public List<T> all() {
        return new ArrayList<>(entries);
    }

    /**
     * Returns a string representation of the EntryManager's internal state.
     *
     * @return A string representation of the entries managed by this manager.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T entry : entries) {
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString();
    }
}

