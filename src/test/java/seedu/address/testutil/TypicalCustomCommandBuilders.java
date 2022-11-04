package seedu.address.testutil;

import seedu.address.logic.commands.CustomCommandBuilder;

/**
 * A utility class containing a list of {@code CustomCommandBuilder} objects to be used in tests.
 */
public class TypicalCustomCommandBuilders {

    public static final CustomCommandBuilder DEFAULT_INFO_COMMAND = new CustomCommandBuilder("addDefaultInfo",
            "seq field add Performance 50 ; field add mcLeft 14 ; field add role Employee");
    public static final CustomCommandBuilder CLOSE_CONTACT_COMMAND = new CustomCommandBuilder("setCloseContact",
        "if [[contains unvaccinated]] ;; [[ field add closeContact 14 days]] ;; "
                + "[[ field add closeContact 7 days]]");
}
