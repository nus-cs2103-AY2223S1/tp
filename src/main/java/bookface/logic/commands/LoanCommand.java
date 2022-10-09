package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;
import bookface.model.person.Person;

/**
 * Loans to the user in the user list a book from the book list.
 */
public class LoanCommand extends Command {
    public static final String COMMAND_WORD = "loan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Loans to the user identified by the index number in user list from the book identified by the index"
            + " number in book list. \n"
            + "Parameters: INDEX (must be a positive integer), INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 3" + " 2";

    public static final String MESSAGE_LOAN_SUCCESS = "User %1$s loaned book %2$s.";

    public static final String ALREADY_LOANED = "Book is already loaned out.";


    private final Index targetUserIndex;

    private final Index targetBookIndex;


    /**
     * Creates an LoanCommand to loan to a specified {@code Person} from the specified {@code Book}
     */
    public LoanCommand(Index userIndex, Index bookIndex) {
        this.targetUserIndex = userIndex;
        this.targetBookIndex = bookIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Book> lastShownBookList = model.getFilteredBookList();

        if (targetUserIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (targetBookIndex.getZeroBased() >= lastShownBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Person personToLoan = lastShownPersonList.get(targetUserIndex.getZeroBased());
        Book bookToLoan = lastShownBookList.get(targetBookIndex.getZeroBased());

        if (bookToLoan.isLoaned()) {
            throw new CommandException(ALREADY_LOANED);
        }

        model.loan(personToLoan, bookToLoan);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredBookList(Model.PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_LOAN_SUCCESS, personToLoan.getName(), bookToLoan.getTitle()));
    }
}

