package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddStuCommand;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
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
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        student.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_ID + student.getId().value + " ");
        sb.append(PREFIX_HANDLE + student.getTelegramHandle().telegramHandle + " ");
        student.getStudentModuleInfo().stream().forEach(
                s -> sb.append(PREFIX_MODULE_CODE + s.fullCode + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStuCommand.EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getTelegramHandle().ifPresent(handle -> sb.append(PREFIX_HANDLE)
                .append(handle.telegramHandle).append(" "));
        if (descriptor.getStudentModuleInfo().isPresent()) {
            Set<ModuleCode> moduleCodes = descriptor.getStudentModuleInfo().get();
            if (moduleCodes.isEmpty()) {
                sb.append(PREFIX_MODULE_CODE).append(" ");
            } else {
                moduleCodes.forEach(s -> sb.append(PREFIX_MODULE_CODE).append(s.fullCode).append(" "));
            }
        }
        if (descriptor.getTeachingAssistantInfo().isPresent()) {
            Set<ModuleCode> moduleCodes = descriptor.getTeachingAssistantInfo().get();
            if (moduleCodes.isEmpty()) {
                sb.append(PREFIX_STUDENT_TA);
            } else {
                moduleCodes.forEach(s -> sb.append(PREFIX_STUDENT_TA).append(s.fullCode).append(" "));
            }
        }
        return sb.toString();
    }
}
