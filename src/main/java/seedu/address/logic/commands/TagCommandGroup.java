package seedu.address.logic.commands;

import java.util.Arrays;

/**
 * Represents all commands in the `tag` command group.
 */
public abstract class TagCommandGroup extends Command {
    public static final String COMMAND_GROUP = "tag";
    public static final String[] BANNED_TAG_NAMES = new String[]{"create", "c", "remove", "r"};
    public static final String MESSAGE_BANNED_TAG_NAME = "The following tag name is not allowed: \n%1$s";

    /**
     * Returns true if a given string is a banned tag name.
     */
    public static boolean isBannedTagName(String test) {
        return Arrays.asList(BANNED_TAG_NAMES).stream().anyMatch(test::equals);
    }
}
