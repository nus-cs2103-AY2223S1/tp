package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Adds a person to the address book.
 */
public class AddFieldCommand extends Command {

    public static final String COMMAND_WORD = "field";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a custom field to the address book. "
            + "Parameters: CUSTOM_PREFIX FIELD";

    public static final String MESSAGE_SUCCESS = "New field added: %1$s";

    private final Prefix prefix;
    private final String fieldName;

    /**
     * Constructs a new AddFieldCommand instance.
     *
     * @param prefix The Prefix for the field.
     * @param fieldName The name of the field.
     */
    public AddFieldCommand(Prefix prefix, String fieldName) {
        requireAllNonNull(prefix, fieldName);
        this.prefix = prefix;
        this.fieldName = fieldName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            PREFIX_FIELD.addPrefix(prefix, fieldName, model);
        } catch (ParseException err) {
            throw new CommandException(err.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, fieldName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFieldCommand // instanceof handles nulls
                && prefix.equals(((AddFieldCommand) other).prefix)
                && fieldName.equals(((AddFieldCommand) other).fieldName));
    }
}
