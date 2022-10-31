package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICEHOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ProfCommand;
import seedu.address.logic.commands.StudentCommand;
import seedu.address.logic.commands.TaCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMatchesPredicate;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        if (person instanceof Student) {
            return StudentCommand.COMMAND_WORD + " " + getPersonDetails((Student) person);
        } else if (person instanceof Professor) {
            return ProfCommand.COMMAND_WORD + " " + getPersonDetails((Professor) person);
        } else if (person instanceof TeachingAssistant) {
            return TaCommand.COMMAND_WORD + " " + getPersonDetails((TeachingAssistant) person);
        }
        return null;
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GENDER + person.getGender().value + " ");
        if (!person.getUsername().value.equals("")) {
            sb.append(PREFIX_GITHUBUSERNAME + person.getUsername().value + " ");
        }
        sb.append(PREFIX_LOCATION + person.getLocation().value + " ");
        if (!person.getUsername().value.equals("")) {
            sb.append(PREFIX_GITHUBUSERNAME + person.getUsername().value + " ");
        }
        if (!person.getYear().value.equals("")) {
            sb.append(PREFIX_YEAR + person.getYear().value + " ");
        }
        person.getModuleCodes().stream().forEach(
                s -> sb.append(PREFIX_MODULE_CODE + s.value + " ")
        );
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    public static String getPersonDetails(TeachingAssistant person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_MODULE_CODE + person.getModuleCode().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GENDER + person.getGender().value + " ");
        if (!person.getUsername().value.equals("")) {
            sb.append(PREFIX_GITHUBUSERNAME + person.getUsername().value + " ");
        }
        sb.append(PREFIX_LOCATION + person.getLocation().value + " ");
        if (!person.getRating().value.equals("")) {
            sb.append(PREFIX_RATING + person.getRating().value + " ");
        }
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    public static String getPersonDetails(Professor person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_MODULE_CODE + person.getModuleCode().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GENDER + person.getGender().value + " ");
        if (!person.getUsername().value.equals("")) {
            sb.append(PREFIX_GITHUBUSERNAME + person.getUsername().value + " ");
        }
        sb.append(PREFIX_LOCATION + person.getLocation().value + " ");
        if (!person.getSpecialisation().value.equals("")) {
            sb.append(PREFIX_SPECIALISATION + person.getSpecialisation().value + " ");
        }
        if (!person.getRating().value.equals("")) {
            sb.append(PREFIX_RATING + person.getRating().value + " ");
        }
        if (!person.getOfficeHour().value.equals("")) {
            sb.append(PREFIX_OFFICEHOUR + "2-18:00-3" + " ");
        }
        person.getTags().stream().forEach(
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
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getYear().ifPresent(year -> sb.append(PREFIX_YEAR).append(year.value).append(" "));
        descriptor.getGithubUsername().ifPresent(username -> sb.append(PREFIX_GITHUBUSERNAME)
                .append(username.value).append(" "));
        descriptor.getRating().ifPresent(rating -> sb.append(PREFIX_RATING).append(rating.value).append(" "));
        descriptor.getOfficeHour().ifPresent(officeHour -> sb.append(PREFIX_OFFICEHOUR)
                .append(officeHour.value).append(" "));
        descriptor.getSpecialisation().ifPresent(specialisation -> sb
                .append(PREFIX_SPECIALISATION)
                .append(specialisation.value)
                .append(" "));
        descriptor.getModuleCode().ifPresent(moduleCode-> sb.append(PREFIX_MODULE_CODE)
                .append(moduleCode.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getModuleCodes().isPresent()) {
            Set<ModuleCode> modules = descriptor.getModuleCodes().get();
            if (!modules.isEmpty()) {
                modules.forEach(s -> sb.append(PREFIX_MODULE_CODE).append(s.value).append(" "));
            }
        }
        return sb.toString();
    }

    public static String getFindCommandDetails(PersonMatchesPredicate predicate) {
        String commandDetails = "";
        if (predicate.getHasNamesList()) {
            commandDetails += PREFIX_NAME + String.join(" ", predicate.getNamesList()) + " ";
        }

        if (predicate.getHasModulesList()) {
            commandDetails += PREFIX_MODULE_CODE + String.join(" ", predicate.getModulesSet()) + " ";
        }

        if (predicate.getHasEmailsList()) {
            commandDetails += PREFIX_EMAIL + String.join(" ", predicate.getEmailsList()) + " ";
        }

        if (predicate.getHasPhonesList()) {
            commandDetails += PREFIX_PHONE + String.join(" ", predicate.getPhonesList()) + " ";
        }

        if (predicate.getHasLocationsList()) {
            commandDetails += PREFIX_LOCATION + String.join(" ", predicate.getLocationsList()) + " ";
        }

        if (predicate.getHasGendersList()) {
            commandDetails += PREFIX_GENDER + String.join(" ", predicate.getGendersList()) + " ";
        }

        if (predicate.getHasOfficeHoursList()) {
            commandDetails += PREFIX_OFFICEHOUR + String.join(" ", predicate.getOfficeHoursList()) + " ";
        }

        if (predicate.getHasUserNamesList()) {
            commandDetails += PREFIX_GITHUBUSERNAME + String.join(" ", predicate.getUserNamesList()) + " ";
        }

        if (predicate.getHasRatingsList()) {
            commandDetails += PREFIX_RATING + String.join(" ", predicate.getRatingsList()) + " ";
        }

        if (predicate.getHasSpecList()) {
            commandDetails += PREFIX_SPECIALISATION + String.join(" ", predicate.getOfficeHoursList()) + " ";
        }

        if (predicate.getHasTagsList()) {
            commandDetails += PREFIX_TAG + String.join(" ", predicate.getTagsSet()) + " ";
        }

        if (predicate.getHasTypesList()) {
            commandDetails += PREFIX_TYPE + String.join(" ", predicate.getTypesList()) + " ";
        }

        if (predicate.getHasSpecsList()) {
            commandDetails += PREFIX_SPECIALISATION + String.join(" ", predicate.getSpecList()) + " ";
        }

        if (predicate.getHasTagsList()) {
            commandDetails += PREFIX_TAG + String.join(" ", predicate.getTagsSet()) + " ";
        }

        if (predicate.getHasYearsList()) {
            commandDetails += PREFIX_YEAR + String.join(" ", predicate.getYearsList()) + " ";
        }


        return commandDetails;
    }
}
