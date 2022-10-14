package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<ModuleClass> validModuleClasses = new HashSet<>();
        validModuleClasses.add(CS1101S);

        CommandResult commandResult = new AddcCommand(validModuleClasses).execute(modelStub);

        String validClassesStr = validModuleClasses.stream().map(Object::toString).collect(Collectors.joining(" "));

        assertEquals(String.format(AddcCommand.MESSAGE_SUCCESS, validClassesStr), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(CS1101S), modelStub.moduleClassesAdded);
    }

    @Test
    public void execute_duplicateModuleClass_throwsCommandException() throws Exception {
        ModuleClass moduleClass = CS1101S;
        AddcCommand addcCommand = new AddcCommand(new HashSet<>(Arrays.asList(moduleClass)));
        ModelStubWithOneModuleClass modelStub = new ModelStubWithOneModuleClass(moduleClass);

        assertThrows(CommandException.class, AddcCommand.MESSAGE_DUPLICATE_MODULE_CLASS, () ->
                addcCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Set<ModuleClass> cs1101sModuleClasses = new HashSet<>();
        cs1101sModuleClasses.add(CS1101S);

        Set<ModuleClass> cs1231sModuleClasses = new HashSet<>();
        cs1231sModuleClasses.add(CS1231S);
        AddcCommand addCs1101sCommand = new AddcCommand(cs1101sModuleClasses);
        AddcCommand addCs1101sCommandCopy = new AddcCommand(cs1101sModuleClasses);
        AddcCommand addCs1231sCommand = new AddcCommand(cs1231sModuleClasses);

        // same object -> returns true
        assertTrue(addCs1101sCommand.equals(addCs1101sCommand));

        // same values -> returns true
        assertTrue(addCs1101sCommand.equals(addCs1101sCommandCopy));

        // different types -> returns false
        assertFalse(addCs1101sCommand.equals(1));

        // null -> returns false
        assertFalse(addCs1101sCommand.equals(null));

        // different module class -> returns false
        assertFalse(addCs1101sCommand.equals(addCs1231sCommand));
    }

    //==================================== Integration Tests =========================================================

    @Test
    public void execute_newModuleClass_success() {
        // module class should not be in any of the classes in TypicalStudents
        ModuleClass validNewModuleClass = new ModuleClassBuilder().build();
        Set<ModuleClass> validModuleClasses = new HashSet<>();
        validModuleClasses.add(validNewModuleClass);

        Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());
        expectedModel.addModuleClass(validNewModuleClass);

        assertCommandSuccess(new AddcCommand(validModuleClasses), model,
                String.format(AddcCommand.MESSAGE_SUCCESS, validNewModuleClass), expectedModel);
    }

    /* TODO - not throwing duplicate exception now
    @Test
    public void execute_duplicateModuleClassIntegration_throwsCommandException() {
        Set<ModuleClass> moduleClasses = new HashSet<>();
        ModuleClass moduleClassInList = model.getTaAssist().getModuleClassList().get(0);
        moduleClasses.add(moduleClassInList);
        assertCommandFailure(new AddcCommand(moduleClasses), model, AddcCommand.MESSAGE_DUPLICATE_MODULE_CLASS);
    }
     */

    //==================================== Model Stubs ===============================================================

    private class ModelStubWithOneModuleClass extends ModelStub {
        private final ModuleClass moduleClass;

        public ModelStubWithOneModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            this.moduleClass = moduleClass;
        }

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass.equals(this.moduleClass);
        }

        @Override
        public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
            return moduleClasses.size() == 1 && moduleClasses.contains(moduleClass);
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
