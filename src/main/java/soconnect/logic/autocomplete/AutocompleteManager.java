package soconnect.logic.autocomplete;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import soconnect.commons.core.LogsCenter;

/**
 * Manager of the autocomplete component.
 */
public class AutocompleteManager implements Autocomplete {
    private static final Logger logger = LogsCenter.getLogger(AutocompleteManager.class);

    private SortedSet<String> uniqueNames;

    /**
     * Constructs a {@code AutocompleteManager} with the given uniqueNames.
     *
     * @param uniqueNames A set of unique names in the SoConnect.
     */
    public AutocompleteManager(TreeSet<String> uniqueNames) {
        this.uniqueNames = uniqueNames;
    }

    @Override
    public void updateUniqueNames(TreeSet<String> uniqueNames) {
        this.uniqueNames = uniqueNames;
        logger.info("Updated unique list of names.");
    }

    @Override
    public List<String> getAutocompleteEntries(String searchValue) {
        List<String> searchResult = new LinkedList<>();
        if (searchValue.length() == 0) {
            return searchResult;
        }
        String lowerCaseSearchValue = searchValue.toLowerCase();
        searchResult = uniqueNames.stream()
                .filter(name -> name.toLowerCase().startsWith(lowerCaseSearchValue)).collect(Collectors.toList());
        return searchResult;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AutocompleteManager)) {
            return false;
        }

        // state check
        AutocompleteManager other = (AutocompleteManager) obj;
        return uniqueNames.equals(other.uniqueNames);
    }
}
