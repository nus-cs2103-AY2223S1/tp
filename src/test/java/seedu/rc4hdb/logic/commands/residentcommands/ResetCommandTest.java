package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.MESSAGE_SUCCESS_FORMAT;
import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.generateComplementListFrom;
import static seedu.rc4hdb.logic.commands.residentcommands.ResetCommand.COMMAND_PAST_TENSE;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;

/**
 * Unit tests for {@link ResetCommand}.
 */
public class ResetCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.setResidentBook(getTypicalResidentBook());
        expectedModel = new ModelManager();
        expectedModel.setResidentBook(model.getResidentBook());
    }

    @Test
    public void execute_resetCommandOnDefaultModel_returnsExpectedModel() {
        assertCommandSuccess(new ResetCommand(), model,
                String.format(MESSAGE_SUCCESS_FORMAT, COMMAND_PAST_TENSE), expectedModel);
    }

    @Test
    public void execute_resetCommandOnModelWithPartiallyVisibleFields_returnsExpectedModel() {
        List<String> fieldsToHide = generateComplementListFrom(VALID_FIELDS);
        model.setVisibleFields(VALID_FIELDS);
        model.setHiddenFields(fieldsToHide);
        assertCommandSuccess(new ResetCommand(), model,
                String.format(MESSAGE_SUCCESS_FORMAT, COMMAND_PAST_TENSE), expectedModel);
    }
}
