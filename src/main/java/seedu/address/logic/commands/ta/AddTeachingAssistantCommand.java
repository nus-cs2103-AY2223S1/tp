package seedu.address.logic.commands.ta;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ta.TeachingAssistant;

/**
 * Adds a teaching assistant to ModQuik.
 */
public class AddTeachingAssistantCommand extends Command {
    public static final String COMMAND_WORD = "add ta";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Teaching Assistant to the ModQuik. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "JOHN "
            + PREFIX_ID + "A0123456B ";

    public static final String MESSAGE_SUCCESS = "New Teaching Assistant added: %1$s";
    public static final String MESSAGE_DUPLICATE_TA = "This Teaching Assistant already exists in the ModQuik";

    private final TeachingAssistant toAdd;

    /**
     * Creates an AddTeachingAssistantCommand to add the specified {@code TeachingAssistant}
     */
    public AddTeachingAssistantCommand(TeachingAssistant ta) {
        requireNonNull(ta);
        toAdd = ta;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeachingAssistant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TA);
        }
        model.addTeachingAssistant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTeachingAssistantCommand // instanceof handles nulls
                && toAdd.equals(((AddTeachingAssistantCommand) other).toAdd));
    }
}
