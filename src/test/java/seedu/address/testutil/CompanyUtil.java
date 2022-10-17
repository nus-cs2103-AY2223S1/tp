package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.company.Company;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Company.
 */
public class CompanyUtil {

    /**
     * Returns an add command string for adding the {@code company}.
     */
    public static String getCreateCommand(Company company, Index index) {
        return CreateCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getCompanyDetails(company);
    }

    /**
     * Returns the part of command string for the given {@code company}'s details.
     */
    public static String getCompanyDetails(Company company) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + company.getName().fullName + " ");
        sb.append(PREFIX_ADDRESS + company.getAddress().value + " ");
        company.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
