package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.commands.MessageCommandGroup;
import seedu.address.logic.commands.ReminderCommandGroup;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.TagCommandGroup;
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

        case AddCommand.COMMAND_WORD:
            // Fallthrough
        case AddCommand.COMMAND_WORD_ALIAS:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            // Fallthrough
        case EditCommand.COMMAND_WORD_ALIAS:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            // Fallthrough
        case DeleteCommand.COMMAND_WORD_ALIAS:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FilterCommand.COMMAND_WORD:
            // Fallthrough
        case FilterCommand.COMMAND_WORD_ALIAS:
            return new FilterCommandParser().parse(arguments);

        case TagCommandGroup.COMMAND_GROUP:
            // Fallthrough
        case TagCommandGroup.COMMAND_GROUP_ALIAS:
            return new TagCommandGroupParser().parse(arguments);

        case MessageCommandGroup.COMMAND_GROUP:
            // Fallthrough
        case MessageCommandGroup.COMMAND_GROUP_ALIAS:
            return new MessageCommandGroupParser().parse(arguments);

        case ReminderCommandGroup.COMMAND_GROUP:
            // Fallthrough
        case ReminderCommandGroup.COMMAND_GROUP_ALIAS:
            return new ReminderCommandGroupParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case HomeCommand.COMMAND_WORD:
            // Fallthrough
        case HomeCommand.COMMAND_WORD_ALIAS:
            return new HomeCommand();

        case ShowCommand.COMMAND_WORD:
            // Fallthrough
        case ShowCommand.COMMAND_WORD_ALIAS:
            return new ShowCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
