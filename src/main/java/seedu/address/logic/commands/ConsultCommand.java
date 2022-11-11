package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CONSULT_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.appointment.UpcomingAppointment;
import seedu.address.model.person.Person;

/**
 * Creates a past appointment for today for a patient, if the patient's upcoming appointment is on the same day,
 * consume it and set it to empty.
 */
public class ConsultCommand extends Command {
    public static final String COMMAND_WORD = "consult";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Consults a Patient and consumes the upcoming "
            + "appointment if it is on the same day\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_MEDICATION + "MEDICATION "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEDICATION + "ibuprofen "
            + PREFIX_DIAGNOSIS + "headache, medicine given for 3 days ";

    private final Index index;
    private final PastAppointment appt;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates a command to add a past appointment to a patient's record and update the upcoming appointment if needed.
     * @param index of the patient in the filtered patient list to edit
     */
    public ConsultCommand(Index index, PastAppointment appt) {
        requireNonNull(index);
        requireNonNull(appt);
        this.index = index;
        this.appt = appt;
        this.editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setUpcomingAppointment(new UpcomingAppointment(""));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        new CreatePastAppointmentCommand(index, appt).execute(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person person = lastShownList.get(index.getZeroBased());

        if (isPresentUpcomingAppointment(person) && person.getUpcomingAppointment().get().value.equals(LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-uuuu")))) {
            new EditCommand(index, editPersonDescriptor).execute(model);
        }

        return new CommandResult(String.format(MESSAGE_CONSULT_SUCCESS, person.getName()));
    }

    private boolean isPresentUpcomingAppointment(Person person) {
        return person.getUpcomingAppointment().isPresent()
                && !person.getUpcomingAppointment().get().toString().equals("Upcoming Appointment Date: None");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        if (other instanceof ConsultCommand) {
            ConsultCommand otherConsult = (ConsultCommand) other;
            return index.equals(otherConsult.index) && appt.equals(otherConsult.appt);
        }
        return false;
    }

}
