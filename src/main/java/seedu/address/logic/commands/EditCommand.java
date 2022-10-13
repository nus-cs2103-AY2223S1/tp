package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient/nurse identified "
            + "by the unique id number used in the displayed person list."
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: UID (must be a positive integer) "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Date and Time are only applicable to patient.\n"
            + "[" + PREFIX_DATE_AND_TIME + "DATE_AND_TIME] \n"
            + "Example: " + COMMAND_WORD + PREFIX_UID + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited %1$s: %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This %1$s already exists in the address book.";
    public static final String MESSAGE_NURSE_INVALID_EDIT = "This uid gives a nurse "
            + "and there are no dates and times (and their indexes) for nurse."
            + "Please remove the date and time field and its index field.";

    public static final String MESSAGE_INVALID_NUMBERS_OF_DATETIME_AND_DATETIMEINDEX = "The dateTime index "
            + "provided is more than the dateTime provided." + "Please remove the dateTime index or add more dateTime.";

    public static final String MESSAGE_OUT_OF_BOUND_DATETIMEINDEX = "The dateTime index given is out of bound "
            + "of the existing list." + "Please retype another index that is within the range or left it empty.";

    private final Uid targetUid;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param targetUid               of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Uid targetUid, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(targetUid);
        requireNonNull(editPersonDescriptor);

        this.targetUid = targetUid;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> personToEdit = lastShownList.stream().filter(p -> p.getUid().equals(targetUid)).findFirst();

        if (!personToEdit.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }
        Person confirmedPersonToEdit = personToEdit.get();
        Person editedPerson = createEditedPerson(confirmedPersonToEdit, editPersonDescriptor);

        boolean haveDatesTimes = !(editPersonDescriptor.getDatesTimes().equals(Optional.empty()));
        boolean haveDateTimeIndexes = !(editPersonDescriptor.getDateTimeIndexes().equals(Optional.empty()));

        if (confirmedPersonToEdit.getCategory().equals("N") && (haveDateTimeIndexes || haveDatesTimes)) {
            throw new CommandException(MESSAGE_NURSE_INVALID_EDIT);
        }

        if (!confirmedPersonToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON,
                    confirmedPersonToEdit.getCategoryIndicator()));
        }

        model.setPerson(confirmedPersonToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                confirmedPersonToEdit.getCategoryIndicator(), editedPerson));

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        String updatedCategory = editPersonDescriptor.getCategory().orElse(personToEdit.getCategory());
        Uid uid = editPersonDescriptor.getUid().orElse(personToEdit.getUid());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        if (personToEdit instanceof Patient && updatedCategory.equals("P")) {
            List<DateTime> originalDateTime = ((Patient) personToEdit).getDatesTimes();
            Optional<List<DateTime>> toBeUpdateDateTime = editPersonDescriptor.getDatesTimes();
            Optional<List<Index>> toBeUpdateDateTimeIndexes = editPersonDescriptor.getDateTimeIndexes();
            List<DateTime> updatedDateTime = createEditedDateTimeList(originalDateTime,
                    toBeUpdateDateTime, toBeUpdateDateTimeIndexes);
            return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                        updatedAddress, updatedTags, updatedDateTime);
        }

        return new Person(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    private static List<DateTime> createEditedDateTimeList(List<DateTime> originalDateTime,
                                                           Optional<List<DateTime>> toBeUpdateDateTimes,
                                                           Optional<List<Index>> toBeUpdateDateTimesIndexes)
            throws CommandException {
        List<DateTime> updatedDateTime = new ArrayList<>();

        boolean isDateTimeNull = toBeUpdateDateTimes.equals(Optional.empty());
        boolean isDateTimeIndexesNull = toBeUpdateDateTimesIndexes.equals(Optional.empty());
        List<DateTime> toBeUpdateDateTime = new ArrayList<>();
        List<Index> toBeUpdateDateTimeIndexes = new ArrayList<>();

        if (!isDateTimeNull) {
            toBeUpdateDateTime = new ArrayList<>(toBeUpdateDateTimes.get());
        }
        if (!isDateTimeIndexesNull) {
            toBeUpdateDateTimeIndexes = new ArrayList<>(toBeUpdateDateTimesIndexes.get());

            //If the indexNo given is out of bound of the existing list -> throw exception
            for (Index indexNo : toBeUpdateDateTimeIndexes) {

                if (indexNo.getZeroBased() >= originalDateTime.size()) {
                    throw new CommandException(MESSAGE_OUT_OF_BOUND_DATETIMEINDEX);
                }
            }
        }

        boolean isDateTimeEmpty = !isDateTimeNull && toBeUpdateDateTime.isEmpty();
        boolean isDateTimeIndexesEmpty = !isDateTimeIndexesNull && toBeUpdateDateTimeIndexes.isEmpty();

        // [CASE 1]
        // 1. dateTime null and dateTimeIndex non-null but empty
        // 2. dateTimeIndex null and dateTime non-null but empty
        // 3. dateTime non-null but empty and dateTimeIndex non-null but empty ->
        // Deletes all the dateTime in the existing list
        if ((isDateTimeNull && isDateTimeIndexesEmpty) || (isDateTimeIndexesNull && isDateTimeEmpty)
                || (isDateTimeEmpty && isDateTimeIndexesEmpty)) {

            updatedDateTime = new ArrayList<>();

        // [CASE 2]
        // 1. dateTime null and dateTimeIndex non-null and non-empty
        // 2. dateTime non-null but empty and dateTimeIndex non-null and non-empty ->
        // Deletes the dateTime at specific index in the existing list
        } else if ((isDateTimeNull || isDateTimeEmpty) && !isDateTimeIndexesEmpty) {

            updatedDateTime = new ArrayList<>(originalDateTime);

            ReverseIndexComparator comp = new ReverseIndexComparator();
            toBeUpdateDateTimeIndexes.sort(comp);
            for (Index index: toBeUpdateDateTimeIndexes) {
                updatedDateTime.remove(index.getZeroBased());
            }

        // [CASE 3]
        // 1. dateTime null and dateTimeIndex null ->
        // Remain as original dateTime list, no changes made
        } else if (isDateTimeNull && isDateTimeIndexesNull) {

            updatedDateTime = new ArrayList<>(originalDateTime);

        // [CASE 4]
        // 1. dateTime non-null and not empty but dateTimeIndex null
        // 2. dateTime non-null and not empty and dateTimeIndex non-null but empty   ->
        // Add the given dateTime to the existing list
        } else if (!isDateTimeEmpty && (isDateTimeIndexesNull || isDateTimeIndexesEmpty)) {

            updatedDateTime = new ArrayList<>(originalDateTime);
            for (DateTime dateTime : toBeUpdateDateTime) {
                updatedDateTime.add(dateTime);
            }

        // [CASE 5]
        // 1. dateTime non-null and not empty and dateTimeIndex non-null and not empty ->
        // Change the dateTime at the specific index in the existing list to the given dateTime
        // If index given more than date time given  -> throws exception
        // If date time given more than index given -> the extra date time will be added to the existing list
        } else if (!isDateTimeEmpty && !isDateTimeIndexesEmpty) {

            updatedDateTime = new ArrayList<>(originalDateTime);
            if (toBeUpdateDateTime.size() < toBeUpdateDateTimeIndexes.size()) {
                throw new CommandException(MESSAGE_INVALID_NUMBERS_OF_DATETIME_AND_DATETIMEINDEX);
            }

            for (int i = 0; i < toBeUpdateDateTimeIndexes.size(); i++) {
                int indexNoToBeUpdated = toBeUpdateDateTimeIndexes.get(i).getZeroBased();
                DateTime dateTimeToBeUpdated = toBeUpdateDateTime.get(i);
                updatedDateTime.set(indexNoToBeUpdated, dateTimeToBeUpdated);
            }

            if (toBeUpdateDateTime.size() > toBeUpdateDateTimeIndexes.size()) {
                for (int i = toBeUpdateDateTimeIndexes.size(); i < toBeUpdateDateTime.size(); i++) {
                    updatedDateTime.add(toBeUpdateDateTime.get(i));
                }
            }

        }
        return updatedDateTime;

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
        return targetUid.equals(e.targetUid)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Uid uid;
        private String category;
        private Name name;
        private Gender gender;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private List<DateTime> datesTimes;
        private List<Index> dateTimeIndexes;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setCategory(toCopy.category);
            setUid(toCopy.uid);
            setName(toCopy.name);
            setGender(toCopy.gender);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setDatesTimes(toCopy.datesTimes);
            setDateTimeIndexes(toCopy.dateTimeIndexes);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(category, name, gender, phone, email, address,
                    tags, datesTimes, dateTimeIndexes);
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Optional<String> getCategory() {
            return Optional.ofNullable(category);
        }

        /**
         * @param uid the id to set
         */
        public void setUid(Uid uid) {
            this.uid = uid;
        }

        /**
         * @return the id
         */
        public Optional<Uid> getUid() {
            return Optional.ofNullable(uid);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }
        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
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

        /**
         * Sets {@code datesTimes} to this object's {@code datesTimes}.
         */
        public void setDatesTimes(List<DateTime> datesTimes) {
            this.datesTimes = (datesTimes != null) ? new ArrayList<DateTime>(datesTimes) : null;
        }

        /**
         * Returns a dateTime list
         * Returns {@code Optional#empty()} if {@code dateTimes} is null.
         */
        public Optional<List<DateTime>> getDatesTimes() {
            return (datesTimes != null) ? Optional.of(new ArrayList<DateTime>(datesTimes)) : Optional.empty();
        }

        /**
         * Sets {@code dateTimeIndexes} to this object's {@code dateTimeIndexes}.
         */
        public void setDateTimeIndexes(List<Index> dateTimeIndexes) {
            this.dateTimeIndexes = (dateTimeIndexes != null) ? new ArrayList<Index>(dateTimeIndexes) : null;
        }

        /**
         * Returns a dateTimeIndexes list
         * Returns {@code Optional#empty()} if {@code dateTimeIndexes} is null.
         */
        public Optional<List<Index>> getDateTimeIndexes() {
            return (dateTimeIndexes != null) ? Optional.of(new ArrayList<Index>(dateTimeIndexes)) : Optional.empty();
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

            return getUid().equals(e.getUid())
                    && getName().equals(e.getName())
                    && getCategory().equals(e.getCategory())
                    && getGender().equals(e.getGender())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getDatesTimes().equals(e.getDatesTimes())
                    && getDateTimeIndexes().equals(e.getDateTimeIndexes())
                    && getTags().equals(e.getTags());
        }
    }
}
