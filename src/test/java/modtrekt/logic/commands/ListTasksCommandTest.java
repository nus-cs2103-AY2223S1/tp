package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.commands.tasks.ListTasksCommand;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;


public class ListTasksCommandTest {

    // === === ===
    // Positive test cases

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        ListTasksCommand cmd = new ListTasksCommand();
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        ListTasksCommand cmd1 = new ListTasksCommand();
        ListTasksCommand cmd2 = new ListTasksCommand();
        assertEquals(cmd1, cmd2);
    }

    // === === ===
    // Negative test cases

    @Test
    public void testCommand_nullObjectEquals_returnsFalse() {
        ListTasksCommand cmd = new ListTasksCommand();
        assertNotEquals(cmd, null);
    }

    @Test
    public void testCommand_differentObjectTypesEquals_returnsFalse() {
        ListTasksCommand cmd = new ListTasksCommand();
        assertNotEquals(cmd, new ModCode("CS2100"));
    }

    @Test
    public void testCommand_validModel_returnsCommandResult() throws CommandException {
        ListTasksCommand cmd = new ListTasksCommand();
        ModelStub model = new ModelStub();
        assertEquals(cmd.execute(model), new CommandResult("Here is the list of all active tasks!"));
    }

    @Test
    public void testCommandShowDoneTasks_validModel_returnsCommandResult() throws CommandException {
        ListTasksCommand cmd = new ListTasksCommand(true);
        ModelStub model = new ModelStub();
        assertEquals(cmd.execute(model),
                new CommandResult("Here is the list of all tasks, including those marked as done!"));
    }

    // === === ===
    // Exception test cases

    @Test
    public void testCommand_nullModel_throwsNullPointerException() {
        ListTasksCommand cmd = new ListTasksCommand();
        assertThrows(NullPointerException.class, () -> cmd.execute(null));
    }

    @Test
    public void testCommand_currentlyCdModule_success() {
        ListTasksCommand cmd = new ListTasksCommand();
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().build();
        model.addModule(module);
        model.setCurrentModule(module.getCode());
        assertDoesNotThrow(() -> cmd.execute(model));
    }


}
