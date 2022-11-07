package seedu.application.logic.commands;

import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.StatsCommand.MESSAGE_SUCCESS;
import static seedu.application.testutil.TypicalApplications.getApplicationBookWithOneApplication;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

public class StatsCommandTest {
    private static final int ZERO_COUNT_APPLICATION = 0;
    private static final int ONE_COUNT_APPLICATION = 1;
    private Model emptyModel = new ModelManager();
    private Model expectedEmptyModel = new ModelManager();
    private Model model = new ModelManager(getApplicationBookWithOneApplication(), new UserPrefs());
    private Model expectedModel = new ModelManager(getApplicationBookWithOneApplication(), new UserPrefs());

    @Test
    public void execute_emptyModel_countSuccess() {
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, ZERO_COUNT_APPLICATION,
                ZERO_COUNT_APPLICATION, ZERO_COUNT_APPLICATION, ZERO_COUNT_APPLICATION, ZERO_COUNT_APPLICATION,
                ZERO_COUNT_APPLICATION));
        assertCommandSuccess(new StatsCommand(), emptyModel, expectedCommandResult, expectedEmptyModel);
    }

    @Test
    public void execute_modelWithOneApplication_countSuccess() {
        //Manually count.
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, ONE_COUNT_APPLICATION,
                ONE_COUNT_APPLICATION, ZERO_COUNT_APPLICATION, ZERO_COUNT_APPLICATION, ONE_COUNT_APPLICATION,
                ZERO_COUNT_APPLICATION));
        assertCommandSuccess(new StatsCommand(), model, expectedCommandResult, expectedModel);
    }
}
