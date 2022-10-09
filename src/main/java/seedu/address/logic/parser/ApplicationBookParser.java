package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ApplicationAddCommand;
import seedu.address.logic.commands.ApplicationCommand;
import seedu.address.logic.commands.ApplicationDeleteCommand;
import seedu.address.logic.commands.ApplicationExitCommand;
import seedu.address.logic.commands.ApplicationListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ApplicationBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public ApplicationCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ApplicationAddCommand.COMMAND_WORD:
            return new ApplicationAddCommandParser().parse(arguments);

        case ApplicationDeleteCommand.COMMAND_WORD:
            return new ApplicationDeleteCommandParser().parse(arguments);

        case ApplicationListCommand.COMMAND_WORD:
            return new ApplicationListCommand();

        case ApplicationExitCommand.COMMAND_WORD:
            return new ApplicationExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
