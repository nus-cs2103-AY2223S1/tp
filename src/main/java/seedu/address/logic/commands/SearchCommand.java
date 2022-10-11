package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Search and lists all persons in address book whose information contains the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";
    public static final String AND_CONDITION = "and";
    public static final String OR_CONDITION = "or";
    public static final String EMPTY_CONDITION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches all persons whose information contains "
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [CONDITION] [KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " t/friend, "
            + COMMAND_WORD + " " + AND_CONDITION + " n/John a/NUS";

    private final Predicate<Person> predicate;

    /**
     * Creates a SearchCommand to search the address book.
     */
    public SearchCommand(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
