package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddStuCommand;
import seedu.address.model.person.Student;

/**
 * A utility class for Person.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddStuCommand(Student student) {
        return AddStuCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        student.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_ID + student.getId().value + " ");
        sb.append(PREFIX_HANDLE + student.getTelegramHandle().telegramHandle + " ");
        student.getStudentModuleInfo().stream().forEach(
                s -> sb.append(PREFIX_STUDENT_INFO + s.fullCode + " ")
        );
        return sb.toString();
    }
}
