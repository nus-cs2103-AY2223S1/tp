package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class RemoveFieldCommand extends Command {

    public static final String COMMAND_WORD = "rmfield";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a custom field from the address book. "
            + "Parameters: FIELD";

    public static final String MESSAGE_SUCCESS = "Field removed: %1$s";

    private String fieldName;

    public RemoveFieldCommand(String fieldName) {
        requireNonNull(fieldName);
        this.fieldName = fieldName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            PREFIX_FIELD.removeField(fieldName, model);
        } catch (ParseException err) {
            throw new CommandException(err.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, fieldName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveFieldCommand // instanceof handles nulls
                && fieldName.equals(((RemoveFieldCommand) other).fieldName)); // state check
    }
}
