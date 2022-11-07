package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_EIGHTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
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

public class DeassignCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deassignAllPatientDateSlotSuccess() {
        Person patientPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        DeassignCommand deassignCommand = new DeassignCommand(patientPerson.getUid(), new ArrayList<>());

        Patient patient = (Patient) patientPerson;
        List<DateSlot> originalDateSlotList = patient.getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotList.get(0).getDateSlotInString(), false,
                false, false, (long) -1);
        updatedDateSlotList.add(dateSlotOne);
        DateSlot dateSlotTwo = new DateSlot(originalDateSlotList.get(1).getDateSlotInString(), false,
                false, false, (long) -1);
        updatedDateSlotList.add(dateSlotTwo);
        Patient editedPatient = new Patient(patient.getUid(), patient.getName(),
                patient.getGender(), patient.getPhone(), patient.getEmail(),
                patient.getAddress(), patient.getTags(), updatedDateSlotList);

        Person nursePersonOne = model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased());
        Nurse nurseOne = (Nurse) nursePersonOne;
        List<HomeVisit> originalHomeVisitList = nurseOne.getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(originalHomeVisitList);
        updatedHomeVisitList.remove(1);
        Nurse editedNurseOne = new Nurse(nurseOne.getUid(), nurseOne.getName(), nurseOne.getGender(),
                nurseOne.getPhone(), nurseOne.getEmail(), nurseOne.getAddress(), nurseOne.getTags(),
                nurseOne.getUnavailableDates(), updatedHomeVisitList, nurseOne.getFullyScheduledDates());

        Person nursePersonTwo = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        Nurse nurseTwo = (Nurse) nursePersonTwo;
        List<HomeVisit> originalHomeVisitListTwo = nurseTwo.getHomeVisits();
        List<HomeVisit> updatedHomeVisitListTwo = new ArrayList<>(originalHomeVisitListTwo);
        updatedHomeVisitListTwo.remove(0);
        Nurse editedNurseTwo = new Nurse(nurseTwo.getUid(), nurseTwo.getName(), nurseTwo.getGender(),
                nurseTwo.getPhone(), nurseTwo.getEmail(), nurseTwo.getAddress(), nurseTwo.getTags(),
                nurseTwo.getUnavailableDates(), updatedHomeVisitListTwo, nurseTwo.getFullyScheduledDates());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(2), editedPatient);
        expectedModel.setPerson(model.getFilteredPersonList().get(4), editedNurseOne);
        expectedModel.setPerson(model.getFilteredPersonList().get(7), editedNurseTwo);
        String expectedMessage = String.format(DeassignCommand.MESSAGE_SUCCESS,
                editedPatient.getUid().getUid());

        assertCommandSuccess(deassignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deassignAllNurseHomeVisitSuccess() {
        Person nursePerson = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        DeassignCommand deassignCommand = new DeassignCommand(nursePerson.getUid(), new ArrayList<>());

        Nurse nurse = (Nurse) nursePerson;
        List<HomeVisit> originalHomeVisitList = nurse.getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>();
        Nurse editedNurse = new Nurse(nurse.getUid(), nurse.getName(),
                nurse.getGender(), nurse.getPhone(), nurse.getEmail(),
                nurse.getAddress(), nurse.getTags(), nurse.getUnavailableDates(), updatedHomeVisitList,
                nurse.getFullyScheduledDates());

        Person patientPersonOne = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient patientOne = (Patient) patientPersonOne;
        List<DateSlot> originalDateSlotListOne = patientOne.getDatesSlots();
        List<DateSlot> updatedDateSlotListOne = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotListOne.get(0).getDateSlotInString(), false,
                false, false, (long) -1);
        updatedDateSlotListOne.add(dateSlotOne);
        DateSlot dateSlotTwo = originalDateSlotListOne.get(1);
        updatedDateSlotListOne.add(dateSlotTwo);
        Patient editedPatient = new Patient(patientOne.getUid(), patientOne.getName(),
                patientOne.getGender(), patientOne.getPhone(), patientOne.getEmail(),
                patientOne.getAddress(), patientOne.getTags(), updatedDateSlotListOne);

        Person patientPersonTwo = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Patient patientTwo = (Patient) patientPersonTwo;
        List<DateSlot> originalDateSlotListTwo = patientTwo.getDatesSlots();
        List<DateSlot> updatedDateSlotListTwo = new ArrayList<>();
        DateSlot dateSlotThree = new DateSlot(originalDateSlotListTwo.get(1).getDateSlotInString(), false,
                false, false, (long) -1);
        updatedDateSlotListTwo.add(dateSlotThree);
        DateSlot dateSlotFour = originalDateSlotListTwo.get(0);
        updatedDateSlotListTwo.add(dateSlotFour);
        Patient editedPatientTwo = new Patient(patientTwo.getUid(), patientTwo.getName(),
                patientTwo.getGender(), patientTwo.getPhone(), patientTwo.getEmail(),
                patientTwo.getAddress(), patientTwo.getTags(), updatedDateSlotListTwo);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(7), editedNurse);
        expectedModel.setPerson(model.getFilteredPersonList().get(2), editedPatient);
        expectedModel.setPerson(model.getFilteredPersonList().get(5), editedPatientTwo);
        String expectedMessage = String.format(DeassignCommand.MESSAGE_SUCCESS,
                editedNurse.getUid().getUid());

        assertCommandSuccess(deassignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deassignSpecificPatientDateSlotSuccess() {
        Person patientPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        List<Index> indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(1));
        DeassignCommand deassignCommand = new DeassignCommand(patientPerson.getUid(), indexList);

        Patient patient = (Patient) patientPerson;
        List<DateSlot> originalDateSlotList = patient.getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotList.get(0).getDateSlotInString(), false,
                false, false, (long) -1);
        updatedDateSlotList.add(dateSlotOne);
        DateSlot dateSlotTwo = originalDateSlotList.get(1);
        updatedDateSlotList.add(dateSlotTwo);
        Patient editedPatient = new Patient(patient.getUid(), patient.getName(),
                patient.getGender(), patient.getPhone(), patient.getEmail(),
                patient.getAddress(), patient.getTags(), updatedDateSlotList);

        Person nursePersonTwo = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        Nurse nurseTwo = (Nurse) nursePersonTwo;
        List<HomeVisit> originalHomeVisitListTwo = nurseTwo.getHomeVisits();
        List<HomeVisit> updatedHomeVisitListTwo = new ArrayList<>(originalHomeVisitListTwo);
        updatedHomeVisitListTwo.remove(0);
        Nurse editedNurseTwo = new Nurse(nurseTwo.getUid(), nurseTwo.getName(), nurseTwo.getGender(),
                nurseTwo.getPhone(), nurseTwo.getEmail(), nurseTwo.getAddress(), nurseTwo.getTags(),
                nurseTwo.getUnavailableDates(), updatedHomeVisitListTwo, nurseTwo.getFullyScheduledDates());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(2), editedPatient);
        expectedModel.setPerson(model.getFilteredPersonList().get(7), editedNurseTwo);
        String expectedMessage = String.format(DeassignCommand.MESSAGE_SUCCESS,
                editedPatient.getUid().getUid());

        assertCommandSuccess(deassignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deassignSpecificNurseHomeVisitSuccess() {
        Person nursePerson = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        List<Index> indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(1));
        DeassignCommand deassignCommand = new DeassignCommand(nursePerson.getUid(), indexList);

        Nurse nurse = (Nurse) nursePerson;
        List<HomeVisit> originalHomeVisitList = nurse.getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>();
        updatedHomeVisitList.add(originalHomeVisitList.get(1));
        Nurse editedNurse = new Nurse(nurse.getUid(), nurse.getName(),
                nurse.getGender(), nurse.getPhone(), nurse.getEmail(),
                nurse.getAddress(), nurse.getTags(), nurse.getUnavailableDates(), updatedHomeVisitList,
                nurse.getFullyScheduledDates());

        Person patientPersonOne = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Patient patientOne = (Patient) patientPersonOne;
        List<DateSlot> originalDateSlotListOne = patientOne.getDatesSlots();
        List<DateSlot> updatedDateSlotListOne = new ArrayList<>();
        DateSlot dateSlotOne = new DateSlot(originalDateSlotListOne.get(0).getDateSlotInString(), false,
                false, false, (long) -1);
        updatedDateSlotListOne.add(dateSlotOne);
        DateSlot dateSlotTwo = originalDateSlotListOne.get(1);
        updatedDateSlotListOne.add(dateSlotTwo);
        Patient editedPatient = new Patient(patientOne.getUid(), patientOne.getName(),
                patientOne.getGender(), patientOne.getPhone(), patientOne.getEmail(),
                patientOne.getAddress(), patientOne.getTags(), updatedDateSlotListOne);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(7), editedNurse);
        expectedModel.setPerson(model.getFilteredPersonList().get(2), editedPatient);
        String expectedMessage = String.format(DeassignCommand.MESSAGE_SUCCESS,
                editedNurse.getUid().getUid());

        assertCommandSuccess(deassignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deassignFail() {
        //DateSlot has passed
        Person personOne = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        List<Index> indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(1));
        DeassignCommand deassignCommand = new DeassignCommand(personOne.getUid(), indexList);

        List<DateSlot> dateSlotList = ((Patient) personOne).getDatesSlots();
        String expectedMessage = String.format(DateSlotChecker.MESSAGE_VISITED_DATESLOT,
                dateSlotList.get(0).getDateSlotFormatted());
        assertCommandFailure(deassignCommand, model, expectedMessage);

        //DateSlot has not been assigned
        personOne = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(1));
        deassignCommand = new DeassignCommand(personOne.getUid(), indexList);

        dateSlotList = ((Patient) personOne).getDatesSlots();
        expectedMessage = String.format(DateSlotChecker.MESSAGE_NOT_ASSIGNED_DATESLOT,
                dateSlotList.get(0).getDateSlotFormatted());
        assertCommandFailure(deassignCommand, model, expectedMessage);

        //Index Out Of Bound
        personOne = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(10));
        deassignCommand = new DeassignCommand(personOne.getUid(), indexList);

        assertCommandFailure(deassignCommand, model, DateSlotManager.MESSAGE_OUTOFBOUND_DATESLOT_INDEX);

        //HomeVisit has passed
        personOne = model.getFilteredPersonList().get(INDEX_FIFTH_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(1));
        deassignCommand = new DeassignCommand(personOne.getUid(), indexList);

        DateSlot dateSlotOne = ((Nurse) personOne).getHomeVisits().get(0).getDateSlot();
        expectedMessage = String.format(DateSlotChecker.MESSAGE_VISITED_DATESLOT,
                dateSlotOne.getDateSlotFormatted());
        assertCommandFailure(deassignCommand, model, expectedMessage);

        //Index Out Of Bound
        personOne = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        indexList = new ArrayList<>();
        indexList.add(Index.fromOneBased(7));
        deassignCommand = new DeassignCommand(personOne.getUid(), indexList);

        assertCommandFailure(deassignCommand, model, HomeVisitManager.MESSAGE_OUTOFBOUND_HOMEVISIT_INDEX);
    }
}

