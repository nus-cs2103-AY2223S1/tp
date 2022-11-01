package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.RemindCommand.SHOWING_REMIND_MESSAGE;
import static seedu.application.testutil.TypicalApplications.WISE;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
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
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_noUpcomingInterviews() {
        UpcomingInterviewPredicateStub predicate = new UpcomingInterviewPredicateStub();
        CommandResult expectedCommandResult = new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
        RemindCommandStub command = new RemindCommandStub();
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getApplicationsWithUpcomingInterviewList());
    }

    @Test
    public void execute_oneUpcomingInterview() {
        Model localModel = new ModelManager(getTypicalApplicationBookWithUpcomingInterview(), new UserPrefs());
        Model localExpectedModel = new ModelManager(getTypicalApplicationBookWithUpcomingInterview(), new UserPrefs());

        UpcomingInterviewPredicateStub predicate = new UpcomingInterviewPredicateStub();
        CommandResult expectedCommandResult = new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
        RemindCommandStub command = new RemindCommandStub();
        assertCommandSuccess(command, localModel, expectedCommandResult, localExpectedModel);
        assertEquals(Arrays.asList(WISE), localModel.getApplicationsWithUpcomingInterviewList());
    }

}
