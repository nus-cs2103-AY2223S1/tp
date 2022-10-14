package soconnect.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static soconnect.testutil.TypicalPersons.ALICE;
import static soconnect.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import soconnect.model.person.UniquePersonList;

public class AutocompleteManagerTest {

    private UniquePersonList uniquePersonList = new UniquePersonList();

    private AutocompleteManager autocompleteManager = new AutocompleteManager(uniquePersonList.getUniqueNames());

    @Test
    public void updateUniqueNames_addedNewPerson_success() {
        uniquePersonList.add(ALICE);
        autocompleteManager.updateUniqueNames(uniquePersonList.getUniqueNames());
        AutocompleteManager expectedAutocompleteManager = new AutocompleteManager(uniquePersonList.getUniqueNames());
        assertEquals(expectedAutocompleteManager, autocompleteManager);
    }

    @Test
    public void getAutocompleteEntries_withMatchingSearchValue_success() {
        String searchValue = "A";
        TreeSet<String> uniqueNames = new TreeSet<>();
        uniqueNames.addAll(Arrays.asList("Alice", "Bob"));
        autocompleteManager.updateUniqueNames(uniqueNames);
        List<String> searchResult = autocompleteManager.getAutocompleteEntries(searchValue);
        List<String> expectedSearchResult = Arrays.asList("Alice");
        assertEquals(expectedSearchResult, searchResult);
    }

    @Test
    public void getAutocompleteEntries_withMultipleMatchingSearchValueInSortedOrder_success() {
        String searchValue = "A";
        TreeSet<String> uniqueNames = new TreeSet<>();
        uniqueNames.addAll(Arrays.asList("Alice", "Alvin", "Alex", "Bob"));
        autocompleteManager.updateUniqueNames(uniqueNames);
        List<String> searchResult = autocompleteManager.getAutocompleteEntries(searchValue);
        List<String> expectedSearchResult = Arrays.asList("Alex", "Alice", "Alvin");
        assertEquals(expectedSearchResult, searchResult);
    }

    @Test
    public void getAutocompleteEntries_withCaseInsensitiveMatchingSearchValue_success() {
        String searchValue = "AL";
        TreeSet<String> uniqueNames = new TreeSet<>();
        uniqueNames.addAll(Arrays.asList("Alice", "aLvin", "alex", "Bob"));
        autocompleteManager.updateUniqueNames(uniqueNames);
        List<String> searchResult = autocompleteManager.getAutocompleteEntries(searchValue);
        List<String> expectedSearchResult = Arrays.asList("Alice", "aLvin", "alex");
        assertEquals(expectedSearchResult, searchResult);
    }

    @Test
    public void getAutocompleteEntries_withNoMatchingSearchValue_success() {
        String searchValue = "A";
        TreeSet<String> uniqueNames = new TreeSet<>();
        uniqueNames.addAll(Arrays.asList("Bob", "Carl"));
        autocompleteManager.updateUniqueNames(uniqueNames);
        List<String> searchResult = autocompleteManager.getAutocompleteEntries(searchValue);
        List<String> expectedSearchResult = new ArrayList<>();
        assertEquals(expectedSearchResult, searchResult);
    }

    @Test
    public void getAutocompleteEntries_withEmptySearchValue_success() {
        String searchValue = "";
        TreeSet<String> uniqueNames = new TreeSet<>();
        uniqueNames.addAll(Arrays.asList("Bob", "Carl"));
        autocompleteManager.updateUniqueNames(uniqueNames);
        List<String> searchResult = autocompleteManager.getAutocompleteEntries(searchValue);
        List<String> expectedSearchResult = new ArrayList<>();
        assertEquals(expectedSearchResult, searchResult);
    }

    @Test
    public void equals() {
        UniquePersonList uniquePersonList = new UniquePersonList();
        uniquePersonList.add(ALICE);
        TreeSet<String> uniqueNames = uniquePersonList.getUniqueNames();

        // same uniqueNames -> returns true
        autocompleteManager = new AutocompleteManager(uniqueNames);
        AutocompleteManager autocompleteManagerCopy = new AutocompleteManager(uniqueNames);
        assertTrue(autocompleteManager.equals(autocompleteManagerCopy));

        // same object -> returns true
        assertTrue(autocompleteManager.equals(autocompleteManager));

        // null -> returns false
        assertFalse(autocompleteManager.equals(null));

        // different uniqueNames -> false
        uniquePersonList.add(BOB);
        TreeSet<String> differentUniqueNames = uniquePersonList.getUniqueNames();
        assertFalse(autocompleteManager.equals(new AutocompleteManager(differentUniqueNames)));
    }
}
