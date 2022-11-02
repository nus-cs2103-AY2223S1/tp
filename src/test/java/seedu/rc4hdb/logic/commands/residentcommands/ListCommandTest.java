package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.showResidentAtIndex;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.AT_LEAST_ONE_VISIBLE_COLUMN;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.INVALID_FIELDS_ENTERED;
import static seedu.rc4hdb.logic.commands.residentcommands.ListCommand.LIST_MESSAGE_SUCCESS;
import static seedu.rc4hdb.logic.commands.residentcommands.ListCommand.LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALL_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALSO_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.DUPLICATE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND;
import static seedu.rc4hdb.testutil.TypicalFieldLists.INVALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.MIXED_CASE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_FIELDS;
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
    public void execute_defaultListCommandWithNoSpecifier_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(ALL_VALID_FIELDS);
        System.out.println(fieldsToHide);
        expectedModel.setVisibleFields(ALL_VALID_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ListCommand(), model, LIST_MESSAGE_SUCCESS, expectedModel);
    }

    // List command with specifier (that invokes separate constructor) returns expected model
    @Test
    public void execute_listCommandWithSpecifier_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        expectedModel.setVisibleFields(VALID_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ListCommand(VALID_FIELDS, fieldsToHide), model,
                LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN, expectedModel);
    }

    // Duplicate field names/letters returns expected model
    @Test
    public void execute_listCommandWithDuplicateFields_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(DUPLICATE_FIELDS);
        expectedModel.setVisibleFields(DUPLICATE_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ListCommand(DUPLICATE_FIELDS, fieldsToHide), model,
                LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN, expectedModel);
    }

    // Case-insensitive field names/letters return expected model
    @Test
    public void execute_listCommandWithMixedCaseFields_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        expectedModel.setVisibleFields(VALID_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ListCommand(MIXED_CASE_FIELDS, fieldsToHide), model,
                LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN, expectedModel);
    }

    // Negative test case (invalid field name/letter) throws exception
    @Test
    public void execute_listCommandWithInvalidFields_throwsCommandException() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(INVALID_FIELDS);
        assertCommandFailure(new ListCommand(INVALID_FIELDS, fieldsToHide), model,
                String.format(INVALID_FIELDS_ENTERED));
    }

    // Negative test case (showing no fields) throws exception
    @Test
    public void execute_listCommandToShowNoColumns_throwsCommandException() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(ALL_VALID_FIELDS);
        assertCommandFailure(new ListCommand(fieldsToShow, ALL_VALID_FIELDS), model,
                String.format(AT_LEAST_ONE_VISIBLE_COLUMN));
    }

    // Repeated list is idempotent, i.e. calling list on separate table states will result in the same model
    @Test
    public void execute_listCommandOnSeparateModels_returnsSameModel() {
        List<String> alreadyHiddenFields = ColumnManipulatorCommand.generateComplementListFrom(
                VALID_FIELDS);
        expectedModel.setVisibleFields(VALID_FIELDS);
        expectedModel.setHiddenFields(alreadyHiddenFields);

        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(ALSO_VALID_FIELDS);
        model.setVisibleFields(ALSO_VALID_FIELDS);
        model.setHiddenFields(fieldsToHide);

        assertCommandSuccess(new ListCommand(VALID_FIELDS, alreadyHiddenFields), model,
                LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN, expectedModel);

        // Simulate HideOnlyCommand being invoked
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(
                HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND);

        assertCommandSuccess(new ListCommand(VALID_FIELDS, alreadyHiddenFields), model,
                LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN, expectedModel);
    }
}
