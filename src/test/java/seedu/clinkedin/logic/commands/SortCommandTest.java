package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.person.Rating;

public class SortCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();


    @Test
    public void execute_sort_success() {
        CommandResult expectedCommandResult = new CommandResult("Sorted by rating!");
        assertCommandSuccess(new SortCommand(), model, expectedCommandResult, expectedModel);
    }


    @Test
    public void equals_sameObject() {
        SortCommand command1 = new SortCommand();
        assertTrue(command1.equals(command1));

    }

    @Test
    public void equals_diffObjectSameParameters() {
        SortCommand command1 = new SortCommand();
        SortCommand command2 = new SortCommand();
        assertTrue(command1.equals(command2));
    }

    @Test
    public void notEqual_null() {
        SortCommand command1 = new SortCommand();
        assertFalse(command1.equals(null));
    }

    @Test
    public void notEqual_differentType() {
        SortCommand command1 = new SortCommand();
        assertFalse(command1.equals(5));
    }
}
