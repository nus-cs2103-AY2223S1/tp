package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.BookCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    public static String getBookCommand(Appointment appointment) {
        return BookCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        return PREFIX_REASON + " " + appointment.getReason() + " " + PREFIX_DATE
                + " " + appointment.getDateTime().format(Appointment.DATE_FORMATTER);
    }

    /**
     * Returns the part of command string for the given {@code EditAppointmentDescriptor}'s details.
     */
    public static String getEditAppointmentDescriptorDetails(EditAppointmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getReason().ifPresent(reason -> sb.append(PREFIX_REASON).append(reason).append(" "));
        descriptor.getDateTime().ifPresent(dateTime -> sb.append(PREFIX_DATE)
                .append(dateTime.format(Appointment.DATE_FORMATTER)).append(" "));
        descriptor.getTimePeriod().ifPresent(timePeriod -> sb.append(PREFIX_RECURRING_PERIOD)
                .append(getFormattedPeriod(timePeriod)).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.toString()).append(" "));
            }
        }
        return sb.toString();
    }

    private static String getFormattedPeriod(List<Integer> timePeriod) {
        String str = "%sY%sM%sD";
        str = String.format(str, timePeriod.get(0), timePeriod.get(1), timePeriod.get(2));
        return str;
    }
}
