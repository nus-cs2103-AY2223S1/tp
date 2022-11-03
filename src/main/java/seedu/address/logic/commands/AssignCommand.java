package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;

/**
 * Assigns a patient's home-visit dateslot to nurse.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign a patient's home visit dateslot to a nurse"
            + "by using the unique id number of the patient, nurse and the dateslot index.\n"
            + "Parameters: " + PREFIX_UID + "UID of a nurse (must be a positive integer) "
            + PREFIX_UID + "UID of a patient (must be a positive integer)"
            + "[" + PREFIX_DATE_AND_SLOT_INDEX + "DATE_AND_SLOT_INDEX] \n"
            + "Example: " + COMMAND_WORD + PREFIX_UID + " 1 "
            + PREFIX_UID + "2"
            + PREFIX_DATE_AND_SLOT_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "%1$s's dateslot/dateslots assigned to %2$s.";

    public static final String MESSAGE_BOTH_NURSE = "The given uids are both nurses.";
    public static final String MESSAGE_BOTH_PATIENT = "The given uids are both patients.";
    public static final String MESSAGE_INVALID_DATESLOT = "The date slot %1$s has already passed.";
    public static final String MESSAGE_ASSIGNED_DATESLOT = "The date slot %1$s has been assigned already.";
    public static final String MESSAGE_OUTOFBOUND_DATESLOT_INDEX = "The date slot index given is out of bounds.";

    public static final String MESSAGE_TIME_CRASHES = "There is already an exisiting homevisit in this dateslot %1$s."
            + "Please assign another nurse";
    public static final String MESSAGE_UNAVAILABLE_DATE = "The nurse is unavailable on this day %1$s. "
            + "Please assign another nurse";

    private final Uid uid1;
    private final Uid uid2;
    private final List<Index> dateslotIndex;

    /**
     * Creates an AssignCommand to assgin specific patient's date slot to a nurse.
     */
    public AssignCommand(Uid uid1, Uid uid2, List<Index> dateslotIndex) {
        requireNonNull(uid1);
        requireNonNull(uid2);
        requireNonNull(dateslotIndex);
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.dateslotIndex = new ArrayList<>();
        this.dateslotIndex.addAll(dateslotIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> person1 = lastShownList.stream().filter(p -> p.getUid().equals(uid1)).findFirst();
        Optional<Person> person2 = lastShownList.stream().filter(p -> p.getUid().equals(uid2)).findFirst();

        if (person1.isEmpty() || person2.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }

        Person firstPerson = person1.get();
        Person secondPerson = person2.get();
        Person patient = getPatient(firstPerson, secondPerson);
        Person nurse = getNurse(firstPerson, secondPerson);
        markAssign(model, patient, nurse);

        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getUid().getUid(), nurse.getUid().getUid()));
    }

    private Person getPatient(Person person1, Person person2) throws CommandException {
        Boolean isPerson1Patient = person1.isPatient();
        Boolean isPerson2Patient = person2.isPatient();

        if (isPerson1Patient && isPerson2Patient) {
            throw new CommandException(MESSAGE_BOTH_PATIENT);
        }
        if (isPerson1Patient || isPerson2Patient) {
            return isPerson1Patient
                    ? person1
                    : person2;
        }
        throw new CommandException(MESSAGE_BOTH_NURSE);
    }

    private Person getNurse(Person person1, Person person2) throws CommandException {
        Boolean isPerson1Nurse = person1.isNurse();
        Boolean isPerson2Nurse = person2.isNurse();

        if (isPerson1Nurse && isPerson2Nurse) {
            throw new CommandException(MESSAGE_BOTH_NURSE);
        }
        if (isPerson1Nurse || isPerson2Nurse) {
            return isPerson1Nurse
                    ? person1
                    : person2;
        }
        throw new CommandException(MESSAGE_BOTH_PATIENT);
    }

    private void markAssign(Model model, Person patient, Person nurse) throws CommandException {
        List<DateSlot> patientDateSlotList = ((Patient) patient).getDatesSlots();
        Long nurseUidNo = nurse.getUid().getUid();
        List<HomeVisit> nurseHomeVisitList = ((Nurse) nurse).getHomeVisits();
        List<Date> nurseFullyScheduledList = ((Nurse) nurse).getFullyScheduledDates();

        List<DateSlot> updatedDateSlotList = new ArrayList<>(patientDateSlotList);
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(nurseHomeVisitList);
        List<Date> updatedFullyScheduledList = new ArrayList<>(nurseFullyScheduledList);
        if (dateslotIndex.isEmpty()) {
            for (DateSlot dateslot : updatedDateSlotList) {
                executeChecksAndActions(dateslot, updatedHomeVisitList, updatedFullyScheduledList,
                        nurse, nurseUidNo, patient);
            }
        } else {
            sortAndCheckIndexForPatient(updatedDateSlotList);

            for (Index index : dateslotIndex) {
                DateSlot dateSlot = updatedDateSlotList.get(index.getZeroBased());
                executeChecksAndActions(dateSlot, updatedHomeVisitList, updatedFullyScheduledList,
                        nurse, nurseUidNo, patient);
            }
        }
        editPatient(model, patient, updatedDateSlotList);
        editNurse(model, nurse, updatedHomeVisitList, updatedFullyScheduledList);
    }

    private void checkInvalid(DateSlot dateSlot) throws CommandException {
        if (dateSlot.getHasVisited()) {
            throw new CommandException(String.format(MESSAGE_INVALID_DATESLOT, dateSlot.getDateSlotFormatted()));
        }
    }

    private void checkAssigned(DateSlot dateSlot) throws CommandException {
        if (dateSlot.getHasAssigned()) {
            throw new CommandException(String.format(MESSAGE_ASSIGNED_DATESLOT, dateSlot.getDateSlotFormatted()));
        }
    }

    private void checkCrashes(DateSlot dateSlot, List<HomeVisit> homeVisitList) throws CommandException {
        Optional<HomeVisit> homeVisit = homeVisitList.stream().filter(
                h -> h.getDateSlot().getDateTime().equals(dateSlot.getDateTime())).findFirst();
        if (!homeVisit.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TIME_CRASHES, dateSlot.getDateSlotFormatted()));
        }
    }

    private void checkUnavailability(DateSlot dateSlot, Person nurse) throws CommandException {
        List<Date> unavailabilityDateList = ((Nurse) nurse).getUnavailableDates();
        Optional<Date> date = unavailabilityDateList.stream().filter(
                d -> d.getDate().equals(dateSlot.getDate())).findFirst();
        if (!date.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_UNAVAILABLE_DATE, dateSlot.getDateSlotFormatted()));
        }
    }

    private void createHomeVisit(DateSlot date, Person patient, List<HomeVisit> homeVisitList,
            List<Date> updatedFullyScheduledDateList) {
        HomeVisit homeVisit = new HomeVisit(date, patient.getUid().getUid());
        homeVisitList.add(homeVisit);
        LocalDate localdate = date.getDate();
        int frequencyCount = 0;
        for (int i = 0; i < homeVisitList.size(); i++) {
            LocalDate dateToCheck = homeVisitList.get(i).getDateSlot().getDate();
            if (dateToCheck.equals(localdate)) {
                frequencyCount++;
            }
        }
        if (frequencyCount == 4) {
            updatedFullyScheduledDateList.add(new Date(localdate));
        }
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

    private void editNurse(Model model, Person nurse, List<HomeVisit> homeVisitList,
            List<Date> fullyScheduledDateList) throws CommandException {
        Uid uid = nurse.getUid();
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        editPersonDescriptor.setHomeVisits(homeVisitList);
        editPersonDescriptor.setFullyScheduledDates(fullyScheduledDateList);
        EditCommand editCommand1 = new EditCommand(uid, editPersonDescriptor);
        editCommand1.execute(model);
    }

    private void sortAndCheckIndexForPatient(List<DateSlot> dateSlotList) throws CommandException {
        ReverseIndexComparator comp = new ReverseIndexComparator();
        dateslotIndex.sort(comp);

        if (dateslotIndex.get(0).getZeroBased() >= dateSlotList.size()) {
            throw new CommandException(MESSAGE_OUTOFBOUND_DATESLOT_INDEX);
        }

    }

    private void executeChecksAndActions(DateSlot dateSlot, List<HomeVisit> homeVisitList,
            List<Date> fullyScheduledDate,
            Person nurse, Long nurseUidNo, Person patient) throws CommandException {
        checkInvalid(dateSlot);
        checkAssigned(dateSlot);
        checkCrashes(dateSlot, homeVisitList);
        checkUnavailability(dateSlot, nurse);
        dateSlot.mark(nurseUidNo);
        createHomeVisit(dateSlot, patient, homeVisitList, fullyScheduledDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                        && uid1.equals(((AssignCommand) other).uid1)
                        && uid2.equals(((AssignCommand) other).uid2)
                        && dateslotIndex.equals(((AssignCommand) other).dateslotIndex));
    }

}
