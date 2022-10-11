package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class SetDeadlineCommandTest {

    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";

    private static final int FIRST_TASK = 1;
    private static final int SECOND_TASK = 2;
    private static final LocalDate FIRST_DEADLINE =  LocalDate.of(2023, 1, 8);
    private static final LocalDate SECOND_DEADLINE =  LocalDate.of(2023, 2, 9);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
        int outOfBoundTaskIndex = model.getTeam().getTaskList().size();
        SetDeadlineCommand setDeadlineCommand = new SetDeadlineCommand(outOfBoundTaskIndex, FIRST_DEADLINE);

        assertCommandFailure(setDeadlineCommand, model,
                String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, outOfBoundTaskIndex + 1));
    }

    @Test
    public void equals() {
        SetDeadlineCommand SetDeadlineFirstCommand = new SetDeadlineCommand(FIRST_TASK, FIRST_DEADLINE);
        SetDeadlineCommand SetDeadlineSecondCommand = new SetDeadlineCommand(FIRST_TASK, SECOND_DEADLINE);
        SetDeadlineCommand SetDeadlineThirdCommand = new SetDeadlineCommand(SECOND_TASK, FIRST_DEADLINE);

        // Same Set Deadline Commands should be equal.
        assertTrue(SetDeadlineFirstCommand.equals(SetDeadlineFirstCommand));

        // Set Deadline commands with same task but different deadlines should not be considered equal.
        assertFalse(SetDeadlineFirstCommand.equals(SetDeadlineSecondCommand));

        // Set Deadline commands with same deadline but different tasks should not be considered equal.
        assertFalse(SetDeadlineFirstCommand.equals(SetDeadlineThirdCommand));

        // Set Deadline commands with wrong type should return false
        assertFalse(SetDeadlineFirstCommand.equals(2));

        // Set deadline command and null are not considered equal.
        assertFalse(SetDeadlineSecondCommand.equals(null));
    }
}