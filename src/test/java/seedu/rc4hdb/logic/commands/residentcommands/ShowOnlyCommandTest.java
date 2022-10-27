package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.AT_LEAST_ONE_VISIBLE_COLUMN;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.INVALID_FIELDS_ENTERED;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.INVALID_SUBSET;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW;
import static seedu.rc4hdb.logic.commands.residentcommands.ShowOnlyCommand.COMMAND_PAST_TENSE;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALL_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.DUPLICATE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.INVALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.INVALID_SUBSEQUENT_FIELDS_FOR_SHOW;
import static seedu.rc4hdb.testutil.TypicalFieldLists.MIXED_CASE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_SUBSEQUENT_FIELDS_FOR_SHOW;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;

/**
 * Unit tests for {@link ShowOnlyCommand}.
 */
public class ShowOnlyCommandTest {
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
    public void execute_validShowCommand_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        expectedModel.setVisibleFields(VALID_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ShowOnlyCommand(VALID_FIELDS, fieldsToHide), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Duplicate field names/letters returns expected model
    @Test
    public void execute_showCommandWithDuplicateFields_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(DUPLICATE_FIELDS);
        expectedModel.setVisibleFields(DUPLICATE_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ShowOnlyCommand(DUPLICATE_FIELDS, fieldsToHide), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Case-insensitive field names/letters return expected model
    @Test
    public void execute_showCommandWithMixedCaseFields_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        expectedModel.setVisibleFields(VALID_FIELDS);
        expectedModel.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ShowOnlyCommand(MIXED_CASE_FIELDS, fieldsToHide), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Negative test case (invalid field name/letter) throws exception
    @Test
    public void execute_showCommandWithInvalidFields_throwsCommandException() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(INVALID_FIELDS);
        assertCommandFailure(new ShowOnlyCommand(INVALID_FIELDS, fieldsToHide), model,
                String.format(INVALID_FIELDS_ENTERED));
    }

    // Negative test case (showing no fields) throws exception
    @Test
    public void execute_showCommandToShowNoColumns_throwsCommandException() {
        List<String> fieldsToShow = ColumnManipulatorCommand.generateComplementListFrom(ALL_VALID_FIELDS);
        assertCommandFailure(new ShowOnlyCommand(fieldsToShow, ALL_VALID_FIELDS), model,
                String.format(AT_LEAST_ONE_VISIBLE_COLUMN));
    }

    // Valid show after initial show returns expected model
    @Test
    public void execute_validShowCommandAfterInitialShowCommand_returnsExpectedModel() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        model.setVisibleFields(VALID_FIELDS);
        model.setHiddenFields(fieldsToHide);

        List<String> alreadyHiddenFields = ColumnManipulatorCommand.generateComplementListFrom(
                VALID_SUBSEQUENT_FIELDS_FOR_SHOW);
        expectedModel.setVisibleFields(VALID_SUBSEQUENT_FIELDS_FOR_SHOW);
        expectedModel.setHiddenFields(alreadyHiddenFields);

        assertCommandSuccess(new ShowOnlyCommand(VALID_SUBSEQUENT_FIELDS_FOR_SHOW, alreadyHiddenFields), model,
                String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE), expectedModel);
    }

    // Invalid show after initial show throws exception
    @Test
    public void execute_invalidShowCommandAfterInitialShowCommand_throwsCommandException() {
        List<String> fieldsToHide = ColumnManipulatorCommand.generateComplementListFrom(VALID_FIELDS);
        model.setVisibleFields(VALID_FIELDS);
        model.setHiddenFields(fieldsToHide);

        List<String> alreadyHiddenFields = ColumnManipulatorCommand.generateComplementListFrom(
                VALID_SUBSEQUENT_FIELDS_FOR_SHOW);
        assertCommandFailure(new ShowOnlyCommand(INVALID_SUBSEQUENT_FIELDS_FOR_SHOW, alreadyHiddenFields),
                model, INVALID_SUBSET);
    }
}
