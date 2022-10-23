package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.CreateMessageCommand;
import seedu.address.logic.commands.DeleteMessageCommand;
import seedu.address.logic.commands.GenerateMessageCommand;
import seedu.address.logic.commands.ListMessageCommand;
import seedu.address.logic.commands.MessageCommandGroup;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for the MessageCommandGroup, and returns the desired command
 * in the MessageCommandGroup, as indicated by the commandSpecifier.
 */
public class MessageCommandGroupParser implements Parser<MessageCommandGroup> {
    // TODO: add complete list of commands
    public static final String MESSAGE_USAGE = String.format("%s\n\n%s\n\n%s\n\n%s",
            ListMessageCommand.MESSAGE_USAGE,
            CreateMessageCommand.MESSAGE_USAGE,
            DeleteMessageCommand.MESSAGE_USAGE,
            GenerateMessageCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the
     * and returns a command in the MessageCommandGroup for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MessageCommandGroup parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        String[] argArray = trimmedArgs.split("\\s+");
        String commandSpecifier = argArray[0];

        switch (commandSpecifier) {
        case CreateMessageCommand.COMMAND_SPECIFIER:
            // Fallthrough
        case CreateMessageCommand.COMMAND_SPECIFIER_ALIAS:
            String[] argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new CreateMessageCommandParser().parse(String.join(" ", argsToPass));
        case DeleteMessageCommand.COMMAND_SPECIFIER:
            // Fallthrough
        case DeleteMessageCommand.COMMAND_SPECIFIER_ALIAS:
            argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new DeleteMessageCommandParser().parse(String.join(" ", argsToPass));
        case GenerateMessageCommand.COMMAND_SPECIFIER:
            // Fallthrough
        case GenerateMessageCommand.COMMAND_SPECIFIER_ALIAS:
            argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new GenerateMessageCommandParser().parse(String.join(" ", argsToPass));
        case ListMessageCommand.COMMAND_SPECIFIER:
            // Fallthrough
        case ListMessageCommand.COMMAND_SPECIFIER_ALIAS:
            return new ListMessageCommandParser().parse("");
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
