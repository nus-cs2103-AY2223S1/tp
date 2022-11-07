package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

public class UnmarkCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unmarkSuccess() {
        Person patientPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Index index = Index.fromOneBased(1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(patientPerson.getUid(), index);

        Patient patient = (Patient) patientPerson;
        List<DateSlot> originalDateSlotList = patient.getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotList.get(0).getDateSlotInString(), false,
                true, false, (long) -1);
        updatedDateSlotList.add(dateSlotOne);
        Patient editedPatient = new Patient(patient.getUid(), patient.getName(),
                patient.getGender(), patient.getPhone(), patient.getEmail(),
                patient.getAddress(), patient.getTags(), updatedDateSlotList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPatient);
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_PATIENT_SUCCESS,
                editedPatient);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unmarkFail() {
        //DateSlot has not passed
        Person personOne = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index index = Index.fromOneBased(1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(personOne.getUid(), index);

        List<DateSlot> dateSlotList = ((Patient) personOne).getDatesSlots();
        String expectedMessage = String.format(DateSlotChecker.MESSAGE_NOT_VISITED_DATESLOT,
                dateSlotList.get(0).getDateSlotFormatted());
        assertCommandFailure(unmarkCommand, model, expectedMessage);

        //Index Out Of Bound
        personOne = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        index = Index.fromOneBased(10);
        unmarkCommand = new UnmarkCommand(personOne.getUid(), index);

        assertCommandFailure(unmarkCommand, model, DateSlotManager.MESSAGE_OUTOFBOUND_DATESLOT_INDEX);

        //Uid given is nurse
        personOne = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        index = Index.fromOneBased(1);
        unmarkCommand = new UnmarkCommand(personOne.getUid(), index);

        assertCommandFailure(unmarkCommand, model, UnmarkCommand.MESSAGE_INVALID_NURSE_UID);
    }
}



