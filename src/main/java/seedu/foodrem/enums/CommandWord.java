package seedu.foodrem.enums;

import java.util.StringJoiner;

/**
 * Represents the command word to be keyed in by a user to execute a command.
 */
public enum CommandWord {
    // GENERAL COMMANDS
    HELP_COMMAND("help"),
    RESET_COMMAND("reset"),
    EXIT_COMMAND("exit"),

    // ITEM COMMANDS
    NEW_COMMAND("new"),
    LIST_COMMAND("list"),
    FIND_COMMAND("find"),
    EDIT_COMMAND("edit"),
    INCREMENT_COMMAND("inc"),
    DECREMENT_COMMAND("dec"),
    SORT_COMMAND("sort"),
    DELETE_COMMAND("del"),
    VIEW_COMMAND("view"),

    // TAG COMMANDS
    NEW_TAG_COMMAND("newtag"),
    RENAME_TAG_COMMAND("renametag"),
    TAG_COMMAND("tag"),
    UNTAG_COMMAND("untag"),
    DELETE_TAG_COMMAND("deletetag"),
    LIST_TAG_COMMAND("listtag"),

    // INVALID
    DEFAULT("default");

    private final String value;

    /**
     * Constructs a CommandWord enum.
     *
     * @param commandWord the value representing the string value of the enum.
     */
    CommandWord(String commandWord) {
        value = commandWord;
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

    /**
     * Returns the string value of a CommandWord.
     *
     * @return the string representation of a CommandWord.
     */
    public String getValue() {
        return value;
    }
}
