package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.realtime.logic.commands.AddClientCommand;
import seedu.realtime.logic.commands.AddInterestedClientCommand;
import seedu.realtime.logic.commands.AddListingCommand;
import seedu.realtime.logic.commands.AddMeetingCommand;
import seedu.realtime.logic.commands.AddOfferCommand;
import seedu.realtime.logic.commands.AddTagsToListingCommand;
import seedu.realtime.logic.commands.ClearCommand;
import seedu.realtime.logic.commands.Command;
import seedu.realtime.logic.commands.DeleteClientCommand;
import seedu.realtime.logic.commands.DeleteListingCommand;
import seedu.realtime.logic.commands.DeleteMeetingCommand;
import seedu.realtime.logic.commands.DeleteOfferCommand;
import seedu.realtime.logic.commands.EditClientCommand;
import seedu.realtime.logic.commands.EditListingCommand;
import seedu.realtime.logic.commands.EditMeetingCommand;
import seedu.realtime.logic.commands.EditOfferCommand;
import seedu.realtime.logic.commands.ExitCommand;
import seedu.realtime.logic.commands.FindClientCommand;
import seedu.realtime.logic.commands.FindOffersForListingCommand;
import seedu.realtime.logic.commands.HelpCommand;
import seedu.realtime.logic.commands.ViewClientListCommand;
import seedu.realtime.logic.commands.ViewListingClientsCommand;
import seedu.realtime.logic.commands.ViewListingsCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class RealTimeParser {

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

        case AddClientCommand.COMMAND_WORD:
            return new AddClientCommandParser().parse(arguments);

        case AddInterestedClientCommand.COMMAND_WORD:
            return new AddInterestedClientCommandParser().parse(arguments);

        case AddListingCommand.COMMAND_WORD:
            return new AddListingCommandParser().parse(arguments);

        case AddTagsToListingCommand.COMMAND_WORD:
            return new AddTagsToListingCommandParser().parse(arguments);

        case AddOfferCommand.COMMAND_WORD:
            return new AddOfferCommandParser().parse(arguments);

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case EditClientCommand.COMMAND_WORD:
            return new EditClientCommandParser().parse(arguments);

        case EditListingCommand.COMMAND_WORD:
            return new EditListingCommandParser().parse(arguments);

        case EditOfferCommand.COMMAND_WORD:
            return new EditOfferCommandParser().parse(arguments);

        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_WORD:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteListingCommand.COMMAND_WORD:
            return new DeleteListingCommandParser().parse(arguments);

        case DeleteOfferCommand.COMMAND_WORD:
            return new DeleteOfferCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindClientCommand.COMMAND_WORD:
            return new FindClientCommandParser().parse(arguments);

        case FindOffersForListingCommand.COMMAND_WORD:
            return new FindOffersForListingCommandParser().parse(arguments);

        case ViewClientListCommand.COMMAND_WORD:
            return new ViewClientListCommand();

        case ViewListingsCommand.COMMAND_WORD:
            return new ViewListingsCommand();

        case ViewListingClientsCommand.COMMAND_WORD:
            return new ViewListingClientsCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
