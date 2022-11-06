package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
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
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
        + "by the index number used in the displayed client list. "
        + "For parameters that are not tags, plans or notes, existing values will be overwritten by the input values. "
        + "For tags, plans, or notes, the input values will be added to existing values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_INCOME + "INCOME] "
        + "[" + PREFIX_MEETING_DATE + "MEETINGDATE] "
        + "[" + PREFIX_MEETING_LOCATION + "MEETINGLOCATION] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "[" + PREFIX_RISK + "RISK] "
        + "[" + PREFIX_PLAN + "PLAN] "
        + "[" + PREFIX_NOTE + "NOTE] \n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This client already exists in the FinBook.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
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
        Meeting meetingToEdit = personToEdit.getMeeting();
        Portfolio portfolio = personToEdit.getPortfolio();
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Income updatedIncome = editPersonDescriptor.getIncome().orElse(personToEdit.getIncome());
        MeetingDate updatedMeetingDate = editPersonDescriptor.getMeetingDate().orElse(meetingToEdit.getMeetingDate());
        MeetingLocation updatedMeetingLocation =
            editPersonDescriptor.getMeetingLocation().orElse(meetingToEdit.getMeetingLocation());
        Set<Tag> updatedTags = Stream.of(editPersonDescriptor.getTags().orElse(new HashSet<>()), personToEdit.getTags())
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
        Risk updatedRisk = editPersonDescriptor.getRisk().orElse(portfolio.getRisk());
        Set<Plan> updatedPlans = Stream
            .of(editPersonDescriptor.getPlans().orElse(new HashSet<>()), personToEdit.getPortfolio().getPlans())
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
        Set<Note> updatedNotes = Stream
            .of(editPersonDescriptor.getNotes().orElse(new HashSet<>()), personToEdit.getPortfolio().getNotes())
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());

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
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
            && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Income income;
        private MeetingDate meetingDate;
        private MeetingLocation meetingLocation;
        private Set<Tag> tags;
        private Risk risk;
        private Set<Plan> plans;
        private Set<Note> notes;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setIncome(toCopy.income);
            setMeetingDate(toCopy.meetingDate);
            setTags(toCopy.tags);
            setRisk(toCopy.risk);
            setPlans(toCopy.plans);
            setNotes(toCopy.notes);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, income, meetingDate, tags,
                risk, plans, notes);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setIncome(Income income) {
            this.income = income;
        }

        public Optional<Income> getIncome() {
            return Optional.ofNullable(income);
        }

        public void setMeetingDate(MeetingDate meetingDate) {
            this.meetingDate = meetingDate;
        }

        public Optional<MeetingDate> getMeetingDate() {
            return Optional.ofNullable(meetingDate);
        }

        public void setMeetingLocation(MeetingLocation meetingLocation) {
            this.meetingLocation = meetingLocation;
        }

        public Optional<MeetingLocation> getMeetingLocation() {
            return Optional.ofNullable(meetingLocation);
        }

        public void setRisk(Risk risk) {
            this.risk = (risk != null) ? risk : null;
        }

        public Optional<Risk> getRisk() {
            return (risk != null) ? Optional.ofNullable(risk) : Optional.empty();
        }

        public void setPlans(Set<Plan> plans) {
            this.plans = (plans != null) ? new HashSet<>(plans) : null;
        }

        public Optional<Set<Plan>> getPlans() {
            return (plans != null) ? Optional.of(Collections.unmodifiableSet(plans)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setNotes(Set<Note> notes) {
            this.notes = (notes != null) ? new HashSet<>(notes) : null;
        }

        /**
         * Returns an unmodifiable note set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code note} is null.
         */
        public Optional<Set<Note>> getNotes() {
            return (notes != null) ? Optional.ofNullable(Collections.unmodifiableSet(notes)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getIncome().equals(e.getIncome())
                && getMeetingDate().equals(e.getMeetingDate())
                && getMeetingLocation().equals(e.getMeetingLocation())
                && getTags().equals(e.getTags())
                && getRisk().equals(e.getRisk())
                && getPlans().equals(e.getPlans())
                && getNotes().equals(e.getNotes());
        }
    }
}
