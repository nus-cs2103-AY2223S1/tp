package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tuthub.logic.commands.AddCommand;
import tuthub.logic.commands.ClearCommand;
import tuthub.logic.commands.Command;
import tuthub.logic.commands.CommentCommand;
import tuthub.logic.commands.DeleteCommand;
import tuthub.logic.commands.DeleteCommentCommand;
import tuthub.logic.commands.EditCommand;
import tuthub.logic.commands.ExitCommand;
import tuthub.logic.commands.FindByPrefixCommand;
import tuthub.logic.commands.HelpCommand;
import tuthub.logic.commands.ListCommand;
import tuthub.logic.commands.MailCommand;
import tuthub.logic.commands.SortCommand;
import tuthub.logic.commands.ViewCommand;
import tuthub.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TuthubParser {

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
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteCommentCommand.COMMAND_WORD:
            return new DeleteCommentCommandParser().parse(arguments);

        case DeleteCommentCommand.ALTERNATIVE_COMMAND_WORD:
            return new DeleteCommentCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case CommentCommand.COMMAND_WORD:
            return new CommentCommandParser().parse(arguments);

        case FindByPrefixCommand.COMMAND_WORD:
            return new FindByPrefixCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case MailCommand.COMMAND_WORD:
            return new MailCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
