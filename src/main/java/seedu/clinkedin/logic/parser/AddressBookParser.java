package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.clinkedin.logic.commands.AddCommand;
import seedu.clinkedin.logic.commands.AddLinkCommand;
import seedu.clinkedin.logic.commands.AddNoteCommand;
import seedu.clinkedin.logic.commands.AddRateCommand;
import seedu.clinkedin.logic.commands.AddTagCommand;
import seedu.clinkedin.logic.commands.AddToCommand;
import seedu.clinkedin.logic.commands.ClearCommand;
import seedu.clinkedin.logic.commands.Command;
import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.logic.commands.DeleteCommand;
import seedu.clinkedin.logic.commands.DeleteLinkCommand;
import seedu.clinkedin.logic.commands.DeleteNoteCommand;
import seedu.clinkedin.logic.commands.DeleteRateCommand;
import seedu.clinkedin.logic.commands.DeleteTagCommand;
import seedu.clinkedin.logic.commands.DeleteTagTypeCommand;
import seedu.clinkedin.logic.commands.EditCommand;
import seedu.clinkedin.logic.commands.EditTagTypeCommand;
import seedu.clinkedin.logic.commands.ExitCommand;
import seedu.clinkedin.logic.commands.ExportCommand;
import seedu.clinkedin.logic.commands.FindCommand;
import seedu.clinkedin.logic.commands.HelpCommand;
import seedu.clinkedin.logic.commands.ImportCommand;
import seedu.clinkedin.logic.commands.ListCommand;
import seedu.clinkedin.logic.commands.RedoCommand;
import seedu.clinkedin.logic.commands.SortCommand;
import seedu.clinkedin.logic.commands.StatsCommand;
import seedu.clinkedin.logic.commands.UndoCommand;
import seedu.clinkedin.logic.commands.ViewCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

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

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");
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

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case AddLinkCommand.COMMAND_WORD:
            return new AddLinkCommandParser().parse(arguments);

        case DeleteLinkCommand.COMMAND_WORD:
            return new DeleteLinkCommandParser().parse(arguments);

        case AddToCommand.COMMAND_WORD:
            return new AddToCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case CreateTagTypeCommand.COMMAND_WORD:
            return new CreateTagTypeCommandParser().parse(arguments);

        case DeleteTagTypeCommand.COMMAND_WORD:
            return new DeleteTagTypeCommandParser().parse(arguments);

        case EditTagTypeCommand.COMMAND_WORD:
            return new EditTagTypeCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case AddRateCommand.COMMAND_WORD:
            return new AddRateCommandParser().parse(arguments);

        case DeleteRateCommand.COMMAND_WORD:
            return new DeleteRateCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
