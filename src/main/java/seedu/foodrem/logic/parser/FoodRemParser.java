package seedu.foodrem.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.enums.CommandType;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.generalcommands.ExitCommand;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.ListCommand;
import seedu.foodrem.logic.commands.tagcommands.ListTagCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.logic.parser.generalcommandparser.HelpCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.DecrementCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.DeleteCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.EditCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.FilterTagCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.FindCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.IncrementCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.NewCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.RemarkCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.SortCommandParser;
import seedu.foodrem.logic.parser.itemcommandparser.ViewCommandParser;
import seedu.foodrem.logic.parser.tagcommandparser.DeleteTagCommandParser;
import seedu.foodrem.logic.parser.tagcommandparser.NewTagCommandParser;
import seedu.foodrem.logic.parser.tagcommandparser.RenameTagCommandParser;
import seedu.foodrem.logic.parser.tagcommandparser.TagCommandParser;
import seedu.foodrem.logic.parser.tagcommandparser.UntagCommandParser;

/**
 * Parses user input.
 */
public class FoodRemParser {
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
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.getUsage()));
        }

        final String commandWordString = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        CommandType commandType = CommandType.parseWord(commandWordString);

        switch (commandType) {
        case NEW_COMMAND:
            return new NewCommandParser().parse(arguments);
        case EDIT_COMMAND:
            return new EditCommandParser().parse(arguments);
        case VIEW_COMMAND:
            return new ViewCommandParser().parse(arguments);
        case INCREMENT_COMMAND:
            return new IncrementCommandParser().parse(arguments);
        case DECREMENT_COMMAND:
            return new DecrementCommandParser().parse(arguments);
        case DELETE_COMMAND:
            return new DeleteCommandParser().parse(arguments);
        case REMARK_COMMAND:
            return new RemarkCommandParser().parse(arguments);
        case FILTER_TAG_COMMAND:
            return new FilterTagCommandParser().parse(arguments);
        case NEW_TAG_COMMAND:
            return new NewTagCommandParser().parse(arguments);
        case RENAME_TAG_COMMAND:
            return new RenameTagCommandParser().parse(arguments);
        case DELETE_TAG_COMMAND:
            return new DeleteTagCommandParser().parse(arguments);
        case RESET_COMMAND:
            return new ResetCommand();
        case FIND_COMMAND:
            return new FindCommandParser().parse(arguments);
        case LIST_COMMAND:
            return new ListCommand();
        case SORT_COMMAND:
            return new SortCommandParser().parse(arguments);
        case EXIT_COMMAND:
            return new ExitCommand();
        case HELP_COMMAND:
            return new HelpCommandParser().parse(arguments);
        case TAG_COMMAND:
            return new TagCommandParser().parse(arguments);
        case UNTAG_COMMAND:
            return new UntagCommandParser().parse(arguments);
        case LIST_TAG_COMMAND:
            return new ListTagCommand();
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
