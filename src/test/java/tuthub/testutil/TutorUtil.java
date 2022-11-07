package tuthub.testutil;

import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;

import tuthub.logic.commands.AddCommand;
import tuthub.logic.commands.EditCommand.EditTutorDescriptor;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.Tutor;

/**
 * A utility class for Tutor.
 */
public class TutorUtil {

    /**
     * Returns an add command string for adding the {@code tutor}.
     */
    public static String getAddCommand(Tutor tutor) {
        return AddCommand.COMMAND_WORD + " " + getTutorDetails(tutor);
    }

    /**
     * Returns the part of command string for the given {@code tutor}'s details.
     */
    public static String getTutorDetails(Tutor tutor) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + tutor.getName().fullName + " ");
        sb.append(PREFIX_PHONE + tutor.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + tutor.getEmail().value + " ");
        tutor.getModules().stream().forEach(
            s -> sb.append(PREFIX_MODULE + s.value + " ")
        );
        sb.append(PREFIX_YEAR + tutor.getYear().value + " ");
        sb.append(PREFIX_STUDENTID + tutor.getStudentId().value + " ");
        sb.append(PREFIX_TEACHINGNOMINATION + tutor.getTeachingNomination().value + " ");
        sb.append(PREFIX_RATING + tutor.getRating().value + " ");
        tutor.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTutorDescriptor}'s details.
     */
    public static String getEditTutorDescriptorDetails(EditTutorDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getModules().ifPresent(s -> s.forEach(m -> sb.append(PREFIX_MODULE).append(m.value).append(" ")));
        descriptor.getYear().ifPresent(year -> sb.append(PREFIX_YEAR).append(year.value).append(" "));
        descriptor.getStudentId().ifPresent(studentId -> sb.append(PREFIX_STUDENTID)
                .append(studentId.value).append(" "));
        descriptor.getRating().ifPresent(rating -> sb.append(PREFIX_RATING)
                .append(rating.value).append(" "));
        descriptor.getTeachingNomination().ifPresent(teachingNomination -> sb.append(PREFIX_TEACHINGNOMINATION)
                .append(teachingNomination.value).append(" "));
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
