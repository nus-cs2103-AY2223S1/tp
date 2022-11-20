package bookface.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;

/**
 * Deletes a book from the book list using its displayed index from the book list
 */
public class DeleteBookCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "book";
    public static final String MESSAGE_USAGE = DeleteCommand.generateMessage(COMMAND_WORD);

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted book: %1$s";
    private final Index targetIndex;

    /**
     * Constructs a DeleteBookCommand for deletion of a book.
     * @param targetIndex the index of the book to delete
     */
    public DeleteBookCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (bookToDelete.isLoaned()) {
            throw new CommandException(Messages.MESSAGE_BOOK_STILL_ON_LOAN);
        }
        model.deleteBook(bookToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBookCommand) other).targetIndex)); // state check
    }
}
