package seedu.guest.testutil;

import static seedu.guest.logic.parser.CliSyntax.PREFIX_DATE_RANGE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_IS_ROOM_CLEAN;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NUMBER_OF_GUESTS;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_ROOM;

import seedu.guest.logic.commands.AddCommand;
import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.model.guest.Guest;

/**
 * A utility class for Guest.
 */
public class GuestUtil {

    /**
     * Returns an add command string for adding the {@code guest}.
     */
    public static String getAddCommand(Guest guest) {
        return AddCommand.COMMAND_WORD + " " + getGuestDetails(guest);
    }

    /**
     * Returns the part of command string for the given {@code guest}'s details.
     */
    public static String getGuestDetails(Guest guest) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + guest.getName().fullName + " ");
        sb.append(PREFIX_PHONE + guest.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + guest.getEmail().value + " ");
        sb.append(PREFIX_ROOM + guest.getRoom().value + " ");
        sb.append(PREFIX_DATE_RANGE + guest.getDateRange().value + " ");
        sb.append(PREFIX_NUMBER_OF_GUESTS + guest.getNumberOfGuests().value + " ");
        sb.append(PREFIX_IS_ROOM_CLEAN + guest.getIsRoomClean().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditGuestDescriptor}'s details.
     */
    public static String getEditGuestDescriptorDetails(EditGuestDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room.value).append(" "));
        descriptor.getDateRange().ifPresent(dateRange -> sb.append(PREFIX_DATE_RANGE).append(dateRange.value)
                .append(" "));
        descriptor.getNumberOfGuests().ifPresent(numberOfGuests -> sb.append(PREFIX_NUMBER_OF_GUESTS)
                .append(numberOfGuests.value).append(" "));
        descriptor.getIsRoomClean().ifPresent(isRoomClean -> sb.append(PREFIX_IS_ROOM_CLEAN)
                .append(isRoomClean.value).append(" "));
        return sb.toString();
    }
}
