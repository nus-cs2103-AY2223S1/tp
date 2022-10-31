package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;

/**
 * Undo unmarks a patient's dateslot using their unique id and dateslot index
 * when unmark fail to visit wrongly.
 */
public class UndoUnmarkCommand extends Command {

    public static final String COMMAND_WORD = "undounmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undo Unmarks the patient's dateslot identified by the unique id number in the displayed person list "
            + "and dateslot index as visited.\n"
            + "Parameters: UID (must be a positive integer) " + PREFIX_DATE_AND_SLOT_INDEX + "Date_Slot_Index"
            + "Example: " + COMMAND_WORD + " " + PREFIX_UID + " 1" + PREFIX_DATE_AND_SLOT_INDEX + "2";

    public static final String MESSAGE_UNDO_UNMARK_PATIENT_SUCCESS = "Undo Unmarked Patient as success visit: %1$s";
    public static final String MESSAGE_INVALID_NURSE_UID = "This uid gives a nurse." + " Please recheck the uid. "
            + "Undo Unmark is only for patient.";
    public static final String MESSAGE_OUT_OF_BOUND_DATE_AND_SLOT_INDEX = "The given date slot index is out of bounds."
            + "Please recheck the index.";
    public static final String MESSAGE_INVALID_DATE_AND_SLOT_INDEX = "The visit dates has not reached."
            + "Cannot undo unmark it as success visit.";
    public static final String MESSAGE_INVALID_DATE_AND_SLOT_INDEX_TWO = "This dates has already been marked as "
            + "success visited. " + "Cannot undo unmark it as success visit.";

    private final Uid uid;
    private final Index dateSlotIndex;

    /**
     * Initialises the UndoUnmarkCommand with a valid and non-null {@code Uid} of
     * the target patient and
     * a valid and non-null {@code Index} of the target DateSlot index.
     */
    public UndoUnmarkCommand(Uid uid, Index dateSlotIndex) {
        requireNonNull(uid);
        requireNonNull(dateSlotIndex);
        this.uid = uid;
        this.dateSlotIndex = dateSlotIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> targetPerson = lastShownList.stream().filter(p -> p.getUid().equals(uid)).findFirst();

        if (targetPerson.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }

        Person personToUndoUnmark = targetPerson.get();

        if (personToUndoUnmark instanceof Nurse) {
            throw new CommandException(MESSAGE_INVALID_NURSE_UID);
        }

        undounmarkAction(personToUndoUnmark, model);

        return new CommandResult(String.format(MESSAGE_UNDO_UNMARK_PATIENT_SUCCESS, personToUndoUnmark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoUnmarkCommand// instanceof handles nulls
                        && this.uid.equals(((UndoUnmarkCommand) other).uid) // state check
                        && this.dateSlotIndex.equals(((UndoUnmarkCommand) other).dateSlotIndex));
    }

    private void undounmarkAction(Person personToUndoUnmark, Model model) throws CommandException {

        List<DateSlot> dateSlotList = ((Patient) personToUndoUnmark).getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>(dateSlotList);

        if (dateSlotIndex.getZeroBased() >= dateSlotList.size()) {
            throw new CommandException(MESSAGE_OUT_OF_BOUND_DATE_AND_SLOT_INDEX);
        }

        DateSlot dateToBeUndoUnmark = updatedDateSlotList.get(dateSlotIndex.getZeroBased());

        if (!dateToBeUndoUnmark.getHasVisited()) {
            throw new CommandException(MESSAGE_INVALID_DATE_AND_SLOT_INDEX);
        }

        if (dateToBeUndoUnmark.getIsSuccessVisit()) {
            throw new CommandException(MESSAGE_INVALID_DATE_AND_SLOT_INDEX_TWO);
        }

        dateToBeUndoUnmark.markSuccess();
        editPatient(model, personToUndoUnmark, updatedDateSlotList);
    }

    private void editPatient(Model model, Person patient, List<DateSlot> dateSlotList) {
        Uid uid = patient.getUid();
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> personToEdit = lastShownList.stream().filter(p -> p.getUid().equals(uid)).findFirst();
        Person confirmedPersonToEdit = personToEdit.get();
        Person newPerson = new Patient(confirmedPersonToEdit.getUid(), confirmedPersonToEdit.getName(),
                confirmedPersonToEdit.getGender(), confirmedPersonToEdit.getPhone(), confirmedPersonToEdit.getEmail(),
                confirmedPersonToEdit.getAddress(), confirmedPersonToEdit.getTags(), dateSlotList);
        model.setPerson(confirmedPersonToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
