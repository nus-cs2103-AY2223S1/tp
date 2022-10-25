package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
    }

    @Test
    public void execute_filteredList_success() {
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
    }

    @Test
    public void equals() {
    }

}
