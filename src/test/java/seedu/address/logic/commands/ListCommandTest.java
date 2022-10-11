package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_noFiltersApplied_showsEverything() {
        new ListCommand(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()).execute(expectedModel);
        assert(model.equals(expectedModel));
        //assertCommandSuccess(new ListCommand(emptyOptional, emptyOptional, emptyOptional, emptyOptional),
        //        model, String.format(ListCommand.MESSAGE_SUCCESS, "NIL", "NIL", "NIL", "NIL"), expectedModel);
    }

    /*
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()),
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()),
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

     */
}
