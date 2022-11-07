package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.showResidentAtIndex;
import static seedu.rc4hdb.logic.commands.residentcommands.ListCommand.LIST_MESSAGE_SUCCESS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALL_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALSO_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getResidentBook(), new VenueBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, LIST_MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);
        assertCommandSuccess(new ListCommand(), model, LIST_MESSAGE_SUCCESS, expectedModel);
    }

    // Default list with no specifier returns expected model
    @Test
    public void execute_defaultListCommand_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(ALL_VALID_FIELDS);
        expectedModel.setVisibleFields(ALL_VALID_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ListCommand(), model, LIST_MESSAGE_SUCCESS, expectedModel);
    }

    // List is idempotent, i.e. calling list on separate table states will result in the same expected model
    @Test
    public void execute_listCommandOnSeparateModels_returnsSameModel() {
        List<String> alreadyHiddenFields = ColumnManipulatorCommand.generateComplementListFrom(
                ALL_VALID_FIELDS);
        expectedModel.setVisibleFields(ALL_VALID_FIELDS);
        expectedModel.setHiddenFields(alreadyHiddenFields);

        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(ALSO_VALID_FIELDS);
        model.setVisibleFields(ALSO_VALID_FIELDS);
        model.setHiddenFields(fieldsToHide);

        assertCommandSuccess(new ListCommand(), model,
                LIST_MESSAGE_SUCCESS, expectedModel);

        // Simulate HideOnlyCommand being invoked
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(
                HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND);

        assertCommandSuccess(new ListCommand(), model,
                LIST_MESSAGE_SUCCESS, expectedModel);
    }
}
