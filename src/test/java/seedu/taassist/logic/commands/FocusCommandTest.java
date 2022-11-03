package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubWithNoModuleClass;
import seedu.taassist.testutil.ModuleClassBuilder;

class FocusCommandTest {

    @Test
    void focus_classDoesNotExist_throwsCommandException() {
        FocusCommand focusCommand = new FocusCommand(new ModuleClassBuilder().build());
        ModelStubWithNoModuleClass modelStub = new ModelStubWithNoModuleClass();
        String expectedMessage =
                String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST, modelStub.getModuleClassList());
        assertThrows(CommandException.class, expectedMessage, () -> focusCommand.execute(modelStub));
    }

    @Test
    void focus_classExists_focusSuccessful() throws CommandException {
        ModuleClass moduleClass = new ModuleClass("CS2103T");
        FocusCommand focusCommand = new FocusCommand(moduleClass);
        ModelStubWithAnyModuleClass modelStub = new ModelStubWithAnyModuleClass();

        CommandResult commandResult = focusCommand.execute(modelStub);

        assertEquals(String.format(FocusCommand.MESSAGE_ENTERED_FOCUS_MODE, moduleClass),
                commandResult.getFeedbackToUser());
        assertEquals(modelStub.getFocusedClass(), moduleClass);
    }

    @Test
    void equals() {
        ModuleClass moduleClass = new ModuleClass("CS2103T");
        FocusCommand focusCommand = new FocusCommand(moduleClass);

        // same object -> returns true
        assertTrue(focusCommand.equals(focusCommand));

        // same value -> returns true
        FocusCommand focusCommandCopy = new FocusCommand(moduleClass);
        assertTrue(focusCommand.equals(focusCommandCopy));

        // different types -> returns false
        assertFalse(focusCommand.equals(new UnfocusCommand()));

        // null -> returns false
        assertFalse(focusCommand.equals(null));

        // different module class -> returns false
        ModuleClass differentModuleClass = new ModuleClass("CS2101");
        assertFalse(focusCommand.equals(new FocusCommand(differentModuleClass)));
    }

    /**
     * A Model stub that pretends to have any module class.
     */
    private static class ModelStubWithAnyModuleClass extends ModelStub {

        private ModuleClass focusedClass;

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }

        @Override
        public void enterFocusMode(ModuleClass classToFocus) {
            requireNonNull(classToFocus);
            focusedClass = classToFocus;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }

        @Override
        public ModuleClass getModuleClassWithSameName(ModuleClass moduleClass) {
            requireNonNull(moduleClass);
            return moduleClass;
        }
    }
}
