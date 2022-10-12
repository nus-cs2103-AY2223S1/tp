package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UserModule;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class AddCommandTest {
    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_hasModule_throwsCommandException() {
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_MODULE,
                () -> new AddCommand(new UserModuleStub()).execute(new ModelStub(true))
        );
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

