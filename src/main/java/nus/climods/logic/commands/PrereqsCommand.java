package nus.climods.logic.commands;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.Module;

/**
 * Lists prerequisites for a module.
 */
public class PrereqsCommand extends Command {
    public static final String COMMAND_WORD = "preq";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "<Module Code>: List prerequisites for a module.\n"
            + "Example: " + COMMAND_WORD + " " + "CS2103";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module '%s' not in current NUS curriculum";
    public static final String MESSAGE_MODULE_LOAD_ERROR = "Error loading prerequisites for %s";
    public static final String MESSAGE_MODULE_NULL_PREREQUISITES = "Module %s has no prerequisites in current NUS "
            + "curriculum";
    public static final String MESSAGE_SUCCESS = "Prerequisite description: %s\nShowing available prerequisites for %s";
    public static final String MESSAGE_MODULE_NO_PREREQUISITES = "Prerequisite description: %s\nUnable to show "
            + "prerequisites for this module.";
    /**
     * Pattern to extract module codes from a string
     */
    private static final Pattern MODULE_CODE_EXTRACT_PATTERN = Pattern.compile("[A-Z]{2,4}\\d{4}[A-Z]{0,5}\\d{0,2}");
    private final String moduleCode;

    /**
     * Constructor for PrereqsCommand class
     * @param moduleCode Module code to list prerequisites for
     */
    public PrereqsCommand(String moduleCode) {
        Objects.requireNonNull(moduleCode);
        this.moduleCode = moduleCode.toUpperCase().trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Optional<Module> moduleOptional = model.getListModule(moduleCode);

        if (moduleOptional.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND, moduleCode));
        }

        Module module = moduleOptional.get();
        try {
            module.loadMoreData();
        } catch (ApiException e) {
            throw new CommandException(String.format(MESSAGE_MODULE_LOAD_ERROR, moduleCode));
        }

        String prereqString = module.getPrerequisite();
        if (prereqString == null) {
            return new CommandResult(String.format(MESSAGE_MODULE_NULL_PREREQUISITES, moduleCode),
                    false, false);
        }
        Matcher matcher = MODULE_CODE_EXTRACT_PATTERN.matcher(prereqString);
        List<String> prereqs = matcher.results().map(MatchResult::group).collect(Collectors.toList());

        // returns false for classes where no prereq in current NUS curriculum
        if (!model.showModules(prereqs)) {
            return new CommandResult(String.format(MESSAGE_MODULE_NO_PREREQUISITES, prereqString), false, false);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, prereqString, moduleCode), false, false);
    }
}
