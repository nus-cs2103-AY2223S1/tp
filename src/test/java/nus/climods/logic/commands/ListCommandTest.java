package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nus.climods.commons.core.Messages;
import nus.climods.logic.parser.ListCommandParser;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;

class ListCommandTest {

    private static final String testAcademicYear = "2022-2023";
    private final Model model = new ModelManager(new ModuleList(testAcademicYear), new UniqueUserModuleList(),
            new UserPrefs());

    @Test
    public void execute_zeroKeywords_allModulesFound() throws ParseException {
        // No faculty code should return the entire catalogue of modules
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW,
                model.getFilteredModuleList().size());
        ListCommand command = new ListCommandParser().parse("");
        CommandResult commandResult = command.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_validFacultyCode() throws ParseException {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 86);

        ListCommand command = new ListCommandParser().parse("ST");
        CommandResult commandResult = command.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_invalidFacultyCode() throws ParseException {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);

        ListCommand command = new ListCommandParser().parse("CSssss");
        CommandResult commandResult = command.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new ListCommand(null, null));
    }

}
