package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.commands.AddcCommand.MESSAGE_DUPLICATE_MODULE_CLASS;
import static seedu.taassist.logic.commands.AddcCommand.getCommandMessage;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubAcceptingModuleClasses;
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

        String validClassesStr = commaSeparate(validModuleClasses, ModuleClass::toString);

        assertEquals(String.format(AddcCommand.MESSAGE_SUCCESS, validClassesStr), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(CS1101S), modelStub.getModuleClassList());
    }

    @Test
    public void execute_duplicateModuleClass_showsDuplicateClassMessage() throws Exception {
        ModuleClass moduleClass = CS1101S;
        AddcCommand addcCommand = new AddcCommand(new HashSet<>(Arrays.asList(moduleClass)));
        ModelStubWithOneModuleClass modelStub = new ModelStubWithOneModuleClass(moduleClass);

        CommandResult commandResult = addcCommand.execute(modelStub);

        assertEquals(String.format(MESSAGE_DUPLICATE_MODULE_CLASS, moduleClass), commandResult.getFeedbackToUser());
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


    @Test
    public void execute_duplicateAndNewModuleClassIntegration_success() {
        ModuleClass duplicateModuleClass = model.getTaAssist().getModuleClassList().get(0);
        ModuleClass newModuleClass = new ModuleClassBuilder().build();

        Set<ModuleClass> moduleClasses = new HashSet<>(Arrays.asList(duplicateModuleClass, newModuleClass));

        Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());
        expectedModel.addModuleClass(newModuleClass);

        String expectedMessage = getCommandMessage(new HashSet<>(Arrays.asList(newModuleClass)),
                new HashSet<>(Arrays.asList(duplicateModuleClass)));

        assertCommandSuccess(new AddcCommand(moduleClasses), model, expectedMessage, expectedModel);
    }

    //==================================== Model Stubs ===============================================================

    private static class ModelStubWithOneModuleClass extends ModelStub {
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

        @Override
        public void addModuleClasses(Set<ModuleClass> moduleClasses) {
            requireAllNonNull(moduleClasses);
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass;
        }
    }
}
