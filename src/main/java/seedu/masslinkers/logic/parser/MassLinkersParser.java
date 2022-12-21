package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.masslinkers.logic.commands.AddCommand;
import seedu.masslinkers.logic.commands.AddInterestCommand;
import seedu.masslinkers.logic.commands.ClearCommand;
import seedu.masslinkers.logic.commands.Command;
import seedu.masslinkers.logic.commands.DeleteCommand;
import seedu.masslinkers.logic.commands.DeleteInterestCommand;
import seedu.masslinkers.logic.commands.EditCommand;
import seedu.masslinkers.logic.commands.ExitCommand;
import seedu.masslinkers.logic.commands.FindCommand;
import seedu.masslinkers.logic.commands.FindInterestCommand;
import seedu.masslinkers.logic.commands.HelpCommand;
import seedu.masslinkers.logic.commands.ListCommand;
import seedu.masslinkers.logic.commands.ModCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MassLinkersParser {

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
            return new ListCommand();

        case FindInterestCommand.COMMAND_WORD:
            return new FindInterestCommandParser().parse(arguments);

        case AddInterestCommand.COMMAND_WORD:
            return new AddInterestCommandParser().parse(arguments);

        case DeleteInterestCommand.COMMAND_WORD:
            return new DeleteInterestCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ModCommand.COMMAND_WORD:
            return new ModCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
