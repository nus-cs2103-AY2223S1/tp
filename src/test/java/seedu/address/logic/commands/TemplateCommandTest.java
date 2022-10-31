package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TemplateCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class TemplateCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_prof_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, ProfCommand.PROF_TEMPLATE);
        assertCommandSuccess(new TemplateCommand("prof"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_student_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, StudentCommand.STUDENT_TEMPLATE);
        assertCommandSuccess(new TemplateCommand("student"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_ta_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, TaCommand.TA_TEMPLATE);
        assertCommandSuccess(new TemplateCommand("ta"), model, expectedCommandResult, expectedModel);
    }

    @Test
    void isValidPerson() {
        // null
        assertThrows(NullPointerException.class, () -> TemplateCommand.isValidPerson(null));

        // invalid person
        assertFalse(TemplateCommand.isValidPerson("")); // empty string
        assertFalse(TemplateCommand.isValidPerson(" ")); // spaces only
        assertFalse(TemplateCommand.isValidPerson("professor")); // wrong Person command

        // valid person
        assertTrue(TemplateCommand.isValidPerson("prof"));
        assertTrue(TemplateCommand.isValidPerson("student"));
        assertTrue(TemplateCommand.isValidPerson("ta"));
    }

    @Test
    void equalsTest() {
        TemplateCommand otherCommand = new TemplateCommand("prof");

        // equals
        assertEquals(otherCommand, otherCommand);
        assertEquals(otherCommand, new TemplateCommand("prof"));

        // not equals
        assertNotEquals(otherCommand, new TemplateCommand("student"));
        assertNotEquals(otherCommand, new TemplateCommand("ta"));
    }
}
