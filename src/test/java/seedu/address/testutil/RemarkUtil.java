package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.remark.Remark;

/**
 * A utility class for Remark.
 */
public class RemarkUtil {

    /**
     * Returns an add command string for adding the {@code company}.
     */
    public static String getCreateCommand(Remark company, Index index) {
        return CreateCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getRemarkDetails(company);
    }

    /**
     * Returns the part of command string for the given {@code company}'s details.
     */
    public static String getRemarkDetails(Remark company) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + company.getName().fullName + " ");
        sb.append(PREFIX_ADDRESS + company.getAddress().value + " ");
        company.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
