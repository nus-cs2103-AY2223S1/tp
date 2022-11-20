package bookface.logic.commands.list;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.model.Model;

/**
 * List all users with overdue loans, and books that are overdue
 */
public class ListOverdueCommand extends ListCommand {
    public static final String COMMAND_WORD = "overdue";
    public static final String MESSAGE_USAGE = ListCommand.generateMessage(COMMAND_WORD);
    public static final String MESSAGE_SUCCESS = "Listed all users with overdue loans and the overdue books";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(Model.PREDICATE_ALL_OVERDUE_BOOKS);
        model.updateFilteredPersonList(Model.PREDICATE_OWE_OVERDUE_BOOKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListOverdueCommand); // all instances of ListOverdueCommand are equal
    }
}
