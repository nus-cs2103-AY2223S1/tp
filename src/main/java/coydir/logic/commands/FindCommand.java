package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static coydir.logic.parser.CliSyntax.PREFIX_NAME;
import static coydir.logic.parser.CliSyntax.PREFIX_POSITION;
import static java.util.Objects.requireNonNull;

import coydir.model.Model;
import coydir.model.person.PersonMatchesKeywordsPredicate;

/**
 * Finds and lists all persons in database whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons by searching for any combination\n"
            + "of name, position, and department given the specified keywords (case-insensitive)\n"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_POSITION + "Recruiter "
            + PREFIX_DEPARTMENT + "Human Resources";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    private final PersonMatchesKeywordsPredicate predicate;

    public FindCommand(PersonMatchesKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()), 0);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
