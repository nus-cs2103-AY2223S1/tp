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

        boolean haveDatesSlots = editPersonDescriptor.getDatesSlots().isPresent();
        boolean haveDateSlotIndexes = editPersonDescriptor.getDateSlotIndexes().isPresent();
        boolean haveUnavailableDates = editPersonDescriptor.getUnavailableDates().isPresent();
        boolean haveUnavailableDateIndexes = editPersonDescriptor.getDateIndexes().isPresent();
        boolean isNurse = editPersonDescriptor.getCategory().equals("N") || confirmedPersonToEdit instanceof Nurse;

        if (isNurse) {
            if (haveDateSlotIndexes || haveDatesSlots) {
                throw new CommandException(MESSAGE_NURSE_INVALID_DATESLOT_EDIT);
            }
        } else {
            if (haveUnavailableDates || haveUnavailableDateIndexes) {
                throw new CommandException(MESSAGE_PATIENT_INVALID_UNAVAILABLE_DATE_EDIT);
            }
        }

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

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public Person createEditedPerson(Model model, List<Person> personList,
            Person personToEdit, EditPersonDescriptor editPersonDescriptor)
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

        if (personToEdit instanceof Patient && updatedCategory.isPatient()) {
            Optional<Physician> updatedPhysician = editPersonDescriptor.getPhysician()
                    .orElse(((Patient) personToEdit).getAttendingPhysician());
            Optional<NextOfKin> updatedNextOfKin = editPersonDescriptor.getNextOfKin()
                    .orElse(((Patient) personToEdit).getNextOfKin());
            List<DateSlot> originalDateSlot = ((Patient) personToEdit).getDatesSlots();
            Optional<List<DateSlot>> toBeUpdateDateSlot = editPersonDescriptor.getDatesSlots();
            Optional<List<Index>> toBeUpdateDateSlotIndexes = editPersonDescriptor.getDateSlotIndexes();

            List<DateSlot> updatedDateSlot = createEditedDateSlotList(originalDateSlot,
                    toBeUpdateDateSlot, toBeUpdateDateSlotIndexes, model, personList);

            return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedDateSlot,
                    updatedPhysician, updatedNextOfKin);

        } else if (updatedCategory.isPatient()) {
            Optional<Physician> updatedPhysician = editPersonDescriptor.getPhysician()
                    .orElse(((Patient) personToEdit).getAttendingPhysician());
            Optional<NextOfKin> updatedNextOfKin = editPersonDescriptor.getNextOfKin()
                    .orElse(((Patient) personToEdit).getNextOfKin());
            List<DateSlot> updatedDateSlot = editPersonDescriptor.getDatesSlots().orElse(null);

            return new Patient(uid, updatedName, updatedGender, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedDateSlot,
                    updatedPhysician, updatedNextOfKin);

        } else if (personToEdit instanceof Nurse && updatedCategory.isNurse()) {
            List<Date> originalDate = ((Nurse) personToEdit).getUnavailableDates();
            Optional<List<Date>> toBeUpdateDate = editPersonDescriptor.getUnavailableDates();
            Optional<List<Index>> toBeUpdateDateIndexes = editPersonDescriptor.getDateIndexes();

            List<Date> updatedUnavailableDate = createEditedUnavailableDateList(originalDate, toBeUpdateDate,
                    toBeUpdateDateIndexes, model, personList, personToEdit);

            List<Date> updatedFullyScheduledDateList = editPersonDescriptor.getFullyScheduledDates()
                    .orElse(((Nurse) personToEdit).getFullyScheduledDates());
            List<HomeVisit> updatedHomeVisitList = editPersonDescriptor.getHomeVisits()
                    .orElse(((Nurse) personToEdit).getHomeVisits());

            return new Nurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                    updatedUnavailableDate, updatedHomeVisitList, updatedFullyScheduledDateList);

        } else if (updatedCategory.isNurse()) {
            List<Date> updatedUnavailableDate = editPersonDescriptor.getUnavailableDates().orElse(null);
            List<HomeVisit> updatedHomeVisitList = editPersonDescriptor.getHomeVisits().orElse(null);
            List<Date> updatedFullyScheduledDateList = editPersonDescriptor.getFullyScheduledDates()
                    .orElse(null);

            return new Nurse(uid, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                    updatedUnavailableDate, updatedHomeVisitList, updatedFullyScheduledDateList);
        } else {
            throw new IllegalArgumentException(Category.MESSAGE_CONSTRAINTS);
        }
    }

    private List<DateSlot> createEditedDateSlotList(List<DateSlot> originalDateSlot,
            Optional<List<DateSlot>> toBeUpdateDateSlots,
            Optional<List<Index>> toBeUpdateDateSlotsIndexes,
            Model model, List<Person> personList)
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

            // If the indexNo given is out of bounds of the existing list -> throw exception
            for (Index indexNo : toBeUpdateDateSlotIndexes) {

                if (indexNo.getZeroBased() >= originalDateSlot.size()) {
                    throw new CommandException(MESSAGE_OUT_OF_BOUND_DATESLOTINDEX);
                }

            }
        }

        boolean isDateSlotEmpty = !isDateSlotNull && toBeUpdateDateSlot.isEmpty();
        boolean isDateSlotIndexesEmpty = !isDateSlotIndexesNull && toBeUpdateDateSlotIndexes.isEmpty();

        // Deletes all the dateTime in the existing list
        if ((isDateSlotNull && isDateSlotIndexesEmpty) || (isDateSlotIndexesNull && isDateSlotEmpty)
                || (isDateSlotEmpty && isDateSlotIndexesEmpty)) {

            updatedDateSlot = new ArrayList<>();
            deleteRespectiveHomeVisit(originalDateSlot, model, personList);

            // Deletes the dateTime at specific index in the existing list
        } else if ((isDateSlotNull || isDateSlotEmpty) && !isDateSlotIndexesEmpty) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);

            ReverseIndexComparator comp = new ReverseIndexComparator();
            toBeUpdateDateSlotIndexes.sort(comp);
            for (Index index : toBeUpdateDateSlotIndexes) {
                DateSlot dateSlotToBeDeleted = updatedDateSlot.get(index.getZeroBased());
                if (dateSlotToBeDeleted.getHasAssigned()) {
                    removeHomeVisit(model, dateSlotToBeDeleted, personList);
                }
                updatedDateSlot.remove(index.getZeroBased());
            }

            // Remain as original dateTime list, no changes made
        } else if (isDateSlotNull && isDateSlotIndexesNull) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);

            // Add the given dateTime to the existing list
        } else if (!isDateSlotEmpty && (isDateSlotIndexesNull || isDateSlotIndexesEmpty)) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);
            updatedDateSlot.addAll(toBeUpdateDateSlot);

            // Change the dateTime at the specific index in the existing list to the given
            // dateTime
            // If index given more than date time given -> throws exception
            // If date time given more than index given -> the extra date time will be added
            // to the existing list
        } else if (!isDateSlotEmpty && !isDateSlotIndexesEmpty) {

            updatedDateSlot = new ArrayList<>(originalDateSlot);
            if (toBeUpdateDateSlot.size() < toBeUpdateDateSlotIndexes.size()) {
                throw new CommandException(MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX);
            }

            for (int i = 0; i < toBeUpdateDateSlotIndexes.size(); i++) {
                int indexNoToBeUpdated = toBeUpdateDateSlotIndexes.get(i).getZeroBased();
                DateSlot dateSlotToBeUpdated = toBeUpdateDateSlot.get(i);
                DateSlot dateSlotToBeDeleted = updatedDateSlot.get(indexNoToBeUpdated);
                if (dateSlotToBeDeleted.getHasAssigned()) {
                    removeHomeVisit(model, dateSlotToBeDeleted, personList);
                }
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

    private List<Date> createEditedUnavailableDateList(List<Date> originalDate,
            Optional<List<Date>> toBeUpdateDates,
            Optional<List<Index>> toBeUpdateDateIndexes,
            Model model, List<Person> personList, Person nurse)
            throws CommandException {
        List<Date> updatedDate = new ArrayList<>();

        boolean isDateNull = toBeUpdateDates.equals(Optional.empty());
        boolean isDateIndexesNull = toBeUpdateDateIndexes.equals(Optional.empty());
        List<Date> toBeUpdateDate = new ArrayList<>();
        List<Index> toBeUpdateDateIndex = new ArrayList<>();

        if (!isDateNull) {
            toBeUpdateDate = new ArrayList<>(toBeUpdateDates.get());
        }
        if (!isDateIndexesNull) {
            toBeUpdateDateIndex = new ArrayList<>(toBeUpdateDateIndexes.get());

            // If the indexNo given is out of bounds of the existing list -> throw exception
            for (Index indexNo : toBeUpdateDateIndex) {

                if (indexNo.getZeroBased() >= originalDate.size()) {
                    throw new CommandException(MESSAGE_OUT_OF_BOUND_DATESLOTINDEX);
                }
            }
        }

        boolean isDateEmpty = !isDateNull && toBeUpdateDate.isEmpty();
        boolean isDateIndexesEmpty = !isDateIndexesNull && toBeUpdateDateIndex.isEmpty();

        // Deletes all the date in the existing list
        if ((isDateNull && isDateIndexesEmpty) || (isDateIndexesNull && isDateEmpty)
                || (isDateEmpty && isDateIndexesEmpty)) {

            updatedDate = new ArrayList<>();

            // Deletes the date at specific index in the existing list
        } else if ((isDateNull || isDateEmpty) && !isDateIndexesEmpty) {

            updatedDate = new ArrayList<>(originalDate);

            ReverseIndexComparator comp = new ReverseIndexComparator();
            toBeUpdateDateIndex.sort(comp);
            for (Index index : toBeUpdateDateIndex) {
                updatedDate.remove(index.getZeroBased());
            }

            // Remain as original date list, no changes made
        } else if (isDateNull && isDateIndexesNull) {

            updatedDate = new ArrayList<>(originalDate);

            // Add the given date to the existing list
        } else if (!isDateEmpty && (isDateIndexesNull || isDateIndexesEmpty)) {

            updatedDate = new ArrayList<>(originalDate);
            for (Date date : toBeUpdateDate) {
                removeAndUnmarkRepectiveHomeVisitAndDateSlot(model, nurse, date, personList);
                updatedDate.add(date);
            }

            // Change the date at the specific index in the existing list to the given date
            // If index given more than date given -> throws exception
            // If date given more than index given -> the extra date will be added to the
            // existing list
        } else if (!isDateEmpty && !isDateIndexesEmpty) {

            updatedDate = new ArrayList<>(originalDate);
            if (toBeUpdateDate.size() < toBeUpdateDateIndex.size()) {
                throw new CommandException(MESSAGE_INVALID_NUMBERS_OF_DATESLOT_AND_DATESLOTINDEX);
            }

            for (int i = 0; i < toBeUpdateDateIndex.size(); i++) {
                int indexNoToBeUpdated = toBeUpdateDateIndex.get(i).getZeroBased();
                Date dateToBeUpdated = toBeUpdateDate.get(i);
                removeAndUnmarkRepectiveHomeVisitAndDateSlot(model, nurse, dateToBeUpdated, personList);
                updatedDate.set(indexNoToBeUpdated, dateToBeUpdated);
            }

            if (toBeUpdateDate.size() > toBeUpdateDateIndex.size()) {
                for (int i = toBeUpdateDateIndex.size(); i < toBeUpdateDate.size(); i++) {
                    removeAndUnmarkRepectiveHomeVisitAndDateSlot(model, nurse, toBeUpdateDate.get(i), personList);
                    updatedDate.add(toBeUpdateDate.get(i));
                }
            }

        }
        return updatedDate;

    }

    private Boolean deleteRespectiveHomeVisit(List<DateSlot> dateSlotList, Model model, List<Person> personList)
            throws CommandException {
        boolean hasDeleted = false;
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

    private boolean unmarkRespectiveDateSlot(Model model, Person person, List<Person> personList)
            throws CommandException {
        List<HomeVisit> homeVisitList = ((Nurse) person).getHomeVisits();
        if (homeVisitList.size() == 0) {
            return false;
        } else {
            for (HomeVisit homevisit : homeVisitList) {
                Long patientUidNo = homevisit.getHomeVisitPatientUidNo();
                DateSlot dateSlot = homevisit.getDateSlot();
                unmarkDateSlot(model, dateSlot, patientUidNo, personList);
            }
            return true;
        }
    }

    private void removeAndUnmarkRepectiveHomeVisitAndDateSlot(Model model, Person nurse,
            Date unavailableDate, List<Person> personList)
            throws CommandException {

        List<HomeVisit> homeVisitList = ((Nurse) nurse).getHomeVisits();
        List<HomeVisit> updatedHomeVisitList = new ArrayList<>(homeVisitList);
        List<Date> fullyScheduledList = ((Nurse) nurse).getFullyScheduledDates();
        List<Date> updatedFullyScheduledList = new ArrayList<>(fullyScheduledList);
        for (HomeVisit homeVisit : homeVisitList) {
            if (homeVisit.getDateSlot().getDate().equals(unavailableDate.getDate())) {
                Long patientUid = homeVisit.getHomeVisitPatientUidNo();
                unmarkDateSlot(model, homeVisit.getDateSlot(), patientUid, personList);
                HomeVisit homeVisitToBeDeleted = updatedHomeVisitList.stream().filter(
                        h -> h.getDateSlot().getDateTime().equals(homeVisit.getDateSlot().getDateTime()))
                        .findFirst().get();
                updatedHomeVisitList.remove(homeVisitToBeDeleted);
            }
        }

        fullyScheduledList.remove(unavailableDate);

        editNurseForUnavailableDate(updatedHomeVisitList, updatedFullyScheduledList);
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

    private void unmarkDateSlot(Model model, DateSlot dateslot, Long patientUidNo, List<Person> personList) {
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
        EditCommand.EditPersonDescriptor editPersonDescriptor1 = new EditCommand.EditPersonDescriptor();
        editPersonDescriptor1.setHomeVisits(homeVisitList);
        editPersonDescriptor1.setFullyScheduledDates(fullyScheduledDateList);
        EditCommand editCommand1 = new EditCommand(uid, editPersonDescriptor1);
        editCommand1.execute(model);
    }

    private void editNurseForUnavailableDate(List<HomeVisit> homeVisitList, List<Date> fullyScheduledDateList) {
        this.editPersonDescriptor.setHomeVisits(homeVisitList);
        this.editPersonDescriptor.setFullyScheduledDates(fullyScheduledDateList);
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
}
