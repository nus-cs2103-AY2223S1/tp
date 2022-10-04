package bookface.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bookface.commons.core.Messages;
import bookface.logic.commands.Command;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public abstract class CommandParser<T extends Command> implements Parser<T> {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private final String messageUsage;

    public CommandParser(String messageUsage) {
        this.messageUsage = messageUsage;
    }

    /**
     * Parses {@code command} into a command and returns it.
     * @param command The user's command
     * @return The {@code Command} based on the input
     * @throws ParseException if {@code command} does not conform to the expected format
     */
    public final T parse(String command) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(command.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
        final String commandWord = matcher.group("commandWord");
        final String args = matcher.group("arguments");
        return handleParsedCommand(commandWord, args);
    }

    // Idea for forcing all subclasses to use the parse method implemented in the abstract class
    // is modified from https://stackoverflow.com/a/4217164/13742805
    /**
     * Handles the parsed command.
     */
    protected abstract T handleParsedCommand(String commandWord, String args) throws ParseException;
}
