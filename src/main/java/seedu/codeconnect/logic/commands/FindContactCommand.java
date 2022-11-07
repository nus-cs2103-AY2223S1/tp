package seedu.codeconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.codeconnect.commons.core.Messages;
import seedu.codeconnect.commons.core.index.Index;
import seedu.codeconnect.logic.commands.exceptions.CommandException;
import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.person.CanHelpWithTaskPredicate;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.task.Task;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching for both names and modules are case-insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findc";

    /*
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";
     */

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons matching a given filter.\n"
            + "Parameters: {field_prefix + keyword} ...\n"
            + "Example 1: " + COMMAND_WORD + " n/ Jacob n/ Alice";

    private final Predicate<Person> predicate;

    public FindContactCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    public static FindContactCommand withoutArgs() {
        return new FindContactCommand(new CanHelpWithTaskPredicate(Index.fromOneBased(1)));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (predicate instanceof CanHelpWithTaskPredicate) {
            CanHelpWithTaskPredicate taskPredicate = (CanHelpWithTaskPredicate) predicate;
            List<Task> lastShownList = model.getSortedTaskList();
            assert(taskPredicate.getTaskIndex().getZeroBased() >= 0);
            if (taskPredicate.getTaskIndex().getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
        }

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }
}
