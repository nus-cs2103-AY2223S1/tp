package eatwhere.foodguide.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.AddCommand;
import eatwhere.foodguide.logic.commands.ClearCommand;
import eatwhere.foodguide.logic.commands.Command;
import eatwhere.foodguide.logic.commands.DeleteCommand;
import eatwhere.foodguide.logic.commands.EditCommand;
import eatwhere.foodguide.logic.commands.ExitCommand;
import eatwhere.foodguide.logic.commands.FindCommand;
import eatwhere.foodguide.logic.commands.FindCuisineCommand;
import eatwhere.foodguide.logic.commands.FindLocationCommand;
import eatwhere.foodguide.logic.commands.FindTagCommand;
import eatwhere.foodguide.logic.commands.HelpCommand;
import eatwhere.foodguide.logic.commands.ListCommand;
import eatwhere.foodguide.logic.commands.TagCommand;
import eatwhere.foodguide.logic.commands.UntagCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FoodGuideParser {

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
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public Command parseCommand(String userInput) throws ParseException, DisplayCommandHelpException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case UntagCommand.COMMAND_WORD:
            return new UntagCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FindTagCommand.COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case FindCuisineCommand.COMMAND_WORD:
            return new FindCuisineCommandParser().parse(arguments);

        case FindLocationCommand.COMMAND_WORD:
            return new FindLocationCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
