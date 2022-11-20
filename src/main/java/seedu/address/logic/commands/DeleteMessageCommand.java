package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.message.Message;
import seedu.address.ui.SecondaryPaneState;


/**
 * Removes a message template from the address book.
 */
public class DeleteMessageCommand extends MessageCommandGroup {

    public static final String COMMAND_SPECIFIER = "delete";
    public static final String COMMAND_SPECIFIER_ALIAS = "d";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the message. "
            + "\nParameters: [MESSAGE]"
            + "\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Message deleted: %1$s";

    private final Index index;

    /**
     * @param index messages to remove from the address book
     */
    public DeleteMessageCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Message> messages = model.getMessages();
        if (index.getZeroBased() >= messages.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MESSAGE_INDEX);
        }
        Message message = messages.get(index.getZeroBased());

        model.deleteMessage(message);
        model.clearTargetPerson();

        if (model.getMessages().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, message),
                SecondaryPaneState.WELCOME);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, message),
                SecondaryPaneState.MESSAGE_TEMPLATES);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMessageCommand)) {
            return false;
        }

        // state check
        DeleteMessageCommand e = (DeleteMessageCommand) other;
        return index.equals(e.index);
    }
}
