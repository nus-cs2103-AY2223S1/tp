package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import longtimenosee.logic.commands.AddCommand;
import longtimenosee.logic.commands.AddEventCommand;
import longtimenosee.logic.commands.CalendarCommand;
import longtimenosee.logic.commands.ClearCommand;
import longtimenosee.logic.commands.Command;
import longtimenosee.logic.commands.DeleteCommand;
import longtimenosee.logic.commands.DeleteEventCommand;
import longtimenosee.logic.commands.EditCommand;
import longtimenosee.logic.commands.EventCommand;
import longtimenosee.logic.commands.ExitCommand;
import longtimenosee.logic.commands.FindCommand;
import longtimenosee.logic.commands.FindEventCommand;
import longtimenosee.logic.commands.FindPolicyCommand;
import longtimenosee.logic.commands.HelpCommand;
import longtimenosee.logic.commands.ListCommand;
import longtimenosee.logic.commands.ListEventsCommand;
import longtimenosee.logic.commands.PersonCommand;
import longtimenosee.logic.commands.PinCommand;
import longtimenosee.logic.commands.PolicyAddCommand;
import longtimenosee.logic.commands.PolicyAssignCommand;
import longtimenosee.logic.commands.PolicyAssignedDeleteCommand;
import longtimenosee.logic.commands.PolicyAssignedListCommand;
import longtimenosee.logic.commands.PolicyCommand;
import longtimenosee.logic.commands.PolicyDeleteCommand;
import longtimenosee.logic.commands.PolicyListCommand;
import longtimenosee.logic.commands.SortCommand;
import longtimenosee.logic.commands.ViewIncomeCommand;
import longtimenosee.logic.commands.ViewPinCommand;
import longtimenosee.logic.parser.exceptions.ParseException;


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

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindEventCommand.COMMAND_WORD:
            return new FindEventCommandParser().parse(arguments);

        case FindPolicyCommand.COMMAND_WORD:
            return new FindPolicyCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewPinCommand.COMMAND_WORD: //for pinning, creates PinCommandParser object
            return new ViewPinCommandParser().parse(arguments);

        case ViewIncomeCommand.COMMAND_WORD:
            return new ViewIncomeCommandParser().parse(arguments);

        case PinCommand.COMMAND_WORD:
            return new PinCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommand(arguments.trim());

        case PolicyAddCommand.COMMAND_WORD:
            return new PolicyAddCommandParser().parse(arguments);

        case PolicyDeleteCommand.COMMAND_WORD:
            return new PolicyDeleteCommandParser().parse(arguments);

        case PolicyListCommand.COMMAND_WORD:
            return new PolicyListCommand();

        case PolicyCommand.COMMAND_WORD:
            return new PolicyCommand();

        case CalendarCommand.COMMAND_WORD:
            return new CalendarCommand();

        case PersonCommand.COMMAND_WORD:
            return new PersonCommand();

        case PolicyAssignCommand.COMMAND_WORD:
            return new PolicyAssignCommandParser().parse(arguments);

        case PolicyAssignedListCommand.COMMAND_WORD:
            return new PolicyAssignedListCommandParser().parse(arguments);

        case PolicyAssignedDeleteCommand.COMMAND_WORD:
            return new PolicyAssignedDeleteCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case ListEventsCommand.COMMAND_WORD:
            return new ListEventsCommand();
        case EventCommand.COMMAND_WORD:
            return new EventCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
