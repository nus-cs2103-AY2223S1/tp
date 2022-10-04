package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsTagPredicate;

/**
 * Lists persons that match all tags specified by the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists persons that match all tags specified\n"
            + "Example: " + COMMAND_WORD + " team1";

    public static final String MESSAGE_SUCCESS = "Listed all person(s) with the specified tag(s)";

    private final PersonContainsTagPredicate predicate;

    public SortCommand(PersonContainsTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
