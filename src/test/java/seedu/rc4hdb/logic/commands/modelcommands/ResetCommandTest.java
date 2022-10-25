package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static seedu.rc4hdb.logic.commands.modelcommands.ColumnManipulatorCommand.MESSAGE_SUCCESS_FORMAT;
import static seedu.rc4hdb.logic.commands.modelcommands.ColumnManipulatorCommand.generateComplementListFrom;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.modelcommands.ResetCommand.COMMAND_PAST_TENSE;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

public class ResetCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
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
