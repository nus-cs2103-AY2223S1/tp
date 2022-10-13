package seedu.phu.testutil;

import static seedu.phu.logic.parser.CliSyntax.PREFIX_APPLICATION_PROCESS;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Set;

import seedu.phu.logic.commands.AddCommand;
import seedu.phu.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.tag.Tag;

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
        sb.append(PREFIX_NAME + internship.getName().fullName + " ");
        sb.append(PREFIX_PHONE + internship.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + internship.getEmail().value + " ");
        sb.append(PREFIX_REMARK + internship.getRemark().value + " ");
        sb.append(PREFIX_POSITION + internship.getPosition().positionName + " ");
        sb.append(PREFIX_APPLICATION_PROCESS + internship.getApplicationProcess().toString() + " ");
        sb.append(PREFIX_DATE + internship.getDate().toInputFormat() + " ");
        sb.append(PREFIX_WEBSITE + internship.getWebsite().value + " ");
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
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRemark().ifPresent(internship -> sb.append(PREFIX_REMARK).append(internship.value).append(" "));
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION).append(position.positionName)
                .append(" "));
        descriptor.getApplicationProcess().ifPresent(applicationProcess -> sb.append(PREFIX_APPLICATION_PROCESS)
                .append(applicationProcess.value).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.toInputFormat()).append(" "));
        descriptor.getWebsite().ifPresent(website -> sb.append(PREFIX_WEBSITE).append(website.value).append(" "));
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
