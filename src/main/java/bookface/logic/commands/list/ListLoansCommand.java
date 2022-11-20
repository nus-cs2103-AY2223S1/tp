package bookface.logic.commands.list;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.model.Model;

/**
 * Lists all users with loans and books that are loaned out
 */
public class ListLoansCommand extends ListCommand {
    public static final String COMMAND_WORD = "loans";
    public static final String MESSAGE_USAGE = ListCommand.generateMessage(COMMAND_WORD);
    public static final String MESSAGE_SUCCESS = "Listed all users with loans and books loaned out";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(Model.PREDICATE_ALL_LOANED_BOOKS);
        model.updateFilteredPersonList(Model.PREDICATE_ALL_LOANEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListLoansCommand); // all instances of ListLoansCommand are equal
    }
}
