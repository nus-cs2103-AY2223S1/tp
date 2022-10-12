package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTOFKIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    public static String getAddCommand(TuitionClass tuitionClass) {
        return AddCommand.COMMAND_WORD + " " + getClassDetails(tuitionClass);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        if (person instanceof Student) {
            sb.append("student ");
        } else if (person instanceof Tutor) {
            sb.append("tutor ");
        }
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        if (person instanceof Student) {
            sb.append(PREFIX_SCHOOL + ((Student) person).getSchool().school + " ");
            sb.append(PREFIX_LEVEL + ((Student) person).getLevel().level + " ");
            sb.append(PREFIX_NEXTOFKIN + ((Student) person).getNextOfKin().nextOfKin + " ");
        } else if (person instanceof Tutor) {
            sb.append(PREFIX_QUALIFICATION + ((Tutor) person).getQualification().qualification + " ");
            sb.append(PREFIX_INSTITUTION + ((Tutor) person).getInstitution().institution + " ");
        }
        return sb.toString();
    }

    public static String getClassDetails(TuitionClass tuitionClass) { //to move to ClassUtil in future
        StringBuilder sb = new StringBuilder();
        sb.append("class ");
        sb.append(PREFIX_NAME + tuitionClass.getName().name + " ");
        sb.append(PREFIX_SUBJECT + tuitionClass.getSubject().subject + " ");
        sb.append(PREFIX_LEVEL + tuitionClass.getLevel().level + " ");
        sb.append(PREFIX_DAY + tuitionClass.getDay().day + " ");
        sb.append(PREFIX_TIME + tuitionClass.getTime().timeFrame + " ");
        tuitionClass.getTags().stream().forEach(
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
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
