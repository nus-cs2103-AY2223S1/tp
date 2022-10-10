package bookface.logic.commands.find;

import static java.util.Objects.requireNonNull;

import bookface.commons.core.Messages;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.ObjectContainsKeywordsPredicate;
import bookface.model.book.Book;

/**
 * Command to delete a book from the booklist using it's displayed index from the book list
 */
public class FindBookCommand extends FindCommand {
    public static final String COMMAND_WORD = "book";
    public static final String MESSAGE_USAGE = FindCommand.COMMAND_WORD + " " + COMMAND_WORD
        + ": Deletes the book identified by the index number used in the displayed book list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + FindCommand.COMMAND_WORD + " " + COMMAND_WORD + " 1";

    private final ObjectContainsKeywordsPredicate<Book, String> predicate;

    public FindBookCommand(ObjectContainsKeywordsPredicate<Book, String> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredBookList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookCommand // instanceof handles nulls
                && predicate.equals(((FindBookCommand) other).predicate)); // state check
    }
}
