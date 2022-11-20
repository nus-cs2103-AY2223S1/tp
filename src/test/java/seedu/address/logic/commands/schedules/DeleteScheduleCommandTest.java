package seedu.address.logic.commands.schedules;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.getTypicalProfNusWithSchedules;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedule.DeleteScheduleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.schedule.Schedule;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteScheduleCommand.
 */
public class DeleteScheduleCommandTest {

    private Model model = new ModelManager(getTypicalProfNusWithSchedules(), new UserPrefs());

    @Test
    public void execute_invalidIndex_throwCommandException() {
        List<Schedule> schedules = model.getFilteredScheduleList();
        Index outOfBound = Index.fromOneBased(schedules.size() + 1);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(outOfBound);
        assertThrows(CommandException.class, () -> deleteScheduleCommand.execute(model));
    }
}
