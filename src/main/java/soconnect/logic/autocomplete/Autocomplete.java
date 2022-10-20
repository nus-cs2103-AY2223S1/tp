package soconnect.logic.autocomplete;

import java.util.List;
import java.util.TreeSet;

/**
 * API of Autocomplete component.
 */
public interface Autocomplete {
    /**
     * Current autocomplete only supports the find command.
     */
    String AUTOCOMPLETE_COMMAND_WORD = "find ";

    /**
     * Updates the set of unique names whenever a command is executed.
     *
     * @param uniqueNames The updated set of unique names.
     */
    void updateUniqueNames(TreeSet<String> uniqueNames);

    /**
     * Generates a list of autocomplete entries that starts with the given searchValue.
     *
     * @param searchValue The value to be matched with.
     * @return A list of matching autocomplete entries.
     */
    List<String> getAutocompleteEntries(String searchValue);
}
