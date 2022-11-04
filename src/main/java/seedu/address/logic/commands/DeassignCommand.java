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
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;

/**
 * Deassigns a home-visit slot.
 */
public class DeassignCommand extends Command {

    public static final String COMMAND_WORD = "deassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deassign home visit dateslot"
            + "by using the unique id number of the patient or the nurse and respective index.\n"
            + "Parameters: " + PREFIX_UID + "UID of a nurse/patient (must be a positive integer) "
            + "If it is a patient, to indicate the specific dateslot to be deassigned: "
            + "[" + PREFIX_DATE_AND_SLOT_INDEX + "DATE_AND_SLOT_INDEX] \n"
            + "If it is a nurse, to indicate the specific homevisit to be deassigned: "
            + "[" + PREFIX_DATE_AND_SLOT_INDEX + "HOME_VISIT_INDEX] \n"
            + "Example: " + COMMAND_WORD + PREFIX_UID + " 1 "
            + PREFIX_DATE_AND_SLOT_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "%1$s 's dateslot/homevisit has been deassigned.";
    public static final String MESSAGE_INVALID_DATESLOT_OR_HOMEVISIT = "The dateslot/homevisit %1$s "
            + "has already passed.";
    public static final String MESSAGE_NOT_ASSIGNED_DATESLOT = "The dateslot %1$s has not been assigned.";
    public static final String MESSAGE_OUTOFBOUND_DATESLOT_INDEX = "The dateslot/homevisit index "
            + "given is out of bounds.";

    private final Uid uid;
    private final List<Index> dateslotOrHomevisitIndex;

