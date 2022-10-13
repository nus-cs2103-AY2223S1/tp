package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_DATE_RANGE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_IS_ROOM_CLEAN;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NUMBER_OF_GUESTS;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
import seedu.guest.model.guest.Guest;

/**
 * Adds a guest to the guest book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a guest to the guest book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DATE_RANGE + "DATE_RANGE "
            + PREFIX_NUMBER_OF_GUESTS + "NUMBER_OF_GUESTS "
            + PREFIX_IS_ROOM_CLEAN + "IS_ROOM_CLEAN "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_DATE_RANGE + "13/09/22 - 15/09/22 "
            + PREFIX_NUMBER_OF_GUESTS + "1 "
            + PREFIX_IS_ROOM_CLEAN + "yes";

    public static final String MESSAGE_SUCCESS = "New guest added: %1$s";
    public static final String MESSAGE_DUPLICATE_GUEST = "This guest already exists in the guest book";

    private final Guest toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Guest}
     */
    public AddCommand(Guest guest) {
        requireNonNull(guest);
        toAdd = guest;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGuest(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GUEST);
        }

        model.addGuest(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
