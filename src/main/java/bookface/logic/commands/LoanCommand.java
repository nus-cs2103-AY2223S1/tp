package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, "Loans to the"
            + " user identified by the index number in user list from the book identified by the index number in book"
            + " list", "USER_INDEX (must be a positive integer), BOOK_INDEX (must be a "
            + "positive integer), [DUE_DATE] (refer to User Guide for valid date formats)",
            COMMAND_WORD + " 3 2 [2022-10-30]");

    public static final String MESSAGE_LOAN_SUCCESS = "User %1$s loaned book %2$s. (Due date: %3$s)";

    public static final String ALREADY_LOANED = "Book is already loaned out.";

    private final Index targetUserIndex;

    private final Index targetBookIndex;

    private final Date returnDate;

    private final String parsedDate;


    /**
     * Creates an LoanCommand to loan to a specified {@code Person} from the specified {@code Book} with the specified
     * return date {@code returnDate}.
     */
    public LoanCommand(Index userIndex, Index bookIndex, Date returnDate) {
        this.targetUserIndex = userIndex;
        this.targetBookIndex = bookIndex;
        this.returnDate = returnDate;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.parsedDate = formatter.format(returnDate);
    }

    /**
     * Creates an LoanCommand to loan to a specified {@code Person} from the specified {@code Book} and the return
     * date is set to 14 days later.
     */
    public LoanCommand(Index userIndex, Index bookIndex) {
        this.targetUserIndex = userIndex;
        this.targetBookIndex = bookIndex;
        // Code below is effectively borrowed from
        // https://stackoverflow.com/questions/12087419/adding-days-to-a-date-in-java
        this.returnDate = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(14));
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.parsedDate = formatter.format(returnDate);
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
        model.loan(personToLoan, bookToLoan, returnDate);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredBookList(Model.PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_LOAN_SUCCESS, personToLoan.getName(),
                bookToLoan.getTitle(), parsedDate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoanCommand // instanceof handles nulls
                && targetUserIndex.equals(((LoanCommand) other).targetUserIndex)
                && targetBookIndex.equals(((LoanCommand) other).targetBookIndex)
                && parsedDate.equals(((LoanCommand) other).parsedDate));
    }
}

