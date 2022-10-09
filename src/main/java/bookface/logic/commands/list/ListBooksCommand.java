package bookface.logic.commands.list;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.model.Model;

/**
 * Lists all books to the user
 */
public class ListBooksCommand extends ListCommand {
    public static final String COMMAND_WORD = "books";

    public static final String MESSAGE_USAGE = ListCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": List all books.\n" + "Example: " + ListCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all books";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(Model.PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListBooksCommand); // all instances of ListBooksCommand are equal
    }
}
