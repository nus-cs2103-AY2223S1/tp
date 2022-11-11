package jarvis.testutil;

import static jarvis.logic.parser.CliSyntax.PREFIX_MATRIC_NUM;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;

import jarvis.logic.commands.AddStudentCommand;
import jarvis.logic.commands.EditStudentCommand.EditStudentDescriptor;
import jarvis.model.Student;

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
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_MATRIC_NUM + student.getMatricNum().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getMatricNum().ifPresent(
                matricNum -> sb.append(PREFIX_MATRIC_NUM).append(matricNum.value).append(" "));
        return sb.toString();
    }
}
