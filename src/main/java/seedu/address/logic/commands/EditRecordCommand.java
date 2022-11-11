package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditRecordCommand extends Command {

    public static final String COMMAND_WORD = "redit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the record identified "
            + "by the index number used in the displayed record list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "RECORD_DATE_TIME "
            + PREFIX_RECORD + "RECORD_DETAILS "
            + "[" + PREFIX_MEDICATION + "MEDICATION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "31-10-2022 1430 (must be formatted in dd-MM-yyyy HHmm) "
            + PREFIX_RECORD + "suffers from common cold "
            + PREFIX_MEDICATION + "Paracetamol 500mg "
            + PREFIX_MEDICATION + "Phenylephrine oral 10mg";

    public static final String MESSAGE_EDIT_RECORD_SUCCESS = "Edited record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECORD = "A record with this date & time"
            + " already exists in the address book.";

    private final Index index;
    private final EditRecordDescriptor editRecordDescriptor;

    /**
     * @param index                of the record in the filtered record list to edit
     * @param editRecordDescriptor details to edit the record with
     */
    public EditRecordCommand(Index index, EditRecordDescriptor editRecordDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecordDescriptor);

        this.index = index;
        this.editRecordDescriptor = new EditRecordDescriptor(editRecordDescriptor);
    }

    /**
     * Creates and returns a {@code Record} with the details of {@code recordToEdit}
     * edited with {@code editRecordDescriptor}.
     */
    private static Record createEditedRecord(Record recordToEdit, EditRecordDescriptor editRecordDescriptor) {
        assert recordToEdit != null;

        LocalDateTime updatedRecordDate = editRecordDescriptor.getRecordDate().orElse(recordToEdit.getRecordDate());
        String updatedRecord = editRecordDescriptor.getRecord().orElse(recordToEdit.getRecordData());
        Set<Medication> updatedMedications = editRecordDescriptor.getMedications()
                .orElse(recordToEdit.getMedications());

        return new Record(updatedRecordDate, updatedRecord, updatedMedications);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Record> lastShownList = model.getFilteredRecordList();

        Record recordToEdit = lastShownList.get(index.getZeroBased());
        Record editedRecord = createEditedRecord(recordToEdit, editRecordDescriptor);

        if (!model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_RECORD_COMMAND_PREREQUISITE);
        }

        if (!recordToEdit.isSameRecord(editedRecord) && model.hasRecord(editedRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        model.setRecord(recordToEdit, editedRecord);
        model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
        return new CommandResult(String.format(MESSAGE_EDIT_RECORD_SUCCESS, editedRecord), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRecordCommand)) {
            return false;
        }

        // state check
        EditRecordCommand e = (EditRecordCommand) other;
        return index.equals(e.index)
                && editRecordDescriptor.equals(e.editRecordDescriptor);
    }

    /**
     * Stores the details to edit the record with. Each non-empty field value will replace the
     * corresponding field value of the record.
     */
    public static class EditRecordDescriptor {
        private LocalDateTime recordDate;
        private String record;
        private Set<Medication> medications;

        public EditRecordDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code medications} is used internally.
         */
        public EditRecordDescriptor(EditRecordDescriptor toCopy) {
            setRecordDate(toCopy.recordDate);
            setRecord(toCopy.record);
            setMedications(toCopy.medications);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(recordDate, record, medications);
        }

        public Optional<LocalDateTime> getRecordDate() {
            return Optional.ofNullable(recordDate);
        }

        public void setRecordDate(LocalDateTime recordDate) {
            this.recordDate = recordDate;
        }

        public Optional<String> getRecord() {
            return Optional.ofNullable(record);
        }

        public void setRecord(String record) {
            this.record = record;
        }

        /**
         * Returns an unmodifiable medication set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code medications} is null.
         */
        public Optional<Set<Medication>> getMedications() {
            return (medications != null) ? Optional.of(Collections.unmodifiableSet(medications)) : Optional.empty();
        }

        /**
         * Sets {@code medications} to this object's {@code medications}.
         * A defensive copy of {@code medication} is used internally.
         */
        public void setMedications(Set<Medication> medications) {
            this.medications = (medications != null) ? new HashSet<>(medications) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecordDescriptor)) {
                return false;
            }

            // state check
            EditRecordDescriptor e = (EditRecordDescriptor) other;

            return getRecord().equals(e.getRecord())
                    && getRecordDate().equals(e.getRecordDate())
                    && getMedications().equals(e.getMedications());
        }
    }
}
