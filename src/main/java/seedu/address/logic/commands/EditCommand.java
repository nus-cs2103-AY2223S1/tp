package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADUATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.Cap.CAP_SEPARATOR;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.person.predicates.SamePersonPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.storage.Storage;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_GRADUATION_DATE + "GRADUATION_DATE] "
            + "[" + PREFIX_CAP + "CAP_VALUE" + CAP_SEPARATOR + "MAX_CAP_VALUE] "
            + "[" + PREFIX_UNIVERSITY + "UNIVERSITY] "
            + "[" + PREFIX_MAJOR + "MAJOR] "
            + "[" + PREFIX_JOB_ID + "JOB_ID] "
            + "[" + PREFIX_JOB_TITLE + "JOB_TITLE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Person edited";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
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
        model.updateViewedPersonList(new SamePersonPredicate(editedPerson));
        return new CommandResult(MESSAGE_EDIT_PERSON_SUCCESS);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Cap updatedCap = editPersonDescriptor.getCap().orElse(personToEdit.getCap());
        GraduationDate updatedGraduationDate = editPersonDescriptor.getGraduationDate()
                .orElse(personToEdit.getGraduationDate());
        University updatedUniversity = editPersonDescriptor.getUniversity().orElse(personToEdit.getUniversity());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(personToEdit.getMajor());
        Id updatedJobId = editPersonDescriptor.getJobId().orElse(personToEdit.getJob().getId());
        Title updatedJobTitle = editPersonDescriptor.getJobTitle().orElse(personToEdit.getJob().getTitle());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail,
                updatedAddress,
                updatedGender,
                updatedGraduationDate,
                updatedCap,
                updatedUniversity,
                updatedMajor,
                updatedJobId,
                updatedJobTitle,
                updatedTags);
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
        private Gender gender;
        private GraduationDate graduationDate;
        private Cap cap;
        private University university;
        private Major major;
        private Id id;
        private Title title;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGender(toCopy.gender);
            setGraduationDate(toCopy.graduationDate);
            setCap(toCopy.cap);
            setUniversity(toCopy.university);
            setMajor(toCopy.major);
            setId(toCopy.id);
            setTitle(toCopy.title);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email,
                    address,
                    gender,
                    graduationDate,
                    cap,
                    university,
                    major,
                    id,
                    title,
                    tags);
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

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }
        public void setGraduationDate(GraduationDate graduationDate) {
            this.graduationDate = graduationDate;
        }
        public Optional<GraduationDate> getGraduationDate() {
            return Optional.ofNullable(graduationDate);
        }

        public void setCap(Cap cap) {
            this.cap = cap;
        }

        public Optional<Cap> getCap() {
            return Optional.ofNullable(cap);
        }

        public void setUniversity(University university) {
            this.university = university;
        }

        public Optional<University> getUniversity() {
            return Optional.ofNullable(university);
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(major);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getJobId() {
            return Optional.ofNullable(id);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getJobTitle() {
            return Optional.ofNullable(title);
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
                    && getGender().equals(e.getGender())
                    && getCap().equals(e.getCap())
                    && getGraduationDate().equals(e.getGraduationDate())
                    && getUniversity().equals(e.getUniversity())
                    && getMajor().equals(e.getMajor())
                    && getJobId().equals(e.getJobId())
                    && getJobTitle().equals(e.getJobTitle())
                    && getTags().equals(e.getTags());
        }
    }
}
