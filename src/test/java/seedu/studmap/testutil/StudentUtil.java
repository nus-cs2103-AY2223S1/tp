package seedu.studmap.testutil;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_GIT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.studmap.logic.commands.AddCommand;
import seedu.studmap.logic.commands.EditCommand.EditCommandStudentEditor;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getstudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getstudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_MODULE + student.getModule().value + " ");
        sb.append(PREFIX_ID + student.getId().value + " ");
        sb.append(PREFIX_GIT + student.getGitName().value + " ");
        sb.append(PREFIX_HANDLE + student.getTeleHandle().value + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditstudentDescriptorDetails(EditCommandStudentEditor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getModule().ifPresent(module -> sb.append(PREFIX_MODULE).append(module.value).append(" "));
        descriptor.getId().ifPresent(studentID -> sb.append(PREFIX_ID).append(studentID.value).append(" "));
        descriptor.getGitName().ifPresent(gitName -> sb.append(PREFIX_GIT).append(gitName.value).append(" "));
        descriptor.getHandle().ifPresent(teleHandle -> sb.append(PREFIX_HANDLE).append(teleHandle.value).append(" "));
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
