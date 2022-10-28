package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommissionCommand;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AllCommissionCommand;
import seedu.address.logic.commands.AllTagCustomerCommand;
import seedu.address.logic.commands.AnyTagCustomerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommissionCommand;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.EditCommissionCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommissionCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListCommissionCommand;
import seedu.address.logic.commands.OpenCommissionCommand;
import seedu.address.logic.commands.OpenCustomerCommand;
import seedu.address.logic.commands.SortCustomerCommand;
import seedu.address.logic.commands.iteration.AddIterationCommand;
import seedu.address.logic.commands.iteration.DeleteIterationCommand;
import seedu.address.logic.commands.iteration.EditIterationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.iteration.AddIterationCommandParser;
import seedu.address.logic.parser.iteration.DeleteIterationCommandParser;
import seedu.address.logic.parser.iteration.EditIterationCommandParser;

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

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case AddCommissionCommand.COMMAND_WORD:
            return new AddCommissionCommandParser().parse(arguments);

        case OpenCustomerCommand.COMMAND_WORD:
            return new OpenCustomerCommandParser().parse(arguments);

        case OpenCommissionCommand.COMMAND_WORD:
            return new OpenCommissionCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case EditCommissionCommand.COMMAND_WORD:
            return new EditCommissionCommandParser().parse(arguments);

        case EditIterationCommand.COMMAND_WORD:
            return new EditIterationCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindCommissionCommand.COMMAND_WORD:
            return new FindCommissionCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListCommissionCommand.COMMAND_WORD:
            return new ListCommissionCommand();

        case AllCommissionCommand.COMMAND_WORD:
            return new AllCommissionCommand();

        case AddIterationCommand.COMMAND_WORD:
            return new AddIterationCommandParser().parse(arguments);

        case DeleteIterationCommand.COMMAND_WORD:
            return new DeleteIterationCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case DeleteCommissionCommand.COMMAND_WORD:
            return new DeleteCommissionCommandParser().parse(arguments);

        case AnyTagCustomerCommand.COMMAND_WORD:
            return new AnyTagCustomerCommandParser().parse(arguments);

        case AllTagCustomerCommand.COMMAND_WORD:
            return new AllTagCustomerCommandParser().parse(arguments);

        case SortCustomerCommand.COMMAND_WORD:
            return new SortCustomerCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
