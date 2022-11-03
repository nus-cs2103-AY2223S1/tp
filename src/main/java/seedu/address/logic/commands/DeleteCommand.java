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
        InternalHomeVisitRemoverFromDateSlot homeVisitRemover = new InternalHomeVisitRemoverFromDateSlot(model,
                personList, dateSlotList);
        hasDeleted = homeVisitRemover.removeHomeVisitsForDateSlot();
        return hasDeleted;
    }

    private Boolean unmarkRespectiveDateSlot(Model model, Person person, List<Person> personList)
            throws CommandException {
        boolean hasUnmarked = false;
        List<HomeVisit> homeVisitList = ((Nurse) person).getHomeVisits();
        if (!homeVisitList.isEmpty()) {
            InternalUnmarkerFromHomeVisit dateSlotUnmarker = new InternalUnmarkerFromHomeVisit(model, personList,
                    homeVisitList);
            dateSlotUnmarker.unmarkDateSlotForHomeVisit();
            hasUnmarked = true;
        }
        return hasUnmarked;
    }
}

