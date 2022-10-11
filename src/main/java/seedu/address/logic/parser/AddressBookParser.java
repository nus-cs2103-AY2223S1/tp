package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.OldAddCommand;
import seedu.address.logic.commands.OldClearCommand;
import seedu.address.logic.commands.OldCommand;
import seedu.address.logic.commands.OldDeleteCommand;
import seedu.address.logic.commands.OldEditCommand;
import seedu.address.logic.commands.OldExitCommand;
import seedu.address.logic.commands.OldFindCommand;
import seedu.address.logic.commands.OldHelpCommand;
import seedu.address.logic.commands.OldListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

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
    public OldCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OldHelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case OldAddCommand.COMMAND_WORD:
            return new OldAddCommandParser().parse(arguments);

        case OldEditCommand.COMMAND_WORD:
            return new OldEditCommandParser().parse(arguments);

        case OldDeleteCommand.COMMAND_WORD:
            return new OldDeleteCommandParser().parse(arguments);

        case OldClearCommand.COMMAND_WORD:
            return new OldClearCommand();

        case OldFindCommand.COMMAND_WORD:
            return new OldFindCommandParser().parse(arguments);

        case OldListCommand.COMMAND_WORD:
            return new OldListCommand();

        case OldExitCommand.COMMAND_WORD:
            return new OldExitCommand();

        case OldHelpCommand.COMMAND_WORD:
            return new OldHelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
