package soconnect.logic.autocomplete;

import java.util.List;
import soconnect.model.ReadOnlySoConnect;

/**
 * API of Autocomplete component.
 */
public interface Autocomplete {
    /**
     * Updates the soConnect data whenever a command is executed.
     *
     * @param soConnect The updated soConnect data.
     */
    void updateSoConnect(ReadOnlySoConnect soConnect);

    /**
     * Generates a list of autocomplete entries that starts with the given searchValue.
     *
     * @param searchValue The value to be matched with.
     * @return A list of matching autocomplete entries.
     */
    List<String> getAutocompleteEntries(String searchValue);

    String getSearchCommandArguments(String userInput);
}
