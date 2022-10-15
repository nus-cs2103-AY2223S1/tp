package seedu.condonery.logic.parser;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.condonery.logic.commands.ClearCommand;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.EditCommand;
import seedu.condonery.logic.commands.ExitCommand;
import seedu.condonery.logic.commands.HelpCommand;
import seedu.condonery.logic.commands.client.AddClientCommand;
import seedu.condonery.logic.commands.client.FilterClientCommand;
import seedu.condonery.logic.commands.client.FindClientCommand;
import seedu.condonery.logic.commands.client.ListClientCommand;
import seedu.condonery.logic.commands.property.AddPropertyCommand;
import seedu.condonery.logic.commands.property.DeletePropertyCommand;
import seedu.condonery.logic.commands.property.FilterPropertyCommand;
import seedu.condonery.logic.commands.property.FindPropertyCommand;
import seedu.condonery.logic.commands.property.ListPropertyCommand;
import seedu.condonery.logic.parser.client.AddClientCommandParser;
import seedu.condonery.logic.parser.client.FilterClientCommandParser;
import seedu.condonery.logic.parser.client.FindClientCommandParser;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.logic.parser.property.AddPropertyCommandParser;
import seedu.condonery.logic.parser.property.DeletePropertyCommandParser;
import seedu.condonery.logic.parser.property.FilterPropertyCommandParser;
import seedu.condonery.logic.parser.property.FindPropertyCommandParser;


/**
 * Parses user input.
 */
public class CondoneryParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<commandWord>\\S+(\\s-[pc]{1})*)(?<arguments>.*)");

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
        //CHECKSTYLE.ON: Regexp
        case AddPropertyCommand.COMMAND_WORD:
            return new AddPropertyCommandParser().parse(arguments);

        case AddClientCommand.COMMAND_WORD:
            return new AddClientCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeletePropertyCommand.COMMAND_WORD:
            return new DeletePropertyCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindPropertyCommand.COMMAND_WORD:
            return new FindPropertyCommandParser().parse(arguments);

        case FindClientCommand.COMMAND_WORD:
            return new FindClientCommandParser().parse(arguments);

        case FilterPropertyCommand.COMMAND_WORD:
            return new FilterPropertyCommandParser().parse(arguments);

        case FilterClientCommand.COMMAND_WORD:
            return new FilterClientCommandParser().parse(arguments);

        case ListPropertyCommand.COMMAND_WORD:
            return new ListPropertyCommand();

        case ListClientCommand.COMMAND_WORD:
            return new ListClientCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
