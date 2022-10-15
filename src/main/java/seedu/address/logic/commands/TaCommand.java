package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TeachingAssistant;

/**
 * Adds a Teaching Assistant to the address book.
 */
public class TaCommand extends Command {

    public static final String COMMAND_WORD = "ta";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Teaching Assistant to the address book. "
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
        + PREFIX_NAME + "Alice Doe "
        + PREFIX_MODULE_CODE + "CS2100 "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "AliceD@example.com "
        + PREFIX_GENDER + "F "
        + PREFIX_TAG + "friends "
        + PREFIX_TAG + "owesMoney "
        + PREFIX_LOCATION + "COM1-0203 "
        + PREFIX_RATING + "5";

    public static final String MESSAGE_DUPLICATE_PERSON = "This Teaching Assistant already exists in the address book";

    public static final String MESSAGE_SUCCESS = "New Teaching Assistant added: %1$s";

    private final Person toAdd;

    /**
     * Creates a TACommand to add the specified {@code person}
     */
    public TaCommand(TeachingAssistant toAdd) {
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
            || (other instanceof TaCommand // instanceof handles nulls
            && toAdd.equals(((TaCommand) other).toAdd));
    }
}
