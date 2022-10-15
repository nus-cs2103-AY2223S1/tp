package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a Professor to the address book.
 */
public class ProfCommand extends Command {

    public static final String COMMAND_WORD = "prof";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a professor to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + "[" + PREFIX_TAG + "TAG]... "
            + PREFIX_LOCATION + "LOCATION "
            + "[" + PREFIX_RATING + "RATING]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Wong Tin Lok "
            + PREFIX_MODULE_CODE + "CS1231S "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "wongTK@example.com "
            + PREFIX_GENDER + "M "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_LOCATION + "COM2 LT4 "
            + PREFIX_RATING + "5";

    public static final String MESSAGE_DUPLICATE_PERSON = "This Professor already exists in the address book";
    public static final String MESSAGE_SUCCESS = "New professor added: %1$s";

    private final Person toAdd;

    /**
     * Creates a ProfCommand to add the specified {@code Professor}
     */
    public ProfCommand(Person toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfCommand // instanceof handles nulls
                && toAdd.equals(((ProfCommand) other).toAdd));
    }
}
