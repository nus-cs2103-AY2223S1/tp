package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;
import modtrekt.testutil.TaskBuilder;

public class RemoveTaskCommandTest {

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        RemoveTaskCommand cmd = new RemoveTaskCommand(Index.fromZeroBased(0));
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        RemoveTaskCommand a = new RemoveTaskCommand(Index.fromZeroBased(0));
        RemoveTaskCommand b = new RemoveTaskCommand(Index.fromZeroBased(1));
        assertNotEquals(a, b);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        RemoveTaskCommand cmd1 = new RemoveTaskCommand(Index.fromZeroBased(0));
        RemoveTaskCommand cmd2 = new RemoveTaskCommand(Index.fromZeroBased(0));
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void testCommand_executeValidIndex_success() {
        ModelStub modelStub = new ModelStub();
        Module module = new ModuleBuilder().build();
        modelStub.addModule(module);
        Task task = new TaskBuilder().withModCode(module.getCode().toString()).build();
        modelStub.addTask(task);
        RemoveTaskCommand cmd = new RemoveTaskCommand(Index.fromZeroBased(0));
        assertDoesNotThrow(() -> cmd.execute(modelStub));
    }

    @Test
    public void testCommand_executeInvalidIndex_throwsCommandException() {
        ModelStub modelStub = new ModelStub();
        Module module = new ModuleBuilder().build();
        modelStub.addModule(module);
        Task task = new TaskBuilder().withModCode(module.getCode().toString()).build();
        modelStub.addTask(task);
        RemoveTaskCommand cmd = new RemoveTaskCommand(Index.fromZeroBased(1));
        assertThrows(CommandException.class, () -> cmd.execute(modelStub));
    }
}
