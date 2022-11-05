package seedu.address.model;

import static seedu.address.logic.commands.BookCommand.MESSAGE_DUPLICATE_APPOINTMENT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.HiddenPredicateSingleton;
import seedu.address.model.tag.Tag;

/**
 * Facilitator for operations involving {@code appointments}.
 */
public class AppointmentList {
    /**
     * Adds the given {@code Appointment} to the address book and the associated patient.
     *
     * @param model {@code Model} which {@code BookCommand} is operating on.
     * @param patient The patient that is involved in the appointment.
     * @param appointment {@code Appointment} to be added.
     * @throws CommandException If there are duplicate appointments.
     */
    public static void addAppointment(Model model, Person patient, Appointment appointment) throws CommandException {
        List<Appointment> appointmentList = patient.getAppointments();
        if (hasSameAppointment(appointmentList, appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }
        appointmentList.add(appointment);
        appointment.setPatient(patient);

        model.addAppointment(appointment);
        model.updateFilteredPersonList(HiddenPredicateSingleton
                .getInstance().getCurrPersonPredicate());
        model.updateFilteredAppointmentList(HiddenPredicateSingleton
                .getInstance().getCurrApptPredicate());
    }

    /**
     * Removes the given {@code Appointment} from the address book and the associated patient.
     *
     * @param model {@code Model} which {@code CancelCommand} is operating on.
     * @param patient The patient that is involved in the appointment.
     * @param appointment {@code Appointment} to be removed.
     */
    public static void cancelAppointment(Model model, Person patient, Appointment appointment) {
        List<Appointment> appointmentList = patient.getAppointments();
        appointmentList.remove(appointment);
        model.deleteAppointment(appointment);

        model.updateFilteredPersonList(HiddenPredicateSingleton
                .getInstance().getCurrPersonPredicate());
        model.updateFilteredAppointmentList(HiddenPredicateSingleton
                .getInstance().getCurrApptPredicate());
    }

    /**
     * Edits the given {@code Appointment} and returns it.
     *
     * @param model {@code Model} which {@code EditCommand} is operating on.
     * @param patient The patient that is involved in the {@code Appointment}
     * @param appointment {@code Appointment} that is about to be edited.
     * @param descriptor Field(s) that are edited.
     * @return The edited {@code Appointment}.
     * @throws CommandException If there are duplicate appointments.
     */
    public static Appointment editAppointment(Model model, Person patient, Appointment appointment,
            EditAppointmentCommand.EditAppointmentDescriptor descriptor) throws CommandException {

        List<Appointment> appointmentList = patient.getAppointments();
        int index = appointmentList.indexOf(appointment);
        Appointment editedAppointment = createEditedAppointment(appointment, descriptor);

        if (hasSameTime(appointmentList, appointment, editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointment, editedAppointment);
        appointmentList.set(index, editedAppointment);
        return editedAppointment;
    }

    private static boolean hasSameAppointment(List<Appointment> appointmentList, Appointment appointment) {
        return appointmentList.stream().anyMatch(x -> x.isSameTime(appointment));
    }

    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
            EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        String reason = editAppointmentDescriptor.getReason().orElse(appointmentToEdit.getReason());
        LocalDateTime dateTime = editAppointmentDescriptor.getDateTime().orElse(appointmentToEdit.getDateTime());
        List<Integer> period = editAppointmentDescriptor.getTimePeriod().orElse(appointmentToEdit.getTimePeriod());
        Set<Tag> tags = editAppointmentDescriptor.getTags().orElse(appointmentToEdit.getTags());
        Appointment editedAppointment = new Appointment(reason, dateTime, period, tags,
                appointmentToEdit.isMarked());
        editedAppointment.setPatient(appointmentToEdit.getPatient());
        return editedAppointment;
    }

    private static boolean hasSameTime(List<Appointment> appointments, Appointment originalAppointment,
                                Appointment appointmentToCheck) {
        List<Appointment> appointmentsToCheck = new ArrayList<>(appointments);
        appointmentsToCheck.remove(originalAppointment);
        return appointmentsToCheck.stream().anyMatch(x -> x.isSameTime(appointmentToCheck));
    }

}
