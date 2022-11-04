package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

/**
 * Creates a past appointment for a patient.
 */
public class CreatePastAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "appt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a past appointment for a patient.\n"
            + "Parameters: INDEX (Must be a positive integer) "
            + PREFIX_DATE + "DATE (Must follow dd-MM-yyyy syntax) "
            + PREFIX_MEDICATION + "MEDICATION "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "01-01-2001 "
            + PREFIX_MEDICATION + "ibuprofen "
            + PREFIX_DIAGNOSIS + "headache, medicine given for 3 days ";
    public static final String MESSAGE_SUCCESS = "Past appointment created for %1$s.\n";
    public static final String DUPLICATE_APPOINTMENT_MESSAGE = "Duplicate past appointments are not allowed.";
    private final Index index;
    private final PastAppointment appt;

    /**
     * Creates a command to add past appointments to a patient's record.
     * @param index of the patient in the filtered patient list to edit
     */
    public CreatePastAppointmentCommand(Index index, PastAppointment appt) {
        requireNonNull(index);
        this.index = index;
        this.appt = appt;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person patient = lastShownList.get(index.getZeroBased());
        if (patient.hasPastAppointment(appt)) {
            throw new CommandException(DUPLICATE_APPOINTMENT_MESSAGE);
        }

        patient.addPastAppointment(appt);
        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getName()) + appt);
    }
}
