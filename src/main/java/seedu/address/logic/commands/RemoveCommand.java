package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingLocation;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;
import seedu.address.model.portfolio.Risk;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the FinBook.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove the tags, plans, or notes of the client "
        + "identified by the index number used in the displayed client list. "
        + "Input existing tags, plans, or notes to remove them.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_TAG + "TAG]..."
        + "[" + PREFIX_PLAN + "PLAN] "
        + "[" + PREFIX_NOTE + "NOTE] \n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_TAG + "friend "
        + PREFIX_PLAN + "NTUC Income Plan "
        + "(this will remove the tag: friend and the plan: NTUC Income Plan from the first person in the list.)";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Removed Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one parameter to remove must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This client already exists in the FinBook.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public RemoveCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson), index.getZeroBased());
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;
        Portfolio portfolio = personToEdit.getPortfolio();
        Meeting meeting = personToEdit.getMeeting();
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Income updatedIncome = editPersonDescriptor.getIncome().orElse(personToEdit.getIncome());
        MeetingDate updatedMeetingDate = editPersonDescriptor.getMeetingDate().orElse(meeting.getMeetingDate());
        MeetingLocation updatedMeetingLocation =
            editPersonDescriptor.getMeetingLocation().orElse(meeting.getMeetingLocation());
        Set<Tag> updatedTags = new HashSet<>() {
            {
                addAll(personToEdit.getTags());
                removeAll(editPersonDescriptor.getTags().orElse(new HashSet<>()));
            }
        };
        Risk updatedRisk = editPersonDescriptor.getRisk().orElse(portfolio.getRisk());
        Set<Plan> updatedPlans = new HashSet<>() {
            {
                addAll(personToEdit.getPortfolio().getPlans());
                removeAll(editPersonDescriptor.getPlans().orElse(new HashSet<>()));
            }
        };
        Set<Note> updatedNotes = new HashSet<>() {
            {
                addAll(personToEdit.getPortfolio().getNotes());
                removeAll(editPersonDescriptor.getNotes().orElse(new HashSet<>()));
            }
        };

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedIncome,
            updatedMeetingDate, updatedMeetingLocation, updatedTags, updatedRisk, updatedPlans, updatedNotes);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        // state check
        RemoveCommand e = (RemoveCommand) other;
        return index.equals(e.index)
            && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
