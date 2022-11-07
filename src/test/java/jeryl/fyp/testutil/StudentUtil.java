package jeryl.fyp.testutil;

import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_EMAIL;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.commands.DeleteStudentCommand;
import jeryl.fyp.logic.commands.EditCommand.EditStudentDescriptor;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add student command string for adding the {@code student}.
     */
    public static String getAddStudentCommand(Student student) {
        return AddStudentCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns an add student command string using another command word for adding the {@code student}.
     */
    public static String getAlternativeAddStudentCommand(Student student) {
        return AddStudentCommand.ALTERNATIVE_COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns a delete student command string for deleting the {@code student}.
     */
    public static String getDeleteStudentCommand(Student student) {
        return DeleteStudentCommand.COMMAND_WORD + " " + PREFIX_STUDENT_ID + student.getStudentId();
    }

    /**
     * Returns a delete student command string using another command word for deleting the {@code student}.
     */
    public static String getAlternativeDeleteStudentCommand(Student student) {
        return DeleteStudentCommand.ALTERNATIVE_COMMAND_WORD + " " + PREFIX_STUDENT_ID + student.getStudentId();
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STUDENT_NAME + student.getStudentName().fullStudentName + " ");
        sb.append(PREFIX_STUDENT_ID + student.getStudentId().id + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_PROJECT_NAME + student.getProjectName().fullProjectName + " ");
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
        descriptor.getStudentName().ifPresent(name -> sb.append(PREFIX_STUDENT_NAME)
                .append(name.fullStudentName).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getProjectName().ifPresent(projectName -> sb.append(PREFIX_PROJECT_NAME)
                .append(projectName.fullProjectName).append(" "));
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
