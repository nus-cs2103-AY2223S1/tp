package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Books an appointment for the given patient.
 */
public class BookCommand extends Command {

    public static final String COMMAND_WORD = "book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Books an appointment for the patient. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_REASON + "REASON "
            + PREFIX_DATE + "DATE "
            + "Example: " + COMMAND_WORD + " 3"
            + PREFIX_REASON + "sore throat "
            + PREFIX_DATE + "2022-10-12 16:30";

    public static final String MESSAGE_BOOK_APPOINTMENT_SUCCESS = "Booked Appointment for Person: %1$s";

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This person already booked an appointment at this time";
    private final Appointment appointment;
    private final Index targetIndex;

    /**
     * Creates a BookCommand with the given index and appointment.
     *
     * @param targetIndex The given index to retrieve the patient.
     * @param appointment The given appointment to book for the patient.
     */
    public BookCommand(Index targetIndex, Appointment appointment) {
        requireNonNull(appointment);
        this.targetIndex = targetIndex;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToBookFor = lastShownList.get(targetIndex.getZeroBased());
        PersonBookDescriptor personBookDescriptor = new PersonBookDescriptor(personToBookFor);
        personBookDescriptor.bookAppointment(appointment);
        Person personWithBooking = personBookDescriptor.createPersonWithBooking();
        appointment.setPatient(personWithBooking);

        model.setPerson(personToBookFor, personWithBooking);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.addAppointment(appointment);
        return new CommandResult(String.format(MESSAGE_BOOK_APPOINTMENT_SUCCESS, personWithBooking));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCommand // instanceof handles nulls
                && targetIndex.equals(((BookCommand) other).targetIndex)
                && appointment.equals(((BookCommand) other).appointment)); // state check
    }

    private static class PersonBookDescriptor {
        private final Name name;
        private final Phone phone;
        private final Email email;
        private final Address address;
        private final List<Appointment> appointments;
        private final Set<Tag> tags;

        private PersonBookDescriptor(Person toCopy) {
            name = toCopy.getName();
            phone = toCopy.getPhone();
            email = toCopy.getEmail();
            address = toCopy.getAddress();
            appointments = new ArrayList<>(toCopy.getAppointments());
            tags = toCopy.getTags();
        }

        private boolean hasSameAppointment(Appointment appointment) {
            return appointments.stream().anyMatch(x -> x.isSameTime(appointment));
        }

        private void bookAppointment(Appointment appointment) throws CommandException {
            if (hasSameAppointment(appointment)) {
                throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointments.add(appointment);
            appointments.sort(Comparator.comparing(Appointment::getDateTime));
        }

        private Person createPersonWithBooking() {
            return new Person(name, phone, email, address, appointments, tags);
        }
    }
}
