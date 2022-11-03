package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNAVAILABLE_COMMAND_HOME;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNAVAILABLE_COMMAND_ITINERARY;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNKNOWN_STAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.logic.commands.ClearCommand;
import seedu.waddle.logic.commands.Command;
import seedu.waddle.logic.commands.DeleteCommand;
import seedu.waddle.logic.commands.DeleteItemCommand;
import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.commands.EditItemCommand;
import seedu.waddle.logic.commands.ExitCommand;
import seedu.waddle.logic.commands.PdfCommand;
import seedu.waddle.logic.commands.CopyCommand;
import seedu.waddle.logic.commands.FindCommand;
import seedu.waddle.logic.commands.FreeCommand;
import seedu.waddle.logic.commands.HelpCommand;
import seedu.waddle.logic.commands.HomeCommand;
import seedu.waddle.logic.commands.ListCommand;
import seedu.waddle.logic.commands.PlanCommand;
import seedu.waddle.logic.commands.SelectCommand;
import seedu.waddle.logic.commands.UnplanCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class WaddleParser {

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

        Stages currStage = StageManager.getInstance().getCurrentStage();

        switch (currStage) {
        case HOME:
            return parseHomeCommand(commandWord, arguments);
        case WISH:
            return parseWishCommand(commandWord, arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_STAGE);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord The command word.
     * @param arguments The arguments.
     * @return The command.
     * @throws ParseException ParseException.
     */
    public Command parseHomeCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case PlanCommand.COMMAND_WORD:

        case UnplanCommand.COMMAND_WORD:

        case FreeCommand.COMMAND_WORD:

        case PdfCommand.COMMAND_WORD:

        case CopyCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_UNAVAILABLE_COMMAND_HOME);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord The command word.
     * @param arguments The arguments.
     * @return The command.
     * @throws ParseException ParseException.
     */
    public Command parseWishCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FreeCommand.COMMAND_WORD:
            return new FreeCommand();

        case AddItemCommand.COMMAND_WORD:
            return new AddItemCommandParser().parse(arguments);

        case EditItemCommand.COMMAND_WORD:
            return new EditItemCommandParser().parse(arguments);

        case DeleteItemCommand.COMMAND_WORD:
            return new DeleteItemCommandParser().parse(arguments);

        case PlanCommand.COMMAND_WORD:
            return new PlanCommandParser().parse(arguments);

        case UnplanCommand.COMMAND_WORD:
            return new UnplanCommandParser().parse(arguments);

        //TODO: help commands must change here
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case PdfCommand.COMMAND_WORD:
            return new PdfCommand();

        case CopyCommand.COMMAND_WORD:
            return new CopyCommand();

        case ClearCommand.COMMAND_WORD:

        case ListCommand.COMMAND_WORD:

        case SelectCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_UNAVAILABLE_COMMAND_ITINERARY);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
