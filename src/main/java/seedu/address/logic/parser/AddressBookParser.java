package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.AddInterestedClientCommand;
import seedu.address.logic.commands.AddListingCommand;
import seedu.address.logic.commands.AddOfferCommand;
import seedu.address.logic.commands.AddTagsToListingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteListingCommand;
import seedu.address.logic.commands.DeleteOfferCommand;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditListingCommand;
import seedu.address.logic.commands.EditOfferCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindClientCommand;
import seedu.address.logic.commands.FindOffersForListingCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewClientListCommand;
import seedu.address.logic.commands.ViewListingClientsCommand;
import seedu.address.logic.commands.ViewListingsCommand;
import seedu.address.logic.parser.exceptions.ParseException;


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

        case EditClientCommand.COMMAND_WORD:
            return new EditClientCommandParser().parse(arguments);

        case EditListingCommand.COMMAND_WORD:
            return new EditListingCommandParser().parse(arguments);

        case EditOfferCommand.COMMAND_WORD:
            return new EditOfferCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_WORD:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteListingCommand.COMMAND_WORD:
            return new DeleteListingCommandParser().parse(arguments);

        case DeleteOfferCommand.COMMAND_WORD:
            return new DeleteOfferCommandParser().parse(arguments);

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
