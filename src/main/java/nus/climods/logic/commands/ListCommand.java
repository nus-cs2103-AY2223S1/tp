package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;
import static nus.climods.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import nus.climods.model.Model;
import nus.climods.model.person.NameContainsKeywordsPredicate;

import java.util.Optional;

/**
 * Lists all modules in NUS to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_SUCCESS = "Listed all relevant modules";

    private final Optional<NameContainsKeywordsPredicate> faculty;

    private final Optional<Boolean> hasUser;


    /**
     * Used for list command containing predicates
     * @param faculty optional argument to specify the faculty
     * @param faculty
     */
    public ListCommand(Optional<NameContainsKeywordsPredicate> faculty, Optional<Boolean> hasUser) {
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
