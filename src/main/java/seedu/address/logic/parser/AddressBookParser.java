package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MatchCommand;
import seedu.address.logic.commands.addcommands.AddBuyerCommand;
import seedu.address.logic.commands.addcommands.AddCommandWithPopup;
import seedu.address.logic.commands.addcommands.AddDelivererCommand;
import seedu.address.logic.commands.addcommands.AddOrderCommand;
import seedu.address.logic.commands.addcommands.AddPetCommand;
import seedu.address.logic.commands.addcommands.AddSupplierCommand;
import seedu.address.logic.commands.deletecommands.DeleteBuyerCommand;
import seedu.address.logic.commands.deletecommands.DeleteCommand;
import seedu.address.logic.commands.deletecommands.DeleteDelivererCommand;
import seedu.address.logic.commands.deletecommands.DeleteOrderCommand;
import seedu.address.logic.commands.deletecommands.DeletePetCommand;
import seedu.address.logic.commands.deletecommands.DeleteSupplierCommand;
import seedu.address.logic.commands.editcommands.EditBuyerCommand;
import seedu.address.logic.commands.editcommands.EditDelivererCommand;
import seedu.address.logic.commands.editcommands.EditSupplierCommand;
import seedu.address.logic.commands.filtercommands.FilterCommand;
import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.parser.addcommandparser.AddBuyerCommandParser;
import seedu.address.logic.parser.addcommandparser.AddCommandWithPopupParser;
import seedu.address.logic.parser.addcommandparser.AddDelivererCommandParser;
import seedu.address.logic.parser.addcommandparser.AddOrderCommandParser;
import seedu.address.logic.parser.addcommandparser.AddPetCommandParser;
import seedu.address.logic.parser.addcommandparser.AddSupplierCommandParser;
import seedu.address.logic.parser.deletecommandparser.DeleteBuyerCommandParser;
import seedu.address.logic.parser.deletecommandparser.DeleteDelivererCommandParser;
import seedu.address.logic.parser.deletecommandparser.DeleteOrderCommandParser;
import seedu.address.logic.parser.deletecommandparser.DeletePetCommandParser;
import seedu.address.logic.parser.deletecommandparser.DeleteSupplierCommandParser;
import seedu.address.logic.parser.editcommandparser.EditBuyerCommandParser;
import seedu.address.logic.parser.editcommandparser.EditDelivererCommandParser;
import seedu.address.logic.parser.editcommandparser.EditSupplierCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser;
import seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser;
import seedu.address.logic.parser.findcommandparser.FindBuyerCommandParser;
import seedu.address.logic.parser.findcommandparser.FindCommandParser;
import seedu.address.logic.parser.findcommandparser.FindDelivererCommandParser;
import seedu.address.logic.parser.findcommandparser.FindSupplierCommandParser;

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

        case AddBuyerCommand.COMMAND_WORD:
            return new AddBuyerCommandParser().parse(arguments);

        case AddDelivererCommand.COMMAND_WORD:
            return new AddDelivererCommandParser().parse(arguments);

        case AddOrderCommand.COMMAND_WORD:
            return new AddOrderCommandParser().parse(arguments);

        case AddPetCommand.COMMAND_WORD:
            return new AddPetCommandParser().parse(arguments);

        case AddSupplierCommand.COMMAND_WORD:
            return new AddSupplierCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        case DeleteBuyerCommand.COMMAND_WORD:
            return new DeleteBuyerCommandParser().parse(arguments);

        case DeleteDelivererCommand.COMMAND_WORD:
            return new DeleteDelivererCommandParser().parse(arguments);

        case DeleteSupplierCommand.COMMAND_WORD:
            return new DeleteSupplierCommandParser().parse(arguments);

        case DeleteOrderCommand.COMMAND_WORD:
            return new DeleteOrderCommandParser().parse(arguments);

        case DeletePetCommand.COMMAND_WORD:
            return new DeletePetCommandParser().parse(arguments);

        case EditBuyerCommand.COMMAND_WORD:
            return new EditBuyerCommandParser().parse(arguments);

        case EditDelivererCommand.COMMAND_WORD:
            return new EditDelivererCommandParser().parse(arguments);

        case EditSupplierCommand.COMMAND_WORD:
            return new EditSupplierCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindBuyerCommandParser.PARSE_WORD:
            return new FindBuyerCommandParser().parse(arguments);

        case FindDelivererCommandParser.PARSE_WORD:
            return new FindDelivererCommandParser().parse(arguments);

        case FindSupplierCommandParser.PARSE_WORD:
            return new FindSupplierCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case AddCommandWithPopup.COMMAND_WORD:
            return new AddCommandWithPopupParser().parse(arguments);

        case CheckCommand.COMMAND_WORD:
            return new CheckCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FilterCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        case FilterPetCommand.COMMAND_WORD:
            return new FilterPetCommandParser().parse(arguments);

        case FilterOrderCommand.COMMAND_WORD:
            return new FilterOrderCommandParser().parse(arguments);

        case MatchCommand.COMMAND_WORD:
            return new MatchCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
