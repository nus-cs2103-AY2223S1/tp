package nus.climods.logic.commands;

import nus.climods.logic.parser.parameters.FacultyCodeParameter;
import nus.climods.logic.parser.parameters.UserFlagParameter;
import nus.climods.model.Model;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static nus.climods.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Lists all modules in NUS to the user.
 */
public class LsCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_SUCCESS = "Listed all relevant modules";

    private final Optional<FacultyCodeParameter> faculty;

    private final Optional<UserFlagParameter> hasUser;


    /**
     * Used for list command containing predicates
     * @param faculty optional argument to specify the faculty
     * @param faculty
     */
    public LsCommand(Optional<FacultyCodeParameter> faculty, Optional<UserFlagParameter> hasUser) {
        this.faculty = faculty;
        this.hasUser = hasUser;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
