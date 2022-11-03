package seedu.classify.testutil;

import static seedu.classify.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import java.util.Set;

import seedu.classify.logic.commands.AddStudentCommand;
import seedu.classify.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Student;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Student person) {
        return AddStudentCommand.COMMAND_WORD + " " + getStudentDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STUDENT_NAME + student.getStudentName().fullName + " ");
        sb.append(PREFIX_ID + student.getId().value + " ");
        sb.append(PREFIX_CLASS + student.getClassName().className + " ");
        sb.append(PREFIX_PARENT_NAME + student.getParentName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        student.getExams().stream().forEach(
            s -> sb.append(PREFIX_EXAM + s.toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getStudentName().ifPresent(name -> sb.append(PREFIX_STUDENT_NAME).append(name.fullName).append(" "));
        descriptor.getClassName().ifPresent(name -> sb.append(PREFIX_CLASS).append(name.className).append(" "));
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getParentName().ifPresent(name -> sb.append(PREFIX_PARENT_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        if (descriptor.getExams().isPresent()) {
            Set<Exam> tags = descriptor.getExams().get();
            if (!tags.isEmpty()) {
                tags.forEach(s -> sb.append(PREFIX_EXAM).append(s.toString()).append(" "));
            }
        }
        return sb.toString();
    }
}
