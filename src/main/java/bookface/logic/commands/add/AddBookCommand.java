package bookface.logic.commands.add;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import bookface.commons.core.Messages;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;

/**
 * The command that adds a book to the BookList.
 */
public class AddBookCommand extends AddCommand {
    public static final String COMMAND_WORD = "book";
    public static final String MESSAGE_USAGE =
            AddCommand.generateMessage(COMMAND_WORD, PREFIX_TITLE + "TITLE "
                    + PREFIX_AUTHOR + "AUTHOR " , COMMAND_WORD
                    + " t/The Broken House a/John Doe");

    public static final String MESSAGE_SUCCESS = "New book added: %s by %s";
    private final Book bookToAdd;

    /**
     * Constructs a AddBookCommand for adding of a book.
     * @param book the book to add
     */
    public AddBookCommand(Book book) {
        requireNonNull(book);
        this.bookToAdd = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBook(this.bookToAdd)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_BOOK);
        }

        model.addBook(this.bookToAdd);
        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                this.bookToAdd.getTitle(),
                this.bookToAdd.getAuthor()));
    }
}
