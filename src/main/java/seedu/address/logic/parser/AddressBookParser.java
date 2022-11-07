package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAddressCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddToFavCommand;
import seedu.address.logic.commands.AddToGroupCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EmailAllCommand;
import seedu.address.logic.commands.ExcludeCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.IncludeCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.OpenCommand;
import seedu.address.logic.commands.PreferCommand;
import seedu.address.logic.commands.SocialCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UngroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.HistoryList;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static String publicReadableUserInput = null;

    /**
     * Returns the user input for commands that need to directly reference it. Only to be used as is (i.e. not parsed).
     * @return The user input string.
     */
    public static String getUserInput() {
        return publicReadableUserInput;
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
        publicReadableUserInput = userInput;
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new EditCommandParser().parse(arguments);

        case AddAddressCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new AddAddressCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            HistoryList.addToHistory(commandWord);
            return new ClearCommand();

        case UndoCommand.COMMAND_WORD:
            HistoryList.addToHistory(commandWord);
            return new UndoCommand();

        case FindCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            HistoryList.addToHistory(commandWord);
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            HistoryList.addToHistory(commandWord);
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            HistoryList.addToHistory(commandWord);
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new SortCommandParser().parse(arguments);

        case AddToGroupCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new AddToGroupCommandParser().parse(arguments);

        case UngroupCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new UngroupCommandParser().parse(arguments);

        case GroupCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new GroupCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case IncludeCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new IncludeCommandParser().parse(arguments);

        case ExcludeCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new ExcludeCommandParser().parse(arguments);

        case PreferCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new PreferCommandParser().parse(arguments);

        case OpenCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new OpenCommandParser().parse(arguments);

        case SocialCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new SocialCommandParser().parse(arguments);

        case AddToFavCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new AddToFavCommandParser().parse(arguments);

        case EmailAllCommand.COMMAND_WORD:
            HistoryList.addToHistory(userInput);
            return new EmailAllCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
