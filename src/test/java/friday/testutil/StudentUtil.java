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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PREFIX_NAME + student.getName().fullName + " ");
        stringBuilder.append(PREFIX_TELEGRAMHANDLE + student.getTelegramHandle().value + " ");
        stringBuilder.append(PREFIX_CONSULTATION + student.getConsultation().toString() + " ");
        stringBuilder.append(PREFIX_MASTERYCHECK + student.getMasteryCheck().toString() + " ");
        student.getTags().stream().forEach(
            s -> stringBuilder.append(PREFIX_TAG + s.tagName + " ")
        );
        return stringBuilder.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder stringBuilder = new StringBuilder();
        descriptor.getName().ifPresent(name ->
                stringBuilder.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getTelegramHandle().ifPresent(phone ->
                stringBuilder.append(PREFIX_TELEGRAMHANDLE).append(phone.value)
                .append(" "));
        descriptor.getConsultation().ifPresent(email ->
                stringBuilder.append(PREFIX_CONSULTATION).append(email.toString())
                .append(" "));
        descriptor.getMasteryCheck().ifPresent(address ->
                stringBuilder.append(PREFIX_MASTERYCHECK).append(address.toString())
                .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                stringBuilder.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> stringBuilder.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditGradeDescriptor}'s grades.
     */
    public static String getEditGradesDescriptorDetails(EditGradeDescriptor descriptor) {
        StringBuilder stringBuilder = new StringBuilder();
        descriptor.getRa1().ifPresent(ra1 ->
                stringBuilder.append(PREFIX_RA1).append(ra1.getScore()).append(" "));
        descriptor.getRa2().ifPresent(ra2 ->
                stringBuilder.append(PREFIX_RA2).append(ra2.getScore()).append(" "));
        descriptor.getMt().ifPresent(mt ->
                stringBuilder.append(PREFIX_MIDTERM).append(mt.getScore()).append(" "));
        descriptor.getFt().ifPresent(ft ->
                stringBuilder.append(PREFIX_FINALS).append(ft.getScore()).append(" "));
        descriptor.getPa().ifPresent(pa ->
                stringBuilder.append(PREFIX_PRACTICAL).append(pa.getScore()).append(" "));
        return stringBuilder.toString();
    }
}
