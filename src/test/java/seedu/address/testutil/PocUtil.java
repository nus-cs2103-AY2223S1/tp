package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.poc.Poc;

/**
 * A utility class for Poc.
 */
public class PocUtil {

    /**
     * Returns an add command string for adding the {@code poc}.
     */
    public static String getCreateCommand(Poc poc, Index index) {
        return CreateCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getPocDetails(poc);
    }

    /**
     * Returns the part of command string for the given {@code poc}'s details.
     */
    public static String getPocDetails(Poc poc) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + poc.getName().fullName + " ");
        sb.append(PREFIX_PHONE + poc.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + poc.getEmail().value + " ");
        poc.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
