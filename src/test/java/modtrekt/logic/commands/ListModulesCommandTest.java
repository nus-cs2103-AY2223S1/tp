package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;


public class ListModulesCommandTest {

    // === === ===
    // Positive test cases

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        ListModulesCommand cmd = new ListModulesCommand();
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        ListModulesCommand cmd1 = new ListModulesCommand();
        ListModulesCommand cmd2 = new ListModulesCommand();
        assertEquals(cmd1, cmd2);
    }

    // === === ===
    // Negative test cases

    @Test
    public void testCommand_nullObjectEquals_returnsFalse() {
        ListModulesCommand cmd = new ListModulesCommand();
        assertNotEquals(cmd, null);
    }

    @Test
    public void testCommand_differentObjectTypesEquals_returnsFalse() {
        ListModulesCommand cmd = new ListModulesCommand();
        assertNotEquals(cmd, new ModCode("CS2100"));
    }

    @Test
    public void testCommand_validModel_returnsCommandResult() throws CommandException {
        ListModulesCommand cmd = new ListModulesCommand();
        ModelStub model = new ModelStub();
        assertEquals(cmd.execute(model), new CommandResult("Here is the list of all active modules!"));
    }

    @Test
    public void testCommandShowDoneModules_validModel_returnsCommandResult() throws CommandException {
        ListModulesCommand cmd = new ListModulesCommand(true);
        ModelStub model = new ModelStub();
        assertEquals(cmd.execute(model),
                new CommandResult("Here is the list of all modules, including those marked as done!"));
    }

    // === === ===
    // Exception test cases

    @Test
    public void testCommand_nullModel_throwsNullPointerException() {
        ListModulesCommand cmd = new ListModulesCommand();
        assertThrows(NullPointerException.class, () -> cmd.execute(null));
    }

    @Test
    public void testCommand_currentlyCdModule_throws() {
        ListModulesCommand cmd = new ListModulesCommand();
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().build();
        model.addModule(module);
        model.setCurrentModule(module.getCode());
        assertThrows(CommandException.class,
                "Please exit the current module using 'cd ..' command!", () -> cmd.execute(model));
    }


}
