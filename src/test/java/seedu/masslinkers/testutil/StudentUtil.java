package seedu.masslinkers.testutil;

import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.masslinkers.logic.commands.AddCommand;
import seedu.masslinkers.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Student;
//@@author
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
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM + student.getTelegram().handle + " ");
        sb.append(PREFIX_GITHUB + student.getGitHub().username + " ");
        student.getInterests().stream().forEach(
            s -> sb.append(PREFIX_INTEREST + s.interestName + " ")
        );
        student.getMods().stream().forEach(
                s -> sb.append(PREFIX_MOD + s.getModName() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.handle).append(" "));
        descriptor.getGitHub().ifPresent(github -> sb.append(PREFIX_GITHUB).append(github.username).append(" "));
        if (descriptor.getInterests().isPresent()) {
            Set<Interest> interests = descriptor.getInterests().get();
            if (interests.isEmpty()) {
                sb.append(PREFIX_INTEREST);
            } else {
                interests.forEach(s -> sb.append(PREFIX_INTEREST).append(s.interestName).append(" "));
            }
        }
        if (descriptor.getMods().isPresent()) {
            ObservableList<Mod> mods = descriptor.getMods().get();
            if (mods.isEmpty()) {
                sb.append(PREFIX_MOD);
            } else {
                mods.forEach(s -> sb.append(PREFIX_MOD).append(s.getModName()).append(" "));
            }
        }
        return sb.toString();
    }
}
