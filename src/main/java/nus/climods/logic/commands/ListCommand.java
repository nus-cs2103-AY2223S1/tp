package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;

import java.util.Optional;

import nus.climods.logic.parser.parameters.FacultyCodeParameter;
import nus.climods.logic.parser.parameters.UserFlagParameter;
import nus.climods.model.Model;
import nus.climods.model.module.predicate.CodeContainsKeywordsPredicate;

/**
 * Lists all modules in NUS to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "ls";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all modules with module code containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: [faculty code] [--user]...\n"
            + "Example: " + COMMAND_WORD + "CS --user";
    public static final String MESSAGE_SUCCESS = "Listed all relevant modules";
    private final Optional<String> facultyCode;

    private final Optional<Boolean> hasUser;


    /**
     * Used for list command containing predicates
     *
     * @param faculty optional argument to specify the faculty
     * @param hasUser
     */
    public ListCommand(FacultyCodeParameter faculty, UserFlagParameter hasUser) {
        this.facultyCode = faculty.getOptionalArgValue();
        this.hasUser = hasUser.getOptionalArgValue();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: Implement filtering for saved modules
        // Support for --user flag
        model.setFilteredModuleList(new CodeContainsKeywordsPredicate(facultyCode));

        return new CommandResult(String.format(MESSAGE_MODULES_LISTED_OVERVIEW,
                model.getFilteredModuleList().size()),
                COMMAND_WORD, model);
    }
}
