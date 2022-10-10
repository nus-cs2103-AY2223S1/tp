package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;

public class AddcCommandTest {

    @Test
    public void constructor_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddcCommand(null));
    }

    @Test
    public void execute_moduleClassAcceptedByModel_success() throws Exception {
        ModelStubAcceptingModuleClasses modelStub = new ModelStubAcceptingModuleClasses();
        ModuleClass validModuleClass = CS1101S;

        CommandResult commandResult = new AddcCommand(validModuleClass).execute(modelStub);

        assertEquals(String.format(AddcCommand.MESSAGE_SUCCESS, validModuleClass), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(CS1101S), modelStub.moduleClassesAdded);
    }

    @Test
    public void execute_duplicateModuleClass_throwsCommandException() throws Exception {
        ModuleClass validModuleClass = CS1101S;
        AddcCommand addcCommand = new AddcCommand(validModuleClass);
        ModelStub modelStub = new ModelStubWithModuleClass(validModuleClass);

        assertThrows(CommandException.class, AddcCommand.MESSAGE_DUPLICATE_MODULE_CLASS, () ->
                addcCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddcCommand addCS1101SCommand = new AddcCommand(CS1101S);
        AddcCommand addCS1101SCommandCopy = new AddcCommand(CS1101S);
        AddcCommand addCS1231SCommand = new AddcCommand(CS1231S);

        // same object -> returns true
        assertTrue(addCS1101SCommand.equals(addCS1101SCommand));

        // same values -> returns true
        assertTrue(addCS1101SCommand.equals(addCS1101SCommandCopy));

        // different types -> returns false
        assertFalse(addCS1101SCommand.equals(1));

        // null -> returns false
        assertFalse(addCS1101SCommand.equals(null));

        // different module class -> returns false
        assertFalse(addCS1101SCommand.equals(addCS1231SCommand));
    }

    private class ModelStubWithModuleClass extends ModelStub {
        private final ModuleClass moduleClass;

        ModelStubWithModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            this.moduleClass = moduleClass;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return this.moduleClass.equals(moduleClass);
        }
    }
    /**
     * A Model stub that always accepts the module class being added.
     */
    private class ModelStubAcceptingModuleClasses extends ModelStub {
        final ArrayList<ModuleClass> moduleClassesAdded = new ArrayList<>();

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClassesAdded.stream().anyMatch(moduleClass::equals);
        }

        @Override
        public void addModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            moduleClassesAdded.add(moduleClass);
        }

        @Override
        public ReadOnlyTaAssist getTaAssist() {
            return new TaAssist();
        }
    }
}
