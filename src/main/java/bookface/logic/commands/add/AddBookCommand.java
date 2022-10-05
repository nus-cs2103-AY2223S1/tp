package bookface.logic.commands.add;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;

/**
 * The command that adds a book to the BookList.
 */
public class AddBookCommand extends AddCommand {
    public static final String COMMAND_WORD = "book";
    public static final String MESSAGE_USAGE = AddCommand.COMMAND_WORD
            + " " + COMMAND_WORD
            + ": Adds a book to the book list."
            + "Parameters: "
            + PREFIX_AUTHOR + "AUTHOR "
            + PREFIX_TITLE + "TITLE \n"
            + "Example: " + AddCommand.COMMAND_WORD
            + " " + COMMAND_WORD + " "
            + PREFIX_TITLE + "The Hobbit "
            + PREFIX_AUTHOR + "J.R.R. Tolkien";
    public static final String MESSAGE_SUCCESS = "New book added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book is already in our records.";
    private final Book book;

    /**
     * Constructs a AddBookCommand for adding of a book.
     * @param book the book to add
     */
    public AddBookCommand(Book book) {
        requireNonNull(book);
        this.book = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addBook(this.book);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.book));
    }
}
