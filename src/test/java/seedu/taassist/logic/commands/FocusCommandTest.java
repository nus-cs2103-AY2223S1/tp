package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.moduleclass.ModuleClass;

class FocusCommandTest {

    @Test
    void focus_classDoesNotExist_throwsCommandException() {
        FocusCommand focusCommand = new FocusCommand(new ModuleClass("Unknown"));
        ModelStubWithNoModuleClasses modelStub = new ModelStubWithNoModuleClasses();
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
        assertTrue(modelStub.isFocusedModuleClass(moduleClass));
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
     * A Model stub that pretends to have no module classes.
     */
    private class ModelStubWithNoModuleClasses extends ModelStub {

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return false;
        }

        @Override
        public ObservableList<ModuleClass> getModuleClassList() {
            return FXCollections.observableArrayList();
        }
    }

    /**
     * A Model stub that pretends to have any module class.
     */
    private class ModelStubWithAnyModuleClass extends ModelStub {

        private ModuleClass focusedClass;

        @Override
        public boolean hasModuleClass(ModuleClass moduleClass) {
            return true;
        }

        @Override
        public void enterFocusMode(ModuleClass classToFocus) {
            focusedClass = classToFocus;
        }

        public boolean isFocusedModuleClass(ModuleClass moduleClass) {
            return this.focusedClass.isSame(moduleClass);
        }
    }
}
