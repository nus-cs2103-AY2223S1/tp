package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;

import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.model.internship.Internship;

/**
 * A utility class for Internship.
 */
public class InternshipUtil {

    /**
     * Returns an add command string for adding the {@code internship}.
     */
    public static String getAddInternshipCommand(Internship internship) {
        return AddInternshipCommand.COMMAND_WORD + " " + getInternshipDetails(internship);
    }

    /**
     * Returns the part of command string for the given {@code internship}'s details.
     */
    public static String getInternshipDetails(Internship internship) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME + internship.getCompanyName().fullName + " ");
        sb.append(PREFIX_INTERNSHIP_ROLE + internship.getInternshipRole().toString() + " ");
        sb.append(PREFIX_INTERNSHIP_STATUS + internship.getInternshipStatus().toString() + " ");
        sb.append(PREFIX_INTERVIEW_DATE + internship.getInterviewDate().toString());
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditInternshipDescriptorDetails(EditInternshipCommand.EditInternshipDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompanyName().ifPresent(company -> sb.append(PREFIX_COMPANY_NAME).append(company).append(" "));
        descriptor.getInternshipRole().ifPresent(role -> sb.append(PREFIX_INTERNSHIP_ROLE).append(role).append(" "));
        descriptor.getInternshipStatus().ifPresent(
                status -> sb.append(PREFIX_INTERNSHIP_STATUS).append(status).append(" "));
        descriptor.getInterviewDate().ifPresent(date -> sb.append(PREFIX_INTERVIEW_DATE).append(date).append(" "));

        return sb.toString();
    }
}
