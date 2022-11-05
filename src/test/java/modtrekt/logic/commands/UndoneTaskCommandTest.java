package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.commands.tasks.UndoneTaskCommand;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;
import modtrekt.testutil.TaskBuilder;

public class UndoneTaskCommandTest {

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        UndoneTaskCommand cmd = new UndoneTaskCommand(Index.fromOneBased(1));
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        UndoneTaskCommand a = new UndoneTaskCommand(Index.fromOneBased(1));
        UndoneTaskCommand b = new UndoneTaskCommand(Index.fromOneBased(2));
        assertNotEquals(a, b);
    }

    @Test
    public void testCommand_withModel_success() throws CommandException {
        ModelStub modelStub = new ModelStub();
        Module module = new ModuleBuilder().build();
        modelStub.addModule(module);
        Task task = new TaskBuilder().withModCode(module.getCode().toString()).build();
        modelStub.addTask(task.setAsDone());
        assertEquals(new UndoneTaskCommand(Index.fromOneBased(1)).execute(modelStub),
                new CommandResult("I successfully marked this task as not done!"));
    }

    @Test
    public void testCommandAlreadyUndone_withModel_throws() {
        ModelStub modelStub = new ModelStub();
        Module module = new ModuleBuilder().build();
        modelStub.addModule(module);
        Task task = new TaskBuilder().withModCode(module.getCode().toString()).build();
        modelStub.addTask(task);
        assertThrows(CommandException.class,
                "Task #1 is already marked as undone.", () ->
                        new UndoneTaskCommand(Index.fromOneBased(1)).execute(modelStub));

    }

    @Test
    public void testCommand_noTask_throws() {
        ModelStub modelStub = new ModelStub();
        assertThrows(CommandException.class,
                "There are no tasks.", () ->
                        new UndoneTaskCommand(Index.fromOneBased(1)).execute(modelStub));

    }

    @Test
    public void testCommand_nonexistentIndex_throws() {
        ModelStub modelStub = new ModelStub();
        Module module = new ModuleBuilder().build();
        modelStub.addModule(module);
        Task task = new TaskBuilder().withModCode(module.getCode().toString()).build();
        modelStub.addTask(task);
        assertThrows(CommandException.class,
                "Task index must an integer between 1 and 1 inclusive.", () ->
                        new UndoneTaskCommand(Index.fromOneBased(20)).execute(modelStub));

    }

}
