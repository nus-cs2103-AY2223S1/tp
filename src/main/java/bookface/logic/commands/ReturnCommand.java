package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;

/**
 * Loans to the user in the user list a book from the book list.
 */
public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, "Returns the"
            + " book identified by the index number in book list", "BOOK_INDEX (must be a positive"
                    + " integer)", COMMAND_WORD + " 2");

    public static final String MESSAGE_RETURN_SUCCESS = "Book %1$s returned successfully.";

    public static final String NOT_ON_LOAN = "Book is not on loan.";

    private final Index targetBookIndex;

    /**
     * Creates a ReturnCommand to return the loan of the specified {@code Book}
     */
    public ReturnCommand(Index bookIndex) {
        this.targetBookIndex = bookIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Book> lastShownBookList = model.getFilteredBookList();

        if (targetBookIndex.getZeroBased() >= lastShownBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToReturn = lastShownBookList.get(targetBookIndex.getZeroBased());

        if (!bookToReturn.isLoaned()) {
            throw new CommandException(NOT_ON_LOAN);
        }

        model.returnLoanedBook(bookToReturn);
        model.updateFilteredBookList(Model.PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_RETURN_SUCCESS, bookToReturn.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReturnCommand // instanceof handles nulls
                && targetBookIndex.equals(((ReturnCommand) other).targetBookIndex));
    }
}

