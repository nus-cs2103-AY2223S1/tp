package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DELETE_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EDIT_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LIST_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user's input.
 */
public class AddressBookParser {

    /**
     * Used for identifying arguments for a command.
     */
    private static final Pattern COMMAND_FORMAT =
        Pattern.compile("(?<commandWord>\\S+)(?<commandArguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher generalMatcher = COMMAND_FORMAT.matcher(userInput.trim());
        if (!generalMatcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = generalMatcher.group("commandWord");
        final String commandArguments = generalMatcher.group("commandArguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(commandArguments);

        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleCommandParser().parse(commandArguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(commandArguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleCommandParser().parse(commandArguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(commandArguments);

        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleCommandParser().parse(commandArguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(commandArguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        /* Prompts the complete command when the user gives an "almost complete" command */
        case AddCommand.COMMAND_TYPE:
            throw new ParseException(MESSAGE_INVALID_ADD_COMMAND);

        case DeleteCommand.COMMAND_TYPE:
            throw new ParseException(MESSAGE_INVALID_DELETE_COMMAND);

        case ListCommand.COMMAND_TYPE:
            throw new ParseException(MESSAGE_INVALID_LIST_COMMAND);

        case EditCommand.COMMAND_TYPE:
            throw new ParseException(MESSAGE_INVALID_EDIT_COMMAND);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
