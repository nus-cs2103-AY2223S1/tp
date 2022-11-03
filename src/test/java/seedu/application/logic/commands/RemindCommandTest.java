package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.RemindCommand.SHOWING_REMIND_MESSAGE;
import static seedu.application.testutil.TypicalApplications.WISE;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBookWithNoUpcomingInterview;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBookWithUpcomingInterview;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class RemindCommandTest {

    @Test
    public void execute_noInterviewInApplications() {
        Model localModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        Model localExpectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
        RemindCommand command = new RemindCommand();
        assertCommandSuccess(command, localModel, expectedCommandResult, localExpectedModel);
        assertEquals(Collections.emptyList(), localModel.getApplicationsWithUpcomingInterviewList());
    }

    @Test
    public void execute_noUpcomingInterviews() {
        Model localModel = new ModelManager(getTypicalApplicationBookWithNoUpcomingInterview(), new UserPrefs());
        Model localExpectedModel = new ModelManager(getTypicalApplicationBookWithNoUpcomingInterview(),
                new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
        RemindCommand command = new RemindCommand();
        assertCommandSuccess(command, localModel, expectedCommandResult, localExpectedModel);
        assertEquals(Collections.emptyList(), localModel.getApplicationsWithUpcomingInterviewList());
    }

    @Test
    public void execute_oneUpcomingInterviews() {
        Model localModel = new ModelManager(getTypicalApplicationBookWithUpcomingInterview(), new UserPrefs());
        Model localExpectedModel = new ModelManager(getTypicalApplicationBookWithUpcomingInterview(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
        RemindCommand command = new RemindCommand();
        assertCommandSuccess(command, localModel, expectedCommandResult, localExpectedModel);
        assertEquals(Arrays.asList(WISE), localModel.getApplicationsWithUpcomingInterviewList());
    }

}
