package seedu.rc4hdb.logic.parser;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.logic.commands.misccommands.ExitCommand;
import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.modelcommands.AddCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ClearCommand;
import seedu.rc4hdb.logic.commands.modelcommands.DeleteCommand;
import seedu.rc4hdb.logic.commands.modelcommands.EditCommand;
import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand;
import seedu.rc4hdb.logic.commands.modelcommands.FindCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.logic.commands.storagemodelcommand.FileCommand;
import seedu.rc4hdb.logic.parser.commandparsers.AddCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.DeleteCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.EditCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.FileCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.FilterCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.FindCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.ListCommandParser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ResidentBookParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case FileCommand.COMMAND_WORD:
            return new FileCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
