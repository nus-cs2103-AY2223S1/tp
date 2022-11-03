package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.message.Message;
import seedu.address.ui.SecondaryPaneState;


/**
 * Lists all messages in the message list.
 */
public class ListMessageCommand extends MessageCommandGroup {

    public static final String COMMAND_SPECIFIER = "list";
    public static final String COMMAND_SPECIFIER_ALIAS = "l";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all messages. ";

    public static final String MESSAGE_SUCCESS = "Showing your message templates.";
    public static final String MESSAGE_NO_MESSAGES = "You currently have no message templates. ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Message> messages = model.getMessages();

        if (messages.isEmpty()) {
            return new CommandResult(MESSAGE_NO_MESSAGES);
        }

        model.clearTargetPerson();

        return new CommandResult(MESSAGE_SUCCESS, SecondaryPaneState.MESSAGE_TEMPLATES);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListMessageCommand;
    }
}
