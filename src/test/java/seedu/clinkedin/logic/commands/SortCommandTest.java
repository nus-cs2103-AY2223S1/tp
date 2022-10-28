package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;

public class SortCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();


    @Test
    public void execute_sort_success() {
        CommandResult expectedCommandResult = new CommandResult("Sorted by rating!");
        assertCommandSuccess(new SortCommand(), model, expectedCommandResult, expectedModel);
    }
}
