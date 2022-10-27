package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLIED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.model.internship.Internship;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Internship.
 */
public class InternshipUtil {

    /**
     * Returns an add command string for adding the {@code internship}.
     */
    public static String getAddCommand(Internship internship) {
        return AddCommand.COMMAND_WORD + " " + getInternshipDetails(internship);
    }

    /**
     * Returns the part of command string for the given {@code internship}'s details.
     */
    public static String getInternshipDetails(Internship internship) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY + internship.getCompany().value + " ");
        sb.append(PREFIX_LINK + internship.getLink().value + " ");
        sb.append(PREFIX_DESCRIPTION + internship.getDescription().value + " ");
        sb.append(PREFIX_APPLIED_DATE + internship.getAppliedDate().value + " ");
        sb.append(PREFIX_INTERVIEW_DATE_TIME + internship.getInterviewDateTime().value + " ");
        internship.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditInternshipDescriptor}'s details.
     */
    public static String getEditInternshipDescriptorDetails(EditInternshipDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY)
                .append(company.value).append(" "));
        descriptor.getLink().ifPresent(link -> sb.append(PREFIX_LINK)
                .append(link.value).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.value).append(" "));
        descriptor.getAppliedDate().ifPresent(appliedDate -> sb.append(PREFIX_APPLIED_DATE)
                .append(appliedDate.value).append(" "));
        descriptor.getInterviewDateTime().ifPresent(interviewDateTime -> sb.append(PREFIX_INTERVIEW_DATE_TIME)
                .append(interviewDateTime.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
