package soconnect.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.testutil.TypicalPersons.ALICE;
import static soconnect.testutil.TypicalPersons.BENSON;
import static soconnect.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import soconnect.model.SoConnect;
import soconnect.testutil.SoConnectBuilder;

public class AutocompleteManagerTest {

    private SoConnect soConnect = new SoConnectBuilder().withPerson(ALICE).build();

    private AutocompleteManager autocompleteManager = new AutocompleteManager(soConnect);

    @Test
    public void updateSoConnect_addedNewPerson_success() {
        soConnect.addPerson(BOB);
        autocompleteManager.updateSoConnect(soConnect);
        AutocompleteManager expectedAutocompleteManager = new AutocompleteManager(soConnect);
        assertEquals(autocompleteManager, expectedAutocompleteManager);
    }

    @Test
    public void getSearchCommandArguments_validSearchCommandFormat_success() {
        String validSearchCommandFormat = "search and n/Alice";
        String searchCommandArguments = autocompleteManager.getSearchCommandArguments(validSearchCommandFormat);
        String expectedSearchCommandArguments = " and n/Alice";
        assertEquals(searchCommandArguments, expectedSearchCommandArguments);
    }

    @Test
    public void getSearchCommandArguments_missingArgument_returnInvalidSearchCommandArgument() {
        String invalidSearchCommandFormat = "search ";
        String searchCommandArguments = autocompleteManager.getSearchCommandArguments(invalidSearchCommandFormat);
        assertEquals(searchCommandArguments, AutocompleteManager.INVALID_SEARCH_COMMAND_ARGUMENT);
    }

    @Test
    public void getSearchCommandArguments_invalidCommandWord_returnInvalidSearchCommandArgument() {
        String invalidCommandWord = "searches and n/Alice";
        String searchCommandArguments = autocompleteManager.getSearchCommandArguments(invalidCommandWord);
        assertEquals(searchCommandArguments, AutocompleteManager.INVALID_SEARCH_COMMAND_ARGUMENT);
    }

    @Test
    public void getLastPrefixArgument_success() {
        String argsString = "and n/Alice p/12345678";
        String lastPrefixArgument = autocompleteManager.getLastPrefixArgument(argsString, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        String expectedLastPrefixArgument = "p/12345678";
        assertEquals(lastPrefixArgument, expectedLastPrefixArgument);
    }

    @Test
    public void getLastPrefixArgument_emptyArgsString_returnInvalidPrefixArgument() {
        String argsString = "";
        String lastPrefixArgument = autocompleteManager.getLastPrefixArgument(argsString, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        assertEquals(lastPrefixArgument, AutocompleteManager.INVALID_PREFIX_ARGUMENT);
    }

    @Test
    public void getLastPrefixArgument_missingArgument_returnInvalidPrefixArgument() {
        String argsString = "and p/12313133 n/";
        String lastPrefixArgument = autocompleteManager.getLastPrefixArgument(argsString, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        assertEquals(lastPrefixArgument, AutocompleteManager.INVALID_PREFIX_ARGUMENT);
    }

    @Test
    public void getLastPrefixArgument_noPrefixArgument_returnInvalidPrefixArgument() {
        String argsString = "and ";
        String lastPrefixArgument = autocompleteManager.getLastPrefixArgument(argsString, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        assertEquals(lastPrefixArgument, AutocompleteManager.INVALID_PREFIX_ARGUMENT);
    }

    @Test
    public void equals() {
        SoConnect soConnect = new SoConnectBuilder().build();

        // same soConnect -> returns true
        autocompleteManager = new AutocompleteManager(soConnect);
        AutocompleteManager autocompleteManagerCopy = new AutocompleteManager(soConnect);
        assertTrue(autocompleteManager.equals(autocompleteManagerCopy));

        // same object -> returns true
        assertTrue(autocompleteManager.equals(autocompleteManager));

        // null -> returns false
        assertFalse(autocompleteManager.equals(null));

        // different SoConnect but has same person list -> true
        soConnect.addPerson(ALICE);
        SoConnect differentSoConnect = new SoConnectBuilder().withPerson(ALICE).build();
        assertTrue(autocompleteManager.equals(new AutocompleteManager(differentSoConnect)));

        // different SoConnect and different person list -> false
        soConnect.addPerson(BOB);
        differentSoConnect.addPerson(BENSON);
        assertFalse(autocompleteManager.equals(new AutocompleteManager(differentSoConnect)));
    }
}
