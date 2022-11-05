package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

// TODO: Add implementation for tests
class SetDeadlineCommandTest {

    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";

    private static final int FIRST_TASK = 1;
    private static final int SECOND_TASK = 2;
    private static final LocalDateTime FIRST_DEADLINE = LocalDateTime.of(2023, 1, 8, 23, 59);
    private static final LocalDateTime SECOND_DEADLINE = LocalDateTime.of(2023, 2, 9, 23, 59);

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
    }

    @Test
    public void equals() {
    }
}
