package nus.climods.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nus.climods.commons.core.Messages;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;

public class HelpCommandTest {

    private static final String testAcademicYear = "2022-2023";
    private final Model model = new ModelManager(new ModuleList(testAcademicYear), new UniqueUserModuleList(),
            new UserPrefs());

    @Test
    public void execute_help_success() {
        String expectedMessage = Messages.MESSAGE_SHOW_HELP;

        HelpCommand helpCommand = new HelpCommand();
        CommandResult commandResult = helpCommand.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }
}
