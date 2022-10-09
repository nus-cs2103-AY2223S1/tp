package bookface.logic.commands.delete;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;

/**
 * Command to delete a book from the booklist
 */
public class DeleteBookCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "book";
    public static final String MESSAGE_USAGE = DeleteCommand.COMMAND_WORD
        + " " + COMMAND_WORD
        + ": Deletes a book from the book list."
        + "Parameters: "
        + PREFIX_TITLE + "TITLE "
        + PREFIX_AUTHOR + "AUTHOR\n"
        + "Example: " + DeleteCommand.COMMAND_WORD
        + " " + COMMAND_WORD + " "
        + PREFIX_TITLE + "The Hobbit "
        + PREFIX_AUTHOR + "JRR Tolkien";
    public static final String MESSAGE_SUCCESS = "Book is deleted: ";
    public static final String MESSAGE_BOOK_NOT_FOUND = "Book not found";
    public final Book bookToDelete;

    /**
     * Constructs a DeleteBookCommand for deletion of a book.
     * @param book the book to delete
     */
    public DeleteBookCommand(Book book) {
        requireNonNull(book);
        bookToDelete = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasBook(bookToDelete)) {
            throw new CommandException(MESSAGE_BOOK_NOT_FOUND);
        }

        model.deleteBook(bookToDelete);
        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                bookToDelete.getTitle(),
                bookToDelete.getAuthor()));
    }
}
