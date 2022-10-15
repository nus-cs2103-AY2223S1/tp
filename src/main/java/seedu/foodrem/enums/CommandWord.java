package seedu.foodrem.enums;

import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.DEFAULT_HELP_MESSAGE;

import java.util.Arrays;
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
import seedu.foodrem.logic.commands.itemcommands.ViewCommand;
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
    HELP_COMMAND("help", DEFAULT_HELP_MESSAGE),
    RESET_COMMAND("reset", DEFAULT_HELP_MESSAGE),
    EXIT_COMMAND("exit", DEFAULT_HELP_MESSAGE),

    // ITEM COMMANDS
    NEW_COMMAND("new", DEFAULT_HELP_MESSAGE),
    LIST_COMMAND("list", DEFAULT_HELP_MESSAGE),
    FIND_COMMAND("find", DEFAULT_HELP_MESSAGE),
    EDIT_COMMAND("edit", DEFAULT_HELP_MESSAGE),
    INCREMENT_COMMAND("inc", DEFAULT_HELP_MESSAGE),
    DECREMENT_COMMAND("dec", DEFAULT_HELP_MESSAGE),
    SORT_COMMAND("sort", DEFAULT_HELP_MESSAGE),
    DELETE_COMMAND("del", DEFAULT_HELP_MESSAGE),
    VIEW_COMMAND("view", DEFAULT_HELP_MESSAGE),

    // TAG COMMANDS
    NEW_TAG_COMMAND("newtag", DEFAULT_HELP_MESSAGE),
    RENAME_TAG_COMMAND("renametag", DEFAULT_HELP_MESSAGE),
    TAG_COMMAND("tag", DEFAULT_HELP_MESSAGE),
    UNTAG_COMMAND("untag", DEFAULT_HELP_MESSAGE),
    DELETE_TAG_COMMAND("deletetag", DEFAULT_HELP_MESSAGE),
    LIST_TAG_COMMAND("listtag", DEFAULT_HELP_MESSAGE),

    // INVALID
    DEFAULT("default", DEFAULT_HELP_MESSAGE);

    // This will only run after all enums are constructed.
    // This is necessary as MESSAGE_USAGE are defined in each respective commands and
    // most of them references the value of the commandWord in the enum.
    // An error regarding self referencing will occur during construction if this is not done.
    // https://stackoverflow.com/questions/11419519/enums-static-and-instance-blocks
    static {
        // General Commands
        EXIT_COMMAND.help = ExitCommand.getUsage();
        HELP_COMMAND.help = HelpCommand.getUsage();
        RESET_COMMAND.help = ResetCommand.getUsage();

        // Item Commands
        NEW_COMMAND.help = NewCommand.getUsage();
        EDIT_COMMAND.help = EditCommand.getUsage();
        INCREMENT_COMMAND.help = IncrementCommand.getUsage();
        DECREMENT_COMMAND.help = DecrementCommand.getUsage();
        DELETE_COMMAND.help = DeleteCommand.getUsage();
        LIST_COMMAND.help = ListCommand.getUsage();
        SORT_COMMAND.help = SortCommand.getUsage();
        FIND_COMMAND.help = FindCommand.getUsage();
        VIEW_COMMAND.help = ViewCommand.getUsage();

        // Tag Commands
        NEW_TAG_COMMAND.help = NewTagCommand.getUsage();
        RENAME_TAG_COMMAND.help = RenameTagCommand.getUsage();
        TAG_COMMAND.help = TagCommand.getUsage();
        UNTAG_COMMAND.help = UntagCommand.getUsage();
        DELETE_TAG_COMMAND.help = DeleteTagCommand.getUsage();
        LIST_TAG_COMMAND.help = ListTagCommand.getUsage();
    }

    private final String commandWord;
    private String help;

    /**
     * Constructs a CommandWord enum.
     *
     * @param commandWord the value representing the string value of the enum.
     */
    CommandWord(String commandWord, String commandHelp) {
        this.commandWord = commandWord;
        help = commandHelp;
    }

    /**
     * Returns the string value of a CommandWord.
     *
     * @return the string representation of a CommandWord.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Returns the string value of a help message for the CommandWord.
     *
     * @return the string representation a help message for a CommandWord.
     */
    public String getUsage() {
        return help;
    }

    /**
     * Returns the CommandWord object from the string value of a command word.
     *
     * @param word a string value of the enum represented by the value provided.
     * @return a CommandWord object of the command represented by the string.
     */
    public static CommandWord parseWord(String word) {
        return Arrays.stream(values())
                .filter(type -> type.commandWord.equals(word))
                .findFirst().orElse(DEFAULT);
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
            stringJoiner.add(commandWord.commandWord);
        }
        return stringJoiner.toString();
    }
}
