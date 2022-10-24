package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.remark.Remark;

/**
 * A utility class for Remark.
 */
public class RemarkUtil {

    /**
     * Returns an add command string for adding the {@code remark}.
     */
    public static String getRemarkCommand(Remark remark, Index index) {
        return RemarkCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getRemarkDetails(remark);
    }

    /**
     * Returns the part of command string for the given {@code remark}'s details.
     */
    public static String getRemarkDetails(Remark remark) {
        StringBuilder sb = new StringBuilder();
        sb.append(remark.getText() + " ");
        remark.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
