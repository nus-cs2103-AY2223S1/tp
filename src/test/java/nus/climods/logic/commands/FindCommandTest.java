package nus.climods.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import nus.climods.commons.core.Messages;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.Module;
import nus.climods.model.module.ModuleList;

class FindCommandTest {

    private static final String testAcademicYear = "2022-2023";
    private final Model model = new ModelManager(new ModuleList(testAcademicYear), new UserPrefs());

    @Test
    public void execute_zeroKeywords_noModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 0);

        FindCommand command = new FindCommand(Collections.emptyList());
        CommandResult commandResult = command.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 3);

        FindCommand command = new FindCommand(prepareSearchRegexes("CS2103"));
        CommandResult commandResult = command.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
        assertEquals(model.getFilteredModuleList().stream().map(Module::getCode).collect(Collectors.toList()),
            Arrays.asList("CS2103", "CS2103R", "CS2103T"));
    }

    @Test
    public void execute_regexKeywords_multipleModulesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, 4);

        FindCommand command = new FindCommand(prepareSearchRegexes("^CS210[0-3]$"));
        CommandResult commandResult = command.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
        assertEquals(model.getFilteredModuleList().stream().map(Module::getCode).collect(Collectors.toList()),
            Arrays.asList("CS2100", "CS2101", "CS2102", "CS2103"));
    }

    /**
     * Parses {@code userInput} into a search regexes.
     */
    private List<Pattern> prepareSearchRegexes(String userInput) {
        return Arrays.stream(userInput.trim().split("\\s+"))
            .map(token -> Pattern.compile(token, Pattern.CASE_INSENSITIVE)).collect(Collectors.toList());
    }
}