    /**
     * Creates a DeassignCommand to deassgin specific patient's date slot or
     * specific nurse's home visit.
     */
    public DeassignCommand(Uid uid, List<Index> dateslotOrHomevisitIndex) {
        requireNonNull(uid);
        requireNonNull(dateslotOrHomevisitIndex);
        this.uid = uid;
        this.dateslotOrHomevisitIndex = new ArrayList<>();
        this.dateslotOrHomevisitIndex.addAll(dateslotOrHomevisitIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> person1 = lastShownList.stream().filter(p -> p.getUid().equals(uid)).findFirst();

        if (person1.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }

        Person personToBeEdit1 = person1.get();
        if (personToBeEdit1 instanceof Patient) {
            unmarkAssignedPatient(model, personToBeEdit1, lastShownList);
        } else if (personToBeEdit1 instanceof Nurse) {
            unmarkAssignedNurse(model, personToBeEdit1, lastShownList);
        } else {
            throw new IllegalArgumentException(Category.MESSAGE_CONSTRAINTS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToBeEdit1.getUid().getUid()));
    }

    private void unmarkAssignedPatient(Model model, Person person, List<Person> personList) throws CommandException {
        List<DateSlot> patientDateSlotList = ((Patient) person).getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>(patientDateSlotList);
        if (dateslotOrHomevisitIndex.isEmpty()) {
            for (DateSlot dateslot : updatedDateSlotList) {
                unmarkActionForPatient(model, dateslot, personList);
            }
        } else {
            sortAndCheckIndexForPatient(updatedDateSlotList);
            for (Index index : dateslotOrHomevisitIndex) {
                DateSlot dateSlot = updatedDateSlotList.get(index.getZeroBased());
                unmarkActionForPatient(model, dateSlot, personList);
            }

        }
        editPatient(model, person, updatedDateSlotList);

    }

    private void unmarkAssignedNurse(Model model, Person person, List<Person> personList) throws CommandException {
        List<HomeVisit> homeVisitsList = ((Nurse) person).getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(homeVisitsList);
        List<Date> fullyScheduledList = ((Nurse) person).getFullyScheduledDates();
        List<Date> updatedFullyScheduledDatesList = new ArrayList<>(fullyScheduledList);

        if (dateslotOrHomevisitIndex.isEmpty()) {
            for (HomeVisit homeVisit : updatedHomeVisitList) {
                unmarkActionForNurse(model, homeVisit, personList,
                        updatedHomeVisitList, updatedFullyScheduledDatesList);
                if (updatedHomeVisitList.size() == 0) {
                    break;
                }
            }
        } else {
            sortAndCheckIndexForNurse(updatedHomeVisitList);
            for (Index index : dateslotOrHomevisitIndex) {
                HomeVisit homeVisit = updatedHomeVisitList.get(index.getZeroBased());
                unmarkActionForNurse(model, homeVisit, personList,
                        updatedHomeVisitList, updatedFullyScheduledDatesList);
            }
        }

        editNurse(model, person, updatedHomeVisitList, updatedFullyScheduledDatesList);
    }

    private void sortAndCheckIndexForPatient(List<DateSlot> dateSlotList) throws CommandException {
        ReverseIndexComparator comp = new ReverseIndexComparator();
        dateslotOrHomevisitIndex.sort(comp);

        if (dateslotOrHomevisitIndex.get(0).getZeroBased() >= dateSlotList.size()) {
            throw new CommandException(MESSAGE_OUTOFBOUND_DATESLOT_INDEX);
        }

    }

    private void sortAndCheckIndexForNurse(List<HomeVisit> homeVisitsList) throws CommandException {
        ReverseIndexComparator comp = new ReverseIndexComparator();
        dateslotOrHomevisitIndex.sort(comp);

        if (dateslotOrHomevisitIndex.get(0).getZeroBased() >= homeVisitsList.size()) {
            throw new CommandException(MESSAGE_OUTOFBOUND_DATESLOT_INDEX);
        }

    }

    private void checkInvalid(DateSlot dateSlot) throws CommandException {
        if (dateSlot.getHasVisited()) {
            throw new CommandException(String.format(MESSAGE_INVALID_DATESLOT_OR_HOMEVISIT,
                    dateSlot.getDateSlotFormatted()));
        }
    }

    private void checkNotAssigned(DateSlot dateSlot) throws CommandException {
        if (!dateSlot.getHasAssigned()) {
            throw new CommandException(String.format(MESSAGE_NOT_ASSIGNED_DATESLOT,
                    dateSlot.getDateSlotFormatted()));
        }
    }

    private void removeHomeVisit(Model model, DateSlot dateSlot, List<Person> personList) throws CommandException {
        Long nurseUidNo = dateSlot.getNurseUidNo();
        Person nurse = personList.stream().filter(p -> p.getUid().getUid().equals(nurseUidNo)).findFirst().get();
        List<HomeVisit> nurseHomeVisitList = ((Nurse) nurse).getHomeVisits();
        List<Date> nurseFullyScheduledList = ((Nurse) nurse).getFullyScheduledDates();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(nurseHomeVisitList);
        List<Date> updatedFullyScheduledList = new ArrayList<>(nurseFullyScheduledList);

        HomeVisit homeVisitToBeDeleted = updatedHomeVisitList.stream().filter(
                h -> h.getDateSlot().getDateTime().equals(dateSlot.getDateTime())).findFirst().get();

        updatedHomeVisitList.remove(homeVisitToBeDeleted);

        Optional<Date> dateToBeDeleted = updatedFullyScheduledList.stream().filter(
                h -> h.getDate().equals(dateSlot.getDate())).findFirst();

        if (!dateToBeDeleted.isEmpty()) {
            updatedFullyScheduledList.remove(dateToBeDeleted.get());
        }

        editNurse(model, nurse, updatedHomeVisitList, updatedFullyScheduledList);

    }

    private void unmarkActionForPatient(Model model, DateSlot dateSlot, List<Person> personList)
            throws CommandException {
        checkInvalid(dateSlot);
        checkNotAssigned(dateSlot);
        removeHomeVisit(model, dateSlot, personList);
        dateSlot.unmark();
    }

    private void unmarkActionForNurse(Model model, HomeVisit homeVisit, List<Person> personList,
            List<HomeVisit> homeVisitList,
            List<Date> fullyScheduledList) throws CommandException {
        DateSlot homeVisitToBeDeleted = homeVisit.getDateSlot();
        Long patientUidNo = homeVisit.getHomeVisitPatientUidNo();
        unmarkDateSlot(model, homeVisitToBeDeleted, patientUidNo, personList);
        homeVisitList.remove(homeVisit);

        Optional<Date> dateToBeDeleted = fullyScheduledList.stream().filter(
                h -> h.getDate().equals(homeVisitToBeDeleted.getDate())).findFirst();

        if (!dateToBeDeleted.isEmpty()) {
            fullyScheduledList.remove(dateToBeDeleted.get());
        }
    }

    private void unmarkDateSlot(Model model, DateSlot dateslot, Long patientUidNo, List<Person> personList) {
        Person patient = personList.stream().filter(
                p -> p.getUid().getUid().equals(patientUidNo)).findFirst().get();
        List<DateSlot> dateSlotList = ((Patient) patient).getDatesSlots();
        List<DateSlot> updatedDateSlotList = new ArrayList<>(dateSlotList);
        DateSlot dateSlotToBeUnmarked = updatedDateSlotList.stream().filter(
                d -> d.getDateTime().equals(dateslot.getDateTime())).findFirst().get();
        dateSlotToBeUnmarked.unmark();
        editPatient(model, patient, updatedDateSlotList);
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeassignCommand // instanceof handles nulls
                        && uid.equals(((DeassignCommand) other).uid)
                        && dateslotOrHomevisitIndex.equals(((DeassignCommand) other).dateslotOrHomevisitIndex));
    }

}
