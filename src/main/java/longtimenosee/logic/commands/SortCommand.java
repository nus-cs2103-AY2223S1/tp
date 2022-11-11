package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.model.person.Birthday.BIRTHDAY_COMPARATOR;
import static longtimenosee.model.person.Birthday.SORT_BIRTHDAY;
import static longtimenosee.model.person.Email.EMAIL_COMPARATOR;
import static longtimenosee.model.person.Email.SORT_EMAIL;
import static longtimenosee.model.person.Income.INCOME_COMPARATOR;
import static longtimenosee.model.person.Income.SORT_INCOME;
import static longtimenosee.model.person.Name.NAME_COMPARATOR;
import static longtimenosee.model.person.Name.SORT_NAME;
import static longtimenosee.model.person.Person.DEFAULT_COMPARATOR;
import static longtimenosee.model.person.Person.SORT_DEFAULT;
import static longtimenosee.model.person.Phone.PHONE_COMPARATOR;
import static longtimenosee.model.person.Phone.SORT_PHONE;
import static longtimenosee.model.person.RiskAppetite.RISK_APPETITE_COMPARATOR;
import static longtimenosee.model.person.RiskAppetite.SORT_RISK_APPETITE;

import java.util.Comparator;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;

/**
 * Sorts the list of Clients based on a user specified metric.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    private static final String DEFAULT_SUCCESS_MESSAGE = "Clients sorted in default order (based on time added)";
    private static final String INVALID_MESSAGE = "There is either no such client details "
                                                    + "or the sorting metric is invalid.";
    private static final String MESSAGE_SUCCESS = "Clients sorted by ";


    private String toSort;

    /**
     * Creates a sort command to sort the address book according to
     * user specified metric
     * @param toSort user specified metric
     */
    public SortCommand(String toSort) {
        requireNonNull(toSort);
        this.toSort = toSort;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Comparator<Person> comparator;

        switch (toSort) {
        case SORT_EMAIL:
            comparator = EMAIL_COMPARATOR;
            break;

        case SORT_NAME:
            comparator = NAME_COMPARATOR;
            break;

        case SORT_PHONE:
            comparator = PHONE_COMPARATOR;
            break;

        case SORT_DEFAULT:
            comparator = DEFAULT_COMPARATOR;
            break;

        case SORT_BIRTHDAY:
            comparator = BIRTHDAY_COMPARATOR;
            break;

        case SORT_INCOME:
            comparator = INCOME_COMPARATOR;
            break;

        case SORT_RISK_APPETITE:
            comparator = RISK_APPETITE_COMPARATOR;
            break;

        default:
            throw new CommandException(INVALID_MESSAGE);
        }
        requireNonNull(comparator);
        model.sort(comparator);
        if (this.toSort.equals(SORT_DEFAULT)) {
            return new CommandResult(DEFAULT_SUCCESS_MESSAGE, false, true, false);
        } else {
            return new CommandResult(MESSAGE_SUCCESS + this.toSort, false, true, false);
        }
    }

    /**
     * Returns the invalid message when providing an invalid sorting metric
     */
    public static String getInvalidMessage() {
        return INVALID_MESSAGE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand e = (SortCommand) other;
        return this.toSort == ((SortCommand) other).toSort;
    }


}
