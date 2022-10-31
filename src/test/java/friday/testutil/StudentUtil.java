package friday.testutil;

import static friday.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static friday.logic.parser.CliSyntax.PREFIX_FINALS;
import static friday.logic.parser.CliSyntax.PREFIX_MASTERYCHECK;
import static friday.logic.parser.CliSyntax.PREFIX_MIDTERM;
import static friday.logic.parser.CliSyntax.PREFIX_NAME;
import static friday.logic.parser.CliSyntax.PREFIX_PRACTICAL;
import static friday.logic.parser.CliSyntax.PREFIX_RA1;
import static friday.logic.parser.CliSyntax.PREFIX_RA2;
import static friday.logic.parser.CliSyntax.PREFIX_TAG;
import static friday.logic.parser.CliSyntax.PREFIX_TELEGRAMHANDLE;

import java.util.Set;

import friday.logic.commands.AddCommand;
import friday.logic.commands.EditCommand.EditStudentDescriptor;
import friday.logic.commands.GradeCommand.EditGradeDescriptor;
import friday.model.student.Student;
import friday.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_TELEGRAMHANDLE + student.getTelegramHandle().value + " ");
        sb.append(PREFIX_CONSULTATION + student.getConsultation().toString() + " ");
        sb.append(PREFIX_MASTERYCHECK + student.getMasteryCheck().toString() + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getTelegramHandle().ifPresent(phone -> sb.append(PREFIX_TELEGRAMHANDLE).append(phone.value)
                .append(" "));
        descriptor.getConsultation().ifPresent(email -> sb.append(PREFIX_CONSULTATION).append(email.toString())
                .append(" "));
        descriptor.getMasteryCheck().ifPresent(address -> sb.append(PREFIX_MASTERYCHECK).append(address.toString())
                .append(" "));
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

    /**
     * Returns the part of command string for the given {@code EditGradeDescriptor}'s grades.
     */
    public static String getEditGradesDescriptorDetails(EditGradeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRa1().ifPresent(ra1 -> sb.append(PREFIX_RA1).append(ra1.getScore()).append(" "));
        descriptor.getRa2().ifPresent(ra2 -> sb.append(PREFIX_RA2).append(ra2.getScore()).append(" "));
        descriptor.getMt().ifPresent(mt -> sb.append(PREFIX_MIDTERM).append(mt.getScore()).append(" "));
        descriptor.getFt().ifPresent(ft -> sb.append(PREFIX_FINALS).append(ft.getScore()).append(" "));
        descriptor.getPa().ifPresent(pa -> sb.append(PREFIX_PRACTICAL).append(pa.getScore()).append(" "));
        return sb.toString();
    }
}
