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
    // General Commands
    HELP_COMMAND("help") {
        @Override
        public String getUsage() {
            return HelpCommand.getUsage();
        }
    },
    RESET_COMMAND("reset") {
        @Override
        public String getUsage() {
            return ResetCommand.getUsage();
        }
    },
    EXIT_COMMAND("exit") {
        @Override
        public String getUsage() {
            return getCommandWord() + ": Exits FoodRem."
                    + "Example: " + getCommandWord();
        }
    },

    // Item Commands
    NEW_COMMAND("new") {
        @Override
        public String getUsage() {
            return NewCommand.getUsage();
        }
    },
    LIST_COMMAND("list") {
        @Override
        public String getUsage() {
            return ListCommand.getUsage();
        }
    },
    FIND_COMMAND("find") {
        @Override
        public String getUsage() {
            return FindCommand.getUsage();
        }
    },
    EDIT_COMMAND("edit") {
        @Override
        public String getUsage() {
            return EditCommand.getUsage();
        }
    },
    INCREMENT_COMMAND("inc") {
        @Override
        public String getUsage() {
            return IncrementCommand.getUsage();
        }
    },
    DECREMENT_COMMAND("dec") {
        @Override
        public String getUsage() {
            return DecrementCommand.getUsage();
        }
    },
    SORT_COMMAND("sort") {
        @Override
        public String getUsage() {
            return SortCommand.getUsage();
        }
    },
    DELETE_COMMAND("del") {
        @Override
        public String getUsage() {
            return DeleteCommand.getUsage();
        }
    },
    VIEW_COMMAND("view") {
        @Override
        public String getUsage() {
            return ViewCommand.getUsage();
        }
    },

    // Tag Commands
    NEW_TAG_COMMAND("newtag") {
        @Override
        public String getUsage() {
            return NewTagCommand.getUsage();
        }
    },
    RENAME_TAG_COMMAND("renametag") {
        @Override
        public String getUsage() {
            return RenameTagCommand.getUsage();
        }
    },
    TAG_COMMAND("tag") {
        @Override
        public String getUsage() {
            return TagCommand.getUsage();
        }
    },
    UNTAG_COMMAND("untag") {
        @Override
        public String getUsage() {
            return UntagCommand.getUsage();
        }
    },
    DELETE_TAG_COMMAND("deletetag") {
        @Override
        public String getUsage() {
            return DeleteTagCommand.getUsage();
        }
    },
    LIST_TAG_COMMAND("listtag") {
        @Override
        public String getUsage() {
            return ListTagCommand.getUsage();
        }
    },

    // INVALID
    DEFAULT("default") {
        @Override
        public String getUsage() {
            return DEFAULT_HELP_MESSAGE;
        }
    };

    private final String commandWord;

    /**
     * Constructs a CommandWord enum.
     *
     * @param commandWord the value representing the string value of the enum.
     */
    CommandWord(String commandWord) {
        this.commandWord = commandWord;
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
    public abstract String getUsage();

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
