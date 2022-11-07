package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandUtil.requireFocusMode;
import static seedu.taassist.logic.commands.CommandUtil.requireModuleClassExists;
import static seedu.taassist.logic.commands.CommandUtil.requireModuleClassesExist;
import static seedu.taassist.logic.commands.CommandUtil.requireSessionExists;
import static seedu.taassist.logic.commands.CommandUtil.requireSessionsExist;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStubAcceptingModuleClasses;
import seedu.taassist.model.stubs.ModelStubInFocusMode;
import seedu.taassist.model.stubs.ModelStubNeverInFocusMode;
import seedu.taassist.model.stubs.ModelStubWithNoModuleClass;
import seedu.taassist.testutil.ModuleClassBuilder;

public class CommandUtilTest {

    private static final String DUMMY_COMMAND_WORD = "DUMMY_COMMAND_WORD";

    @Test
    public void requireModuleClassExists_moduleClassDoesNotExist_throwsCommandException() {
        ModelStubWithNoModuleClass modelStub = new ModelStubWithNoModuleClass();
        assertThrows(CommandException.class, () -> requireModuleClassExists(CS1101S, modelStub));
    }

    @Test
    public void requireModuleClassExists_moduleClassExists_doesNotThrowCommandException() {
        ModelStubAcceptingModuleClasses modelStub = new ModelStubAcceptingModuleClasses();
        modelStub.addModuleClass(CS1101S);
        try {
            requireModuleClassExists(CS1101S, modelStub);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void requireModuleClassesExists_oneModuleClassDoesNotExist_throwsCommandException() {
        ModelStubAcceptingModuleClasses modelStub = new ModelStubAcceptingModuleClasses();
        modelStub.addModuleClass(CS1101S);
        assertThrows(CommandException.class, () -> requireModuleClassesExist(List.of(CS1101S, CS1231S), modelStub));
    }

    @Test
    public void requireModuleClassesExists_allModuleClassesExist_doesNotThrowCommandException() {
        ModelStubAcceptingModuleClasses modelStub = new ModelStubAcceptingModuleClasses();
        Set<ModuleClass> moduleClasses = new HashSet<>(List.of(CS1101S, CS1231S));
        modelStub.addModuleClasses(moduleClasses);
        try {
            requireModuleClassesExist(moduleClasses, modelStub);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void requireSessionExists_sessionDoesNotExist_throwsCommandException() {
        ModuleClass moduleClass = new ModuleClassBuilder().withSessions(LAB_1).build();
        assertThrows(CommandException.class, () -> requireSessionExists(TUTORIAL_1, moduleClass));
    }

    @Test
    public void requireSessionExists_sessionExists_doesNotThrowCommandException() {
        ModuleClass moduleClass = new ModuleClassBuilder().withSessions(LAB_1).build();
        try {
            requireSessionExists(LAB_1, moduleClass);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void requireSessionsExist_oneSessionDoesNotExist_throwsCommandException() {
        ModuleClass moduleClass = new ModuleClassBuilder().withSessions(LAB_1, TUTORIAL_1).build();
        assertThrows(CommandException.class, () ->
                requireSessionsExist(List.of(LAB_1, TUTORIAL_1, ASSIGNMENT_1), moduleClass));
    }

    @Test
    public void requireSessionsExist_allSessionsExist_doesNotThrowCommandException() {
        ModuleClass moduleClass = new ModuleClassBuilder().withSessions(LAB_1, TUTORIAL_1).build();
        try {
            requireSessionsExist(List.of(LAB_1, TUTORIAL_1), moduleClass);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void requireFocusMode_notInFocusMode_throwsCommandException() {
        ModelStubNeverInFocusMode modelStub = new ModelStubNeverInFocusMode();
        assertThrows(CommandException.class, () -> requireFocusMode(modelStub, DUMMY_COMMAND_WORD));
    }

    @Test
    public void requireFocusMode_inFocusMode_doesNotThrowCommandException() {
        ModelStubInFocusMode modelStub = new ModelStubInFocusMode();
        try {
            requireFocusMode(modelStub, DUMMY_COMMAND_WORD);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }
}
