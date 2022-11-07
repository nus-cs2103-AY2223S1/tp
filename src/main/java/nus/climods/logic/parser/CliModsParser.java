package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nus.climods.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nus.climods.logic.commands.AddCommand;
import nus.climods.logic.commands.Command;
import nus.climods.logic.commands.DeleteCommand;
import nus.climods.logic.commands.ExitCommand;
import nus.climods.logic.commands.FindCommand;
import nus.climods.logic.commands.HelpCommand;
import nus.climods.logic.commands.ListCommand;
import nus.climods.logic.commands.PickCommand;
import nus.climods.logic.commands.PrereqsCommand;
import nus.climods.logic.commands.ViewCommand;
import nus.climods.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CliModsParser {

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
    public static Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Invalid command"));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):
            return new AddCommandParser().parse(arguments);
        case (DeleteCommand.COMMAND_WORD):
            return new DeleteCommandParser().parse(arguments);
        case (ExitCommand.COMMAND_WORD):
            return new ExitCommandParser().parse(arguments);
        case (ListCommand.COMMAND_WORD):
            return new ListCommandParser().parse(arguments);
        case (FindCommand.COMMAND_WORD):
            return new FindCommandParser().parse(arguments);
        case (HelpCommand.COMMAND_WORD):
            return new HelpCommandParser().parse(arguments);
        case (PickCommand.COMMAND_WORD):
            return new PickCommandParser().parse(arguments);
        case (ViewCommand.COMMAND_WORD):
            return new ViewCommandParser().parse(arguments);
        case (PrereqsCommand.COMMAND_WORD):
            return new PrereqsCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
