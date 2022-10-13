package seedu.foodrem.logic.parser.generalcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.HELP_FOR_ALL_COMMANDS;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.HELP_FOR_SPECIFIC_COMMAND;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.NOT_A_COMMAND;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.generalcommands.ExitCommand;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.DecrementCommand;
import seedu.foodrem.logic.commands.itemcommands.DeleteCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.FindCommand;
import seedu.foodrem.logic.commands.itemcommands.IncrementCommand;
import seedu.foodrem.logic.commands.itemcommands.ListCommand;
import seedu.foodrem.logic.commands.itemcommands.NewCommand;
import seedu.foodrem.logic.commands.itemcommands.SortCommand;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String commandWordString = argMultimap.getPreamble();

        if (commandWordString.isBlank()) {
            return new HelpCommand(HELP_FOR_ALL_COMMANDS);
        }

        CommandWord commandWord = CommandWord.getCommandWordFromCommandWordString(commandWordString);
        if (commandWord.equals(CommandWord.DEFAULT)) {
            return new HelpCommand(String.format(NOT_A_COMMAND, commandWordString));
        }

        return new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND, getHelp(commandWord)));
    }

    /**
     * Returns a string containing the message to help the user.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public static String getHelp(CommandWord commandWord) {
        switch (commandWord) {
        case NEW_COMMAND:
            return NewCommand.MESSAGE_USAGE;
        case EDIT_COMMAND:
            return EditCommand.MESSAGE_USAGE;
        case INCREMENT_COMMAND:
            return IncrementCommand.MESSAGE_USAGE;
        case DECREMENT_COMMAND:
            return DecrementCommand.MESSAGE_USAGE;
        case DELETE_COMMAND:
            return DeleteCommand.MESSAGE_USAGE;
        case NEW_TAG_COMMAND:
            return NewTagCommand.MESSAGE_USAGE;
        case RENAME_TAG_COMMAND:
            return RenameTagCommand.MESSAGE_USAGE;
        case RESET_COMMAND:
            return ResetCommand.MESSAGE_USAGE;
        case FIND_COMMAND:
            return FindCommand.MESSAGE_USAGE;
        case LIST_COMMAND:
            return ListCommand.MESSAGE_USAGE;
        case SORT_COMMAND:
            return SortCommand.MESSAGE_USAGE;
        case EXIT_COMMAND:
            return ExitCommand.MESSAGE_USAGE;
        case HELP_COMMAND:
            return HelpCommand.MESSAGE_USAGE;
        case TAG_COMMAND:
            return TagCommand.MESSAGE_USAGE;
        case UNTAG_COMMAND:
            return UntagCommand.MESSAGE_USAGE;
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
