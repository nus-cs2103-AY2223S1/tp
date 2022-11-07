package nus.climods.logic.commands;

import static nus.climods.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.testutil.ModuleListTestUtil;

public class ExitCommandTest {

    private static final String testAcademicYear = "2022-2023";
    private final Model model =
        new ModelManager(new ModuleList(ModuleListTestUtil.loadModuleList(testAcademicYear)),
            new UniqueUserModuleList(),
            new UserPrefs());

    @Test
    public void execute_exit_success() throws CommandException {
        String expectedMessage = MESSAGE_EXIT_ACKNOWLEDGEMENT;

        ExitCommand exitCommand = new ExitCommand();
        CommandResult commandResult = exitCommand.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }
}
