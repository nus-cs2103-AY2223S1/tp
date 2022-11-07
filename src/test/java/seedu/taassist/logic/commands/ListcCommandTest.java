package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.logic.commands.ListcCommand.MESSAGE_EMPTY_CLASS_LIST;
import static seedu.taassist.logic.commands.ListcCommand.MESSAGE_SUCCESS;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.ModelManager;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.stubs.ModelStubAcceptingModuleClasses;

public class ListcCommandTest {

    @Test
    public void execute_emptyList_showsEmptyListMessage() {
        ListcCommand command = new ListcCommand();
        CommandResult commandResult = command.execute(new ModelManager(new TaAssist(), new UserPrefs()));
        assertEquals(MESSAGE_EMPTY_CLASS_LIST, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_containsModuleClass_showsListOfModuleClasses() {
        ListcCommand command = new ListcCommand();
        ModelStubAcceptingModuleClasses modelStub = new ModelStubAcceptingModuleClasses();
        modelStub.addModuleClass(CS1101S);
        CommandResult commandResult = command.execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Arrays.asList(CS1101S)), commandResult.getFeedbackToUser());
    }
}
