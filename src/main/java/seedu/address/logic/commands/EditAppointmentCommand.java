package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING_PERIOD;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AppointmentList;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Edits a given appointment's details.
 */
public class EditAppointmentCommand extends SelectAppointmentCommand {

    public static final String COMMAND_WORD = "edit";
    public static final String DESCRIPTOR_WORD = "appts";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment for %1$s: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + DESCRIPTOR_WORD
            + ": Edits the appointment details of the person identified "
            + "by the appointmentIndex number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: APPOINTMENT_INDEX (must be a positive integer) "
            + "[" + PREFIX_REASON + "REASON] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_RECURRING_PERIOD + "TIME PERIOD] "
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " 1 "
            + PREFIX_REASON + "Sore Throat "
            + PREFIX_DATE + "2022-10-12 16:30 "
            + PREFIX_RECURRING_PERIOD + "1Y0M0D";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "This person already booked an appointment at this time";
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * Creates an EditAppointmentCommand with the given appointment index,
     * and the editAppointmentDescriptor.
     *
     * @param indexOfAppointment The index of the appointment to edit.
     * @param editAppointmentDescriptor The descriptor that contains the edited details.
     */
    public EditAppointmentCommand(Index indexOfAppointment,
                                  EditAppointmentDescriptor editAppointmentDescriptor) {
        super(indexOfAppointment);
        this.editAppointmentDescriptor = editAppointmentDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Appointment targetAppointment = getTargetAppointment(model);
        Person person = getTargetPerson(model);
        Appointment editedAppointment =
                AppointmentList.editAppointment(model, person, targetAppointment, editAppointmentDescriptor);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), editedAppointment));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return hasSameIndexOfAppointment(e) && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private String reason;
        private LocalDateTime dateTime;
        private List<Integer> timePeriod;
        private Set<Tag> tags;
        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param toCopy The editAppointmentDescriptor to copy the details.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setReason(toCopy.reason);
            setDateTime(toCopy.dateTime);
            setTimePeriod(toCopy.timePeriod);
            setTags(toCopy.tags);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(reason, dateTime, timePeriod, tags);
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Optional<String> getReason() {
            return Optional.ofNullable(reason);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setTimePeriod(List<Integer> timePeriod) {
            this.timePeriod = timePeriod;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public Optional<List<Integer>> getTimePeriod() {
            return Optional.ofNullable(timePeriod);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;
            return getReason().equals(e.getReason()) && getDateTime().equals(e.getDateTime())
                    && getTimePeriod().equals(e.getTimePeriod());
        }
    }
}
