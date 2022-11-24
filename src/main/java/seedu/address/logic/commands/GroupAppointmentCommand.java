package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEY;
import static seedu.address.model.Model.COMPARATOR_GROUP_MARK_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_GROUP_PATIENT_APPOINTMENTS;
import static seedu.address.model.Model.COMPARATOR_GROUP_TAG_APPOINTMENTS;

import seedu.address.model.Key;
import seedu.address.model.Model;

/**
 * Group all appointments in idENTify to the user.
 */
public class GroupAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "group";
    public static final String DESCRIPTOR_WORD = "appts";
    public static final String MESSAGE_SUCCESS_APPOINTMENTS = "Grouped all appointments by %1$s.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + DESCRIPTOR_WORD
            + ": Group all appointments by their tags or patients or mark status.\n"
            + "Parameters: KEY (must be \"tag\"(\"t\"), \"patient\"(\"p\") or \"mark\"(\"m\")) "
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " "
            + PREFIX_KEY + "tag ";

    private final Key key;

    public GroupAppointmentCommand(Key key) {
        this.key = key;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (this.key.equals(Key.TAG)) {
            model.updateAppointmentComparator(COMPARATOR_GROUP_TAG_APPOINTMENTS);
        } else if (this.key.equals(Key.PATIENT)) {
            model.updateAppointmentComparator(COMPARATOR_GROUP_PATIENT_APPOINTMENTS);
        } else {
            model.updateAppointmentComparator(COMPARATOR_GROUP_MARK_APPOINTMENTS);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS_APPOINTMENTS, key.toString().toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupAppointmentCommand // instanceof handles nulls
                && key.equals(((GroupAppointmentCommand) other).key)); // state check
    }
}
