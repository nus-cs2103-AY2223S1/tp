package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
            + " [" + PREFIX_DATE_AND_SLOT_INDEX + "DATE_AND_SLOT_INDEX] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_UID + "1 "
            + PREFIX_UID + "2 "
            + PREFIX_DATE_AND_SLOT_INDEX + "1 ";

    public static final String MESSAGE_SUCCESS = "%1$s's dateslot/dateslots assigned to %2$s.";

    public static final String MESSAGE_BOTH_NURSE = "The given uids are both nurses.";
    public static final String MESSAGE_BOTH_PATIENT = "The given uids are both patients.";

    private final Uid uid1;
    private final Uid uid2;
    private final List<Index> dateslotIndex;

    /**
     * Creates an AssignCommand to assgin specific patient's date slot to a nurse.
     */
    public AssignCommand(Uid uid1, Uid uid2, List<Index> dateslotIndex) {
        requireAllNonNull(uid1, uid2, dateslotIndex);
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
        Patient patient = getPatient(firstPerson, secondPerson);
        Nurse nurse = getNurse(firstPerson, secondPerson);
        markAssign(model, patient, nurse);

        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.toLiteString(), nurse.toLiteString()));
    }

    private Patient getPatient(Person person1, Person person2) throws CommandException {
        Boolean isPerson1Patient = person1.isPatient();
        Boolean isPerson2Patient = person2.isPatient();

        if (isPerson1Patient && isPerson2Patient) {
            throw new CommandException(MESSAGE_BOTH_PATIENT);
        }
        if (isPerson1Patient || isPerson2Patient) {
            return isPerson1Patient
                    ? (Patient) person1
                    : (Patient) person2;
        }
        throw new CommandException(MESSAGE_BOTH_NURSE);
    }

    private Nurse getNurse(Person person1, Person person2) throws CommandException {
        Boolean isPerson1Nurse = person1.isNurse();
        Boolean isPerson2Nurse = person2.isNurse();

        if (isPerson1Nurse && isPerson2Nurse) {
            throw new CommandException(MESSAGE_BOTH_NURSE);
        }
        if (isPerson1Nurse || isPerson2Nurse) {
            return isPerson1Nurse
                    ? (Nurse) person1
                    : (Nurse) person2;
        }
        throw new CommandException(MESSAGE_BOTH_PATIENT);
    }

    private void markAssign(Model model, Patient patient, Nurse nurse) throws CommandException {
        Long patientUidNo = patient.getUid().getUid();
        Long nurseUidNo = nurse.getUid().getUid();
        List<DateSlot> patientDateSlotList = patient.getDatesSlots();
        List<HomeVisit> nurseHomeVisitList = nurse.getHomeVisits();
        List<Date> nurseFullyScheduledList = nurse.getFullyScheduledDates();
        List<Date> nurseUnavailableDates = nurse.getUnavailableDates();

        DateSlotManager marker = new DateSlotManager(patientDateSlotList, this.dateslotIndex);
        List<DateSlot> updatedDateSlotList = marker.markAssigned(nurseHomeVisitList, nurseUnavailableDates, nurseUidNo);
        HomeVisitManager creator = new HomeVisitManager(nurseHomeVisitList, nurseFullyScheduledList);
        List<HomeVisit> updatedHomeVisitList = creator.createHomeVisitList(patientDateSlotList,
                this.dateslotIndex, patientUidNo);
        List<Date> updatedFullyScheduledList = creator.getFullyScheduledDateList();

        InternalEditor editor = new InternalEditor(model);
        editor.editPatient(patient, updatedDateSlotList);
        editor.editNurse(nurse, updatedHomeVisitList, updatedFullyScheduledList);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AssignCommand) {
            AssignCommand o = (AssignCommand) other;
            System.out.println(uid1.equals(o.uid1));
            System.out.println(uid2.equals(o.uid2));
            System.out.println(dateslotIndex.equals(o.dateslotIndex));
        }
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                        && uid1.equals(((AssignCommand) other).uid1)
                        && uid2.equals(((AssignCommand) other).uid2)
                        && dateslotIndex.equals(((AssignCommand) other).dateslotIndex));
    }

}
