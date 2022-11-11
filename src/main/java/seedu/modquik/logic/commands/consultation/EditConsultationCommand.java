package seedu.modquik.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.core.Messages.MESSAGE_UNCHANGED_FIELD;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.modquik.model.ModelType.CONSULTATION;

import java.util.List;
import java.util.Optional;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.commons.util.CollectionUtil;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.consultation.ConsultationDescription;
import seedu.modquik.model.consultation.ConsultationName;
import seedu.modquik.model.datetime.DatetimeRange;

/**
 * Edits the details of an existing consultation in ModQuik.
 */
public class EditConsultationCommand extends Command {
    public static final String COMMAND_WORD = "edit consultation";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the consultation identified "
            + "by the index number used in the displayed consult list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_VENUE + "VENUE] "
            + "[" + PREFIX_DATE_DAY + "DAY and "
            + PREFIX_TIME + "TIMESLOT] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_DESCRIPTION + "Review paper AY2020-2021 Question 3,5,16";

    public static final String MESSAGE_EDIT_CONSULTATION_SUCCESS = "Edited Consult: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "There is already a consultation at that timing.";
    public static final String MESSAGE_DATETIME_CONSULTATION = "Both new day and new timeslot must be inputted.";
    public static final String MESSAGE_CLASH_CONSULTATION =
            "There exists a consultation with overlapping timeslot in the ModQuik";

    private final Index index;
    private final EditConsultDescriptor editConsultDescriptor;

    /**
     * @param index of the consult in the filtered consult list to edit
     * @param editConsultDescriptor details to edit the consult with
     */
    public EditConsultationCommand(Index index, EditConsultDescriptor editConsultDescriptor) {
        requireNonNull(index);
        requireNonNull(editConsultDescriptor);

        this.index = index;
        this.editConsultDescriptor = new EditConsultDescriptor(editConsultDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> lastShownList = model.getFilteredConsultationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
        }

        Consultation consultToEdit = lastShownList.get(index.getZeroBased());
        Consultation editedConsult = createEditedConsult(consultToEdit, editConsultDescriptor);

        if (!consultToEdit.isSameConsultation(editedConsult) && model.hasConsultation(editedConsult)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        if (model.hasClashingConsultationExcept(editedConsult, consultToEdit)) {
            throw new CommandException(MESSAGE_CLASH_CONSULTATION);
        }

        model.setConsultation(consultToEdit, editedConsult);
        model.updateFilteredConsultationList(Model.PREDICATE_SHOW_ALL_CONSULTATIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_CONSULTATION_SUCCESS, editedConsult), CONSULTATION);
    }

    /**
     * Creates and returns a {@code Consult} with the details of {@code consultToEdit}
     * edited with {@code editConsultDescriptor}.
     */
    private static Consultation createEditedConsult(Consultation consultToEdit,
                                                    EditConsultDescriptor editConsultDescriptor)
            throws CommandException {
        assert consultToEdit != null;

        ConsultationName updatedName = editConsultDescriptor.getName().orElse(consultToEdit.getName());
        ModuleCode updatedModule = editConsultDescriptor.getModule().orElse(consultToEdit.getModule());
        Venue updatedVenue = editConsultDescriptor.getVenue().orElse(consultToEdit.getVenue());
        DatetimeRange updatedTimeSlot = editConsultDescriptor.getTimeslot().orElse(consultToEdit.getTimeslot());
        ConsultationDescription updatedDescription = editConsultDescriptor.getDescription()
                .orElse(consultToEdit.getDescription());

        Consultation editedConsultation = new Consultation(updatedName, updatedModule,
                updatedVenue, updatedTimeSlot, updatedDescription);

        if (editedConsultation.equals(consultToEdit)) {
            throw new CommandException(MESSAGE_UNCHANGED_FIELD);
        }
        return editedConsultation;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditConsultationCommand)) {
            return false;
        }

        // state check
        EditConsultationCommand e = (EditConsultationCommand) other;
        return index.equals(e.index)
                && editConsultDescriptor.equals(e.editConsultDescriptor);
    }

    /**
     * Stores the details to edit the consult with. Each non-empty field value will replace the
     * corresponding field value of the consult.
     */
    public static class EditConsultDescriptor {
        private ConsultationName name;
        private ModuleCode module;
        private Venue venue;
        private DatetimeRange timeslot;
        private ConsultationDescription description;


        public EditConsultDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditConsultDescriptor(EditConsultDescriptor toCopy) {
            setName(toCopy.name);
            setModule(toCopy.module);
            setVenue(toCopy.venue);
            setTimeSlot(toCopy.timeslot);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, module, venue, timeslot, description);
        }

        public void setName(ConsultationName name) {
            this.name = name;
        }

        public Optional<ConsultationName> getName() {
            return Optional.ofNullable(name);
        }

        public void setModule(ModuleCode module) {
            this.module = module;
        }

        public Optional<ModuleCode> getModule() {
            return Optional.ofNullable(module);
        }

        public void setVenue(Venue venue) {
            this.venue = venue;
        }

        public Optional<Venue> getVenue() {
            return Optional.ofNullable(venue);
        }

        public void setTimeSlot(DatetimeRange timeslot) {
            this.timeslot = timeslot;
        }

        public Optional<DatetimeRange> getTimeslot() {
            return Optional.ofNullable(timeslot);
        }

        public void setDescription(ConsultationDescription description) {
            this.description = description;
        }

        public Optional<ConsultationDescription> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditConsultDescriptor)) {
                return false;
            }

            // state check
            EditConsultDescriptor e = (EditConsultDescriptor) other;

            return getName().equals(e.getName())
                    && getModule().equals(e.getModule())
                    && getVenue().equals(e.getVenue())
                    && getTimeslot().equals(e.getTimeslot())
                    && getDescription().equals(e.getDescription());
        }
    }
}
