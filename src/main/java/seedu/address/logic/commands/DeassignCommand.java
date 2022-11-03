package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

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
        DateSlotManager unmarker = new DateSlotManager(patientDateSlotList, dateslotOrHomevisitIndex);
        List<DateSlot> updatedDateSlotList = unmarker.unmarkAssigned();

        InternalHomeVisitRemoverFromDateSlot homeVisitRemover = new InternalHomeVisitRemoverFromDateSlot(model,
                personList, patientDateSlotList, dateslotOrHomevisitIndex);
        homeVisitRemover.removeHomeVisitsForDateSlot();

        InternalEditor editor = new InternalEditor(model);
        editor.editPatient(person, updatedDateSlotList);
    }

    private void unmarkAssignedNurse(Model model, Person person, List<Person> personList) throws CommandException {
        List<HomeVisit> homeVisitsList = ((Nurse) person).getHomeVisits();
        List<Date> fullyScheduledList = ((Nurse) person).getFullyScheduledDates();
        HomeVisitManager remover = new HomeVisitManager(homeVisitsList, dateslotOrHomevisitIndex, fullyScheduledList);
        List<HomeVisit> updatedHomeVisitList = remover.removeHomeVisits();
        List<Date> updatedFullyScheduledDatesList = remover.getFullyScheduledDateList();

        InternalUnmarkerFromHomeVisit dateSlotUnmarker = new InternalUnmarkerFromHomeVisit(model, personList,
                homeVisitsList, dateslotOrHomevisitIndex);
        dateSlotUnmarker.unmarkDateSlotForHomeVisit();

        InternalEditor editor = new InternalEditor(model);
        editor.editNurse(person, updatedHomeVisitList, updatedFullyScheduledDatesList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeassignCommand // instanceof handles nulls
                        && uid.equals(((DeassignCommand) other).uid)
                        && dateslotOrHomevisitIndex.equals(((DeassignCommand) other).dateslotOrHomevisitIndex));
    }

}


