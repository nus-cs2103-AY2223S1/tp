package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_FLAG_NOT_SPECIFIED;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.event.AddProfilesToEventCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.event.DeleteProfilesFromEventCommand;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.logic.commands.event.EventCommand;
import seedu.address.logic.commands.event.FindEventCommand;
import seedu.address.logic.commands.event.ViewEventsCommand;
import seedu.address.logic.commands.event.ViewUpcomingEventsCommand;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EventCommand object
 */
public class EventCommandParser implements Parser<EventCommand> {
    private static final Pattern EVENT_COMMAND_FORMAT = Pattern.compile(
            "\\s+(-(?<eventOption>\\S*))(\\s+(?!-)\\S+)*");

    /**
     * Parses the given {@code String} of arguments in the context of the EventCommand
     * and returns an EventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventCommand parse(String args) throws ParseException {

        if (!args.matches("\\s+-.*")) {
            Matcher matcher = Pattern.compile("(\\s+-\\S*)").matcher(args);
            long numFlags = matcher.results().count();
            if (numFlags == 0) {
                throw new ParseException(String.format(
                        MESSAGE_FLAG_NOT_SPECIFIED, EventCommand.EVENT_FORMAT + EventCommand.VALID_FLAGS));
            } else if (numFlags == 1) {
                throw new ParseException(EventCommand.OPTION_WRONG_ORDER);
            } else {
                throw new ParseException(EventCommand.OPTION_WRONG_ORDER_NO_MULTIPLE);
            }
        }

        final Matcher eventCommandMatcher = EVENT_COMMAND_FORMAT.matcher(args);

        if (!eventCommandMatcher.matches()) {
            throw new ParseException(EventCommand.OPTION_NO_MULTIPLE);
        }

        String eventOption = eventCommandMatcher.group("eventOption");

        switch (eventOption) {
        case AddEventCommand.COMMAND_OPTION:
            return new AddEventCommandParser().parse(args);
        case DeleteEventCommand.COMMAND_OPTION:
            return new DeleteEventCommandParser().parse(args);
        case EditEventCommand.COMMAND_OPTION:
            return new EditEventCommandParser().parse(args);
        case FindEventCommand.COMMAND_OPTION:
            return new FindEventCommandParser().parse(args);
        case ViewUpcomingEventsCommand.COMMAND_OPTION:
            return new ViewUpcomingEventsCommandParser().parse(args);
        case ViewEventsCommand.COMMAND_OPTION:
            return new ViewEventsCommandParser().parse(args);
        case AddProfilesToEventCommand.COMMAND_OPTION:
            return new AddProfilesToEventCommandParser().parse(args);
        case DeleteProfilesFromEventCommand.COMMAND_OPTION:
            return new DeleteProfilesFromEventCommandParser().parse(args);
        default:
            throw new ParseException(EventCommand.OPTION_UNKNOWN + EventCommand.VALID_FLAGS);
        }
    }

    /**
     * Returns a hash map containing the details about event commands to display in the help window.
     */
    public static Map<String, String> getEventCommands() {
        return new LinkedHashMap<>() {
            {
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION + AddEventCommand.COMMAND_OPTION,
                        AddEventCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION
                        + AddProfilesToEventCommand.COMMAND_OPTION, AddProfilesToEventCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION
                        + DeleteProfilesFromEventCommand.COMMAND_OPTION, DeleteProfilesFromEventCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION + DeleteEventCommand.COMMAND_OPTION,
                        DeleteEventCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION + EditEventCommand.COMMAND_OPTION,
                        EditEventCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION + FindEventCommand.COMMAND_OPTION,
                        FindEventCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION + ViewEventsCommand.COMMAND_OPTION,
                        ViewEventsCommand.MESSAGE_HELP);
                put(EventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION
                        + ViewUpcomingEventsCommand.COMMAND_OPTION, ViewUpcomingEventsCommand.MESSAGE_HELP);
            }
        };
    }
}
