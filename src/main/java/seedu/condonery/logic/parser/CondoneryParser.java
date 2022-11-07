package seedu.condonery.logic.parser;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.ExitCommand;
import seedu.condonery.logic.commands.HelpCommand;
import seedu.condonery.logic.commands.UndoCommand;
import seedu.condonery.logic.commands.client.ClearClientCommand;
import seedu.condonery.logic.commands.client.ListClientCommand;
import seedu.condonery.logic.commands.property.ClearPropertyCommand;
import seedu.condonery.logic.commands.property.ListPropertyCommand;
import seedu.condonery.logic.parser.client.AddClientCommandParser;
import seedu.condonery.logic.parser.client.DeleteClientCommandParser;
import seedu.condonery.logic.parser.client.EditClientCommandParser;
import seedu.condonery.logic.parser.client.FilterClientCommandParser;
import seedu.condonery.logic.parser.client.FindClientCommandParser;
import seedu.condonery.logic.parser.client.SelectClientCommandParser;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.logic.parser.property.AddPropertyCommandParser;
import seedu.condonery.logic.parser.property.DeletePropertyCommandParser;
import seedu.condonery.logic.parser.property.EditPropertyCommandParser;
import seedu.condonery.logic.parser.property.FilterPropertyCommandParser;
import seedu.condonery.logic.parser.property.FindPropertyCommandParser;
import seedu.condonery.logic.parser.property.RangePropertyCommandParser;
import seedu.condonery.logic.parser.property.SelectPropertyCommandParser;
import seedu.condonery.logic.parser.property.StatusPropertyCommandParser;
import seedu.condonery.logic.parser.property.TypePropertyCommandParser;

/**
 * Parses user input.
 */
public class CondoneryParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<commandWord>\\S+)(?<flag>\\s+-[pc]{1})*(?<arguments>.*)");

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

        final String commandRoot = matcher.group("commandWord");
        final String flag = matcher.group("flag");
        final String arguments = matcher.group("arguments");
        final CommandEnum commandCase;

        try {
            commandCase = CommandEnum.valueOf(commandRoot.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        if (flag != null && flag.trim().equals("-p")) {
            // commands with property flag
            switch (commandCase) {

            case ADD:
                return new AddPropertyCommandParser().parse(arguments);

            case EDIT:
                return new EditPropertyCommandParser().parse(arguments);

            case DELETE:
                return new DeletePropertyCommandParser().parse(arguments);

            case SELECT:
                return new SelectPropertyCommandParser().parse(arguments);

            case CLEAR:
                return new ClearPropertyCommand();

            case STATUS:
                return new StatusPropertyCommandParser().parse(arguments);

            case FIND:
                return new FindPropertyCommandParser().parse(arguments);

            case FILTER:
                return new FilterPropertyCommandParser().parse(arguments);

            case RANGE:
                return new RangePropertyCommandParser().parse(arguments);

            case TYPE:
                return new TypePropertyCommandParser().parse(arguments);

            case LIST:
                return new ListPropertyCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

            }

        }

        if (flag != null && flag.trim().equals("-c")) {

            switch (commandCase) {
            // commands with client flag
            case ADD:
                return new AddClientCommandParser().parse(arguments);

            case EDIT:
                return new EditClientCommandParser().parse(arguments);

            case DELETE:
                return new DeleteClientCommandParser().parse(arguments);

            case SELECT:
                return new SelectClientCommandParser().parse(arguments);

            case CLEAR:
                return new ClearClientCommand();

            case FIND:
                return new FindClientCommandParser().parse(arguments);

            case FILTER:
                return new FilterClientCommandParser().parse(arguments);

            case LIST:
                return new ListClientCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

            }

        }
        // commands without property or client flag
        switch (commandCase) {

        case EXIT:
            return new ExitCommand();

        case HELP:
            return new HelpCommand();

        case UNDO:
            return new UndoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }

    }
}
