package seedu.foodrem.enums;

import java.util.StringJoiner;

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
import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.logic.commands.tagcommands.ListTagCommand;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;

/**
 * Represents the command word to be keyed in by a user to execute a command.
 */
public enum CommandWord {
    // GENERAL COMMANDS
    HELP_COMMAND("help", ""),
    RESET_COMMAND("reset", ""),
    EXIT_COMMAND("exit", ""),

    // ITEM COMMANDS
    NEW_COMMAND("new", ""),
    LIST_COMMAND("list", ""),
    FIND_COMMAND("find", ""),
    EDIT_COMMAND("edit", ""),
    INCREMENT_COMMAND("inc", ""),
    DECREMENT_COMMAND("dec", ""),
    SORT_COMMAND("sort", ""),
    DELETE_COMMAND("del", ""),

    // TAG COMMANDS
    NEW_TAG_COMMAND("newtag", ""),
    RENAME_TAG_COMMAND("renametag", ""),
    TAG_COMMAND("tag", ""),
    UNTAG_COMMAND("untag", ""),
    DELETE_TAG_COMMAND("deletetag", ""),
    LIST_TAG_COMMAND("listtag", ""),

    // INVALID
    DEFAULT("default", "Please refer to the user guide.");

    static {
        // General Commands
        EXIT_COMMAND.help = ExitCommand.MESSAGE_USAGE;
        HELP_COMMAND.help = HelpCommand.MESSAGE_USAGE;
        RESET_COMMAND.help = ResetCommand.MESSAGE_USAGE;

        // Item Commands
        NEW_COMMAND.help = NewCommand.MESSAGE_USAGE;
        EDIT_COMMAND.help = EditCommand.MESSAGE_USAGE;
        INCREMENT_COMMAND.help = IncrementCommand.MESSAGE_USAGE;
        DECREMENT_COMMAND.help = DecrementCommand.MESSAGE_USAGE;
        DELETE_COMMAND.help = DeleteCommand.MESSAGE_USAGE;
        LIST_COMMAND.help = ListCommand.MESSAGE_USAGE;
        SORT_COMMAND.help = SortCommand.MESSAGE_USAGE;
        FIND_COMMAND.help = FindCommand.MESSAGE_USAGE;

        // Tag Commands
        NEW_TAG_COMMAND.help = NewTagCommand.MESSAGE_USAGE;
        RENAME_TAG_COMMAND.help = RenameTagCommand.MESSAGE_USAGE;
        TAG_COMMAND.help = TagCommand.MESSAGE_USAGE;
        UNTAG_COMMAND.help = UntagCommand.MESSAGE_USAGE;
        DELETE_TAG_COMMAND.help = DeleteTagCommand.MESSAGE_USAGE;
        LIST_TAG_COMMAND.help = ListTagCommand.MESSAGE_USAGE;
    }

    private final String value;
    private String help;

    /**
     * Constructs a CommandWord enum.
     *
     * @param commandWord the value representing the string value of the enum.
     */
    CommandWord(String commandWord, String commandHelp) {
        value = commandWord;
        help = commandHelp;
    }

    /**
     * Returns the string value of a CommandWord.
     *
     * @return the string representation of a CommandWord.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the string value of a help message for the CommandWord.
     *
     * @return the string representation a help message for a CommandWord.
     */
    public String getHelp() {
        return help;
    }

    /**
     * Returns the CommandWord object from the string value of a command word.
     *
     * @param value a string value of the enum represented by the value provided.
     * @return a CommandWord object of the command represented by the string.
     */
    public static CommandWord getCommandWordFromCommandWordString(String value) {
        for (CommandWord commandWord : CommandWord.values()) {
            if (commandWord.getValue().equals(value)) {
                return commandWord;
            }
        }
        return DEFAULT;
    }

    /**
     * Returns the list of all command words separated by commas
     */
    public static String listAllCommandWords() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (CommandWord commandWord : values()) {
            if (commandWord.equals(DEFAULT)) {
                continue;
            }
            stringJoiner.add(commandWord.getValue());
        }
        return stringJoiner.toString();
    }
}
