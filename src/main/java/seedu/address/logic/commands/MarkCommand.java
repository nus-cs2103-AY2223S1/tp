package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the appointment of the person identified "
            + "by the index numbers used in the displayed person and their corresponding appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX_OF_PERSON (must be a positive integer) "
            + "INDEX_OF_APPOINTMENT (must be a positive integer)";

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked appointment %1$s for Person: %2$s";
    public static final String MESSAGE_NOT_MARKED = "All 2 indexes must be provided to mark.";
    public static final String MESSAGE_ALREADY_MARKED = "This appointment is already marked.";

    private final Index indexOfPerson;
    private final Index indexOfAppointment;

    /**
     * @param indexOfPerson index of the person in the filtered person list to mark.
     * @param indexOfAppointment index of the appointment of the specified person to mark.
     */
    public MarkCommand(Index indexOfPerson, Index indexOfAppointment) {
        requireNonNull(indexOfPerson);
        requireNonNull(indexOfAppointment);

        this.indexOfPerson = indexOfPerson;
        this.indexOfAppointment = indexOfAppointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexOfPerson.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMarkFor = lastShownList.get(indexOfPerson.getZeroBased());
        List<Appointment> targetAppointmentList = personToMarkFor.getAppointments();

        if (indexOfAppointment.getZeroBased() >= targetAppointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToMark = targetAppointmentList.get(indexOfAppointment.getZeroBased());
        if (appointmentToMark.isMarked()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED);
        }

        appointmentToMark.mark();
        return new CommandResult(String.format(MESSAGE_MARK_PERSON_SUCCESS,
                indexOfAppointment.getOneBased(),
                personToMarkFor.getName()));
    }
}
