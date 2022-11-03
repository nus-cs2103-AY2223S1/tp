package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tracko.logic.commands.ClearCommand;
import tracko.logic.commands.Command;
import tracko.logic.commands.ExitCommand;
import tracko.logic.commands.HelpCommand;
import tracko.logic.commands.MultiLevelCommand;
import tracko.logic.commands.item.AddItemCommand;
import tracko.logic.commands.item.DeleteItemCommand;
import tracko.logic.commands.item.EditItemCommand;
import tracko.logic.commands.item.FindItemCommand;
import tracko.logic.commands.item.ListItemsCommand;
import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.commands.order.DeleteOrderCommand;
import tracko.logic.commands.order.EditOrderCommand;
import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.commands.order.ListOrdersCommand;
import tracko.logic.commands.order.MarkOrderCommand;
import tracko.logic.commands.order.SortOrderCommand;
import tracko.logic.parser.exceptions.ParseException;
import tracko.logic.parser.item.AddItemCommandParser;
import tracko.logic.parser.item.DeleteItemCommandParser;
import tracko.logic.parser.item.EditItemCommandParser;
import tracko.logic.parser.item.FindItemCommandParser;
import tracko.logic.parser.order.AddOrderCommandParser;
import tracko.logic.parser.order.DeleteOrderCommandParser;
import tracko.logic.parser.order.EditOrderCommandParser;
import tracko.logic.parser.order.FindOrderCommandParser;
import tracko.logic.parser.order.MarkOrderCommandParser;
import tracko.logic.parser.order.SortOrderCommandParser;

/**
 * Parses user input.
 */
public class TrackOParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input and update an in-progress command for execution.
     *
     * @param userInput full user input string
     * @param command the in-progress command to be updated
     * @return The updated command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public MultiLevelCommand parseAndUpdateCommand(String userInput, MultiLevelCommand command) throws ParseException {
        if (command instanceof AddOrderCommand) {
            return new AddOrderCommandParser().parseAndUpdate(userInput, (AddOrderCommand) command);
        } else if (command instanceof ClearCommand) {
            return new ClearCommandParser().parseAndUpdate(userInput, (ClearCommand) command);
        }
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }

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
        System.out.println(commandWord);
        System.out.println(arguments);
        switch (commandWord) {

        case AddOrderCommand.COMMAND_WORD:
            return new AddOrderCommandParser().parse(arguments);

        case ListOrdersCommand.COMMAND_WORD:
            return new ListOrdersCommand();

        case FindOrderCommand.COMMAND_WORD:
            return new FindOrderCommandParser().parse(arguments);

        case SortOrderCommand.COMMAND_WORD:
            return new SortOrderCommandParser().parse(arguments);

        case EditOrderCommand.COMMAND_WORD:
            return new EditOrderCommandParser().parse(arguments);

        case MarkOrderCommand.COMMAND_WORD:
            return new MarkOrderCommandParser().parse(arguments);

        case DeleteOrderCommand.COMMAND_WORD:
            return new DeleteOrderCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddItemCommand.COMMAND_WORD:
            return new AddItemCommandParser().parse(arguments);

        case ListItemsCommand.COMMAND_WORD:
            return new ListItemsCommand();

        case FindItemCommand.COMMAND_WORD:
            return new FindItemCommandParser().parse(arguments);

        case DeleteItemCommand.COMMAND_WORD:
            return new DeleteItemCommandParser().parse(arguments);

        case EditItemCommand.COMMAND_WORD:
            return new EditItemCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            // an empty string is used as a dummy input for the parser method.
            // no inputs from the user is taken in as arguments for the ClearCommand method
            return new ClearCommandParser().parse("");

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
