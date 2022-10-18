package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.message.Message;


/**
 *
 */
public class ListMessageCommand extends MessageCommandGroup {

    public static final String COMMAND_SPECIFIER = "list";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all messages. ";

    public static final String MESSAGE_SUCCESS = "Your message templates: %1$s";
    public static final String MESSAGE_NO_MESSAGES = "You currently have no message templates. ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Message> messages = model.getMessages();

        if (messages.isEmpty()) {
            return new CommandResult(MESSAGE_NO_MESSAGES);
        }

        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < messages.size(); ++i) {
            sb.append(i + ". " + messages.get(i) + "\n");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListMessageCommand;
    }
}
