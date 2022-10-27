package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
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
 * Deletes a patient/nurse identified using it's displayed index from the
 * address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient/nurse identified by the unique id number used in the displayed person list.\n"
            + "Parameters: UID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_UID + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted %1$s: %2$s";

    private final Uid targetUid;

    public DeleteCommand(Uid targetUid) {
        this.targetUid = targetUid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Person> personToDelete = lastShownList.stream().filter(p -> p.getUid().equals(targetUid)).findFirst();
        if (!personToDelete.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }
        Person confirmedPersonToDelete = personToDelete.get();

        Boolean haveDeleted = false;
        Boolean haveUnmark = false;
        if (confirmedPersonToDelete instanceof Patient) {
            haveDeleted = deleteRespectiveHomeVisit(model, confirmedPersonToDelete, lastShownList);
        } else {
            haveUnmark = unmarkRespectiveDateSlot(model, confirmedPersonToDelete, lastShownList);
        }
        String extraMesssage = "";
        if (haveDeleted) {
            extraMesssage = "The respective home visit has also been deleted.";
        }
        if (haveUnmark) {
            extraMesssage = "The respective date slot has also been unmarked.";
        }
        model.deletePerson(confirmedPersonToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                confirmedPersonToDelete.getCategoryIndicator(), confirmedPersonToDelete) + " " + extraMesssage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                        && targetUid.equals(((DeleteCommand) other).targetUid)); // state check
    }

    private Boolean deleteRespectiveHomeVisit(Model model, Person person, List<Person> personList)
            throws CommandException {
        boolean hasDeleted = false;
        List<DateSlot> dateSlotList = ((Patient) person).getDatesSlots();
        if (dateSlotList.size() == 0) {
            return hasDeleted;
        } else {
            for (DateSlot dateSlot : dateSlotList) {
                if (dateSlot.getHasAssigned()) {
                    removeHomeVisit(model, dateSlot, personList);
                    hasDeleted = true;
                }
            }
        }
        return hasDeleted;
    }

    private Boolean unmarkRespectiveDateSlot(Model model, Person person, List<Person> personList)
            throws CommandException {
        boolean hasUnmarked = false;
        List<HomeVisit> homeVisitList = ((Nurse) person).getHomeVisits();
        if (homeVisitList.size() == 0) {
            return hasUnmarked;
        } else {
            for (HomeVisit homevisit : homeVisitList) {
                Long patientUidNo = homevisit.getHomeVisitPatientUidNo();
                DateSlot dateSlot = homevisit.getDateSlot();
                unmarkDateSlot(model, dateSlot, patientUidNo, personList);
                hasUnmarked = true;
            }
        }
        return hasUnmarked;
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

    private void unmarkDateSlot(Model model, DateSlot dateslot, Long patientUidNo, List<Person> personList)
            throws CommandException {
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
}
