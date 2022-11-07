package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_EIGHTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
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
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

public class AssignCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_assignAllDateSlotSuccess() {
        Person patientPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person nursePerson = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        AssignCommand assignCommand = new AssignCommand(patientPerson.getUid(),
                nursePerson.getUid(), new ArrayList<>());

        Patient patient = (Patient) patientPerson;
        List<DateSlot> originalDateSlotList = patient.getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotList.get(0).getDateSlotInString(), true,
                false, false, nursePerson.getUid().getUid());
        updatedDateSlotList.add(dateSlotOne);
        DateSlot dateSlotTwo = new DateSlot(originalDateSlotList.get(1).getDateSlotInString(), true,
                false, false, nursePerson.getUid().getUid());
        updatedDateSlotList.add(dateSlotTwo);
        Patient editedPatient = new Patient(patient.getUid(), patient.getName(),
                patient.getGender(), patient.getPhone(), patient.getEmail(),
                patient.getAddress(), patient.getTags(), updatedDateSlotList);

        Nurse nurse = (Nurse) nursePerson;
        List<HomeVisit> originalHomeVisitList = nurse.getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(originalHomeVisitList);
        updatedHomeVisitList.add(new HomeVisit(dateSlotOne, patient.getUid().getUid()));
        updatedHomeVisitList.add(new HomeVisit(dateSlotTwo, patient.getUid().getUid()));
        Nurse editedNurse = new Nurse(nurse.getUid(), nurse.getName(), nurse.getGender(), nurse.getPhone(),
                nurse.getEmail(), nurse.getAddress(), nurse.getTags(), nurse.getUnavailableDates(),
                updatedHomeVisitList, nurse.getFullyScheduledDates());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPatient);
        expectedModel.setPerson(model.getFilteredPersonList().get(6), editedNurse);
        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS,
                editedPatient.getUid().getUid(), editedNurse.getUid().getUid());

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignSpecificDateSlotSuccess() {
        Person patientPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person nursePerson = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        List<Index> dateSlotIndexList = new ArrayList<>();
        dateSlotIndexList.add(Index.fromOneBased(1));
        AssignCommand assignCommand = new AssignCommand(patientPerson.getUid(),
                nursePerson.getUid(), dateSlotIndexList);

        Patient patient = (Patient) patientPerson;
        List<DateSlot> originalDateSlotList = patient.getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotList.get(0).getDateSlotInString(), true,
                false, false, nursePerson.getUid().getUid());
        updatedDateSlotList.add(dateSlotOne);
        updatedDateSlotList.add(originalDateSlotList.get(1));
        Patient editedPatient = new Patient(patient.getUid(), patient.getName(),
                patient.getGender(), patient.getPhone(), patient.getEmail(),
                patient.getAddress(), patient.getTags(), updatedDateSlotList);

        Nurse nurse = (Nurse) nursePerson;
        List<HomeVisit> originalHomeVisitList = nurse.getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(originalHomeVisitList);
        updatedHomeVisitList.add(new HomeVisit(dateSlotOne, patient.getUid().getUid()));
        Nurse editedNurse = new Nurse(nurse.getUid(), nurse.getName(), nurse.getGender(), nurse.getPhone(),
                nurse.getEmail(), nurse.getAddress(), nurse.getTags(), nurse.getUnavailableDates(),
                updatedHomeVisitList, nurse.getFullyScheduledDates());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPatient);
        expectedModel.setPerson(model.getFilteredPersonList().get(6), editedNurse);
        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS,
                editedPatient.getUid().getUid(), editedNurse.getUid().getUid());

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignDateSlotFail() {
        // Both Patient
        Person personOne = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personTwo = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        AssignCommand assignCommand = new AssignCommand(personTwo.getUid(), personOne.getUid(), new ArrayList<>());

        assertCommandFailure(assignCommand, model, AssignCommand.MESSAGE_BOTH_PATIENT);

        // Both Nurse
        personOne = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        personTwo = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        assignCommand = new AssignCommand(personTwo.getUid(), personOne.getUid(), new ArrayList<>());

        assertCommandFailure(assignCommand, model, AssignCommand.MESSAGE_BOTH_NURSE);

        //DateSlot has passed
        personOne = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        personTwo = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        assignCommand = new AssignCommand(personTwo.getUid(), personOne.getUid(), new ArrayList<>());

        List<DateSlot> dateSlotList = ((Patient) personOne).getDatesSlots();
        String expectedMessage = String.format(DateSlotChecker.MESSAGE_VISITED_DATESLOT,
                dateSlotList.get(0).getDateSlotFormatted());
        assertCommandFailure(assignCommand, model, expectedMessage);

        //DateSlot Time Clash
        personOne = model.getFilteredPersonList().get(INDEX_FOURTH_PERSON.getZeroBased());
        personTwo = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        List<Index> indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(2));
        assignCommand = new AssignCommand(personOne.getUid(), personTwo.getUid(), indexList);

        dateSlotList = ((Patient) personOne).getDatesSlots();
        expectedMessage = String.format(DateSlotChecker.MESSAGE_TIME_CRASHES,
                dateSlotList.get(1).getDateSlotFormatted());
        assertCommandFailure(assignCommand, model, expectedMessage);

        //DateSlot Clash with Unavailable Date
        personOne = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        personTwo = model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(2));
        assignCommand = new AssignCommand(personOne.getUid(), personTwo.getUid(), indexList);

        dateSlotList = ((Patient) personOne).getDatesSlots();
        expectedMessage = String.format(DateSlotChecker.MESSAGE_UNAVAILABLE_DATE,
                dateSlotList.get(1).getDateSlotFormatted());
        assertCommandFailure(assignCommand, model, expectedMessage);

        //DateSlot has been assigned
        personOne = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        personTwo = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(1));
        assignCommand = new AssignCommand(personOne.getUid(), personTwo.getUid(), indexList);

        dateSlotList = ((Patient) personOne).getDatesSlots();
        expectedMessage = String.format(DateSlotChecker.MESSAGE_ASSIGNED_DATESLOT,
                dateSlotList.get(0).getDateSlotFormatted());
        assertCommandFailure(assignCommand, model, expectedMessage);

        //Index Out Of Bound
        personTwo = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        personOne = model.getFilteredPersonList().get(INDEX_SEVENTH_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(10));
        assignCommand = new AssignCommand(personOne.getUid(), personTwo.getUid(), indexList);

        assertCommandFailure(assignCommand, model, DateSlotManager.MESSAGE_OUTOFBOUND_DATESLOT_INDEX);
    }
}

