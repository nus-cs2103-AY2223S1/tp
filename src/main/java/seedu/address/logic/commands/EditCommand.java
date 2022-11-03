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
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNAVAILABLE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNAVAILABLE_DATE_INDEX;
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
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
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
            + "Date and Slot are only applicable to patient and Date and Slot Index is used to indicate"
            + "the specific date and slot to be edited. \n"
            + "[" + PREFIX_DATE_AND_SLOT + "DATE_AND_SLOT] \n"
            + "[" + PREFIX_DATE_AND_SLOT_INDEX + "DATE_AND_SLOT_INDEX \n"
            + "Unavailable Date are only applicable to nurse and Unavailable Date Index is used to indicate"
            + "the specific unavailable date to be edited. \n"
            + "[" + PREFIX_UNAVAILABLE_DATE + "UNAVAILABLE_DATE] \n"
            + "[" + PREFIX_UNAVAILABLE_DATE_INDEX + "UNAVAILABLE_DATE_INDEX] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_UID + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited %1$s: %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This %1$s already exists in the address book.";
    public static final String MESSAGE_NURSE_INVALID_DATESLOT_EDIT = "This uid gives a nurse "
            + "and there are no dates and slot (and their indexes) for nurse. "
            + "Please remove the date and slot field and its index field.";

    public static final String MESSAGE_PATIENT_INVALID_UNAVAILABLE_DATE_EDIT = "This uid gives a patient "
            + "and there are no unavailable dates (and their indexes) for patient. "
            + "Please remove the unavailable dates field and its index field.";

    public static final String MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX = "The dateSlot index "
            + "provided is more than the dateSlot provided." + "Please remove the dateSlot index or add more dateSlot.";

    public static final String MESSAGE_OUT_OF_BOUND_DATESLOTINDEX = "The dateSlot index given is out of bounds "
            + "of the existing list." + "Please retype another index that is within the range or left it empty.";

    public static final String MESSAGE_INVALID_NUMBERS_OF_UNAVAILABLEDATES_AND_UNAVAILABLEDATESINDEX =
            "The unavailable date index " + "provided is more than the unavailable date provided."
                    + "Please remove the unavailable date index or add more unavailable date.";

    public static final String MESSAGE_OUT_OF_BOUND_UNAVAILABLEDATESINDEX = "The unavailable date index "
            + "given is out of bounds of the existing list."
            + "Please retype another index that is within the range or left it empty.";

    private final Uid targetUid;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param targetUid            Uid of the person in the filtered person list to
     *                             edit
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

        checkDescriptionGiven(editPersonDescriptor, confirmedPersonToEdit);

        Person editedPerson = createEditedPerson(model, lastShownList, confirmedPersonToEdit, editPersonDescriptor);

        if (!confirmedPersonToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON,
                    confirmedPersonToEdit.getCategoryIndicator()));
        }

        model.setPerson(confirmedPersonToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                confirmedPersonToEdit.getCategoryIndicator(), editedPerson));

    }

    private void checkDescriptionGiven(EditPersonDescriptor editPersonDescriptor, Person personToEdit)
            throws CommandException {
        boolean haveDatesSlots = editPersonDescriptor.getDatesSlots().isPresent();
        boolean haveDateSlotIndexes = editPersonDescriptor.getDateSlotIndexes().isPresent();
        boolean haveUnavailableDates = editPersonDescriptor.getUnavailableDates().isPresent();
        boolean haveUnavailableDateIndexes = editPersonDescriptor.getDateIndexes().isPresent();
        boolean isNurse = editPersonDescriptor.getCategory().equals("N") || personToEdit instanceof Nurse;

        if (isNurse) {
            if (haveDateSlotIndexes || haveDatesSlots) {
                throw new CommandException(MESSAGE_NURSE_INVALID_DATESLOT_EDIT);
            }
        } else {
            if (haveUnavailableDates || haveUnavailableDateIndexes) {
                throw new CommandException(MESSAGE_PATIENT_INVALID_UNAVAILABLE_DATE_EDIT);
            }
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public Person createEditedPerson(Model model, List<Person> personList, Person personToEdit,
                                     EditPersonDescriptor editPersonDescriptor) throws CommandException {
        assert personToEdit != null;
        Category updatedCategory = editPersonDescriptor.getCategory().orElse(personToEdit.getCategory());
        Uid uid = editPersonDescriptor.getUid().orElse(personToEdit.getUid());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        if (personToEdit instanceof Patient && updatedCategory.isPatient()) {
            return createUpdatedPatient(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, editPersonDescriptor, personToEdit, model, personList);

        } else if (updatedCategory.isPatient()) {
            return createNewPatient(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, editPersonDescriptor);

        } else if (personToEdit instanceof Nurse && updatedCategory.isNurse()) {
            return createUpdatedNurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, editPersonDescriptor, personToEdit, model, personList);

        } else if (updatedCategory.isNurse()) {
            return createNewNurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress,
                    updatedTags, editPersonDescriptor);

        } else {
            throw new IllegalArgumentException(Category.MESSAGE_CONSTRAINTS);
        }
    }

    private Patient createUpdatedPatient(Uid uid, Name updatedName, Gender updatedGender, Phone updatedPhone,
                                         Email updatedEmail, Address updatedAddress, Set<Tag> updatedTags,
                                         EditPersonDescriptor editPersonDescriptor, Person personToEdit,
                                         Model model, List<Person> personList) throws CommandException {

        Optional<Physician> updatedPhysician = editPersonDescriptor.getPhysician()
                .orElse(((Patient) personToEdit).getAttendingPhysician());
        Optional<NextOfKin> updatedNextOfKin = editPersonDescriptor.getNextOfKin()
                .orElse(((Patient) personToEdit).getNextOfKin());

        List<DateSlot> originalDateSlot = ((Patient) personToEdit).getDatesSlots();
        Optional<List<DateSlot>> toBeUpdateDateSlot = editPersonDescriptor.getDatesSlots();
        Optional<List<Index>> toBeUpdateDateSlotIndexes = editPersonDescriptor.getDateSlotIndexes();
        EditedDateSlotCreator creator = new EditedDateSlotCreator(model, personList, originalDateSlot,
                toBeUpdateDateSlot, toBeUpdateDateSlotIndexes);
        List<DateSlot> updatedDateSlot = creator.createEditedDateSlotList();

        return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                updatedAddress, updatedTags, updatedDateSlot, updatedPhysician, updatedNextOfKin);
    }

    private Patient createNewPatient(Uid uid, Name updatedName, Gender updatedGender, Phone updatedPhone,
                                     Email updatedEmail, Address updatedAddress, Set<Tag> updatedTags,
                                     EditPersonDescriptor editPersonDescriptor) {

        Optional<Physician> updatedPhysician = editPersonDescriptor.getPhysician()
                .orElse(Optional.empty());
        Optional<NextOfKin> updatedNextOfKin = editPersonDescriptor.getNextOfKin()
                .orElse(Optional.empty());
        List<DateSlot> updatedDateSlot = editPersonDescriptor.getDatesSlots().orElse(null);

        return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                updatedAddress, updatedTags, updatedDateSlot, updatedPhysician, updatedNextOfKin);
    }

    private Nurse createUpdatedNurse(Uid uid, Name updatedName, Gender updatedGender, Phone updatedPhone,
                                     Email updatedEmail, Address updatedAddress, Set<Tag> updatedTags,
                                     EditPersonDescriptor editPersonDescriptor, Person personToEdit,
                                     Model model, List<Person> personList) throws CommandException {

        List<Date> originalDate = ((Nurse) personToEdit).getUnavailableDates();
        Optional<List<Date>> toBeUpdateDate = editPersonDescriptor.getUnavailableDates();
        Optional<List<Index>> toBeUpdateDateIndexes = editPersonDescriptor.getDateIndexes();
        EditedUnavailableDateCreator creator = new EditedUnavailableDateCreator(model, personToEdit, personList,
                originalDate, toBeUpdateDate, toBeUpdateDateIndexes);
        List<Date> updatedUnavailableDate = creator.createEditedUnavailableDateList(editPersonDescriptor);

        List<Date> updatedFullyScheduledDateList = editPersonDescriptor.getFullyScheduledDates()
                .orElse(((Nurse) personToEdit).getFullyScheduledDates());
        List<HomeVisit> updatedHomeVisitList = editPersonDescriptor.getHomeVisits()
                .orElse(((Nurse) personToEdit).getHomeVisits());

        return new Nurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedUnavailableDate, updatedHomeVisitList, updatedFullyScheduledDateList);
    }

    private Nurse createNewNurse(Uid uid, Name updatedName, Gender updatedGender, Phone updatedPhone,
                                     Email updatedEmail, Address updatedAddress, Set<Tag> updatedTags,
                                     EditPersonDescriptor editPersonDescriptor) {

        List<Date> updatedUnavailableDate = editPersonDescriptor.getUnavailableDates().orElse(null);
        List<HomeVisit> updatedHomeVisitList = editPersonDescriptor.getHomeVisits().orElse(null);
        List<Date> updatedFullyScheduledDateList = editPersonDescriptor.getFullyScheduledDates()
                .orElse(null);

        return new Nurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedUnavailableDate, updatedHomeVisitList, updatedFullyScheduledDateList);
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
        private List<HomeVisit> homeVisits;
        private List<Date> unavailableDates;
        private List<Index> dateIndexes;
        private List<Date> fullyScheduledDates;
        private Optional<Physician> physician;
        private Optional<NextOfKin> nextOfKin;

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
            setHomeVisits(toCopy.homeVisits);
            setUnavailableDates(toCopy.unavailableDates);
            setDateIndexes(toCopy.dateIndexes);
            setFullyScheduledDates(toCopy.fullyScheduledDates);
            setPhysician(toCopy.physician);
            setNextOfKin(toCopy.nextOfKin);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(category, name, gender, phone, email, address,
                    tags, datesSlots, dateSlotIndexes, unavailableDates, dateIndexes);
        }

        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        /**
         * @return the id
         */
        public Optional<Uid> getUid() {
            return Optional.ofNullable(uid);
        }

        /**
         * @param uid the id to set
         */
        public void setUid(Uid uid) {
            this.uid = uid;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
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
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns a dateSlot list
         * Returns {@code Optional#empty()} if {@code dateSlots} is null.
         */
        public Optional<List<DateSlot>> getDatesSlots() {
            return (datesSlots != null) ? Optional.of(new ArrayList<DateSlot>(datesSlots)) : Optional.empty();
        }

        /**
         * Sets {@code datesSlots} to this object's {@code datesSlots}.
         */
        public void setDatesSlots(List<DateSlot> datesSlots) {
            this.datesSlots = (datesSlots != null) ? new ArrayList<DateSlot>(datesSlots) : null;
        }

        /**
         * Returns a dateSlotIndexes list
         * Returns {@code Optional#empty()} if {@code dateSlotIndexes} is null.
         */
        public Optional<List<Index>> getDateSlotIndexes() {
            return (dateSlotIndexes != null) ? Optional.of(new ArrayList<Index>(dateSlotIndexes)) : Optional.empty();
        }

        /**
         * Sets {@code dateSlotIndexes} to this object's {@code dateSlotIndexes}.
         */
        public void setDateSlotIndexes(List<Index> dateSlotIndexes) {
            this.dateSlotIndexes = (dateSlotIndexes != null) ? new ArrayList<Index>(dateSlotIndexes) : null;
        }

        /**
         * Returns a homeVisit list
         * Returns {@code Optional#empty()} if {@code homeVisits} is null.
         */
        public Optional<List<HomeVisit>> getHomeVisits() {
            return (homeVisits != null) ? Optional.of(new ArrayList<HomeVisit>(homeVisits)) : Optional.empty();
        }

        /**
         * Sets {@code homeVisits} to this object's {@code homeVisits}.
         */
        public void setHomeVisits(List<HomeVisit> homeVisits) {
            this.homeVisits = (homeVisits != null) ? new ArrayList<HomeVisit>(homeVisits) : null;
        }

        /**
         * Returns a unavailableDate list
         * Returns {@code Optional#empty()} if {@code unavailableDate} is null.
         */
        public Optional<List<Date>> getUnavailableDates() {
            return (unavailableDates != null) ? Optional.of(new ArrayList<Date>(unavailableDates)) : Optional.empty();
        }

        /**
         * Sets {@code unavailableDate} to this object's {@code unavailableDates}.
         */
        public void setUnavailableDates(List<Date> unavailableDates) {
            this.unavailableDates = (unavailableDates != null) ? new ArrayList<Date>(unavailableDates) : null;
        }

        /**
         * Returns a dateIndexes list
         * Returns {@code Optional#empty()} if {@code dateIndexes} is null.
         */
        public Optional<List<Index>> getDateIndexes() {
            return (dateIndexes != null) ? Optional.of(new ArrayList<Index>(dateIndexes)) : Optional.empty();
        }

        /**
         * Sets {@code dateIndexes} to this object's {@code dateIndexes}.
         */
        public void setDateIndexes(List<Index> dateIndexes) {
            this.dateIndexes = (dateIndexes != null) ? new ArrayList<Index>(dateIndexes) : null;
        }

        /**
         * Returns a fullyScheduledlist
         * Returns {@code Optional#empty()} if {@code fullyScheduledDates} is null.
         */
        public Optional<List<Date>> getFullyScheduledDates() {
            return (fullyScheduledDates != null) ? Optional.of(new ArrayList<Date>(fullyScheduledDates))
                    : Optional.empty();
        }

        /**
         * Sets {@code fullyScheduledDates} to this object's {@code fullScheduledDates}.
         */
        public void setFullyScheduledDates(List<Date> fullyScheduledDates) {
            this.fullyScheduledDates = (fullyScheduledDates != null) ? new ArrayList<Date>(fullyScheduledDates) : null;
        }

        /**
         * Returns the attending physician
         *
         * @return {@code Optional#empty()} if {@code physician} is null.
         */
        public Optional<Optional<Physician>> getPhysician() {
            return (physician != null) ? Optional.of(physician) : Optional.empty();
        }

        /**
         * Sets {@code p} to this object's {@code physician}.
         *
         * @return
         */
        public EditPersonDescriptor setPhysician(Optional<Physician> p) {
            physician = p;
            return this;
        }

        /**
         * Returns the next of kin
         *
         * @return {@code Optioanl.empty()} if {@code nextOfKin} is null.
         */
        public Optional<Optional<NextOfKin>> getNextOfKin() {
            return (nextOfKin != null) ? Optional.of(nextOfKin) : Optional.empty();
        }

        /**
         * Sets {@code n} to this object's {@code nextOfKin}
         *
         * @return
         */
        public EditPersonDescriptor setNextOfKin(Optional<NextOfKin> n) {
            nextOfKin = n;
            return this;
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
                    && getHomeVisits().equals(e.getHomeVisits())
                    && getUnavailableDates().equals(e.getUnavailableDates())
                    && getDateIndexes().equals(e.getDateIndexes())
                    && getFullyScheduledDates().equals(e.getFullyScheduledDates());
        }
    }

    public static class EditedDateSlotCreator {

        public static final String MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX = "The dateSlot index "
                + "provided is more than the dateSlot provided. "
                + "Please remove the dateSlot index or add more dateSlot.";
        private final Model model;
        private final List<Person> personList;
        private final List<DateSlot> originalDateSlotList;
        private final List<DateSlot> toBeUpdateDateSlots;
        private final List<Index> toBeUpdateDateSlotsIndexes;
        private final Boolean isDateSlotsGivenNull;
        private final Boolean isDateSlotIndexesGivenNull;
        private final Boolean isDateSlotsGivenEmpty;
        private final Boolean isDateSlotIndexesGivenEmpty;


        EditedDateSlotCreator(Model model, List<Person> personList, List<DateSlot> originalDateSlots,
                              Optional<List<DateSlot>> toBeUpdateDateSlots,
                              Optional<List<Index>> toBeUpdateDateSlotsIndexes) {
            this.model = model;
            this.personList = personList;
            this.originalDateSlotList = originalDateSlots;
            this.isDateSlotsGivenNull = getIsDateSlotsGivenNull(toBeUpdateDateSlots);
            this.isDateSlotIndexesGivenNull = getIsDateSlotIndexesGivenNull(toBeUpdateDateSlotsIndexes);
            this.toBeUpdateDateSlots = createToBeUpdateDateSlotList(toBeUpdateDateSlots);
            this.toBeUpdateDateSlotsIndexes = createToBeUpdatedIndexList(toBeUpdateDateSlotsIndexes);
            this.isDateSlotsGivenEmpty = getIsDateSlotsGivenEmpty();
            this.isDateSlotIndexesGivenEmpty = getIsDateSlotIndexesGivenEmpty();
        }

        private boolean getIsDateSlotsGivenNull(Optional<List<DateSlot>> toBeUpdateDateSlots) {
            return toBeUpdateDateSlots.equals(Optional.empty());
        }

        private boolean getIsDateSlotIndexesGivenNull(Optional<List<Index>> toBeUpdateDateSlotIndexes) {
            return toBeUpdateDateSlotIndexes.equals(Optional.empty());
        }

        private List<DateSlot> createToBeUpdateDateSlotList(Optional<List<DateSlot>> toBeUpdateDateSlots) {
            List<DateSlot> toBeUpdateDateSlotList = new ArrayList<>();
            if (!isDateSlotsGivenNull) {
                toBeUpdateDateSlotList = new ArrayList<>(toBeUpdateDateSlots.get());
            }
            return toBeUpdateDateSlotList;
        }

        private List<Index> createToBeUpdatedIndexList(Optional<List<Index>> toBeUpdateDateSlotsIndexes) {
            List<Index> toBeUpdateDateSlotIndexList = new ArrayList<>();
            if (!isDateSlotIndexesGivenNull) {
                toBeUpdateDateSlotIndexList = new ArrayList<>(toBeUpdateDateSlotsIndexes.get());
            }
            return toBeUpdateDateSlotIndexList;
        }

        private boolean getIsDateSlotsGivenEmpty() {
            return !isDateSlotsGivenNull && toBeUpdateDateSlots.isEmpty();
        }

        private boolean getIsDateSlotIndexesGivenEmpty() {
            return !isDateSlotIndexesGivenNull && toBeUpdateDateSlotsIndexes.isEmpty();
        }
        private List<DateSlot> createEditedDateSlotList() throws CommandException {

            if (isDelete()) {
                // Deletes all the dateTime in the existing list/ Deletes specific dateTime in the existing list
                return removeActionForDateSlot();

            } else if (isAdd()) {
                // Add the given dateTime to the existing list
                return addActionForDateSlot();

            } else if (isEdit()) {
                // Remove specific dateSlot in the existing list and add the given dateSlot
                return editActionForDateSlot();

            } else {
                // Remain as original dateTime list, no changes made
                return originalDateSlotList;
            }
        }

        private Boolean isDelete() {
            Boolean isAllDeleted =  (isDateSlotsGivenNull && isDateSlotIndexesGivenEmpty) ||
                    (isDateSlotIndexesGivenNull && isDateSlotsGivenEmpty) ||
                    (isDateSlotsGivenEmpty && isDateSlotIndexesGivenEmpty);
            Boolean isSpecificDeleted = !isDateSlotIndexesGivenEmpty && (isDateSlotsGivenNull || isDateSlotsGivenEmpty);
            return isAllDeleted || isSpecificDeleted;
        }

        private Boolean isAdd() {
            return !isDateSlotsGivenEmpty && (isDateSlotIndexesGivenNull || isDateSlotIndexesGivenEmpty);
        }

        private Boolean isEdit() {
            return !isDateSlotsGivenEmpty && !isDateSlotIndexesGivenEmpty;
        }

        private List<DateSlot> removeActionForDateSlot() throws CommandException {
            DateSlotManager remover = new DateSlotManager(originalDateSlotList, toBeUpdateDateSlotsIndexes);
            InternalHomeVisitRemoverFromDateSlot homeVisitRemover = new InternalHomeVisitRemoverFromDateSlot(model, personList,
                    originalDateSlotList, toBeUpdateDateSlotsIndexes);
            homeVisitRemover.removeHomeVisitsForDateSlot();
            return remover.removeDateSlot();
        }

        private List<DateSlot> addActionForDateSlot() {
            DateSlotManager adder = new DateSlotManager(originalDateSlotList);
            return adder.addDateSlot(toBeUpdateDateSlots);
        }

        private List<DateSlot> editActionForDateSlot() throws CommandException {
            checkDateSlotAndIndex();
            DateSlotManager editor = new DateSlotManager(originalDateSlotList);
            InternalHomeVisitRemoverFromDateSlot homeVisitRemover = new InternalHomeVisitRemoverFromDateSlot(model, personList,
                    originalDateSlotList, toBeUpdateDateSlotsIndexes);
            homeVisitRemover.removeHomeVisitsForDateSlot();
            editor.removeDateSlot();
            return editor.addDateSlot(toBeUpdateDateSlots);
        }

        private void checkDateSlotAndIndex() throws CommandException {
            if (toBeUpdateDateSlots.size() < toBeUpdateDateSlotsIndexes.size()) {
                throw new CommandException(MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX);
            }
        }
    }

    public static class EditedUnavailableDateCreator {

        public static final String MESSAGE_OUT_OF_BOUND_UNAVAILABLE_DATE_INDEX = "The unavailable date index "
                + "given is out of bounds of the existing list."
                + " Please retype another index that is within the range or left it empty.";

        public static final String MESSAGE_INVALID_NUMBERS_OF_UNAVAILABLE_DATES_AND_UNAVAILABLE_DATE_INDEXES =
                "The unavailable date index " + "provided is more than the unavailable date provided."
                        + " Please remove the unavailable date index or add more unavailable date.";

        private final Model model;
        private final Person nurseToEdit;
        private final List<Person> personList;
        private final List<Date> originalUnavailableDateList;
        private final List<Date> toBeUpdateUnavailableDates;
        private final List<Index> toBeUpdateUnavailableDateIndexes;
        private final Boolean isUnavailableDatesGivenNull;
        private final Boolean isUnavailableDateIndexesGivenNull;
        private final Boolean isUnavailableDatesGivenEmpty;
        private final Boolean isUnavailableDateIndexesGivenEmpty;


        EditedUnavailableDateCreator(Model model, Person nurseToEdit, List<Person> personList,
                                     List<Date> originalUnavailableDates,
                                     Optional<List<Date>> toBeUpdateUnavailableDates,
                                     Optional<List<Index>> toBeUpdateUnavailableDateIndexes) {
            this.model = model;
            this.nurseToEdit = nurseToEdit;
            this.personList = personList;
            this.originalUnavailableDateList = originalUnavailableDates;
            this.isUnavailableDatesGivenNull = getIsUnavailableDatesGivenNull(toBeUpdateUnavailableDates);
            this.isUnavailableDateIndexesGivenNull = getIsUnavailableDateIndexesGivenNull(
                    toBeUpdateUnavailableDateIndexes);
            this.toBeUpdateUnavailableDates = createToBeUpdateUnavailableDateList(toBeUpdateUnavailableDates);
            this.toBeUpdateUnavailableDateIndexes = createToBeUpdatedIndexList(toBeUpdateUnavailableDateIndexes);
            this.isUnavailableDatesGivenEmpty = getIsUnavailableDatesGivenEmpty();
            this.isUnavailableDateIndexesGivenEmpty = getIsUnavailableDateIndexesGivenEmpty();
        }

        private boolean getIsUnavailableDatesGivenNull(Optional<List<Date>> toBeUpdateUnavailableDates) {
            return toBeUpdateUnavailableDates.equals(Optional.empty());
        }

        private boolean getIsUnavailableDateIndexesGivenNull(Optional<List<Index>> toBeUpdateUnavailableDateIndexes) {
            return toBeUpdateUnavailableDateIndexes.equals(Optional.empty());
        }

        private List<Date> createToBeUpdateUnavailableDateList(Optional<List<Date>> toBeUpdateUnavailableDates) {
            List<Date> toBeUpdateUnavailableDateList = new ArrayList<>();
            if (!isUnavailableDatesGivenNull) {
                toBeUpdateUnavailableDateList = new ArrayList<>(toBeUpdateUnavailableDates.get());
            }
            return toBeUpdateUnavailableDateList;
        }

        private List<Index> createToBeUpdatedIndexList(Optional<List<Index>> toBeUpdateUnavailableDateIndexes) {
            List<Index> toBeUpdateUnavailableDateIndexList = new ArrayList<>();
            if (!isUnavailableDateIndexesGivenNull) {
                toBeUpdateUnavailableDateIndexList = new ArrayList<>(toBeUpdateUnavailableDateIndexes.get());
            }
            return toBeUpdateUnavailableDateIndexList;
        }

        private boolean getIsUnavailableDatesGivenEmpty() {
            return !isUnavailableDatesGivenNull && toBeUpdateUnavailableDates.isEmpty();
        }

        private boolean getIsUnavailableDateIndexesGivenEmpty() {
            return !isUnavailableDateIndexesGivenNull && toBeUpdateUnavailableDateIndexes.isEmpty();
        }
        private List<Date> createEditedUnavailableDateList(EditPersonDescriptor editPersonDescriptor)
                throws CommandException {

            if (isAllDelete()) {
                // Deletes all the unavailable dates in the existing list
                return removeAllActionForUnavailableDate();

            } else if (isSpecificDelete()) {
                // Deletes specific unavailable dates in the existing list
                return removeSpecificActionForUnavailableDate();

            } else if (isAdd()) {
                // Add the given unavailable dates to the existing list
                return addActionForUnavailableDate(editPersonDescriptor);

            } else if (isEdit()) {
                // Remove specific unavailable dates in the existing list and add the given unavailable dates
                return editActionForUnavailableDates(editPersonDescriptor);

            } else {
                // Remain as original unavailable dates list, no changes made
                return originalUnavailableDateList;
            }
        }

        private Boolean isAllDelete() {
            return (isUnavailableDatesGivenNull && isUnavailableDateIndexesGivenEmpty) ||
                    (isUnavailableDateIndexesGivenNull && isUnavailableDatesGivenEmpty) ||
                    (isUnavailableDatesGivenEmpty && isUnavailableDateIndexesGivenEmpty);
        }

        private Boolean isSpecificDelete() {
            return !isUnavailableDateIndexesGivenEmpty && (isUnavailableDatesGivenNull ||
                    isUnavailableDatesGivenEmpty);
        }

        private Boolean isAdd() {
            return !isUnavailableDatesGivenEmpty && (isUnavailableDateIndexesGivenNull ||
                    isUnavailableDateIndexesGivenEmpty);
        }

        private Boolean isEdit() {
            return !isUnavailableDatesGivenEmpty && !isUnavailableDateIndexesGivenEmpty;
        }

        private List<Date> removeAllActionForUnavailableDate() throws CommandException {
            return new ArrayList<>();
        }

        private List<Date> removeSpecificActionForUnavailableDate() throws CommandException {
            sortIndex();
            checkIndexOutOfBound();
            for(Index index : toBeUpdateUnavailableDateIndexes) {
                Date unavailableDate = originalUnavailableDateList.get(index.getZeroBased());
                originalUnavailableDateList.remove(unavailableDate);
            }
            return originalUnavailableDateList;
        }

        private void sortIndex() {
            ReverseIndexComparator comp = new ReverseIndexComparator();
            this.toBeUpdateUnavailableDateIndexes.sort(comp);
        }

        public void checkIndexOutOfBound() throws CommandException {
            if (toBeUpdateUnavailableDateIndexes.get(0).getZeroBased() >= originalUnavailableDateList.size()) {
                throw new CommandException(MESSAGE_OUT_OF_BOUND_UNAVAILABLE_DATE_INDEX);
            }
        }

        private List<Date> addActionForUnavailableDate(EditPersonDescriptor editPersonDescriptor) {
            List<HomeVisit> homeVisitList = ((Nurse) nurseToEdit).getHomeVisits();
            List<Date> fullyScheduledDateList = ((Nurse) nurseToEdit).getFullyScheduledDates();
            InternalUnmarkerFromHomeVisit unmarker = new InternalUnmarkerFromHomeVisit(model,
                    personList, homeVisitList);
            unmarker.unmarkDateSlotForUnavailableDates(toBeUpdateUnavailableDates);

            HomeVisitManager remover = new HomeVisitManager(homeVisitList, fullyScheduledDateList);
            List<HomeVisit> updatedHomeVisitList = remover.removeHomeVisitFromUnavailableDates(
                    toBeUpdateUnavailableDates);
            List<Date> updatedFullyScheduledDateList = remover.getFullyScheduledDateList();
            editPersonDescriptor.setHomeVisits(updatedHomeVisitList);
            editPersonDescriptor.setFullyScheduledDates(updatedFullyScheduledDateList);

            originalUnavailableDateList.addAll(toBeUpdateUnavailableDates);
            return originalUnavailableDateList;
        }

        private List<Date> editActionForUnavailableDates(EditPersonDescriptor editPersonDescriptor)
                throws CommandException {
            checkDateSlotAndIndex();
            removeSpecificActionForUnavailableDate();
            addActionForUnavailableDate(editPersonDescriptor);
            return originalUnavailableDateList;
        }

        private void checkDateSlotAndIndex() throws CommandException {
            if (toBeUpdateUnavailableDates.size() < toBeUpdateUnavailableDateIndexes.size()) {
                throw new CommandException(MESSAGE_INVALID_NUMBERS_OF_UNAVAILABLE_DATES_AND_UNAVAILABLE_DATE_INDEXES);
            }
        }

    }


}

