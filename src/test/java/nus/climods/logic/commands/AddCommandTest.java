package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.module.UserModule;

public class AddCommandTest {
    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_hasModule_throwsCommandException() {
        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_MODULE, ()
                        -> new AddCommand(new UserModuleStub()).execute(new ModelStub(true)));
    }

    @Test
    public void execute_notHasModule_success() {
        UserModule toAdd = new UserModuleStub();
        try {
            CommandResult cmdRes = new AddCommand(toAdd).execute(new ModelStub(false));
            assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, toAdd), cmdRes.getFeedbackToUser());
        } catch (CommandException e) {
            fail();
        }
    }
}

