package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;

public class AddCommandTest {
    private static final String testAcademicYear = "2022-2023";
    private final Model model = new ModelManager(new ModuleList(testAcademicYear), new UniqueUserModuleList(),
            new UserPrefs());

    // TODO: add more tests once we have infrastructure to compare expected vs actual model state
    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    @Test
    public void execute_add_success() throws CommandException {
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "CS1010J");

        AddCommand addCommand = new AddCommand("CS1010J", SemestersEnum.S1);
        CommandResult commandResult = addCommand.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    /**
     * CS1010J is not offered in Semester 2.
     * @throws CommandException
     */
    @Test
    public void execute_addModuleNotOfferedInSemester_throwsException() {
        AddCommand addCommand = new AddCommand("CS1010J", SemestersEnum.S2);

        assertThrows(CommandException.class, () -> addCommand.execute(model));
    }
}

