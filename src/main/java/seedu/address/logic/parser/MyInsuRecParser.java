package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.DeleteProductCommand;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListClientCommand;
import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.commands.ListProductCommand;
import seedu.address.logic.commands.ViewClientCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MyInsuRecParser {

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
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddClientCommand.COMMAND_WORD:
            return new AddClientCommandParser().parse(arguments);

        case EditClientCommand.COMMAND_WORD:
            return new EditClientCommandParser().parse(arguments);

        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_WORD:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListClientCommand.COMMAND_WORD:
            return new ListClientCommandParser().parse(arguments);

        case ViewMeetingCommand.COMMAND_WORD:
            return new ViewMeetingCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case ListMeetingCommand.COMMAND_WORD:
            return new ListMeetingCommandParser().parse(arguments);

        case ViewClientCommand.COMMAND_WORD:
            return new ViewClientCommandParser().parse(arguments);

        case AddProductCommand.COMMAND_WORD:
            return new AddProductCommandParser().parse(arguments);

        case DeleteProductCommand.COMMAND_WORD:
            return new DeleteProductCommandParser().parse(arguments);

        case ListProductCommand.COMMAND_WORD:
            return new ListProductCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
