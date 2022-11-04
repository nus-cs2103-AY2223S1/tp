package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.testutil.CdModuleCommandBuilder;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;

public class CdModuleCommandTest {

    @Test
    public void cd_noRestrictions_returnsTrue() throws CommandException {
        CdModuleCommand command = CdModuleCommandBuilder.build("CS2103");
        command.execute(new ModelHasModule());
    }

    @Test
    public void cd_doneModule_returnsTrue() throws CommandException {
        CdModuleCommand command = CdModuleCommandBuilder.build("CS2103");
        command.execute(new ModelHasModuleAndModuleIsDone());
    }

    @Test
    public void cd_moduleDoesNotExist_throws() {
        CdModuleCommand command = CdModuleCommandBuilder.build("CS2103");
        assertThrows(CommandException.class, () -> command.execute(new ModelStub()));
    }

    @Test
    public void cdOutOfModule_notInAModule_throws() {
        CdModuleCommand command = CdModuleCommandBuilder.build("..");
        assertThrows(CommandException.class, "Already showing all modules.", ()
                -> command.execute(new ModelStub()));
    }

    @Test
    public void cdOutOfModuleInAModule_successful() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().build();
        model.addModule(module);
        model.setCurrentModule(new ModCode("CS1231S"));
        CdModuleCommand command = CdModuleCommandBuilder.build("..");
        assertDoesNotThrow(() -> command.execute(model));
        assertEquals(null, model.getCurrentModule());
    }

    @Test
    public void cdIntoModuleNotInAModule_successful() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().build();
        model.addModule(module);
        CdModuleCommand command = CdModuleCommandBuilder.build("CS1231S");
        assertDoesNotThrow(() -> command.execute(model));
        assertEquals(new ModCode("CS1231S"), module.getCode());
    }


    @Test
    public void cdIntoModuleInAModule_successful() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().build();
        model.addModule(module);
        model.setCurrentModule(new ModCode("CS1231S"));
        Module secondModule = new ModuleBuilder().withCode("CS1231").build();
        model.addModule(secondModule);
        CdModuleCommand command = CdModuleCommandBuilder.build("CS1231");
        assertDoesNotThrow(() -> command.execute(model));
        assertEquals(new ModCode("CS1231"), model.getCurrentModule());
    }

    @Test
    public void cdIntoModuleInvalidModuleCode_throws() {
        CdModuleCommand command = CdModuleCommandBuilder.build("CS201919191991");
        assertThrows(CommandException.class, "Invalid module code. Usage:\n"
                        + "cd <alphanumeric mod code of 6-9 characters>: "
                        + "cds into specified module.\n"
                        + "cd ..: cds out of current module.", () -> command.execute(new ModelStub()));
    }



    /**
     * A model stub that returns true for the hasModuleWithModCode method and parseModuleFromCode returns a
     * module, to mimic specified behaviour.
     */
    private class ModelHasModule extends ModelStub {
        @Override
        public boolean hasModuleWithModCode(ModCode code) {
            return true;
        }

        @Override
        public Module parseModuleFromCode(ModCode code) {
            return new ModuleBuilder().build();
        }
    }

    /**
     * A model stub that returns true for the hasModuleWithModCode method and parseModuleFromCode returns a
     * module that is done, to mimic specified behaviour.
     */
    private class ModelHasModuleAndModuleIsDone extends ModelHasModule {
        @Override
        public Module parseModuleFromCode(ModCode code) {
            return new ModuleBuilder().withDone(true).build();
        }
    }
}
