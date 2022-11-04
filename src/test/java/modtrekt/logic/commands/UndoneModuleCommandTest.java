package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.ModtrektParser;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;


public class UndoneModuleCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        ModCode moduleCode = null;
        assertThrows(NullPointerException.class, () -> new UndoneModuleCommand(moduleCode));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = null;
        ModCode moduleCode = new ModCode("CS1101S");
        UndoneModuleCommand command = new UndoneModuleCommand(moduleCode);
        assertThrows(NullPointerException.class, () -> command.execute(model));
    }

    /**
     * Utility function to add modules for command execution.
     */
    private ModelStub testExecutionSetup() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().withDone(true).build();
        Module secondModule = new ModuleBuilder().withCode("CS2100").withCredit("4")
                .withName("Computer Organisation").withDone(true).build();
        model.addModule(module);
        model.addModule(secondModule);
        return model;
    }

    @Test
    public void unsuccessfulTestExecuteCommandCdIntoAnotherModule() {
        ModelStub model = testExecutionSetup();
        model.setCurrentModule(new ModCode("CS2100"));
        assertThrows(CommandException.class, "Please exit the current module using 'cd ..' command!", ()
                -> new ModtrektParser().parseCommand("undone mod CS1231S").execute(model));
    }

    @Test
    public void unsuccessfulTestExecuteCommandNonexistentModCode() {
        ModelStub model = testExecutionSetup();
        assertThrows(CommandException.class, "Module code CS9999 does not exist.", ()
                -> new ModtrektParser().parseCommand("undone mod CS9999").execute(model));
    }

    @Test
    public void unsuccessfulTestExecuteCommandModuleAlreadyUndone() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().withDone(false).build();
        model.addModule(module);
        assertThrows(CommandException.class, "Module CS1231S is already marked as undone.", ()
                -> new ModtrektParser().parseCommand("undone mod CS1231S").execute(model));
    }

    @Test
    public void successfulTestExecuteMarkUndone() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().withDone(true).build();
        model.addModule(module);
        assertDoesNotThrow(() -> new ModtrektParser()
                .parseCommand("undone mod CS1231S").execute(model));
    }
}
