package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
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
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.person.VisitStatus;
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
            + "Date and Slot are only applicable to patient and Date and Slot Index is used to indicate"
            + "the specific date and slot to be edited. \n"
            + "[" + PREFIX_DATE_AND_SLOT + "DATE_AND_SLOT] \n"
            + "[" + PREFIX_DATE_AND_SLOT_INDEX + "DATE_AND_SLOT_INDEX \n"
            + "Example: " + COMMAND_WORD + PREFIX_UID + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited %1$s: %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This %1$s already exists in the address book.";
    public static final String MESSAGE_NURSE_INVALID_DATESLOT_EDIT = "This uid gives a nurse "
            + "and there are no dates and slot (and their indexes) for nurse. "
            + "Please remove the date and slot field and its index field.";

    public static final String MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX = "The dateSlot index "
            + "provided is more than the dateSlot provided." + "Please remove the dateSlot index or add more dateSlot.";

    public static final String MESSAGE_OUT_OF_BOUND_DATESLOTINDEX = "The dateSlot index given is out of bound "
            + "of the existing list." + "Please retype another index that is within the range or left it empty.";

    public static final String MESSAGE_NURSE_INVALID_VISITSTATUS_EDIT = "This uid gives a nurse "
            + "and nurses do not have a visit status. "
            + "Please remove the visit status field.";

    private final Uid targetUid;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param targetUid Uid of the person in the filtered person list to edit
     * @param editPersonDescriptor Details to edit the person with
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

        if (personToEdit.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }

        Person confirmedPersonToEdit = personToEdit.get();

        boolean haveDatesSlots = editPersonDescriptor.getDatesSlots().isPresent();
        boolean haveDateSlotIndexes = editPersonDescriptor.getDateSlotIndexes().isPresent();
        boolean haveVisitStatus = editPersonDescriptor.getVisitStatus().isPresent();
        boolean isNurse = editPersonDescriptor.getCategory().equals("P") || confirmedPersonToEdit instanceof Nurse;

        if (isNurse) {
            if (haveDateSlotIndexes || haveDatesSlots) {
                throw new CommandException(MESSAGE_NURSE_INVALID_DATESLOT_EDIT);
            }

            if (haveVisitStatus) {
                throw new CommandException(MESSAGE_NURSE_INVALID_VISITSTATUS_EDIT);
            }
        }

        Person editedPerson = createEditedPerson(confirmedPersonToEdit, editPersonDescriptor);

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

        Category updatedCategory = editPersonDescriptor.getCategory().orElse(personToEdit.getCategory());
        Uid uid = editPersonDescriptor.getUid().orElse(personToEdit.getUid());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        if (personToEdit instanceof Patient && updatedCategory.categoryName.equals("P")) {
            List<DateSlot> originalDateSlot = ((Patient) personToEdit).getDatesSlots();
            Optional<List<DateSlot>> toBeUpdateDateSlot = editPersonDescriptor.getDatesSlots();
            Optional<List<Index>> toBeUpdateDateSlotIndexes = editPersonDescriptor.getDateSlotIndexes();

            List<DateSlot> updatedDateSlot = createEditedDateSlotList(originalDateSlot,
                    toBeUpdateDateSlot, toBeUpdateDateSlotIndexes);
            VisitStatus updatedVisitStatus = editPersonDescriptor.getVisitStatus()
                    .orElse(((Patient) personToEdit).getVisitStatus());

            return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                        updatedAddress, updatedTags, updatedDateSlot, updatedVisitStatus);

        } else if (updatedCategory.categoryName.equals("P")) {

            List<DateSlot> updatedDateSlot = editPersonDescriptor.getDatesSlots().orElse(null);
            VisitStatus updatedVisitStatus = editPersonDescriptor.getVisitStatus()
                    .orElse(((Patient) personToEdit).getVisitStatus());

            return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedDateSlot, updatedVisitStatus);

        } else if (updatedCategory.categoryName.equals("N")) {

            return new Nurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags);

        } else {

            throw new IllegalArgumentException(Category.MESSAGE_CONSTRAINTS);
        }
    }

    private static List<DateSlot> createEditedDateSlotList(List<DateSlot> originalDateSlot,
                                                           Optional<List<DateSlot>> toBeUpdateDateSlots,
                                                           Optional<List<Index>> toBeUpdateDateSlotsIndexes)
            throws CommandException {
        List<DateSlot> updatedDateSlot = new ArrayList<>();

        boolean isDateSlotNull = toBeUpdateDateSlots.equals(Optional.empty());
        boolean isDateSlotIndexesNull = toBeUpdateDateSlotsIndexes.equals(Optional.empty());
        List<DateSlot> toBeUpdateDateSlot = new ArrayList<>();
        List<Index> toBeUpdateDateSlotIndexes = new ArrayList<>();

        if (!isDateSlotNull) {
            toBeUpdateDateSlot = new ArrayList<>(toBeUpdateDateSlots.get());
        }
        if (!isDateSlotIndexesNull) {
            toBeUpdateDateSlotIndexes = new ArrayList<>(toBeUpdateDateSlotsIndexes.get());

            //If the indexNo given is out of bound of the existing list -> throw exception
            for (Index indexNo : toBeUpdateDateSlotIndexes) {

                if (indexNo.getZeroBased() >= originalDateSlot.size()) {
                    throw new CommandException(MESSAGE_OUT_OF_BOUND_DATESLOTINDEX);
                }
            }
        }

        boolean isDateSlotEmpty = !isDateSlotNull && toBeUpdateDateSlot.isEmpty();
        boolean isDateSlotIndexesEmpty = !isDateSlotIndexesNull && toBeUpdateDateSlotIndexes.isEmpty();

        // [CASE 1]
        // 1. dateTime null and dateTimeIndex non-null but empty
        // 2. dateTimeIndex null and dateTime non-null but empty
        // 3. dateTime non-null but empty and dateTimeIndex non-null but empty ->
        // Deletes all the dateTime in the existing list
        if ((isDateSlotNull && isDateSlotIndexesEmpty) || (isDateSlotIndexesNull && isDateSlotEmpty)
                || (isDateSlotEmpty && isDateSlotIndexesEmpty)) {

            updatedDateSlot = new ArrayList<>();

        // [CASE 2]
        // 1. dateTime null and dateTimeIndex non-null and non-empty
        // 2. dateTime non-null but empty and dateTimeIndex non-null and non-empty ->
        // Deletes the dateTime at specific index in the existing list
        } else if ((isDateSlotNull || isDateSlotEmpty) && !isDateSlotIndexesEmpty) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);

            ReverseIndexComparator comp = new ReverseIndexComparator();
            toBeUpdateDateSlotIndexes.sort(comp);
            for (Index index: toBeUpdateDateSlotIndexes) {
                updatedDateSlot.remove(index.getZeroBased());
            }

        // [CASE 3]
        // 1. dateTime null and dateTimeIndex null ->
        // Remain as original dateTime list, no changes made
        } else if (isDateSlotNull && isDateSlotIndexesNull) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);

        // [CASE 4]
        // 1. dateTime non-null and not empty but dateTimeIndex null
        // 2. dateTime non-null and not empty and dateTimeIndex non-null but empty   ->
        // Add the given dateTime to the existing list
        } else if (!isDateSlotEmpty && (isDateSlotIndexesNull || isDateSlotIndexesEmpty)) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);
            for (DateSlot dateTime : toBeUpdateDateSlot) {
                updatedDateSlot.add(dateTime);
            }

        // [CASE 5]
        // 1. dateTime non-null and not empty and dateTimeIndex non-null and not empty ->
        // Change the dateTime at the specific index in the existing list to the given dateTime
        // If index given more than date time given  -> throws exception
        // If date time given more than index given -> the extra date time will be added to the existing list
        } else if (!isDateSlotEmpty && !isDateSlotIndexesEmpty) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);
            if (toBeUpdateDateSlot.size() < toBeUpdateDateSlotIndexes.size()) {
                throw new CommandException(MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX);
            }

            for (int i = 0; i < toBeUpdateDateSlotIndexes.size(); i++) {
                int indexNoToBeUpdated = toBeUpdateDateSlotIndexes.get(i).getZeroBased();
                DateSlot dateSlotToBeUpdated = toBeUpdateDateSlot.get(i);
                updatedDateSlot.set(indexNoToBeUpdated, dateSlotToBeUpdated);
            }

            if (toBeUpdateDateSlot.size() > toBeUpdateDateSlotIndexes.size()) {
                for (int i = toBeUpdateDateSlotIndexes.size(); i < toBeUpdateDateSlot.size(); i++) {
                    updatedDateSlot.add(toBeUpdateDateSlot.get(i));
                }
            }

        }
        return updatedDateSlot;

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
        private Category category;
        private Uid uid;
        private Name name;
        private Gender gender;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private List<DateSlot> datesSlots;
        private List<Index> dateSlotIndexes;
        private VisitStatus visitStatus;

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
            setDatesSlots(toCopy.datesSlots);
            setDateSlotIndexes(toCopy.dateSlotIndexes);
            setVisitStatus(toCopy.visitStatus);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(category, name, gender, phone, email, address,
                    tags, datesSlots, dateSlotIndexes);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Optional<Category> getCategory() {
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
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code datesSlots} to this object's {@code datesSlots}.
         */
        public void setDatesSlots(List<DateSlot> datesSlots) {
            this.datesSlots = (datesSlots != null) ? new ArrayList<DateSlot>(datesSlots) : null;
        }

        /**
         * Returns a dateSlot list
         * Returns {@code Optional#empty()} if {@code dateSlots} is null.
         */
        public Optional<List<DateSlot>> getDatesSlots() {
            return (datesSlots != null) ? Optional.of(new ArrayList<DateSlot>(datesSlots)) : Optional.empty();
        }

        /**
         * Sets {@code dateSlotIndexes} to this object's {@code dateSlotIndexes}.
         */
        public void setDateSlotIndexes(List<Index> dateSlotIndexes) {
            this.dateSlotIndexes = (dateSlotIndexes != null) ? new ArrayList<Index>(dateSlotIndexes) : null;
        }

        /**
         * Returns a dateSlotIndexes list
         * Returns {@code Optional#empty()} if {@code dateSlotIndexes} is null.
         */
        public Optional<List<Index>> getDateSlotIndexes() {
            return (dateSlotIndexes != null) ? Optional.of(new ArrayList<Index>(dateSlotIndexes)) : Optional.empty();
        }

        /**
         * Sets {@code visitStatus} to this object's {@code visitStatus}.
         */
        public EditPersonDescriptor setVisitStatus(VisitStatus visitStatus) {
            this.visitStatus = visitStatus;
            return this;
        }

        /**
         * Returns a VisitStatus
         * Returns {@code Optional#empty()} if {@code visitStatus} is null.
         */
        public Optional<VisitStatus> getVisitStatus() {
            return (visitStatus != null) ? Optional.of(visitStatus) : Optional.empty();
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
                    && getDatesSlots().equals(e.getDatesSlots())
                    && getDateSlotIndexes().equals(e.getDateSlotIndexes())
                    && getTags().equals(e.getTags())
                    && getVisitStatus().equals(e.getVisitStatus());
        }
    }
}
