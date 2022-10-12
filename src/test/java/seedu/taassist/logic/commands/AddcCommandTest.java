package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.testutil.ModuleClassBuilder;

public class AddcCommandTest {

    private Model model = new ModelManager(getTypicalTaAssist(), new UserPrefs());

    //==================================== Unit Tests ================================================================

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
        ModelStubWithModuleClass modelStub = new ModelStubWithModuleClass(validModuleClass);

        assertThrows(CommandException.class, AddcCommand.MESSAGE_DUPLICATE_MODULE_CLASS, () ->
                addcCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddcCommand addCs1101sCommand = new AddcCommand(CS1101S);
        AddcCommand addCs1101sCommandCopy = new AddcCommand(CS1101S);
        AddcCommand addCS1231SCommand = new AddcCommand(CS1231S);

        // same object -> returns true
        assertTrue(addCs1101sCommand.equals(addCs1101sCommand));

        // same values -> returns true
        assertTrue(addCs1101sCommand.equals(addCs1101sCommandCopy));

        // different types -> returns false
        assertFalse(addCs1101sCommand.equals(1));

        // null -> returns false
        assertFalse(addCs1101sCommand.equals(null));

        // different module class -> returns false
        assertFalse(addCs1101sCommand.equals(addCS1231SCommand));
    }

    //==================================== Integration Tests =========================================================

    @Test
    public void execute_newModuleClass_success() {
        // module class should not be in any of the classes in TypicalStudents
        ModuleClass validNewModuleClass = new ModuleClassBuilder().build();

        Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());
        expectedModel.addModuleClass(validNewModuleClass);

        assertCommandSuccess(new AddcCommand(validNewModuleClass), model,
                String.format(AddcCommand.MESSAGE_SUCCESS, validNewModuleClass), expectedModel);
    }

    @Test
    public void execute_duplicateModuleClassIntegration_throwsCommandException() {
        ModuleClass moduleClassInList = model.getTaAssist().getModuleClassList().get(0);
        assertCommandFailure(new AddcCommand(moduleClassInList), model, AddcCommand.MESSAGE_DUPLICATE_MODULE_CLASS);
    }

    //==================================== Model Stubs ===============================================================

    /**
     * A Model stub that contains one module class.
     */
    private class ModelStubWithModuleClass extends ModelStub {
        private final ModuleClass moduleClass;

        public ModelStubWithModuleClass(ModuleClass moduleClass) {
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
        private final ArrayList<ModuleClass> moduleClassesAdded = new ArrayList<>();

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return false;
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
