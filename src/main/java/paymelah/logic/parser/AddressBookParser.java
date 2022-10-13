package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import paymelah.logic.commands.AddCommand;
import paymelah.logic.commands.AddDebtCommand;
import paymelah.logic.commands.ClearCommand;
import paymelah.logic.commands.ClearDebtsCommand;
import paymelah.logic.commands.Command;
import paymelah.logic.commands.DeleteCommand;
import paymelah.logic.commands.DeleteDebtCommand;
import paymelah.logic.commands.EditCommand;
import paymelah.logic.commands.ExitCommand;
import paymelah.logic.commands.FindCommand;
import paymelah.logic.commands.FindDebtCommand;
import paymelah.logic.commands.HelpCommand;
import paymelah.logic.commands.ListCommand;
import paymelah.logic.commands.ListDebtorsCommand;
import paymelah.logic.commands.StatementCommand;
import paymelah.logic.parser.exceptions.ParseException;

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

        case AddDebtCommand.COMMAND_WORD:
            return new AddDebtCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteDebtCommand.COMMAND_WORD:
            return new DeleteDebtCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindDebtCommand.COMMAND_WORD:
            return new FindDebtCommandParser().parse(arguments);

        case StatementCommand.COMMAND_WORD:
            return new StatementCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ClearDebtsCommand.COMMAND_WORD:
            return new ClearDebtsCommandParser().parse(arguments);

        case ListDebtorsCommand.COMMAND_WORD:
            return new ListDebtorsCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
