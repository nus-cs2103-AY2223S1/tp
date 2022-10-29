package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.travelr.logic.commands.AddCommand;
import seedu.travelr.logic.commands.AddEventCommand;
import seedu.travelr.logic.commands.AddEventToTripCommand;
import seedu.travelr.logic.commands.ClearCommand;
import seedu.travelr.logic.commands.Command;
import seedu.travelr.logic.commands.DeleteCommand;
import seedu.travelr.logic.commands.DeleteEventCommand;
import seedu.travelr.logic.commands.DeleteEventFromTripCommand;
import seedu.travelr.logic.commands.DisplayEventCommand;
import seedu.travelr.logic.commands.DisplayTripCommand;
import seedu.travelr.logic.commands.EventListCommand;
import seedu.travelr.logic.commands.ExitCommand;
import seedu.travelr.logic.commands.HelpCommand;
import seedu.travelr.logic.commands.ListCommand;
import seedu.travelr.logic.commands.MarkTripDoneCommand;
import seedu.travelr.logic.commands.SelectCommand;
import seedu.travelr.logic.commands.SortEventsCommand;
import seedu.travelr.logic.commands.SortTripsCommand;
import seedu.travelr.logic.commands.SummaryCommand;
import seedu.travelr.logic.commands.UnmarkDoneTripCommand;
import seedu.travelr.logic.commands.ViewAllCommand;
import seedu.travelr.logic.commands.ViewCompletedCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TravelrParser {

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

        case UnmarkDoneTripCommand.COMMAND_WORD:
            return new UnmarkDoneTripCommandParser().parse(arguments);

        case MarkTripDoneCommand.COMMAND_WORD:
            return new MarkTripDoneCommandParser().parse(arguments);

        case DeleteEventFromTripCommand.COMMAND_WORD:
            return new DeleteEventFromTripCommandParser().parse(arguments);

        case AddEventToTripCommand.COMMAND_WORD:
            return new AddEventToTripCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DisplayTripCommand.COMMAND_WORD:
            return new DisplayTripCommandParser().parse(arguments);

        case DisplayEventCommand.COMMAND_WORD:
            return new DisplayEventCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case EventListCommand.COMMAND_WORD:
            return new EventListCommand();

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewAllCommand.COMMAND_WORD:
            return new ViewAllCommand();

        case ViewCompletedCommand.COMMAND_WORD:
            return new ViewCompletedCommand();

        case SortTripsCommand.COMMAND_WORD:
            return new SortTripsCommandParser().parse(arguments);

        case SummaryCommand.COMMAND_WORD:
            return new SummaryCommand();

        case SortEventsCommand.COMMAND_WORD:
            return new SortEventsCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
