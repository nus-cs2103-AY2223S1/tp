package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hobbylist.logic.commands.AddCommand;
import hobbylist.logic.commands.ClearCommand;
import hobbylist.logic.commands.Command;
import hobbylist.logic.commands.DeleteCommand;
import hobbylist.logic.commands.EditCommand;
import hobbylist.logic.commands.ExitCommand;
import hobbylist.logic.commands.FindCommand;
import hobbylist.logic.commands.FindTagCommand;
import hobbylist.logic.commands.HelpCommand;
import hobbylist.logic.commands.ListCommand;
import hobbylist.logic.commands.RateCommand;
import hobbylist.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class HobbyListParser {

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
        System.out.println(ExitCommand.getCommandWord());

        if (commandWord.equals(AddCommand.getCommandWord())) {
            return new AddCommandParser().parse(arguments);
        } else if (commandWord.equals(EditCommand.getCommandWord())) {
            return new EditCommandParser().parse(arguments);
        } else if (commandWord.equals(DeleteCommand.getCommandWord())) {
            return new DeleteCommandParser().parse(arguments);
        } else if (commandWord.equals(ClearCommand.getCommandWord())) {
            return new ClearCommand();
        } else if (commandWord.equals(FindCommand.getCommandWord())) {
            return new FindCommandParser().parse(arguments);
        } else if (commandWord.equals(FindTagCommand.getCommandWord())) {
            return new FindTagCommandParser().parse(arguments);
        } else if (commandWord.equals(ListCommand.getCommandWord())) {
            return new ListCommand();
        } else if (commandWord.equals(ExitCommand.getCommandWord())) {
            return new ExitCommand();
        } else if (commandWord.equals(HelpCommand.getCommandWord())) {
            return new HelpCommand();
        } else if (commandWord.equals(RateCommand.getCommandWord())) {
            return new RateCommandParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
