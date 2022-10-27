package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.AT_LEAST_ONE_VISIBLE_COLUMN;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.INVALID_FIELDS_ENTERED;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.INVALID_SUBSET;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW;
import static seedu.rc4hdb.logic.commands.residentcommands.HideOnlyCommand.COMMAND_PAST_TENSE;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALL_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.DUPLICATE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND;
import static seedu.rc4hdb.testutil.TypicalFieldLists.INVALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.INVALID_SUBSEQUENT_FIELDS_FOR_HIDE;
import static seedu.rc4hdb.testutil.TypicalFieldLists.MIXED_CASE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_SUBSEQUENT_FIELDS_FOR_HIDE;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;

/**
 * Unit tests for {@link HideOnlyCommand}.
 */
public class HideOnlyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.setResidentBook(getTypicalResidentBook());
        expectedModel = new ModelManager();
        expectedModel.setResidentBook(model.getResidentBook());
    }

    // Positive test case returns expected model
    @Test
    public void execute_validHideCommand_returnsExpectedModel() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        expectedModel.setHiddenFields(VALID_FIELDS);
        expectedModel.setVisibleFields(fieldsToShow);
        assertCommandSuccess(new HideOnlyCommand(fieldsToShow, VALID_FIELDS), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Duplicate field names/letters returns expected model
    @Test
    public void execute_hideCommandWithDuplicateFields_returnsExpectedModel() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(DUPLICATE_FIELDS);
        expectedModel.setHiddenFields(DUPLICATE_FIELDS);
        expectedModel.setVisibleFields(fieldsToShow);
        assertCommandSuccess(new HideOnlyCommand(fieldsToShow, DUPLICATE_FIELDS), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Case-insensitive field names/letters return expected model
    @Test
    public void execute_hideCommandWithMixedCaseFields_returnsExpectedModel() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        expectedModel.setHiddenFields(VALID_FIELDS);
        expectedModel.setVisibleFields(fieldsToShow);
        assertCommandSuccess(new HideOnlyCommand(fieldsToShow, MIXED_CASE_FIELDS), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Negative test case (invalid field name/letter) throws exception
    @Test
    public void execute_hideCommandWithInvalidFields_throwsCommandException() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(INVALID_FIELDS);
        assertCommandFailure(new HideOnlyCommand(fieldsToShow, INVALID_FIELDS), model,
                String.format(INVALID_FIELDS_ENTERED));
    }

    // Negative test case (hiding all fields) throws exception
    @Test
    public void execute_hideCommandToHideAllColumns_throwsCommandException() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(ALL_VALID_FIELDS);
        assertCommandFailure(new HideOnlyCommand(fieldsToShow, ALL_VALID_FIELDS), model,
                String.format(AT_LEAST_ONE_VISIBLE_COLUMN));
    }

    // Valid hide after initial hide returns expected model
    @Test
    public void execute_validHideCommandAfterInitialHideCommand_returnsExpectedModel() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(VALID_FIELDS);

        List<String> alreadyVisibleFields = ColumnManipulatorCommand.generateComplementListFrom(
                HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND);
        expectedModel.setVisibleFields(alreadyVisibleFields);
        expectedModel.setHiddenFields(HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND);

        List<String> complementList = ColumnManipulatorCommand.generateComplementListFrom(
                VALID_SUBSEQUENT_FIELDS_FOR_HIDE);
        assertCommandSuccess(new HideOnlyCommand(complementList, VALID_SUBSEQUENT_FIELDS_FOR_HIDE), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Invalid hide after initial hide throws exception
    @Test
    public void execute_invalidHideCommandAfterInitialHideCommand_throwsCommandException() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(VALID_FIELDS);

        List<String> complementList = ColumnManipulatorCommand.generateComplementListFrom(
                INVALID_SUBSEQUENT_FIELDS_FOR_HIDE);
        assertCommandFailure(new HideOnlyCommand(complementList, INVALID_SUBSEQUENT_FIELDS_FOR_HIDE),
                model, INVALID_SUBSET);
    }
}
