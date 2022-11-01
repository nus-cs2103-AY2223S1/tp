package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Set;

import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.logic.commands.student.EditStudentCommand.EditPersonDescriptor;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddStudentCommand(Student student) {
        return AddStudentCommand.COMMAND_WORD + " " + getPersonDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_ID + student.getId().id + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM + student.getTelegram().telegram + " ");
        sb.append(PREFIX_MODULE + student.getTutorialModule().moduleCode + " ");
        sb.append(PREFIX_TUTORIAL + student.getTutorialName().fullName + " ");
        sb.append(PREFIX_ATTENDANCE + String.valueOf(student.getAttendance().value) + " ");
        sb.append(PREFIX_PARTICIPATION + String.valueOf(student.getParticipation().value) + " ");
        sb.append(PREFIX_GRADE + student.getGrade().value + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.id).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM)
                .append(telegram.telegram).append(" "));
        descriptor.getTutorialModule().ifPresent(module -> sb.append(PREFIX_MODULE)
                .append(module.moduleCode).append(" "));
        descriptor.getTutorialName().ifPresent(tutorial -> sb.append(PREFIX_TUTORIAL)
                .append(tutorial.fullName).append(" "));
        descriptor.getAttendance().ifPresent(attendance -> sb.append(PREFIX_ATTENDANCE)
                .append(attendance.value).append(" "));
        descriptor.getParticipation().ifPresent(participation -> sb.append(PREFIX_PARTICIPATION)
                .append(participation.value).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE)
                .append(grade.value).append(" "));
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
