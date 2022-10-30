package seedu.boba.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_BIRTHDAY_MONTH;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_REWARD;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.boba.model.customer.Customer.BIRTHDAY_TAG;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.model.Model;
import seedu.boba.model.customer.Customer;

import java.time.LocalDate;

/**
 * Adds a customer to bobaBot.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to bobaBot. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_BIRTHDAY_MONTH + "BIRTHDAY_MONTH "
            + PREFIX_REWARD + "REWARD "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_BIRTHDAY_MONTH + "1 "
            + PREFIX_REWARD + "5000 "
            + PREFIX_TAG + "GOLD "
            + PREFIX_TAG + "MEMBER";

    public static final String MESSAGE_SUCCESS = "New customer added: %1$s";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in bobaBot";

    private final Customer toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Customer}
     */
    public AddCommand(Customer customer) {
        requireNonNull(customer);
        toAdd = customer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        LocalDate currentDate = LocalDate.now();
        String currentMonth = String.valueOf(currentDate.getMonth().getValue());
        if (toAdd.getTags().contains(BIRTHDAY_TAG)
                && !toAdd.getBirthdayMonth().value.equals(currentMonth)) {
            toAdd.removeBirthdayTag();
        } else if (!toAdd.getTags().contains(BIRTHDAY_TAG)
                && toAdd.getBirthdayMonth().value.equals(currentMonth)) {
            toAdd.addBirthdayTag();
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
