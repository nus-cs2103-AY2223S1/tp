package seedu.trackascholar.testutil;

import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;

import java.util.Set;

import seedu.trackascholar.logic.commands.AddCommand;
import seedu.trackascholar.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.major.Major;

/**
 * A utility class for Applicant.
 */
public class ApplicantUtil {

    /**
     * Returns an add command string for adding the {@code applicant}.
     */
    public static String getAddCommand(Applicant applicant) {
        return AddCommand.COMMAND_WORD + " " + getApplicantDetails(applicant);
    }

    /**
     * Returns the part of command string for the given {@code applicant}'s details.
     */
    public static String getApplicantDetails(Applicant applicant) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + applicant.getFullName() + " ");
        sb.append(PREFIX_PHONE + applicant.getPhoneNumber() + " ");
        sb.append(PREFIX_EMAIL + applicant.getEmailAddress() + " ");
        sb.append(PREFIX_SCHOLARSHIP + applicant.getScholarshipName() + " ");
        sb.append(PREFIX_APPLICATION_STATUS + applicant.getStatusOfApplication() + " ");
        applicant.getMajors().stream().forEach(
            major -> sb.append(PREFIX_MAJOR + major.getName() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditApplicantDescriptor}'s details.
     */
    public static String getEditApplicantDescriptorDetails(EditApplicantDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.getFullName()).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.getNumber()).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.getEmailAddress()).append(" "));
        descriptor.getScholarship()
                .ifPresent(scholarship -> sb.append(PREFIX_SCHOLARSHIP)
                        .append(scholarship.getScholarship()).append(" "));
        descriptor.getApplicationStatus()
                .ifPresent(applicationStatus -> sb.append(PREFIX_APPLICATION_STATUS)
                        .append(applicationStatus.getApplicationStatus()).append(" "));
        if (descriptor.getMajors().isPresent()) {
            Set<Major> majors = descriptor.getMajors().get();
            if (majors.isEmpty()) {
                sb.append(PREFIX_MAJOR);
            } else {
                majors.forEach(major -> sb.append(PREFIX_MAJOR).append(major.getName()).append(" "));
            }
        }
        return sb.toString();
    }
}
