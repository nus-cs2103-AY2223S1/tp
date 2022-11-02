package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;

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
