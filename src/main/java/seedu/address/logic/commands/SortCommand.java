package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Sorts the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sort the list of contacts displayed by certain parameter(s).\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "] "
        + "[" + PREFIX_PHONE + "] "
        + "[" + PREFIX_EMAIL + "] "
        + "[" + PREFIX_ADDRESS + "] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_NAME;

    public static final String MESSAGE_SUCCESS = "List has been sorted by: ";
    public static final String MESSAGE_WRONG_PREFIX = "Invalid parameter";

    private final Prefix parameter;
    private final Boolean isReverse;

    /**
     * Creates a SortCommand to sort the address book.
     */
    public SortCommand(Prefix prefix, Boolean isReverse) {
        requireAllNonNull(prefix, isReverse);
        this.parameter = prefix;
        this.isReverse = isReverse;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (parameter.equals(PREFIX_NAME)) {
            model.sortByName();
        } else if (parameter.equals(PREFIX_PHONE)) {
            model.sortByPhone();
        } else if (parameter.equals(PREFIX_EMAIL)) {
            model.sortByEmail();
        } else if (parameter.equals(PREFIX_ADDRESS)) {
            model.sortByAddress();
        } else {
            throw new CommandException(MESSAGE_WRONG_PREFIX);
        }

        if (isReverse) {
            model.reverseSort();
        }

        return new CommandResult(MESSAGE_SUCCESS + parameter.getPrefix());
    }
}
