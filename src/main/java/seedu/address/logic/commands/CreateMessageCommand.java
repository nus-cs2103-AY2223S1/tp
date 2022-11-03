package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.message.Message;

/**
 *  Creates a message template in the address book.
 */
public class CreateMessageCommand extends MessageCommandGroup {

    public static final String COMMAND_SPECIFIER = "create";
    public static final String COMMAND_SPECIFIER_ALIAS = "c";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates the message. "
            + "\nParameters: [MESSAGE]"
            + "\nExample: " + COMMAND_WORD + " Hello {name}!";

    public static final String MESSAGE_SUCCESS = "New message created: %1$s";
    public static final String MESSAGE_DUPLICATE_MESSAGE = "Message %1$s already exist in the address book";

    private final Message toCreate;

    /**
     * @param toCreate the message to add to the address book
     */
    public CreateMessageCommand(Message toCreate) {
        requireNonNull(toCreate);
        this.toCreate = toCreate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMessage(toCreate)) {
            return new CommandResult(String.format(MESSAGE_DUPLICATE_MESSAGE, toCreate));
        }

        model.createMessage(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateMessageCommand)) {
            return false;
        }

        // state check
        CreateMessageCommand e = (CreateMessageCommand) other;
        return toCreate.equals(e.toCreate);
    }
}
