package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import longtimenosee.commons.core.Messages;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.person.predicate.PinnedPersonPredicate;


/**
 * Lists all persons pinned to the user.
 */
public class ViewPinCommand extends Command {

    public static final String COMMAND_WORD = "viewPin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": pins the client to start of list\n"
            + "Parameters: CLIENT_INDEX...\n"
            + "Example: " + COMMAND_WORD + "Alice";

    private final PinnedPersonPredicate predicate;

    public ViewPinCommand(PinnedPersonPredicate p) {
        this.predicate = p;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, true, false);
    }
}
