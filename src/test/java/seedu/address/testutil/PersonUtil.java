package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;
import seedu.address.model.tag.Tag;

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

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        Portfolio portfolio = person.getPortfolio();
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_INCOME + person.getIncome().value + " ");
        sb.append(PREFIX_MEETING_DATE + person.getMeeting().getMeetingDate().get() + " ");
        sb.append(PREFIX_MEETING_LOCATION + person.getMeeting().getMeetingLocation().get() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_RISK + portfolio.getRisk().get() + " ");
        portfolio.getPlans().stream().forEach(
            s -> sb.append(PREFIX_PLAN + s.value + " ")
        );
        portfolio.getNotes().stream().forEach(
            s -> sb.append(PREFIX_NOTE + s.value + " ")
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
        descriptor.getIncome().ifPresent(income -> sb.append(PREFIX_INCOME).append(income.value).append(" "));
        descriptor.getMeetingDate().ifPresent(meetingDate -> sb.append(PREFIX_MEETING_DATE)
            .append(meetingDate.get()).append(" "));
        descriptor.getMeetingLocation().ifPresent(meetingLocation -> sb.append(PREFIX_MEETING_LOCATION)
            .append(meetingLocation.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        descriptor.getRisk().ifPresent(risk -> sb.append(PREFIX_RISK).append(risk.get()).append(" "));

        if (descriptor.getPlans().isPresent()) {
            Set<Plan> plans = descriptor.getPlans().get();
            if (plans.isEmpty()) {
                sb.append(PREFIX_PLAN).append(" ");
            } else {
                plans.forEach(s -> sb.append(PREFIX_PLAN).append(s.value).append(" "));
            }
        }

        if (descriptor.getNotes().isPresent()) {
            Set<Note> notes = descriptor.getNotes().get();
            if (notes.isEmpty()) {
                sb.append(PREFIX_NOTE).append(" ");
            } else {
                notes.forEach(s -> sb.append(PREFIX_NOTE).append(s.value).append(" "));
            }
        }

        return sb.toString();
    }
}
