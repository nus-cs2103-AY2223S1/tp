package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddModuleCommand(validModule).execute(modelStub);

        assertEquals(String.format(AddModuleCommand.MESSAGE_ADD_MODULE_SUCCESS, validModule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class, AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () ->
                addModuleCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateModuleIgnoreCase_throwsCommandException() {
        Module validModule =
                new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE.toLowerCase()).build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class, AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () ->
                addModuleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module cs2109s = new ModuleBuilder().withModuleCode("CS2109S").build();
        Module is1103 = new ModuleBuilder().withModuleCode("IS1103").build();
        AddModuleCommand addCs2109sCommand = new AddModuleCommand(cs2109s);
        AddModuleCommand addIs1103Command = new AddModuleCommand(is1103);

        // same object -> returns true
        assertTrue(addCs2109sCommand.equals(addCs2109sCommand));

        // same values -> returns true
        AddModuleCommand addCs2109sCommandCopy = new AddModuleCommand(cs2109s);
        assertTrue(addCs2109sCommand.equals(addCs2109sCommandCopy));

        // different types -> returns false
        assertFalse(addCs2109sCommand.equals(1));

        // null -> returns false
        assertFalse(addCs2109sCommand.equals(null));

        // different module -> returns false
        assertFalse(addCs2109sCommand.equals(addIs1103Command));
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }
    }

    /**
     * A Model stub that always accept the module being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();
        private boolean isHome = true;

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public Boolean getHomeStatusAsBoolean() {
            return isHome;
        }

        @Override
        public void setHomeStatus(boolean isHome) {
            requireNonNull(isHome);
            this.isHome = isHome;
        }

        @Override
        public void goToHomePage() {
            isHome = true;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
