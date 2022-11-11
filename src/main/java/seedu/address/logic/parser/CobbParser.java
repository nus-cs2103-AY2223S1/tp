package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.buyer.AddBuyerCommand;
import seedu.address.logic.commands.buyer.DeleteBuyerCommand;
import seedu.address.logic.commands.buyer.EditBuyerCommand;
import seedu.address.logic.commands.buyer.FilterBuyersCommand;
import seedu.address.logic.commands.buyer.FindBuyersCommand;
import seedu.address.logic.commands.buyer.ListBuyersCommand;
import seedu.address.logic.commands.buyer.MatchBuyerCommand;
import seedu.address.logic.commands.buyer.SortBuyersCommand;
import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.logic.commands.property.DeletePropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.FilterPropertiesCommand;
import seedu.address.logic.commands.property.FindPropertiesCommand;
import seedu.address.logic.commands.property.ListPropertiesCommand;
import seedu.address.logic.commands.property.MatchPropertyCommand;
import seedu.address.logic.commands.property.SortPropertiesCommand;
import seedu.address.logic.parser.buyer.AddBuyerCommandParser;
import seedu.address.logic.parser.buyer.DeleteBuyerCommandParser;
import seedu.address.logic.parser.buyer.EditBuyerCommandParser;
import seedu.address.logic.parser.buyer.FilterBuyersCommandParser;
import seedu.address.logic.parser.buyer.FindBuyersCommandParser;
import seedu.address.logic.parser.buyer.MatchBuyerCommandParser;
import seedu.address.logic.parser.buyer.SortBuyersCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.property.AddPropertyCommandParser;
import seedu.address.logic.parser.property.DeletePropertyCommandParser;
import seedu.address.logic.parser.property.EditPropertyCommandParser;
import seedu.address.logic.parser.property.FilterPropertiesCommandParser;
import seedu.address.logic.parser.property.FindPropertiesCommandParser;
import seedu.address.logic.parser.property.MatchPropertyCommandParser;
import seedu.address.logic.parser.property.SortPropertiesCommandParser;

/**
 * Parses user input.
 */
public class CobbParser {

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

        case AddBuyerCommand.COMMAND_WORD:
            return new AddBuyerCommandParser().parse(arguments);

        case EditBuyerCommand.COMMAND_WORD:
            return new EditBuyerCommandParser().parse(arguments);

        case DeleteBuyerCommand.COMMAND_WORD:
            return new DeleteBuyerCommandParser().parse(arguments);

        case DeletePropertyCommand.COMMAND_WORD:
            return new DeletePropertyCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindBuyersCommand.COMMAND_WORD:
            return new FindBuyersCommandParser().parse(arguments);

        case FindPropertiesCommand.COMMAND_WORD:
            return new FindPropertiesCommandParser().parse(arguments);

        case ListBuyersCommand.COMMAND_WORD:
            return new ListBuyersCommand();

        case ListPropertiesCommand.COMMAND_WORD:
            return new ListPropertiesCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddPropertyCommand.COMMAND_WORD:
            return new AddPropertyCommandParser().parse(arguments);

        case EditPropertyCommand.COMMAND_WORD:
            return new EditPropertyCommandParser().parse(arguments);

        case FilterBuyersCommand.COMMAND_WORD:
            return new FilterBuyersCommandParser().parse(arguments);

        case FilterPropertiesCommand.COMMAND_WORD:
            return new FilterPropertiesCommandParser().parse(arguments);

        case SortBuyersCommand.COMMAND_WORD:
            return new SortBuyersCommandParser().parse(arguments);

        case SortPropertiesCommand.COMMAND_WORD:
            return new SortPropertiesCommandParser().parse(arguments);

        case MatchBuyerCommand.COMMAND_WORD:
            return new MatchBuyerCommandParser().parse(arguments);

        case MatchPropertyCommand.COMMAND_WORD:
            return new MatchPropertyCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
