package seedu.foodrem.logic.commands;

import static seedu.foodrem.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

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
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Represents the command help to be displayed to users during help.
 */
public class CommandHelp {
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
