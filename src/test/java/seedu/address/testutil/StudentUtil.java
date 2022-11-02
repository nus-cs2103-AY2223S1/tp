package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.logic.commands.AddStuCommand;
import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.model.student.Student;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an addstu command string for adding the {@code student}.
     */
    public static String getAddStuCommand(Student student) {
        return AddStuCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_TELEGRAM + student.getTelegram().telegram + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.telegram).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAttendance().ifPresent(attendance -> sb.append(PREFIX_ATTENDANCE).append(attendance.attendance)
                .append(" "));

        return sb.toString();
    }
}
